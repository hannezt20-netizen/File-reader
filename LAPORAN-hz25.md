# LAPORAN - hz25 (Video / Toggle Terbaru-Folder / FileActionSheet)
> File ini di-update terus tiap ada pencapaian terkonfirmasi user (bukan file baru per tanggal).

## Fase Kerja
- Toggle Terbaru/Folder untuk PDF/Excel/Teks-Kode/Favorit + perluasan ke Gambar - [SELESAI]
- Hapus kartu "Favorit" dari grid Beranda - [SELESAI]
- Tugas 2: sistem FileActionSheet (clipboard/file-ops) - [SELESAI] (langkah 5/5: tombol Tempel di DirektoriScreen + fileOpsTick untuk auto-refresh listing folder. ImageThumbnail Gambar masih tertunda re-koordinasi hz19)

## Kesepakatan Baru dengan User
- (kosong saat ini, semua sudah diserap hz11 ke TODO.md)

## Rencana Kerja & File Terkait
- Hapus kartu Favorit dari Beranda: `HomeScreen.kt` (`CategoryHomeScreen`/`CATEGORY_LIST`) - drawer sudah punya menu Favorit terpisah, jadi kartu di grid Beranda dobel & dihapus.
- Tugas 2 FileActionSheet, urutan kerja: `FileDao.kt` (tambah `deleteByPath`/`renamePath`) -> `FileClipboard.kt` (baru) -> `FileActionSheet.kt` (baru) -> sambung ke `FileRow` (`HomeScreen.kt`/`FileListWithModeToggle.kt`) & `VideoThumbnail` (`VideoGalleryScreen.kt`) -> tombol Tempel di `DirektoriScreen` (`HomeScreen.kt`). `ImageThumbnail` (Gambar) ditunda sampai poin koordinasi di bawah selesai disepakati.

### Balasan koordinasi ke hz19 (soal integrasi ImageThumbnail + FileActionSheet)
Rencana teknis dari sisi hz25 (belum dieksekusi, menunggu konfirmasi hz19 dulu sebelum jalan):
- `FileActionSheet` akan dipicu lewat **long-press** pada thumbnail/row file (bukan tap biasa, supaya tidak bentrok dengan tap yang sudah dipakai buka viewer).
- Yang dibutuhkan dari `ImageThumbnail` di `ImageGalleryScreen.kt`: tambah 1 parameter baru `onLongClick: (FileEntity) -> Unit`, lalu ganti `Modifier.clickable(onClick = onClick)` jadi `Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick)` (dari `androidx.compose.foundation.combinedClickable`).
- Parameter ini perlu diteruskan (thread) ke fungsi publik `ImageGalleryScreen(...)` sebagai `onFileLongClick: (FileEntity) -> Unit`, dan disambungkan ke KEDUA pemanggilan `ImageThumbnail` (grid mode Terbaru & grid dalam folder mode Folder) - sesuai catatan hz19 kalau komponen ini dipakai di 2 tempat.
- State "sheet lagi kebuka untuk file mana" dan isi sheet-nya sendiri akan saya (hz25) yang tangani di level `HomeViewModel`/`HomeScreen.kt` - jadi hz19 cukup tambah 1 parameter callback itu saja, tidak perlu tahu isi sheet-nya seperti apa.
- Pola yang sama akan saya terapkan juga ke `FileRow` & `VideoThumbnail` biar konsisten di semua kategori.
- Mohon konfirmasi hz19: apakah pola ini oke, atau ada preferensi lain? Kalau oke, saya siap kasih patch persis untuk `ImageGalleryScreen.kt` begitu Tugas 2 dimulai (supaya hz19 tinggal jalankan, tidak perlu susun sendiri).

## Bug
- (kosong - bug label-bulan mode Terbaru Gambar sudah dialihkan jadi tanggung jawab hz19 per keputusan hz11)

## Log Pencapaian
- 8 Sept: hapus "Favorit" dari CATEGORY_LIST (kartu Beranda) - drawer sudah punya menu sama, tidak dobel lagi
- 8 Sept: fix pill toggle Video ketutupan navbar sistem
- 8 Sept: toggle Terbaru/Folder untuk PDF/Excel/Teks-Kode/Favorit (`FileListWithModeToggle.kt` baru)
- 8 Sept: fix import `height` yang kelewat di `FileListWithModeToggle.kt`
- 8 Sept: perluasan toggle Terbaru/Folder ke kategori Gambar (folder mode drill-down ala Video, mode Terbaru tidak diubah) - koordinasi dengan hz19 dikonfirmasi aman sebelum eksekusi
- 8 Sept: fix posisi pill toggle Gambar (sempat di atas, seharusnya di bawah)

## Update 11 Sept 2026 - Integrasi FileActionSheet ke ImageThumbnail (Gambar)
- [SELESAI] Tambah onLongClick di ImageThumbnail (Gambar), pakai combinedClickable, wiring onFileLongClick dari HomeScreen ke ImageGalleryScreen di KEDUA mode (Terbaru & Folder). Pola persis meniru VideoThumbnail. DIKONFIRMASI user: build hijau, tes di HP oke, long-press berfungsi di kedua mode.

## Update 11 Sept 2026 - Tahap 2: Multi-select (checkpoint 2)
- [PROSES] Checkpoint 1 (fondasi): FileClipboard diperluas jadi List<FileEntity>, state selectedPaths/isSelectionMode/toggleSelect/startSelection/clearSelection/deleteFiles di HomeViewModel. Build hijau, sudah push.
- [PROSES] Checkpoint 2: aksi baru "Pilih" di FileActionSheet - masuk mode-pilih dari long-press biasa (tidak mengganti perilaku FileActionSheet yang sudah ada). Menunggu build.

## Update 11 Sept 2026 - Tahap 2: Multi-select (checkpoint 3)
- [PROSES] Checkpoint 3: file baru SelectionBar.kt (SelectionTopBar + SelectionActionBar, reusable). Wiring penuh ke DirektoriScreen: tap toggle pilih, highlight+centang di FileRow, Tempel loop banyak file, BackHandler keluar mode-pilih dulu. Menunggu build+tes di HP.
- Rencana lanjut: wiring sama ke VideoGalleryScreen, ImageGalleryScreen, FileListWithModeToggle (PDF/Excel/Teks/Favorit) di checkpoint berikutnya.

## Update 11 Sept 2026 - Tahap 2: Multi-select (checkpoint 4 - Video)
- [PROSES] VideoGalleryScreen.kt: VideoThumbnail +isSelected (highlight+centang), tap toggle pilih saat mode aktif, SelectionTopBar/SelectionActionBar disisipkan di kedua Box (dalam folder & grid utama), BackHandler gabungan (keluar mode pilih dulu sebelum navigasi folder). Wiring penuh di HomeScreen.kt (CategoryDetailScreen -> selectedPaths/isSelectionMode dari viewModel, selectedFileEntities dihitung dari videos+selectedPaths). Menunggu build+tes di HP.
- Checkpoint selanjutnya: Gambar (ImageGalleryScreen.kt, 2 mode), lalu FileListWithModeToggle.kt (PDF/Excel/Teks/Favorit). Ada juga permintaan tertunda: long-press folder di Direktori supaya folder kosong bisa dihapus (dikerjakan pas nyentuh HomeScreen.kt lagi).

## Update 11 Sept 2026 - Tahap 2: Multi-select (checkpoint 6 - List PDF/Excel/Teks/Favorit)
- [PROSES] FileListWithModeToggle.kt: FileRowPublic +isSelected (highlight+centang), tap toggle pilih saat mode aktif, SelectionTopBar/SelectionActionBar disisipkan. Wiring penuh di HomeScreen.kt (CategoryDetailScreen). Checkpoint 5 (Gambar) SENGAJA DITUNDA - hz19 masih proses accordion galeri Gambar di file yang sama, dihindari dulu supaya tidak bentrok. Menunggu build+tes di HP.

## Update 13 Sept 2026 - Tahap 2: Multi-select (checkpoint 5 - Gambar)
- [PROSES] ImageGalleryScreen.kt: ImageThumbnail +isSelected (highlight+centang), tap toggle pilih saat mode aktif di 3 titik (folder terpilih, mode Terbaru paging, AccordionGridItem.FullPhoto), SelectionTopBar/SelectionActionBar disisipkan di folder terpilih & Box utama, BackHandler gabungan. Wiring penuh di HomeScreen.kt. Menunggu build+tes di HP.
- Konfirmasi ke hz11: checkpoint 3 (Direktori), checkpoint 4 (Video), checkpoint 6 (List PDF/Excel/Teks/Favorit) SUDAH dikonfirmasi user build hijau - mohon update status di TODO.md/STATUS.md dari [PROSES]/menunggu verifikasi jadi [SELESAI].
- Checkpoint 5 ini SEKALIGUS jadi pembuka blocker untuk rencana multi-select foto hz19 (checkbox+select-all accordion untuk Fase D deteksi duplikat) - begitu ini SELESAI, silakan hz19 lanjut.

## Update 13 Sept 2026 - Fix bug: bar aksi mode-pilih tumpang tindih dengan pill Terbaru/Folder
- [PROSES] Ditemukan dari screenshot user (Gambar): SelectionActionBar dan pill toggle Terbaru/Folder sama-sama Alignment.BottomCenter, jadi menumpuk. Fix: sembunyikan pill toggle Terbaru/Folder (if !isSelectionMode) di 3 file sekaligus - ImageGalleryScreen.kt, VideoGalleryScreen.kt, FileListWithModeToggle.kt (pola sama, jadi bug yang sama diperbaiki sekaligus preventif). Menunggu build+tes di HP.

## Update 13 Sept 2026 - Fix lanjutan: bar Gambar mode Terbaru masih menumpuk
- [PROSES] Fix sebelumnya cuma kena ModeToggleRow di mode Folder (baris 283), terlewat 1 lagi di mode Terbaru/accordion (baris 477) - sekarang sudah dibungkus if(!isSelectionMode) juga. Menunggu konfirmasi tes di HP.

## Update 14 Sept 2026 - Fix bug Android/data folder kosong: TEMUAN PENTING untuk hz11/yhs13
- [SELESAI] Setelah investigasi (dikonfirmasi lewat riset + tes langsung `ls` folder tsb dari Termux sendiri kena "Permission denied"), root cause di LAPORAN-yhs13.md TERNYATA KELIRU. Ini BUKAN soal "baca dari DB scan vs baca live folder" - ini PEMBATASAN LEVEL SISTEM ANDROID (FUSE) sejak Android 11, berlaku untuk SEMUA aplikasi pihak ketiga termasuk file manager dengan izin MANAGE_EXTERNAL_STORAGE aktif. Sejak Android 13, celah lama lewat SAF juga sudah ditutup Google. Tidak ada cara legal tanpa root/Shizuku untuk baca folder Android/data milik app LAIN - baik lewat DB maupun live read, keduanya kena batasan sama persis.
- [SELESAI] Solusi realistis yang dikerjakan (disetujui user): folder Android/data & Android/obb milik app LAIN sekarang tampilkan pesan jujur "Dibatasi sistem Android" (bukan "Folder kosong" yang menyesatkan). Folder Android/data/com.yohanes.filereader (milik app sendiri) tetap bisa diakses normal karena app boleh baca folder data sendiri.
- REKOMENDASI ke hz11: mohon update STATUS.md/TODO.md - item "Fix bug Android/data folder kosong" statusnya diubah dari upaya "membuat bisa diakses" jadi "pesan error yang jujur" (sudah SELESAI dengan pendekatan ini), karena akses penuh secara teknis tidak memungkinkan tanpa root atau Shizuku (dipertimbangkan tapi TIDAK direkomendasikan untuk device RAM kecil target app ini).

## Update 14 Sept 2026 - T1: Fondasi Multi-Tab (dari LAPORAN-yhs13.md bagian B)
- [PROSES] File baru ScanManager.kt (objek global): isScanning, refreshScan(), fileOpsTick, notifyFileOpsChanged() dipindah dari HomeViewModel ke sini. HomeViewModel tetap punya API publik sama persis (isScanning/refreshScan/fileOpsTick/notifyFileOpsChanged) tapi sekarang delegasi ke ScanManager - HomeScreen.kt TIDAK perlu diubah untuk bagian ini (minim risiko).
- [PROSES] currentDir Direktori dipindah dari `remember` lokal (bug lama - reset tiap composable dilepas) ke HomeViewModel sebagai StateFlow. DirektoriScreen di HomeScreen.kt disesuaikan (4 titik assignment: BackHandler, tombol kembali, breadcrumb, klik folder).
- Ini fondasi WAJIB sebelum T2 (tab dinamis + viewModel(key=tabId) per tab) - dikerjakan SENDIRIAN sesuai catatan TODO.md, tidak barengan sesi lain sentuh HomeViewModel.kt/MainActivity.kt. Menunggu build+tes di HP.
