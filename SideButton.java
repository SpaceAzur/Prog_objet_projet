import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideButton extends JPanel {

    String src;
    MenuButton dst;
    int id;
    Interface interf;

    public SideButton(String src, MenuButton dst, int id, Interface interf, int x, int y) {

        this.src=src;
        this.dst=dst;
        this.id=id;
        this.setSize(160,80);
        this.setLocation(x, y);
        this.setBackground(interf.yellow);
        this.setLayout(null);

        JLabel label = new JLabel(dst.getName());
        label.setFont(interf.sideFont);
        label.setForeground(Color.WHITE);
        label.setSize(label.getPreferredSize());
        label.setLocation(this.getWidth() / 2 - label.getWidth() / 2,
        this.getHeight() / 2 - label.getHeight() / 2);

        this.add(label);
        this.addMouseListener(interf.sideCtrl);

        interf.side.add(this);

    }

    public String getSrc() {
        return src;
    }

    public MenuButton getDst() {
        return dst;
    }

    public int getId() {
        return id;
    }

}