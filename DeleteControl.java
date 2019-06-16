import java.awt.event.*;

public class DeleteControl implements MouseListener {

    Interface interf;

    public DeleteControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof DeleteButton) {

            DeleteButton bouton = (DeleteButton) e.getSource();
            if (bouton.getItem() instanceof Institution) {
                Institution pm = (Institution) bouton.getItem();
                interf.mod.deleteInstitution(pm);
            }

            interf.side.removeAll();

        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}