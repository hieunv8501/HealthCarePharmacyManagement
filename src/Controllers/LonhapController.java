package Controllers;
import DBConnection.DBConnection;
import Models.LoNhap;
import java.sql.ResultSet;

public class LonhapController {
    
        public LoNhap layLoNhap(int maLo) {

        String query = "SELECT * FROM lonhap Where DaXoa = 0 AND MaLo = '" + maLo + "'";
        DBConnection con = new DBConnection();
        try {
            ResultSet rs = con.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int soluongConlai = rs.getInt("SoLuongConLai");
                    LoNhap ln = new LoNhap();
                    ln.setSoluongConlai(soluongConlai);
                    ln.setMaLo(maLo);
                    return ln;
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
