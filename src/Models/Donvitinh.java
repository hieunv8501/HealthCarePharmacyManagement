package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Donvitinh {

    private int maDonvitinh;
    private String tenDonvitinh;
    private String giatri;
    private boolean daXoa;

    public Donvitinh() {

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
    
}
