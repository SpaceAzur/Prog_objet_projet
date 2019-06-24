package a38;

import java.awt.Color;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideCancelButton extends JPanel {

    A38Object obj;
    MenuButton dst;

    public SideCancelButton(Side side, A38Object obj) {

        this.obj=obj;

        this.setSize(125,65);
        this.setLocation(225, 580);
        this.setBackground(Color.RED);
        this.setLayout(null);

        JLabel label = new JLabel("Annuler");
        label.setFont(side.interf.sideFont);
        label.setForeground(Color.WHITE);
        label.setSize(label.getPreferredSize());
        label.setLocation(this.getWidth() / 2 - label.getWidth() / 2,
        this.getHeight() / 2 - label.getHeight() / 2);

        this.add(label);
        this.addMouseListener(side.sctrl);

        side.add(this);

    }

    public A38Object getObj() {
        return obj;
    }

}