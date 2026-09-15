# Laporan Kerja - ydiv2
**Area:** PDF Text-to-Speech (kontrol notifikasi/lock screen), Audio Player (implementasi + UI + playlist + lirik)
> Format: entri terbaru di atas, ringkas per pencapaian, disimpan maks ~10 entri terakhir.

---

## Perlu Perhatian / Blocker
Tidak ada saat ini.

## Log Pencapaian (terbaru di atas)

**15 Sept** - Audit lintas-akun (README/STATUS/ROADMAP/TODO/laporan semua akun + git log): ditemukan beberapa dokumen acuan hz11 basi (TODO poin Android/data & T1 hz25 sudah commit selesai; diagnosis awal yhs13 soal Android/data sudah dikoreksi hz25 jadi "pembatasan sistem Android 11+"; ROADMAP Lirik C2-C5 & hapus tombol hamburger PlayerControlBar total basi). Catatan disisipkan ke laporan ini, tidak dibuat file terpisah (hemat kuota Actions).

**15 Sept** - Bug tombol X notifikasi TTS: **SELESAI & terkonfirmasi.** Root cause: kartu "Media Controls" modern baca custom action dari level MediaSession (`PlaybackStateCompat.addCustomAction`), bukan dari notification action biasa seperti Prev/Play/Next. Fix: tambah `addCustomAction` di `TtsPlaybackBridge.updateState()` + handle `onCustomAction` di session callback.

**SELESAI** - TtsPlaybackService (statusbar+lock screen TTS) didaftarkan ke manifest & disambungkan penuh ke PdfViewerScreen (sebelumnya kode ada tapi tidak pernah dipanggil). 3 tombol nav fix (`MediaSessionCompat.Callback` dipasang).

**SELESAI** - Audio Focus: TTS (`TtsHelper`) & Audio Player (`AudioPlayerService`) sama-sama request/abandon audio focus dengan benar - gantian normal dgn app musik lain (Musicolet) dikonfirmasi.

**SELESAI** - Notifikasi Audio dirombak: non-dismissable, tombol X (stop), tap badan buka ke Pemutar.

**SELESAI** - Checkpoint B Playlist: thumbnail cover album, judul asli (tag TIT2), menu "..." (Info lagu/Hapus-Tambah Playlist/Bagikan via FileProvider).

**SELESAI** - Checkpoint A Lirik: `Id3UsltReader` digeneralisasi + `readTitle()`, `LyricsStore` dirombak total ke sumber tag USLT tertanam (bukan file .lrc privat spt rencana awal) - deteksi otomatis pola `[mm:ss.ss]` di dalamnya (ala Musicolet).

**SELESAI** - Fix bug: judul & lagu tidak sinkron di tab Playlist (index geser krn `mapNotNull` buang path hilang sebelum index dihitung - fix filter dulu baru hitung index).

**SELESAI** - Tahap C1: navigasi Audio Player dirombak jadi `HorizontalPager` 3 halaman (Playlist-Pemutar-Lirik), menyimpang dari rencana awal "bottom sheet Spotify" di ROADMAP (alasan: kurang nyaman diakses + hindari konflik gestur drawer).

**Riwayat lebih lama (ringkas):** Tahap B playlist Room, Tahap A redesain UI neumorphism, implementasi dasar Audio Player, tombol close bar TTS - semua SELESAI & terkonfirmasi lama.

## Rencana Selanjutnya
Diskusi fitur baru (belum dipatch): TTS highlight per KALIMAT di Mode Baca PDF - `ReflowPage` dapat parameter `activeSentenceIndex: Int?`, composable baru `HighlightedSentences`, auto-scroll via `BringIntoViewRequester`. `formatReadableText()` dikonfirmasi sama persis dgn yang dipakai TTS jadi index dijamin sinkron.
