package Models;

import java.util.Calendar;

public class ChitietPhieunhap {

    private int maPhieunhap;
    private int maThuoc;
    private int maDonvitinh;
    private int soluong;
    private float dongia;
    private Calendar ngaySanxuat;
    private Calendar ngayHethan;
    private boolean daXoa;
    
    private String tenThuoc;
    private String tenLoaiThuoc;
    private String tenDonvitinh;
    private int maLo;
    
    
    public ChitietPhieunhap() {}
    
    public ChitietPhieunhap(int maPhieunhap, int maThuoc, int soLuong, float donGia, Calendar ngaySanxuat, Calendar ngayHethan, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.soluong = soLuong;
        this.dongia = donGia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;
        this.daXoa = daXoa;
    }

    public ChitietPhieunhap(int maPhieunhap, int maThuoc, int soLuong, float donGia, Calendar ngaySanxuat, Calendar ngayHethan) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.soluong = soLuong;
        this.dongia = donGia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;
    }


    public ChitietPhieunhap(int maPhieunhap, int maLo, int maThuoc, String tenThuoc, String tenLoaiThuoc, int maDonvitinh, String tenDonvitinh, int soLuong, float donGia, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maLo = maLo;
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.tenLoaiThuoc = tenLoaiThuoc;
        this.soluong = soLuong;
        this.maDonvitinh = maDonvitinh;
        this.tenDonvitinh = tenDonvitinh;
        this.dongia = donGia;
        this.daXoa = daXoa;
    }
    
    public int getMaLo() {
        return maLo;
    }

    public void setMaLo(int maLo) {
        this.maLo = maLo;
    }
    
    public String getTenDonvitinh() {
        return tenDonvitinh;
    }

    public void setTenDonvitinh(String tenDonvitinh) {
        this.tenDonvitinh = tenDonvitinh;
    }
    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
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

    public Calendar getNgaySanxuat() {
        return ngaySanxuat;
    }

    public void setNgaySanxuat(Calendar ngaySanxuat) {
        this.ngaySanxuat = ngaySanxuat;
    }

    public Calendar getNgayHethan() {
        return ngayHethan;
    }

    public void setNgayHethan(Calendar ngayHethan) {
        this.ngayHethan = ngayHethan;
    }

    public boolean isDaxoa() {
        return daXoa;
    }

    public void setDaxoa(boolean daxoa) {
        this.daXoa = daxoa;
    }
}
