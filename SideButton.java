import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideButton extends JPanel {

    A38Object src;
    MenuButton dst;

    public SideButton(A38Object src, MenuButton dst, Side side, int x, int y) {

        this.src=src;
        this.dst=dst;
        this.setSize(160,80);
        this.setLocation(x, y);
        this.setBackground(side.interf.yellow);
        this.setLayout(null);

        JLabel label = new JLabel(dst.getName());
        label.setFont(side.interf.sideFont);
        label.setForeground(Color.WHITE);
        label.setSize(label.getPreferredSize());
        label.setLocation(this.getWidth() / 2 - label.getWidth() / 2,
        this.getHeight() / 2 - label.getHeight() / 2);

        this.add(label);
        this.addMouseListener(side.sctrl);

        side.add(this);

    }

    public A38Object getSrc() {
        return src;
    }

    public MenuButton getDst() {
        return dst;
    }

}