import java.awt.Color;

import javax.swing.JLabel;

public class SideLabel extends JLabel {
    
    public SideLabel(String s, Side side, int x, int y) {

        super(s);
        this.setFont(side.interf.sideFont);
        this.setForeground(Color.WHITE);
        this.setSize(this.getPreferredSize());
        this.setLocation(x,y);
        side.add(this);
    }

}