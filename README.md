# Responsi Akhir PBO - Sistem Manajemen Kamar Rumah Sakit

* **Nama** : Muhammad Naufal
* **NIM** : H1D025087
* **Tema** : Rumah Sakit

---

## Membuka di IntelliJ IDEA

1. Buka folder proyek ini di IntelliJ IDEA.
2. Pastikan Project SDK menunjuk ke JDK yang terpasang (JDK 11 atau lebih baru).
3. Tandai folder `src` sebagai **Sources Root** bila IntelliJ belum mengenalnya.
4. Buka `src/Main.java`, lalu klik ikon Run.

---

## Compile dan Run dari Terminal

Dari folder utama proyek:

```bash
cd src
javac *.java
java Main

```

Cara Menggunakan

1. Pilih 1 untuk melihat status ketersediaan seluruh kamar (ICU dan VIP).

2. Pilih 2 untuk melakukan pendaftaran rawat inap pasien (Check-In). Sistem akan menolak jika kamar yang dipilih sedang terisi.

3. Pilih 3 untuk memproses pasien yang selesai rawat inap (Check-Out) sehingga kamar kembali kosong.

4. Pilih 4 untuk menghitung estimasi total biaya rawat inap berdasarkan jumlah hari.

5. Pilih 5 untuk menjalankan fitur layanan khusus ICU (Sterilisasi UV-C & Panggilan Darurat via Polimorfisme).

6. Pilih 6 untuk keluar dari program.


## Konsep PBO yang Ditunjukkan

| Ketentuan | Implementasi |
| --- | --- |
| State dan behavior | Atribut `statusTersedia`, `hargaPerMalam`, method `prosesCheckOut()`, `tampilkanInformasi()`, dan method getter/setter. |
| Immutability | Konstanta `NAMA_RUMAH_SAKIT` (`public static final`) dan kode kamar `kodeKamar` (`private final`). |
| Enkapsulasi dan overloading | Atribut `private` dengan getter/setter; overloaded constructor pada class `FasilitasKamar`. |
| Inheritance | Class `KamarICU` dan `KamarVIP` melakukan `extends FasilitasKamar`; class `Main` dipisahkan. |
| Super chaining | Subclass memanggil `super(...)` pada konstruktor dan `super.tampilkanInformasi()`. |
| Overriding | Method `prosesAlokasi`, `tampilkanInformasi`, dan `hitungBiayaInap` ditimpa dengan `@Override` di subclass. |
| Abstract class | Class `FasilitasKamar` bertipe `abstract` dengan abstract method `prosesAlokasi`. |
| Percabangan yang mengubah state | Method `prosesAlokasi` dan `prosesCheckOut` menggunakan logika `if/else` untuk mengubah `statusTersedia`. |
| Polimorfisme interface | Pemanggilan method melalui variabel interface: `Sterilisasi s = kamarICU;` dan `FasilitasPrioritas p = kamarICU;`. |
| Multiple interface | Subclass `KamarICU` mengimplementasikan `implements Sterilisasi, FasilitasPrioritas`. |



## Luaran Program (Dokumentasi Output)

### 1. Tampilan Menu Utama
![Tampilan Menu Utama](Dokumentasi/1.%20MENU.png)
*Menampilkan menu navigasi interaktif sistem manajemen kamar rumah sakit.*

### 2. Lihat Status Kamar
![Lihat Status Kamar](Dokumentasi/2.%20Lihat%20Status%20Kamar.png)
*Menampilkan rincian status ketersediaan seluruh fasilitas kamar (ICU & VIP).*

### 3. Pendaftaran Pasien Inap (Check-In)
![Pendaftaran Pasien Inap](Dokumentasi/3.%20Tambah%20Pasien%20Inap.png)
*Proses mengalokasikan pasien ke dalam kamar dan penanganan peringatan jika kamar penuh.*

### 4. Pasien Selesai Rawat Inap (Check-Out)
![Pasien Check Out](Dokumentasi/4.%20Pasien%20Check%20Out.png)
*Proses pengosongan kamar ketika pasien telah selesai menjalani masa perawatan.*

### 5. Hitung Estimasi Biaya Inap
![Estimasi Biaya Inap](Dokumentasi/5.%20Estimasi%20Biaya%20per%20hari.png)
*Kalkulasi otomatis total biaya rawat inap berdasarkan lama durasi menginap.*

### 6. Fitur Layanan Sterilisasi & Darurat ICU
![Steril Ruangan](Dokumentasi/6.%20Steril%20Ruangan.png)
*Eksekusi fitur sterilisasi UV-C dan sinyal darurat melalui polimorfisme interface.*