package Controllers;

import DBConnection.DBConnection;
import Models.Tinh;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TinhController {

    public static ArrayList<Tinh> getDanhsachTinh() {
        ArrayList<Tinh> dsTinh = new ArrayList<>();
        String query = "SELECT * FROM tinh";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maTinh = rs.getInt("MaTinh");
                    String tenTinh = rs.getString("TenTinh");
                    dsTinh.add(new Tinh(maTinh, tenTinh));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return dsTinh;
    }
    
    public static Tinh getTinh(int _maTinh) {
        for (var tinh : getDanhsachTinh()) {
            if (tinh.getMaTinh() == _maTinh) {
                return tinh;
            }
        }
        return null;
    }
    
}
