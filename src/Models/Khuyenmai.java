/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author TinhBui
 */
public class Khuyenmai {
    private String maKhuyenmai;
    private String tenKhuyenmai;
    private float dieukienKhuyenmai;
    private float phantramKhuyenmai;
    private Calendar ngayBatdau;
    private Calendar ngayKetthuc;
    private boolean daXoa;

    public Khuyenmai(String maKhuyenmai, String tenKhuyenmai, float dieukienKhuyenmai, float phantramKhuyenmai, Calendar ngayBatdau, Calendar ngayKetthuc, boolean daXoa) {
        this.maKhuyenmai = maKhuyenmai;
        this.tenKhuyenmai = tenKhuyenmai;
        this.dieukienKhuyenmai = dieukienKhuyenmai;
        this.phantramKhuyenmai = phantramKhuyenmai;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
        this.daXoa = daXoa;
    }

    public String getMaKhuyenmai() {
        return maKhuyenmai;
    }

    public void setMaKhuyenmai(String maKhuyenmai) {
        this.maKhuyenmai = maKhuyenmai;
    }

    public String getTenKhuyenmai() {
        return tenKhuyenmai;
    }

    public void setTenKhuyenmai(String tenKhuyenmai) {
        this.tenKhuyenmai = tenKhuyenmai;
    }

    public float getDieukienKhuyenmai() {
        return dieukienKhuyenmai;
    }

    public void setDieukienKhuyenmai(float dieukienKhuyenmai) {
        this.dieukienKhuyenmai = dieukienKhuyenmai;
    }

    public float getPhantramKhuyenmai() {
        return phantramKhuyenmai;
    }

    public void setPhantramKhuyenmai(float phantramKhuyenmai) {
        this.phantramKhuyenmai = phantramKhuyenmai;
    }

    public Calendar getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(Calendar ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public Calendar getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(Calendar ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    
}
