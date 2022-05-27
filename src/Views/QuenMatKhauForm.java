package Views;

import Models.Nhanvien;
import Models.Taikhoan;
import Models.Quyen;
import Controllers.TaikhoanController;
import Controllers.QuyenController;
import Controllers.NhanvienController;
import Components.ExcelOperation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
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
import javax.swing.JPanel;
import org.mindrot.jbcrypt.BCrypt;

public class QuenMatKhauForm extends JFrame {

    public QuenMatKhauForm() {
        initComponents();
        overlay.setBackground(new Color(0, 0, 0, 150));
        //btnDangNhap.setBackground(new Color(0,0,0,0));
        btnExit.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Quên mật khẩu");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        setIconImage(logo.getImage());

        this.setLocationRelativeTo(null);
        //String alertStringLabel = "";
        // Thêm hotkey event enter
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnResetPwd.doClick();
                }
            }
        };

        txtTenTaiKhoan.addKeyListener(ka);
        txtCaptcha.addKeyListener(ka);

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
        txtTenTaiKhoan.addFocusListener(fl);
        txtCaptcha.addFocusListener(fl);

        // Tự động focus vào tên đăng nhập
        txtTenTaiKhoan.requestFocus();
        txtTenTaiKhoan.addKeyListener(new KeyListener() {
            String username = "";
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {              
                username = txtTenTaiKhoan.getText();
                if (username.isEmpty()) {
                    jlblVerifyEmail.setText("Không được bỏ trống tên tài khoản!");     
                    txtTenTaiKhoan.requestFocus();
                }
                else if (username.length() >= 6) {
                    jlblVerifyEmail.setText("");
                    if ((hasADigit(username) || hasALowerChar(username) || hasAnUpperChar(username)) && (countSpecialChars(username) == 1) && (hasAnImproperChar(username) == false) && !username.contains(" ")) jlblVerifyEmail.setText("");
                    else {
                        if (countSpecialChars(username) >= 2) jlblVerifyEmail.setText("Tên tài khoản không được chứa quá 2 ký tự đặc biệt!");
                        else if (hasAnImproperChar(username) == true) jlblVerifyEmail.setText("Tên tài khoản không được chứa các ký tự lạ!");
                        else if (username.contains(" ")) jlblVerifyEmail.setText("Tên tài khoản không được chứa ký tự trắng!");
                    }
                }
                else {
                    jlblVerifyEmail.setText("Tên tài khoản quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
        });
        txtCaptcha.addKeyListener(new KeyListener() {
            char[] password;
            @Override
            public void keyPressed(KeyEvent e) {              
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {              
                password = txtCaptcha.getPassword();               
                System.out.println();
                if (password.length == 0) {
                    lblVerifyCaptcha.setText("Không được bỏ trống mật khẩu!");     
                    txtCaptcha.requestFocus();
                }
                else if (String.valueOf(password).length() >= 6) {
                    lblVerifyCaptcha.setText("");
                    if (hasADigit(String.valueOf(password)) && hasALowerChar(String.valueOf(password)) && hasAnUpperChar(String.valueOf(password)) && hasASpecialChar(String.valueOf(password)) && (hasAnImproperChar(String.valueOf(password)) == false) && !String.valueOf(password).contains(" ")) lblVerifyCaptcha.setText("");
                    else {
                        if (String.valueOf(password).contains(" ") == true) lblVerifyCaptcha.setText("Mật khẩu không được chứa ký tự trắng!");
                        else if (hasADigit(String.valueOf(password)) == false) lblVerifyCaptcha.setText("Mật khẩu phải chứa ít nhất 1 ký tự số!");                        
                        else if (hasALowerChar(String.valueOf(password)) == false) lblVerifyCaptcha.setText("Mật khẩu phải chứa ít nhất 1 ký tự thường!");                        
                        else if (hasAnUpperChar(String.valueOf(password)) == false) lblVerifyCaptcha.setText("Mật khẩu phải chứa ít nhất 1 ký tự hoa!");
                        else if (hasAnImproperChar(String.valueOf(password)) == true) lblVerifyCaptcha.setText("Mật khẩu không được chứa ký tự lạ!");                        
                        else if (hasASpecialChar(String.valueOf(password)) == false) lblVerifyCaptcha.setText("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!");
                    }
                }
                else {
                    lblVerifyCaptcha.setText("Mật khẩu quá ngắn, cần ít nhất 6 ký tự!");
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
        txtCaptcha = new javax.swing.JPasswordField();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnResetPwd = new javax.swing.JButton();
        btnExit = new javax.swing.JPanel();
        btnCloseLogin = new javax.swing.JLabel();
        lblVerifyCaptcha = new javax.swing.JLabel();
        jlblVerifyEmail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblCaptcha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblThongBaoResetPwd = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        overlay = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginBox.setBackground(new java.awt.Color(255, 255, 255));
        loginBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCaptcha.setBackground(new java.awt.Color(255, 0, 0));
        txtCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCaptcha.setForeground(new java.awt.Color(102, 204, 255));
        txtCaptcha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtCaptcha.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCaptcha.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCaptcha.setOpaque(false);
        txtCaptcha.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtCaptchaInputMethodTextChanged(evt);
            }
        });
        txtCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaptchaActionPerformed(evt);
            }
        });
        txtCaptcha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCaptchaKeyPressed(evt);
            }
        });
        loginBox.add(txtCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 120, 30));

        txtTenTaiKhoan.setBackground(new java.awt.Color(240, 240, 240));
        txtTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenTaiKhoan.setForeground(new java.awt.Color(102, 204, 255));
        txtTenTaiKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtTenTaiKhoan.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenTaiKhoan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenTaiKhoan.setDragEnabled(true);
        txtTenTaiKhoan.setOpaque(false);
        txtTenTaiKhoan.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTenTaiKhoanInputMethodTextChanged(evt);
            }
        });
        txtTenTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTaiKhoanActionPerformed(evt);
            }
        });
        txtTenTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenTaiKhoanKeyPressed(evt);
            }
        });
        loginBox.add(txtTenTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 258, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tài khoản");
        loginBox.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Captcha");
        loginBox.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        btnResetPwd.setBackground(new java.awt.Color(102, 102, 255));
        btnResetPwd.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        btnResetPwd.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPwd.setText("Đặt lại mật khẩu");
        btnResetPwd.setToolTipText("");
        btnResetPwd.setBorderPainted(false);
        btnResetPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetPwd.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btnResetPwd.setMaximumSize(new java.awt.Dimension(123, 40));
        btnResetPwd.setMinimumSize(new java.awt.Dimension(123, 40));
        btnResetPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPwdActionPerformed(evt);
            }
        });
        loginBox.add(btnResetPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, -1, 36));

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

        lblVerifyCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVerifyCaptcha.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblVerifyCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 325, 350, 25));

        jlblVerifyEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jlblVerifyEmail.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(jlblVerifyEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 243, 350, 25));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Healthcare Pharmacy Management System");
        loginBox.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 35));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Email");
        loginBox.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 272, -1, -1));

        lblCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCaptcha.setForeground(new java.awt.Color(255, 255, 51));
        lblCaptcha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaptcha.setText("captcha is here");
        lblCaptcha.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 0)));
        loginBox.add(lblCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 150, 50));

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEmail.setOpaque(false);
        loginBox.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 292, 258, 30));

        lblThongBaoResetPwd.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lblThongBaoResetPwd.setForeground(new java.awt.Color(255, 255, 255));
        lblThongBaoResetPwd.setText("Vui lòng kiếm tra tài khoản email để nhận mật khẩu mới!");
        loginBox.add(lblThongBaoResetPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 330, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_refresh_30px.png"))); // NOI18N
        jButton1.setOpaque(false);
        loginBox.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 40, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_login.png"))); // NOI18N
        loginBox.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 220, 160));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background_right.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setDoubleBuffered(true);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setMinimumSize(new java.awt.Dimension(500, 500));
        jLabel5.setOpaque(true);
        loginBox.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 530));

        getContentPane().add(loginBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 470, 530));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overlay.setBackground(new Color(0,0,0,100));
        overlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Welcome");
        overlay.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("UTM Bebas", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dashboard Reset");
        overlay.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));
        overlay.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 180, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_login_2.png"))); // NOI18N
        overlay.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 250, 190));

        jLabel6.setBackground(new java.awt.Color(153, 153, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background_left.png"))); // NOI18N
        overlay.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 529));

        jPanel1.add(overlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenTaiKhoanKeyPressed

    }//GEN-LAST:event_txtTenTaiKhoanKeyPressed

    private void txtTenTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTaiKhoanActionPerformed

    }//GEN-LAST:event_txtTenTaiKhoanActionPerformed

    private void txtCaptchaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaptchaKeyPressed

    }//GEN-LAST:event_txtCaptchaKeyPressed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnResetPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPwdActionPerformed
        //Khởi tạo đối tượng và dẫn nhập từ bàn phím
        String tentk = txtTenTaiKhoan.getText();
        var mk = txtCaptcha.getPassword();
        if (tentk.isEmpty() && mk.length == 0) {
            jlblVerifyEmail.setText("Không được bỏ trống tên tài khoản!");            
            lblVerifyCaptcha.setText("Không được bỏ trống captcha!");
            txtTenTaiKhoan.requestFocus();
            return;
        }
        else if (tentk.length() >= 6 && mk.length == 0) {
            lblVerifyCaptcha.setText("Không được bỏ trống tên tài khoản!");            
            jlblVerifyEmail.setText("");
            txtCaptcha.requestFocus();
            return;
        }
        else if (mk.length >= 6 && tentk.isEmpty()){
            jlblVerifyEmail.setText("Không được bỏ trống tên tài khoản!");            
            lblVerifyCaptcha.setText("");
            txtTenTaiKhoan.requestFocus();
            return;
        }
        else {
            jlblVerifyEmail.setText("");
            lblVerifyCaptcha.setText("");
            TaikhoanController taikhoanCtrl = new TaikhoanController();
            Taikhoan tk = taikhoanCtrl.getTaiKhoan(tentk);
            
            //Kiểm tra xem có tồn tại tên tài khoản như này không
            if (tk != null) {
                // Kiểm tra xem nhân viên của tài khoản này có bị khóa (Ẩn/xóa) hay không
                Nhanvien nv = new NhanvienController().getNhanVien(tk.getMaNhanvien());
                if (nv.isDaXoa() == true) {
                    JOptionPane.showMessageDialog(this, "Tài khoản này đã bị tạm khóa, do chủ nhân tài khoản này đã bị ẨN khỏi hệ thống!");
                    return;
                }

                //Default max rounds for hashing password toleration: 8
                var password = txtCaptcha.getPassword();
                if (password.length != 0) {              
                    if (BCrypt.checkpw(String.valueOf(password), tk.getMatkhau())) {
                        taiKhoanLogin = tk;
                        nhanVienLogin = nv;
                        quyenLogin = new QuyenController().getQuyen(taiKhoanLogin.getMaQuyen());

//                        //MainView withour resizing - to select, uncomment 2 lines belows this line & uncomment another codes from MainView class
//                        //new MainView().setVisible(true);
//                        //this.dispose();
                    } else {
                        lblVerifyCaptcha.setText("Captcha nhập vào không đúng!");
                        txtCaptcha.requestFocus();
                    }
                } else {
                    lblVerifyCaptcha.setText("Không được để trống captcha!");
                    txtCaptcha.requestFocus();
                }
            } else {
                jlblVerifyEmail.setText("Tài khoản vừa nhập không tồn tại!");
                txtTenTaiKhoan.requestFocus();
            }
        }
    }//GEN-LAST:event_btnResetPwdActionPerformed

    private void txtCaptchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaptchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaptchaActionPerformed
    
    private void txtCaptchaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtCaptchaInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaptchaInputMethodTextChanged

    private void txtTenTaiKhoanInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTenTaiKhoanInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTaiKhoanInputMethodTextChanged
    
    private boolean hasADigit(String password){   
        for(int i = 0; i < password.length(); i++){
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57) return true;
        }
        return false;
    }
    private boolean hasALowerChar(String password){   
        for(int i = 0; i < password.length(); i++){
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122) return true;
        }
        return false;
    }
    private boolean hasAnUpperChar(String password){   
        for(int i = 0; i < password.length(); i++){
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) return true;
        }
        return false;
    }
    private boolean hasASpecialChar(String password){   
        for(int i = 0; i < password.length(); i++){
            if ((password.charAt(i) >= 33 && password.charAt(i) <= 47) 
                || (password.charAt(i) >= 58 && password.charAt(i) <= 64) 
                || (password.charAt(i) >= 91 && password.charAt(i) <= 96) 
                || (password.charAt(i) >= 123 && password.charAt(i) <= 126)) 
            return true;
        }
        return false;
    }
    private int countSpecialChars(String password){ 
        int count = 0;
        for(int i = 0; i < password.length(); i++){
            if ((password.charAt(i) >= 33 && password.charAt(i) <= 47) 
                || (password.charAt(i) >= 58 && password.charAt(i) <= 64) 
                || (password.charAt(i) >= 91 && password.charAt(i) <= 96) 
                || (password.charAt(i) >= 123 && password.charAt(i) <= 126)) 
            count++;
        }
        return count;
    }
    private boolean hasAnImproperChar(String password){   
        for(int i = 0; i < password.length(); i++){
            if (password.charAt(i) < 32 || password.charAt(i) > 126) return true;
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
    private javax.swing.JLabel btnCloseLogin;
    private javax.swing.JPanel btnExit;
    private javax.swing.JButton btnResetPwd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblVerifyEmail;
    private javax.swing.JLabel lblCaptcha;
    private javax.swing.JLabel lblThongBaoResetPwd;
    private javax.swing.JLabel lblVerifyCaptcha;
    private javax.swing.JPanel loginBox;
    private javax.swing.JPanel overlay;
    private javax.swing.JPasswordField txtCaptcha;
    private javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
