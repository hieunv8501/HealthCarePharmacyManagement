package Controllers;

import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import Models.LoaiThuoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LoaiThuocController {
   // private static  ArrayList<LoaiThuoc> dsLoaiThuocs=null;
    public static  void themLoaiThuoc(LoaiThuoc loaiThuoc)
    {
        String command="INSERT INTO loaithuoc(MaLoaiThuoc,TenLoaiThuoc,MoTa)  values (?,?,?)";

        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, loaiThuoc.getMaLoaiThuoc());
            pre.setString(2, loaiThuoc.getTenLoaiThuoc());
            pre.setString(3, loaiThuoc.getMota());
            pre.executeUpdate();
            System.out.println("Thêm mã loại thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void xoaLoaiThuoc(int maLoaiThuoc) {
        String command = "UPDATE loaithuoc set DaXoa=1 where MaLoaiThuoc=?";
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
        String command="UPDATE loaithuoc SET MaLoaiThuoc=?, TenLoaiThuoc=?, MoTa=? WHERE MaLoaiThuoc=?";
        DBConnection con = new DBConnection();

        try {
            
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, loaiThuoc.getMaLoaiThuoc());
            pre.setString(2, loaiThuoc.getTenLoaiThuoc());
            pre.setString(3, loaiThuoc.getMota());
            pre.setInt(3, maLoaiThuocCu);
            pre.executeUpdate();
            System.out.println("Cập nhật mã loại thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            con.closeConnection();
        }
    }
    public static ArrayList<LoaiThuoc> getDanhSachLoaiThuoc()
    {
        ArrayList<LoaiThuoc> dsLoaiThuocs = new ArrayList();
        DBConnection LoaiThuocConnection = new DBConnection();
        try {
            String query = "SELECT * FROM loaithuoc";
            ResultSet rs = LoaiThuocConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maLoaiThuoc = rs.getInt("MaLoaiThuoc");
                    String tenLoaiThuoc= rs.getString("TenLoaiThuoc");   
                    String mota= rs.getString("MoTa");
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsLoaiThuocs.add(new LoaiThuoc(maLoaiThuoc,tenLoaiThuoc,mota,daXoa));
                }
            }          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng thuốc");
        } finally {
            LoaiThuocConnection.closeConnection();
        }
        return dsLoaiThuocs;
    }
    public LoaiThuoc getLoaiThuoc(int maLoaiThuoc)
    {
        for(LoaiThuoc loaiThuoc:getDanhSachLoaiThuoc())
        {
            if(loaiThuoc.getMaLoaiThuoc()==maLoaiThuoc)
            {
                return loaiThuoc;
            }
        }
            return null;
        

    }
}
