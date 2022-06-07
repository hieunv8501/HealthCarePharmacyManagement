package Models;

import java.util.Calendar;

public class Hoadon {

    private int maHoadon;
    private Nhanvien nhanvien;
    private Khachhang khachhang;
    private Khuyenmai khuyenmai;
    private Calendar ngayLap;
    private float tongTien;
    private boolean daXoa;
    
    public Hoadon(int maHoadon, Nhanvien nhanvien, Khachhang khachhang, Khuyenmai khuyenmai, Calendar ngayLap, float tongTien, boolean daXoa) {
        this.maHoadon = maHoadon;
        this.nhanvien = nhanvien;
        this.khachhang = khachhang;
        this.khuyenmai = khuyenmai;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.daXoa = daXoa;
    }
    
    public Hoadon(Nhanvien nhanvien, Khachhang khachhang, Khuyenmai khuyenmai, Calendar ngayLap) {
        this.nhanvien = nhanvien;
        this.khachhang = khachhang;
        this.khuyenmai = khuyenmai;
        this.ngayLap = ngayLap;
    }

    public Hoadon(int maHoadon, Nhanvien nhanVien, Khachhang khachHang, Khuyenmai maKhuyenmai, Calendar ngayLap) {
        this.maHoadon = maHoadon;
        this.nhanvien = nhanVien;
        this.khachhang = khachHang;
        this.khuyenmai = maKhuyenmai;
        this.ngayLap = ngayLap;
    }

    public int getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(int maHoadon) {
        this.maHoadon = maHoadon;
    }

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public Khachhang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }

    public Khuyenmai getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(Khuyenmai khuyenmai) {
        this.khuyenmai = khuyenmai;
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
