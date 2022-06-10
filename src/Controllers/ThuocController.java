package Controllers;

import Models.Thuoc;
import DBConnection.DBConnection;

import Models.Donvitinh;
import Models.LoaiThuoc;
import Models.Nhacungcap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ThuocController {
    private static ArrayList<Thuoc> dsThuoc = new ArrayList();
    public static void themThuoc(Thuoc thuoc)
    {
  
        String command="insert into thuoc(MaThuoc,TenThuoc,Mota,DoTuoi,HinhAnh,MaDonViTinh,MaDonViQuiDoi,TiLeQuiDoi,MaNhaCungCap,MaLoaiThuoc,GiaBan) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, thuoc.getMaThuoc());

            pre.setString(2, thuoc.getTenThuoc());
            pre.setString(3, thuoc.getMota());
            pre.setString(4, thuoc.getDotuoi());
            pre.setString(5, thuoc.getHinhanh());
            pre.setInt(6, thuoc.getDonvitinh().getMaDonvitinh());
            pre.setInt(7, thuoc.getDonviQuydoi().getMaDonvitinh());
            pre.setInt(8, thuoc.getTileQuydoi());
            pre.setInt(9, thuoc.getNhacungcap().getMaNhacungcap());
            pre.setInt(10, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            pre.setFloat(11, thuoc.getGiaBan());
            pre.executeUpdate();
            System.out.println("Thêm mã thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void timTatCaThuoc() {

    }

    public static void capnhatThuoc(Thuoc thuoc, int maThuocCu) {
        String command = "Update thuoc set MaThuoc=?,TenThuoc=?,Mota=?,DoTuoi=?,HinhAnh=?,MaDonViTinh=?,MaDonViQuiDoi=?,TiLeQuiDoi=?,MaNhaCungCap=?,MaLoaiThuoc=?,GiaBan=? where MaThuoc=? ";

        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, thuoc.getMaThuoc());
            pre.setString(2, thuoc.getTenThuoc());
            pre.setString(3, thuoc.getMota());
            pre.setString(4, thuoc.getDotuoi());
            pre.setString(5, thuoc.getHinhanh());
            pre.setInt(6, thuoc.getDonvitinh().getMaDonvitinh());
            pre.setInt(7, thuoc.getDonviQuydoi().getMaDonvitinh());
            pre.setInt(8, thuoc.getTileQuydoi());
            pre.setInt(9, thuoc.getNhacungcap().getMaNhacungcap());
            pre.setInt(10, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            pre.setFloat(11, thuoc.getGiaBan());
            pre.setInt(12, maThuocCu);
            pre.executeUpdate();
            System.out.println("Cập nhật thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void xoaThuoc(int maThuoc) {
        String command = "UPDATE thuoc SET DaXoa = 1 WHERE MaThuoc = ?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);

            pre.setInt(1, maThuoc);
            pre.executeUpdate();
            System.out.println("Xóa thuốc thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Thuoc> getDanhSachThuoc()
    {
        dsThuoc=new ArrayList<>();
        DBConnection thuocConnection = new DBConnection();
        try {
            String query = "SELECT * FROM thuoc";
            ResultSet rs = thuocConnection.sqlQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    int maThuoc = rs.getInt("MaThuoc");
                    String tenThuoc= rs.getString("TenThuoc");
                    String mota=rs.getString("MoTa");
                    String dotuoi= rs.getString("DoTuoi");
                    int maNhacungcap =rs.getInt("MaNhaCungCap");
                    NhacungcapController nhacungcapController=new NhacungcapController();
                    Nhacungcap nhacungcap=nhacungcapController.getNhacungcap(maNhacungcap);
                    int maDonvitinh=rs.getInt("MaDonViTinh");
                    DonvitinhController donvitinhController=new DonvitinhController();
                    Donvitinh donvitinh=donvitinhController.getDonvitinh(maDonvitinh);
                    String hinhanh=rs.getString("HinhAnh"); 
                    int maloaithuoc=rs.getInt("MaLoaiThuoc");
                    LoaiThuocController loaiThuocController=new LoaiThuocController();
                    LoaiThuoc loaiThuoc=loaiThuocController.getLoaiThuoc(maloaithuoc);
                    String giabanString=rs.getString("GiaBan");
                    float giaban=Float.parseFloat(giabanString);
                    int maDonviQuidoi=rs.getInt("MaDonViQuiDoi");
                    Donvitinh donviQuidoi=donvitinhController.getDonvitinh(maDonviQuidoi);
                    int tilequydoi=rs.getInt("TiLeQuiDoi");
                    boolean daXoa= (rs.getInt("DaXoa")==1)? true:false;
                    dsThuoc.add(new Thuoc(maThuoc,tenThuoc,mota,dotuoi,hinhanh,donvitinh,donviQuidoi,tilequydoi,nhacungcap,loaiThuoc,giaban,daXoa));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng thuốc");
        } finally {
            thuocConnection.closeConnection();
        }
        return dsThuoc;
    }
}
