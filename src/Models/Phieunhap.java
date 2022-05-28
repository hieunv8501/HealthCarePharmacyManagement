package Models;

import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Phieunhap {

    int maPhieunhap;
    int maNhacungcap;
    int maNhanvien;
    Calendar ngayNhap;
    float tongTien;
    boolean daXoa;
    DBConnection connection;

    public Phieunhap() {
    }

    public Phieunhap(int maPhieunhap, int maNhacungcap, int maNhanvien, Calendar ngayNhap, float tongTien, boolean daXoa) {
        this.maPhieunhap = maPhieunhap;
        this.maNhacungcap = maNhacungcap;
        this.maNhanvien = maNhanvien;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.daXoa = daXoa;
    }

    public int getMaPhieunhap() {
        return maPhieunhap;
    }

    public void setMaPhieunhap(int maPhieunhap) {
        this.maPhieunhap = maPhieunhap;
    }

    public int getMaNhacungcap() {
        return maNhacungcap;
    }

    public void setMaNhacungcap(int maNhacungcap) {
        this.maNhacungcap = maNhacungcap;
    }

    public int getMaNhanvien() {
        return maNhanvien;
    }

    public void setMaNhanvien(int maNhanvien) {
        this.maNhanvien = maNhanvien;
    }

    public Calendar getNgaynhap() {
        return ngayNhap;
    }

    public void setNgaynhap(Calendar ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDaxoa() {
        return daXoa;
    }

    public void setDaxoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public Date calendarToDate(Calendar calendar) {
	return calendar.getTime();
    }  
    
    public ArrayList readDB() {
        connection = new DBConnection();
        ArrayList<Phieunhap> dspn = new ArrayList<Phieunhap>();
        try {
            String query = "SELECT * FROM phieunhap";
            ResultSet rs = connection.sqlQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    Phieunhap pn = new Phieunhap();
                    pn.setMaPhieunhap(rs.getInt(1));
                    pn.setMaNhacungcap(rs.getInt(2));
                    pn.setMaNhanvien(rs.getInt(3));
                    pn.setNgaynhap(dateToCalendar(rs.getDate(4)));
                    pn.setTongTien(rs.getFloat(5));                    
                    pn.setDaxoa(rs.getBoolean(6));
                    dspn.add(pn);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập nào!!");
        } finally {
            connection.closeConnection();
        }
        return dspn;
    }

    public Boolean add(Phieunhap pn) {
        connection = new DBConnection();
        Boolean ok = connection.sqlUpdate("INSERT INTO phieunhap(MaNhaCungCap, MaNhanVien, NgayNhap, TongTien) VALUES ('"
                + pn.getMaNhacungcap()+ "','"
                + pn.getMaNhanvien()+ "','"
                + pn.getNgaynhap()+ "','"
                + pn.getTongTien() + "');");
        connection.closeConnection();
        return ok;
    }
    
    public Boolean update(Phieunhap pn) {
        connection = new DBConnection();
        Boolean ok = connection.sqlUpdate("UPDATE phieunhap SET MaNhaCungCap = '" + pn.getMaNhacungcap() + "', MaNhanVien = '" + pn.getMaNhanvien() + "', NgayNhap = '" + pn.getNgaynhap()+ "', TongTien = '" + pn.getTongTien() + "', DaXoa =' " + pn.isDaxoa() +"' WHERE MaPhieuNhap = '" + pn.getMaPhieunhap() + "';");
        connection.closeConnection();
        return ok;
    }
    
    public Boolean softDelete(Phieunhap pn) {
        connection = new DBConnection();
        Boolean ok = connection.sqlUpdate("UPDATE phieunhap SET DaXoa = '" +pn.isDaxoa()+"' WHERE MaPhieuNhap = '" +pn.getMaPhieunhap()+ "';");
        connection.closeConnection();
        return ok;
    }

    public Boolean delete(int mapn) {
        connection = new DBConnection();
        if (!connection.sqlUpdate("DELETE FROM phieunhap WHERE MaPhieuNhap ='" + mapn + "';")) {
            JOptionPane.showMessageDialog(null, "Vui lòng xóa hết các chi tiết phiếu nhập !!!");
            connection.closeConnection();
            return false;
        }
        connection.closeConnection();
        return false;
    }

    public Boolean updateTongTien(int _mapn, float _tongTien) {
        connection = new DBConnection();
        Boolean ok = connection.sqlUpdate("UPDATE phieunhap SET TongTien = '" + _tongTien + "' WHERE MaPhieuNhap='" + _mapn + "';");
        connection.closeConnection();
        return ok;
    }
    
}

