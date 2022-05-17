/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author TinhBui
 */
public class ChitietHoadon {

    private int maHoadon;
    private int maThuoc;
    private int maLo;
    private int maDonvitinh;
    private int soluong;
    private float dongia;
    private boolean daXoa;

    public int getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(int maHoadon) {
        this.maHoadon = maHoadon;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getMaLo() {
        return maLo;
    }

    public void setMaLo(int maLo) {
        this.maLo = maLo;
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

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public ChitietHoadon(int maHoadon, int maThuoc, int maLo, int maDonvitinh, int soluong, float dongia, boolean daXoa) {
        this.maHoadon = maHoadon;
        this.maThuoc = maThuoc;
        this.maLo = maLo;
        this.maDonvitinh = maDonvitinh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.daXoa = daXoa;
    }

    public ChitietHoadon(int maThuoc, int maLo, int maDonvitinh, int soluong, float dongia) {
        this.maThuoc = maThuoc;
        this.maLo = maLo;
        this.maDonvitinh = maDonvitinh;
        this.soluong = soluong;
        this.dongia = dongia;
    }

}