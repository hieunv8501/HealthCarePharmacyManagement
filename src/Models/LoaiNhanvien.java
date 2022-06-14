package Models;

public class LoaiNhanvien {
    private int maLoaiNhanvien;
    private String tenLoaiNhanvien;
    private long luongCoBan;
    private boolean daXoa;

    public LoaiNhanvien() {
    }

    public LoaiNhanvien(int maLoaiNhanvien, String tenLoaiNhanvien, long luongCoBan, boolean daXoa) {
        this.maLoaiNhanvien = maLoaiNhanvien;
        this.tenLoaiNhanvien = tenLoaiNhanvien;
        this.luongCoBan = luongCoBan;
        this.daXoa = daXoa;
    }

    
    public int getMaLoaiNhanvien() {
        return maLoaiNhanvien;
    }

    public void setMaLoaiNhanvien(int maLoaiNhanvien) {
        this.maLoaiNhanvien = maLoaiNhanvien;
    }

    public String getTenLoaiNhanvien() {
        return tenLoaiNhanvien;
    }

    public void setTenLoaiNhanvien(String tenLoaiNhanvien) {
        this.tenLoaiNhanvien = tenLoaiNhanvien;
    }

    public long getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(long luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    
    
    
    
}
