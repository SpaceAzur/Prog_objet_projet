import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuButton extends JPanel {

    private String name;

    public MenuButton(String name, MenuControl mctrl, Interface interf, int y) {

        this.name=name;
        this.addMouseListener(mctrl);
        this.setSize(interf.MENU, 40);
        this.setBackground(interf.yellow);
        this.setLocation(0, y);

        JLabel label = new JLabel(name);
        label.setFont(interf.menuFont);
        this.add(label);

    }

    public String getName() {

        return this.name;

    }
    
}