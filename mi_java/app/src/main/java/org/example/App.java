package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    // Mengubah struktur Soal untuk menampung visualisasi perkalian nyata
    static class SoalPerkalian {
        String namaBenda;
        String namaWadah;
        int jumlahWadah;
        int isiPerWadah;
        int totalBenar;

        public SoalPerkalian(String namaBenda, String namaWadah, int jumlahWadah, int isiPerWadah) {
            this.namaBenda = namaBenda;
            this.namaWadah = namaWadah;
            this.jumlahWadah = jumlahWadah;
            this.isiPerWadah = isiPerWadah;
            this.totalBenar = jumlahWadah * isiPerWadah;
        }

        // Membuat teks narasi cerita nyata untuk ditampilkan di game
        public String getTeksCerita() {
            return "Anda melihat ada " + jumlahWadah + " " + namaWadah + ". \n" +
                   "Setiap " + namaWadah + " tersebut berisi " + isiPerWadah + " " + namaBenda + ".";
        }

        // Membuat visualisasi penjumlahan berulang saat pemain menjawab
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
        // Membuat daftar ilustrasi objek nyata di kehidupan sehari-hari
        List<SoalPerkalian> bankSoal = new ArrayList<>();
        bankSoal.add(new SoalPerkalian("Donat", "Kotak", 3, 4));
        bankSoal.add(new SoalPerkalian("Kaki Kucing", "Ekor Kucing", 5, 4));
        bankSoal.add(new SoalPerkalian("Tablet Obat", "Hari (Diminum)", 3, 1));
        bankSoal.add(new SoalPerkalian("Kelereng", "Kantong plastik", 4, 5));
        bankSoal.add(new SoalPerkalian("Roda", "Mobil", 2, 4));

        // Mengacak urutan soal agar game seru saat dimainkan ulang
        Collections.shuffle(bankSoal);

        Scanner scanner = new Scanner(System.in);
        int skor = 0;
        int poinPerSoal = 20;
        int totalPertanyaan = 5; // Membatasi game hingga 5 ronde

        System.out.println("=== GAME ILUSTRASI PERKALIAN NYATA ===");
        System.out.println("Hitunglah total benda berdasarkan situasi nyata berikut!\n");

        for (int i = 0; i < totalPertanyaan && i < bankSoal.size(); i++) {
            SoalPerkalian soal = bankSoal.get(i);
            
            System.out.println("--------------------------------------------------");
            System.out.println("Pertanyaan " + (i + 1) + ":");
            System.out.println(soal.getTeksCerita());
            System.out.println("Berapakah total " + soal.namaBenda + " seluruhnya?");
            System.out.println("--------------------------------------------------");

            // Membuat pilihan jawaban (1 pilihan benar, 3 pilihan acak/salah)
            List<Integer> pilihanJawaban = new ArrayList<>();
            pilihanJawaban.add(soal.totalBenar);
            pilihanJawaban.add(soal.totalBenar + 2);
            pilihanJawaban.add(soal.totalBenar - 1 < 0 ? soal.totalBenar + 5 : soal.totalBenar - 1);
            pilihanJawaban.add(soal.totalBenar * 2 - 1);
            
            // Menghilangkan duplikat jika ada angka pilihan yang tidak sengaja sama
            pilihanJawaban = new ArrayList<>(new java.util.HashSet<>(pilihanJawaban));
            while(pilihanJawaban.size() < 4) {
                pilihanJawaban.add(soal.totalBenar + pilihanJawaban.size() + 3);
            }
            
            Collections.shuffle(pilihanJawaban);

            // Menampilkan pilihan jawaban ke layar
            for (int j = 0; j < pilihanJawaban.size(); j++) {
                System.out.println((j + 1) + ". " + pilihanJawaban.get(j) + " " + soal.namaBenda);
            }

            // Validasi input angka dari pemain
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

            // Memeriksa hasil jawaban pemain
            int jawabanTerpilih = pilihanJawaban.get(pilihanPemain - 1);
            if (jawabanTerpilih == soal.totalBenar) {
                System.out.println("\n✨ Benar Sekali!");
                skor += poinPerSoal;
            } else {
                System.out.println("\n❌ Kurang Tepat!");
            }

            // MENAMPILKAN ILUSTRASI MATEMATIKANYA
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
