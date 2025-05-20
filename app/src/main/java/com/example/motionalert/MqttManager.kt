package com.example.motionalert

import android.content.Context
import android.util.Log
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.datatypes.MqttQos
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*

object MqttManager {
    private const val SERVER = "1c96de2709a740a68e5b6d81365811b2.s1.eu.hivemq.cloud"
    private const val USERNAME = "duyduong"
    private const val PASSWORD = "Duy10012004/"
    private const val TOPIC = "/alert/motion"

    private val client = MqttClient.builder()
        .useMqttVersion3()
        .serverHost(SERVER)
        .serverPort(8883)
        .sslWithDefaultConfig()
        .buildAsync()

    private var isConnected = false

    fun connect(context: Context, onEventReceived: (IntrusionEvent) -> Unit) {
        if (isConnected) return

        client.connectWith()
            .simpleAuth()
            .username(USERNAME)
            .password(StandardCharsets.UTF_8.encode(PASSWORD))
            .applySimpleAuth()
            .send()
            .whenComplete { _, ex ->
                if (ex != null) {
                    Log.e("MQTT", "Connect failed", ex)
                    return@whenComplete
                }

                isConnected = true
                Log.d("MQTT", "Connected successfully!")

                client.subscribeWith()
                    .topicFilter(TOPIC)
                    .qos(MqttQos.AT_LEAST_ONCE)
                    .send()
                    .whenComplete { _, subEx ->
                        if (subEx != null) {
                            Log.e("MQTT", "Subscription failed", subEx)
                        } else {
                            Log.i("MQTT", "Subscribed to topic: $TOPIC")
                        }
                    }

                client.publishes(MqttGlobalPublishFilter.ALL) { publish ->
                    val json = try {
                        publish.payload.orElse(null)?.let { buffer ->
                            val bytes = ByteArray(buffer.remaining())
                            buffer.get(bytes)
                            String(bytes, StandardCharsets.UTF_8)
                        }
                    } catch (e: Exception) {
                        Log.e("MQTT", "Error decoding payload", e)
                        null
                    }

                    Log.d("MQTT", "Received: $json")

                    if (json != null && json.contains("motion_detected")) {
                        val time = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(Date())
                        val message = IntrusionEvent("Phát hiện xâm nhập!", "ESP32", time)
                        StorageHelper.saveEvent(context, message)

                        // Hiển thị thông báo
                        NotificationUtils.showNotification(
                            context,
                            "Cảnh báo xâm nhập",
                            "${message.message} lúc ${message.timestamp}"
                        )

                        onEventReceived(message)
                    }
                }
            }
    }

    fun disconnect() {
        if (isConnected) {
            client.disconnect()
            isConnected = false
        }
    }
}
