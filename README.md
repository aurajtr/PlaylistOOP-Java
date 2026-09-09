# PlaylistOOP-Java

Tugas Kelompok 1 - Introduction to OOP & Data Structures
Mata kuliah Data Structures and Algorithm Analysis (Week 3).

Sistem manajemen playlist musik sederhana berbasis Java yang menerapkan enkapsulasi, inheritance, dan polymorphism, dengan array sebagai struktur data penyimpan objek lagu.

## Identitas Kelompok

| No | Nama Lengkap | NIM |
|----|--------------|-----|
| 1  | *(isi nama)* | *(isi NIM)* |
| 2  | *(isi nama)* | *(isi NIM)* |
| 3  | *(isi nama)* | *(isi NIM)* |
| 4  | *(isi nama)* | *(isi NIM)* |

## Isi Repositori

| Berkas | Keterangan |
|--------|------------|
| `PlaylistOOP.java` | Kode program lengkap beserta komentar pada setiap method utama |
| `LAPORAN.md` | Laporan penjelasan inheritance, polymorphism, dan analisis kompleksitas |

## Struktur Class

- `Lagu` menyimpan judul, artis, dan durasi dengan atribut private serta getter/setter.
- `Playlist` menampung koleksi lagu di dalam array `Lagu[]` berkapasitas tetap.
- `User` adalah parent class abstrak berisi identitas pengguna dan method tampilkan daftar lagu.
- `Admin` adalah turunan `User` yang berhak menambah lagu ke playlist.
- `Member` adalah turunan `User` yang berhak mencari lagu dan menghitung rata-rata durasi.
- `PlaylistOOP` adalah class utama yang menjalankan skenario demo.

## Cara Menjalankan

```bash
javac PlaylistOOP.java
java PlaylistOOP
```

Program tidak memerlukan pustaka eksternal dan sudah diuji pada JDK 17 ke atas.
