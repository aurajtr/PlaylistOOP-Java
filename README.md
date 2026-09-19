# PlaylistOOP-Java

Repositori tugas kelompok mata kuliah **Data Structures and Algorithm Analysis**.
Kelas: **LYCA - LEC**

## Group 1

| No | Nama Lengkap | NIM |
|----|--------------|-----|
| 1  | AURA JATRA RATU SEMENDAWAI | 2902811884 |
| 2  | MUHAMMAD SETIADI PRATAMA | 2902807962 |
| 3  | MUH FAISAL BIMANTARA | 2902827232 |
| 4  | GAGE | 2902807395 |
| 5  | AFTA PASMA LOHDRI | 2902827150 |

## Daftar Tugas

| Tugas | Topik | Lokasi | Cara menjalankan |
|-------|-------|--------|------------------|
| Kelompok 1 (Week 3) | Introduction to OOP & Data Structures | root repositori | `javac PlaylistOOP.java && java PlaylistOOP` |
| Kelompok 2 (Week 4) | Arrays and its Operations | `PlaylistArray.java` + `Lagu.java` (root) | `javac PlaylistArray.java Lagu.java && java PlaylistArray` |

---

## Tugas Kelompok 1 - Sistem Manajemen Playlist Musik (OOP)

Menerapkan enkapsulasi, inheritance, dan polymorphism pada sistem playlist musik.

```bash
javac PlaylistOOP.java
java PlaylistOOP
```

## Tugas Kelompok 2 - Playlist Musik Berbasis Array

Pengembangan dari Tugas 1 dengan fokus pada **operasi dasar array**: Traversal, Searching
(linear search), Insertion, Deletion, ditambah Sorting berdasarkan durasi (Selection Sort).
Data disimpan pada array statis berkapasitas maksimal 10 lagu.

```bash
javac PlaylistArray.java Lagu.java
java PlaylistArray
```

`class Lagu` tidak ditulis ulang — `PlaylistArray.java` memakai ulang `Lagu.java` dari
Tugas 1, sesuai instruksi tugas. Berkas laporan ada di folder [`tugas-2/`](tugas-2/).

Analisis kompleksitas waktu (Big O) tiap operasi ada di [`tugas-2/LAPORAN.md`](tugas-2/LAPORAN.md), contoh output tiap menu di [`tugas-2/CONTOH-OUTPUT.md`](tugas-2/CONTOH-OUTPUT.md).
