package Controllers;

import DBConnection.DBConnection;
import Models.Xa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XaController {

    public ArrayList<Xa> getDanhsachXa() {
        ArrayList<Xa> dsXa = new ArrayList<>();
        String query = "SELECT * FROM xa";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maXa = rs.getInt("MaXa");
                    String tenXa = rs.getString("TenXa");
                    int maHuyen = rs.getInt("MaHuyen");
                    HuyenController huyenCtrl = new HuyenController();

                    dsXa.add(new Xa(maXa, tenXa, huyenCtrl.getHuyen(maHuyen)));
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsXa;
    }

    public ArrayList<Xa> getDanhsachXaTheoHuyen(String tenHuyen) {
        ArrayList<Xa> dsXa = new ArrayList<>();
        String query = "select * from xa join huyen on xa.MaHuyen = huyen.MaHuyen where huyen.TenHuyen = N'" + tenHuyen + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maXa = rs.getInt("MaXa");
                    String tenXa = rs.getString("TenXa");
                    int maHuyen = rs.getInt("MaHuyen");
                    HuyenController huyenCtrl = new HuyenController();

                    dsXa.add(new Xa(maXa, tenXa, huyenCtrl.getHuyen(maHuyen)));
                }
            }
        } catch (SQLException e) {
        } finally {
            con.closeConnection();
        }
        return dsXa;
    }

    public Xa getXa(int _maxa) {
        Xa xa = null;
        String query = "Select * from xa where MaXa = " + _maxa + " ";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maXa = rs.getInt("MaXa");
                    String tenXa = rs.getString("TenXa");
                    int maHuyen = rs.getInt("MaHuyen");
                    HuyenController huyenCtrl = new HuyenController();
                    xa = new Xa(maXa, tenXa, huyenCtrl.getHuyen(maHuyen));
                }
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
            }
        }
        return xa;
    }
    
    public Xa getXa(String _tenXa) {
        Xa xa = null;
        String query = "Select * from xa where TenXa = N'" + _tenXa + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maXa = rs.getInt("MaXa");
                    String tenXa = rs.getString("TenXa");
                    int maHuyen = rs.getInt("MaHuyen");
                    HuyenController huyenCtrl = new HuyenController();
                    xa = new Xa(maXa, tenXa, huyenCtrl.getHuyen(maHuyen));
                }
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
            }
        }
        return xa;
    }
}
