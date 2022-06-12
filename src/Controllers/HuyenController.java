package Controllers;

import DBConnection.DBConnection;
import Models.Huyen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HuyenController {

    public ArrayList<Huyen> getDanhsachHuyen() {
        ArrayList<Huyen> dsHuyen = new ArrayList<>();
        String query = "SELECT * FROM huyen";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maHuyen = rs.getInt("MaHuyen");
                    String tenHuyen = rs.getString("TenHuyen");
                    int maTinh = rs.getInt("MaTinh");
                    TinhController tinhCtrl = new TinhController();

                    dsHuyen.add(new Huyen(maHuyen, tenHuyen, tinhCtrl.getTinh(maTinh)));
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsHuyen;
    }

    public ArrayList<Huyen> getDanhsachHuyenTheoTinh(String tenTinh) {
        ArrayList<Huyen> dsHuyen = new ArrayList<>();
        String query = "select * from huyen join tinh ON huyen.MaTinh = tinh.MaTinh where tinh.TenTinh = N'" + tenTinh + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maHuyen = rs.getInt("MaHuyen");
                    String tenHuyen = rs.getString("TenHuyen");
                    int maTinh = rs.getInt("MaTinh");
                    TinhController tinhCtrl = new TinhController();

                    dsHuyen.add(new Huyen(maHuyen, tenHuyen, tinhCtrl.getTinh(maTinh)));
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsHuyen;
    }

    public Huyen getHuyen(int _mahuyen) {
        Huyen huyen = null;
        String query = "Select * from huyen where MaHuyen = " + _mahuyen + " ";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maHuyen = rs.getInt("MaHuyen");
                    String tenHuyen = rs.getString("TenHuyen");
                    int maTinh = rs.getInt("MaTinh");
                    TinhController tinhCtrl = new TinhController();

                    huyen = new Huyen(maHuyen, tenHuyen, tinhCtrl.getTinh(maTinh));
                }
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
            }
        }
        return huyen;
    }

}
