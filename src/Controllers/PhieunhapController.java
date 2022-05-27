package Controllers;

import Models.Phieunhap;
import java.util.ArrayList;
import java.util.Calendar;

public class PhieunhapController {

    private ArrayList<Phieunhap> dspn = new ArrayList<Phieunhap>();
    Phieunhap phieunhapDA = new Phieunhap();
    
    public PhieunhapController() {
        dspn = phieunhapDA.readDB();
    }

    public void showConsole() {
        System.out.println("Đã chạy tới trong này.");
        dspn.forEach((pn) -> {
            System.out.print(pn.getMaPhieunhap()+ " ");
            System.out.print(pn.getMaNhacungcap()+ " ");
            System.out.print(pn.getMaNhanvien()+ " ");
            System.out.print(pn.getNgaynhap());
            System.out.print(pn.getTongTien()+ " ");            
            System.out.print("Đã tạm ẩn: " + pn.isDaxoa()+ " ");
        });
    }
    
    public String[] getHeaders() {
        return new String[]{"STT", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Tổng tiền", "Trạng thái"};
    }

    public void readDB() {
        dspn = phieunhapDA.readDB();
    }

    public Phieunhap getPhieuNhap(int mapn) {
        for (var pn : dspn) {
            if (pn.getMaPhieunhap() == mapn) {
                return pn;
            }
        }
        return null;
    }

    public ArrayList<Phieunhap> search(String searchValue, String searchType) {
        ArrayList<Phieunhap> result = new ArrayList<Phieunhap>();

        dspn.forEach((pn) -> {
            if (searchType.equals("Tất cả")) {
                if (String.valueOf(pn.getMaPhieunhap()).toLowerCase().contains(searchValue.toLowerCase())
                        || String.valueOf(pn.getMaNhacungcap()).toLowerCase().contains(searchValue.toLowerCase())
                        || String.valueOf(pn.getMaNhanvien()).toLowerCase().contains(searchValue.toLowerCase())
                        //|| pn.getNgaynhap() == stringToCalendar(searchValue)
                        || String.valueOf(pn.getTongTien()).toLowerCase().contains(searchValue.toLowerCase())) {
                    result.add(pn);
                }
            } else {
                switch (searchType) {
//                    case "Mã phiếu nhập":
//                        if (pn.get().toLowerCase().contains(searchValue.toLowerCase())) {
//                            result.add(pn);
//                        }
//                        break;
//                    case "Mã nhà cung cấp":
//                        if (pn.getMatkhau().toLowerCase().contains(searchValue.toLowerCase())) {
//                            result.add(pn);
//                        }
//                        break;
//                    case "Mã nhân viên":
//                        if (pn.getMaNhanvien() == Integer.parseInt(searchValue)) {
//                            result.add(pn);
//                        }
//                        break;
//                    case "Ngày nhập":
//                        if (pn.getMaQuyen().toLowerCase().contains(searchValue.toLowerCase())) {
//                            result.add(pn);
//                        }
//                        break;
                }
            }

        });

        return result;
    }

    public Boolean add(Phieunhap pn) {
        Boolean ok = phieunhapDA.add(pn);
        if (ok) {
            dspn.add(pn);
        }
        return ok;
    }

//    public Boolean softDelete(int maPhieuNhap) {
//        Boolean ok = phieunhapDA.update(maPhieuNhap);
//        if (ok) {
//            for (int i = (dspn.size() - 1); i >= 0; i--) {
//                if (dspn.get(i).getMaPhieunhap() == maPhieuNhap) {
//                    dspn.get(i).setDaxoa(true);
//                }
//            }
//        }
//        return ok;
//    }
    
    public Boolean hardDelete(int maPhieuNhap) {
        Boolean ok = phieunhapDA.delete(maPhieuNhap);
        if (ok) {
            for (int i = (dspn.size() - 1); i >= 0; i--) {
                if (dspn.get(i).getMaPhieunhap() == maPhieuNhap) {
                    dspn.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(Phieunhap pn) {
        Boolean ok = phieunhapDA.update(pn);
        return ok;
    }

    public ArrayList<Phieunhap> getDanhSachPhieuNhap() {
        return dspn;
    }
}

