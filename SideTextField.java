import java.awt.Color;

import javax.swing.JTextField;

public class SideTextField extends JTextField {

    String key;
    
    public SideTextField(String k, String v, Side side, int x, int y) {

        super(v);
        this.key=k;
        this.setSize(300,35);
        this.setFont(side.interf.sideFont);
        this.setForeground(side.interf.blue);
        this.setLocation(x,y);
        side.add(this);
        
    }

    public String getKey() {
        return this.key;
    }

    public String getVal() {
        return this.getText();
    }

}