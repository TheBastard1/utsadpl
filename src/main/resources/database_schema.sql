CREATE DATABASE IF NOT EXISTS mypadangku_resep;
USE mypadangku_resep;

CREATE TABLE IF NOT EXISTS resep (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_resep VARCHAR(255) NOT NULL,
    kategori VARCHAR(100) NOT NULL,
    deskripsi TEXT,
    bahan_dan_cara TEXT
);