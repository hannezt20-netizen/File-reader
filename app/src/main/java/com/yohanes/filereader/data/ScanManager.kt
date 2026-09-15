package com.yohanes.filereader.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * T1 fondasi multi-tab (14 Sept 2026, LAPORAN-yhs13.md bagian B): trigger-scan & fileOpsTick
 * dipindah keluar dari HomeViewModel jadi objek global di sini - supaya kalau nanti 4 tab
 * dibuka bareng (tiap tab HomeViewModel sendiri via viewModel(key=tabId)), scan tidak
 * dobel-jalan dan notifikasi "file berubah" tetap 1 sumber kebenaran bersama semua tab.
 */
object ScanManager {
    private var dao: FileDao? = null
    private var scanPrefs: android.content.SharedPreferences? = null
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning

    private val _fileOpsTick = MutableStateFlow(0)
    val fileOpsTick: StateFlow<Int> = _fileOpsTick

    fun init(context: Context) {
        if (dao != null) return
        dao = AppDatabase.getInstance(context.applicationContext).fileDao()
        val prefs = context.applicationContext.getSharedPreferences("home_scan_prefs", Context.MODE_PRIVATE)
        scanPrefs = prefs
        val lastScan = prefs.getLong(KEY_LAST_SCAN, 0L)
        val elapsed = System.currentTimeMillis() - lastScan
        if (elapsed > SCAN_INTERVAL_MS) {
            refreshScan()
        }
    }

    fun notifyFileOpsChanged() { _fileOpsTick.value++ }

    fun refreshScan() {
        val currentDao = dao ?: return
        scope.launch {
            _isScanning.value = true
            val results = withContext(Dispatchers.IO) { FileScanner.scanAll() }
            withContext(Dispatchers.IO) { currentDao.syncAll(results) }
            scanPrefs?.edit()?.putLong(KEY_LAST_SCAN, System.currentTimeMillis())?.apply()
            _isScanning.value = false
        }
    }

    private const val KEY_LAST_SCAN = "last_scan_timestamp"
    private const val SCAN_INTERVAL_MS = 10 * 60 * 1000L
}
