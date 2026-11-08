package com.mypadangku.resep.controller;

import com.mypadangku.resep.config.DatabaseConfig;
import com.mypadangku.resep.model.Resep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResepController {

    public List<Resep> getAllResep() {
        List<Resep> listResep = new ArrayList<>();
        String sql = "SELECT * FROM resep";

        try (Connection conn = DatabaseConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Resep resep = new Resep();
                resep.setId(rs.getInt("id"));
                resep.setNamaResep(rs.getString("nama_resep"));
                resep.setKategori(rs.getString("kategori"));
                resep.setDeskripsi(rs.getString("deskripsi"));

                listResep.add(resep);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data resep: " + e.getMessage());
            e.printStackTrace();
        }
        return listResep;
    }

    public boolean addResep(Resep resep) {
        String sql = "INSERT INTO resep (nama_resep, kategori, deskripsi) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, resep.getNamaResep());
            pstmt.setString(2, resep.getKategori());
            pstmt.setString(3, resep.getDeskripsi());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menambah resep: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateResep(Resep resep) {
        String sql = "UPDATE resep SET nama_resep = ?, kategori = ?, deskripsi = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, resep.getNamaResep());
            pstmt.setString(2, resep.getKategori());
            pstmt.setString(3, resep.getDeskripsi());
            pstmt.setInt(4, resep.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saat mengubah resep: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteResep(int id) {
        String sql = "DELETE FROM resep WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus resep: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
