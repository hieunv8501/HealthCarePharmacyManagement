package Controllers;

import DBConnection.DBConnection;
import Models.Huyen;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HuyenController {

    public static ArrayList<Huyen> getDanhsachHuyen() {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsHuyen;
    }
    
    public static Huyen getHuyen(int _mahuyen) {
        for (var huyen : getDanhsachHuyen()) {
            if (huyen.getMaHuyen() == _mahuyen) {
                return huyen;
            }
        }
        return null;
    }
    
}
