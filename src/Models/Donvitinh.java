/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author HauPC
 */
public class Donvitinh {
    private int maDonvitinh;
    private String tenDonvitinh;
    private String giatri;
    private boolean daXoa;
    public Donvitinh()
    {
        
    }
    public Donvitinh(int maDonvitinh, String tenDonvitinh, String giatri, boolean daXoa) {
        this.maDonvitinh = maDonvitinh;
        this.tenDonvitinh = tenDonvitinh;
        this.giatri = giatri;
        this.daXoa = daXoa;
    }

    public Donvitinh(int maDonvitinh, String tenDonvitinh, String giatri) {
        this.maDonvitinh = maDonvitinh;
        this.tenDonvitinh = tenDonvitinh;
        this.giatri = giatri;
    }
    
    public int getMaDonvitinh() {
        return maDonvitinh;
    }

    public String getTenDonvitinh() {
        return tenDonvitinh;
    }

    public void setMaDonvitinh(int maDonvitinh) {
        this.maDonvitinh = maDonvitinh;
    }

    public void setTenDonvitinh(String tenDonvitinh) {
        this.tenDonvitinh = tenDonvitinh;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    @Override
    public String toString() {
        return tenDonvitinh;
    }

    
    
    public ArrayList<Donvitinh> readDB() {
        ArrayList<Donvitinh> dsDonvitinh = new ArrayList();
        DBConnection DonvitinhConnection = new DBConnection();
        try {
            String query = "SELECT * FROM donvitinh";
            ResultSet rs = DonvitinhConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maDonvitinh = rs.getInt("MaDonViTinh");
                    String tenDonvitinh= rs.getString("TenDonViTinh");
                    String giatri=rs.getString("GiaTri"); 
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsDonvitinh.add(new Donvitinh(maDonvitinh,tenDonvitinh,giatri,daXoa));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng đơn vị tính");
        } finally {
            DonvitinhConnection.closeConnection();
        }
        return dsDonvitinh;
    }
}
