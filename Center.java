import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Center extends JPanel {

    Interface interf;
    CenterTableControl ctctrl;
    JScrollPane panneau;
    String currentType;
    A38Object filter;

    public Center(Interface interf) {

        this.interf = interf;
        this.ctctrl = new CenterTableControl(interf);

        setLocation(interf.MENU, interf.TOPBAR);
        setSize(interf.CENTER, interf.HEIGHT - interf.TOPBAR);
        setBackground(interf.blue);
        setLayout(null);

    }

    private void updatePanneau(CenterTable ct) {

        if (ct.getRowCount() > 0) {
            panneau = new JScrollPane(ct);
        }

        else {

            JLabel message = new JLabel("Pas de résultats");
            message.setFont(interf.sideTitleFont);
            message.setSize(message.getPreferredSize());
            message.setForeground(Color.WHITE);
            message.setLocation(interf.CENTER / 2 - message.getWidth() / 2,
                    (interf.CENTER - interf.TOPBAR) / 2 - message.getHeight() / 2);

            JPanel box = new JPanel();
            box.setBackground(interf.blue);
            box.setLayout(null);
            box.setSize(interf.CENTER, interf.HEIGHT - interf.TOPBAR);
            box.setLocation(0, 0);
            box.add(message);

            panneau = new JScrollPane(box);

        }

        panneau.setSize(interf.CENTER, interf.HEIGHT - interf.TOPBAR);
        panneau.setBorder(new LineBorder(interf.yellow, 1));
        panneau.setLocation(0, 0);
        panneau.getViewport().setBackground(interf.blue);
        this.add(panneau);

    }

    public String getCurrentType() {
        return this.currentType;
    }

    public void setCurrentType(String s) {
        this.currentType = s;
    }

    public A38Object getFilter() {
        return this.filter;
    }

    public void setFilter(A38Object obj) {
        this.filter = obj;
    }

    public void showInstitutions() {

        removeAll();

        HashMap<Integer, Institution> institutions = interf.mod.getInstitutions();

        String[] colonnes = { "ID", "Raison sociale", "Adresse" };
        String[][] data = new String[institutions.size()][3];

        int i = 0;
        for (Institution val : institutions.values()) {
            data[i][0] = Integer.toString(val.getId());
            data[i][1] = val.getRaisonSociale();
            data[i][2] = val.getAdresse();
            i++;
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Liste des institutions");

    }

    public void showMateriels() {

        removeAll();

        ArrayList<Materiel> materiels = interf.mod.getMateriels(filter);

        String[] colonnes = { "ID", "Marque", "Modele", "Etat" };
        String[][] data = new String[materiels.size()][4];

        for (int i = 0; i < materiels.size(); i++) {
            data[i][0] = Integer.toString(materiels.get(i).getId());
            data[i][1] = materiels.get(i).getMarque();
            data[i][2] = materiels.get(i).getModele();
            data[i][3] = materiels.get(i).getEtat();
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Tous les matériels");

    }

    public void showEmprunts() { 

        removeAll();

        ArrayList<Emprunt> emprunts = interf.mod.getEmprunts(filter);

        String[] colonnes = { "ID", "Emprunteur", "Materiel", "Propriétaire", "Rendu" };
        Object[][] data = new String[emprunts.size()][5];

        for (int i=0 ; i<emprunts.size() ; i++) {
            data[i][0] = Integer.toString(emprunts.get(i).getId());
            data[i][1] = emprunts.get(i).getEmprunteur().getNom();
            data[i][2] = emprunts.get(i).getMateriel().getModele();
            data[i][3] = emprunts.get(i).getMateriel().getProprietaire().getRaisonSociale();
            data[i][4] = emprunts.get(i).isRendu() ? "Oui" : "Non";
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Tous les emprunts");

    }

    public void showIndividus() {

        removeAll();

        ArrayList<Individu> individus = interf.mod.getIndividus(filter);

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

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Liste des personnes");

    }

    public void showBatiments() {

        removeAll();

        ArrayList<Batiment> batiments = interf.mod.getBatiments(filter);

        String[] colonnes = { "ID", "Nom", "Adresse", "Propriétaire", "Responsable" };
        String[][] data = new String[batiments.size()][5];

        for (int i = 0; i < batiments.size(); i++) {
            data[i][0] = Integer.toString(batiments.get(i).getId());
            data[i][1] = batiments.get(i).getNom();
            data[i][2] = batiments.get(i).getAdresse();
            data[i][3] = batiments.get(i).getProprietaire().getRaisonSociale();
            data[i][4] = batiments.get(i).getResponsable().getPrenom() + " "
                    + batiments.get(i).getResponsable().getNom();
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Tous les bâtiments");

    }

    public void showSalles() {

        removeAll();

        ArrayList<Salle> salles = interf.mod.getSalles(filter);
        
        String[] colonnes = { "ID", "Nom", "Batiment", "Etage", "Surface" };
        String[][] data = new String[salles.size()][5];

        for (int i = 0; i < salles.size(); i++) {
            data[i][0] = Integer.toString(salles.get(i).getId());
            data[i][1] = salles.get(i).getNom();
            data[i][2] = salles.get(i).getLocalisation().getNom();
            data[i][3] = Integer.toString(salles.get(i).getEtage());
            data[i][4] = Integer.toString(salles.get(i).getSurface());
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Toutes les salles");

    }

    public void showArmoires() {

        removeAll();

        ArrayList<Armoire> armoires = interf.mod.getArmoires(filter);

        String[] colonnes = { "ID", "Nom", "Batiment", "Salle" };
        String[][] data = new String[armoires.size()][4];

        for (int i = 0; i < armoires.size(); i++) {
            data[i][0] = Integer.toString(armoires.get(i).getId());
            data[i][1] = armoires.get(i).getNom();
            data[i][2] = armoires.get(i).getLocalisation().getLocalisation().getNom();
            data[i][3] = armoires.get(i).getLocalisation().getNom();
        }

        CenterTable table = new CenterTable(data, colonnes, interf, currentType);
        table.addMouseListener(ctctrl);
        updatePanneau(table);

        if (filter == null)
            interf.changeTitle("Toutes les armoires");

    }

}