/* =====================================================================
 * Data Structures and Algorithm Analysis
 * Tugas Kelompok ke-1 (Week 3) - Introduction to OOP & Data Structures
 * Nama File : PlaylistOOP.java
 * Topik     : Sistem Manajemen Playlist Musik Sederhana
 *
 * IDENTITAS KELOMPOK
 *   1. <Nama Anggota 1>  -  <NIM>
 *   2. <Nama Anggota 2>  -  <NIM>
 *   3. <Nama Anggota 3>  -  <NIM>
 *   4. <Nama Anggota 4>  -  <NIM>
 *
 * Konsep OOP yang diterapkan:
 *   - Enkapsulasi : seluruh atribut private + akses lewat getter/setter
 *   - Inheritance : User (parent) diturunkan menjadi Admin dan Member
 *   - Polymorphism: method overriding (getPeran, tampilkanHakAkses) dan
 *                   method overloading (tambahLagu) serta upcasting
 *                   melalui array bertipe User
 *   - Array       : Playlist menyimpan kumpulan objek Lagu di dalam array
 * ===================================================================== */

import java.util.Locale;

/* =====================================================================
 * CLASS LAGU
 * Merepresentasikan satu buah entitas lagu di dalam sistem.
 * Menerapkan ENKAPSULASI: semua atribut dibuat private sehingga tidak
 * dapat diakses langsung dari luar class, melainkan harus melalui
 * method getter (membaca) dan setter (mengubah).
 * ===================================================================== */
class Lagu {
    // Atribut private -> inti dari enkapsulasi (information hiding)
    private String judul;
    private String artis;
    private double durasi; // satuan: menit

    /*
     * Constructor.
     * Logika: menerima nilai awal untuk setiap atribut saat objek dibuat.
     * Pengisian durasi sengaja dilewatkan ke setDurasi() agar aturan
     * validasi hanya ditulis satu kali (tidak duplikat).
     */
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.setDurasi(durasi);
    }

    // ---------------------- GETTER ----------------------
    // Menyediakan akses BACA yang terkontrol ke atribut private.
    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public double getDurasi() {
        return durasi;
    }

    // ---------------------- SETTER ----------------------
    // Menyediakan akses UBAH yang terkontrol, lengkap dengan validasi.
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    /*
     * Setter durasi dengan validasi.
     * Logika: durasi sebuah lagu tidak mungkin bernilai negatif, sehingga
     * nilai yang tidak masuk akal ditolak dan diganti 0. Inilah keuntungan
     * enkapsulasi: data objek tidak bisa dirusak dari luar class.
     */
    public void setDurasi(double durasi) {
        if (durasi < 0) {
            System.out.println("[!] Durasi tidak valid untuk lagu \"" + judul + "\". Nilai diset ke 0.");
            this.durasi = 0;
        } else {
            this.durasi = durasi;
        }
    }

    /*
     * Method tampilkanInfo().
     * Logika: mencetak seluruh informasi lagu dalam format multi-baris
     * yang mudah dibaca. Dipakai saat pengguna meminta DETAIL satu lagu.
     */
    public void tampilkanInfo() {
        System.out.println("  Judul  : " + judul);
        System.out.println("  Artis  : " + artis);
        System.out.println("  Durasi : " + String.format(Locale.US, "%.2f", durasi) + " menit");
    }

    /*
     * Method tampilkanRingkas().
     * Logika: mencetak informasi lagu dalam SATU baris bernomor, dipakai
     * ketika menampilkan seluruh isi playlist agar output tetap rapi.
     */
    public void tampilkanRingkas(int nomor) {
        System.out.println(String.format(Locale.US, "  %-3d %-22s %-15s %5.2f menit",
                nomor, judul, artis, durasi));
    }
}

/* =====================================================================
 * CLASS PLAYLIST
 * Struktur data penyimpan koleksi lagu. Di sinilah ARRAY dipakai:
 * Lagu[] daftarLagu menampung kumpulan objek Lagu, dan atribut
 * jumlahLagu mencatat berapa banyak slot array yang sudah terisi.
 * Array berukuran tetap, sehingga kapasitas maksimum harus dijaga.
 * ===================================================================== */
class Playlist {
    private String namaPlaylist;
    private Lagu[] daftarLagu; // ARRAY of object -> struktur data utama
    private int jumlahLagu;    // penanda batas data valid di dalam array

    /*
     * Constructor.
     * Logika: menyiapkan array kosong sebesar kapasitas yang diminta.
     * jumlahLagu dimulai dari 0 karena belum ada lagu yang dimasukkan.
     */
    public Playlist(String namaPlaylist, int kapasitas) {
        this.namaPlaylist = namaPlaylist;
        this.daftarLagu = new Lagu[kapasitas];
        this.jumlahLagu = 0;
    }

    public String getNamaPlaylist() {
        return namaPlaylist;
    }

    public int getJumlahLagu() {
        return jumlahLagu;
    }

    public int getKapasitas() {
        return daftarLagu.length;
    }

    /*
     * Method tambahLagu().
     * Logika: menyisipkan objek Lagu pada slot kosong pertama, yaitu
     * indeks ke-jumlahLagu, lalu menaikkan pencacah. Sebelum menyisipkan,
     * dilakukan dua pemeriksaan: array sudah penuh atau objek bernilai null.
     * Kompleksitas waktu: O(1) karena posisi penyisipan langsung diketahui.
     */
    public boolean tambahLagu(Lagu lagu) {
        if (lagu == null) {
            System.out.println("[!] Gagal: data lagu kosong.");
            return false;
        }
        if (jumlahLagu >= daftarLagu.length) {
            System.out.println("[!] Gagal: playlist penuh (kapasitas " + daftarLagu.length + " lagu).");
            return false;
        }
        daftarLagu[jumlahLagu] = lagu;
        jumlahLagu++;
        return true;
    }

    /*
     * Method getLagu().
     * Logika: mengambil objek Lagu pada indeks tertentu dengan pengecekan
     * batas agar tidak terjadi ArrayIndexOutOfBoundsException.
     * Kompleksitas waktu: O(1) - keunggulan utama struktur data array.
     */
    public Lagu getLagu(int indeks) {
        if (indeks < 0 || indeks >= jumlahLagu) {
            return null;
        }
        return daftarLagu[indeks];
    }
}

/* =====================================================================
 * CLASS USER  (PARENT CLASS / SUPERCLASS)
 * Berisi atribut dan perilaku yang dimiliki SEMUA jenis pengguna.
 * Dibuat abstract karena "User" hanyalah konsep umum; yang benar-benar
 * dipakai adalah turunannya (Admin dan Member).
 * ===================================================================== */
abstract class User {
    // protected -> dapat diwariskan dan diakses langsung oleh child class
    protected String nama;
    protected String id;
    protected Playlist playlist; // playlist yang sedang diakses pengguna

    /*
     * Constructor parent.
     * Logika: menyimpan identitas pengguna dan referensi playlist.
     * Constructor ini dipanggil oleh child class melalui super(...),
     * sehingga kode inisialisasi tidak perlu ditulis ulang.
     */
    public User(String nama, String id, Playlist playlist) {
        this.nama = nama;
        this.id = id;
        this.playlist = playlist;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    /*
     * Method ABSTRACT -> kontrak yang WAJIB diimplementasikan child class.
     * Inilah dasar POLYMORPHISM: pemanggil cukup tahu objeknya bertipe User,
     * sedangkan isi method ditentukan oleh class turunannya saat runtime.
     */
    public abstract String getPeran();

    public abstract void tampilkanHakAkses();

    /*
     * Method tampilkanIdentitas().
     * Logika: dipakai bersama oleh Admin dan Member (INHERITANCE), tetapi
     * hasilnya berbeda karena memanggil getPeran() yang sudah di-override
     * masing-masing turunan (dynamic method dispatch).
     */
    public void tampilkanIdentitas() {
        System.out.println("Pengguna aktif : " + nama + " (" + id + ") - peran " + getPeran());
    }

    /*
     * Method tampilkanDaftarLagu().
     * Logika: melakukan traversal array playlist dari indeks 0 sampai
     * jumlahLagu-1 dan mencetak setiap lagu dalam bentuk tabel.
     * Method ini diletakkan di parent karena Admin maupun Member
     * sama-sama berhak melihat daftar lagu.
     * Kompleksitas waktu: O(n).
     */
    public void tampilkanDaftarLagu() {
        System.out.println("Daftar lagu pada playlist \"" + playlist.getNamaPlaylist() + "\" "
                + "(" + playlist.getJumlahLagu() + "/" + playlist.getKapasitas() + " lagu)");

        if (playlist.getJumlahLagu() == 0) {
            System.out.println("  (playlist masih kosong)");
            return;
        }

        System.out.println(String.format("  %-3s %-22s %-15s %s", "No", "Judul", "Artis", "Durasi"));
        System.out.println("  ---------------------------------------------------------");
        for (int i = 0; i < playlist.getJumlahLagu(); i++) {
            playlist.getLagu(i).tampilkanRingkas(i + 1);
        }
    }
}

/* =====================================================================
 * CLASS ADMIN  (CHILD CLASS dari User)
 * Peran: menambahkan lagu baru ke dalam sistem dan melihat daftar lagu.
 * ===================================================================== */
class Admin extends User {

    /*
     * Constructor Admin.
     * Logika: seluruh atribut identitas diserahkan ke constructor parent
     * lewat super(...) -> contoh nyata penggunaan inheritance.
     */
    public Admin(String nama, String id, Playlist playlist) {
        super(nama, id, playlist);
    }

    /*
     * OVERRIDING method abstract milik User.
     * Logika: mengembalikan nama peran khusus untuk Admin.
     */
    @Override
    public String getPeran() {
        return "Admin Playlist";
    }

    /*
     * OVERRIDING method abstract milik User.
     * Logika: mencetak hak akses yang dimiliki Admin. Method dengan nama
     * sama juga ada di Member, tetapi isinya berbeda -> POLYMORPHISM.
     */
    @Override
    public void tampilkanHakAkses() {
        System.out.println("  Hak akses: menambah lagu baru, melihat seluruh daftar lagu.");
    }

    /*
     * Method tambahLagu() versi ke-1 (OVERLOADING).
     * Logika: menerima objek Lagu yang sudah jadi, meneruskannya ke
     * Playlist, lalu mencetak status berhasil/gagal penambahan.
     */
    public void tambahLagu(Lagu lagu) {
        boolean berhasil = playlist.tambahLagu(lagu);
        if (berhasil) {
            System.out.println("  [OK] " + nama + " menambahkan lagu \"" + lagu.getJudul()
                    + "\" - " + lagu.getArtis());
        }
    }

    /*
     * Method tambahLagu() versi ke-2 (OVERLOADING).
     * Logika: menerima data mentah lagu, membungkusnya menjadi objek Lagu,
     * lalu memanggil versi pertama. Nama method sama, parameter berbeda,
     * sehingga Java memilih versi yang tepat saat kompilasi.
     */
    public void tambahLagu(String judul, String artis, double durasi) {
        this.tambahLagu(new Lagu(judul, artis, durasi));
    }
}

/* =====================================================================
 * CLASS MEMBER  (CHILD CLASS dari User)
 * Peran: menelusuri lagu, melihat detail satu lagu, dan menghitung
 * rata-rata durasi lagu pada playlist.
 * ===================================================================== */
class Member extends User {

    /*
     * Constructor Member.
     * Logika: memanggil constructor parent untuk mengisi identitas.
     */
    public Member(String nama, String id, Playlist playlist) {
        super(nama, id, playlist);
    }

    /*
     * OVERRIDING: peran yang dikembalikan berbeda dengan Admin.
     */
    @Override
    public String getPeran() {
        return "Pengguna Playlist";
    }

    /*
     * OVERRIDING: hak akses Member lebih terbatas dibanding Admin.
     */
    @Override
    public void tampilkanHakAkses() {
        System.out.println("  Hak akses: melihat daftar lagu, mencari lagu, menghitung rata-rata durasi.");
    }

    /*
     * Method cariLagu().
     * Logika: SEQUENTIAL / LINEAR SEARCH. Array ditelusuri satu per satu
     * dari indeks 0; judul dibandingkan tanpa membedakan huruf besar-kecil
     * memakai equalsIgnoreCase(). Begitu ketemu, objek langsung dikembalikan
     * (early return). Bila sampai akhir tidak ada yang cocok, hasilnya null.
     * Kompleksitas waktu: O(n) pada kasus terburuk.
     */
    public Lagu cariLagu(String judul) {
        for (int i = 0; i < playlist.getJumlahLagu(); i++) {
            Lagu lagu = playlist.getLagu(i);
            if (lagu.getJudul().equalsIgnoreCase(judul)) {
                return lagu;
            }
        }
        return null;
    }

    /*
     * Method tampilkanDetailLagu().
     * Logika: memanfaatkan cariLagu() untuk mendapatkan objek yang dicari.
     * Jika ditemukan, detailnya dicetak lewat tampilkanInfo(); jika tidak,
     * pengguna diberi pesan bahwa lagu tidak ada di dalam playlist.
     */
    public void tampilkanDetailLagu(String judul) {
        System.out.println("Pencarian lagu berjudul \"" + judul + "\" oleh " + nama + ":");
        Lagu hasil = cariLagu(judul);
        if (hasil != null) {
            System.out.println("  [DITEMUKAN]");
            hasil.tampilkanInfo();
        } else {
            System.out.println("  [TIDAK DITEMUKAN] Lagu tersebut tidak ada di dalam playlist.");
        }
    }

    /*
     * Method hitungRataRataDurasi().
     * Logika: menjumlahkan durasi seluruh lagu melalui traversal array,
     * lalu membaginya dengan jumlah lagu. Pembagian dengan nol dicegah
     * dengan mengembalikan 0 saat playlist masih kosong.
     * Kompleksitas waktu: O(n).
     */
    public double hitungRataRataDurasi() {
        int jumlah = playlist.getJumlahLagu();
        if (jumlah == 0) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < jumlah; i++) {
            total += playlist.getLagu(i).getDurasi();
        }
        return total / jumlah;
    }
}

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
