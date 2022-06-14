/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Controllers.BaoCaoController;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author HauPC
 */
public class ThongKeDoanhThuThuoc extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeDonThuoc
     */
    public ThongKeDoanhThuThuoc() {
        initComponents();
        showNam();
        //DecimalFormat df=new DecimalFormat("$ #,##0.##");
        chart.addLegend("Doanh thu", new Color(140, 50, 180));

//        chart.addLegend("1-2", new Color(135, 189, 245));
//        chart.addLegend("2-3", new Color(189, 135, 245));
//        chart.addLegend("3-4", new Color(139, 0, 222));
//        chart.addLegend("4-5", new Color(120, 30, 190));
//        chart.addLegend("5-6", new Color(140, 50, 180));
//        chart.addLegend("6-7", new Color(160, 70, 170));
//        chart.addLegend("7-8", new Color(180, 90, 160));
//        chart.addLegend("8-9", new Color(190, 110, 150));
//        chart.addLegend("9-10", new Color(100, 150, 130));
    }

    public void initChart(int nam) {
        chart.removeData();
        BaoCaoController baocao = new BaoCaoController();
        HashMap<Integer, Float> doanhthu = new HashMap<>();
        doanhthu = baocao.getDoanhThuThang(nam);
        chart.addData(new ModelChart("Tháng 1", new double[]{doanhthu.get(1) == null ? 0 : doanhthu.get(1)}));
        chart.addData(new ModelChart("Tháng 2", new double[]{doanhthu.get(2) == null ? 0 : doanhthu.get(2)}));
        chart.addData(new ModelChart("Tháng 3", new double[]{doanhthu.get(3) == null ? 0 : doanhthu.get(3)}));
        chart.addData(new ModelChart("Tháng 4", new double[]{doanhthu.get(4) == null ? 0 : doanhthu.get(4)}));
        chart.addData(new ModelChart("Tháng 5", new double[]{doanhthu.get(5) == null ? 0 : doanhthu.get(5)}));
        chart.addData(new ModelChart("Tháng 6", new double[]{doanhthu.get(6) == null ? 0 : doanhthu.get(6)}));
        chart.addData(new ModelChart("Tháng 7", new double[]{doanhthu.get(7) == null ? 0 : doanhthu.get(7)}));
        chart.addData(new ModelChart("Tháng 8", new double[]{doanhthu.get(8) == null ? 0 : doanhthu.get(8)}));
        chart.addData(new ModelChart("Tháng 9", new double[]{doanhthu.get(9) == null ? 0 : doanhthu.get(9)}));
        chart.addData(new ModelChart("Tháng 10", new double[]{doanhthu.get(10) == null ? 0 : doanhthu.get(10)}));
        chart.addData(new ModelChart("Tháng 11", new double[]{doanhthu.get(11) == null ? 0 : doanhthu.get(11)}));
        chart.addData(new ModelChart("Tháng 12", new double[]{doanhthu.get(12) == null ? 0 : doanhthu.get(12)}));

    }

    public void showNam() {
        DefaultComboBoxModel chonNamModel = (DefaultComboBoxModel) txtChonNam.getModel();
        BaoCaoController baoCaoController = new BaoCaoController();
        chonNamModel.removeAllElements();
        ArrayList<Integer> nams = baoCaoController.getNamHoaDon();
        nams.forEach(nam -> {
            chonNamModel.addElement(nam);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chart = new com.raven.chart.Chart();
        txtChonNam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        lblBieuDo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        txtChonNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtChonNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChonNamActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Chọn Năm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Thống kê doanh thu bán thuốc theo năm");

        lblBieuDo.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        lblBieuDo.setForeground(new java.awt.Color(255, 0, 0));
        lblBieuDo.setText("Biểu đồ doanh thu bán thuốc trong năm");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_replay_30px.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(51, 51, 51)
                                .addComponent(txtChonNam, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 1207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(lblBieuDo)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 617, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 618, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(56, 56, 56)
                .addComponent(lblBieuDo)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChonNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtChonNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChonNamActionPerformed
        // TODO add your handling code here:
        if (txtChonNam.getSelectedItem() != null) {

            int nam = (int) txtChonNam.getSelectedItem();

            initChart(nam);
            chart.setVisible(false);
            chart.setVisible(true);
            lblBieuDo.setText("Biểu đồ doanh thu bán thuốc trong năm " + nam);
        }

    }//GEN-LAST:event_txtChonNamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtChonNam.getSelectedItem() != null) {

            int nam = (int) txtChonNam.getSelectedItem();

            initChart(nam);
            chart.setVisible(false);
            chart.setVisible(true);
            lblBieuDo.setText("Biểu đồ doanh thu bán thuốc trong năm " + nam);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.chart.Chart chart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBieuDo;
    private javax.swing.JComboBox<String> txtChonNam;
    // End of variables declaration//GEN-END:variables
}
