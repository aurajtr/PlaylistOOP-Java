/* =====================================================================
 * CLASS ADMIN  (CHILD CLASS dari User)
 * Peran: menambahkan lagu baru ke dalam sistem dan melihat daftar lagu.
 * ===================================================================== */
public class Admin extends User {

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
