package Controllers;

import DBConnection.DBConnection;
import Models.Xa;
import java.sql.ResultSet;
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
            }
        }
        return xa;
    }
}
