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
    private int soluong;
    private float dongia;
    private boolean daXoa;
    private String tenThuoc;
    private String tenDonvitinh;

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

    public ChitietHoadon(int maHoadon, int maThuoc, int maLo, int soluong, float dongia, boolean daXoa) {
        this.maHoadon = maHoadon;
        this.maThuoc = maThuoc;
        this.maLo = maLo;
        this.soluong = soluong;
        this.dongia = dongia;
        this.daXoa = daXoa;
    }

    public ChitietHoadon(int maThuoc, int maLo, int soluong, float dongia) {
        this.maThuoc = maThuoc;
        this.maLo = maLo;
        this.soluong = soluong;
        this.dongia = dongia;
    }

}