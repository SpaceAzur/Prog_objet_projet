package a38;

import java.awt.event.*;

public class MenuControl implements MouseListener {

    Interface interf;

    public MenuControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof MenuButton) {

            MenuButton bouton = (MenuButton) e.getSource();
            String type = bouton.getName();

            interf.showObjects(type, null);

            if (interf.menu.current != null) interf.menu.current.setBackground(interf.yellow);
            interf.menu.current=bouton;
            interf.menu.current.setBackground(interf.lightBlue);
        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}