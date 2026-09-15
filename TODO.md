# TODO.md — Pembagian Kerja Aktif per Akun
> Diupdate hz11, 13 Sept 2026. [SELESAI]/[PROSES]/[BELUM]

## PRIORITAS UTAMA (revisi 15 Sept)
1. [SELESAI-hz25] Android/data: pembatasan sistem Android 11+ (FUSE), tak bisa diperbaiki - solusi: pesan "Dibatasi sistem Android"
2. [SELESAI-hz25] T1 Fondasi multi-tab: ScanManager.kt global + currentDir StateFlow - dikonfirmasi build+tes
3. [PROSES-hz25] Checkpoint 5 multi-select Gambar - masih menunggu tes HP
4. [PROSES-ydiv2] Bug baru: tombol X notif TTS tak muncul di Media Controls - perlu addCustomAction
5. [BELUM] Dialog Properti + zip Kompres/Ekstrak - mandiri, belum ditugaskan

> Item ydiv2 lain sudah CONFIRMED. Detail: LAPORAN-hz25.md, LAPORAN-ydiv2.md.

## hz19 - Gambar
- [SELESAI] Viewer full-screen + semua bug terkait
- [SELESAI] Galeri grid Coil+cache, Paging3 ~23rb foto
- [SELESAI] Accordion final (Tahun-Bulan-Tanggal, inline-expand+preview 3 foto), dikonfirmasi 11 Sept. Bug header bulan hilang ikut terselesaikan
- [SELESAI] Tes data asli ~23rb foto - OK (13 Sept)
- [BELUM] Sticky header & Fast Scroller - dilewati dulu
- [MENUNGGU KOORDINASI] Fase D - deteksi duplikat (semua tipe file, custom pilihan kategori scan), dipetakan ke menu Analisis>File Duplikat
- [BLOCKED] Multi-select foto - checkpoint 5 hz25 (Gambar) sudah dikerjakan & build hijau (13 Sept), menunggu konfirmasi tes HP sebelum hz19 lanjut
- [BELUM] Desain ulang Beranda/kategori (digabung dgn desain yhs13, bukan 2 rencana beda - keduanya multi-tab maks 4):
  1. Toggle Grid/List + Sort/Urutkan
  2. Thumbnail nyata PDF (render halaman 1), xlsx cukup ikon
  3. List view: thumbnail+nama+ukuran+tanggal
  4. Breadcrumb path + badge persentase penyimpanan
  5. Multi-tab (=T2-T4 yhs13) - paling akhir
- [SELESAI] Kartu Analisis dipindah ke Beranda - sudah masuk desain final yhs13
- [PROSES] Analisis submodul File Besar: AnalisisViewModel.kt+FileBesarScreen.kt (mandiri) - menunggu build. Wiring navigasi bisa lanjut (T1 sudah selesai)

## hz21 - PDF SettingsPanel / Mode Baca
- [SELESAI] Fix bug PDF mode Scroll (halaman bertumpuk saat zoom) + revisi lanjutan
- [SELESAI] Padding Beranda/kategori & toggle Video (dikonfirmasi screenshot user)
- [BELUM] SettingsPanel PDF: status visual FINAL belum diverifikasi build+tes di HP
- [BELUM] Hamburger belum sejajar 1 baris dgn search bar - menunggu keputusan user
- Bug kartu Gambar 0 file SUDAH TIDAK RELEVAN (terdeteksi normal 22935 file) - dihapus dari daftar bug

## hz25 - Video / Toggle / FileActionSheet / Multi-select
- [SELESAI] Toggle Terbaru/Folder: PDF/Excel/Teks/Favorit/Gambar
- [SELESAI] Hapus kartu Favorit dari grid Beranda
- [SELESAI] FileActionSheet penuh (Tugas 2) termasuk integrasi ImageThumbnail Gambar, dikonfirmasi 11 Sept
- [SELESAI] Multi-select checkpoint 1,2,3,4,6 (fondasi/aksi Pilih/Direktori/Video/List) - dikonfirmasi user build hijau
- [PROSES] Checkpoint 5 (Gambar) - dikerjakan 13 Sept, build hijau Actions, menunggu konfirmasi tes HP. Jadi pembuka blocker multi-select foto hz19
- [PROSES] Fix bug SelectionActionBar tumpang-tindih pill toggle (Image/Video/List) - build hijau, menunggu tes HP
- [SELESAI] Bug Android/data folder kosong - ternyata pembatasan sistem Android 11+ (FUSE), bukan soal DB-vs-live. Solusi: pesan "Dibatasi sistem Android" utk folder app lain
- [SELESAI] T1 Fondasi multi-tab - ScanManager.kt (global) + currentDir jadi StateFlow HomeViewModel (4 titik disesuaikan)
- [BELUM] Long-press folder kosong di Direktori utk dihapus

## ydiv2 (dibantu yhs13 di Audio) - PDF TTS / Audio Player
- [SELESAI] TTS: tombol close bar
- [SELESAI] TTS: kontrol notifikasi/lock screen dasar (Prev/Play/Next+tombol X) - dikonfirmasi
- [PROSES] Bug lanjutan: tombol X notif TTS blm muncul di Media Controls modern - perlu addCustomAction
- [SELESAI] Audio Player dasar - Tahap A (redesain UI) - Tahap B (playlist Room) - Tahap C1 (3 halaman swipe)
- [SELESAI] Fix bug judul&lagu tidak sinkron di tab Playlist
- [SELESAI] Tahap C2 Lirik DIROMBAK: sumber tag USLT tertanam, Checkpoint A+B (reader/judul+UI Playlist) - dikonfirmasi
- [SELESAI] Rombak notifikasi Audio (non-dismissable, tombol X stop) - dikonfirmasi
- [SELESAI] Audio Focus request AudioPlayerService & TTS PDF - dikonfirmasi
- ~~Hapus tombol hamburger PlayerControlBar~~ - sudah selesai dari dulu
- ~~Tahap C3-C5 Lirik~~ - BASI: C3 sudah lama jadi, C4/C5 dibatalkan final (pivot gaya Musicolet)
- Bug build clickable URGENT (8 Sept) SUDAH BASI, dikonfirmasi ydiv2 - dihapus dari daftar
