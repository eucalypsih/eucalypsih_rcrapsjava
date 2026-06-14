package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    // Tambahkan metode ini agar AppTest.java tidak error saat dijalankan
    public String getGreeting() {
        return "Hello World!";
    }

    static class Soal {
        String frasaJepang;
        String artiBenar;
        List<String> pilihanSalah;

        public Soal(String frasaJepang, String artiBenar, List<String> pilihanSalah) {
            this.frasaJepang = frasaJepang;
            this.artiBenar = artiBenar;
            this.pilihanSalah = pilihanSalah;
        }
    }

    public static void main(String[] args) {
        List<Soal> daftarSoal = new ArrayList<>();
        daftarSoal.add(new Soal(
            "ご飯を食べる", 
            "eat rice", 
            Arrays.asList("drink water", "sleep", "walk")
        ));
        daftarSoal.add(new Soal(
            "朝ご飯を食べる", 
            "eat breakfast", 
            Arrays.asList("skip breakfast", "cook", "shop")
        ));

        Scanner scanner = new Scanner(System.in);
        int skor = 0;

        System.out.println("=== GAME TEBAK ARTI BAHASA JEPANG ===");
        System.out.println("Pilihlah jawaban yang paling tepat (1-4)!\n");

        for (int i = 0; i < daftarSoal.size(); i++) {
            Soal soalSaatIni = daftarSoal.get(i);
            System.out.println("Pertanyaan " + (i + 1) + ": Apa arti dari \"" + soalSaatIni.frasaJepang + "\"?");

            List<String> semuaPilihan = new ArrayList<>();
            semuaPilihan.add(soalSaatIni.artiBenar);
            semuaPilihan.addAll(soalSaatIni.pilihanSalah);
            
            Collections.shuffle(semuaPilihan);

            for (int j = 0; j < semuaPilihan.size(); j++) {
                System.out.println((j + 1) + ". " + semuaPilihan.get(j));
            }

            int pilihanPemain = 0;
            while (true) {
                System.out.print("Jawaban Anda (1-4): ");
                if (scanner.hasNextInt()) {
                    pilihanPemain = scanner.nextInt();
                    if (pilihanPemain >= 1 && pilihanPemain <= 4) {
                        break;
                    }
                } else {
                    scanner.next();
                }
                System.out.println("Input tidak valid! Harap masukkan angka 1 sampai 4.");
            }

            String jawabanTerpilih = semuaPilihan.get(pilihanPemain - 1);
            if (jawabanTerpilih.equals(soalSaatIni.artiBenar)) {
                System.out.println("✨ Benar sekali!\n");
                skor += 50;
            } else {
                System.out.println("❌ Salah! Jawaban yang benar adalah: " + soalSaatIni.artiBenar + "\n");
            }
        }

        System.out.println("=== GAME SELESAI ===");
        System.out.println("Total Skor Anda: " + skor + " / 100");
        scanner.close();
    }
}
