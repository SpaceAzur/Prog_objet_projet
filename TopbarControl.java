package a38;

import java.awt.event.*;

public class TopbarControl implements MouseListener {

    Interface interf;

    public TopbarControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof AddButton) {

            AddButton bouton = (AddButton) e.getSource();
            interf.newObject(bouton.getType(), bouton.getFilter());

        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}