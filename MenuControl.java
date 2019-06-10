import java.awt.event.*;

public class MenuControl implements MouseListener {

    Interface interf;

    public MenuControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        MenuItem item;
        if (e.getSource() instanceof MenuItem) {

            item = (MenuItem) e.getSource();

            if (item.getName().equals("personnes")) interf.showPersonnes();
            if (item.getName().equals("materiel")) interf.showMateriel();
            if (item.getName().equals("emprunts")) interf.showEmprunts();
            if (item.getName().equals("batiments")) interf.showBatiments();

            if (interf.currentMenu != null) interf.currentMenu.setBackground(interf.yellow);
            interf.currentMenu=item;
            interf.currentMenu.setBackground(interf.lightBlue);
        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}