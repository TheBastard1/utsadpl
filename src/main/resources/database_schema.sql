USE resep_padangku;

CREATE TABLE IF NOT EXISTS resep (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_resep VARCHAR(100) NOT NULL,
    kategori VARCHAR(50) NOT NULL,
    deskripsi TEXT
);

INSERT INTO resep (nama_resep, kategori, deskripsi) VALUES
('Rendang Daging', 'Makanan Utama', 'Daging sapi yang dimasak perlahan dengan bumbu rempah dan santan pekat hingga mengering.'),
('Ayam Pop', 'Makanan Utama', 'Ayam kampung yang direbus bumbu lalu digoreng sebentar, disajikan dengan sambal tomat khas.'),
('Dendeng Batokok', 'Makanan Utama', 'Daging sapi iris tipis yang dipukul-pukul (ditokok) lalu digoreng dengan lumuran cabai hijau.');