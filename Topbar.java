import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Topbar extends JPanel {

    Interface interf;
    JLabel title;

    public Topbar(Interface interf) {

        this.interf=interf;
        
        setLocation(interf.MENU, 0);
        setSize(interf.CENTER, interf.TOPBAR);
        setBackground(interf.blue);
        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, interf.yellow));

        title = new JLabel();
        title.setFont(interf.sideTitleFont);
        title.setForeground(Color.WHITE);
        title.setLocation(30, 15);
        add(title);

    }

    public void changeTitle(String t) {

        title.setText(t);
        title.setSize(title.getPreferredSize());

    }

}