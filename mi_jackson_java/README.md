Untuk membaca bank soal dari file .json, kita akan menggunakan pustaka Jackson, salah satu pustaka pemroses JSON yang paling standar dan populer di ekosistem Java/Maven.

Berikut adalah langkah-langkah implementasi lengkap beserta struktur file dan kodenya.

1. Tambahkan Dependency di `pom.xml`
Pastikan proyek Maven Anda memiliki pustaka Jackson. Tambahkan baris berikut di dalam tag `<dependencies>` pada file `pom.xml` Anda:
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>

```

2. Buat File JSON (`banksoal.json`)
Buat sebuah file baru bernama `banksoal.json` dan letakkan di dalam folder `src/main/resources/banksoal.json`. Masukkan data variasi objek ilustrasi berikut:
```
[
  { "namaBenda": "Donat", "namaWadah": "Kotak", "jumlahWadah": 3, "isiPerWadah": 4 },
  { "namaBenda": "Kaki Kucing", "namaWadah": "Ekor Kucing", "jumlahWadah": 5, "isiPerWadah": 4 },
  { "namaBenda": "Tablet Obat", "namaWadah": "Hari (Diminum)", "jumlahWadah": 3, "isiPerWadah": 1 },
  { "namaBenda": "Kelereng", "namaWadah": "Kantong plastik", "jumlahWadah": 4, "isiPerWadah": 5 },
  { "namaBenda": "Roda", "namaWadah": "Mobil", "jumlahWadah": 2, "isiPerWadah": 4 },
  { "namaBenda": "Butir Telur", "namaWadah": "Sarang Burung", "jumlahWadah": 3, "isiPerWadah": 3 },
  { "namaBenda": "Buku Pelajaran", "namaWadah": "Meja Belajar", "jumlahWadah": 4, "isiPerWadah": 2 },
  { "namaBenda": "Lilin", "namaWadah": "Kue Ulang Tahun", "jumlahWadah": 2, "isiPerWadah": 6 },
  { "namaBenda": "Ikan Mas", "namaWadah": "Akuarium", "jumlahWadah": 3, "isiPerWadah": 5 },
  { "namaBenda": "Pensil Warna", "namaWadah": "Kotak Pensil", "jumlahWadah": 5, "isiPerWadah": 6 },
  { "namaBenda": "Keping Biskuit", "namaWadah": "Stoples", "jumlahWadah": 2, "isiPerWadah": 8 },
  { "namaBenda": "Sepatu", "namaWadah": "Rak Sepatu", "jumlahWadah": 4, "isiPerWadah": 4 },
  { "namaBenda": "Lembar Pakaian", "namaWadah": "Jemuran", "jumlahWadah": 6, "isiPerWadah": 2 },
  { "namaBenda": "Potong Semangka", "namaWadah": "Piring", "jumlahWadah": 3, "isiPerWadah": 6 },
  { "namaBenda": "Sayap", "namaWadah": "Ekor Burung Elang", "jumlahWadah": 4, "isiPerWadah": 2 }
]

```

3. Kode Program Java yang Diperbarui (`App.java`)
Kode di bawah ini menggunakan `ObjectMapper` dari Jackson untuk membaca file JSON secara otomatis dan memasukkannya ke dalam objek Java. Kita juga menambahkan *default constructor* dan metode `hitungTotal()` agar Jackson bisa mengisi data dan menghitung perkaliannya dengan benar.
```
package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    static class SoalPerkalian {
        public String namaBenda;
        public String namaWadah;
        public int jumlahWadah;
        public int isiPerWadah;
        public int totalBenar;

        // Constructor kosong wajib ada agar Jackson bisa membaca JSON
        public SoalPerkalian() {}

        public SoalPerkalian(String namaBenda, String namaWadah, int jumlahWadah, int isiPerWadah) {
            this.namaBenda = namaBenda;
            this.namaWadah = namaWadah;
            this.jumlahWadah = jumlahWadah;
            this.isiPerWadah = isiPerWadah;
            hitungTotal();
        }

        // Metode untuk menghitung total setelah data JSON selesai dimuat
        public void hitungTotal() {
            this.totalBenar = this.jumlahWadah * this.isiPerWadah;
        }

        public String getTeksCerita() {
            return "Anda melihat ada " + jumlahWadah + " " + namaWadah + ". \n" +
                   "Setiap " + namaWadah + " tersebut berisi " + isiPerWadah + " " + namaBenda + ".";
        }

        public String getVisualisasiPenjumlahan() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jumlahWadah; i++) {
                sb.append(isiPerWadah);
                if (i < jumlahWadah - 1) sb.append(" + ");
            }
            return sb.toString() + " = " + totalBenar;
        }
    }

    public static void main(String[] args) {
        List<SoalPerkalian> bankSoal = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        // Membaca file JSON dari folder resources
        try (InputStream inputStream = App.class.getResourceAsStream("/banksoal.json")) {
            if (inputStream == null) {
                System.out.println("❌ Error: File banksoal.json tidak ditemukan di folder resources!");
                return;
            }
            
            // Konversi JSON menjadi List Java
            bankSoal = mapper.readValue(inputStream, new TypeReference<List<SoalPerkalian>>() {});
            
            // Hitung kalkulasi total perkalian untuk setiap soal yang diimpor
            for (SoalPerkalian soal : bankSoal) {
                soal.hitungTotal();
            }
            
        } catch (Exception e) {
            System.out.println("❌ Gagal membaca file JSON: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Mengacak urutan bank soal agar variasi cerita selalu acak
        Collections.shuffle(bankSoal);

        Scanner scanner = new Scanner(System.in);
        int skor = 0;
        int poinPerSoal = 20;
        int totalPertanyaan = 5; 

        System.out.println("=== GAME ILUSTRASI PERKALIAN NYATA (IMPOR JSON) ===");
        System.out.println("Hitunglah total benda berdasarkan situasi nyata berikut!\n");

        for (int i = 0; i < totalPertanyaan && i < bankSoal.size(); i++) {
            SoalPerkalian soal = bankSoal.get(i);
            
            System.out.println("--------------------------------------------------");
            System.out.println("Pertanyaan " + (i + 1) + ":");
            System.out.println(soal.getTeksCerita());
            System.out.println("Berapakah total " + soal.namaBenda + " seluruhnya?");
            System.out.println("--------------------------------------------------");

            List<Integer> pilihanJawaban = new ArrayList<>();
            pilihanJawaban.add(soal.totalBenar);
            pilihanJawaban.add(soal.totalBenar + 2);
            pilihanJawaban.add(soal.totalBenar - 1 < 0 ? soal.totalBenar + 5 : soal.totalBenar - 1);
            pilihanJawaban.add(soal.totalBenar * 2 - 1);
            
            pilihanJawaban = new ArrayList<>(new java.util.HashSet<>(pilihanJawaban));
            while(pilihanJawaban.size() < 4) {
                pilihanJawaban.add(soal.totalBenar + pilihanJawaban.size() + 3);
            }
            
            Collections.shuffle(pilihanJawaban);

            for (int j = 0; j < pilihanJawaban.size(); j++) {
                System.out.println((j + 1) + ". " + pilihanJawaban.get(j) + " " + soal.namaBenda);
            }

            int pilihanPemain = 0;
            while (true) {
                System.out.print("\nJawaban Anda (Pilih Nomor 1-4): ");
                if (scanner.hasNextInt()) {
                    pilihanPemain = scanner.nextInt();
                    if (pilihanPemain >= 1 && pilihanPemain <= 4) {
                        break;
                    }
                } else {
                    scanner.next();
                }
                System.out.println("Input salah! Harap masukkan angka nomor pilihan 1 sampai 4.");
            }

            int jawabanTerpilih = pilihanJawaban.get(pilihanPemain - 1);
            if (jawabanTerpilih == soal.totalBenar) {
                System.out.println("\n✨ Benar Sekali!");
                skor += poinPerSoal;
            } else {
                System.out.println("\n❌ Kurang Tepat!");
            }

            System.out.println("💡 Penjelasan Konsep Perkalian:");
            System.out.println("   • Bentuk Penjumlahan Berulang : " + soal.getVisualisasiPenjumlahan());
            System.out.println("   • Bentuk Operasi Perkalian    : " + soal.jumlahWadah + " x " + soal.isiPerWadah + " = " + soal.totalBenar);
            System.out.println();
        }

        System.out.println("==================================================");
        System.out.println("===                GAME SELESAI                ===");
        System.out.println("==================================================");
        System.out.println("Total Skor Anda: " + skor + " / " + (totalPertanyaan * poinPerSoal));
        scanner.close();
    }
}

```









<br>
