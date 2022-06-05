package Models;

import java.util.Calendar;

public class Khachhang {
    private int maKhachhang;
    private String tenKhachhang, gioitinh, soDienthoai;
    private Calendar ngaySinh;
    private Xa xa;
    private boolean khachQuen, daXoa;

    public Khachhang() {
    }

    public Khachhang(int maKhachhang, String tenKhachhang, String gioitinh, String soDienthoai, Calendar ngaySinh, Xa xa, boolean khachQuen, boolean daXoa) {
        this.maKhachhang = maKhachhang;
        this.tenKhachhang = tenKhachhang;
        this.gioitinh = gioitinh;
        this.soDienthoai = soDienthoai;
        this.ngaySinh = ngaySinh;
        this.xa = xa;
        this.khachQuen = khachQuen;
        this.daXoa = daXoa;
    }

    public int getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(int maKhachhang) {
        this.maKhachhang = maKhachhang;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public Calendar getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Calendar ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Xa getXa() {
        return xa;
    }

    public void setXa(Xa xa) {
        this.xa = xa;
    }

    public boolean isKhachQuen() {
        return khachQuen;
    }

    public void setKhachQuen(boolean khachQuen) {
        this.khachQuen = khachQuen;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    
}
