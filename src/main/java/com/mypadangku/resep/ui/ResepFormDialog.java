package com.mypadangku.resep.ui;

import com.mypadangku.resep.controller.ResepController;
import com.mypadangku.resep.model.Resep;
import javax.swing.*;
import java.awt.*;

public class ResepFormDialog extends JDialog {

    private JTextField txtNama;
    private JComboBox<String> cbKategori;
    private JTextArea txtDeskripsi;
    private JTextArea txtBahanCara;
    private Resep resep;
    private ResepController resepDAO;

    public ResepFormDialog(Frame owner, Resep resep) {
        super(owner, resep == null ? "Tambah Resep Baru" : "Ubah Resep Masakan", true);
        this.resep = resep;
        this.resepDAO = new ResepController();
        setSize(500, 500);
        setLocationRelativeTo(owner);
        initComponents();
    }

    private void initComponents() {
        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JPanel pnlNama = new JPanel(new BorderLayout());
        pnlNama.add(new JLabel("Nama Resep:"), BorderLayout.NORTH);
        txtNama = new JTextField();
        pnlNama.add(txtNama, BorderLayout.CENTER);
        formPanel.add(pnlNama);

        JPanel pnlKategori = new JPanel(new BorderLayout());
        pnlKategori.add(new JLabel("Kategori Masakan:"), BorderLayout.NORTH);
        cbKategori = new JComboBox<>(new String[]{"Gulai", "Rendang", "Balado", "Bakar", "Goreng", "Sayur", "Lainnya"});
        pnlKategori.add(cbKategori, BorderLayout.CENTER);
        formPanel.add(pnlKategori);

        JPanel pnlDeskripsi = new JPanel(new BorderLayout());
        pnlDeskripsi.add(new JLabel("Deskripsi Singkat:"), BorderLayout.NORTH);
        txtDeskripsi = new JTextArea(3, 20);
        txtDeskripsi.setLineWrap(true);
        pnlDeskripsi.add(new JScrollPane(txtDeskripsi), BorderLayout.CENTER);
        formPanel.add(pnlDeskripsi);

        JPanel pnlBahan = new JPanel(new BorderLayout());
        pnlBahan.add(new JLabel("Bahan & Cara Membuat:"), BorderLayout.NORTH);
        txtBahanCara = new JTextArea(6, 20);
        txtBahanCara.setLineWrap(true);
        pnlBahan.add(new JScrollPane(txtBahanCara), BorderLayout.CENTER);
        formPanel.add(pnlBahan);

        panelUtama.add(formPanel, BorderLayout.CENTER);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSimpan = new JButton("Simpan Resep");
        JButton btnBatal = new JButton("Batal");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnBatal);
        panelUtama.add(panelTombol, BorderLayout.SOUTH);

        add(panelUtama);

        if (resep != null) {
            txtNama.setText(resep.getNamaResep());
            cbKategori.setSelectedItem(resep.getKategori());
            txtDeskripsi.setText(resep.getDeskripsi());
            txtBahanCara.setText(resep.getBahanDanCara());
        }

        btnSimpan.addActionListener(e -> simpanData());
        btnBatal.addActionListener(e -> dispose());
    }

    private void simpanData() {
        String nama = txtNama.getText().trim();
        String kategori = (String) cbKategori.getSelectedItem();
        String deskripsi = txtDeskripsi.getText().trim();
        String bahanCara = txtBahanCara.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama resep tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (resep == null) {
            resepDAO.addResep(new Resep(0, nama, kategori, deskripsi, bahanCara));
            JOptionPane.showMessageDialog(this, "Resep masakan berhasil ditambahkan!");
        } else {
            resep.setNamaResep(nama);
            resep.setKategori(kategori);
            resep.setDeskripsi(deskripsi);
            resep.setBahanDanCara(bahanCara);
            resepDAO.updateResep(resep);
            JOptionPane.showMessageDialog(this, "Resep masakan berhasil diubah!");
        }
        dispose();
    }
}
