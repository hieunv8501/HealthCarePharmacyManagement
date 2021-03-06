package Controllers;

import DBConnection.DBConnection;
import Models.Huyen;
import Models.Tinh;
import Models.Xa;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiaChiController {

    public static Xa layDoiTuongXa(int maXa) {
        Xa xa = null;
        String query = "select xa.MaXa, xa.TenXa, huyen.MaHuyen, huyen.TenHuyen, tinh.MaTinh, tinh.TenTinh from (xa JOIN huyen on xa.MaHuyen = huyen.MaHuyen) join tinh on huyen.MaTinh = tinh.MaTinh where xa.MaXa = '" + maXa + "'";
        DBConnection conn = new DBConnection();
        try {
            ResultSet rs = conn.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    String tenXa = rs.getString("TenXa");
                    int maHuyen = rs.getInt("MaHuyen");
                    String tenHuyen = rs.getString("TenHuyen");
                    int maTinh = rs.getInt("MaTinh");
                    String tenTinh = rs.getString("TenTinh");
                    Tinh tinh = new Tinh(maTinh, tenTinh);
                    Huyen huyen = new Huyen(maHuyen, tenHuyen, tinh);
                    xa = new Xa(maXa, tenXa, huyen);
                }
            }
        } catch (SQLException e) {
        } finally {
            conn.closeConnection();
        }
        return xa;
    }
}
