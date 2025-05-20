package com.example.motionalert

//package com.example.intrusionapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StorageHelper {
    private const val PREF_NAME = "intrusion_history"
    private const val KEY_HISTORY = "events"

    fun saveEvent(context: Context, event: IntrusionEvent) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val events = getEvents(context).toMutableList()
        events.add(event)

        val json = Gson().toJson(events)
        prefs.edit().putString(KEY_HISTORY, json).apply()
    }

    fun getEvents(context: Context): List<IntrusionEvent> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_HISTORY, null) ?: return emptyList()

        val type = object : TypeToken<List<IntrusionEvent>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun clearEvents(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_HISTORY).apply()
    }
}

