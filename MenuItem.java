import javax.swing.JPanel;

public class MenuItem extends JPanel {

    private String name;

    public MenuItem(String name, MenuControl mctrl) {

        this.name=name;
        this.addMouseListener(mctrl);

    }

    public String getName() {

        return this.name;

    }
    
}