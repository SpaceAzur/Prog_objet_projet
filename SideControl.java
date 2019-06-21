import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class SideControl implements MouseListener {

    Interface interf;
    Modele mod;

    public SideControl(Interface interf) {
        this.interf = interf;
        this.mod = interf.mod;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof SideButton)
            sideAction((SideButton) e.getSource());

        if (e.getSource() instanceof EditButton) {

            EditButton bouton = (EditButton) e.getSource();
            interf.editItem(bouton.getItem());

        }

        if (e.getSource() instanceof DeleteButton) {

            DeleteButton bouton = (DeleteButton) e.getSource();
            if (bouton.getItem() instanceof Institution) {
                Institution pm = (Institution) bouton.getItem();
                interf.mod.deleteInstitution(pm);
            }

            interf.side.removeAll();

        }

        if (e.getSource() instanceof SideCancelButton) {

            SideCancelButton bouton = (SideCancelButton) e.getSource();
            interf.showObject(bouton.getObj());

        }

        if (e.getSource() instanceof SideSaveButton) {

            SideSaveButton bouton = (SideSaveButton) e.getSource();
            A38Object obj;
            try {
                obj = mod.save(bouton.getObj(), bouton.getValues(), bouton.getType());
                interf.showObject(obj);
                interf.refreshObjects();
            } catch (Exception se) {
                interf.showError(se.getMessage());
            }

        }

    }

    private void sideAction(SideButton item) {

        String dst = item.getDst().getName();
        A38Object src = item.getSrc();
        int id = src.getId();

        interf.showObjects(dst, src);

        String beginTitle = "";
        String endTitle = "";

        if (dst.equals("Matériels"))
            beginTitle = "Matériels de ";
        else if (dst.equals("Personnes"))
            beginTitle = "Personnes liées à ";

        if (src instanceof Institution)
            endTitle = interf.mod.getInstitution(id).getRaisonSociale();

        interf.changeTitle(beginTitle + endTitle);

        interf.changeCurrentMenu(item.getDst());

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
