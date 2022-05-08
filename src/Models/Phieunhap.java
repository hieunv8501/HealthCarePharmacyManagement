package Models;

import java.time.LocalDate;

public class Phieunhap {
    int maPhieunhap;
    int maNhacungcap;    
    int maNhanvien;
    LocalDate ngaynhap;
    float tongTien;
    boolean daxoa;

    public Phieunhap(int maPhieunhap, int maNhacungcap, int maNhanvien, LocalDate ngaynhap, float tongTien, boolean daxoa) {
        this.maPhieunhap = maPhieunhap;
        this.maNhacungcap = maNhacungcap;
        this.maNhanvien = maNhanvien;
        this.ngaynhap = ngaynhap;
        this.tongTien = tongTien;
        this.daxoa = daxoa;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public int getMaNhacungcap() {
        return maNhacungcap;
    }

    public void setMaNhacungcap(int maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public LocalDate getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDaxoa() {
        return daxoa;
    }

    public void setDaxoa(boolean daxoa) {
        this.daxoa = daxoa;
    }
    
    
}
