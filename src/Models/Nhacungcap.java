package Models;

import Controllers.XaController;
import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Nhacungcap {

    private int maNhacungcap;
    private String tenNhacungcap;
    private Xa xa;
    private String soDienthoai;
    private String fax;
    private boolean DaXoa;
    DBConnection nhacungcapConnection;
    //Phuong thuc 

    public Nhacungcap(int maNhacungcap, String tenNhacungcap, Xa xa, String soDienthoai, String fax, boolean DaXoa) {
        this.maNhacungcap = maNhacungcap;
        this.tenNhacungcap = tenNhacungcap;
        this.xa = xa;
        this.soDienthoai = soDienthoai;
        this.fax = fax;
        this.DaXoa = DaXoa;
    }

    public Nhacungcap(int maNhacungcap, String tenNhacungcap, Xa xa, String soDienthoai, String fax) {
        this.maNhacungcap = maNhacungcap;
        this.tenNhacungcap = tenNhacungcap;
        this.xa = xa;
        this.soDienthoai = soDienthoai;
        this.fax = fax;
    }
    public Nhacungcap() {
    }
    
    public int getMaNhacungcap() {
        return maNhacungcap;
    }

    public void setMaNhacungcap(int maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public String getTenNhacungcap() {
        return tenNhacungcap;
    }

    public void setTenNhacungcap(String tenNhacungcap) {
        this.tenNhacungcap = tenNhacungcap;
    }

    public Xa getXa() {
        return xa;
    }

    public void setXa(Xa xa) {
        this.xa = xa;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    public ArrayList<Nhacungcap> readDB() {
        ArrayList<Nhacungcap> dsNhacungcap = new ArrayList();
        nhacungcapConnection = new DBConnection();
        try {
            String query = "SELECT * FROM nhacungcap";
            ResultSet rs = nhacungcapConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maNhacungcap = rs.getInt("MaNhaCungCap");
                    String tenNhacungcap= rs.getString("TenNhaCungCap");
                    XaController xaController=new XaController();
                    int maXa=rs.getInt("MaXa");
                    Xa xaNhacungcap= xaController.getXa(maXa);
                    String sodienthoai= rs.getString("SoDienThoai");
                    String fax =rs.getString("Fax");                                
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsNhacungcap.add(new Nhacungcap(maNhacungcap,tenNhacungcap,xaNhacungcap,sodienthoai,fax,daXoa));
                }
            }          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng thuốc");
        } finally {
            nhacungcapConnection.closeConnection();
        }
        return dsNhacungcap;
    }

    @Override
    public String toString() {
        return tenNhacungcap;
    }
}
