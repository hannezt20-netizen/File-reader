package com.yohanes.filereader.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp

/**
 * T2 fondasi multi-tab (14-15 Sept 2026, LAPORAN-yhs13.md bagian B): bar tab dinamis
 * ala Chrome, maksimal 4 tab, minimal 1 tab (tidak bisa ditutup sampai habis).
 * Tiap tabId dipakai sebagai key ke viewModel(key = tabId) di MainActivity, jadi
 * otomatis tiap tab punya HomeViewModel (seluruh state navigasi) sendiri-sendiri.
 */
@Composable
fun TabBar(
    tabIds: List<String>,
    activeTabId: String,
    onTabSelected: (String) -> Unit,
    onTabClosed: (String) -> Unit,
    onNewTab: () -> Unit
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()).padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabIds.forEachIndexed { index, id ->
            val selected = id == activeTabId
            Surface(
                onClick = { onTabSelected(id) },
                shape = RoundedCornerShape(50),
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(start = 14.dp, end = 4.dp, top = 6.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Tab ${index + 1}", style = MaterialTheme.typography.labelLarge)
                    if (tabIds.size > 1) {
                        IconButton(onClick = { onTabClosed(id) }, modifier = Modifier.size(20.dp)) {
                            Icon(Icons.Filled.Close, contentDescription = "Tutup tab", modifier = Modifier.size(14.dp))
                        }
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
