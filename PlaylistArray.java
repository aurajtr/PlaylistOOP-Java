/* =====================================================================
 * Data Structures and Algorithm Analysis
 * Tugas Kelompok ke-2 (Week 4) - Arrays and its Operations
 * Nama File : PlaylistArray.java
 * Topik     : Sistem Manajemen Playlist Musik Berbasis Array
 * Kelas     : LYCA - LEC
 *
 * IDENTITAS KELOMPOK (Group 1)
 *   1. AURA JATRA RATU SEMENDAWAI  -  2902811884
 *   2. MUHAMMAD SETIADI PRATAMA    -  2902807962
 *   3. MUH FAISAL BIMANTARA        -  2902827232
 *   4. GAGE                        -  2902807395
 *   5. AFTA PASMA LOHDRI           -  2902827150
 *
 * Operasi array yang diimplementasikan:
 *   - Traversal  : menelusuri seluruh elemen array satu per satu   -> O(n)
 *   - Searching  : linear search berdasarkan judul lagu            -> O(n)
 *   - Insertion  : menyisipkan lagu pada posisi kosong berikutnya  -> O(1)
 *   - Deletion   : menghapus lagu lalu menggeser elemen agar rapat -> O(n)
 *   - Sorting    : Selection Sort berdasarkan durasi (ascending)   -> O(n^2)
 *
 * Class Lagu TIDAK ditulis ulang di file ini, melainkan dipakai ulang dari
 * Lagu.java milik Tugas Kelompok 1 (sesuai instruksi "Gunakan kembali dari
 * Tugas Kelompok 1"). Kedua file berada pada folder yang sama sehingga
 * berbagi default package dan saling terlihat tanpa perlu import.
 *
 * Berkas yang dibutuhkan: PlaylistArray.java + Lagu.java
 *
 * Cara menjalankan:
 *   javac PlaylistArray.java Lagu.java
 *   java PlaylistArray
 * ===================================================================== */

import java.util.Locale;
import java.util.Scanner;

/* =====================================================================
 * CLASS PLAYLISTARRAY
 * Mengelola kumpulan objek Lagu di dalam ARRAY STATIS berkapasitas 10.
 *
 * Kenapa butuh atribut jumlahLagu?
 *   Panjang array (playlist.length) SELALU 10 sejak dibuat, sedangkan isi
 *   nyatanya bisa kurang dari itu. Variabel jumlahLagu menandai batas data
 *   yang valid, yaitu indeks 0 .. jumlahLagu-1. Semua operasi array di
 *   bawah ini bekerja pada rentang tersebut, bukan pada seluruh 10 slot.
 * ===================================================================== */
public class PlaylistArray {

    // Kapasitas maksimum array sesuai ketentuan tugas (maksimal 10 lagu)
    private static final int MAKS_LAGU = 10;

    private Lagu[] playlist;   // array statis penampung objek Lagu
    private int jumlahLagu;    // banyaknya slot yang sudah terisi
    private Scanner input;     // satu Scanner dipakai bersama seluruh menu

    /*
     * Constructor.
     * Menyiapkan array kosong berukuran tetap MAKS_LAGU dan menandai bahwa
     * belum ada data yang valid (jumlahLagu = 0).
     */
    public PlaylistArray() {
        this.playlist = new Lagu[MAKS_LAGU];
        this.jumlahLagu = 0;
        this.input = new Scanner(System.in);
    }

    /* =================================================================
     * OPERASI 1 - TRAVERSAL
     * void tampilkanSemuaLagu()
     *
     * Logika: mulai dari indeks 0, kunjungi setiap elemen valid tepat satu
     * kali sampai indeks jumlahLagu-1, lalu cetak isinya. Tidak ada elemen
     * yang dilewati dan tidak ada yang dikunjungi dua kali.
     *
     * Kompleksitas waktu : O(n)  -> loop berjalan persis n kali
     * Kompleksitas ruang : O(1)  -> tidak ada array bantuan
     * ================================================================= */
    public void tampilkanSemuaLagu() {
        System.out.println();
        System.out.println("--- DAFTAR LAGU DALAM PLAYLIST ---");

        // Kasus khusus: array masih kosong, tidak ada yang ditelusuri
        if (jumlahLagu == 0) {
            System.out.println("  (Playlist masih kosong. Silakan tambah lagu terlebih dahulu.)");
            return;
        }

        System.out.println(String.format("  %-3s %-22s %-15s %s", "No", "Judul", "Artis", "Durasi"));
        System.out.println("  ------------------------------------------------------------");

        // Inilah proses TRAVERSAL: satu kali lewat dari awal sampai akhir data
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanRingkas(i + 1);
        }

        System.out.println("  ------------------------------------------------------------");
        System.out.println("  Total: " + jumlahLagu + " dari " + MAKS_LAGU + " slot terisi.");
    }

    /* =================================================================
     * OPERASI 2 - INSERTION
     * void tambahLagu()
     *
     * Logika: lagu baru selalu ditempatkan pada slot kosong pertama, yaitu
     * indeks jumlahLagu (tepat setelah elemen terakhir). Karena menyisipkan
     * di BELAKANG, tidak ada satu pun elemen lama yang perlu digeser.
     * Sebelum menyisipkan, program wajib memeriksa apakah array sudah penuh
     * agar tidak terjadi ArrayIndexOutOfBoundsException.
     *
     * Kompleksitas waktu : O(1)  -> satu penugasan, tanpa perulangan
     * Kompleksitas ruang : O(1)
     * ================================================================= */
    public void tambahLagu() {
        System.out.println();
        System.out.println("--- TAMBAH LAGU BARU ---");

        // PEMERIKSAAN KAPASITAS: array statis tidak bisa membesar sendiri
        if (jumlahLagu >= MAKS_LAGU) {
            System.out.println("  [!] Playlist sudah PENUH (" + MAKS_LAGU + "/" + MAKS_LAGU + " lagu).");
            System.out.println("      Hapus salah satu lagu dulu sebelum menambah yang baru.");
            return;
        }

        System.out.print("Masukkan judul lagu   : ");
        String judul = input.nextLine().trim();
        if (judul.isEmpty()) {
            System.out.println("  [!] Judul tidak boleh kosong. Penambahan dibatalkan.");
            return;
        }

        System.out.print("Masukkan artis        : ");
        String artis = input.nextLine().trim();
        if (artis.isEmpty()) {
            artis = "(tidak diketahui)";
        }

        System.out.print("Masukkan durasi (menit): ");
        double durasi = bacaDurasi();
        if (durasi < 0) { // nilai penanda bahwa input tidak valid
            System.out.println("  [!] Durasi harus berupa angka. Penambahan dibatalkan.");
            return;
        }

        // INTI OPERASI INSERTION: isi slot kosong berikutnya, lalu naikkan counter
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;

        System.out.println("  [OK] Lagu berhasil ditambahkan pada posisi ke-" + jumlahLagu + "!");
        tampilkanSemuaLagu();
    }

    /* =================================================================
     * OPERASI 3 - DELETION
     * void hapusLagu()
     *
     * Logika dua tahap:
     *   1) CARI posisi lagu memakai linear search (judul, abaikan huruf
     *      besar/kecil).
     *   2) GESER setiap elemen di sebelah kanan posisi tersebut satu langkah
     *      ke kiri, sehingga lubang bekas penghapusan tertutup dan data
     *      tetap rapat (tidak ada slot kosong di tengah array).
     *   Slot terakhir diset null agar tidak menyisakan referensi ganda,
     *   lalu jumlahLagu dikurangi satu.
     *
     * Kompleksitas waktu : O(n)  -> pencarian O(n) + penggeseran O(n)
     * Kompleksitas ruang : O(1)  -> penggeseran dilakukan in-place
     * ================================================================= */
    public void hapusLagu() {
        System.out.println();
        System.out.println("--- HAPUS LAGU BERDASARKAN JUDUL ---");

        if (jumlahLagu == 0) {
            System.out.println("  [!] Playlist masih kosong, tidak ada yang bisa dihapus.");
            return;
        }

        System.out.print("Masukkan judul lagu yang ingin dihapus: ");
        String judul = input.nextLine().trim();

        // Tahap 1 - cari dulu posisinya
        int posisi = cariIndeks(judul);
        if (posisi == -1) {
            System.out.println("  [!] Lagu \"" + judul + "\" tidak ditemukan di dalam playlist.");
            return;
        }

        String judulTerhapus = playlist[posisi].getJudul();

        // Tahap 2 - GESER elemen setelah posisi satu langkah ke kiri.
        // Contoh menghapus indeks 1 dari [A][B][C][D]:
        //   playlist[1] = playlist[2] -> [A][C][C][D]
        //   playlist[2] = playlist[3] -> [A][C][D][D]
        //   slot terakhir dikosongkan -> [A][C][D][ ]
        for (int i = posisi; i < jumlahLagu - 1; i++) {
            playlist[i] = playlist[i + 1];
        }

        playlist[jumlahLagu - 1] = null; // buang referensi sisa di ekor array
        jumlahLagu--;                    // data valid berkurang satu

        System.out.println("  [OK] Lagu \"" + judulTerhapus + "\" berhasil dihapus dari posisi ke-"
                + (posisi + 1) + ".");
        System.out.println("       Elemen setelahnya sudah digeser agar data tetap rapat.");
        tampilkanSemuaLagu();
    }

    /* =================================================================
     * OPERASI 4 - SEARCHING (LINEAR SEARCH)
     * void cariLagu()
     *
     * Logika: bandingkan judul yang dicari dengan elemen ke-0, ke-1, dan
     * seterusnya sampai ketemu atau data habis. Disebut linear karena
     * penelusurannya lurus satu arah tanpa melompat; syaratnya pun ringan,
     * data TIDAK perlu dalam keadaan terurut.
     *
     * Kompleksitas waktu : O(1) best case  -> ketemu di elemen pertama
     *                      O(n) average & worst case -> ketemu di akhir
     *                                                   atau tidak ada
     * Kompleksitas ruang : O(1)
     * ================================================================= */
    public void cariLagu() {
        System.out.println();
        System.out.println("--- CARI LAGU BERDASARKAN JUDUL ---");

        if (jumlahLagu == 0) {
            System.out.println("  [!] Playlist masih kosong, tidak ada yang bisa dicari.");
            return;
        }

        System.out.print("Masukkan judul lagu yang dicari: ");
        String judul = input.nextLine().trim();

        int perbandingan = 0; // penghitung langkah, bukti nyata perilaku O(n)
        int posisi = -1;

        // INTI LINEAR SEARCH: periksa elemen satu per satu dari kiri ke kanan
        for (int i = 0; i < jumlahLagu; i++) {
            perbandingan++;
            if (playlist[i].getJudul().equalsIgnoreCase(judul)) {
                posisi = i;
                break; // berhenti begitu ketemu -> hemat langkah sisanya
            }
        }

        if (posisi == -1) {
            System.out.println("  [X] Lagu \"" + judul + "\" TIDAK ditemukan.");
            System.out.println("      Jumlah perbandingan: " + perbandingan
                    + " (seluruh data diperiksa -> worst case O(n)).");
        } else {
            System.out.println("  [OK] Lagu ditemukan pada posisi ke-" + (posisi + 1)
                    + " (indeks array ke-" + posisi + ").");
            playlist[posisi].tampilkanInfo();
            System.out.println("      Jumlah perbandingan: " + perbandingan + " langkah.");
        }
    }

    /* =================================================================
     * FITUR TAMBAHAN - SORTING (SELECTION SORT, ASCENDING)
     * void urutkanLaguBerdasarkanDurasi()
     *
     * Logika Selection Sort:
     *   Untuk setiap posisi i (0..n-2), telusuri sisa array di sebelah
     *   kanannya untuk mencari elemen dengan durasi TERKECIL, lalu tukar
     *   elemen terkecil itu dengan elemen di posisi i. Setelah putaran ke-i
     *   selesai, bagian array 0..i dijamin sudah terurut.
     *
     * Kenapa O(n^2)?
     *   Terdapat dua perulangan bersarang. Loop luar berjalan (n-1) kali dan
     *   loop dalam rata-rata berjalan n/2 kali, sehingga total perbandingan
     *   = (n-1) + (n-2) + ... + 1 = n(n-1)/2. Suku dominannya n^2/2, dan
     *   karena Big O mengabaikan konstanta hasilnya O(n^2). Jumlah langkah
     *   ini tidak bergantung pada kondisi awal data, jadi best, average, dan
     *   worst case sama-sama O(n^2).
     *
     * Kompleksitas ruang : O(1) -> pengurutan in-place, hanya butuh satu
     *                              variabel sementara untuk menukar
     * ================================================================= */
    public void urutkanLaguBerdasarkanDurasi() {
        System.out.println();
        System.out.println("--- URUTKAN LAGU BERDASARKAN DURASI (ASCENDING) ---");

        if (jumlahLagu < 2) {
            System.out.println("  [!] Butuh minimal 2 lagu untuk bisa diurutkan.");
            return;
        }

        // Tampilkan kondisi SEBELUM pengurutan
        System.out.println();
        System.out.println(">> SEBELUM PENGURUTAN:");
        cetakDaftarRingkas();

        int perbandingan = 0;
        int pertukaran = 0;

        // INTI SELECTION SORT
        for (int i = 0; i < jumlahLagu - 1; i++) {   // loop luar : posisi yang sedang diisi
            int indeksMin = i;                       // asumsi awal: elemen ke-i paling kecil

            for (int j = i + 1; j < jumlahLagu; j++) { // loop dalam : cari yang benar-benar terkecil
                perbandingan++;
                if (playlist[j].getDurasi() < playlist[indeksMin].getDurasi()) {
                    indeksMin = j;
                }
            }

            // Tukar hanya bila memang ditemukan elemen yang lebih kecil
            if (indeksMin != i) {
                Lagu sementara = playlist[i];
                playlist[i] = playlist[indeksMin];
                playlist[indeksMin] = sementara;
                pertukaran++;
            }
        }

        // Tampilkan kondisi SESUDAH pengurutan
        System.out.println();
        System.out.println(">> SESUDAH PENGURUTAN:");
        cetakDaftarRingkas();

        System.out.println();
        System.out.println("  Statistik Selection Sort untuk n = " + jumlahLagu + " lagu:");
        System.out.println("    - Jumlah perbandingan : " + perbandingan
                + "  (sesuai rumus n(n-1)/2 = " + (jumlahLagu * (jumlahLagu - 1) / 2) + ")");
        System.out.println("    - Jumlah pertukaran   : " + pertukaran);
        System.out.println("    - Kompleksitas waktu  : O(n^2) karena dua loop bersarang.");
    }

    /* -----------------------------------------------------------------
     * HELPER - cariIndeks()
     * Linear search versi internal yang MENGEMBALIKAN indeks (bukan
     * mencetak). Dipakai ulang oleh hapusLagu() sehingga logika pencarian
     * tidak perlu ditulis dua kali.
     * Mengembalikan -1 bila judul tidak ditemukan.  -> O(n)
     * ----------------------------------------------------------------- */
    private int cariIndeks(String judul) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judul)) {
                return i;
            }
        }
        return -1;
    }

    /* -----------------------------------------------------------------
     * HELPER - cetakDaftarRingkas()
     * Traversal sederhana tanpa header/footer, dipakai untuk menampilkan
     * perbandingan sebelum dan sesudah pengurutan.  -> O(n)
     * ----------------------------------------------------------------- */
    private void cetakDaftarRingkas() {
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanRingkas(i + 1);
        }
    }

    /* -----------------------------------------------------------------
     * HELPER - bacaDurasi()
     * Membaca durasi sebagai teks lalu mengonversinya sendiri, bukan lewat
     * nextDouble(). Tujuannya agar salah ketik (misal pengguna mengetik
     * huruf) tidak membuat program berhenti karena InputMismatchException.
     * Mengembalikan -1 sebagai penanda input tidak valid.
     * ----------------------------------------------------------------- */
    private double bacaDurasi() {
        String teks = input.nextLine().trim().replace(',', '.');
        try {
            return Double.parseDouble(teks);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /* -----------------------------------------------------------------
     * HELPER - isiDataAwal()
     * Mengisi playlist dengan beberapa lagu contoh memakai operasi
     * INSERTION yang sama, supaya saat program pertama kali dijalankan
     * menu Traversal, Searching, dan Sorting langsung ada datanya.
     * ----------------------------------------------------------------- */
    private void isiDataAwal() {
        playlist[jumlahLagu++] = new Lagu("Perfect", "Ed Sheeran", 4.23);
        playlist[jumlahLagu++] = new Lagu("Shivers", "Ed Sheeran", 3.50);
    }

    /* -----------------------------------------------------------------
     * HELPER - tampilkanMenu()
     * Mencetak daftar pilihan operasi array kepada pengguna.
     * ----------------------------------------------------------------- */
    private void tampilkanMenu() {
        System.out.println();
        System.out.println("=== MENU PLAYLIST MUSIK ===");
        System.out.println("1. Tampilkan semua lagu");
        System.out.println("2. Tambah lagu baru");
        System.out.println("3. Hapus lagu berdasarkan judul");
        System.out.println("4. Cari lagu berdasarkan judul");
        System.out.println("5. Urutkan berdasarkan durasi");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu: ");
    }

    /* -----------------------------------------------------------------
     * jalankan()
     * Loop utama program: tampilkan menu, baca pilihan, jalankan operasi
     * array yang sesuai, ulangi sampai pengguna memilih Keluar.
     * ----------------------------------------------------------------- */
    public void jalankan() {
        System.out.println("=====================================================");
        System.out.println("   SISTEM MANAJEMEN PLAYLIST MUSIK - BERBASIS ARRAY  ");
        System.out.println("   Tugas Kelompok 2 | Group 1 | Kelas LYCA - LEC     ");
        System.out.println("   Kapasitas array statis: " + MAKS_LAGU + " lagu             ");
        System.out.println("=====================================================");

        isiDataAwal();

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();

            // Pilihan dibaca sebagai teks agar input salah tidak membuat crash
            String pilihan = input.hasNextLine() ? input.nextLine().trim() : "6";

            switch (pilihan) {
                case "1":
                    tampilkanSemuaLagu();          // TRAVERSAL
                    break;
                case "2":
                    tambahLagu();                  // INSERTION
                    break;
                case "3":
                    hapusLagu();                   // DELETION
                    break;
                case "4":
                    cariLagu();                    // SEARCHING
                    break;
                case "5":
                    urutkanLaguBerdasarkanDurasi();// SORTING (fitur tambahan)
                    break;
                case "6":
                    berjalan = false;
                    System.out.println();
                    System.out.println("Terima kasih telah menggunakan program ini. Sampai jumpa!");
                    break;
                default:
                    System.out.println("  [!] Pilihan tidak dikenali. Masukkan angka 1 sampai 6.");
                    break;
            }
        }

        input.close();
    }

    /* -----------------------------------------------------------------
     * MAIN
     * Titik masuk program: membuat satu objek PlaylistArray lalu
     * menjalankan loop menunya.
     * ----------------------------------------------------------------- */
    public static void main(String[] args) {
        new PlaylistArray().jalankan();
    }
}
