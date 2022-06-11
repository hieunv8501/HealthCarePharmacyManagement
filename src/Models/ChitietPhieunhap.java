package Models;

import java.time.LocalDate;

public class ChitietPhieunhap {

    private int maPhieunhap;
    private int maThuoc;
    private String tenThuoc;
    private String tenloaiThuoc;
    private int tileQuidoi;
    private String tenDonvitinh;
    private String tenDonvibanle;
    private int soluong;
    private float dongia;
    private LocalDate ngaySanxuat;
    private LocalDate ngayHethan;
    private int soLuongConLai;
    private String hinhAnh;
    private boolean daXoa;

    public ChitietPhieunhap() {

    }

    public ChitietPhieunhap(int maPhieunhap, int maThuoc, int soLuong, float donGia, LocalDate ngaySanxuat, LocalDate ngayHethan, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.soluong = soLuong;
        this.dongia = donGia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;
        this.daXoa = daXoa;
    }

    public ChitietPhieunhap(int maPhieunhap, int maThuoc, int soluong, float dongia, LocalDate ngaysanxuat, LocalDate ngayhethan) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.soluong = soluong;
        this.dongia = dongia;
        this.ngaySanxuat = ngaysanxuat;
        this.ngayHethan = ngayhethan;
    }

    public ChitietPhieunhap(int maPhieunhap, int maThuoc, String tenThuoc, String tenloaiThuoc, int tileQuidoi, String tenDonvitinh, String tenDonvibanle, int soluong, float dongia, LocalDate ngaySanxuat, LocalDate ngayHethan, int soLuongConLai, String hinhAnh, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.tenloaiThuoc = tenloaiThuoc;
        this.tileQuidoi = tileQuidoi;
        this.tenDonvitinh = tenDonvitinh;
        this.tenDonvibanle = tenDonvibanle;
        this.soluong = soluong;
        this.dongia = dongia;
        this.ngaySanxuat = ngaySanxuat;
        this.ngayHethan = ngayHethan;
        this.soLuongConLai = soLuongConLai;
        this.hinhAnh = hinhAnh;
        this.daXoa = daXoa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getTenloaiThuoc() {
        return tenloaiThuoc;
    }

    public void setTenloaiThuoc(String tenloaiThuoc) {
        this.tenloaiThuoc = tenloaiThuoc;
    }

    public int getTileQuidoi() {
        return tileQuidoi;
    }

    public void setTileQuidoi(int tileQuidoi) {
        this.tileQuidoi = tileQuidoi;
    }

    public String getTenDonvitinh() {
        return tenDonvitinh;
    }

    public void setTenDonvitinh(String tenDonvitinh) {
        this.tenDonvitinh = tenDonvitinh;
    }

    public String getTenDonvibanle() {
        return tenDonvibanle;
    }

    public void setTenDonvibanle(String tenDonvibanle) {
        this.tenDonvibanle = tenDonvibanle;
    }

    public int getSoLuongConLai() {
        return soLuongConLai;
    }

    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
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
