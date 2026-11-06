package com.mypadangku.resep.dao;

import com.mypadangku.resep.config.DatabaseConfig;
import com.mypadangku.resep.model.Resep;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResepDAO {

    public List<Resep> getAllResep() {
        List<Resep> listResep = new ArrayList<>();
        String sql = "SELECT * FROM resep";
        try (Connection conn = DatabaseConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listResep.add(new Resep(
                        rs.getInt("id"),
                        rs.getString("nama_resep"),
                        rs.getString("kategori"),
                        rs.getString("deskripsi"),
                        rs.getString("bahan_dan_cara")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResep;
    }

    public void addResep(Resep resep) {
        String sql = "INSERT INTO resep (nama_resep, kategori, deskripsi, bahan_dan_cara) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, resep.getNamaResep());
            pstmt.setString(2, resep.getKategori());
            pstmt.setString(3, resep.getDeskripsi());
            pstmt.setString(4, resep.getBahanDanCara());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateResep(Resep resep) {
        String sql = "UPDATE resep SET nama_resep=?, kategori=?, deskripsi=?, bahan_dan_cara=? WHERE id=?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, resep.getNamaResep());
            pstmt.setString(2, resep.getKategori());
            pstmt.setString(3, resep.getDeskripsi());
            pstmt.setString(4, resep.getBahanDanCara());
            pstmt.setInt(5, resep.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteResep(int id) {
        String sql = "DELETE FROM resep WHERE id=?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
