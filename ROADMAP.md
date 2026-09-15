# ROADMAP.md
> Update hz11, 13 Sept 2026 - urutan prioritas kerja ke depan

## Prioritas Sekarang
1. Fase D (hz19) - deteksi foto duplikat/mirip, masuk menu Analisis>File Duplikat. Sepakati scope dulu (foto saja vs semua tipe file) sebelum eksekusi
2. Multi-select tuntas semua kategori (hz25) - lanjutkan checkpoint 5 Gambar (accordion hz19 sudah selesai, aman dilanjut), verifikasi checkpoint 2/3/4/6
3. Lirik Audio Tahap C2-C5 (ydiv2) - lanjut dari sumber tag USLT tertanam
4. Finalisasi SettingsPanel PDF + keputusan layout hamburger Beranda (hz21)

## Menyusul Setelah Prioritas Di Atas
- Sticky header & Fast Scroller galeri Gambar (ditunda sejak 13 Sept)
- Long-press folder kosong di Direktori utk dihapus (hz25)
- Hapus tombol hamburger dari PlayerControlBar Audio (ydiv2)

## Ide Fitur Belum Resmi (dari user, belum masuk TODO)
- Internet speed meter (indikator kecepatan koneksi)
- Clipboard tambahan di FileReaderApp

## Catatan Rilis APK
- [SELESAI] Upload APK ke GitHub Release sudah jalan (commit 44ae7f8, 13 Sept) - distribusi kini lewat link Releases, bukan Actions Artifacts

## Restyle & Fitur Baru (dari sesi desain yhs13, 14 Sept)
- Restyle Beranda: pill tab + tombol tab baru, 2 kartu (Storage+Analisis), grid 6 kategori, baris ke-4 dihapus - setelah T1 selesai
- Restyle Direktori: ikon folder glossy biru, badge Download/DCIM/WhatsApp, ikon app asli utk Android/data/<paket>, ikon cari-dalam-folder, subjudul dari MAX(lastModified) termasuk subfolder
- Restyle Analisis: gaya daftar (bukan pie chart), tambah kategori Apps & Archives, urutan submodul File Besar->Garis Besar->Semua Partisi
- T2-T4 multi-tab lanjutan: pill tab dinamis (maks 4 tab), wiring drawer ke tab aktif, edge case tutup tab
- Fitur besar baru: App Manager (daftar app terpasang, detail izin/aktivitas, uninstall, backup APK) - akses dari drawer
- Clipboard tersambung ke clipboard sistem Android dua arah (ClipData+FileProvider)
- Menu "Lebih banyak": Sembunyikan/Bagikan/Properti/Pindahkan/Salin/Kompres (item jaringan LAN/FTP/Cloud dipangkas)
