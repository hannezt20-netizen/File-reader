package com.yohanes.filereader.ui.analisis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * CATATAN UNTUK EKSEKUTOR:
 * Scaffold Compose untuk Analisis sesuai LAPORAN-yhs13.md bagian E.
 * - TopAppBar SENGAJA tidak ada, TANPA ikon cari (beda dari Direktori).
 * - GarisBesarItem.proporsi dipakai buat lebar segmen bar di atas (total semua item
 *   harus <= 1f). Hitung di ViewModel dari data scan, bukan di composable ini.
 * - Urutan pengerjaan submodul: File Besar dulu -> Garis Besar -> Semua Partisi
 *   File ini baru mockup tampilan Garis Besar + 2 kartu ringkasan; layar detail
 *   Semua Partisi/File Besar/dll dibuat terpisah, dibuka lewat onOpenSemuaPartisi/onOpenFileBesar.
 */

data class GarisBesarItem(
    val label: String,
    val ukuran: String,
    val proporsi: Float,
    val icon: ImageVector,
    val bg: Color,
    val fg: Color,
)

fun garisBesarDefault() = listOf(
    GarisBesarItem("Gambar", "22,96 GB", 0.24f, Icons.Filled.Image, Color(0xFFFAC775), Color(0xFF633806)),
    GarisBesarItem("Video", "23,70 GB", 0.22f, Icons.Filled.Movie, Color(0xFFF4C0D1), Color(0xFF72243E)),
    GarisBesarItem("Audio", "455,97 MB", 0.08f, Icons.Filled.MusicNote, Color(0xFFCECBF6), Color(0xFF3C3489)),
    GarisBesarItem("Dokumen", "3,1 GB", 0.16f, Icons.Filled.Description, Color(0xFFB5D4F4), Color(0xFF0C447C)),
    GarisBesarItem("Apps", "4,8 GB", 0.12f, Icons.Filled.Apps, Color(0xFFF5C4B3), Color(0xFF712B13)),
    GarisBesarItem("Archives", "1,2 GB", 0.06f, Icons.Filled.FolderZip, Color(0xFFC0DD97), Color(0xFF27500A)),
    GarisBesarItem("Lainnya", "63,01 GB", 0.12f, Icons.Filled.MoreHoriz, Color(0xFFD3D1C7), Color(0xFF444441)),
)

@Composable
fun GarisBesarBar(items: List<GarisBesarItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
    ) {
        for (item in items) {
            Box(modifier = Modifier.weight(item.proporsi).fillMaxHeight().background(item.bg))
        }
    }
}

@Composable
fun GarisBesarRow(item: GarisBesarItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(item.bg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(item.icon, contentDescription = null, tint = item.fg, modifier = Modifier.size(17.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(item.label, fontSize = 13.sp, modifier = Modifier.weight(1f))
        Text(item.ukuran, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    HorizontalDivider()
}

@Composable
fun RingkasanCard(judul: String, subjudul: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(judul, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Text(subjudul, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}

@Composable
fun AnalisisScreen(
    ruangBebas: String,
    garisBesar: List<GarisBesarItem>,
    subjudulSemuaPartisi: String,
    subjudulFileBesar: String,
    onOpenSemuaPartisi: () -> Unit,
    onOpenFileBesar: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
        Spacer(Modifier.height(16.dp))
        Text(ruangBebas, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(6.dp))
        GarisBesarBar(garisBesar)
        Spacer(Modifier.height(10.dp))
        for (item in garisBesar) {
            GarisBesarRow(item)
        }
        Spacer(Modifier.height(10.dp))
        RingkasanCard("Semua Partisi", subjudulSemuaPartisi, onOpenSemuaPartisi)
        Spacer(Modifier.height(8.dp))
        RingkasanCard("File Besar", subjudulFileBesar, onOpenFileBesar)
        Spacer(Modifier.height(16.dp))
    }
}
