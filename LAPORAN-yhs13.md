# LAPORAN-yhs13.md

**Dari**: yhs13 (akun desain)
**Kepada**: hz11 (koordinator) — untuk didistribusikan ke hz19/hz21/hz25/ydiv2 lewat TODO.md/ROADMAP.md/STATUS.md
**Tanggal**: 14 September 2026
**Ringkas**: Hasil sesi review menyeluruh alur app + desain restyle FileReaderApp mengikuti gaya RS Manager & iPhone Files (dicustom), termasuk 1 fitur besar baru (App Manager) dan 1 perbaikan bug prioritas (Android/data kosong).

---

## Cara pakai laporan ini

Semua poin di bawah statusnya **[BELUM DIKERJAKAN]** — ini rancangan, bukan kode. hz11 tolong pecah jadi:
- **TODO.md** (maks 5 poin, urutan prioritas — lihat usulan urutan di bagian paling bawah)
- **ROADMAP.md** (fitur besar yang belum masuk urutan dekat: App Manager, Sistem Multi-Tab penuh)
- **STATUS.md** (keputusan desain final + jebakan teknis yang sudah ditemukan, supaya semua akun baca ini duluan sebelum mulai)

---

## A. Perbaikan Bug Prioritas Tinggi

### Bug: folder `Android/data` app pihak ketiga tampil "Folder kosong"
- **Root cause dikonfirmasi** (BUKAN masalah izin): izin `MANAGE_EXTERNAL_STORAGE` sudah aktif (dicek langsung di HP, toggle ON)
- Dugaan kuat: Direktori baca dari database hasil scan (yang tidak pernah menyentuh `Android/data`), bukan baca folder langsung
- **Perbaikan**: untuk path seperti `Android/data`, Direktori harus baca folder langsung dari sistem file (live), bukan lewat data scan
- Catatan: belum ada jaminan 100% isi file di dalamnya bisa dibuka semua (pembatasan Android tambahan mungkin ada) — perlu dites langsung di HP setelah dikerjakan

## B. Fondasi Sistem Multi-Tab (dari temuan hz25 + review MainActivity.kt/HomeViewModel.kt)

Sekarang: 2 tab tetap (`AppTab.HOME`/`RECENT`), 1 instance `HomeViewModel` tunggal. `currentDir` Direktori masih `remember` lokal (bug lama — folder Gambar/Video/kategori lain sudah dinaikkan ke ViewModel duluan, Direktori ketinggalan).

**Pembagian state untuk arsitektur baru:**
- **Per-tab** (nanti ikut `viewModel(key=tabId)`): kategori terpilih, kata pencarian, sort, `showDirektori`+`currentDir`, mode Terbaru/Folder + folder terpilih, accordion, mode pilih (centang), file yang lagi buka menu titik-tiga
- **Tetap global**: data Room, trigger scan (`refreshScan`) — **perlu dipindah keluar dari HomeViewModel** jadi objek singleton supaya tidak dobel-scan kalau 4 tab dibuka bareng, `fileOpsTick` (notifikasi file berubah) — **juga perlu dipindah keluar**, `FavoritesStore`/`LastPlayedStore` (sudah aman), Clipboard (1), viewer fullscreen (`currentUri`, sudah aman)

**Urutan kerja (T1 → T4):**
1. **T1**: keluarkan trigger scan & `fileOpsTick` dari HomeViewModel jadi objek global; pindahkan `currentDir` masuk ke HomeViewModel (wajib duluan sebelum digandakan)
2. **T2**: bangun tab dinamis (pill nama tab + tombol `X` nempel di pill, maks 4 tab) + `viewModel(key=tabId)` per tab
3. **T3**: sambungkan drawer (Beranda/Direktori/Favorit) ke ViewModel tab yang sedang aktif, bukan referensi tetap
4. **T4**: edge case — tutup tab aktif pindah ke tab sebelahnya, dll

Tab "Terbaru" yang sudah ada: **tetap cuma lewat drawer**, tidak jadi tab khusus.

## C. Restyle Beranda

- Bar atas: hamburger → pill tab (+ tombol buka tab baru) → ikon kaca pembesar (cari semua file) → ikon Clipboard → titik tiga
- 2 kartu: **Storage** (lingkaran %, tap buka Direktori root) + **Analisis** (tap buka menu Analisis)
- Grid 6 kategori bulat warna-warni (restyle dari kartu yang sudah ada): PDF, Gambar, Excel, Video, Audio, Teks/Kode
- Baris ke-4 (dulu diusulkan Terbaru/Favorit/Direktori) **dihapus** — sudah ada di drawer, dikosongkan dulu
- Penataan visual "lebih modern" (Material You dll) — **ditunda**, tunggu referensi baru dari yhs13

## D. Restyle Direktori

- **Hapus TopAppBar/judul/tombol back** — back pakai gesture sistem (konsisten kategori lain)
- **Tambah ikon kaca pembesar** di posisi itu — cari file di dalam folder yang lagi dibuka (beda cakupan dari pencarian Beranda)
- Ikon folder: gaya kaca/glossy biru gradasi (referensi macOS/iOS)
- Baris & ikon lebih besar (ala RS Manager) + **badge** kecil penanda fungsi folder (Download=panah turun, DCIM=kamera, WhatsApp=logo)
- Folder `Android/data/<paket>` → ikon ASLI app pemiliknya (PackageManager, kalau app masih terpasang)
- Subjudul tiap baris: file terakhir dipakai **termasuk isi sub-folder** (query dari data scan yang sudah ada — `MAX(lastModified)` di bawah path folder itu, bukan baca live disk)
- Folder tersembunyi (prefix titik) → ditampilkan digelapkan, bukan disembunyikan total

## E. Restyle Analisis

- Hapus TopAppBar/judul/tombol back (konsisten). **Tanpa** ikon cari (beda dari Direktori)
- "Garis Besar" ganti gaya jadi **daftar** (ikon kotak warna + nama + ukuran per baris, referensi iPhone Files app), bukan pie chart+legenda RS Manager
- Kategori ditambah: **Apps** (ukuran total app terpasang, dari App Manager) dan **Archives** (file `.zip`)
- Urutan kerja submodul (termurah → termahal): **File Besar** (sort `sizeBytes`, limit N) → **Garis Besar** (rekap byte per kategori) → **Semua Partisi** (agregasi ukuran per folder dari data scan, paling berat — pikirkan caching)

## F. Mode Pilih File & Clipboard

- Trigger: long-press file
- Top bar mode pilih: `X`, counter (`3/16`), pilih-semua, balik-pilihan
- Bar bawah: Salin, Potong, Hapus, Namai ulang (aktif hanya kalau 1 file terpilih), Lebih banyak
- Centang → masuk **1 Clipboard** (tidak dikelompokkan), diakses via ikon di bar atas (bukan nempel ke 1 tab)
- **Tambahan**: Clipboard disambungkan ke clipboard sistem Android dua arah (`ClipData`+`FileProvider`) — salin di FileReaderApp bisa ditempel di app lain (Chrome/WhatsApp/dll) dan sebaliknya. Catatan: tidak semua app tujuan mendukung tempel file, itu wajar

## G. Menu "Lebih banyak" + Kompres/Ekstrak + Properti

- Menu dipakai: Sembunyikan (rename prefix titik), Bagikan, Properti, Pindahkan ke, Salin ke, Kompres. Dipangkas: semua item jaringan (LAN/FTP/Cloud/Pencarian Web)
- **Kompres/Ekstrak**: pakai `java.util.zip` bawaan (`Deflater.BEST_COMPRESSION` = level 9), **tanpa library tambahan**. Ekstrak `nama.zip` → folder baru `nama/` di lokasi sama (perlu tambah deteksi `.zip` di FileTypeDetector dulu)
- **Dialog Properti**: Jenis, Jalur, Ukuran, Diubah, Dapat dibaca/ditulis, Tersembunyi — semua sumber data sudah ada di `FileEntity`/`java.io.File`, tidak perlu tabel baru

## H. Fitur Baru: App Manager

- **Akses**: drawer (sejajar Direktori/Favorit), bukan kategori grid Beranda
- Daftar app terpasang (filter Semua/Pihak Ketiga/Sistem) + detail (info dasar, daftar izin versi sederhana, Aktivitas/Layanan/Penerima di bagian Lanjutan)
- Aksi: buka app, uninstall (dialog sistem), backup/ekstrak APK ke folder pilihan (baca `ApplicationInfo.publicSourceDir`, legal tanpa root — tangani app dengan split APK)
- **Batasan**: clear cache/data app lain TIDAK BISA langsung tanpa root — cukup tombol pintasan ke halaman Info Aplikasi sistem

---

## Usulan urutan TODO.md (5 poin prioritas)

1. Perbaikan bug `Android/data` kosong (bagian A) — dampak besar, kemungkinan perbaikan kecil
2. T1 fondasi multi-tab (bagian B) — wajib duluan sebelum T2-T4 jalan
3. Dialog Properti + deteksi `.zip`/Kompres/Ekstrak (bagian G) — kerjaan mandiri, cepat selesai
4. Fitur Analisis: File Besar dulu (bagian E) — termurah, langsung dari data scan
5. Restyle Beranda (bagian C) — visual, tidak bergantung ke fondasi tab

Sisanya (Direktori restyle, T2-T4 tab, App Manager, Clipboard sistem) masuk ROADMAP.md sebagai antrian berikutnya.
