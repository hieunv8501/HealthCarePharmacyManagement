package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Quyen {
    String maQuyen, tenQuyen, chitietQuyen;
    boolean daXoa;
    DBConnection quyenDBConnection;

    public Quyen() {
    }
    
    public Quyen(String maQuyen, String tenQuyen, String chitietQuyen) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.chitietQuyen = chitietQuyen;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getChitietQuyen() {
        return chitietQuyen;
    }

    public void setChitietQuyen(String chitietQuyen) {
        this.chitietQuyen = chitietQuyen;
    }
    
    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public ArrayList<Quyen> readDB() {
        quyenDBConnection = new DBConnection();
        ArrayList<Quyen> dsq = new ArrayList<>();
        try {
            String qry = "SELECT * FROM phanquyen";
            ResultSet r = quyenDBConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    String chitietq = r.getString("ChiTietQuyen");
                    
                    dsq.add(new Quyen(maq, tenq, chitietq));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng phân quyền");
        } finally {
            quyenDBConnection.closeConnection();
        }
        return dsq;
    }
    public ArrayList<Quyen> search(String columnName, String value) {
        quyenDBConnection = new DBConnection();
        ArrayList<Quyen> dsq = new ArrayList<>();

        try {
            String qry = "SELECT * FROM phanquyen WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = quyenDBConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    String chitietq = r.getString("ChiTietQuyen");
                    
                    dsq.add(new Quyen(maq, tenq, chitietq));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng phân quyền");
        } finally {
            quyenDBConnection.closeConnection();
        }

        return dsq;
    }
    
    public Boolean add(Quyen q) {
        quyenDBConnection = new DBConnection();
        Boolean ok = quyenDBConnection.sqlUpdate("INSERT INTO phanquyen (MaQuyen, TenQuyen, ChiTietQuyen) VALUES ('"
                + q.getMaQuyen()+ "', '" 
                + q.getTenQuyen()+ "', '" 
                + q.getChitietQuyen()+ "');");
        quyenDBConnection.closeConnection();
        return ok;
    }

    public Boolean delete(String maq) {
        quyenDBConnection = new DBConnection();
        Boolean ok = quyenDBConnection.sqlUpdate("DELETE FROM phanquyen WHERE phanquyen.MaQuyen = '" + maq + "'");
        quyenDBConnection.closeConnection();
        return ok;
    }

    public Boolean update(String maq, String tenquyen, String chitietquyen) {
        quyenDBConnection = new DBConnection();
        Boolean ok = quyenDBConnection.sqlUpdate("UPDATE phanquyen SET "
                + "TenQuyen='" + tenquyen 
                + "',ChiTietQuyen='" + chitietquyen 
                + "' where MaQuyen='" + maq + "';");
        quyenDBConnection.closeConnection();
        return ok;
    }

    public void close() {
        quyenDBConnection.closeConnection();
    }
}
