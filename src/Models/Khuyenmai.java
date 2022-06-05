package Models;

import java.time.LocalDate;

public class Khuyenmai {
    private String maKhuyenmai;
    private String tenKhuyenmai;
    private float dieukienKhuyenmai;
    private float phantramKhuyenmai;
    private LocalDate ngayBatdau;
    private LocalDate ngayKetthuc;
    private boolean daXoa;

    public Khuyenmai(){
        
    }
    public Khuyenmai(String maKhuyenmai, String tenKhuyenmai, float dieukienKhuyenmai, float phantramKhuyenmai, LocalDate ngayBatdau, LocalDate ngayKetthuc, boolean daXoa) {
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

    public LocalDate getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(LocalDate ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public LocalDate getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(LocalDate ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    
    
}