import java.awt.Color;

import javax.swing.JLabel;

public class SideLabel extends JLabel {
    
    public SideLabel(String s, Interface interf, int x, int y) {

        super(s);
        this.setFont(interf.sideFont);
        this.setForeground(Color.WHITE);
        this.setSize(this.getPreferredSize());
        this.setLocation(x,y);
        interf.side.add(this);
    }

}