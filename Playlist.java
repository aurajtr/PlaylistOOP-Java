/* =====================================================================
 * CLASS PLAYLIST
 * Struktur data penyimpan koleksi lagu. Di sinilah ARRAY dipakai:
 * Lagu[] daftarLagu menampung kumpulan objek Lagu, dan atribut
 * jumlahLagu mencatat berapa banyak slot array yang sudah terisi.
 * Array berukuran tetap, sehingga kapasitas maksimum harus dijaga.
 * ===================================================================== */
public class Playlist {
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
