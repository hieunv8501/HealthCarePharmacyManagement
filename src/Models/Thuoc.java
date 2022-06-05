package Models;

public class Thuoc {

    private int maThuoc;
    private String tenThuoc;
    private String mota;
    private Nhacungcap nhacungcap;
    private Donvitinh donvitinh;
    private String hinhanh;
    private int trangthai;
    private float giaBan;
    //Phuong thuc cua Thuoc

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setNhacungcap(Nhacungcap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public void setDonvitinh(Donvitinh donvitinh) {
        this.donvitinh = donvitinh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public String getMota() {
        return mota;
    }

    public Nhacungcap getNhacungcap() {
        return nhacungcap;
    }

    public Donvitinh getDonvitinh() {
        return donvitinh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    @Override
    public String toString() {
        return ("Thông tin về thuốc là\n Mã thuốc:" + this.maThuoc + "\t Tên thuốc: " + this.tenThuoc + "\t Mota:" + this.mota + "\t Hình ảnh: " + this.hinhanh + "\t Nhà cung cấp" + this.nhacungcap);

    }
}
