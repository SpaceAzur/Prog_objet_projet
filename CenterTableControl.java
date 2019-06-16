import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CenterTableControl implements MouseListener {

    Interface interf;

    public CenterTableControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        CenterTable table = (CenterTable) e.getSource();
        int row = table.getSelectedRow();
        int id = Integer.parseInt((String) table.getValueAt(row, 0));
        if (table.getType().equals("Institutions")) {
            interf.showSideInst(id);
        }
        else if (table.getType().equals("Matériels")) {
            interf.showSideMat(id);
        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}