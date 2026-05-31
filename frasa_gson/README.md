
```bash

```

> Berikut adalah kode game kuis berbasis teks dalam bahasa Java menggunakan data yang Anda berikan. Game ini akan menampilkan frasa Jepang, lalu meminta pemain memilih arti bahasa Inggris yang benar dari 4 pilihan acak.
<br>


> ⚙️ Cara Kerja KodeRepresentasi Data: Karena Java tidak memiliki tipe data Tuple bawaan seperti Kotlin, kode di atas menggunakan static class Soal untuk menyimpan teks Jepang, arti yang benar, dan daftar pilihan yang salah.Pengacakan Jawaban: Menggunakan Collections.shuffle() agar posisi kunci jawaban selalu berubah secara acak setiap kali game dijalankan.Sistem Input: Menggunakan objek Scanner untuk membaca angka yang diketik oleh pemain di terminal/konsol, lengkap dengan fitur pengaman jika pemain salah mengetik huruf.Jika Anda ingin menambahkan fitur baru, beri tahu saya apakah Anda ingin menyertakan sistem nyawa (lives), penghitung waktu mundur, atau menambah variasi soal ke dalam game ini.

```bash
git init && sleep 1.5 && git remote add origin git@github.com:eucalypsih/hello.git && sleep 1.5 && git config --global user.name "eucalypsih" && sleep 1.5 && git config --global user.email "eucalypsih@gmail.com" && sleep 1.5 && git checkout -b main
```


```bash
./gradlew test
```

```bash
./gradlew run --console plain
```


build.gradle (Format Groovy)
```groovy
run {
    standardInput = System.in
}

```

build.gradle.kts (Format Kotlin DSL)

```kotlin
tasks.named<JavaExec>("run") {
    standardInput = System.in
}

```
> Error java.util.NoSuchElementException terjadi karena secara default, plugin Gradle menjalankan aplikasi di dalam child process dengan input (System.in) yang kosong atau dinonaktifkan. Akibatnya, saat kode Java mencoba membaca ketikan Anda (scanner.nextInt()), Gradle menganggap tidak ada input yang tersedia dan langsung memicu crash. Untuk memperbaikinya, Anda harus mengonfigurasi Gradle agar meneruskan input terminal Anda ke aplikasi Java.
<br>

