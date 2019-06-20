import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Center extends JPanel {

    Interface interf;
    CenterTableControl ctctrl;
    JScrollPane panneau;

    public Center(Interface interf) {

        this.interf = interf;
        this.ctctrl = new CenterTableControl(interf);

        setLocation(interf.MENU, interf.TOPBAR);
        setSize(interf.CENTER, interf.HEIGHT - interf.TOPBAR);
        setBackground(interf.blue);
        setLayout(null);

    }

    private void updatePanneau(CenterTable ct) {

        panneau = new JScrollPane(ct);
        panneau.setSize(interf.CENTER, interf.HEIGHT - interf.TOPBAR);
        panneau.setBorder(new LineBorder(interf.yellow, 1));
        panneau.setLocation(0, 0);
        this.add(panneau);

    }

    public void showInstitutions(A38Object filter) {

        removeAll();

        HashMap<Integer, Institution> institutions = interf.mod.getInstitutions();

        if (institutions.size() > 0) {

            String[] colonnes = { "ID", "Raison sociale", "Adresse" };
            String[][] data = new String[institutions.size()][3];

            int i = 0;
            for (Institution val : institutions.values()) {
                data[i][0] = Integer.toString(val.getId());
                data[i][1] = val.getRaisonSociale();
                data[i][2] = val.getAdresse();
                i++;
            }

            CenterTable table = new CenterTable(data, colonnes, interf, "Institutions");
            table.addMouseListener(ctctrl);
            updatePanneau(table);

        }

        if (filter == null)
            interf.changeTitle("Liste des institutions");

    }

    public void showMateriels(A38Object filter) {

        removeAll();

        ArrayList<Materiel> materiels = interf.mod.getMateriels(filter);

        if (materiels.size() > 0) {

            String[] colonnes = { "ID", "Marque", "Modele", "Etat" };
            String[][] data = new String[materiels.size()][4];

            for (int i = 0; i < materiels.size(); i++) {
                data[i][0] = Integer.toString(materiels.get(i).getId());
                data[i][1] = materiels.get(i).getMarque();
                data[i][2] = materiels.get(i).getModele();
                data[i][3] = materiels.get(i).getEtat();
            }

            CenterTable table = new CenterTable(data, colonnes, interf, "Matériels");
            table.addMouseListener(ctctrl);
            updatePanneau(table);

        }

        if (filter == null)
            interf.changeTitle("Tous les matériels");

    }

    public void showEmprunts(A38Object filter) {

        removeAll();

        String[] colonnes = { "ID", "Emprunteur", "Institution", "Statut" };
        Object[][] data = { { "0", "Tellier", "UEVE", "En cours" }, { "1", "Bouyer", "ENSiiE", "Terminé" } };

        CenterTable table = new CenterTable(data, colonnes, interf, "emprunts");
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Tous les emprunts");

    }

    public void showIndividus(A38Object filter) {

        removeAll();

        ArrayList<Individu> individus = interf.mod.getIndividus(filter);

        if (individus.size() > 0) {

            String[] colonnes = { "ID", "Prénom", "Nom", "Status" };
            String[][] data = new String[individus.size()][4];

            int i = 0;

            for (Individu val : individus) {

                data[i][0] = Integer.toString(val.getId());
                data[i][1] = val.getPrenom();
                data[i][2] = val.getNom();
                data[i][3] = val.getStatus();
                i++;

            }

            CenterTable table = new CenterTable(data, colonnes, interf, "Personnes");
            table.addMouseListener(ctctrl);
            updatePanneau(table);

        }

        if (filter == null)
            interf.changeTitle("Liste des personnes");

    }

    public void showBatiments(A38Object filter) {

        removeAll();

        ArrayList<Batiment> batiments = interf.mod.getBatiments(filter);

        if (batiments.size() > 0) {

            String[] colonnes = { "ID", "Nom", "Adresse", "Propriétaire", "Responsable" };
            String[][] data = new String[batiments.size()][5];

            for (int i = 0 ; i < batiments.size() ; i++) {
                data[i][0] = Integer.toString(batiments.get(i).getId());
                data[i][1] = batiments.get(i).getNom();
                data[i][2] = batiments.get(i).getAdresse();
                data[i][3] = batiments.get(i).getProprietaire().getRaisonSociale();
                data[i][4] = batiments.get(i).getResponsable().getPrenom() + " " + batiments.get(i).getResponsable().getNom();
            }

            CenterTable table = new CenterTable(data, colonnes, interf, "Personnes");
            table.addMouseListener(ctctrl);
            updatePanneau(table);

        }

        if (filter == null)
            interf.changeTitle("Tous les bâtiments");

    }

}