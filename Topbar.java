import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Topbar extends JPanel {

    Interface interf;
    JLabel title;
    TopbarControl topctrl;

    public Topbar(Interface interf) {

        this.interf=interf;
        topctrl = new TopbarControl(interf);
        
        setLocation(interf.MENU, 0);
        setSize(interf.CENTER, interf.TOPBAR);
        setBackground(interf.blue);
        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, interf.yellow));

        title = new JLabel();
        title.setMaximumSize(new Dimension(interf.CENTER, interf.TOPBAR));
        title.setFont(interf.topbarFont);
        title.setForeground(Color.WHITE);
        title.setLocation(30, 20);
        add(title);

        AddButton ajout = new AddButton(interf.center);
        ajout.addMouseListener(topctrl);
        add(ajout);

    }

    public void changeTitle(String t) {

        title.setText(t);
        title.setSize(title.getPreferredSize());

    }

}