/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author admin
 */
public class Huyen {
    private int maHuyen;
    private String tenHuyen;
    private Tinh tinh;

    public Huyen() {
    }

    public Huyen(int maHuyen, String tenHuyen, Tinh tinh) {
        this.maHuyen = maHuyen;
        this.tenHuyen = tenHuyen;
        this.tinh = tinh;
    }

    public int getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(int maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getTinh() {
        return tinh.getTenTinh();
    }
   
}
