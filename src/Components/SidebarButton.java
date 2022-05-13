package Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SidebarButton extends SidebarItem {

    protected Color bgHover, colorHover;
    protected Boolean actived = false;
    protected JLabel lbIcon;

    public SidebarButton(Rectangle rec, String text, String iconUrl) {
        super(rec, text);
        this.bgHover = new Color(49, 49, 49);
        this.colorHover = new Color(255, 255, 255);

        // icon string to icon
        setIconFromString(iconUrl);

        // mouse event overriding
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setHovered(true);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setHovered(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } 
        });
    }

    public void setHovered(Boolean hovering) {
        if (!actived) {
            if (hovering) {
                setBackground(bgHover);
                lbLabel.setForeground(colorHover);
            } else {
                setBackground(bgDefault);
                lbLabel.setForeground(colorDefault);
            }
        }
    }
    
    public void setBgHover(Color bgHover) {
        this.bgHover = bgHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    //setup icon from string
    public void setIconFromString(String iconUrl) {
        lbIcon = new JLabel();
        lbIcon.setIcon(new ImageIcon(getClass().getResource("/Images/" + iconUrl)));
        add(lbIcon);
    }
    
    public void setIconLocation(Rectangle rec) {
        lbIcon.setBounds(rec);
    }

    // Tính toán vị trí các icon và label
    public void relocate() {
        Rectangle rec = getBounds();
        lbIcon.setBounds(rec.width / 10, rec.height / 4, rec.width /4, (int) (rec.height / 1.8));
        lbLabel.setBounds((int)(rec.width / 3.2), rec.height / 4, (int)(rec.width * 3 / 4), (int) (rec.height / 1.8));
    }
    
    // Tính toán vị trí kích thước icon label cho nút logout
    public void relocate2() {
        Rectangle rec = getBounds();
        lbIcon.setBounds(5, rec.height / 4, rec.width / 4, (int) (rec.height / 1.75));
        lbLabel.setBounds(rec.width / 4, rec.height / 4, rec.width * 3 / 4, (int) (rec.height / 1.75));
    }

    // Khởi tạo và chuyển trạng thái active
    public void setActive(Boolean stage) {
        this.actived = stage;
        if (this.actived) {
            this.setBackground(Color.blue);
            lbLabel.setForeground(Color.white);
        } else {
            this.setBackground(bgDefault);
            lbLabel.setForeground(colorDefault);
        }
    }

    public Boolean getActive() {
        return this.actived;
    }
    
    public void switchActive() {
        this.actived = !this.actived;
        setActive(actived);
    }
}
