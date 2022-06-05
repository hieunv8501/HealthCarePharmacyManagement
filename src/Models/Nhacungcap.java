package Models;

public class Nhacungcap {
    private int maNhacungcap;
    private String tenNhacungcap;
    private Xa xa;
    private String soDienthoai;
    private String fax;
    //Phuong thuc

    public Xa getXa() {
        return xa;
    }

    public void setXa(Xa xa) {
        this.xa = xa;
    }

    public void setMaNhacungcap(int maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public void setTenNhacungcap(String tenNhacungcap) {
        this.tenNhacungcap = tenNhacungcap;
    }


    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getMaNhacungcap() {
        return maNhacungcap;
    }

    public String getTenNhacungcap() {
        return tenNhacungcap;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public String getFax() {
        return fax;
    }
    
}
