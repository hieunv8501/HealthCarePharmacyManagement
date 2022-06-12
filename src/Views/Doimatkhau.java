package Views;

import Models.Nhanvien;
import Models.Taikhoan;
import Models.Quyen;
import Controllers.NhanvienController;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;

public class Doimatkhau extends JFrame {

    public Doimatkhau() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
        overlay.setBackground(new Color(0, 0, 0, 150));
        //btnDangNhap.setBackground(new Color(0,0,0,0));
        btnExit.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Đổi mật khẩu");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        setIconImage(logo.getImage());

        this.setLocationRelativeTo(null);
        // Thêm hotkey event enter
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnChangePwd.doClick();
                }
            }
        };

        txtMKcu.addKeyListener(ka);
        txtNewPwd.addKeyListener(ka);
        txtReNewPwd.addKeyListener(ka);

        // Thêm vào tự động chọn text khi focus
        // https://stackoverflow.com/questions/7361291/select-all-on-focus-in-lots-of-jtextfield
        FocusListener fl = new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (fe.getSource() instanceof JTextField) {
                    JTextField tx = (JTextField) fe.getSource();
                    tx.select(0, tx.getText().length());

                } else if (fe.getSource() instanceof JPasswordField) {
                    JPasswordField tx = (JPasswordField) fe.getSource();
                    tx.select(0, tx.getPassword().length);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (fe.getSource() instanceof JTextField) {
                    JTextField tx = (JTextField) fe.getSource();
                    tx.select(0, 0);
                } else if (fe.getSource() instanceof JPasswordField) {
                    JPasswordField tx = (JPasswordField) fe.getSource();
                    tx.select(0, 0);
                }
            }
        };
        txtMKcu.addFocusListener(fl);
        txtNewPwd.addFocusListener(fl);
        txtReNewPwd.addFocusListener(fl);

        // Tự động focus vào tên đăng nhập
        txtMKcu.requestFocus();
        txtMKcu.addKeyListener(new KeyListener() {
            char[] password;

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                password = txtMKcu.getPassword();
                if (password.length == 0) {
                    lblOldPwd.setText("Không được để trống mật khẩu!");
                    txtMKcu.requestFocus();
                } else if (String.valueOf(password).length() >= 6) {
                    lblOldPwd.setText("");
                    if (hasADigit(String.valueOf(password)) && hasALowerChar(String.valueOf(password)) && hasAnUpperChar(String.valueOf(password)) && hasASpecialChar(String.valueOf(password)) && (hasAnImproperChar(String.valueOf(password)) == false) && !String.valueOf(password).contains(" ")) {
                        if (!(String.valueOf(txtNewPwd.getPassword()).equals(password))) {
                            lblNewPwd.setText("Mật khẩu mới không khớp!");
                        } else if (!(String.valueOf(txtReNewPwd.getPassword()).equals(String.valueOf(txtNewPwd.getPassword())))) {
                            lblNewPwd.setText("Mật khẩu xác nhận không khớp!");
                        } else if (!(String.valueOf(txtNewPwd.getPassword()).equals(password)) && !(String.valueOf(txtReNewPwd.getPassword()).equals(password))) {
                            lblNewPwd.setText("Mật khẩu mới không khớp!");
                            lblRenewPwd.setText("Mật khẩu xác nhận không khớp!");
                        } else {
                            lblOldPwd.setText("");
                        }
                    } else {
                        if (String.valueOf(password).contains(" ") == true) {
                            lblOldPwd.setText("Mật khẩu không được chứa ký tự trắng!");
                        } else if (hasADigit(String.valueOf(password)) == false) {
                            lblOldPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự số!");
                        } else if (hasALowerChar(String.valueOf(password)) == false) {
                            lblOldPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự thường!");
                        } else if (hasAnUpperChar(String.valueOf(password)) == false) {
                            lblOldPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự hoa!");
                        } else if (hasAnImproperChar(String.valueOf(password)) == true) {
                            lblOldPwd.setText("Mật khẩu không được chứa ký tự lạ!");
                        } else if (hasASpecialChar(String.valueOf(password)) == false) {
                            lblOldPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!");
                        }
                    }
                } else {
                    lblOldPwd.setText("Mật khẩu quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
        });
        txtNewPwd.addKeyListener(new KeyListener() {
            char[] password;

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                password = txtNewPwd.getPassword();
                if (password.length == 0) {
                    lblNewPwd.setText("Không được để trống mật khẩu!");
                    txtNewPwd.requestFocus();
                } else if (String.valueOf(password).length() >= 6) {
                    lblNewPwd.setText("");
                    if (hasADigit(String.valueOf(password)) && hasALowerChar(String.valueOf(password)) && hasAnUpperChar(String.valueOf(password)) && hasASpecialChar(String.valueOf(password)) && (hasAnImproperChar(String.valueOf(password)) == false) && !String.valueOf(password).contains(" ")) {
                        if (!(String.valueOf(txtNewPwd.getPassword()).equals(password))) {
                            lblNewPwd.setText("Mật khẩu mới không khớp!");
                        } else if (!(String.valueOf(txtReNewPwd.getPassword()).equals(String.valueOf(txtNewPwd.getPassword())))) {
                            lblNewPwd.setText("Mật khẩu xác nhận không khớp!");
                        } else if (!(String.valueOf(txtNewPwd.getPassword()).equals(password)) && !(String.valueOf(txtReNewPwd.getPassword()).equals(password))) {
                            lblNewPwd.setText("Mật khẩu mới không khớp!");
                            lblRenewPwd.setText("Mật khẩu xác nhận không khớp!");
                        } else {
                            lblNewPwd.setText("");
                        }
                    } else {
                        if (String.valueOf(password).contains(" ") == true) {
                            lblNewPwd.setText("Mật khẩu không được chứa ký tự trắng!");
                        } else if (hasADigit(String.valueOf(password)) == false) {
                            lblNewPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự số!");
                        } else if (hasALowerChar(String.valueOf(password)) == false) {
                            lblNewPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự thường!");
                        } else if (hasAnUpperChar(String.valueOf(password)) == false) {
                            lblNewPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự hoa!");
                        } else if (hasAnImproperChar(String.valueOf(password)) == true) {
                            lblNewPwd.setText("Mật khẩu không được chứa ký tự lạ!");
                        } else if (hasASpecialChar(String.valueOf(password)) == false) {
                            lblNewPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!");
                        }
                    }
                } else {
                    lblNewPwd.setText("Mật khẩu quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
        });
        txtReNewPwd.addKeyListener(new KeyListener() {
            char[] password;

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                password = txtReNewPwd.getPassword();
                if (password.length == 0) {
                    lblReMKmoi.setText("Không được để trống mật khẩu!");
                    txtReNewPwd.requestFocus();
                } else if (String.valueOf(password).length() >= 6) {
                    lblReMKmoi.setText("");
                    if (hasADigit(String.valueOf(password)) && hasALowerChar(String.valueOf(password)) && hasAnUpperChar(String.valueOf(password)) && hasASpecialChar(String.valueOf(password)) && (hasAnImproperChar(String.valueOf(password)) == false) && !String.valueOf(password).contains(" ")) {
                        if (( !(String.valueOf(txtReNewPwd.getPassword())).equals(String.valueOf(txtNewPwd.getPassword()))) && (!(String.valueOf(txtReNewPwd.getPassword()).isEmpty())) || (!(String.valueOf(txtNewPwd.getPassword())).equals(String.valueOf(txtReNewPwd.getPassword()))) && !(String.valueOf(txtNewPwd.getPassword()).isEmpty())) {
                            lblRenewPwd.setText("Mật khẩu mới và mật khẩu xác nhận không khớp!");
                        } else {
                            lblReMKmoi.setText("");
                        }
                    } else {
                        if (String.valueOf(password).contains(" ") == true) {
                            lblReMKmoi.setText("Mật khẩu không được chứa ký tự trắng!");
                        } else if (hasADigit(String.valueOf(password)) == false) {
                            lblReMKmoi.setText("Mật khẩu phải chứa ít nhất 1 ký tự số!");
                        } else if (hasALowerChar(String.valueOf(password)) == false) {
                            lblReMKmoi.setText("Mật khẩu phải chứa ít nhất 1 ký tự thường!");
                        } else if (hasAnUpperChar(String.valueOf(password)) == false) {
                            lblReMKmoi.setText("Mật khẩu phải chứa ít nhất 1 ký tự hoa!");
                        } else if (hasAnImproperChar(String.valueOf(password)) == true) {
                            lblReMKmoi.setText("Mật khẩu không được chứa ký tự lạ!");
                        } else if (hasASpecialChar(String.valueOf(password)) == false) {
                            lblReMKmoi.setText("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!");
                        }
                    }
                } else {
                    lblReMKmoi.setText("Mật khẩu quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        loginBox = new javax.swing.JPanel();
        lblNameTaiKhoan = new javax.swing.JLabel();
        lblReMKmoi = new javax.swing.JLabel();
        btnChangePwd = new javax.swing.JButton();
        btnExit = new javax.swing.JPanel();
        btnCloseLogin = new javax.swing.JLabel();
        lblNewPwd = new javax.swing.JLabel();
        lblOldPwd = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMKmoi = new javax.swing.JLabel();
        lblWarning = new javax.swing.JLabel();
        lblRenewPwd = new javax.swing.JLabel();
        txtNewPwd = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtReNewPwd = new javax.swing.JPasswordField();
        txtMKcu = new javax.swing.JPasswordField();
        lblMKcu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        overlay = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginBox.setBackground(new java.awt.Color(255, 255, 255));
        loginBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblNameTaiKhoan.setText("Mật khẩu");
        loginBox.add(lblNameTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        lblReMKmoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblReMKmoi.setForeground(new java.awt.Color(255, 255, 255));
        lblReMKmoi.setText("Nhập lại mật khẩu mới");
        loginBox.add(lblReMKmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 355, -1, -1));

        btnChangePwd.setBackground(new java.awt.Color(102, 102, 255));
        btnChangePwd.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        btnChangePwd.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePwd.setText("Đổi mật khẩu");
        btnChangePwd.setToolTipText("");
        btnChangePwd.setBorderPainted(false);
        btnChangePwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangePwd.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btnChangePwd.setMaximumSize(new java.awt.Dimension(123, 40));
        btnChangePwd.setMinimumSize(new java.awt.Dimension(123, 40));
        btnChangePwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePwdActionPerformed(evt);
            }
        });
        loginBox.add(btnChangePwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, -1, 40));

        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });

        btnCloseLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeLoginIcon.png"))); // NOI18N
        btnCloseLogin.setText("jLabel10");
        btnCloseLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCloseLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCloseLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        loginBox.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 30, 30));

        lblNewPwd.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNewPwd.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblNewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 325, 350, 25));

        lblOldPwd.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblOldPwd.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblOldPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 243, 350, 25));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Healthcare Pharmacy Management System");
        loginBox.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 35));

        lblMKmoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMKmoi.setForeground(new java.awt.Color(255, 255, 255));
        lblMKmoi.setText("Mật khẩu mới");
        loginBox.add(lblMKmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 272, -1, -1));

        lblWarning.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        lblWarning.setForeground(new java.awt.Color(255, 255, 255));
        lblWarning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBox.add(lblWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 390, 25));

        lblRenewPwd.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblRenewPwd.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblRenewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 412, 350, 25));

        txtNewPwd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNewPwd.setForeground(new java.awt.Color(102, 204, 255));
        txtNewPwd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtNewPwd.setOpaque(false);
        loginBox.add(txtNewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 293, 280, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_login.png"))); // NOI18N
        loginBox.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 220, 160));

        txtReNewPwd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtReNewPwd.setForeground(new java.awt.Color(102, 204, 255));
        txtReNewPwd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtReNewPwd.setOpaque(false);
        loginBox.add(txtReNewPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 280, 30));

        txtMKcu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMKcu.setForeground(new java.awt.Color(102, 204, 255));
        txtMKcu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtMKcu.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMKcu.setOpaque(false);
        loginBox.add(txtMKcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 212, 290, 30));

        lblMKcu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMKcu.setForeground(new java.awt.Color(51, 51, 255));
        lblMKcu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMKcu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background_right.png"))); // NOI18N
        lblMKcu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMKcu.setDoubleBuffered(true);
        lblMKcu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMKcu.setMinimumSize(new java.awt.Dimension(500, 500));
        lblMKcu.setOpaque(true);
        loginBox.add(lblMKcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 530));

        getContentPane().add(loginBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 470, 530));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overlay.setBackground(new Color(0,0,0,100));
        overlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("UTM Bebas", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password Change");
        overlay.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));
        overlay.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 180, -1));

        jLabel6.setBackground(new java.awt.Color(153, 153, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background_left.png"))); // NOI18N
        overlay.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 529));

        jPanel1.add(overlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnChangePwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePwdActionPerformed
        //Khởi tạo đối tượng và dẫn nhập từ bàn phím

        String mkOld = String.valueOf(txtMKcu.getPassword());
        String mkNew = String.valueOf(txtNewPwd.getPassword());
        String mkReNew = String.valueOf(txtReNewPwd.getPassword());

        if (mkOld.isEmpty() && mkNew.isEmpty() && mkReNew.isEmpty()) {
            lblOldPwd.setText("Không được để trống mật khẩu cũ!");
            lblNewPwd.setText("Không được để trống mật khẩu mới!");
            lblRenewPwd.setText("Không được để trống xác nhận mật khẩu!");
            txtMKcu.requestFocus();
            return;
        } else if (mkOld.length() >= 6 && mkNew.isEmpty() && !mkReNew.isEmpty()) {
            lblNewPwd.setText("Không được để trống mật khẩu mới!");
            lblOldPwd.setText("");
            lblRenewPwd.setText("");
            txtNewPwd.requestFocus();
            return;
        } else if (!mkNew.isEmpty() && mkOld.isEmpty() && !mkReNew.isEmpty()) {
            lblOldPwd.setText("Không được để trống mật khẩu cũ!");
            lblNewPwd.setText("");
            lblRenewPwd.setText("");
            txtMKcu.requestFocus();
            return;
        } else if (!mkNew.isEmpty() && !mkOld.isEmpty() && mkReNew.isEmpty()) {
            lblRenewPwd.setText("Không được để trống xác nhận mật khẩu!");
            lblNewPwd.setText("");
            lblOldPwd.setText("");
            txtReNewPwd.requestFocus();
            return;
        } else if (!mkNew.isEmpty() && !mkOld.isEmpty() && mkReNew.isEmpty()) {
            lblRenewPwd.setText("Không được để trống xác nhận mật khẩu!");
            lblNewPwd.setText("");
            lblOldPwd.setText("Không được để trống mật khẩu!");
            txtMKcu.requestFocus();
            return;
        } else if (mkNew.isEmpty() && !mkOld.isEmpty() && mkReNew.isEmpty()) {
            lblRenewPwd.setText("Không được để trống xác nhận mật khẩu!");
            lblNewPwd.setText("Không được để trống mật khẩu mới!");
            lblOldPwd.setText("");
            txtNewPwd.requestFocus();
            return;
        } else if (mkNew.isEmpty() && mkOld.isEmpty() && !mkReNew.isEmpty()) {
            lblRenewPwd.setText("");
            lblNewPwd.setText("Không được để trống mật khẩu mới!");
            lblOldPwd.setText("Không được để trống mật khẩu cũ!");
            txtMKcu.requestFocus();
            return;
        } else if ((!(mkReNew.equals(mkNew)) && !mkReNew.isEmpty()) || (!(mkNew.equals(mkReNew)) && !mkNew.isEmpty())) {
            lblRenewPwd.setText("Mật khẩu mới và mật khẩu xác nhận không khớp!");
        } else {
            //Kiểm tra xem có tồn tại tên tài khoản như này không
            Taikhoan tk = taiKhoanLogin;
            if (tk != null) {
                // Kiểm tra xem nhân viên của tài khoản này có bị khóa (Ẩn/xóa) hay không
                Nhanvien nv = new NhanvienController().getNhanVien(tk.getNv().getMaNhanvien());
                if (nv.isDaXoa() == true) {
                    JOptionPane.showMessageDialog(this, "Tài khoản này đã bị tạm khóa, do chủ tài khoản này đã bị ẨN khỏi hệ thống!");
                    return;
                } else {
                    //Đổi mật khẩu
                    String newPwd = String.valueOf(txtNewPwd.getPassword());
                    if (newPwd != null && !newPwd.isEmpty()) {
                        String hash = BCrypt.hashpw(newPwd, BCrypt.gensalt(10));
                        tk.resetMatKhau(tk, hash);
                    }
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công, vui lòng đăng nhập lại!");
                    taiKhoanLogin = null;
                    this.dispose();
                    new DangnhapView().setVisible(true);
                }
            } else {
                lblWarning.setText("Đổi mật khẩu thất bại, vui lòng thử lại sau!");
                txtMKcu.requestFocus();
            }
        }

    }//GEN-LAST:event_btnChangePwdActionPerformed

    private boolean hasADigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean hasALowerChar(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAnUpperChar(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                return true;
            }
        }
        return false;
    }

    private boolean hasASpecialChar(String password) {
        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) >= 33 && password.charAt(i) <= 47)
                    || (password.charAt(i) >= 58 && password.charAt(i) <= 64)
                    || (password.charAt(i) >= 91 && password.charAt(i) <= 96)
                    || (password.charAt(i) >= 123 && password.charAt(i) <= 126)) {
                return true;
            }
        }
        return false;
    }

    private int countSpecialChars(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) >= 33 && password.charAt(i) <= 47)
                    || (password.charAt(i) >= 58 && password.charAt(i) <= 64)
                    || (password.charAt(i) >= 91 && password.charAt(i) <= 96)
                    || (password.charAt(i) >= 123 && password.charAt(i) <= 126)) {
                count++;
            }
        }
        return count;
    }

    private boolean hasAnImproperChar(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < 32 || password.charAt(i) > 126) {
                return true;
            }
        }
        return false;
    }
    //Function check valid 
    //Static properties
    public static String saveFileName = "temp";
    public static Quyen quyenLogin;
    public static Nhanvien nhanVienLogin;
    public static Taikhoan taiKhoanLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePwd;
    private javax.swing.JLabel btnCloseLogin;
    private javax.swing.JPanel btnExit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMKcu;
    private javax.swing.JLabel lblMKmoi;
    private javax.swing.JLabel lblNameTaiKhoan;
    private javax.swing.JLabel lblNewPwd;
    private javax.swing.JLabel lblOldPwd;
    private javax.swing.JLabel lblReMKmoi;
    private javax.swing.JLabel lblRenewPwd;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JPanel loginBox;
    private javax.swing.JPanel overlay;
    private javax.swing.JPasswordField txtMKcu;
    private javax.swing.JPasswordField txtNewPwd;
    private javax.swing.JPasswordField txtReNewPwd;
    // End of variables declaration//GEN-END:variables
}
