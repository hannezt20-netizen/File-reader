package com.yohanes.filereader.data

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object TextFilterStore {
    private const val PREFS_NAME = "text_filter"
    private var prefs: android.content.SharedPreferences? = null
    private val _filters = MutableStateFlow<Map<String, Set<String>>>(emptyMap())
    val filters: StateFlow<Map<String, Set<String>>> = _filters

    fun init(context: Context) {
        if (prefs == null) {
            val p = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs = p
            val map = mutableMapOf<String, Set<String>>()
            for (key in p.all.keys) {
                val set = p.getStringSet(key, null)
                if (set != null) map[key] = set
            }
            _filters.value = map
        }
    }

    fun getFilters(docKey: String): Set<String> = _filters.value[docKey] ?: emptySet()

    fun addFilter(docKey: String, phrase: String) {
        val trimmed = phrase.trim()
        if (trimmed.isEmpty()) return
        val current = _filters.value.toMutableMap()
        val set = (current[docKey] ?: emptySet()).toMutableSet()
        set.add(trimmed)
        current[docKey] = set
        _filters.value = current
        prefs?.edit()?.putStringSet(docKey, set)?.apply()
    }

    fun removeFilter(docKey: String, phrase: String) {
        val current = _filters.value.toMutableMap()
        val set = (current[docKey] ?: emptySet()).toMutableSet()
        set.remove(phrase)
        current[docKey] = set
        _filters.value = current
        prefs?.edit()?.putStringSet(docKey, set)?.apply()
    }

    fun applyFilter(docKey: String, text: String): String {
        val phrases = getFilters(docKey)
        if (phrases.isEmpty() || text.isBlank()) return text
        var result = text
        for (phrase in phrases) {
            if (phrase.isNotBlank()) {
                result = result.replace(phrase, " ")
            }
        }
        return result
    }
}
