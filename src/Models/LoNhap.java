package Models;

public class LoNhap {
    private int maLo;
    private int maThuoc;
    private int maPhieunhap;
    private int soluongConlai;
    private boolean daXoa;

    public LoNhap(int maLo, int maThuoc, int maPhieunhap, int soluongConlai, boolean daXoa) {
        this.maLo = maLo;
        this.maThuoc = maThuoc;
        this.maPhieunhap = maPhieunhap;
        this.soluongConlai = soluongConlai;
        this.daXoa = daXoa;
    }

    public LoNhap() {
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
