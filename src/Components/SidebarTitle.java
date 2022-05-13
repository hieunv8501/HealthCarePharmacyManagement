package Components;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

public class SidebarTitle extends SidebarItem {
    
    public SidebarTitle(Rectangle rec, String text) {
        super(rec, text);
        setLayout(new GridBagLayout());
        setFontSize(20);
        lbLabel.setForeground(Color.WHITE);
    }
}
