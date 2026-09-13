# TODO.md — Pembagian Kerja Aktif per Akun
> Diupdate hz11, 13 Sept 2026. [SELESAI]/[PROSES]/[BELUM]

## hz19 - Gambar
- [SELESAI] Viewer full-screen + semua bug terkait
- [SELESAI] Galeri grid Coil+cache, Paging3 ~23rb foto
- [SELESAI] Accordion final (Tahun-Bulan-Tanggal, inline-expand+preview 3 foto), dikonfirmasi 11 Sept. Bug header bulan hilang ikut terselesaikan
- [SELESAI] Tes data asli ~23rb foto - OK (13 Sept)
- [BELUM] Sticky header & Fast Scroller - dilewati dulu
- [MENUNGGU KOORDINASI] Fase D - deteksi foto duplikat (pHash), dipetakan ke menu Analisis>File Duplikat. Perlu sepakati scope dulu
- [BLOCKED] Multi-select foto - tunggu checkpoint 5 hz25 selesai

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
- [PROSES] Multi-select: checkpoint 1 fondasi build hijau; checkpoint 2 aksi Pilih menunggu build; checkpoint 3 SelectionBar+Direktori menunggu build+tes; checkpoint 4 Video menunggu build+tes; checkpoint 6 List PDF/Excel/Teks/Favorit menunggu build+tes
- [BISA DILANJUT] Checkpoint 5 (Gambar) - sebelumnya ditunda krn hz19 proses accordion di file sama, accordion hz19 SUDAH SELESAI, perlu konfirmasi ke hz25 utk lanjut
- [BELUM] Long-press folder kosong di Direktori utk dihapus

## ydiv2 (dibantu yhs13 di Audio) - PDF TTS / Audio Player
- [SELESAI] TTS: tombol close bar, kontrol notifikasi/lock screen
- [SELESAI] Audio Player dasar - Tahap A (redesain UI) - Tahap B (playlist Room) - Tahap C1 (3 halaman swipe)
- [SELESAI] Fix bug judul&lagu tidak sinkron di tab Playlist
- [PROSES] Tahap C2 Lirik DIROMBAK: sumber dari tag USLT tertanam (bukan .lrc privat lagi) + deteksi otomatis pola waktu. Checkpoint A (Id3UsltReader+readTitle) proses; Checkpoint B (UI Playlist thumbnail+judul+menu) proses, belum tes manual
- [PROSES] Rombak notifikasi Audio (non-dismissable, tombol X stop) - 2x iterasi fix, menunggu build hijau final
- [PROSES] Audio Focus request di AudioPlayerService & TTS PDF - menunggu build+tes
- [BELUM] Hapus tombol hamburger dari PlayerControlBar (redundant sejak swipe)
- [BELUM] Tahap C3-C5 Lirik (highlight sinkron, editor Sederhana, editor Disinkronkan)
- Bug build clickable URGENT (8 Sept) SUDAH BASI, dikonfirmasi ydiv2 - dihapus dari daftar
