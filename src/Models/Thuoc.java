package Models;

import Controllers.DonvitinhController;
import Controllers.NhacungcapController;
import DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Thuoc {

    private int maThuoc;
    private String tenThuoc;
    private String mota;
    private String dotuoi;
    private String hinhanh;
    private Donvitinh donvitinh;
    private Donvitinh donviQuydoi;
    private int tileQuydoi;
    private Nhacungcap nhacungcap;
    private LoaiThuoc loaiThuoc;
    private float giaBan;
    private boolean DaXoa;
    DBConnection thuocConnection;
    //Phuong thuc cua Thuoc

    public Thuoc() {
    
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setNhacungcap(Nhacungcap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public void setDonvitinh(Donvitinh donvitinh) {
        this.donvitinh = donvitinh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getDotuoi() {
        return dotuoi;
    }

    public void setDotuoi(String dotuoi) {
        this.dotuoi = dotuoi;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public String getMota() {
        return mota;
    }

    public Nhacungcap getNhacungcap() {
        return nhacungcap;
    }

    public Donvitinh getDonvitinh() {
        return donvitinh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public boolean isDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(boolean DaXoa) {
        this.DaXoa = DaXoa;
    }

    public Donvitinh getDonviQuydoi() {
        return donviQuydoi;
    }

    public int getTileQuydoi() {
        return tileQuydoi;
    }

    public DBConnection getThuocConnection() {
        return thuocConnection;
    }

    public void setDonviQuydoi(Donvitinh donviQuydoi) {
        this.donviQuydoi = donviQuydoi;
    }

    public void setTileQuydoi(int tileQuydoi) {
        this.tileQuydoi = tileQuydoi;
    }

    public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public LoaiThuoc getLoaiThuoc() {
        return loaiThuoc;
    }

    public Thuoc(int maThuoc, String tenThuoc, String mota, String dotuoi, String hinhanh, Donvitinh donvitinh, Donvitinh donviQuydoi, int tileQuydoi, Nhacungcap nhacungcap, LoaiThuoc loaiThuoc, float giaBan, boolean DaXoa) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.mota = mota;
        this.dotuoi = dotuoi;
        this.hinhanh = hinhanh;
        this.donvitinh = donvitinh;
        this.donviQuydoi = donviQuydoi;
        this.tileQuydoi = tileQuydoi;
        this.nhacungcap = nhacungcap;
        this.loaiThuoc = loaiThuoc;
        this.giaBan = giaBan;
        this.DaXoa = DaXoa;
    }

    public Thuoc(int maThuoc, String tenThuoc, String mota, String dotuoi, String hinhanh, Donvitinh donvitinh, Donvitinh donviQuydoi, int tileQuydoi, Nhacungcap nhacungcap, LoaiThuoc loaiThuoc, float giaBan) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.mota = mota;
        this.dotuoi = dotuoi;
        this.hinhanh = hinhanh;
        this.donvitinh = donvitinh;
        this.donviQuydoi = donviQuydoi;
        this.tileQuydoi = tileQuydoi;
        this.nhacungcap = nhacungcap;
        this.loaiThuoc = loaiThuoc;
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return ("Thông tin về thuốc là\n Mã thuốc:" + this.maThuoc + "\t Tên thuốc: " + this.tenThuoc + "\t Mota:" + this.mota + "\t Hình ảnh: " + this.hinhanh + "\t Nhà cung cấp" + this.nhacungcap);

    }
    public ArrayList<Thuoc> readDB() {
        ArrayList<Thuoc> dsThuoc = new ArrayList();
        thuocConnection = new DBConnection();
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
     public void close() {
        thuocConnection.closeConnection();
    }

}
