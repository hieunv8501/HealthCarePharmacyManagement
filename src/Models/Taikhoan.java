package Models;

public class Taikhoan {
    String taikhoan, matkhau, maNhanvien, maQuyen;

    public Taikhoan(String taikhoan, String matkhau, String maNhanvien, String maQuyen) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.maNhanvien = maNhanvien;
        this.maQuyen = maQuyen;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(String maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    
}
