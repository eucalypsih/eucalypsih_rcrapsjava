# eucalypsih_rcrapsjava


```
owner="eucalypsih";repo="eucalypsih_rcrapsjava";git clone -q --filter=blob:none --sparse git@github.com:${owner}/${repo}.git && sleep 0.5 && cd $repo && sleep 0.5 && un="eucalypsih";ue="eucalypsih@gmail.com";git checkout main && sleep 0.5 && git config user.name "$un" && sleep 0.5 && git config user.email "$ue" && sleep 0.5 && git config gpg.format ssh && sleep 0.5 && git config user.signingkey ~/.ssh/id_rsa.pub && sleep 0.5 && git config commit.gpgsign true && sleep 0.5 && git config gpg.ssh.allowedSignersFile ~/.ssh/allowed_signers
```
`owner="eucalypsih";repo="eucalypsih_rcrapsjava";git clone -q --filter=blob:none --sparse git@github.com:${owner}/${repo}.git && sleep 0.5 && cd $repo && sleep 0.5 && un="eucalypsih";ue="eucalypsih@gmail.com";git checkout main && sleep 0.5 && git config user.name "$un" && sleep 0.5 && git config user.email "$ue" && sleep 0.5 && git config gpg.format ssh && sleep 0.5 && git config user.signingkey ~/.ssh/id_rsa.pub && sleep 0.5 && git config commit.gpgsign true && sleep 0.5 && git config gpg.ssh.allowedSignersFile ~/.ssh/allowed_signers`

> 
```java

```
>
<br>


> 
```java
https://github.com/eucalypsih/eucalypsih_easy-java
```
>
<br>


> source $PRERIX/etc/bash.bashrc
```bash
source $PRERIX/etc/bash.bashrc
```
>
<br>

# plugins

> <br>
```bash
[plugins]
# Daftarkan plugin shadow versi terbaru yang kompatibel dengan Gradle 8/9
shadow = { id = "com.gradleup.shadow", version = "9.4.2" }
```
> Daftarkan Plugin di `libs.versions.toml`
<br>


> <br>
```bash
plugins {
    id 'application'
    alias(libs.plugins.shadow) // id 'com.gradleup.shadow' // Tambahkan baris ini
}
```
> Pasang Plugin di `app/build.gradle`

<br>


> <br>
```groovy
application {
    mainClass = 'org.example.App' // Sesuai dengan struktur package Anda
}
```
> Pastikan blok `application` di bagian bawah berkas tersebut sudah mendefinisikan `mainClass` tempat fungsi utama Java Anda berada
<br>


> <br>
```bash
./gradlew clean shadowJar --no-configuration-cache
```
> Kompilasi Berkas Menggunakan Perintah `shadowJar`.<br>
> Proses ini akan menghasilkan berkas biner gabungan dengan penanda akhiran khusus. Berkas tersebut akan tersimpan di dalam direktori target keluaran Anda: `app/build/libs/app-all.jar`.
<br>


> <br>
```bash
java -jar app-all.jar
```
> Cara Menjalankan Berkas JAR di Perangkat Lain<br>
> Berkas `app-all.jar` tersebut kini bersifat independen. Anda dapat menyalin berkas tunggal tersebut ke komputer, peladen (server), atau direktori lain, lalu mengeksekusinya secara langsung menggunakan perintah Java standar berikut tanpa membutuhkan Gradle lagi.
<br>


> <br>
```bash
curl -fsL -o app/libs/gson-2.11.0.jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.11.0/gson-2.11.0.jar
```
>
<br>

```bash
./gradlew clean run --no-configuration-cache
```
> memaksa Gradle memperbarui pengaturannya, Anda harus mematikan fitur cache tersebut sementara waktu.<br>
> `--no-configuration-cache`: Memaksa Gradle untuk membaca ulang seluruh isi file `app/build.gradle` dari awal dan mendaftarkan library Gson ke dalam sistem kompilasi Java.

> <br>
```bash
./gradlew clean build
```
> Untuk membuat ulang build dari awal (bersih), Anda perlu menghapus folder `build` yang lama terlebih dahulu menggunakan perintah `clean`, lalu menjalankan perintah `build` kembali.<br>
> `clean`: Menghapus folder build beserta seluruh file cache kompilasi lama agar tidak ada sisa kode yang usang.<br>
> `build`: Mengompilasi ulang seluruh kode sumber dan dependensi lokal Anda dari nol untuk menghasilkan file `.jar` yang benar-benar baru.<br>
> **File** `.jar` **Utama**: Berada di folder `app/build/libs/app.jar` (atau nama project Anda).<br>
> **File Distribusi (Zip/Tar)**: Berada di folder `app/build/distributions/`. File ini berisi aplikasi Anda beserta seluruh dependensi lokal yang sudah dibungkus rapi.<br>
> **Script Eksekusi**: Berada di `app/build/scripts/` untuk menjalankan aplikasi langsung dari terminal.
<br>


> <br>
```bash
./gradlew build --rerun-tasks
```
> mempercepat proses dan mengabaikan cache konfigurasi yang tersimpan, Anda juga bisa menambahkan flag `--rerun-tasks`.
<br>


> <br>
```bash
# Jalankan kompilasi bersih tanpa cache sama sekali
./gradlew compileJava --no-build-cache --no-configuration-cache
```
>
<br>


```bash
# Hapus folder cache internal Gradle di proyek Anda
rm -rf .gradle
rm -rf app/.gradle
```
> Hapus Cache Kompilasi Secara Paksa<br>
> Agar Gradle tidak menggunakan sisa-sisa kompilasi yang eror sebelumnya, kita hapus folder `.gradle` tersembunyi yang menyimpan cache kompilasi.
<br>


> <br>
```bash
# Hapus folder build lama
./gradlew clean

# Hentikan paksa daemon Gradle di Termux
./gradlew --stop

# Jalankan build ulang dengan mematikan fitur cache agar Gradle mengunduh ulang Gson
./gradlew build --no-configuration-cache --refresh-dependencies
```
>
<br>






# qa
* Menjalankan aplikasi langsung lewat Gradle (menggunakan task run).
* Mengonfigurasi **Fat JAR / Shadow JAR** agar semua dependensi lokal langsung menyatu dalam satu file `.jar` tunggal.
* Membuat **Fat JAR / Shadow JAR** agar aplikasi ini bisa Anda salin dan jalankan langsung di perangkat atau server lain hanya dengan perintah `java -jar`.
* Membuat script otomatisasi deployment atau testing.


# note
Masalah import paket (com.google.gson) memang sering menjadi jebakan kecil saat pertama kali mengonfigurasi pustaka biner secara manual, ditambah dengan fitur cache Gradle yang terkadang mengunci setelan lama. Sekarang semuanya sudah bersih dan berjalan interaktif di terminal Termux Anda.


<br>
