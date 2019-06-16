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

            if (item.getName().equals("Personnes")) interf.showIndividus(null,0);
            if (item.getName().equals("Matériels")) interf.showMateriel(null, 0);
            if (item.getName().equals("Emprunts")) interf.showEmprunts();
            if (item.getName().equals("Bâtiments")) interf.showBatiments();
            if (item.getName().equals("Institutions")) interf.showInstitutions();

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