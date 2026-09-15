package com.yohanes.filereader.ui.analisis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yohanes.filereader.data.FileEntity

/**
 * CATATAN UNTUK EKSEKUTOR:
 * Layar detail submodul "File Besar" (LAPORAN-yhs13.md bagian E, urutan pertama).
 * Dibuka lewat AnalisisScreen.onOpenFileBesar - BELUM disambungkan ke navigasi asli
 * (MainActivity.kt) karena hz25 masih pegang T1 fondasi multi-tab. Begitu T1 selesai,
 * tinggal panggil FileBesarScreen() dari titik navigasi yang sesuai.
 */
@Composable
fun FileBesarScreen(viewModel: AnalisisViewModel = viewModel()) {
    val daftar by viewModel.fileTerbesar.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
        item { Spacer(Modifier.height(16.dp)) }
        items(daftar) { file ->
            FileBesarRow(file)
            HorizontalDivider()
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
private fun FileBesarRow(file: FileEntity) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(file.name, fontSize = 13.sp, maxLines = 1)
            Text(file.path, fontSize = 11.sp, maxLines = 1)
        }
        Text(formatUkuranFile(file.sizeBytes), fontSize = 13.sp)
    }
}

private fun formatUkuranFile(bytes: Long): String {
    val kb = 1024.0
    val mb = kb * 1024
    val gb = mb * 1024
    return when {
        bytes >= gb -> String.format("%.2f GB", bytes / gb)
        bytes >= mb -> String.format("%.2f MB", bytes / mb)
        bytes >= kb -> String.format("%.0f KB", bytes / kb)
        else -> "$bytes B"
    }
}
