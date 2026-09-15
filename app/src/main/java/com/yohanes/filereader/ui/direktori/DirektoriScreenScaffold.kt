package com.yohanes.filereader.ui.direktori

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap

/**
 * CATATAN UNTUK EKSEKUTOR:
 * Scaffold Compose untuk Direktori sesuai LAPORAN-yhs13.md bagian D.
 * - TopAppBar SENGAJA tidak ada - back pakai gesture sistem.
 * - DirEntry.badge diisi berdasar nama folder yang dikenali (Download/DCIM/WhatsApp) -
 *   logika pengenalannya ditaruh di ViewModel/util, bukan di composable ini.
 * - DirEntry.appPackageName diisi kalau folder ini Android/data/<paket> DAN app-nya
 *   masih terpasang (dicek pakai PackageManager di ViewModel).
 * - subjudul sudah harus berupa teks jadi (hasil query), jangan dihitung di composable ini.
 */

enum class BadgeFolder(val icon: ImageVector, val warna: Color) {
    DOWNLOAD(Icons.Filled.ArrowDownward, Color(0xFF5F5E5A)),
    DCIM(Icons.Filled.CameraAlt, Color(0xFF5F5E5A)),
    WHATSAPP(Icons.Filled.ChatBubble, Color(0xFF1D9E75)),
}

data class DirEntry(
    val nama: String,
    val subjudul: String,
    val isFolder: Boolean,
    val isHidden: Boolean = false,
    val badge: BadgeFolder? = null,
    val appPackageName: String? = null,
)

@Composable
fun DirektoriTopRow(breadcrumb: String, onSearch: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            breadcrumb,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
        )
        IconButton(onClick = onSearch) {
            Icon(Icons.Filled.Search, contentDescription = "Cari di folder ini")
        }
    }
}

@Composable
fun FolderIcon(entry: DirEntry) {
    val ctx = LocalContext.current
    Box(modifier = Modifier.size(44.dp)) {
        if (entry.appPackageName != null) {
            val drawable = runCatching {
                ctx.packageManager.getApplicationIcon(entry.appPackageName)
            }.getOrNull()
            if (drawable != null) {
                Image(
                    bitmap = drawable.toBitmap().asImageBitmap(),
                    contentDescription = entry.nama,
                    modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)),
                )
            } else {
                GenericFolderIcon()
            }
        } else {
            GenericFolderIcon()
            if (entry.badge != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(entry.badge.warna),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        entry.badge.icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(10.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun GenericFolderIcon() {
    Box(
        modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFF378ADD)),
        contentAlignment = Alignment.Center,
    ) {
        Icon(Icons.Filled.Folder, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun DirEntryRow(entry: DirEntry, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .alpha(if (entry.isHidden) 0.45f else 1f)
            .padding(vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FolderIcon(entry)
        Spacer(Modifier.width(12.dp))
        Column {
            Text(entry.nama, fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
            Text(entry.subjudul, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun DirektoriScreenScaffold(
    breadcrumb: String,
    daftarEntry: List<DirEntry>,
    onSearch: () -> Unit,
    onEntryClick: (DirEntry) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DirektoriTopRow(breadcrumb, onSearch)
        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
            items(daftarEntry) { entry ->
                DirEntryRow(entry) { onEntryClick(entry) }
                HorizontalDivider()
            }
        }
    }
}
