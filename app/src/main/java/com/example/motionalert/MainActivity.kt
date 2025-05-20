package com.example.motionalert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Yêu cầu quyền thông báo nếu cần (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }

        // Tạo notification channel
        NotificationUtils.createNotificationChannel(this)

        // Kết nối MQTT và xử lý sự kiện
        MqttManager.connect(this) { message ->
            Log.i("MQTT", "Nhận cảnh báo: $message")
        }

        // Hiển thị Fragment danh sách xâm nhập
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, IntrusionHistoryFragment())
            .addToBackStack(null)
            .commit()
    }
}
