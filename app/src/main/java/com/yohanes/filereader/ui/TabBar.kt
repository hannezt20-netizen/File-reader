package com.yohanes.filereader.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

/**
 * T2 fondasi multi-tab (14-15 Sept 2026, LAPORAN-yhs13.md bagian B), REDESAIN 15 Sept:
 * tab non-aktif ditampilkan sebagai titik kecil (penanda ada tab terbuka, tap utk pindah),
 * tab AKTIF ditampilkan sebagai nama+tombol X (nama dikirim dari MainActivity, mengikuti
 * kategori/Direktori yang sedang dibuka tab itu). Maksimal 4 tab, minimal 1 (tak bisa ditutup
 * sampai habis). tabId dipakai sebagai key ke viewModel(key = tabId) di MainActivity.
 */
@Composable
fun TabBar(
    tabIds: List<String>,
    activeTabId: String,
    activeLabel: String,
    onTabSelected: (String) -> Unit,
    onTabClosed: (String) -> Unit,
    onNewTab: () -> Unit
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()).padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Titik kecil utk tab lain yang masih terbuka di belakang
        for (id in tabIds) {
            if (id == activeTabId) continue
            Surface(
                onClick = { onTabSelected(id) },
                shape = CircleShape,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                modifier = Modifier.padding(horizontal = 3.dp).size(7.dp)
            ) {}
        }

        Surface(
            shape = RoundedCornerShape(50),
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 6.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 14.dp, end = 4.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(activeLabel, style = MaterialTheme.typography.labelLarge)
                if (tabIds.size > 1) {
                    IconButton(onClick = { onTabClosed(activeTabId) }, modifier = Modifier.size(20.dp)) {
                        Icon(Icons.Filled.Close, contentDescription = "Tutup tab", modifier = Modifier.size(14.dp))
                    }
                }
            }
        }

        if (tabIds.size < 4) {
            IconButton(onClick = onNewTab) {
                Icon(Icons.Filled.Add, contentDescription = "Tab baru")
            }
        }
    }
}
