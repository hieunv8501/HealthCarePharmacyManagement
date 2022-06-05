package Models;

public class Nhacungcap {

    private int maNhacungcap;
    private String tenNhacungcap;
    private Xa xa;
    private String soDienthoai;
    private String fax;
    private boolean DaXoa;
    //Phuong thuc

    public Nhacungcap(int maNhacungcap, String tenNhacungcap, Xa xa, String soDienthoai, String fax, boolean DaXoa) {
        this.maNhacungcap = maNhacungcap;
        this.tenNhacungcap = tenNhacungcap;
        this.xa = xa;
        this.soDienthoai = soDienthoai;
        this.fax = fax;
        this.DaXoa = DaXoa;
    }

    public int getMaNhacungcap() {
        return maNhacungcap;
    }

    public void setMaNhacungcap(int maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public String getTenNhacungcap() {
        return tenNhacungcap;
    }

    public void setTenNhacungcap(String tenNhacungcap) {
        this.tenNhacungcap = tenNhacungcap;
    }

    public Xa getXa() {
        return xa;
    }

    public void setXa(Xa xa) {
        this.xa = xa;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }

}
