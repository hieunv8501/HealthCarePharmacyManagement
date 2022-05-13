/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author TinhBui
 */
public class ChitietHoadon {
    private int maHoadon;
    private int maThuoc;
    private int maLo;
    private int maDonvitinh;
    private int soluong;
    private float dongia;
    private boolean daXoa;

    public ChitietHoadon(int maHoadon, int maThuoc, int maLo, int maDonvitinh, int soluong, float dongia, boolean daXoa) {
        this.maHoadon = maHoadon;
        this.maThuoc = maThuoc;
        this.maLo = maLo;
        this.maDonvitinh = maDonvitinh;
        this.soluong = soluong;
        this.dongia = dongia;
        this.daXoa = daXoa;
    }
    
    
    
}
