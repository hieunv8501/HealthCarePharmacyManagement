package Models;

public class Xa {
    private int maXa;
    private String tenXa;
    private Huyen huyen;

    public Xa() {
    }

    public Xa(int maXa, String tenXa, Huyen huyen) {
        this.maXa = maXa;
        this.tenXa = tenXa;
        this.huyen = huyen;
    }

    public int getMaXa() {
        return maXa;
    }

    public void setMaXa(int maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return tenXa;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    public String getHuyen() {
        return huyen.getTenHuyen();
    }
    
    public String getTinh() {
        return huyen.getTinh();
    }

    @Override
    public String toString() {
        return tenXa;
    }
    
    
    
}
