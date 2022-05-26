package Views;

import Components.ExcelOperation;
import Components.SidebarButton;
import Components.SidebarContainer;
import Components.SidebarSeperator;
import Components.SidebarTitle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainView extends JFrame implements MouseListener, MouseMotionListener {

    //old statement
    //final int WIDTH = 1500, HEIGHT = 820;
    int px, py;
    SidebarContainer menu, header;
    SidebarButton currentTab;
    SidebarTitle headerTitle;
    JPanel plContent = new JPanel();

    //Khởi tạo các đối tượng trang JPanel và add các đối tượng trang ở switch case phía dưới bên trong method doAction để chuyển trang, ví dụ: Empty Page - Trang trắng
    EmptyPage emptypage = new EmptyPage();
    KhuyenmaiView khuyenmai = new KhuyenmaiView();
    HoadonView hoadon = new HoadonView();

    //test statement
    //private static final long SERIAL_VERSION_UID = 1L;        
    private Point dragStartPoint = new Point(0, 0);
    private Point startLocation = new Point(0, 0);
    private Point precedentLocation;
    private int precedentWidth;
    private int precedentHeight;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private int minWidth;
    private int minHeight;
    private Point initialLocation;
    int cursorArea = 5;
    Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private final int DIFF_MIN_WIDTH = 30;
    private final int DIFF_MIN_HEIGHT = 30;

    public MainView(Dimension initialDimension, Point initialLocation) {
        this.initialLocation = initialLocation;
        minWidth = (int) initialDimension.getWidth();
        minHeight = (int) initialDimension.getHeight() + 10;

        setLocation(initialLocation);
        setLayout(new BorderLayout());
        //setSize(WIDTH, HEIGHT);
        this.setSize(minWidth, minHeight);
        //Title d.app
        setTitle("Quản Lý Nhà Thuốc Healthcare Pharmacy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        //setBackground(Color.blue);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
        setIconImage(logo.getImage());

        //Menu sidebar items
        String[] navItemInfo = {
            "seperate", "1", "", "",
            "Bán thuốc", "icons8_small_business_30px_3.png", "qlBanThuoc", "qlBanThuoc",
            "Nhập thuốc", "icons8_truck_30px.png", "qlNhapThuoc", "qlNhapThuoc",
            "seperate", "1", "", "",
            "Thuốc", "icons8_antibiotic_30px.png", "xemThuoc", "qlThuoc",
            "Hóa đơn", "icons8_bill_30px.png", "xemHoaDon", "qlHoaDon",
            "Khuyến mãi", "icons8_gift_30px.png", "xemKhuyenMai", "qlKhuyenMai",
            "seperate", "1", "", "",
            "Nhân viên", "icons8_assistant_30px.png", "xemNhanVien", "qlNhanVien",
            "Khách hàng", "icons8_user_30px.png", "xemKhachHang", "qlKhachHang",
            "Nhà cung cấp", "icons8_company_30px.png", "xemNCC", "qlNCC",
            "seperate", "1", "", "",
            "Tài khoản", "icons8_key_30px.png", "xemTaiKhoan", "qlTaiKhoan",
            "Quyền", "icons8_police_badge_30px.png", "xemQuyen", "qlQuyen",
            "seperate", "1", "", "",
            "Thống kê", "icons8_bar_chart_30px.png", "", "",
            "Công cụ", "icons8_maintenance_30px.png", "", "",
            "Cài đặt", "icons8_settings_30px.png", "", ""
        };

        int menuW = (int) (minWidth / 8);
        int menuH = 0;
        menu = new SidebarContainer(new Rectangle(0, 0, menuW, minHeight));
        //menu.addItem(new SidebarTitle(new Rectangle(0, 0, 0, 55), "CHỨC NĂNG"));
        for (int i = 0; i < navItemInfo.length; i += 4) {
            //Thanh ngăn cách
            if (navItemInfo[i].equals("seperate")) {
                SidebarSeperator s = new SidebarSeperator(new Rectangle(0, 0, 1, Integer.parseInt(navItemInfo[i + 1])));
                menu.addItem(s);

            } else {
                //cài đặt các tab ở sidebar
                String chitietquyen = LoginForm.quyenLogin.getChitietQuyen();
                if (chitietquyen.contains(navItemInfo[i + 2]) || chitietquyen.contains(navItemInfo[i + 3])) {
                    SidebarButton sb = new SidebarButton(new Rectangle(0, 0, 0, 46), navItemInfo[i], navItemInfo[i + 1]);
                    sb.addMouseListener(this);
                    sb.setToolTipText("Quản lý " + navItemInfo[i].toString());
                    menu.addItem(sb);
                    menuH += 46;
                }
            }
        }

        //https://stackoverflow.com/questions/1385737/scrollable-jpanel
        //https://stackoverflow.com/questions/5590242/how-to-hide-the-jscrollbars-in-a-jscrollpane
        //https://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
        //cài đặt JScrollPane
        JScrollPane scrollMenu = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menu.setAutoscrolls(true);
        menu.setPreferredSize(new Dimension(menuW, menuH));
        scrollMenu.setPreferredSize(new Dimension(menuW, minHeight));
        scrollMenu.setBorder(BorderFactory.createEmptyBorder());
        scrollMenu.getVerticalScrollBar().setUnitIncrement(5);

        //Sidebar header title
        int headerBg = 30;
        int headerH = 35;
        header = new SidebarContainer(new Rectangle(0, 0, minWidth, headerH));
        header.setBackground(new Color(35, 35, 112));

        headerTitle = new SidebarTitle(new Rectangle((minWidth - 400) / 2, 0, 400, headerH), "QUẢN LÝ NHÀ THUỐC HP");
        headerTitle.setColorDefault(new Color(255, 255, 255));
        headerTitle.setBgDefault(new Color(35, 35, 112));
        headerTitle.setFontSize(18);
        header.addItem(headerTitle, false);

        //Button thoát phần mềm quản lý
        int btnWidth = 40;
        int iconSize = 20;
        SidebarButton btnClose = new SidebarButton(new Rectangle(minWidth - btnWidth, 0, btnWidth, headerH), "", "icons8_close_20px.png");
        btnClose.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
        btnClose.setBgDefault(new Color(35, 35, 112));
        btnClose.setForeground(new Color(255, 255, 255));
        btnClose.setBgHover(new Color(190, 45, 45));
        btnClose.setToolTipText("Close - Thoát phần mềm");
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int reply = JOptionPane.showConfirmDialog(getRootPane(),
                        "Bạn có chắc muốn thoát chương trình Quản Lý?", "Chú ý",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        header.addItem(btnClose, false);

        // Button thu nhỏ minimize
        SidebarButton btnMinimize = new SidebarButton(new Rectangle(minWidth - btnWidth * 2, 0, btnWidth, headerH), "", "icons8_subtract_20px.png");
        btnMinimize.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
        btnMinimize.setBgDefault(new Color(35, 35, 112));
        btnMinimize.setBgHover(new Color(190, 49, 49));
        btnMinimize.setToolTipText("Minimize - Tạm ẩn");
        btnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                setState(ICONIFIED);
            }
        });
        header.addItem(btnMinimize, false);

        //Button kết thúc phiên làm việc
        if (LoginForm.taiKhoanLogin != null) {

            String tenNhanVien = LoginForm.nhanVienLogin.getTenNhanvien();

            SidebarButton btnLogout = new SidebarButton(new Rectangle(0, 0, menuW - btnWidth, headerH), tenNhanVien, "icons8_logout_20px.png");
            btnLogout.setBgDefault(new Color(35, 35, 112));
            btnLogout.setBgHover(new Color(190, 49, 49));
            btnLogout.relocate2();
            btnLogout.setToolTipText("Kết thúc phiên làm việc (" + tenNhanVien + " - " + LoginForm.nhanVienLogin.getMaNhanvien() + ")");
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    logout();
                }
            });
            header.addItem(btnLogout, false);

            //Button cài đặt tài khoản
            SidebarButton btnSettingUser = new SidebarButton(new Rectangle(menuW - btnWidth, 0, btnWidth, headerH), "", "icons8_settings_20px.png");
            btnSettingUser.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
            btnSettingUser.setBgDefault(new Color(35, 35, 112));
            btnSettingUser.setBgHover(new Color(190, 49, 49));
            btnSettingUser.setToolTipText("Cài đặt tài khoản");
            btnSettingUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    //new DoiMatKhauForm(LoginForm.taiKhoanLogin.getUsername()).setVisible(true);
                }
            });
            header.addItem(btnSettingUser, false);
        }

        //Các function hỗ trợ kéo thả header
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                px = me.getX();
                py = me.getY();
            }
        });
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - px, getLocation().y + me.getY() - py);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                dragStartPoint = me.getLocationOnScreen();
                startLocation = getLocation();
                if (getWidth() < screen.getWidth() || getHeight() < screen.getHeight()) {
                    precedentLocation = getLocation();
                    precedentWidth = getWidth();
                    precedentHeight = getHeight();
                }
            }
        });
        plContent.setLayout(new BorderLayout());
        plContent.add(new BeginForm("Xin chào " + LoginForm.nhanVienLogin.getTenNhanvien() + " - " + LoginForm.nhanVienLogin.getMaNhanvien()), BorderLayout.CENTER);

        //addMouseListener(this);
        add(scrollMenu, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(plContent, BorderLayout.CENTER);

        //Function hỗ trợ resize :(
        minWidth -= DIFF_MIN_WIDTH;
        minHeight -= DIFF_MIN_HEIGHT;
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    //Outside the MainView Constructor
    //Func logout
    private void logout() {
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn kết thúc phiên làm việc của " + LoginForm.nhanVienLogin.getTenNhanvien() + "?", "Chú ý",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            new ExcelOperation(LoginForm.saveFileName).write(""); // xóa dữ liệu đăng nhập
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }

    //Func to show sidebar content, add các đối tượng trang ở đây, như ví dụ ở dưới
    public void doAction(String nameAction) {
        plContent.removeAll();
        //Đi tới các trang
        switch (nameAction) {
            case "Công cụ":
                emptypage.setLabelText("Công cụ đang bảo trì");
                plContent.add(emptypage, BorderLayout.CENTER);
                break;

            case "Cài đặt":
                emptypage.setLabelText("Cài đặt đang bảo trì");
                plContent.add(emptypage, BorderLayout.CENTER);
                break;

            case "Khuyến mãi":
                plContent.add(khuyenmai, BorderLayout.CENTER);
                break;

            case "Hóa đơn":
                plContent.add(hoadon, BorderLayout.CENTER);
                break;
        }
        headerTitle.setLabel(nameAction.toUpperCase());
        // https://stackoverflow.com/questions/12989388/switching-panels-with-menubar

        revalidate();//Refresh UI và Layout
        repaint();
    }

    //override methods
    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getSource() instanceof SidebarButton) {

            SidebarButton btn = (SidebarButton) me.getSource();
            if (currentTab != null) {
                currentTab.setActive(false);
            }
            btn.setActive(true);
            currentTab = btn;
            doAction(btn.text);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        Point cursorLocation = me.getPoint();
        int xPos = cursorLocation.x;
        int yPos = cursorLocation.y;

        if (xPos >= cursorArea && xPos <= getWidth() - cursorArea && yPos >= getHeight() - cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else if (xPos >= getWidth() - cursorArea && yPos >= cursorArea && yPos <= getHeight() - cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (xPos <= cursorArea && yPos >= cursorArea && yPos <= getHeight() - cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        } else if (xPos >= cursorArea && xPos <= getWidth() - cursorArea && yPos <= cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else if (xPos <= cursorArea && yPos <= cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        } else if (xPos >= getWidth() - cursorArea && yPos <= cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        } else if (xPos >= getWidth() - cursorArea && yPos >= getHeight() - cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else if (xPos <= cursorArea && yPos >= getHeight() - cursorArea) {
            setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //di chuyển full size màn hình
        moveOrFullResizeFrame(me);
    }

    //Double clicks
    @Override
    public void mouseClicked(MouseEvent me) {
        Object sourceObject = me.getSource();
        if (sourceObject instanceof JPanel) {
            if (me.getClickCount() == 2) {
                if (getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
                    headerDoubleClickResize();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    public static Point getScreenLocation(MouseEvent me, JFrame frame) {
        Point cursor = me.getPoint();
        Point view_location = frame.getLocationOnScreen();
        return new Point((int) (view_location.getX() + cursor.getX()), (int) (view_location.getY() + cursor.getY()));
    }

    private void headerDoubleClickResize() {
        if (getWidth() < screen.getWidth() || getHeight() < screen.getHeight()) {
            this.setSize((int) screen.getWidth(), (int) screen.getHeight());
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = this.getSize();
            this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        } else {
            this.setSize(precedentWidth, precedentHeight);
            this.setLocation(precedentLocation);
        }
    }

    private void moveOrFullResizeFrame(MouseEvent me) {
        Object sourceObject = me.getSource();
        Point current = getScreenLocation(me, this);
        Point offset = new Point((int) current.getX() - (int) dragStartPoint.getX(), (int) current.getY() - (int) dragStartPoint.getY());

        if (sourceObject instanceof JPanel
                && getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
            setLocation((int) (startLocation.getX() + offset.getX()), (int) (startLocation.getY() + offset.getY()));
        } else if (!getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
            int oldLocationX = (int) getLocation().getX();
            int oldLocationY = (int) getLocation().getY();
            int newLocationX = (int) (this.startLocation.getX() + offset.getX());
            int newLocationY = (int) (this.startLocation.getY() + offset.getY());
            boolean N_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
            boolean NE_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
            boolean NW_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
            boolean E_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            boolean W_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
            boolean S_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            boolean SW_Resize = getCursor().equals(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
            boolean setLocation = false;
            int newWidth = me.getX();
            int newHeight = me.getY();

            if (NE_Resize) {
                newHeight = getHeight() - (newLocationY - oldLocationY);
                newLocationX = (int) getLocation().getX();
                setLocation = true;
            } else if (E_Resize) {
                newHeight = getHeight();
            } else if (S_Resize) {
                newWidth = getWidth();
            } else if (N_Resize) {
                newLocationX = (int) getLocation().getX();
                newWidth = getWidth();
                newHeight = getHeight() - (newLocationY - oldLocationY);
                setLocation = true;
            } else if (NW_Resize) {
                newWidth = getWidth() - (newLocationX - oldLocationX);
                newHeight = getHeight() - (newLocationY - oldLocationY);
                setLocation = true;
            } else if (NE_Resize) {
                newHeight = getHeight() - (newLocationY - oldLocationY);
                newLocationX = (int) getLocation().getX();
            } else if (SW_Resize) {
                newWidth = getWidth() - (newLocationX - oldLocationX);
                newLocationY = (int) getLocation().getY();
                setLocation = true;
            }
            if (W_Resize) {
                newWidth = getWidth() - (newLocationX - oldLocationX);
                newLocationY = (int) getLocation().getY();
                newHeight = getHeight();
                setLocation = true;
            }
            if (newWidth >= (int) toolkit.getScreenSize().getWidth() || newWidth <= minWidth) {
                newLocationX = oldLocationX;
                newWidth = getWidth();
            }
            if (newHeight >= (int) toolkit.getScreenSize().getHeight() - 30 || newHeight <= minHeight) {
                newLocationY = oldLocationY;
                newHeight = getHeight();
            }
            if (newWidth != getWidth() || newHeight != getHeight()) {
                this.setSize(newWidth, newHeight);
                if (setLocation) {
                    this.setLocation(newLocationX, newLocationY);
                }
            }
        }
    }

//    final int WIDTH = 1500, HEIGHT = 820;
//    int px, py;
//    SidebarContainer menu, header;
//    SidebarButton currentTab;
//    SidebarTitle headerTitle;
//
//    JPanel plContent = new JPanel();
//    EmptyPage emptypage = new EmptyPage();
//
//    public MainView() {
//        setLayout(new BorderLayout());
//        setSize(WIDTH, HEIGHT);
//        setTitle("Quản Lý Nhà Thuốc Healthcare Pharmacy");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true);
//        setLocationRelativeTo(null);
//        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
//
//        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logo_login.png"));
//        setIconImage(logo.getImage());
//        
//        //Menu sidebar items
//        String[] navItemInfo = {
//            "seperate", "1", "", "",
//            "Bán thuốc", "icons8_small_business_30px_3.png", "qlBanThuoc", "qlBanThuoc",
//            "Nhập thuốc", "icons8_truck_30px.png", "qlNhapThuoc", "qlNhapThuoc",
//            "seperate", "1", "", "",
//            "Thuốc", "icons8_antibiotic_30px.png", "xemThuoc", "qlThuoc",
//            "Hóa đơn", "icons8_bill_30px.png", "xemHoaDon", "qlHoaDon",
//            "Khuyến mãi", "icons8_gift_30px.png", "xemKhuyenMai", "qlKhuyenMai",
//            "seperate", "1", "", "",
//            "Nhân viên", "icons8_assistant_30px.png", "xemNhanVien", "qlNhanVien",
//            "Khách hàng", "icons8_user_30px.png", "xemKhachHang", "qlKhachHang",
//            "Nhà cung cấp", "icons8_company_30px.png", "xemNCC", "qlNCC",
//            "seperate", "1", "", "",
//            "Tài khoản", "icons8_key_30px.png", "xemTaiKhoan", "qlTaiKhoan",
//            "Quyền", "icons8_police_badge_30px.png", "xemQuyen", "qlQuyen",
//            "seperate", "1", "", "",
//            "Thống kê", "icons8_bar_chart_30px.png", "", "",
//            "Công cụ", "icons8_maintenance_30px.png", "", "",
//            "Cài đặt", "icons8_settings_30px.png", "", ""
//        };
//
//        int menuW = 220;
//        int menuH = 0;
//        menu = new SidebarContainer(new Rectangle(0, 0, menuW, HEIGHT));
//        // menu.addItem(new SidebarTitle(new Rectangle(0, 0, 0, 55), "CHỨC NĂNG"));
//        for (int i = 0; i < navItemInfo.length; i += 4) {
//            if (navItemInfo[i].equals("seperate")) {
//                SidebarSeperator s = new SidebarSeperator(new Rectangle(0, 0, 0, Integer.parseInt(navItemInfo[i + 1])));
//                menu.addItem(s);
//
//            } else {
//
//                String chitietquyen = LoginForm.quyenLogin.getChitietQuyen();
//                if (chitietquyen.contains(navItemInfo[i + 2]) || chitietquyen.contains(navItemInfo[i + 3])) {
//                    SidebarButton nb = new SidebarButton(new Rectangle(0, 0, 0, 50), navItemInfo[i], navItemInfo[i + 1]);
//                    nb.addMouseListener(this);
//                    menu.addItem(nb);
//                    menuH += 50;
//                }
//            }
//        }
//
//        //https://stackoverflow.com/questions/1385737/scrollable-jpanel
//        //https://stackoverflow.com/questions/5590242/how-to-hide-the-jscrollbars-in-a-jscrollpane
//        //https://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
//        JScrollPane scrollMenu = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        menu.setAutoscrolls(true);
//        menu.setPreferredSize(new Dimension(menuW, menuH + 100));
//        scrollMenu.setPreferredSize(new Dimension(menuW, HEIGHT));
//        scrollMenu.setBorder(BorderFactory.createEmptyBorder());
//        scrollMenu.getVerticalScrollBar().setUnitIncrement(5);
//
//        //Sidebar
//        int headerBg = 30;
//        int headerH = 55;
//        header = new SidebarContainer(new Rectangle(0, 0, WIDTH, headerH));
//        header.setBackground(new Color(headerBg, headerBg, headerBg));
//
//        headerTitle = new SidebarTitle(new Rectangle((WIDTH - 400) / 2, 0, 400, headerH), "QUẢN LÝ NHÀ THUỐC HP");
//        headerTitle.setColorDefault(new Color(255, 255, 255));
//        headerTitle.setBgDefault(new Color(headerBg, headerBg, headerBg));
//        headerTitle.setFontSize(23);
//        header.addItem(headerTitle, false);
//
//        //Button thoát phần mềm quản lý
//        int btnWidth = 50;
//        int iconSize = 30;
//        SidebarButton btnClose = new SidebarButton(new Rectangle(WIDTH - btnWidth, 0, btnWidth, headerH), "", "icons8_shutdown_30px_1.png");
//        btnClose.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
//        btnClose.setBgDefault(new Color(headerBg, headerBg, headerBg));
//        btnClose.setBgHover(new Color(190, 45, 45));
//        btnClose.setToolTipText("Thoát");
//        btnClose.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent me) {
//                int reply = JOptionPane.showConfirmDialog(getRootPane(),
//                        "Bạn có chắc muốn thoát chương trình Quản Lý?", "Chú ý",
//                        JOptionPane.YES_NO_OPTION);
//                if (reply == JOptionPane.YES_OPTION) {
//                    System.exit(0);
//                }
//            }
//        });
//        header.addItem(btnClose, false);
//
//        // Button thu nhỏ minimize
//        SidebarButton btnMinimize = new SidebarButton(new Rectangle(WIDTH - btnWidth * 2, 0, btnWidth, headerH), "", "icons8_angle_down_30px.png");
//        btnMinimize.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
//        btnMinimize.setBgDefault(new Color(headerBg, headerBg, headerBg));
//        btnMinimize.setBgHover(new Color(49, 49, 49));
//        btnMinimize.setToolTipText("Thu nhỏ");
//        btnMinimize.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent me) {
//                setState(ICONIFIED);
//            }
//        });
//        header.addItem(btnMinimize, false);
//
//        //Button kết thúc phiên làm việc
//        if (LoginForm.taiKhoanLogin != null) {
//
//            String tenNhanVien = LoginForm.nhanVienLogin.getTenNhanvien();
//
//            SidebarButton btnLogout = new SidebarButton(new Rectangle(0, 0, menuW - btnWidth, headerH), tenNhanVien, "icons8_exit_30px.png");
//            btnLogout.setBgDefault(new Color(headerBg, headerBg, headerBg));
//            btnLogout.setBgHover(new Color(49, 49, 49));
//            btnLogout.relocate2();
//            btnLogout.setToolTipText("Kết thúc phiên làm việc (" + tenNhanVien + " - " + LoginForm.nhanVienLogin.getMaNhanvien()+ ")");
//            btnLogout.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent me) {
//                    logout();
//                }
//            });
//            header.addItem(btnLogout, false);
//
//            //Button xem thông tin tài khoản đăng nhập
//            SidebarButton btnSettingUser = new SidebarButton(new Rectangle(menuW - btnWidth, 0, btnWidth, headerH), "", "icons8_settings_30px_1.png");
//            btnSettingUser.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
//            btnSettingUser.setBgDefault(new Color(headerBg, headerBg, headerBg));
//            btnSettingUser.setBgHover(new Color(49, 49, 49));
//            btnSettingUser.setToolTipText("Tài khoản");
//            btnSettingUser.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent me) {
//                    //new DoiMatKhauForm(LoginForm.taiKhoanLogin.getUsername()).setVisible(true);
//                }
//            });
//            header.addItem(btnSettingUser, false);
//        }
//
//        //Các function hỗ trợ kéo thả header
//        header.addMouseListener(new MouseAdapter() {       
//            @Override
//            public void mousePressed(MouseEvent me) {
//                px = me.getX();
//                py = me.getY();
//            }
//        });
//        header.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent me) {
//                setLocation(getLocation().x + me.getX() - px, getLocation().y + me.getY() - py);
//            }
//        });
//
//        plContent.setLayout(new BorderLayout());
//        plContent.add(new BeginForm("Chào " + LoginForm.nhanVienLogin.getTenNhanvien()+ " - " + LoginForm.nhanVienLogin.getMaNhanvien()), BorderLayout.CENTER);
//
//        addMouseListener(this);
//        add(scrollMenu, BorderLayout.WEST);
//        add(header, BorderLayout.NORTH);
//        add(plContent, BorderLayout.CENTER);
//        
//        //Function hỗ trợ resize
//        
//
//    }
//    
//    //Func logout
//    private void logout() {
//        int reply = JOptionPane.showConfirmDialog(getRootPane(),
//                "Bạn có chắc muốn kết thúc phiên làm việc của " + LoginForm.nhanVienLogin.getTenNhanvien()+ "?", "Chú ý",
//                JOptionPane.YES_NO_OPTION);
//
//        if (reply == JOptionPane.YES_OPTION) {
//            new ExcelOperation(LoginForm.saveFileName).write(""); // xóa dữ liệu đăng nhập
//            new LoginForm().setVisible(true);
//            this.dispose();
//        }
//    }
//    
//    //Func to show sidebar content
//    public void doAction(String nameAction) {
//        plContent.removeAll();
//        switch (nameAction) {     
//            case "Công cụ":
//                emptypage.setLabelText("Công cụ đang bảo trì");
//                plContent.add(emptypage, BorderLayout.CENTER);
//                break;
//
//            case "Cài đặt":
//                emptypage.setLabelText("Cài đặt đang bảo trì");
//                plContent.add(emptypage, BorderLayout.CENTER);
//                break;
//        }
//        headerTitle.setLabel(nameAction.toUpperCase());
//        // https://stackoverflow.com/questions/12989388/switching-panels-with-menubar
//        revalidate();//refresh ui and layout
//        repaint();
//    }
//
//    //override methods
//    @Override
//    public void mouseReleased(MouseEvent me) {
//        if (me.getSource() instanceof SidebarButton) {
//
//            SidebarButton btn = (SidebarButton) me.getSource();
//            if (currentTab != null) {
//                currentTab.setActive(false);
//            }
//
//            btn.setActive(true);
//            currentTab = btn;
//            doAction(btn.text);
//        }
//    }
//
//    @Override    
//    public void mouseMoved(MouseEvent me)       
//    {      
////        Point cursorLocation = me.getPoint();         
////        int xPos = cursorLocation.x;         
////        int yPos = cursorLocation.y;      
////               
////        if(xPos >= cursorArea && xPos <= getWidth()-cursorArea && yPos >= getHeight()-cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));      
////        else if(xPos >= getWidth()-cursorArea && yPos >= cursorArea && yPos <= getHeight()-cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));      
////        else if(xPos <= cursorArea && yPos >= cursorArea && yPos <= getHeight()-cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));      
////        else if(xPos >= cursorArea && xPos <= getWidth()-cursorArea && yPos <= cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));      
////        else if(xPos <= cursorArea && yPos <= cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));      
////        else if(xPos >= getWidth() - cursorArea && yPos <= cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));      
////        else if(xPos >= getWidth()-cursorArea && yPos >= getHeight()-cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));      
////        else if(xPos <= cursorArea && yPos >= getHeight()-cursorArea)      
////            setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));      
////        else    
////            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));       
//    }
//      
//    @Override    
//    public void mouseDragged(MouseEvent e)       
//    {      
//        //moveOrFullResizeFrame(e);      
//    }
//    
//    @Override
//    public void mouseClicked(MouseEvent me) {
//    }
//
//    @Override
//    public void mousePressed(MouseEvent me) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent me) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent me) {
//
//    }
}
