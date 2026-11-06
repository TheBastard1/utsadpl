package com.mypadangku.resep.ui;

import com.mypadangku.resep.dao.ResepDAO;
import com.mypadangku.resep.model.Resep;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private ResepDAO resepDAO;

    public MainFrame() {
        resepDAO = new ResepDAO();
        setTitle("Kelola Resep - MyPadangKu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadData();
    }

    private void initComponents() {
        JPanel panelAtas = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Daftar Resep MyPadangKu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(178, 34, 34));
        panelAtas.add(lblTitle, BorderLayout.CENTER);
        panelAtas.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(panelAtas, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama Resep", "Kategori", "Deskripsi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new FlowLayout());
        JButton btnTambah = new JButton("Tambah Resep");
        JButton btnUbah = new JButton("Ubah Resep");
        JButton btnHapus = new JButton("Hapus Resep");
        JButton btnRefresh = new JButton("Refresh");

        panelBawah.add(btnTambah);
        panelBawah.add(btnUbah);
        panelBawah.add(btnHapus);
        panelBawah.add(btnRefresh);
        panelBawah.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelBawah, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> {
            new ResepFormDialog(this, null).setVisible(true);
            loadData();
        });

        btnUbah.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                Resep selectedResep = resepDAO.getAllResep().stream().filter(r -> r.getId() == id).findFirst().orElse(null);
                if (selectedResep != null) {
                    new ResepFormDialog(this, selectedResep).setVisible(true);
                    loadData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih resep yang ingin diubah terlebih dahulu!");
            }
        });

        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                if (JOptionPane.showConfirmDialog(this, "Yakin hapus resep ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    resepDAO.deleteResep(id);
                    loadData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih resep yang ingin dihapus terlebih dahulu!");
            }
        });

        btnRefresh.addActionListener(e -> loadData());
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Resep> resepList = resepDAO.getAllResep();
        for (Resep r : resepList) {
            tableModel.addRow(new Object[]{r.getId(), r.getNamaResep(), r.getKategori(), r.getDeskripsi()});
        }
    }
}
