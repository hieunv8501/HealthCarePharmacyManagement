package Models;

import java.util.Calendar;

public class Phieunhap {

    private int maPhieunhap;
    private Nhanvien nv;
    private Nhacungcap ncc;
    private Calendar ngayNhap;
    private float tongTien;
    private boolean daXoa;
    
    public Phieunhap(int maPhieunhap, Nhacungcap nCC, Nhanvien nV, Calendar ngayNhap, float tongTien, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.ncc = nCC;
        this.nv = nV;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.daXoa = daXoa;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public Nhanvien getNv() {
        return nv;
    }

    public void setNv(Nhanvien nv) {
        this.nv = nv;
    }

    public Nhacungcap getNcc() {
        return ncc;
    }

    public void setNcc(Nhacungcap ncc) {
        this.ncc = ncc;
    }

    public Calendar getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Calendar ngayNhap) {
        this.ngayNhap = ngayNhap;
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

