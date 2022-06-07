package Controllers;

import Models.Taikhoan;
import java.util.ArrayList;

public class TaikhoanController {

    private static ArrayList<Taikhoan> dstk = new ArrayList<Taikhoan>();
    Taikhoan taikhoanDA = new Taikhoan();

    public TaikhoanController() {
        dstk = taikhoanDA.readDB();
    }
    
    public String[] getHeaders() {
        return new String[]{"Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"};
    }
    
    public void readDB() {
        dstk = taikhoanDA.readDB();
    }

    public Taikhoan getTaiKhoan(String tentk) {
        for (var tk : dstk) {
            if (tk.getTaikhoan().equals(tentk)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<Taikhoan> search(String value, String type) {
        ArrayList<Taikhoan> result = new ArrayList<Taikhoan>();

        dstk.forEach((tk) -> {
            if (type.equals("Tất cả")) {
                if (tk.getTaikhoan().toLowerCase().contains(value.toLowerCase())
//                        || tk.getMatkhau().toLowerCase().contains(value.toLowerCase())
                        || tk.getNv().getMaNhanvien() == Integer.parseInt(value)
                        || tk.getQ().getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                    result.add(tk);
                }
            } else {
                switch (type) {
                    case "Tên tài khoản":
                        if (tk.getTaikhoan().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
//                    case "Mật khẩu":
//                        if (tk.getMatkhau().toLowerCase().contains(value.toLowerCase())) {
//                            result.add(tk);
//                        }
//                        break;
                    case "Mã nhân viên":
                        if (tk.getNv().getMaNhanvien() == Integer.parseInt(value)) {
                            result.add(tk);
                        }
                        break;
                    case "Mã quyền":
                        if (tk.getQ().getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                }
            }

        });

        return result;
    }

    public Boolean themTaiKhoan(Taikhoan taikhoan) {
        Boolean ok = taikhoanDA.themTaiKhoan(taikhoan);
        if (ok) {
            dstk.add(taikhoan);
        }
        return ok;
    }

    public Boolean softDelete(String username) {
        Boolean ok = taikhoanDA.softDelete(username);
//        if (ok) {
//            for (int i = (dstk.size() - 1); i >= 0; i--) {
//                if (dstk.get(i).getTaikhoan().equals(username)) {
//                    dstk.remove(i);
//                }
//            }
//        }
        return ok;
    }

    public Boolean capnhatTaiKhoan(Taikhoan taikhoan) {
        Boolean ok = taikhoanDA.capnhatTaiKhoan(taikhoan);
        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getTaikhoan().equals(taikhoan.getTaikhoan())) {
                    tk.setMatkhau(taikhoan.getMatkhau());
                    tk.setNv(taikhoan.getNv());
                    tk.setQ(taikhoan.getQ());                    
                    tk.setDaXoa(taikhoan.isDaXoa());
                }
            });
        }
        return ok;
    }
    
    public Boolean resetMatKhau(Taikhoan taikhoan, String hashedString) {
        Boolean ok = taikhoanDA.resetMatKhau(taikhoan, hashedString);
        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getTaikhoan().equals(taikhoan.getTaikhoan())) {
                    tk.setMatkhau(taikhoan.getMatkhau());
                }
            });
        }
        return ok;
    }
    
    public static ArrayList<Taikhoan> getDanhSachTaiKhoan() {
        return dstk;
    }
}
