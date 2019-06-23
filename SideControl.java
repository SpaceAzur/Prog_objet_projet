import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;

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
            mod.deleteObject(bouton.getItem());
            interf.side.removeAll();
            interf.side.repaint();
            interf.refreshObjects();

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
            } catch (SQLException se) {
                interf.showError(se.getMessage());
            } catch (ParseException pse) {
                interf.showError(pse.getMessage());
            } catch (Exception ex) {
                interf.showError(ex.getMessage());
            }

        }

    }

    private void sideAction(SideButton item) {

        String dst = item.getDst().getName();
        A38Object src = item.getSrc();
        int id = src.getId();

        interf.showObjects(dst, src);

        String beginTitle = dst + " : ";
        String endTitle = "";

        if (src instanceof Institution)
            endTitle = mod.getInstitution(id).getRaisonSociale();
        if (src instanceof Individu)
            endTitle = mod.getIndividu(id).getPrenom() + " " + mod.getIndividu(id).getNom();
        if (src instanceof Armoire)
            endTitle = "Armoire " + mod.getArmoire(id).getNom();
        if (src instanceof Batiment)
            endTitle = "Batiment " + mod.getBatiment(id).getNom();
        if (src instanceof Salle)
            endTitle = "Salle " + mod.getSalle(id).getNom();

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
