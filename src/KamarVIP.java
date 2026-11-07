public class KamarVIP extends FasilitasKamar {
    private String fasilitasExtra;
    private static final double BIAYA_LAYANAN_VIP = 200000.0;

    public KamarVIP(String kodeKamar, double hargaPerMalam, String fasilitasExtra) {
        super(kodeKamar, 2, hargaPerMalam);
        this.fasilitasExtra = fasilitasExtra;
    }


    @Override
    public void prosesAlokasi(String namaPasien) throws KamarPenuh {
        if (isStatusTersedia()) {
            setStatusTersedia(false);
            setNamaPasienSaatIni(namaPasien);
            System.out.println("\n[PENDAFTARAN BERHASIL] Pasien " + namaPasien + " resmi menempati Kamar VIP " + getKodeKamar() + ".");
        } else {
            throw new KamarPenuh("Kamar VIP " + getKodeKamar() + " saat ini sedang penuh digunakan oleh pasien " + getNamaPasienSaatIni() + ".");
        }
    }


    @Override
    public double hitungBiayaInap(int jumlahHari) {
        return (super.getHargaPerMalam() + BIAYA_LAYANAN_VIP) * jumlahHari;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Kategori Kamar : VIP (Pelayanan Khusus)");
        System.out.println("Fasilitas Extra: " + this.fasilitasExtra);
        System.out.println("Layanan Ekstra : Kebersihan harian & menu makanan bebas pilih (+Rp " + String.format("%,.0f", BIAYA_LAYANAN_VIP) + "/malam)");
    }
}