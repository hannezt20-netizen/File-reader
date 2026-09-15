package com.yohanes.filereader.ui.beranda

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * CATATAN UNTUK EKSEKUTOR:
 * Scaffold Compose sesuai hasil desain sesi 14 Sept (lihat LAPORAN-yhs13.md).
 * Belum disambungkan ke HomeViewModel asli - parameter di bawah perlu diganti
 * dengan state/callback dari HomeViewModel yang sebenarnya.
 */

enum class Kategori(val label: String, val icon: ImageVector, val bg: Color, val fg: Color) {
    PDF("PDF", Icons.Filled.Description, Color(0xFFF5C4B3), Color(0xFF712B13)),
    GAMBAR("Gambar", Icons.Filled.Image, Color(0xFFFAC775), Color(0xFF633806)),
    EXCEL("Excel", Icons.Filled.TableChart, Color(0xFFC0DD97), Color(0xFF27500A)),
    VIDEO("Video", Icons.Filled.Movie, Color(0xFFF4C0D1), Color(0xFF72243E)),
    AUDIO("Audio", Icons.Filled.MusicNote, Color(0xFFCECBF6), Color(0xFF3C3489)),
    TEKS_KODE("Teks/Kode", Icons.Filled.Code, Color(0xFFB5D4F4), Color(0xFF0C447C)),
}

@Composable
fun BerandaTopBar(
    tabName: String,
    onOpenDrawer: () -> Unit,
    onNewTab: () -> Unit,
    onSearch: () -> Unit,
    onOpenClipboard: () -> Unit,
    onMoreMenu: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onOpenDrawer) {
            Icon(Icons.Filled.Menu, contentDescription = "Buka drawer", tint = Color.White)
        }
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.22f))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Folder, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(tabName, color = Color.White, fontSize = 14.sp)
            Spacer(Modifier.width(6.dp))
            Icon(
                Icons.Filled.Add,
                contentDescription = "Tab baru",
                tint = Color.White,
                modifier = Modifier.size(16.dp).clickable(onClick = onNewTab),
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onSearch) {
            Icon(Icons.Filled.Search, contentDescription = "Cari", tint = Color.White)
        }
        IconButton(onClick = onOpenClipboard) {
            Icon(Icons.Filled.ContentPaste, contentDescription = "Clipboard", tint = Color.White)
        }
        IconButton(onClick = onMoreMenu) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Menu lain", tint = Color.White)
        }
    }
}

@Composable
fun StorageAnalisisRow(
    persenTerpakai: Int,
    labelStorage: String,
    onStorageClick: () -> Unit,
    onAnalisisClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Card(
            modifier = Modifier.weight(1f).clickable(onClick = onStorageClick),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(
                    progress = { persenTerpakai / 100f },
                    modifier = Modifier.size(40.dp),
                    strokeWidth = 4.dp,
                )
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Storage", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text(labelStorage, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        Card(
            modifier = Modifier.weight(1f).clickable(onClick = onAnalisisClick),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFCECBF6)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.PieChart, contentDescription = null, tint = Color(0xFF3C3489))
                }
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Analisis", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text("Lihat ringkasan", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun KategoriGrid(onKategoriClick: (Kategori) -> Unit) {
    val kategoris = Kategori.entries
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        for (baris in kategoris.chunked(3)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                for (kat in baris) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { onKategoriClick(kat) },
                    ) {
                        Box(
                            modifier = Modifier.size(52.dp).clip(CircleShape).background(kat.bg),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(kat.icon, contentDescription = kat.label, tint = kat.fg)
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(kat.label, fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BerandaScreenScaffold(
    tabName: String,
    persenStorageTerpakai: Int,
    labelStorage: String,
    onOpenDrawer: () -> Unit,
    onNewTab: () -> Unit,
    onSearch: () -> Unit,
    onOpenClipboard: () -> Unit,
    onMoreMenu: () -> Unit,
    onStorageClick: () -> Unit,
    onAnalisisClick: () -> Unit,
    onKategoriClick: (Kategori) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BerandaTopBar(tabName, onOpenDrawer, onNewTab, onSearch, onOpenClipboard, onMoreMenu)
        StorageAnalisisRow(persenStorageTerpakai, labelStorage, onStorageClick, onAnalisisClick)
        KategoriGrid(onKategoriClick)
        // Baris ke-4 sengaja dikosongkan dulu - lihat LAPORAN-yhs13.md bagian C
    }
}
