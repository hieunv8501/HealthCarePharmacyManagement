package Controllers;

import DBConnection.DBConnection;
import Models.Nhacungcap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NhacungcapController {

    public static ArrayList<Nhacungcap> getDanhsachNhacungcap() {
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

    public static Nhacungcap getNhacungcap(int _maNCC) {
        for (var ncc : getDanhSachNhacungcap()) {
            if (ncc.getMaNhacungcap() == _maNCC) {
                return ncc;
            }
        }
        return null;
    }

    public static void themNhacungcap(Nhacungcap nhacungcap) throws Exception {
        String command = "INSERT INTO nhacungcap(MaNhacungcap,TenNhacungcap,MaXa,SoDienThoai,Fax)  values (?,?,?,?,?)";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, nhacungcap.getMaNhacungcap());
            pre.setString(2, nhacungcap.getTenNhacungcap());
            pre.setInt(3, nhacungcap.getXa().getMaXa());
            pre.setString(4, nhacungcap.getSoDienthoai());
            pre.setString(5, nhacungcap.getFax());
            pre.executeUpdate();
            System.out.println("Thêm nhà cung cấp thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Thêm nhà cung cấp không thành công");
        }

    }

    public static void xoaNhacungcap(int maNhacungcap) throws Exception {
        String command = "UPDATE nhacungcap set DaXoa=1 where MaNhacungcap=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, maNhacungcap);
            pre.executeUpdate();
            System.out.println("Xóa nhà cung cấp thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Xóa nhà cung cấp không thành công");
        }
    }

    public static void capnhatNhacungcap(Nhacungcap nhacungcap, int maNhacungcapCu) throws Exception {
        String command = "UPDATE nhacungcap SET MaNhaCungCap=?, TenNhacungcap=?, MaXa=?,SoDienThoai=?,Fax=? where MaNhaCungCap=?";
        try {
            DBConnection con = new DBConnection();
            PreparedStatement pre = con.getConn().prepareStatement(command);
            pre.setInt(1, nhacungcap.getMaNhacungcap());
            pre.setString(2, nhacungcap.getTenNhacungcap());
            pre.setInt(3, nhacungcap.getXa().getMaXa());
            pre.setString(4, nhacungcap.getSoDienthoai());
            pre.setString(5, nhacungcap.getFax());
            pre.setInt(6, maNhacungcapCu);
            pre.executeUpdate();
            System.out.println("Cập nhật nhà cung cấp thành công");
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Cập nhật nhà cung cấp không thành công");
        }
    }

    public static ArrayList<Nhacungcap> timkiemNhacungcap(String userText) {
        ArrayList<Nhacungcap> dsNhacungcap = new ArrayList();
        ArrayList<Nhacungcap> dsNhacungcapTimKiem = new ArrayList();
        dsNhacungcap = getDanhSachNhacungcap();
        dsNhacungcap.forEach(nhacungcap -> {
            String c = String.valueOf(nhacungcap.getMaNhacungcap());
            if (c.toLowerCase().startsWith(userText)) {
                dsNhacungcapTimKiem.add(nhacungcap);
            } else {
                if (nhacungcap.getTenNhacungcap().toLowerCase().contains(userText)) {
                    dsNhacungcapTimKiem.add(nhacungcap);
                } else {
                    if (nhacungcap.getSoDienthoai().toLowerCase().startsWith(userText)) {
                        dsNhacungcapTimKiem.add(nhacungcap);
                    } else {
                        if (nhacungcap.getFax().toLowerCase().startsWith(userText)) {
                            dsNhacungcapTimKiem.add(nhacungcap);
                        }

                    }
                }

            }
        }
        );
        return dsNhacungcapTimKiem;

    }

    public static ArrayList<Nhacungcap> getDanhSachNhacungcap() {
        Nhacungcap c = new Nhacungcap();
        return c.readDB();
    }
}
