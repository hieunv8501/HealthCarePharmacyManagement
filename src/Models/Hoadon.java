/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author TinhBui
 */
public class Hoadon {

    private int maHoadon;
    private int maNhanvien;
    private int maKhachhang;
    private String maKhuyenmai;
    private Calendar ngayLap;
    private float tongTien;
    private boolean daXoa;

    public Hoadon(int maHoadon, int maNhanvien, int maKhachhang, String maKhuyenmai, Calendar ngayLap, float tongTien, boolean daXoa) {
        this.maHoadon = maHoadon;
        this.maNhanvien = maNhanvien;
        this.maKhachhang = maKhachhang;
        this.maKhuyenmai = maKhuyenmai;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.daXoa = daXoa;
    }

    public Hoadon(int maNhanvien, int maKhachhang, String maKhuyenmai, Calendar ngayLap) {
        this.maNhanvien = maNhanvien;
        this.maKhachhang = maKhachhang;
        this.maKhuyenmai = maKhuyenmai;
        this.ngayLap = ngayLap;
    }

    public Hoadon(int maHoadon, int maNhanvien, int maKhachhang, String maKhuyenmai, Calendar ngayLap) {
        this.maHoadon = maHoadon;
        this.maNhanvien = maNhanvien;
        this.maKhachhang = maKhachhang;
        this.maKhuyenmai = maKhuyenmai;
        this.ngayLap = ngayLap;
    }

    public int getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(int maHoadon) {
        this.maHoadon = maHoadon;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public int getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(int maKhachhang) {
        this.maKhachhang = maKhachhang;
    }

    public String getMaKhuyenmai() {
        return maKhuyenmai;
    }

    public void setMaKhuyenmai(String maKhuyenmai) {
        this.maKhuyenmai = maKhuyenmai;
    }

    public Calendar getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Calendar ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

}