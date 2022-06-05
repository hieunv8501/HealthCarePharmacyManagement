package Controllers;

import DBConnection.DBConnection;
import Models.Nhacungcap;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NhacungcapController {
    
    public ArrayList<Nhacungcap> getDanhsachNhacungcap() {
        ArrayList<Nhacungcap> dsNcc = new ArrayList<>();
        String sqlCommmand = "SELECT * FROM nhacungcap Where nhacungcap.DaXoa = 0;";
        DBConnection conn = new DBConnection();
        try {
            ResultSet rs = conn.sqlQuery(sqlCommmand);
            if (rs != null) {
                while (rs.next()) {
                    int maNCC = rs.getInt("MaNhaCungCap");
                    String tenNCC = rs.getString("TenNhaCungCap");
                    int maxa = rs.getInt("MaXa");
                    XaController xaCtrl = new XaController();
                    String fax = rs.getString("Fax");               
                    String sdt = rs.getString("SoDienThoai");               
                    boolean daXoa = rs.getBoolean("DaXoa");                 
                    dsNcc.add(new Nhacungcap(maNCC, tenNCC, xaCtrl.getXa(maxa), sdt, fax, daXoa));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return dsNcc;
    }
    public Nhacungcap getNhacungcap(int _maNCC) {
        for (var ncc : getDanhsachNhacungcap()) {
            if (ncc.getMaNhacungcap() == _maNCC) {
                return ncc;
            }
        }
        return null;
    }
}
