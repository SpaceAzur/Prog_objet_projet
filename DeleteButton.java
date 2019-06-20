import java.awt.Color;

import javax.swing.*;

public class DeleteButton extends JPanel {

    private Object item;

    public DeleteButton(Side side, Object o) {

        this.setSize(40,40);
        this.setLocation(350,10);
        this.setBackground(Color.RED);
        this.item=o;
        this.addMouseListener(side.sctrl);
        
    }

    public Object getItem() {
        return this.item;
    }

}