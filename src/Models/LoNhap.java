package Models;

public class LoNhap {
    private int maLo;
    private Thuoc thuoc;
    private Phieunhap phieunhap;
    private int soluongConlai;
    private boolean daXoa;

    public LoNhap(int maLo, Thuoc thuoc, Phieunhap phieunhap, int soluongConlai, boolean daXoa) {
        this.maLo = maLo;
        this.thuoc = thuoc;
        this.phieunhap = phieunhap;
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

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public Phieunhap getPhieunhap() {
        return phieunhap;
    }

    public void setPhieunhap(Phieunhap phieunhap) {
        this.phieunhap = phieunhap;
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
