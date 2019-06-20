import java.awt.Color;

import javax.swing.*;

public class EditButton extends JPanel {

    private A38Object item;

    public EditButton(Side side, A38Object o) {

        this.setSize(40,40);
        this.setLocation(10,10);
        this.setBackground(Color.ORANGE);
        this.item=o;
        this.addMouseListener(side.sctrl);
        
    }

    public A38Object getItem() {
        return this.item;
    }

}