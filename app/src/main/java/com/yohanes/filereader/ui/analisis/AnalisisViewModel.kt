package com.yohanes.filereader.ui.analisis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yohanes.filereader.data.AppDatabase
import com.yohanes.filereader.data.FileEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * CATATAN: ViewModel mandiri utk halaman Analisis (submodul "File Besar" duluan,
 * sesuai urutan LAPORAN-yhs13.md bagian E). Sengaja TIDAK bergantung ke HomeViewModel.kt
 * supaya aman dikerjakan sementara hz25 pegang T1 fondasi multi-tab.
 */
class AnalisisViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val dao = db.fileDao()

    val fileTerbesar: StateFlow<List<FileEntity>> = dao.getLargestFiles(100)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
