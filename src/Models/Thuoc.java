package Models;

import Controllers.DonvitinhController;
import Controllers.NhacungcapController;
import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Thuoc {

    private int maThuoc;
    private String tenThuoc;
    private String mota;
    private String dotuoi;
    private String hinhanh;
    private Donvitinh donvitinh;
    private Donvitinh donviQuydoi;
    private int tileQuydoi;
    private Nhacungcap nhacungcap;
    private LoaiThuoc loaiThuoc;
    private float giaBan;
    private boolean DaXoa;
   

    public Thuoc() {
    
    }

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

    public String getDotuoi() {
        return dotuoi;
    }

    public void setDotuoi(String dotuoi) {
        this.dotuoi = dotuoi;
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

    

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }

    public Donvitinh getDonviQuydoi() {
        return donviQuydoi;
    }

    public int getTileQuydoi() {
        return tileQuydoi;
    }

    

    public void setDonviQuydoi(Donvitinh donviQuydoi) {
        this.donviQuydoi = donviQuydoi;
    }

    public void setTileQuydoi(int tileQuydoi) {
        this.tileQuydoi = tileQuydoi;
    }

    public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public LoaiThuoc getLoaiThuoc() {
        return loaiThuoc;
    }

    public Thuoc(int maThuoc, String tenThuoc, String mota, String dotuoi, String hinhanh, Donvitinh donvitinh, Donvitinh donviQuydoi, int tileQuydoi, Nhacungcap nhacungcap, LoaiThuoc loaiThuoc, float giaBan, boolean DaXoa) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.mota = mota;
        this.dotuoi = dotuoi;
        this.hinhanh = hinhanh;
        this.donvitinh = donvitinh;
        this.donviQuydoi = donviQuydoi;
        this.tileQuydoi = tileQuydoi;
        this.nhacungcap = nhacungcap;
        this.loaiThuoc = loaiThuoc;
        this.giaBan = giaBan;
        this.DaXoa = DaXoa;
    }

    public Thuoc(int maThuoc, String tenThuoc, String mota, String dotuoi, String hinhanh, Donvitinh donvitinh, Donvitinh donviQuydoi, int tileQuydoi, Nhacungcap nhacungcap, LoaiThuoc loaiThuoc, float giaBan) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.mota = mota;
        this.dotuoi = dotuoi;
        this.hinhanh = hinhanh;
        this.donvitinh = donvitinh;
        this.donviQuydoi = donviQuydoi;
        this.tileQuydoi = tileQuydoi;
        this.nhacungcap = nhacungcap;
        this.loaiThuoc = loaiThuoc;
        this.giaBan = giaBan;
    }

    


    
    @Override
    public String toString() {
        return ("Thông tin về thuốc là\n Mã thuốc:" + this.maThuoc + "\t Tên thuốc: " + this.tenThuoc + "\t Mota:" + this.mota + "\t Hình ảnh: " + this.hinhanh + "\t Nhà cung cấp" + this.nhacungcap);

    }
    
     

}
