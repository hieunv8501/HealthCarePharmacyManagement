package Controllers;

import DBConnection.DBConnection;
import Models.Donvitinh;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DonvitinhController {
    private ArrayList<Donvitinh> dsDonvitinhs=new ArrayList<>();
   public Donvitinh getDonvitinh(int _maDonvitinh) {
        for(Donvitinh donvitinh:getDanhSachDonvitinh())
            if(donvitinh.getMaDonvitinh()==_maDonvitinh)
            {

                return donvitinh;
            }
        }
        return null;
    }

    public static void themDonvitinh(Donvitinh donvitinh) {
        String command = "INSERT INTO donvitinh(MaDonvitinh,TenDonvitinh,GiaTri)  values (?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, donvitinh.getMaDonvitinh());
            pre.setString(2, donvitinh.getTenDonvitinh());
            pre.setString(3, donvitinh.getGiatri());
            pre.executeUpdate();
            System.out.println("Thêm đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void xoaDonvitinh(int maDonvitinh) {
        String command = "UPDATE donvitinh set DaXoa=1 where MaDonvitinh=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, maDonvitinh);
            pre.executeUpdate();
            System.out.println("Xóa đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void capnhatDonvitinh(Donvitinh donvitinh, int maDonvitinhCu) {
        String command = "UPDATE donvitinh SET MaDonViTinh=?, TenDonvitinh=?, GiaTri=? WHERE MaDonViTinh=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, donvitinh.getMaDonvitinh());
            pre.setString(2, donvitinh.getTenDonvitinh());
            pre.setString(3, donvitinh.getGiatri());
            pre.setInt(4, maDonvitinhCu);
            pre.executeUpdate();
            System.out.println("Cập nhật đơn vị tính thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  ArrayList<Donvitinh> getDanhSachDonvitinh()
    {
          ArrayList<Donvitinh> dsDonvitinh = new ArrayList();
        DBConnection DonvitinhConnection = new DBConnection();
        try {
            String query = "SELECT * FROM donvitinh";
            ResultSet rs = DonvitinhConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maDonvitinh = rs.getInt("MaDonViTinh");
                    String tenDonvitinh= rs.getString("TenDonViTinh");
                    String giatri=rs.getString("GiaTri"); 
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsDonvitinh.add(new Donvitinh(maDonvitinh,tenDonvitinh,giatri,daXoa));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng đơn vị tính");
        } finally {
            DonvitinhConnection.closeConnection();
        }
        return dsDonvitinh;

    }
}
