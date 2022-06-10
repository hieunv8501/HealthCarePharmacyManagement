package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoaiThuoc {
    private int maLoaiThuoc;
    private String tenLoaiThuoc;
    private String mota;
    boolean daXoa;

    public int getMaLoaiThuoc() {
        return maLoaiThuoc;
    }

    public String getTenLoaiThuoc() {
        return tenLoaiThuoc;
    }

    public void setMaLoaiThuoc(int maLoaiThuoc) {
        this.maLoaiThuoc = maLoaiThuoc;
    }

    public void setTenLoaiThuoc(String tenLoaiThuoc) {
        this.tenLoaiThuoc = tenLoaiThuoc;
    }

    public LoaiThuoc(int maLoaiThuoc, String tenLoaiThuoc, String mota, boolean daXoa) {
        this.maLoaiThuoc = maLoaiThuoc;
        this.tenLoaiThuoc = tenLoaiThuoc;
        this.mota = mota;
        this.daXoa = daXoa;
    }

    public LoaiThuoc(int maLoaiThuoc, String tenLoaiThuoc, String mota) {
        this.maLoaiThuoc = maLoaiThuoc;
        this.tenLoaiThuoc = tenLoaiThuoc;
        this.mota = mota;
    }
    
    
    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
    public boolean isDaXoa() {
        return daXoa;
    }
    
    public LoaiThuoc() {
    }
    @Override
    public String toString() {
        return  tenLoaiThuoc ;
    }
    
}
