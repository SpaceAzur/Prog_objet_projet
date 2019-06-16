import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SideControl implements MouseListener {

    Interface interf;

    public SideControl(Interface interf) {
        this.interf = interf;
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof SideButton) {

            SideButton item = (SideButton) e.getSource();

            String newTitle="";

            String dst=item.getDst().getName();
            String src=item.getSrc();

            if (dst.equals("Matériels")) {
                interf.showMateriel(item.getSrc(), item.getId());
                if (src.equals("Institutions")) newTitle="Matériels de " + interf.mod.getInstitutions().get(item.getId()).getRaisonSociale();
                interf.changeTitle(newTitle);
            } 
            else if (dst.equals("Personnes")) {
                interf.showIndividus(item.getSrc(), item.getId());
                if (src.equals("Institutions")) newTitle="Personnes liées à " + interf.mod.getInstitutions().get(item.getId()).getRaisonSociale();
                interf.changeTitle(newTitle);
            }

            interf.menu.changeCurrentMenu(item.getDst());

            /*if (item.getDst().equals("Bâtiments")) {
                interf.showBatiments(item.getSrc(), item.getId());
            }*/

            /*if (item.getName().equals("institutions")) interf.showInstitutions();
            if (item.getName().equals("materiel")) interf.showMateriel();
            if (item.getName().equals("emprunts")) interf.showEmprunts();
            if (item.getName().equals("batiments")) interf.showBatiments();*/

            /*if (interf.currentMenu != null) interf.currentMenu.setBackground(interf.yellow);
            interf.currentMenu=item;
            interf.currentMenu.setBackground(interf.lightBlue);*/

        }

    }

    public void mouseExited(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }

}