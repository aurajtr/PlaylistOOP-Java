/* =====================================================================
 * CLASS MEMBER  (CHILD CLASS dari User)
 * Peran: menelusuri lagu, melihat detail satu lagu, dan menghitung
 * rata-rata durasi lagu pada playlist.
 * ===================================================================== */
public class Member extends User {

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
