package com.mypadangku.resep.model;

public class Resep {

    private int id;
    private String namaResep;
    private String kategori;
    private String deskripsi;
    private String bahanDanCara;

    public Resep() {
    }

    public Resep(int id, String namaResep, String kategori, String deskripsi, String bahanDanCara) {
        this.id = id;
        this.namaResep = namaResep;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.bahanDanCara = bahanDanCara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaResep() {
        return namaResep;
    }

    public void setNamaResep(String namaResep) {
        this.namaResep = namaResep;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBahanDanCara() {
        return bahanDanCara;
    }

    public void setBahanDanCara(String bahanDanCara) {
        this.bahanDanCara = bahanDanCara;
    }
}
