package Controllers;

import Models.Taikhoan;
import java.util.ArrayList;

public class TaikhoanController {

    private ArrayList<Taikhoan> dstk = new ArrayList<>();
    Taikhoan taikhoanDA = new Taikhoan();

    public TaikhoanController() {
        dstk = taikhoanDA.readDB();
    }

    public void showConsole() {
        dstk.forEach((tk) -> {
            System.out.print(tk.getTaikhoan()+ " ");
            System.out.print(tk.getMatkhau()+ " ");
            System.out.print(tk.getMaNhanvien()+ " ");
            System.out.print(tk.getMaQuyen());
        });
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
        ArrayList<Taikhoan> result = new ArrayList<>();

        dstk.forEach((tk) -> {
            if (type.equals("Tất cả")) {
                if (tk.getTaikhoan().toLowerCase().contains(value.toLowerCase())
                        || tk.getMatkhau().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaNhanvien() == Integer.parseInt(value)
                        || tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                    result.add(tk);
                }
            } else {
                switch (type) {
                    case "Tên tài khoản":
                        if (tk.getTaikhoan().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mật khẩu":
                        if (tk.getMatkhau().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã nhân viên":
                        if (tk.getMaNhanvien() == Integer.parseInt(value)) {
                            result.add(tk);
                        }
                        break;
                    case "Mã quyền":
                        if (tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                }
            }

        });

        return result;
    }

    public Boolean add(Taikhoan tk) {
        Boolean ok = taikhoanDA.add(tk);

        if (ok) {
            dstk.add(tk);
        }
        return ok;
    }

    public Boolean add(String username, String pass, int maNV, String maQuyen) {
        Taikhoan tk = new Taikhoan(username, pass, maNV, maQuyen);
        return add(tk);
    }

    public Boolean delete(String username) {
        Boolean ok = taikhoanDA.delete(username);

        if (ok) {
            for (int i = (dstk.size() - 1); i >= 0; i--) {
                if (dstk.get(i).getTaikhoan().equals(username)) {
                    dstk.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String username, String pass, int maNV, String maQuyen) {
        Boolean ok = taikhoanDA.update(username, pass, maNV, maQuyen);

        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getTaikhoan().equals(username)) {
                    tk.setMatkhau(pass);
                    tk.setMaNhanvien(maNV);
                    tk.setMaQuyen(maQuyen);
                }
            });
        }
        return ok;
    }

    public ArrayList<Taikhoan> getDanhSachTaiKhoan() {
        return dstk;
    }
}
