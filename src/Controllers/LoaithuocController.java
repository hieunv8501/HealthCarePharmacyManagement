package Controllers;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.LoaiThuoc;
import java.util.ArrayList;

public class LoaithuocController {
    
    public static void themLoaiThuoc(LoaiThuoc loaiThuoc)
    {
        String command="INSERT INTO loaithuoc(MaLoaiThuoc,TenLoaiThuoc)  values (?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, loaiThuoc.getMaLoaiThuoc());
            pre.setString(2, loaiThuoc.getTenLoaiThuoc());
            pre.executeUpdate();
            System.out.println("Thêm mã loại thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void xoaLoaiThuoc(int maLoaiThuoc)
    {
       String command="UPDATE loaithuoc set DaXoa=1 where MaLoaiThuoc=?";
       try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, maLoaiThuoc);
        
            pre.executeUpdate();
            System.out.println("Xóa mã loại thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void capnhatLoaiThuoc(LoaiThuoc loaiThuoc,int maLoaiThuocCu)
    {
        String command="UPDATE loaithuoc SET MaLoaiThuoc=?, TenLoaiThuoc=? WHERE MaLoaiThuoc=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, loaiThuoc.getMaLoaiThuoc());
            pre.setString(2, loaiThuoc.getTenLoaiThuoc());
            pre.setInt(3, maLoaiThuocCu);
            pre.executeUpdate();
            System.out.println("Cập nhật mã loại thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<LoaiThuoc> getDanhSachLoaiThuoc()
    {
        LoaiThuoc LoaiThuocDatabase=new LoaiThuoc();
        return LoaiThuocDatabase.readDB();
    }
}
