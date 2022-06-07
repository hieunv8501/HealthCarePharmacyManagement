package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoaiThuoc {
    private int maLoaiThuoc;
    private String tenLoaiThuoc;
    boolean daXoa;
    DBConnection loaiThuocConnection;

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

    public LoaiThuoc(int maLoaiThuoc, String tenLoaiThuoc) {
        this.maLoaiThuoc = maLoaiThuoc;
        this.tenLoaiThuoc = tenLoaiThuoc;
    }

    public LoaiThuoc(int maLoaiThuoc, String tenLoaiThuoc, boolean daXoa) {
        this.maLoaiThuoc = maLoaiThuoc;
        this.tenLoaiThuoc = tenLoaiThuoc;
        this.daXoa = daXoa;
    }

    public boolean isDaXoa() {
        return daXoa;
    }
    
    public LoaiThuoc() {
    }
     public ArrayList<LoaiThuoc> readDB() {
        ArrayList<LoaiThuoc> dsLoaiThuoc = new ArrayList();
        loaiThuocConnection = new DBConnection();
        try {
            String query = "SELECT * FROM loaithuoc";
            ResultSet rs = loaiThuocConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maLoaiThuoc = rs.getInt("MaLoaiThuoc");
                    String tenLoaiThuoc= rs.getString("TenLoaiThuoc");                                                  
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsLoaiThuoc.add(new LoaiThuoc(maLoaiThuoc,tenLoaiThuoc,daXoa));
                }
            }          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng thuốc");
        } finally {
            loaiThuocConnection.closeConnection();
        }
        return dsLoaiThuoc;
    }

    @Override
    public String toString() {
        return  tenLoaiThuoc ;
    }
    
}
