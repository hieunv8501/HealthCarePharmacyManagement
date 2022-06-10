/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author TinhBui
 */
public class LoNhap {

    private int maLo;
    private int maThuoc;
    private String tenThuoc;
    private int maPhieunhap;
    private int soluongConlai;
    private int tiLeQuidoi;
    private String tenDonvitinh;
    private String tenDonviBanLe;
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private boolean daXoa;

    public String getTenDonviBanLe() {
        return tenDonviBanLe;
    }

    public void setTenDonviBanLe(String tenDonviBanLe) {
        this.tenDonviBanLe = tenDonviBanLe;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getTiLeQuidoi() {
        return tiLeQuidoi;
    }

    public void setTiLeQuidoi(int tiLeQuidoi) {
        this.tiLeQuidoi = tiLeQuidoi;
    }

    public String getTenDonvitinh() {
        return tenDonvitinh;
    }

    public void setTenDonvitinh(String tenDonvitinh) {
        this.tenDonvitinh = tenDonvitinh;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public LoNhap(int maLo, int maThuoc, int maPhieunhap, int soluongConlai, boolean daXoa) {
        this.maLo = maLo;
        this.maThuoc = maThuoc;
        this.maPhieunhap = maPhieunhap;
        this.soluongConlai = soluongConlai;
        this.daXoa = daXoa;
    }

    public LoNhap() {
    }

    public LoNhap(int maLo, int maThuoc, String tenThuoc, int soluongConlai, int tiLeQuidoi, String tenDonvitinh, String tenDonviBanLe, LocalDate ngaySanXuat, LocalDate ngayHetHan, boolean daXoa) {
        this.maLo = maLo;
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.soluongConlai = soluongConlai;
        this.tiLeQuidoi = tiLeQuidoi;
        this.tenDonvitinh = tenDonvitinh;
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.tenDonviBanLe = tenDonviBanLe;
        this.daXoa = daXoa;
    }

    public int getMaLo() {
        return maLo;
    }

    public void setMaLo(int maLo) {
        this.maLo = maLo;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public int getSoluongConlai() {
        return soluongConlai;
    }

    public void setSoluongConlai(int soluongConlai) {
        this.soluongConlai = soluongConlai;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

}
