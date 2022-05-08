package Models;

import java.time.LocalDate;

public class ChitietPhieunhap {
    int maPhieunhap;
    int maThuoc;
    int maDonvitinh;
    int soluong;
    float dongia;
    LocalDate ngaySanxuat;
    LocalDate ngayHethan;
    boolean daxoa;

    public ChitietPhieunhap(int maPhieunhap, int maThuoc, int maDonvitinh, int soluong, float dongia, LocalDate ngaySanxuat, LocalDate ngayHethan, boolean daxoa) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.maDonvitinh = maDonvitinh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;
        this.daxoa = daxoa;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getMaDonvitinh() {
        return maDonvitinh;
    }

    public void setMaDonvitinh(int maDonvitinh) {
        this.maDonvitinh = maDonvitinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public LocalDate getNgaySanxuat() {
        return ngaySanxuat;
    }

    public void setNgaySanxuat(LocalDate ngaySanxuat) {
        this.ngaySanxuat = ngaySanxuat;
    }

    public LocalDate getNgayHethan() {
        return ngayHethan;
    }

    public void setNgayHethan(LocalDate ngayHethan) {
        this.ngayHethan = ngayHethan;
    }

    public boolean isDaxoa() {
        return daxoa;
    }

    public void setDaxoa(boolean daxoa) {
        this.daxoa = daxoa;
    }
    
}
