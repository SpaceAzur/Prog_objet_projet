import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SideControl implements MouseListener {

    Interface interf;

    Map<String, Consumer<A38Object>> commandes = new HashMap<>();

    public SideControl(Interface interf) {
        this.interf = interf;

        //commandes.put("Matériels", (src) -> interf.showMateriels(src));
        //commandes.put("Personnes", (src) -> interf.showIndividus(src));
        // commandes.put("Emprunts", (src,id) -> interf.showEmprunts(src, id));
        // commandes.put("Bâtiments", (src, id) -> interf.showBatiments(src, id));

    }

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof SideButton) {

            SideButton item = (SideButton) e.getSource();

            String dst = item.getDst().getName();
            A38Object src = item.getSrc();
            int id = src.getId();

            //commandes.get(dst).accept(src);
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
