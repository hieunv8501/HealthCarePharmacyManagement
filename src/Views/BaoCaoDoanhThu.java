/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

/**
 *
 * @author HauPC
 */
public class BaoCaoDoanhThu extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public BaoCaoDoanhThu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quanlyKhoView1 = new Views.QuanlyKhoView();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        thongKeSoLuongDonBan2 = new Views.ThongKeSoLuongDonBan();
        thongKeDoanhThuThuoc1 = new Views.ThongKeDoanhThuThuoc();
        quanlyKhoView2 = new Views.QuanlyKhoView();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jTabbedPane1.addTab("Báo cáo số lượng đơn hàng trong năm", thongKeSoLuongDonBan2);
        jTabbedPane1.addTab("Thống kê doanh thu bán thuốc theo năm", thongKeDoanhThuThuoc1);
        jTabbedPane1.addTab("Thống kê kho", quanlyKhoView2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private Views.QuanlyKhoView quanlyKhoView1;
    private Views.QuanlyKhoView quanlyKhoView2;
    private Views.ThongKeDoanhThuThuoc thongKeDoanhThuThuoc1;
    private Views.ThongKeSoLuongDonBan thongKeSoLuongDonBan2;
    // End of variables declaration//GEN-END:variables
}
