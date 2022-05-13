package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SidebarContainer extends JPanel {

    private int bottomPos = 0;
    private ArrayList<SidebarItem> navItems = new ArrayList<>();

    public SidebarContainer(Rectangle rec) {
        setLayout(null);
        setBounds(rec);
        setPreferredSize(new Dimension(rec.width, rec.height));
        setBackground(new Color(35, 35, 35));
    }

    public void addItem(SidebarItem item, Boolean fullWidth) {
        if (fullWidth) {
            item.setBounds(0, bottomPos, getWidth(), item.getHeight());
            if(item instanceof SidebarButton) {
                SidebarButton btnitem = (SidebarButton)item;
                btnitem.relocate();
            }
        } else {
            item.setLocation(item.getBounds().x, item.getBounds().y);
        }

        bottomPos += item.getHeight();
        navItems.add(item); // thêm vào mảng danh sách
        add(item); // thêm vào jPanel
    }

    public void addItem(SidebarItem i) {
        addItem(i, true);
    }
    
    public SidebarItem getItem(int i) {
        return navItems.get(i);
    }
    
    public int getLength() {
        return navItems.size();
    }
}
