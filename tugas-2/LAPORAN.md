# Laporan Tugas Kelompok ke-2

**Mata Kuliah:** Data Structures and Algorithm Analysis
**Topik (Week 4):** Arrays and its Operations
**Kelas:** LYCA - LEC
**Nama File Program:** `PlaylistArray.java`
**Berkas yang dibutuhkan:** `PlaylistArray.java` + `Lagu.java` (keduanya di root repositori)

## Identitas Kelompok (Group 1)

| No | Nama Lengkap | NIM |
|----|--------------|-----|
| 1 | AURA JATRA RATU SEMENDAWAI | 2902811884 |
| 2 | MUHAMMAD SETIADI PRATAMA | 2902807962 |
| 3 | MUH FAISAL BIMANTARA | 2902827232 |
| 4 | GAGE | 2902807395 |
| 5 | AFTA PASMA LOHDRI | 2902827150 |

---

## 1. Deskripsi Program

Program ini adalah pengembangan dari sistem manajemen playlist musik pada Tugas Kelompok 1.
Jika pada tugas sebelumnya fokusnya adalah konsep OOP, pada tugas ini fokusnya berpindah ke
**operasi dasar struktur data array**: bagaimana data lagu disimpan, ditelusuri, dicari,
disisipkan, dihapus, dan diurutkan.

Data disimpan dalam **array statis** `Lagu[] playlist` berkapasitas tetap **10 lagu**.
Karena panjang array selalu 10 sejak dibuat sementara isi nyatanya bisa lebih sedikit,
program menyimpan satu variabel pembantu `jumlahLagu` yang menandai batas data valid, yaitu
indeks `0` sampai `jumlahLagu - 1`. Seluruh operasi di bawah hanya bekerja pada rentang itu.

**Struktur class:**

| Class | Peran |
|-------|-------|
| `Lagu` | Entitas satu lagu (judul, artis, durasi). **Dipakai ulang langsung dari `Lagu.java` milik Tugas Kelompok 1** — tidak ditulis ulang, sesuai instruksi "Gunakan kembali dari Tugas Kelompok 1". Seluruh atribut `private` dengan getter/setter, memiliki `tampilkanInfo()` dan `tampilkanRingkas()`. |
| `PlaylistArray` | Pengelola array lagu sekaligus titik masuk program. Berisi `tampilkanSemuaLagu()`, `tambahLagu()`, `hapusLagu()`, `cariLagu()`, dan `urutkanLaguBerdasarkanDurasi()`. |

Kedua file berada di folder yang sama sehingga berbagi *default package* Java dan saling terlihat tanpa perlu `import`. Dengan cara ini `class Lagu` hanya ada satu definisi untuk seluruh repositori, dipakai bersama oleh Tugas 1 dan Tugas 2.

---

## 2. Analisis Kompleksitas Waktu (Big O Notation)

Notasi `n` di bawah ini adalah **jumlah lagu yang sedang tersimpan** (`jumlahLagu`),
bukan kapasitas array (10). Inilah yang menentukan berapa banyak langkah nyata dikerjakan.

| Operasi | Kompleksitas | Penjelasan |
|---------|--------------|------------|
| **Traversal**<br>`tampilkanSemuaLagu()` | **O(n)** | Satu perulangan dari indeks `0` sampai `n-1`. Setiap elemen dikunjungi **tepat satu kali** untuk dicetak, tidak ada yang dilewati dan tidak ada yang diulang. Jumlah langkah tumbuh lurus mengikuti banyaknya lagu: 5 lagu = 5 langkah, 10 lagu = 10 langkah. Tidak ada kasus terbaik/terburuk karena seluruh data memang harus dicetak. |
| **Searching**<br>`cariLagu()` | **O(1)** best case<br>**O(n)** average & worst case | Menggunakan **linear search**: judul dibandingkan dengan elemen ke-0, ke-1, dan seterusnya sampai cocok atau data habis. *Best case* terjadi bila lagu ada di posisi pertama, cukup 1 perbandingan. *Worst case* terjadi bila lagu ada di posisi terakhir atau **tidak ada sama sekali**, sehingga seluruh `n` elemen harus diperiksa. Rata-rata sekitar `n/2` perbandingan, yang secara Big O tetap O(n) karena konstanta diabaikan. Kelebihan metode ini: data **tidak perlu terurut** lebih dulu. |
| **Insertion**<br>`tambahLagu()` | **O(1)** | Lagu baru selalu ditempatkan pada slot kosong pertama, yaitu indeks `jumlahLagu` (tepat setelah elemen terakhir). Karena menyisipkan di **belakang**, **tidak ada satu pun elemen lama yang perlu digeser** — cukup satu penugasan `playlist[jumlahLagu] = laguBaru` lalu counter dinaikkan. Jumlah langkahnya tetap, tidak peduli playlist berisi 1 atau 10 lagu. Pemeriksaan kapasitas (`jumlahLagu >= 10`) juga hanya satu perbandingan, jadi tetap O(1).<br><br>*Catatan:* seandainya lagu disisipkan di **awal atau tengah** array, seluruh elemen setelahnya harus digeser ke kanan dan kompleksitasnya menjadi **O(n)**. |
| **Deletion**<br>`hapusLagu()` | **O(n)** | Dikerjakan dua tahap. **(1) Pencarian** posisi judul dengan linear search → O(n). **(2) Penggeseran**: setiap elemen di sebelah kanan posisi yang dihapus dipindahkan satu langkah ke kiri agar lubang bekas penghapusan tertutup dan data tetap rapat → paling banyak `n-1` perpindahan, juga O(n). Total `O(n) + O(n) = O(2n)`, dan karena konstanta diabaikan hasilnya **O(n)**. *Best case* menghapus elemen terakhir (tidak ada yang digeser), *worst case* menghapus elemen pertama (seluruh sisa array digeser). |
| **Sorting** (fitur tambahan)<br>`urutkanLaguBerdasarkanDurasi()` | **O(n²)** | Selection Sort dengan dua perulangan bersarang. Penjelasan rinci ada di bagian 3. |

### Kompleksitas Ruang (Space Complexity)

| Operasi | Ruang tambahan | Keterangan |
|---------|----------------|------------|
| Traversal | **O(1)** | Hanya memakai variabel pencacah `i`. |
| Searching | **O(1)** | Hanya `i`, `posisi`, dan `perbandingan`. |
| Insertion | **O(1)** | Tidak membuat array baru. |
| Deletion | **O(1)** | Penggeseran dilakukan **in-place** di array yang sama. |
| Sorting | **O(1)** | Pertukaran in-place, hanya butuh satu variabel `sementara`. |

Penyimpanan datanya sendiri **O(n)** (tepatnya selalu 10 slot karena arraynya statis), tetapi
tidak ada satu pun operasi yang mengalokasikan struktur bantu berukuran `n`.

---

## 3. Fitur Tambahan: Sorting Berdasarkan Durasi

Algoritma yang dipilih: **Selection Sort**, urut menaik (*ascending*) berdasarkan durasi.

**Cara kerjanya:** untuk setiap posisi `i` dari `0` sampai `n-2`, program menelusuri seluruh
sisa array di sebelah kanan `i` untuk menemukan lagu berdurasi **terkecil**, lalu menukarnya
dengan elemen di posisi `i`. Setelah putaran ke-`i` selesai, bagian array `0..i` dijamin
sudah terurut. Program menampilkan daftar **sebelum** dan **sesudah** pengurutan, beserta
jumlah perbandingan dan pertukaran yang benar-benar terjadi.

### Mengapa Selection Sort termasuk O(n²)?

Karena algoritma ini memakai **dua perulangan bersarang**, dan loop dalam dijalankan ulang
untuk setiap putaran loop luar:

```
for (i = 0; i < n-1; i++)          <- loop luar, berjalan (n-1) kali
    for (j = i+1; j < n; j++)      <- loop dalam, berjalan (n-1-i) kali
        bandingkan durasi          <- operasi dasar yang dihitung
```

Jumlah perbandingannya:

```
(n-1) + (n-2) + (n-3) + ... + 2 + 1  =  n(n-1)/2  =  (n² - n)/2
```

Dalam Big O Notation, hanya **suku dengan pertumbuhan tercepat** yang dipertahankan dan
seluruh koefisien konstanta diabaikan. Dari `(n² - n)/2`, suku `n²` tumbuh jauh lebih cepat
daripada `n`, dan pembagi `2` adalah konstanta. Maka kompleksitasnya adalah **O(n²)**.

Konsekuensi praktisnya: jumlah lagu dilipatduakan → waktu kerja menjadi sekitar **empat kali**
lipat.

| n (jumlah lagu) | Perbandingan = n(n-1)/2 |
|-----------------|--------------------------|
| 3 | 3 |
| 5 | 10 |
| 10 | 45 |
| 20 | 190 |
| 100 | 4.950 |

Satu ciri khas Selection Sort: jumlah perbandingannya **selalu sama** apa pun kondisi awal
data, karena loop dalam tidak pernah berhenti lebih cepat. Jadi **best case, average case,
dan worst case sama-sama O(n²)** — berbeda dari Bubble Sort yang bisa turun ke O(n) bila
diberi optimasi flag dan datanya sudah terurut. Keunggulan Selection Sort adalah jumlah
**pertukarannya** paling sedikit, maksimal `n-1` kali, sedangkan Bubble Sort bisa sampai
`n(n-1)/2` kali pertukaran.

Untuk `n` sekecil 10 seperti pada tugas ini, O(n²) masih sangat wajar. Namun bila playlist
berisi puluhan ribu lagu, algoritma O(n log n) seperti Merge Sort atau Quick Sort akan jauh
lebih tepat.

---

## 4. Ringkasan Perbandingan Operasi

| Operasi | Best | Average | Worst | Ruang |
|---------|------|---------|-------|-------|
| Traversal | O(n) | O(n) | O(n) | O(1) |
| Searching (linear) | O(1) | O(n) | O(n) | O(1) |
| Insertion (di akhir) | O(1) | O(1) | O(1) | O(1) |
| Deletion (dengan penggeseran) | O(1) | O(n) | O(n) | O(1) |
| Sorting (Selection Sort) | O(n²) | O(n²) | O(n²) | O(1) |

**Kesimpulan:** array unggul pada **akses langsung lewat indeks** (O(1)) dan penambahan di
akhir (O(1)), tetapi lemah pada **penghapusan** karena menuntut penggeseran elemen (O(n)) dan
pada **pencarian** karena tanpa pengurutan hanya bisa ditelusuri secara linear (O(n)).
Keterbatasan lainnya adalah ukuran array yang **tetap** — saat 10 slot penuh, program harus
menolak penambahan baru, tidak bisa membesar sendiri seperti `ArrayList` atau linked list.

---

## 5. Cara Menjalankan

```bash
javac PlaylistArray.java Lagu.java
java PlaylistArray
```

Atau compile seluruh berkas repositori sekaligus dengan `javac *.java`.

Program dijalankan dengan dua lagu contoh (*Perfect* dan *Shivers*) agar menu Traversal,
Searching, dan Sorting langsung dapat diuji.

## 6. Skenario Pengujian

| # | Menu | Aksi | Hasil yang diharapkan |
|---|------|------|-----------------------|
| 1 | 1 | Tampilkan semua lagu | Tampil 2 lagu awal (Traversal) |
| 2 | 2 | Tambah "Yellow - Coldplay - 4.10" | Masuk di posisi ke-3 (Insertion) |
| 3 | 4 | Cari "shivers" | Ditemukan di posisi ke-2, 2 perbandingan |
| 4 | 4 | Cari "Bohemian Rhapsody" | Tidak ditemukan, 3 perbandingan (worst case) |
| 5 | 5 | Urutkan berdasarkan durasi | Shivers (3.50) → Yellow (4.10) → Perfect (4.23) |
| 6 | 3 | Hapus "Perfect" | Terhapus, elemen sesudahnya digeser rapat |
| 7 | 2 | Tambah saat 10 slot penuh | Ditolak dengan pesan "Playlist sudah PENUH" |
| 8 | 3 | Hapus judul yang tidak ada | Pesan "tidak ditemukan", data tidak berubah |
| 9 | 2 | Isi durasi dengan huruf | Ditolak, program tidak crash |
| 10 | 6 | Keluar | Program berhenti dengan pesan penutup |
