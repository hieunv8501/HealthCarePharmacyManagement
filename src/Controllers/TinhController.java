package Controllers;

import DBConnection.DBConnection;
import Models.Tinh;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TinhController {

    public ArrayList<Tinh> getDanhsachTinh() {
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
            try {
                con.closeConnection();
            } catch (Exception e) {
            }
        }
        return dsTinh;
    }

    public Tinh getTinh(int _maTinh) {
        Tinh tinh = null;
        String query = "Select * from tinh where MaTinh = " + _maTinh + " ";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maTinh = rs.getInt("MaTinh");
                    String tenTinh = rs.getString("TenTinh");
                    tinh = new Tinh(maTinh, tenTinh);
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
        return tinh;
    }

}
