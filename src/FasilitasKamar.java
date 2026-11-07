public abstract class FasilitasKamar {

    public static final String NAMA_RUMAH_SAKIT = "RS Margono Soekarjo";
    private final String kodeKamar;


    private int kapasitas;
    private boolean statusTersedia;
    private double hargaPerMalam;
    private String namaPasienSaatIni;


    public FasilitasKamar(String kodeKamar, int kapasitas, double hargaPerMalam) {
        this.kodeKamar = kodeKamar;
        this.kapasitas = kapasitas;
        this.hargaPerMalam = hargaPerMalam;
        this.statusTersedia = true;
        this.namaPasienSaatIni = "-";
    }


    public FasilitasKamar(String kodeKamar, int kapasitas) {
        this(kodeKamar, kapasitas, 500000.0);
    }


    public abstract void prosesAlokasi(String namaPasien) throws KamarPenuh;


    public double hitungBiayaInap(int jumlahHari) {
        return this.hargaPerMalam * jumlahHari;
    }


    public void prosesCheckOut() {
        if (!this.statusTersedia) {
            System.out.println("\n[SISTEM] Pasien " + this.namaPasienSaatIni + " telah selesai rawat inap.");
            System.out.println("[SISTEM] Kamar " + this.kodeKamar + " berhasil dikosongkan dan siap dibersihkan.");
            this.statusTersedia = true;
            this.namaPasienSaatIni = "-";
        } else {
            System.out.println("\n[PERINTAH DITOLAK] Kamar " + this.kodeKamar + " saat ini sedang kosong, tidak ada pasien yang check-out.");
        }
    }


    public void tampilkanInformasi() {
        System.out.println("--------------------------------------------------");
        System.out.println("Rumah Sakit    : " + NAMA_RUMAH_SAKIT);
        System.out.println("Kode Kamar     : " + this.kodeKamar);
        System.out.println("Kapasitas Bed  : " + this.kapasitas + " Tempat Tidur");
        System.out.println("Tarif / Malam  : Rp " + String.format("%,.0f", this.hargaPerMalam));
        System.out.println("Status Kamar   : " + (this.statusTersedia ? "Tersedia (KOSONG)" : "TERISI oleh Pasien " + this.namaPasienSaatIni));
    }


    public String getKodeKamar() { return kodeKamar; }
    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    public boolean isStatusTersedia() { return statusTersedia; }
    public void setStatusTersedia(boolean statusTersedia) { this.statusTersedia = statusTersedia; }
    public double getHargaPerMalam() { return hargaPerMalam; }
    public void setHargaPerMalam(double hargaPerMalam) { this.hargaPerMalam = hargaPerMalam; }
    public String getNamaPasienSaatIni() { return namaPasienSaatIni; }
    public void setNamaPasienSaatIni(String namaPasienSaatIni) { this.namaPasienSaatIni = namaPasienSaatIni; }
}