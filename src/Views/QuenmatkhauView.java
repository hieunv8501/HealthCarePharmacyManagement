package Views;

import Models.Nhanvien;
import Models.Taikhoan;
import Models.Quyen;
import Helpers.Captcha;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import Helpers.Email;
import Controllers.TaikhoanController;
import Controllers.QuyenController;
import Controllers.NhanvienController;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;

public class QuenmatkhauView extends JFrame {

    int countCaptcha = 0;
    ImageIcon icon;
    Captcha img;
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public QuenmatkhauView() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
        overlay.setBackground(new Color(0, 0, 0, 150));
        //btnDangNhap.setBackground(new Color(0,0,0,0));
        btnExit.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Quên mật khẩu");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        setIconImage(logo.getImage());

        //Tạo captcha hình ảnh văn bản
        img = new Captcha();
        icon = new ImageIcon(img.ToImages());
        lblCaptcha.setIcon(icon);

        this.setLocationRelativeTo(null);
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
        txtEmail.addKeyListener(ka);
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
        txtEmail.addFocusListener(fl);
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
                    jlblVerifyTenTaiKhoan.setText("Không được để trống Tài khoản!");
                    txtTenTaiKhoan.requestFocus();
                } else if (username.length() >= 6) {
                    jlblVerifyTenTaiKhoan.setText("");
                    if ((hasADigit(username) || hasALowerChar(username) || hasAnUpperChar(username)) && (countSpecialChars(username) == 1) && (hasAnImproperChar(username) == false) && !username.contains(" ")) {
                        jlblVerifyTenTaiKhoan.setText("");
                    } else {
                        if (countSpecialChars(username) >= 2) {
                            jlblVerifyTenTaiKhoan.setText("Tên tài khoản không được chứa quá 1 ký tự đặc biệt!");
                        } else if (hasAnImproperChar(username) == true) {
                            jlblVerifyTenTaiKhoan.setText("Tên tài khoản không được chứa các ký tự lạ!");
                        } else if (username.contains(" ")) {
                            jlblVerifyTenTaiKhoan.setText("Tên tài khoản không được chứa ký tự trắng!");
                        }
                    }
                } else {
                    jlblVerifyTenTaiKhoan.setText("Tên tài khoản quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
        });
        txtEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txtEmail.getText().equals("")) {
                    lblVerifyEmail.setText("Không được để trống email!");
                } else if (!doesMatchPattern(txtEmail.getText())) {
                    lblVerifyEmail.setText("Email không hợp lệ, mời nhập lại!");
                } else {
                    lblVerifyEmail.setText("");
                }
            }
        });
        txtCaptcha.addKeyListener(new KeyListener() {
            String captchaa;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                captchaa = txtCaptcha.getText();
                if (captchaa.length() == 0) {
                    lblVerifyCaptcha.setText("Không được để trống Captcha!");
                } else if (captchaa.equals(img.getImageCodeCaptcha())) {
                    lblVerifyCaptcha.setText("");
                } else if (captchaa.length() != 0 && !captchaa.equals(img.getImageCodeCaptcha())) {
                    lblVerifyCaptcha.setText("Captcha nhập vào không đúng!");
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
        txtTenTaiKhoan = new javax.swing.JTextField();
        lblNameTaiKhoan = new javax.swing.JLabel();
        lblNameCaptcha = new javax.swing.JLabel();
        btnResetPwd = new javax.swing.JButton();
        btnExit = new javax.swing.JPanel();
        btnCloseLogin = new javax.swing.JLabel();
        lblVerifyEmail = new javax.swing.JLabel();
        jlblVerifyTenTaiKhoan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNameEmail = new javax.swing.JLabel();
        lblCaptcha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblThongBaoResetPwd = new javax.swing.JLabel();
        btnChangeCaptcha = new javax.swing.JButton();
        btnQuayLaiDN = new javax.swing.JButton();
        txtCaptcha = new javax.swing.JTextField();
        lblVerifyCaptcha = new javax.swing.JLabel();
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

        lblNameTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblNameTaiKhoan.setText("Tài khoản");
        loginBox.add(lblNameTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        lblNameCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameCaptcha.setForeground(new java.awt.Color(255, 255, 255));
        lblNameCaptcha.setText("Captcha");
        loginBox.add(lblNameCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 355, -1, -1));

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
        loginBox.add(btnResetPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, -1, 40));

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

        lblVerifyEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVerifyEmail.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblVerifyEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 325, 350, 25));

        jlblVerifyTenTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jlblVerifyTenTaiKhoan.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(jlblVerifyTenTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 243, 350, 25));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Healthcare Pharmacy Management System");
        loginBox.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 35));

        lblNameEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblNameEmail.setText("Email");
        loginBox.add(lblNameEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 272, -1, -1));

        lblCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCaptcha.setForeground(new java.awt.Color(240, 240, 240));
        lblCaptcha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaptcha.setToolTipText("");
        loginBox.add(lblCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 160, 50));

        txtEmail.setBackground(new java.awt.Color(255, 0, 0));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(102, 204, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEmail.setOpaque(false);
        loginBox.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 292, 258, 30));

        lblThongBaoResetPwd.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        lblThongBaoResetPwd.setForeground(new java.awt.Color(255, 255, 255));
        lblThongBaoResetPwd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBox.add(lblThongBaoResetPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 390, 25));

        btnChangeCaptcha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_refresh_30px.png"))); // NOI18N
        btnChangeCaptcha.setOpaque(false);
        btnChangeCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeCaptchaActionPerformed(evt);
            }
        });
        loginBox.add(btnChangeCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 40, 30));

        btnQuayLaiDN.setBackground(new java.awt.Color(0, 51, 255));
        btnQuayLaiDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQuayLaiDN.setForeground(new java.awt.Color(204, 255, 255));
        btnQuayLaiDN.setText("Quay lại đăng nhập");
        btnQuayLaiDN.setBorder(null);
        btnQuayLaiDN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuayLaiDN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQuayLaiDN.setOpaque(false);
        btnQuayLaiDN.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnQuayLaiDNMouseMoved(evt);
            }
        });
        btnQuayLaiDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuayLaiDNMouseExited(evt);
            }
        });
        btnQuayLaiDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiDNActionPerformed(evt);
            }
        });
        loginBox.add(btnQuayLaiDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 480, 140, 20));

        txtCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCaptcha.setForeground(new java.awt.Color(102, 204, 255));
        txtCaptcha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtCaptcha.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCaptcha.setOpaque(false);
        loginBox.add(txtCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 120, 30));

        lblVerifyCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVerifyCaptcha.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblVerifyCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 412, 350, 25));

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

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnResetPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPwdActionPerformed
        //Khởi tạo đối tượng và dẫn nhập từ bàn phím

        String tentk = txtTenTaiKhoan.getText();
        String email = txtEmail.getText();
        String captcha = txtCaptcha.getText();
        final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        
        if (tentk.isEmpty() && email.isEmpty() && captcha.isEmpty()) {
            jlblVerifyTenTaiKhoan.setText("Không được để trống tên tài khoản!");
            lblVerifyEmail.setText("Không được để trống email!");
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            txtTenTaiKhoan.requestFocus();
            return;
        } else if (tentk.length() >= 6 && email.isEmpty() && !captcha.isEmpty()) {
            lblVerifyEmail.setText("Không được để trống email!");
            jlblVerifyTenTaiKhoan.setText("");
            lblVerifyCaptcha.setText("");
            txtEmail.requestFocus();
            return;
        } else if (email.isEmpty() && tentk.isEmpty() && !captcha.isEmpty()) {
            jlblVerifyTenTaiKhoan.setText("Không được để trống tên tài khoản!");
            lblVerifyEmail.setText("");
            lblVerifyCaptcha.setText("");
            txtTenTaiKhoan.requestFocus();
            return;
        } else if (email.isEmpty() && !tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            lblVerifyEmail.setText("");
            jlblVerifyTenTaiKhoan.setText("");
            txtCaptcha.requestFocus();
            return;
        } else if (email.isEmpty() && tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            lblVerifyEmail.setText("");
            jlblVerifyTenTaiKhoan.setText("Không được để trống tên tài khoản!");
            txtTenTaiKhoan.requestFocus();
            return;
        } else if (email.isEmpty() && !tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            lblVerifyEmail.setText("Không được để trống email!");
            jlblVerifyTenTaiKhoan.setText("");
            txtEmail.requestFocus();
            return;
        } else if (email.isEmpty() && tentk.isEmpty() && !captcha.isEmpty()) {
            lblVerifyCaptcha.setText("");
            lblVerifyEmail.setText("Không được để trống email!");
            jlblVerifyTenTaiKhoan.setText("Không được để trống tên tài khoản!");
            txtTenTaiKhoan.requestFocus();
            return;
        } else if (!doesMatchPattern(email) && email.length() != 0) {
            lblVerifyEmail.setText("Email nhập vào không hợp lệ!");
            txtEmail.requestFocus();
            return;
        }
        else if (!captcha.equals("") && !captcha.equals(img.getImageCodeCaptcha())) {
            lblVerifyCaptcha.setText("Captcha nhập vào không đúng!");
            return;
        } else {
            //Kiểm tra xem có tồn tại tên tài khoản như này không
            TaikhoanController taikhoanCtrl = new TaikhoanController();
            Taikhoan tk = taikhoanCtrl.getTaiKhoan(tentk);
            if (tk != null) {
                //Check email and captcha

                // Kiểm tra xem nhân viên của tài khoản này có bị khóa (Ẩn/xóa) hay không
                Nhanvien nv = new NhanvienController().getNhanVien(tk.getNv().getMaNhanvien());
                if (nv.isDaXoa() == true) {
                    JOptionPane.showMessageDialog(this, "Tài khoản này đã bị tạm khóa, do chủ tài khoản này đã bị ẨN khỏi hệ thống!");
                    return;
                } else {                                     
                    Email em = new Email();
                    try {
                        lblThongBaoResetPwd.setText("Vui lòng chờ trong giây lát, hệ thống đang xử lý...");
                        em.sendEmail(tk, email);
                    } catch (MessagingException ex) {
                        Logger.getLogger(QuenmatkhauView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(QuenmatkhauView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lblThongBaoResetPwd.setText("Vui lòng kiếm tra tài khoản email để nhận mật khẩu mới!");
                }
            } else {
                jlblVerifyTenTaiKhoan.setText("Tài khoản vừa nhập không tồn tại!");
                txtTenTaiKhoan.requestFocus();
            }
        }

    }//GEN-LAST:event_btnResetPwdActionPerformed

    private void txtTenTaiKhoanInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTenTaiKhoanInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTaiKhoanInputMethodTextChanged

    private void btnQuayLaiDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiDNActionPerformed
        new DangnhapView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiDNActionPerformed

    private void btnQuayLaiDNMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiDNMouseMoved
        btnQuayLaiDN.setForeground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnQuayLaiDNMouseMoved

    private void btnQuayLaiDNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiDNMouseExited
        btnQuayLaiDN.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnQuayLaiDNMouseExited

    private void btnChangeCaptchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeCaptchaActionPerformed
        countCaptcha = 0;
        Captcha img = new Captcha();
        img.init();
        icon = new ImageIcon(img.ToImages());
        lblCaptcha.setIcon(icon);
    }//GEN-LAST:event_btnChangeCaptchaActionPerformed

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

    public static boolean doesMatchPattern(final String email) {
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
    }
    //Function check valid 
    //Static properties
    public static String saveFileName = "temp";
    public static Quyen quyenLogin;
    public static Nhanvien nhanVienLogin;
    public static Taikhoan taiKhoanLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeCaptcha;
    private javax.swing.JLabel btnCloseLogin;
    private javax.swing.JPanel btnExit;
    private javax.swing.JButton btnQuayLaiDN;
    private javax.swing.JButton btnResetPwd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlblVerifyTenTaiKhoan;
    private javax.swing.JLabel lblCaptcha;
    private javax.swing.JLabel lblNameCaptcha;
    private javax.swing.JLabel lblNameEmail;
    private javax.swing.JLabel lblNameTaiKhoan;
    private javax.swing.JLabel lblThongBaoResetPwd;
    private javax.swing.JLabel lblVerifyCaptcha;
    private javax.swing.JLabel lblVerifyEmail;
    private javax.swing.JPanel loginBox;
    private javax.swing.JPanel overlay;
    private javax.swing.JTextField txtCaptcha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
