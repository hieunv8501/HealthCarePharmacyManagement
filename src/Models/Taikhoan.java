package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Taikhoan {
    String taikhoan, matkhau, maQuyen;
    int maNhanvien;
    DBConnection taikhoanConnection;
    
    public Taikhoan(){}
    
    public Taikhoan(String taikhoan, String matkhau, int maNhanvien, String maQuyen) {
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

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    
    public ArrayList<Taikhoan> readDB() {
        taikhoanConnection = new DBConnection();
        ArrayList<Taikhoan> dstk = new ArrayList<>();
        try {
            String qry = "SELECT * FROM taikhoan";
            ResultSet rs = taikhoanConnection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    String ten = rs.getString("TenTaiKhoan");
                    String pass = rs.getString("MatKhau");
                    int manv = rs.getInt("MaNhanVien");
                    String maquyen = rs.getString("MaQuyen");                  
                    dstk.add(new Taikhoan(ten, pass, manv, maquyen));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
        } finally {
            taikhoanConnection.closeConnection();
        }
        return dstk;
    }

    public Boolean add(Taikhoan tk) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("INSERT INTO taikhoan (TenTaiKhoan, MatKhau, MaNhanVien, MaQuyen) VALUES ('"
                + tk.getTaikhoan()+ "', '" + tk.getMatkhau()+ "', '" + tk.getMaNhanvien()+ "', '" + tk.getMaQuyen() + "');");
        taikhoanConnection.closeConnection();
        return ok;
    }

    public Boolean delete(String username) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("DELETE FROM taikhoan WHERE taikhoan.TenTaiKhoan = '" + username + "'");
        taikhoanConnection.closeConnection();
        return ok;
    }

    public Boolean update(String username, String pass, int maNhanVien, String maQuyen) {
        taikhoanConnection = new DBConnection();
        Boolean ok = taikhoanConnection.sqlUpdate("UPDATE taikhoan SET MatKhau='" + pass + "',MaNV='" + maNhanVien
                + "',MaQuyen='" + maQuyen + "' WHERE TenTaiKhoan='" + username + "'");
        taikhoanConnection.closeConnection();
        return ok;
    }

    public void close() {
        taikhoanConnection.closeConnection();
    }
}
