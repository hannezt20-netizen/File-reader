# ROADMAP.md
> Update hz11, 13 Sept 2026 - urutan prioritas kerja ke depan

## Prioritas Sekarang
1. Fase D (hz19) - deteksi duplikat (semua tipe file, custom pilihan kategori), menu Analisis>File Duplikat. Mulai fondasi setelah checkpoint 5 hz25 confirmed
2. Sisa multi-select: checkpoint 5 Gambar (hz25) - satu-satunya belum confirmed
3. Finalisasi SettingsPanel PDF + keputusan hamburger Beranda (hz21)
4. Lanjut restyle Beranda/Direktori/Analisis (yhs13 + usulan hz19: Grid/List+Sort, PDF thumbnail asli, breadcrumb+badge %) - T1 sudah selesai, aman dimulai

## Menyusul Setelah Prioritas Di Atas
- Sticky header & Fast Scroller galeri Gambar (ditunda sejak 13 Sept)
- Long-press folder kosong di Direktori utk dihapus (hz25)

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
