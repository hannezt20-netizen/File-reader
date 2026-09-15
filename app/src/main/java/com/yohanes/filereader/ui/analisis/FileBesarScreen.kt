package com.yohanes.filereader.ui.analisis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yohanes.filereader.data.FileEntity

/**
 * CATATAN UNTUK EKSEKUTOR:
 * Layar detail submodul "File Besar". onFileClick diteruskan dari HomeScreen supaya
 * tap file di daftar ini membuka file yang sama seperti di kategori lain.
 */
@Composable
fun FileBesarScreen(
    viewModel: AnalisisViewModel = viewModel(),
    onFileClick: (FileEntity) -> Unit,
) {
    val daftar by viewModel.fileTerbesar.collectAsState()
    val totalUkuran = daftar.sumOf { it.sizeBytes }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
        item {
            Spacer(Modifier.height(16.dp))
            Text(
                "Total: " + formatUkuranFile(totalUkuran) + " dari " + daftar.size + " file terbesar",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(Modifier.height(8.dp))
        }
        items(daftar) { file ->
            FileBesarRow(file, onClick = { onFileClick(file) })
            HorizontalDivider()
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
private fun FileBesarRow(file: FileEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 10.dp),
    ) {
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
