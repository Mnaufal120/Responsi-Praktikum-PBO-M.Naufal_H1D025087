import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<FasilitasKamar> daftarKamar = new ArrayList<>();
        daftarKamar.add(new KamarICU("ICU-01", 2500000.0, 2));
        daftarKamar.add(new KamarVIP("VIP-101", 1200000.0, "TV 50 Inch, Sofa Bed, Kulkas Minibar"));
        daftarKamar.add(new KamarVIP("VIP-102", 1500000.0, "Smart TV, Balkon Taman, Jacuzzi"));

        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n==============================================");
            System.out.println("   SISTEM MANAJEMEN KAMAR RS MARGONO SOEKARJO ");
            System.out.println("==============================================");
            System.out.println("1. Lihat Status Seluruh Kamar");
            System.out.println("2. Pendaftaran Pasien Inap (Check-In)");
            System.out.println("3. Pasien Pulang / Selesai Inap (Check-Out)");
            System.out.println("4. Hitung Estimasi Biaya Rawat Inap");
            System.out.println("5. Jalankan Fitur Layanan Khusus ICU (Polimorfisme)");
            System.out.println("6. Keluar Program");
            System.out.print("Silakan pilih menu (1-6): ");

            int pilihan;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR] Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.println("\n=== DAFTAR STATUS KAMAR RUMAH SAKIT ===");
                    for (FasilitasKamar kamar : daftarKamar) {
                        kamar.tampilkanInformasi();
                    }
                    break;

                case 2:
                    System.out.println("\n--- PILIH KAMAR UNTUK CHECK-IN ---");
                    for (int i = 0; i < daftarKamar.size(); i++) {
                        System.out.println((i + 1) + ". Kamar " + daftarKamar.get(i).getKodeKamar() +
                                " [" + (daftarKamar.get(i).isStatusTersedia() ? "Tersedia" : "Terisi") + "]");
                    }
                    System.out.print("Pilih nomor kamar (1-" + daftarKamar.size() + "): ");
                    int idxCheckIn = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idxCheckIn >= 0 && idxCheckIn < daftarKamar.size()) {
                        System.out.print("Masukkan Nama Pasien: ");
                        String namaPasien = scanner.nextLine();

                        try {
                            daftarKamar.get(idxCheckIn).prosesAlokasi(namaPasien);
                        } catch (KamarPenuh e) {
                            System.out.println("\n[PERINGATAN SISTEM] " + e.getMessage());
                        }
                    } else {
                        System.out.println("\n[ERROR] Pilihan nomor kamar tidak valid.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- PILIH KAMAR UNTUK CHECK-OUT ---");
                    for (int i = 0; i < daftarKamar.size(); i++) {
                        System.out.println((i + 1) + ". Kamar " + daftarKamar.get(i).getKodeKamar() +
                                " (Pasien: " + daftarKamar.get(i).getNamaPasienSaatIni() + ")");
                    }
                    System.out.print("Pilih nomor kamar (1-" + daftarKamar.size() + "): ");
                    int idxCheckOut = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idxCheckOut >= 0 && idxCheckOut < daftarKamar.size()) {
                        daftarKamar.get(idxCheckOut).prosesCheckOut();
                    } else {
                        System.out.println("\n[ERROR] Pilihan nomor kamar tidak valid.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- HITUNG ESTIMASI BIAYA RAWAT INAP ---");
                    for (int i = 0; i < daftarKamar.size(); i++) {
                        System.out.println((i + 1) + ". Kamar " + daftarKamar.get(i).getKodeKamar());
                    }
                    System.out.print("Pilih nomor kamar (1-" + daftarKamar.size() + "): ");
                    int idxBiaya = scanner.nextInt() - 1;

                    System.out.print("Berapa lama inap (jumlah hari): ");
                    int lamaHari = scanner.nextInt();
                    scanner.nextLine();

                    if (idxBiaya >= 0 && idxBiaya < daftarKamar.size() && lamaHari > 0) {
                        FasilitasKamar kamarPilihan = daftarKamar.get(idxBiaya);
                        double totalBiaya = kamarPilihan.hitungBiayaInap(lamaHari);
                        System.out.println("\n--------------------------------------------------");
                        System.out.println("Estimasi Biaya Kamar " + kamarPilihan.getKodeKamar() + " selama " + lamaHari + " hari:");
                        System.out.println("Total Biaya: Rp " + String.format("%,.0f", totalBiaya));
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("\n[ERROR] Data pilihan atau jumlah hari tidak valid.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- MENJALANKAN FITUR KHUSUS ICU (POLIMORFISME) ---");
                    KamarICU kamarICU = null;
                    for (FasilitasKamar k : daftarKamar) {
                        if (k instanceof KamarICU) {
                            kamarICU = (KamarICU) k;
                            break;
                        }
                    }

                    if (kamarICU != null) {

                        Sterilisasi layananSteril = kamarICU;
                        FasilitasPrioritas layananDarurat = kamarICU;

                        layananSteril.lakukanSterilisasi();
                        layananDarurat.aktifkanPanggilanDarurat();
                    }
                    break;

                case 6:
                    berjalan = false;
                    System.out.println("\nTerima kasih! Program Sistem Kamar Rumah Sakit ditutup.");
                    break;

                default:
                    System.out.println("\n[ERROR] Pilihan menu tidak tersedia.");
            }
        }

        scanner.close();
    }
}