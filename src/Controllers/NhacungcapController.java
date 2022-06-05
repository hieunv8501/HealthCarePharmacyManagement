package Controllers;

import DBConnection.DBConnection;
import Models.Nhacungcap;
import Models.Xa;
import java.sql.ResultSet;

public class NhacungcapController {
    
    public Nhacungcap getNhacungcap(int _maNCC) {

        String sqlCommmand = "SELECT * FROM nhacungcap,xa Where thuoc.DaXoa = 0 AND MaThuoc = '" + _maNCC + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(sqlCommmand);
            if (rs != null) {
                while (rs.next()) {
                    Nhacungcap ncc = new Nhacungcap();
                    String maNCC = rs.getString("MaNhaCungCap");
                    String tenNCC = rs.getString("TenNhaCungCap");
                    String fax = rs.getString("Fax");               
                    String sdt = rs.getString("SoDienThoai");               
                    boolean daxoa = rs.getBoolean("DaXoa");                 
                    //ncc.add(new Nhacungcap(maNCC, tenNCC, sdt, fax, daxoa));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
        }
        return null;
    }
}
