package Models;

import java.util.Calendar;

public class Phieunhap {

    private int maPhieunhap;
    private int maNhacungcap;
    private int maNhanvien;
    private Calendar ngayNhap;
    private float tongTien;
    private boolean daXoa;
    private Nhanvien nv;
    private Nhacungcap ncc;
    private String tenNhacungcap;
    private String tenNhanvien;

    public Phieunhap(int maPhieunhap, int maNhacungcap, int maNhanvien, Calendar ngayNhap, float tongTien, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maNhacungcap = maNhacungcap;
        this.maNhanvien = maNhanvien;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.daXoa = daXoa;
    }
    
     public Phieunhap(int maNhacungcap, int maNhanvien, Calendar ngayNhap, float tongTien) {
        this.maNhacungcap = maNhacungcap;
        this.maNhanvien = maNhanvien;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public Phieunhap(int maPhieunhap, Nhacungcap nCC, Nhanvien nV, Calendar ngayNhap, float tongTien, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.ncc.setMaNhacungcap(nCC.getMaNhacungcap());
        this.ncc.setTenNhacungcap(nCC.getTenNhacungcap());
        this.nv.setMaNhanvien(nV.getMaNhanvien());
        this.nv.setTenNhanvien(nV.getTenNhanvien());
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

    public Calendar getNgaynhap() {
        return ngayNhap;
    }

    public void setNgaynhap(Calendar ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDaxoa() {
        return daXoa;
    }

    public void setDaxoa(boolean daXoa) {
        this.daXoa = daXoa;
    }     
}

