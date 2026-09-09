# Laporan Tugas Kelompok ke-1

**Mata Kuliah:** Data Structures and Algorithm Analysis
**Topik:** Introduction to OOP & Data Structures (Week 3)
**Judul Program:** Sistem Manajemen Playlist Musik Sederhana (`PlaylistOOP.java`)

## Identitas Kelompok

| No | Nama Lengkap | NIM |
|----|--------------|-----|
| 1  | *(isi nama)* | *(isi NIM)* |
| 2  | *(isi nama)* | *(isi NIM)* |
| 3  | *(isi nama)* | *(isi NIM)* |
| 4  | *(isi nama)* | *(isi NIM)* |

---

## 1. Deskripsi Singkat Program

Program ini mensimulasikan sistem manajemen playlist musik dengan dua peran pengguna.
Admin Playlist bertugas menambahkan lagu dan melihat daftar lagu yang tersimpan.
Pengguna Playlist dapat menelusuri lagu, melihat detail satu lagu, dan menghitung rata-rata durasi.

Struktur data yang dipakai adalah **array statis** `Lagu[]` di dalam class `Playlist`.
Array dipilih karena akses ke elemen berdasarkan indeks dilakukan dalam waktu konstan.
Konsekuensinya, kapasitas ditetapkan di awal sehingga penambahan lagu harus diperiksa terhadap batas kapasitas.

### Struktur Class

| Class | Peran | Relasi |
|-------|-------|--------|
| `Lagu` | Entitas data satu lagu | Disimpan di dalam array milik `Playlist` |
| `Playlist` | Struktur data penyimpan koleksi lagu | Dipakai bersama oleh `Admin` dan `Member` |
| `User` | Parent class abstrak untuk semua pengguna | Diturunkan oleh `Admin` dan `Member` |
| `Admin` | Child class, berhak menambah lagu | `extends User` |
| `Member` | Child class, berhak mencari dan menghitung | `extends User` |
| `PlaylistOOP` | Class utama berisi `main()` | Menjalankan skenario demo |

---

## 2. Penerapan Enkapsulasi

Seluruh atribut pada `Lagu` dan `Playlist` dideklarasikan `private`, sehingga tidak dapat disentuh langsung dari luar class.
Akses dilakukan lewat getter untuk membaca dan setter untuk mengubah.

Manfaat nyatanya terlihat pada `setDurasi()`. Method tersebut menolak nilai negatif dan menggantinya dengan nol.
Seandainya atribut `durasi` bersifat publik, kode di luar class bisa menuliskan nilai tidak masuk akal dan merusak hasil perhitungan rata-rata.
Dengan enkapsulasi, aturan validasi cukup ditulis di satu tempat dan berlaku untuk semua jalur pengisian data, termasuk lewat constructor.

Hal serupa berlaku pada `Playlist`. Array `daftarLagu` disembunyikan agar kode luar tidak dapat menulis ke indeks sembarangan.
Penambahan hanya boleh lewat `tambahLagu()` yang menjaga konsistensi antara isi array dan pencacah `jumlahLagu`.

---

## 3. Penerapan Inheritance

`User` dibuat sebagai class abstrak yang menampung segala hal yang dimiliki setiap jenis pengguna, yaitu `nama`, `id`, dan referensi ke `playlist`.
`Admin` dan `Member` mewarisinya dengan kata kunci `extends`.

Ada tiga bentuk pewarisan yang terjadi di program ini.

- **Pewarisan atribut.** Baik `Admin` maupun `Member` otomatis memiliki `nama`, `id`, dan `playlist` tanpa perlu mendeklarasikan ulang. Atribut tersebut diberi modifier `protected` agar tetap tertutup dari luar hierarki, tetapi terbuka untuk class turunan.
- **Pewarisan constructor lewat `super()`.** Constructor `Admin` dan `Member` hanya meneruskan argumen ke constructor parent. Logika inisialisasi identitas ditulis satu kali di `User`.
- **Pewarisan method konkret.** Method `tampilkanDaftarLagu()` diletakkan di `User` karena kedua peran sama-sama berhak melihat isi playlist. Menempatkannya di parent menghilangkan duplikasi kode yang akan muncul bila method itu ditulis dua kali di masing-masing child.

Setelah bagian bersama diangkat ke parent, tiap child tinggal menambahkan kemampuan khasnya.
`Admin` menambah `tambahLagu()`, sedangkan `Member` menambah `cariLagu()`, `tampilkanDetailLagu()`, dan `hitungRataRataDurasi()`.
Inilah yang membuat pembagian hak akses antar peran menjadi tegas.

---

## 4. Penerapan Polymorphism

Polymorphism berarti satu nama method dapat menghasilkan perilaku berbeda. Program ini memakai dua bentuknya.

### 4.1 Method Overriding (runtime polymorphism)

`User` mendeklarasikan dua method abstrak, yaitu `getPeran()` dan `tampilkanHakAkses()`.
Keduanya wajib diimplementasikan ulang oleh setiap child dengan isi yang berbeda.
`Admin` mengembalikan peran "Admin Playlist" dan mencetak hak akses menambah lagu.
`Member` mengembalikan peran "Pengguna Playlist" dan mencetak hak akses menelusuri playlist.

Efeknya paling jelas pada `tampilkanIdentitas()` yang hanya ditulis sekali di parent.
Method tersebut memanggil `getPeran()`, dan versi yang benar-benar dijalankan ditentukan oleh tipe objek sebenarnya saat program berjalan, bukan oleh tipe variabelnya.
Mekanisme ini disebut *dynamic method dispatch*.

### 4.2 Upcasting melalui array bertipe parent

Pada bagian akhir `main()`, objek `Admin` dan `Member` disimpan dalam satu array bertipe `User`:

```java
User[] daftarPengguna = new User[2];
daftarPengguna[0] = admin;
daftarPengguna[1] = member;

for (int i = 0; i < daftarPengguna.length; i++) {
    daftarPengguna[i].tampilkanIdentitas();
    daftarPengguna[i].tampilkanHakAkses();
}
```

Perulangan itu memanggil baris kode yang sama untuk kedua elemen, tetapi keluarannya berbeda mengikuti jenis objeknya.
Keuntungannya, penambahan peran baru di masa depan, misalnya `PremiumMember`, tidak menuntut perubahan pada perulangan tersebut.

### 4.3 Method Overloading (compile-time polymorphism)

Class `Admin` memiliki dua method bernama sama dengan daftar parameter berbeda.

| Versi | Tanda tangan | Kegunaan |
|-------|--------------|----------|
| 1 | `tambahLagu(Lagu lagu)` | Menerima objek `Lagu` yang sudah dibentuk |
| 2 | `tambahLagu(String judul, String artis, double durasi)` | Menerima data mentah lalu membentuk objeknya sendiri |

Versi kedua memanggil versi pertama, sehingga logika penambahan tetap berada di satu tempat.
Java memilih versi yang dipakai pada saat kompilasi berdasarkan tipe argumen yang diberikan.

---

## 5. Analisis Kompleksitas Operasi

Bagian ini menjadi jembatan menuju analisis algoritma pada tugas berikutnya.

| Operasi | Method | Kompleksitas | Alasan |
|---------|--------|--------------|--------|
| Menambah lagu | `Playlist.tambahLagu` | O(1) | Posisi penyisipan sudah diketahui dari pencacah |
| Mengakses lagu ke-i | `Playlist.getLagu` | O(1) | Array mendukung akses acak lewat indeks |
| Menampilkan daftar | `User.tampilkanDaftarLagu` | O(n) | Seluruh elemen ditelusuri satu kali |
| Mencari judul | `Member.cariLagu` | O(n) | Linear search, terburuk ketika data tidak ada |
| Rata-rata durasi | `Member.hitungRataRataDurasi` | O(n) | Menjumlahkan seluruh durasi |

Keterbatasan array statis adalah kapasitas tetap. Bila playlist penuh, penambahan ditolak.
Alternatifnya adalah array dinamis yang menyalin isi ke array lebih besar saat penuh, atau linked list yang tumbuh sesuai kebutuhan namun kehilangan akses indeks O(1).

---

## 6. Cara Menjalankan Program

```bash
javac PlaylistOOP.java
java PlaylistOOP
```

---

## 7. Kesimpulan

Enkapsulasi menjaga data lagu tetap valid karena setiap perubahan harus melewati setter yang memvalidasi.
Inheritance menghapus duplikasi dengan mengangkat atribut dan perilaku bersama ke class `User`.
Polymorphism membuat kode pemanggil tetap sederhana meskipun jenis penggunanya bertambah, karena pemilihan perilaku diserahkan kepada objeknya sendiri.
Array sebagai struktur data memberikan akses indeks yang cepat, dengan konsekuensi kapasitas yang harus ditetapkan di awal.
