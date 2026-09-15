# TODO.md — Pembagian Kerja Aktif per Akun
> Diupdate hz11, 13 Sept 2026. [SELESAI]/[PROSES]/[BELUM]

## PRIORITAS UTAMA SEKARANG (revisi 14 Sept, dari sesi desain yhs13)
1. [hz25] Fix bug Android/data folder kosong - baca folder live dari sistem file (bukan DB scan). Root cause dikonfirmasi BUKAN izin
2. [hz25] T1 - Fondasi multi-tab: pindah trigger-scan & fileOpsTick jadi objek global, currentDir masuk ke HomeViewModel. WAJIB sendirian, jangan barengan sesi lain sentuh HomeViewModel.kt/MainActivity.kt
3. [hz25] Verifikasi tes HP: checkpoint 5 multi-select Gambar + fix overlap SelectionActionBar/pill toggle
4. [ydiv2] Verifikasi tes HP: notifikasi TTS PDF (tombol X+callback) + Audio Focus Player&TTS
5. [BELUM ditugaskan] Dialog Properti + deteksi .zip + Kompres/Ekstrak (java.util.zip) - mandiri, aman dikerjakan kapan saja

> Detail rancangan lengkap: LAPORAN-yhs13.md. Breakdown akun di bawah tetap dipakai utk tracking detail teknis.

## hz19 - Gambar
- [SELESAI] Viewer full-screen + semua bug terkait
- [SELESAI] Galeri grid Coil+cache, Paging3 ~23rb foto
- [SELESAI] Accordion final (Tahun-Bulan-Tanggal, inline-expand+preview 3 foto), dikonfirmasi 11 Sept. Bug header bulan hilang ikut terselesaikan
- [SELESAI] Tes data asli ~23rb foto - OK (13 Sept)
- [BELUM] Sticky header & Fast Scroller - dilewati dulu
- [MENUNGGU KOORDINASI] Fase D - deteksi duplikat (semua tipe file, custom pilihan kategori scan), dipetakan ke menu Analisis>File Duplikat
- [BLOCKED] Multi-select foto - checkpoint 5 hz25 (Gambar) sudah dikerjakan & build hijau (13 Sept), menunggu konfirmasi tes HP sebelum hz19 lanjut
- [BELUM] Desain ulang tampilan Beranda/kategori (rencana baru 13 Sept, urutan kerja):
  1. Toggle Grid/List + Sort/Urutkan
  2. Thumbnail nyata PDF (render halaman pertama), xlsx cukup ikon
  3. List view: thumbnail+nama+ukuran+tanggal (tanpa kode izin -rw/drw)
  4. Breadcrumb path + badge persentase penyimpanan
  5. Multi-tab ala browser (tab per folder, maks 4 tab) - paling akhir, paling kompleks
- [BELUM] Kartu Analisis dipindah juga ke Beranda (bukan cuma drawer) - fitur maintenance perlu terlihat/mengundang

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
- [BELUM] Long-press folder kosong di Direktori utk dihapus

## ydiv2 (dibantu yhs13 di Audio) - PDF TTS / Audio Player
- [SELESAI] TTS: tombol close bar
- [PROSES] TTS: kontrol notifikasi/lock screen - ternyata sejak awal service-nya belum pernah didaftarkan/disambungkan (tidak pernah muncul). Diperbaiki 13 Sept: daftar manifest, sambung callback, tombol X ditambah. Build hijau Actions, menunggu tes HP
- [SELESAI] Audio Player dasar - Tahap A (redesain UI) - Tahap B (playlist Room) - Tahap C1 (3 halaman swipe)
- [SELESAI] Fix bug judul&lagu tidak sinkron di tab Playlist
- [PROSES] Tahap C2 Lirik DIROMBAK: sumber dari tag USLT tertanam (bukan .lrc privat lagi) + deteksi otomatis pola waktu. Checkpoint A (Id3UsltReader+readTitle) proses; Checkpoint B (UI Playlist thumbnail+judul+menu) proses, belum tes manual
- [PROSES] Rombak notifikasi Audio (non-dismissable, tombol X stop) - 2x iterasi fix, menunggu build hijau final
- [PROSES] Audio Focus request di AudioPlayerService & TTS PDF - menunggu build+tes
- [BELUM] Hapus tombol hamburger dari PlayerControlBar (redundant sejak swipe)
- [BELUM] Tahap C3-C5 Lirik (highlight sinkron, editor Sederhana, editor Disinkronkan)
- Bug build clickable URGENT (8 Sept) SUDAH BASI, dikonfirmasi ydiv2 - dihapus dari daftar
