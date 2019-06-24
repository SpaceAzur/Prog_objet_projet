package a38;

import java.awt.event.*;

public class CenterTableControl implements MouseListener {

    Interface interf;

    public CenterTableControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        CenterTable table = (CenterTable) e.getSource();
        String type = table.getType();
        int row = table.getSelectedRow();
        int id = Integer.parseInt(
                (String) table.getValueAt(row, table.convertColumnIndexToView(table.getColumn("ID").getModelIndex())));
        A38Object obj = null;

        if (type.equals("Institutions"))
            obj = interf.mod.getInstitution(id);
        else if (type.equals("Matériels"))
            obj = interf.mod.getMateriel(id);
        else if (type.equals("Personnes"))
            obj = interf.mod.getIndividu(id);
        else if (type.equals("Emprunts"))
            obj = interf.mod.getEmprunt(id);
        else if (type.equals("Bâtiments"))
            obj = interf.mod.getBatiment(id);
        else if (type.equals("Salles"))
            obj = interf.mod.getSalle(id);
        else if (type.equals("Armoires"))
            obj = interf.mod.getArmoire(id);

        interf.showObject(obj);

    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

}