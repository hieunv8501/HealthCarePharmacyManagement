/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author admin
 */
public class Nhanvien {
    private int maNhanvien;
    private String tenNhanvien, soDienThoai, gioiTinh, bangCap;
    private Calendar ngaySinh;
    private LoaiNhanvien loaiNhanvien;
    private Xa xa;
    private long luong;
    private boolean daXoa;

    public Nhanvien() {
    }

    public Nhanvien(int maNhanvien, String tenNhanvien, Calendar ngaySinh, String soDienThoai, String gioiTinh, String bangCap, LoaiNhanvien loaiNhanvien, Xa xa, long luong, boolean daXoa) {
        this.maNhanvien = maNhanvien;
        this.tenNhanvien = tenNhanvien;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.bangCap = bangCap;
        this.loaiNhanvien = loaiNhanvien;
        this.xa = xa;
        this.luong = luong;
        this.daXoa = daXoa;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getTenNhanvien() {
        return tenNhanvien;
    }

    public void setTenNhanvien(String tenNhanvien) {
        this.tenNhanvien = tenNhanvien;
    }

    public Calendar getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Calendar ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getBangCap() {
        return bangCap;
    }

    public void setBangCap(String bangCap) {
        this.bangCap = bangCap;
    }

    public LoaiNhanvien getLoaiNhanvien() {
        return loaiNhanvien;
    }

    public void setLoaiNhanvien(LoaiNhanvien loaiNhanvien) {
        this.loaiNhanvien = loaiNhanvien;
    }

    public String getXa() {
        return xa.getTenXa();
    }
    
    public String getHuyen() {
        return xa.getHuyen();
    }
    
    public String getTinh() {
        return xa.getTinh();
    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    
    
}
