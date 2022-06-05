package Views;

import Models.Nhanvien;
import Models.Taikhoan;
import Models.Quyen;
import Controllers.TaikhoanController;
import Controllers.QuyenController;
import Controllers.NhanvienController;
import Components.ExcelOperation;
import FormHelpers.Captcha;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginView extends JFrame {

    int countCaptcha = 0;
    ImageIcon icon;
    static Captcha img;
    public LoginView() {
        initComponents();
        overlay.setBackground(new Color(0, 0, 0, 150));
        //btnDangNhap.setBackground(new Color(0,0,0,0));
        btnExit.setBackground(new Color(0, 0, 0, 0));
        this.setTitle("Đăng nhập");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        setIconImage(logo.getImage());

        //Tạo captcha hình ảnh văn bản
        img = new Captcha();
        icon = new ImageIcon(img.ToImages());
        lblCaptcha.setIcon(icon);

        this.setLocationRelativeTo(null);
        //String alertStringLabel = "";
        // Thêm hotkey event enter
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
            }
        };

        txtTenDangNhap.addKeyListener(ka);
        txtMatKhau.addKeyListener(ka);
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
        txtTenDangNhap.addFocusListener(fl);
        txtMatKhau.addFocusListener(fl);
        txtCaptcha.addFocusListener(fl);

        // Tự động focus vào tên đăng nhập
        txtTenDangNhap.requestFocus();

        // Kiểm tra lần đăng nhập cũ (nhớ mật khẩu)
        String text = new ExcelOperation(saveFileName).read();
        if (!text.equals("")) {
            try {
                txtTenDangNhap.setText(text.split(" ")[0]);
                txtMatKhau.setText(text.split(" ")[1]);
                ckbNhoMatKhau.setSelected(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi nhớ mật khẩu");
            }
        }
        txtTenDangNhap.addKeyListener(new KeyListener() {
            String username = "";

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                username = txtTenDangNhap.getText();
                if (username.isEmpty()) {
                    jlblVerifyUsername.setText("Không được để trống tên tài khoản!");
                    txtTenDangNhap.requestFocus();
                } else if (username.length() >= 6) {
                    jlblVerifyUsername.setText("");
                    if ((hasADigit(username) || hasALowerChar(username) || hasAnUpperChar(username)) && (countSpecialChars(username) == 1) && (hasAnImproperChar(username) == false) && !username.contains(" ")) {
                        jlblVerifyUsername.setText("");
                    } else {
                        if (countSpecialChars(username) >= 2) {
                            jlblVerifyUsername.setText("Tên tài khoản không được chứa quá 1 ký tự đặc biệt!");
                        } else if (hasAnImproperChar(username) == true) {
                            jlblVerifyUsername.setText("Tên tài khoản không được chứa các ký tự lạ!");
                        } else if (username.contains(" ")) {
                            jlblVerifyUsername.setText("Tên tài khoản không được chứa ký tự trắng!");
                        }
                    }
                } else {
                    jlblVerifyUsername.setText("Tên tài khoản quá ngắn, cần ít nhất 6 ký tự!");
                }
            }
        });
        txtMatKhau.addKeyListener(new KeyListener() {
            char[] password;

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                password = txtMatKhau.getPassword();
                if (password.length == 0) {
                    jlblVerifyPwd.setText("Không được để trống mật khẩu!");
                    txtMatKhau.requestFocus();
                } else if (String.valueOf(password).length() >= 6) {
                    jlblVerifyPwd.setText("");
                    if (hasADigit(String.valueOf(password)) && hasALowerChar(String.valueOf(password)) && hasAnUpperChar(String.valueOf(password)) && hasASpecialChar(String.valueOf(password)) && (hasAnImproperChar(String.valueOf(password)) == false) && !String.valueOf(password).contains(" ")) {
                        jlblVerifyPwd.setText("");
                    } else {
                        if (String.valueOf(password).contains(" ") == true) {
                            jlblVerifyPwd.setText("Mật khẩu không được chứa ký tự trắng!");
                        } else if (hasADigit(String.valueOf(password)) == false) {
                            jlblVerifyPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự số!");
                        } else if (hasALowerChar(String.valueOf(password)) == false) {
                            jlblVerifyPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự thường!");
                        } else if (hasAnUpperChar(String.valueOf(password)) == false) {
                            jlblVerifyPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự hoa!");
                        } else if (hasAnImproperChar(String.valueOf(password)) == true) {
                            jlblVerifyPwd.setText("Mật khẩu không được chứa ký tự lạ!");
                        } else if (hasASpecialChar(String.valueOf(password)) == false) {
                            jlblVerifyPwd.setText("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!");
                        }
                    }
                } else {
                    jlblVerifyPwd.setText("Mật khẩu quá ngắn, cần ít nhất 6 ký tự!");
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
                } else if (captchaa.length() != 0 && captchaa != img.getImageCodeCaptcha()) {
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
        txtMatKhau = new javax.swing.JPasswordField();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        btnExit = new javax.swing.JPanel();
        btnCloseLogin = new javax.swing.JLabel();
        btnQuenMatKhau = new javax.swing.JLabel();
        jlblVerifyPwd = new javax.swing.JLabel();
        jlblVerifyUsername = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ckbNhoMatKhau = new javax.swing.JCheckBox();
        btnChangeCaptcha = new javax.swing.JButton();
        ckbHienMatKhau = new javax.swing.JCheckBox();
        lblNameCaptcha = new javax.swing.JLabel();
        txtCaptcha = new javax.swing.JTextField();
        lblVerifyCaptcha = new javax.swing.JLabel();
        lblCaptcha = new javax.swing.JLabel();
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

        txtMatKhau.setBackground(new java.awt.Color(255, 0, 0));
        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(102, 204, 255));
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtMatKhau.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMatKhau.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMatKhau.setOpaque(false);
        txtMatKhau.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtMatKhauInputMethodTextChanged(evt);
            }
        });
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });
        loginBox.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 258, 30));

        txtTenDangNhap.setBackground(new java.awt.Color(240, 240, 240));
        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenDangNhap.setForeground(new java.awt.Color(102, 204, 255));
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtTenDangNhap.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenDangNhap.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenDangNhap.setDragEnabled(true);
        txtTenDangNhap.setOpaque(false);
        txtTenDangNhap.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTenDangNhapInputMethodTextChanged(evt);
            }
        });
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        txtTenDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenDangNhapKeyPressed(evt);
            }
        });
        loginBox.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 258, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tài khoản");
        loginBox.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mật khẩu");
        loginBox.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        btnDangNhap.setBackground(new java.awt.Color(102, 102, 255));
        btnDangNhap.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setToolTipText("");
        btnDangNhap.setBorderPainted(false);
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btnDangNhap.setMaximumSize(new java.awt.Dimension(123, 40));
        btnDangNhap.setMinimumSize(new java.awt.Dimension(123, 40));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        loginBox.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 460, -1, 36));

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

        btnQuenMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        btnQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnQuenMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnQuenMatKhau.setText("Quên mật khẩu?");
        btnQuenMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuenMatKhauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuenMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuenMatKhauMouseExited(evt);
            }
        });
        loginBox.add(btnQuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 125, -1));
        btnQuenMatKhau.getAccessibleContext().setAccessibleName("Quên mật khẩu");

        jlblVerifyPwd.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jlblVerifyPwd.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(jlblVerifyPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 323, 350, 25));

        jlblVerifyUsername.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jlblVerifyUsername.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(jlblVerifyUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 242, 350, 25));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Healthcare Pharmacy Management System");
        loginBox.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 35));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_login.png"))); // NOI18N
        loginBox.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 220, 160));

        ckbNhoMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ckbNhoMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        ckbNhoMatKhau.setLabel("Nhớ mật khẩu");
        ckbNhoMatKhau.setOpaque(false);
        loginBox.add(ckbNhoMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, -1, -1));

        btnChangeCaptcha.setForeground(new java.awt.Color(240, 240, 240));
        btnChangeCaptcha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_refresh_30px.png"))); // NOI18N
        btnChangeCaptcha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangeCaptcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeCaptchaActionPerformed(evt);
            }
        });
        loginBox.add(btnChangeCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 40, 30));

        ckbHienMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ckbHienMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        ckbHienMatKhau.setText("Hiện mật khẩu");
        ckbHienMatKhau.setOpaque(false);
        ckbHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbHienMatKhauActionPerformed(evt);
            }
        });
        loginBox.add(ckbHienMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, -1));

        lblNameCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameCaptcha.setForeground(new java.awt.Color(255, 255, 255));
        lblNameCaptcha.setText("Captcha");
        loginBox.add(lblNameCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        txtCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCaptcha.setForeground(new java.awt.Color(102, 204, 255));
        txtCaptcha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtCaptcha.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCaptcha.setOpaque(false);
        loginBox.add(txtCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 373, 120, 30));

        lblVerifyCaptcha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVerifyCaptcha.setForeground(new java.awt.Color(255, 0, 0));
        loginBox.add(lblVerifyCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 405, 305, 30));
        loginBox.add(lblCaptcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 160, 50));

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
        jLabel4.setText("Dashboard Login");
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

    private void txtTenDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDangNhapKeyPressed

    }//GEN-LAST:event_txtTenDangNhapKeyPressed

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed

    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed

    }//GEN-LAST:event_txtMatKhauKeyPressed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuenMatKhauMouseClicked
        try {
            new QuenmatkhauView().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnQuenMatKhauMouseClicked

    //mouse hover for button DangKy
    private void btnQuenMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuenMatKhauMouseEntered
        btnQuenMatKhau.setForeground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnQuenMatKhauMouseEntered
    //mouse unhover for button DangKy
    private void btnQuenMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuenMatKhauMouseExited
        btnQuenMatKhau.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnQuenMatKhauMouseExited

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        //Khởi tạo đối tượng và dẫn nhập từ bàn phím
        String tentk = txtTenDangNhap.getText();
        char[] mk = txtMatKhau.getPassword();
        String captcha = txtCaptcha.getText();
        
        if (!txtCaptcha.getText().equals("") && !txtCaptcha.getText().equals(img.getImageCodeCaptcha())) {
                lblVerifyCaptcha.setText("Captcha nhập vào không đúng!");
                return;
        }
        
        if (tentk.isEmpty() && mk.length == 0 && captcha.isEmpty()) {
            jlblVerifyUsername.setText("Không được để trống tên tài khoản!");
            jlblVerifyPwd.setText("Không được để trống mật khẩu!");
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            txtTenDangNhap.requestFocus();
            return;
        } else if (tentk.length() >= 6 && mk.length == 0 && !captcha.isEmpty()) {
            jlblVerifyPwd.setText("Không được để trống mật khẩu!");
            jlblVerifyUsername.setText("");
            txtMatKhau.requestFocus();
            return;
        } else if (mk.length >= 6 && tentk.isEmpty() && !captcha.isEmpty()) {
            jlblVerifyUsername.setText("Không được để trống tên tài khoản!");
            jlblVerifyPwd.setText("");
            lblVerifyCaptcha.setText("");
            txtTenDangNhap.requestFocus();
            return;
        } else if (mk.length >= 6 && !tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            jlblVerifyPwd.setText("");
            jlblVerifyUsername.setText("");
            txtCaptcha.requestFocus();
            return;
        } else if (mk.length >= 6 && tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            jlblVerifyPwd.setText("");
            jlblVerifyUsername.setText("Không được để trống tên tài khoản!");
            txtTenDangNhap.requestFocus();
            return;
        } else if (mk.length == 0 && !tentk.isEmpty() && captcha.isEmpty()) {
            lblVerifyCaptcha.setText("Không được để trống captcha!");
            jlblVerifyPwd.setText("Không được để trống mật khẩu!");
            jlblVerifyUsername.setText("");
            txtMatKhau.requestFocus();
            return;
        } else if (mk.length == 0 && tentk.isEmpty() && !captcha.isEmpty()) {
            lblVerifyCaptcha.setText("");
            jlblVerifyPwd.setText("Không được để trống mật khẩu!");
            jlblVerifyUsername.setText("Không được để trống tên tài khoản!");
            txtTenDangNhap.requestFocus();
            return;
        }
        else {
            
            jlblVerifyUsername.setText("");
            jlblVerifyPwd.setText("");
            TaikhoanController taikhoanCtrl = new TaikhoanController();
            Taikhoan tk = taikhoanCtrl.getTaiKhoan(tentk);

            //Kiểm tra xem có tồn tại tên tài khoản như này không
            if (tk != null) {
                // Kiểm tra xem nhân viên của tài khoản này có bị khóa (Ẩn/xóa) hay không
                Nhanvien nv = new NhanvienController().getNhanVien(tk.getNv().getMaNhanvien());
                if (nv.isDaXoa() == true) {
                    JOptionPane.showMessageDialog(this, "Tài khoản này đã bị tạm khóa, do chủ nhân tài khoản này đã bị ẨN khỏi hệ thống!");
                    return;
                }

                //Default max rounds for hashing password toleration: 8
                var password = txtMatKhau.getPassword();
                if (password.length != 0) {
                    if (BCrypt.checkpw(String.valueOf(password), tk.getMatkhau())) {
                        taiKhoanLogin = tk;
                        nhanVienLogin = nv;
                        quyenLogin = new QuyenController().getQuyen(taiKhoanLogin.getQ().getMaQuyen());

                        // Đăng nhập thành công
                        if (ckbNhoMatKhau.isSelected()) {
                            // Nếu nhớ tài khoản thì lưu tài khoản đăng nhập vào file storage
                            new ExcelOperation(saveFileName).write(taiKhoanLogin.getTaikhoan() + " " + taiKhoanLogin.getMatkhau());
                        } else {
                            // Nếu không thì xóa mọi dữ liệu trong file storage
                            new ExcelOperation(saveFileName).write("");
                        }

//                        // Khởi tạo một layout mainView mới               
//                        // Mainview with resize, status: testing
                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        int frameWidth = 1540;
                        int frameHeight = 815;
                        Point initialLocation = new Point((int) toolkit.getScreenSize().getWidth() / 2 - frameWidth / 2, (int) toolkit.getScreenSize().getHeight() / 2 - frameHeight / 2);
                        Dimension initialDimension = new Dimension(frameWidth, frameHeight);
                        MainView mainView = new MainView(initialDimension, initialLocation);

                        JPanel viewContainer = (JPanel) mainView.getContentPane();
                        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
                        headerPanel.setPreferredSize(new Dimension(frameWidth, 35));
                        headerPanel.addMouseListener(mainView);
                        headerPanel.addMouseMotionListener(mainView);
                        viewContainer.add(headerPanel, BorderLayout.NORTH);
                        mainView.setVisible(true);
//
//                        //MainView withour resizing - to select, uncomment 2 lines belows this line & uncomment another codes from MainView class
//                        //new MainView().setVisible(true);
                        this.dispose();
                    } else {
                        jlblVerifyPwd.setText("Mật khẩu không đúng!");
                        txtMatKhau.requestFocus();
                    }
                } else {
                    jlblVerifyPwd.setText("Không được để trống mật khẩu!");
                    txtMatKhau.requestFocus();
                }
            } else {
                jlblVerifyUsername.setText("Tài khoản vừa nhập không tồn tại!");
                txtTenDangNhap.requestFocus();
            }
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void txtMatKhauInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtMatKhauInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauInputMethodTextChanged

    private void txtTenDangNhapInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTenDangNhapInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapInputMethodTextChanged

    private void ckbHienMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbHienMatKhauActionPerformed
        // TODO add your handling code here:
        if (ckbHienMatKhau.isSelected())
            txtMatKhau.setEchoChar((char) 0);
        else
            txtMatKhau.setEchoChar('\u25cf');
    }//GEN-LAST:event_ckbHienMatKhauActionPerformed

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
    //Function check valid 
    //Static properties
    public static String saveFileName = "temp";
    public static Quyen quyenLogin;
    public static Nhanvien nhanVienLogin;
    public static Taikhoan taiKhoanLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeCaptcha;
    private javax.swing.JLabel btnCloseLogin;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JPanel btnExit;
    private javax.swing.JLabel btnQuenMatKhau;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox ckbHienMatKhau;
    private javax.swing.JCheckBox ckbNhoMatKhau;
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
    private javax.swing.JLabel jlblVerifyPwd;
    private javax.swing.JLabel jlblVerifyUsername;
    private javax.swing.JLabel lblCaptcha;
    private javax.swing.JLabel lblNameCaptcha;
    private javax.swing.JLabel lblVerifyCaptcha;
    private javax.swing.JPanel loginBox;
    private javax.swing.JPanel overlay;
    private javax.swing.JTextField txtCaptcha;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}

class capcha {

    private static String img;
    //The width of the verification code image.
    private int width = 160;
    //The height of the verification code image.
    private int height = 40;
    //Number of characters in the verification code
    private int codeCount = 6;
    private Font font, rotatedFont;
    AffineTransform affineTransform;
    private int codeX = 0;
    //font height
    private int fontHeight;
    private int codeY;
    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public capcha() {
        init();
        ToImages();
    }

    /**
     * Initialize and verify image properties
     */
    public void init() {
        codeX = (width - 40) / (codeCount + 1);
        fontHeight = height - 10;
        codeY = height - 10;
    }

    public static String getImageCodeCaptcha() {
        return img;
    }

    public BufferedImage ToImages() {
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
            Graphics2D g = buffImg.createGraphics();
            //Create a random number generator class
            Random random = new Random();
            //Fill the image with white
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            //Create a font, the size of the font should be determined according to the height of the picture.

            //Draw the border.
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width - 1, height - 1);
            //Randomly generate an interference line, so that the authentication code in the image is not easily detected by other programs.
            for (int i = 0; i < 300; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                g.drawOval(x, y, 1, 1);
            }

            //randomCode is used to save the randomly generated verification code so that the user can log in for verification.
            StringBuffer randomCode = new StringBuffer();
            int red = 0, green = 0, blue = 0;

            //Randomly generate a verification code with codeCount numbers.
            for (int i = 0; i < codeCount; i++) {
                //Get the randomly generated verification code number.
                String strRand = String.valueOf(codeSequence[random.nextInt(62)]);
                int randRadInt = new SecureRandom().nextInt(35 + 35) - 35;
                //System.out.println(randRadInt);
                switch (i) {
                    case 0:
                        font = new Font("Arial", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    case 1:
                        font = new Font("Segoe UI", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    case 2:
                        font = new Font("Times New Roman", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    case 3:
                        font = new Font("Serif", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    case 4:
                        font = new Font("SansSerif", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    case 5:
                        font = new Font("Verdana", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                    default:
                        font = new Font("Roboto", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);
                        break;
                }
                //Set the font
                g.setFont(rotatedFont);
                //Generate random color components to construct the color value, so that the color value of each number output will be different.
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);
                while (red == 255 && green == 255 && blue == 255) {
                    red = random.nextInt(255);
                    green = random.nextInt(255);
                    blue = random.nextInt(255);
                }
                //Draw the verification code into the image with a randomly generated color.              
                g.setColor(new Color(red, green, blue));
                g.drawString(strRand, i * codeX + codeX, codeY + 1);
                //Combine the four random numbers generated.
                randomCode.append(strRand);
            }
            img = randomCode.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffImg;
    }
}
