import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    Interface interf;
    MenuControl mctrl;

    MenuButton materiels;
    MenuButton emprunts;
    MenuButton institutions;
    MenuButton batiments;
    MenuButton personnes;

    MenuButton current;

    public Menu(Interface interf) {

        this.interf=interf;
        mctrl = new MenuControl(interf);

        this.setLocation(0, 0);
        this.setSize(interf.MENU, interf.HEIGHT);
        this.setBackground(interf.blue);
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, interf.yellow));

        JLabel a38 = new JLabel(" A38");
        a38.setLocation(0, 0);
        a38.setSize(interf.MENU, 100);
        a38.setFont(interf.titleFont);
        a38.setForeground(interf.yellow);

        materiels = new MenuButton("Matériels", mctrl, interf, 100);
        emprunts = new MenuButton("Emprunts", mctrl, interf, 155);
        institutions = new MenuButton("Institutions", mctrl, interf, 210);
        personnes = new MenuButton("Personnes", mctrl, interf, 265);
        batiments = new MenuButton("Bâtiments", mctrl, interf, 320);

        this.add(a38);
        this.add(materiels);
        this.add(emprunts);
        this.add(institutions);
        this.add(batiments);
        this.add(personnes);

    }

    public void changeCurrentMenu(MenuButton b) {

        if (current != null) current.setBackground(interf.yellow);
        current=b;
        current.setBackground(interf.lightBlue);

    }

}