import java.util.Locale;

/* =====================================================================
 * CLASS UTAMA
 * Berisi method main() sebagai titik awal eksekusi program sekaligus
 * skenario demo yang menguji seluruh fitur sistem playlist.
 * ===================================================================== */
public class PlaylistOOP {

    /*
     * Method bantu untuk mencetak judul setiap bagian demo
     * agar output program mudah dibaca pada screenshot.
     */
    private static void judulBagian(String teks) {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println(" " + teks);
        System.out.println("==============================================================");
    }

    /*
     * Method main().
     * Logika program secara berurutan:
     *   1. Membuat objek Playlist beserta array penyimpan lagunya.
     *   2. Membuat objek Admin dan Member yang mengakses playlist sama.
     *   3. Admin mengisi playlist memakai dua versi method tambahLagu().
     *   4. Admin dan Member menampilkan daftar lagu (method warisan).
     *   5. Member mencari lagu (ketemu dan tidak ketemu) serta menghitung
     *      rata-rata durasi.
     *   6. Demonstrasi polymorphism lewat array bertipe User.
     */
    public static void main(String[] args) {

        System.out.println("SISTEM MANAJEMEN PLAYLIST MUSIK SEDERHANA");
        System.out.println("Tugas Kelompok 1 - Data Structures and Algorithm Analysis");

        // ---------- 1. Menyiapkan struktur data playlist ----------
        // Kapasitas array ditetapkan 8 slot; saat ini akan diisi 5 lagu.
        Playlist playlist = new Playlist("Lagu Favorit Kelompok", 8);

        // ---------- 2. Membuat objek pengguna ----------
        // Keduanya menerima referensi playlist yang sama, sehingga
        // perubahan oleh Admin langsung terlihat oleh Member.
        Admin admin = new Admin("Andi Wijaya", "ADM-001", playlist);
        Member member = new Member("Bunga Lestari", "MBR-001", playlist);

        // ---------- 3. Admin menambahkan lagu ----------
        judulBagian("BAGIAN 1 - ADMIN MENAMBAHKAN LAGU");
        admin.tampilkanIdentitas();
        admin.tampilkanHakAkses();
        System.out.println();

        // Cara pertama: membuat objek Lagu dulu, lalu dikirim ke Admin.
        Lagu lagu1 = new Lagu("Bohemian Rhapsody", "Queen", 5.55);
        Lagu lagu2 = new Lagu("Shape of You", "Ed Sheeran", 3.53);
        Lagu lagu3 = new Lagu("Yellow", "Coldplay", 4.29);
        admin.tambahLagu(lagu1);
        admin.tambahLagu(lagu2);
        admin.tambahLagu(lagu3);

        // Cara kedua: memakai method tambahLagu() hasil OVERLOADING,
        // objek Lagu dibentuk otomatis di dalam method tersebut.
        admin.tambahLagu("Laskar Pelangi", "Nidji", 4.42);
        admin.tambahLagu("Hati-Hati di Jalan", "Tulus", 5.02);

        // ---------- 4. Menampilkan isi playlist ----------
        judulBagian("BAGIAN 2 - DAFTAR LAGU DALAM PLAYLIST");
        admin.tampilkanDaftarLagu(); // method milik parent class User

        // ---------- 5. Aktivitas Member ----------
        judulBagian("BAGIAN 3 - MEMBER MENELUSURI PLAYLIST");
        member.tampilkanIdentitas();
        member.tampilkanHakAkses();
        System.out.println();

        // Kasus 1: judul lagu tersedia di dalam playlist.
        member.tampilkanDetailLagu("Yellow");
        System.out.println();

        // Kasus 2: judul lagu tidak tersedia -> menguji jalur "tidak ditemukan".
        member.tampilkanDetailLagu("Blinding Lights");
        System.out.println();

        // Menghitung rata-rata durasi seluruh lagu pada playlist.
        double rataRata = member.hitungRataRataDurasi();
        System.out.println("Rata-rata durasi " + playlist.getJumlahLagu() + " lagu: "
                + String.format(Locale.US, "%.2f", rataRata) + " menit");

        // ---------- 6. Demonstrasi polymorphism ----------
        judulBagian("BAGIAN 4 - DEMONSTRASI POLYMORPHISM");

        // UPCASTING: objek Admin dan Member disimpan dalam satu array
        // bertipe parent (User). Java menentukan versi method yang dipanggil
        // berdasarkan tipe objek sebenarnya saat program berjalan.
        User[] daftarPengguna = new User[2];
        daftarPengguna[0] = admin;
        daftarPengguna[1] = member;

        for (int i = 0; i < daftarPengguna.length; i++) {
            // Baris kode yang sama, hasil berbeda -> inilah polymorphism.
            daftarPengguna[i].tampilkanIdentitas();
            daftarPengguna[i].tampilkanHakAkses();
            System.out.println();
        }

        System.out.println("Program selesai.");
    }
}
