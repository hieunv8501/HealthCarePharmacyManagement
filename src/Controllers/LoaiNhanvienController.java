/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DBConnection.DBConnection;
import Models.LoaiNhanvien;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class LoaiNhanvienController {
    public static LoaiNhanvien getLoaiNhanvien(int maLoaiNhanvien) {
        LoaiNhanvien loaiNhanvien = null;
        String query = "SELECT * FROM loainhanvien WHERE DaXoa = 0 AND MaLoaiNhanVien = " + maLoaiNhanvien + "";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    String tenLoaiNhanvien = rs.getString("TenLoaiNhanVien");
                    long luong = rs.getLong("LuongCoBan");
                    loaiNhanvien = new LoaiNhanvien(maLoaiNhanvien, tenLoaiNhanvien, luong, false);
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return loaiNhanvien;
    }
    
    public ArrayList<LoaiNhanvien> getDSLoaiNhanvien(){
        ArrayList<LoaiNhanvien> dsLoaiNhanvien = new ArrayList<>();
        String query = "SELECT * FROM loainhanvien WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    int maLoaiNhanvien = rs.getInt("MaLoaiNhanVien");
                    String tenLoaiNhanvien = rs.getString("TenLoaiNhanVien");
                    long luong = rs.getLong("LuongCoBan");
                    dsLoaiNhanvien.add(new LoaiNhanvien(maLoaiNhanvien, tenLoaiNhanvien, luong, false));
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsLoaiNhanvien;
    }
    
    public ArrayList<String> getDSTenLoaiNhanVien(){
        ArrayList<String> dsTenLoaiNhanvien = new ArrayList<>();
        String query = "SELECT TenLoaiNhanVien FROM loainhanvien WHERE DaXoa = 0";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    
                    String tenLoaiNhanvien = rs.getString("TenLoaiNhanVien");
                    
                    dsTenLoaiNhanvien.add(tenLoaiNhanvien);
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsTenLoaiNhanvien;
    }
    
    public static int getMaLoaiNhanvien(String tenLoaiNhanvien) {
        int maLoaiNV = 0;
        String query = "SELECT MaLoaiNhanVien FROM loainhanvien WHERE TenLoaiNhanVien = N'"+tenLoaiNhanvien+"'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    
                    maLoaiNV = rs.getInt("MaLoaiNhanVien");
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return maLoaiNV;
    }
}
