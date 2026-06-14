package org.example;

// trap: .code 
// HAPUS kata ".code" dari baris import lama Anda
import com.google.gson.Gson; // jebakan: com.google.code.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    static class Soal {
        String frasaJepang;
        String artiBenar;
        List<String> pilihanSalah;
    }

    public static void main(String[] args) {
        List<Soal> daftarSoal = new ArrayList<>();
        Gson gson = new Gson();

        String namaFileSoal = "soal.json"; // Default jika tidak diisi

        // Mencari argumen -f atau --file
        for (int i = 0; i < args.length; i++) {
            if ((args[i].equals("-f") || args[i].equals("--file")) && (i + 1 < args.length)) {
                namaFileSoal = args[i + 1];
                break;
            }
        }

        System.out.println("Memuat soal dari: " + namaFileSoal);


        // Membaca soal dari file JSON eksternal
        try (FileReader reader = new FileReader(namaFileSoal)) {
            Type tipeListSoal = new TypeToken<ArrayList<Soal>>() {}.getType();
            daftarSoal = gson.fromJson(reader, tipeListSoal);
        } catch (IOException e) {
            System.out.println("❌ Gagal membaca file soal.json! Pastikan file berada di folder yang benar.");
            e.printStackTrace();
            return; 
        }

        Scanner scanner = new Scanner(System.in);
        int skor = 0;
        int poinPerSoal = 20; 

        System.out.println("=== GAME TEBAK ARTI BAHASA JEPANG (JSON) ===");
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
                skor += poinPerSoal;
            } else {
                System.out.println("❌ Salah! Jawaban yang benar adalah: " + soalSaatIni.artiBenar + "\n");
            }
        }

        int skorMaksimal = daftarSoal.size() * poinPerSoal; 

        System.out.println("=== GAME SELESAI ===");
        System.out.println("Total Skor Anda: " + skor + " / " + skorMaksimal);
        scanner.close();
    }
}

