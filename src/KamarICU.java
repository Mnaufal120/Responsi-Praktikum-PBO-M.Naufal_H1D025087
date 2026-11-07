public class KamarICU extends FasilitasKamar implements Sterilisasi, FasilitasPrioritas {
    private int jumlahVentilator;


    public KamarICU(String kodeKamar, double hargaPerMalam, int jumlahVentilator) {
        super(kodeKamar, 1, hargaPerMalam);
        this.jumlahVentilator = jumlahVentilator;
    }


    @Override
    public void prosesAlokasi(String namaPasien) throws KamarPenuh {
        if (isStatusTersedia()) {
            setStatusTersedia(false);
            setNamaPasienSaatIni(namaPasien);
            System.out.println("\n[PENDAFTARAN BERHASIL] Pasien " + namaPasien + " resmi masuk ke Kamar ICU " + getKodeKamar() + ".");
        } else {

            throw new KamarPenuh("Kamar ICU " + getKodeKamar() + " saat ini sedang terisi oleh pasien " + getNamaPasienSaatIni() + ". Silakan pilih kamar lain.");
        }
    }


    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Kategori Kamar : ICU (Perawatan Intensif)");
        System.out.println("Fasilitas ICU  : " + this.jumlahVentilator + " Unit Ventilator medis terpasang");
    }

    @Override
    public void lakukanSterilisasi() {
        System.out.println("\n[PROSES STERILISASI] Ruangan ICU " + getKodeKamar() + " sedang disterilkan menggunakan sinar UV-C dan disinfektan.");
    }

    @Override
    public void aktifkanPanggilanDarurat() {
        System.out.println("\n[TANDA DARURAT] Sinyal darurat Kamar ICU " + getKodeKamar() + " berbunyi! Tim dokter jaga segera menuju lokasi.");
    }
}