# STATUS.md
> Update hz11, 13 Sept 2026

## Build Status
Branch: main (branch-per-akun sudah dihapus semua). Build terkonfirmasi hijau terbaru: Accordion Gambar (11 Sept), FileActionSheet penuh (11 Sept). Beberapa patch multi-select & Audio masih menunggu verifikasi build+tes user.

## Fitur Terkonfirmasi Jalan
- Viewer Gambar (zoom/pan) + galeri accordion Tahun-Bulan-Tanggal
- Toggle Terbaru/Folder di semua kategori
- FileActionSheet (copy/paste/delete) + long-press semua kategori
- Audio Player: playlist custom + navigasi 3 halaman swipe
- PDF: fix mode Scroll, TTS + kontrol notifikasi/lock screen

## Bug Aktif
- SettingsPanel PDF: status visual final belum diverifikasi build+tes
- Hamburger Beranda belum sejajar 1 baris dgn search bar - menunggu keputusan user

## Bug Sudah Tidak Relevan (dihapus dari daftar)
- Kartu Gambar 0 file (sekarang normal, 22935 file)
- Build error clickable AudioPlayerScreen (basi, 5 build terakhir main sukses)

## Perlu Verifikasi User (build+tes HP)
- Checkpoint 5 multi-select Gambar (hz25) - satu-satunya sisa
- Bug baru: tombol X notif TTS tak muncul di Media Controls (ydiv2, perlu addCustomAction)

## Sudah Confirmed (audit ydiv2, 15 Sept)
- Multi-select checkpoint 1,2,3,4,6 + T1 Fondasi multi-tab (hz25)
- Notifikasi Audio, Audio Focus Player&TTS, Checkpoint B UI Playlist (ydiv2)

## Update 13 Sept 2026 - Cek GitHub Actions
Semua build terbaru di tab Actions HIJAU (checkpoint 5 multi-select Gambar, rombak notifikasi TTS PDF, fix SelectionActionBar tumpang-tindih). Catatan: build hijau = kompilasi sukses saja, BUKAN otomatis berarti sudah dites di HP - status [PROSES] di TODO.md tetap dipakai sampai user konfirmasi tes fungsional.

## Update 14 Sept 2026 - Keputusan Desain Final (sesi yhs13)
- Gaya visual: RS Manager + iPhone Files dicustom (BUKAN Material You murni - ditunda, tunggu referensi baru)
- Mode Pilih & Clipboard: 1 clipboard GLOBAL (bukan per-tab), diakses via ikon di bar atas
- Arsitektur multi-tab: state per-tab (kategori/search/sort/Direktori/mode pilih dll) vs state global (data Room, trigger scan, fileOpsTick, Favorit, Clipboard) sudah dipetakan - WAJIB baca LAPORAN-yhs13.md sebelum sentuh MainActivity.kt/HomeViewModel.kt lagi

## Bug Aktif (tambahan) - RESOLVED 14 Sept
- ~~Android/data/<paket> tampil "Folder kosong"~~ - root cause TERNYATA pembatasan sistem Android 11+ (FUSE), BUKAN soal DB-vs-live (koreksi diagnosis awal). Tidak ada cara legal tanpa root/Shizuku. Solusi final: pesan jujur "Dibatasi sistem Android" utk folder app lain, folder app sendiri tetap normal
