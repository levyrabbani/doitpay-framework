# DoitPay Automation Test Framework

Proyek ini berisi **Framework Pengujian Otomasi UI** yang dibuat menggunakan **Selenium WebDriver**, **TestNG**, dan **Allure Reports**.  
Framework ini digunakan untuk menguji tiga flow, yaitu:
- User Registration
- User Login
- Add to Cart flow

Framework ini mengikuti pola **Page Object Model (POM)** dan menggunakan pendekatan **data-driven testing** dengan file Excel.

---

## Fitur

- Menggunakan Page Object Model (POM)  
- Manajemen pengujian berbasis TestNG  
- Pengujian berbasis data (data-driven) melalui Excel  
- Integrasi laporan dengan Allure Reporting  
- Penanganan error dan utilitas yang dapat digunakan ulang  
- Data pengujian dan lingkungan dapat dikonfigurasi

---

## Teknologi yang Digunakan

| Komponen            | Library / Alat |
|---------------------|--|
| Bahasa Pemrograman  | Java 17 |
| Build Tool          | Maven |
| Framework Pengujian | TestNG |
| Otomasi UI          | Selenium WebDriver |
| Manajemen Data      | Apache POI |
| Pelaporan           | Allure Reports |
| Browser             | Chrome (via WebDriverManager) |
| API                 | RestAssured |
---

## Langkah-Langkah Setup

### Clone Repository
```bash
git clone https://github.com/yourusername/doitpay-framework.git
cd doitpay-framework
```

### Install Dependensi
Pastikan Maven dan JDK 17+ sudah terpasang, lalu jalankan:
```bash
mvn clean install
```

### Jalankan Pengujian
Untuk menjalankan seluruh suite TestNG:
```bash
mvn test
```

Untuk menjalankan kelas pengujian tertentu:
```bash
mvn test -Dtest=LoginTest
```

---

## Melihat Laporan Pengujian (Allure)

Setelah pengujian dijalankan, buat dan buka laporan dengan perintah berikut:
```bash
allure serve target/allure-results
```

Perintah tersebut akan membuka laporan HTML interaktif yang menampilkan:
- Hasil pengujian
- Waktu eksekusi
- Log tiap langkah pengujian

## Penanganan Error

Setiap page object memiliki:
- Explicit Wait (WebDriverWait)
- Exception handling untuk elemen yang tidak ditemukan
- Validasi fallback jika terjadi kegagalan navigasi atau redirect

Jika pengujian gagal, pesan assertion akan menampilkan penyebab kegagalan.

---

## Asumsi & Keterbatasan

- Aplikasi yang diuji: [https://www.saucedemo.com](https://www.saucedemo.com)
- API yang diuji: [https://jsonplaceholder.typicode.com]
- Browser default yang digunakan adalah Chrome (bisa diubah di konfigurasi).

---

## Rencana Pengembangan Selanjutnya

- Menjalankan pengujian secara paralel dengan konfigurasi TestNG XML
- Integrasi ke pipeline CI/CD (misalnya Jenkins atau GitHub Actions)

---

## Penulis

**Ahmad Levy Rabbani**  
