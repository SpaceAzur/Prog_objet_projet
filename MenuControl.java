import java.awt.event.*;

public class MenuControl implements MouseListener {

    Interface interf;

    public MenuControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        MenuButton item;
        if (e.getSource() instanceof MenuButton) {

            item = (MenuButton) e.getSource();

            //if (item.getName().equals("personnes")) interf.showPersonnes();
            if (item.getName().equals("materiel")) interf.showMateriel(null, 0);
            if (item.getName().equals("emprunts")) interf.showEmprunts();
            if (item.getName().equals("batiments")) interf.showBatiments();

            if (interf.menu.current != null) interf.menu.current.setBackground(interf.yellow);
            interf.menu.current=item;
            interf.menu.current.setBackground(interf.lightBlue);
        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}