/* =====================================================================
 * CLASS USER  (PARENT CLASS / SUPERCLASS)
 * Berisi atribut dan perilaku yang dimiliki SEMUA jenis pengguna.
 * Dibuat abstract karena "User" hanyalah konsep umum; yang benar-benar
 * dipakai adalah turunannya (Admin dan Member).
 * ===================================================================== */
public abstract class User {
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
