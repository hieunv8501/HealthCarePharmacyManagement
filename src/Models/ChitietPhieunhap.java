package Models;

import java.time.LocalDate;

public class ChitietPhieunhap {

    private int maPhieunhap;
    private LoNhap loNhap;
    private Thuoc thuoc;
    private int soluong;
    private float dongia;
    private LocalDate ngaySanxuat;
    private LocalDate ngayHethan;
    private boolean daXoa;
    
    public ChitietPhieunhap() {}
    
    public ChitietPhieunhap(int maPhieunhap, LoNhap loNhap, Thuoc thuoc, int soLuong, float donGia, LocalDate ngaySanxuat, LocalDate ngayHethan, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.loNhap = loNhap;
        this.thuoc = thuoc;
        this.soluong = soLuong;
        this.dongia = donGia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;    
        this.daXoa = daXoa;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public LoNhap getLoNhap() {
        return loNhap;
    }

    public void setLoNhap(LoNhap loNhap) {
        this.loNhap = loNhap;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
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

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    
}
