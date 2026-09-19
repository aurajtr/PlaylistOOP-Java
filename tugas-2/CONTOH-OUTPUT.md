# Contoh Output Eksekusi

Acuan hasil menjalankan `java PlaylistArray`.
Program berada di **root repositori** bersama `Lagu.java`:
`javac PlaylistArray.java Lagu.java && java PlaylistArray`. Pakai sebagai panduan saat mengambil
**screenshot tiap operasi** untuk dikumpulkan ke LMS.

Bagian bertanda `<--` adalah teks yang diketik pengguna.

## Tampilan awal

```
=====================================================
   SISTEM MANAJEMEN PLAYLIST MUSIK - BERBASIS ARRAY
   Tugas Kelompok 2 | Group 1 | Kelas LYCA - LEC
   Kapasitas array statis: 10 lagu
=====================================================

=== MENU PLAYLIST MUSIK ===
1. Tampilkan semua lagu
2. Tambah lagu baru
3. Hapus lagu berdasarkan judul
4. Cari lagu berdasarkan judul
5. Urutkan berdasarkan durasi
6. Keluar
Pilih menu:
```

## 1. Traversal - menu 1

```
Pilih menu: 1                                          <--

--- DAFTAR LAGU DALAM PLAYLIST ---
  No  Judul                  Artis           Durasi
  ------------------------------------------------------------
  1   Perfect                Ed Sheeran       4.23 menit
  2   Shivers                Ed Sheeran       3.50 menit
  ------------------------------------------------------------
  Total: 2 dari 10 slot terisi.
```

## 2. Insertion - menu 2

```
Pilih menu: 2                                          <--

--- TAMBAH LAGU BARU ---
Masukkan judul lagu   : Yellow                         <--
Masukkan artis        : Coldplay                       <--
Masukkan durasi (menit): 4.10                          <--
  [OK] Lagu berhasil ditambahkan pada posisi ke-3!

--- DAFTAR LAGU DALAM PLAYLIST ---
  No  Judul                  Artis           Durasi
  ------------------------------------------------------------
  1   Perfect                Ed Sheeran       4.23 menit
  2   Shivers                Ed Sheeran       3.50 menit
  3   Yellow                 Coldplay         4.10 menit
  ------------------------------------------------------------
  Total: 3 dari 10 slot terisi.
```

## 3. Searching - menu 4 (ditemukan)

```
Pilih menu: 4                                          <--

--- CARI LAGU BERDASARKAN JUDUL ---
Masukkan judul lagu yang dicari: shivers               <--
  [OK] Lagu ditemukan pada posisi ke-2 (indeks array ke-1).
  Judul  : Shivers
  Artis  : Ed Sheeran
  Durasi : 3.50 menit
      Jumlah perbandingan: 2 langkah.
```

Pencarian tidak membedakan huruf besar/kecil, sehingga `shivers` tetap cocok dengan `Shivers`.

## 4. Searching - menu 4 (tidak ditemukan / worst case)

```
Pilih menu: 4                                          <--

--- CARI LAGU BERDASARKAN JUDUL ---
Masukkan judul lagu yang dicari: Bohemian Rhapsody     <--
  [X] Lagu "Bohemian Rhapsody" TIDAK ditemukan.
      Jumlah perbandingan: 3 (seluruh data diperiksa -> worst case O(n)).
```

## 5. Sorting - menu 5 (sebelum & sesudah)

```
Pilih menu: 5                                          <--

--- URUTKAN LAGU BERDASARKAN DURASI (ASCENDING) ---

>> SEBELUM PENGURUTAN:
  1   Perfect                Ed Sheeran       4.23 menit
  2   Shivers                Ed Sheeran       3.50 menit
  3   Yellow                 Coldplay         4.10 menit

>> SESUDAH PENGURUTAN:
  1   Shivers                Ed Sheeran       3.50 menit
  2   Yellow                 Coldplay         4.10 menit
  3   Perfect                Ed Sheeran       4.23 menit

  Statistik Selection Sort untuk n = 3 lagu:
    - Jumlah perbandingan : 3  (sesuai rumus n(n-1)/2 = 3)
    - Jumlah pertukaran   : 2
    - Kompleksitas waktu  : O(n^2) karena dua loop bersarang.
```

## 6. Deletion - menu 3

```
Pilih menu: 3                                          <--

--- HAPUS LAGU BERDASARKAN JUDUL ---
Masukkan judul lagu yang ingin dihapus: Perfect        <--
  [OK] Lagu "Perfect" berhasil dihapus dari posisi ke-3.
       Elemen setelahnya sudah digeser agar data tetap rapat.

--- DAFTAR LAGU DALAM PLAYLIST ---
  No  Judul                  Artis           Durasi
  ------------------------------------------------------------
  1   Shivers                Ed Sheeran       3.50 menit
  2   Yellow                 Coldplay         4.10 menit
  ------------------------------------------------------------
  Total: 2 dari 10 slot terisi.
```

## 7. Penanganan kasus khusus

```
  [!] Playlist sudah PENUH (10/10 lagu).
      Hapus salah satu lagu dulu sebelum menambah yang baru.

  [!] Lagu "TidakAda" tidak ditemukan di dalam playlist.

  [!] Durasi harus berupa angka. Penambahan dibatalkan.

  [!] Butuh minimal 2 lagu untuk bisa diurutkan.

  (Playlist masih kosong. Silakan tambah lagu terlebih dahulu.)

  [!] Pilihan tidak dikenali. Masukkan angka 1 sampai 6.
```

## 8. Keluar - menu 6

```
Pilih menu: 6                                          <--

Terima kasih telah menggunakan program ini. Sampai jumpa!
```
