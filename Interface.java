import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class Interface {

    Modele mod;

    public final int HEIGHT = 700;
    public final int WIDTH = 1150;
    public final int MENU = 150;
    public final int CENTER = 600;
    public final int SIDE = 400;
    public final int TOPBAR = 75;

    JFrame fenetre;
    JPanel center;
    JPanel side;
    JPanel topbar;

    JLabel title;

    Menu menu;

    CenterTableControl tableCtrl;
    SideControl sideCtrl;
    DeleteControl delCtrl;

    public Font titleFont = new Font("Verdana", Font.BOLD, 50);
    public Font sideTitleFont = new Font("Verdana", Font.BOLD, 35);
    public Font menuFont = new Font("Verdana", Font.PLAIN, 25);
    public Font headerFont = new Font("Verdana", Font.BOLD, 15);
    public Font tableFont = new Font("Verdana", Font.PLAIN, 15);
    public Font sideFont = new Font("Verdana", Font.PLAIN, 20);

    public Color yellow = new Color(242, 190, 84);
    public Color blue = new Color(21, 62, 92);
    public Color lightBlue = new Color(135, 174, 180);

    public Interface(Modele mod) {

        this.mod = mod;

        tableCtrl = new CenterTableControl(this);
        sideCtrl = new SideControl(this);
        delCtrl = new DeleteControl(this);

        initWindow();

        menu = new Menu(this);

        initCenter();
        initSide();
        initTopbar();

        fenetre.add(menu);
        fenetre.add(center);
        fenetre.add(side);
        fenetre.add(topbar);
        fenetre.setVisible(true);

    }

    public void initWindow() {

        fenetre = new JFrame();
        fenetre.setSize(WIDTH, HEIGHT);
        fenetre.setLayout(null);
        fenetre.setResizable(false);
        fenetre.setLocation(100, 100);
        fenetre.setTitle("A38");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initTopbar() {

        topbar = new JPanel();
        topbar.setLocation(MENU, 0);
        topbar.setSize(CENTER, TOPBAR);
        topbar.setBackground(blue);
        topbar.setLayout(null);
        topbar.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, yellow));

        title = new JLabel();
        title.setFont(sideTitleFont);
        title.setForeground(Color.WHITE);
        title.setLocation(30, 15);
        topbar.add(title);
        changeTitle("Test");

    }

    public void changeTitle(String t) {

        title.setText(t);
        title.setSize(title.getPreferredSize());

    }

    public void initSide() {

        side = new JPanel();
        side.setLocation(MENU + CENTER, 0);
        side.setSize(SIDE, HEIGHT);
        side.setBackground(blue);
        side.setLayout(null);
        side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, yellow));

    }

    public void initCenter() {

        center = new JPanel();
        center.setLocation(MENU, TOPBAR);
        center.setSize(CENTER, HEIGHT - TOPBAR);
        center.setBackground(Color.WHITE);
        center.setLayout(null);

    }

    public void showInstitutions() {

        center.removeAll();

        String[] colonnes = { "ID institution", "Raison sociale", "Adresse" };
        HashMap<Integer, Institution> institutions = mod.getInstitutions();
        String[][] data = new String[institutions.size()][3];

        int i = 0;

        for (Institution val : institutions.values()) {

            data[i][0] = Integer.toString(val.getId());
            data[i][1] = val.getRaisonSociale();
            data[i][2] = val.getAdresse();
            i++;

        }

        CenterTable table = new CenterTable(data, colonnes, this, "Institutions");
        table.addMouseListener(tableCtrl);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(CENTER, HEIGHT - TOPBAR);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        changeTitle("Liste des institutions");

        center.add(panneau);

    }

    public void showIndividus(String src, int id) {

        center.removeAll();

        String[] colonnes = { "ID", "Prénom", "Nom", "Status" };
        ArrayList<Individu> individus = mod.getIndividus(src,id);
        String[][] data = new String[individus.size()][4];

        int i = 0;

        for (Individu val : individus) {

            data[i][0] = Integer.toString(val.getId());
            data[i][1] = val.getPrenom();
            data[i][2] = val.getNom();
            data[i][3] = val.getStatus();
            i++;

        }

        CenterTable table = new CenterTable(data, colonnes, this, "Personnes");
        table.addMouseListener(tableCtrl);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(CENTER, HEIGHT - TOPBAR);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        changeTitle("Liste des personnes");

        center.add(panneau);

    }

    public void showMateriel(String src, int id) {

        center.removeAll();

        String[] colonnes = { "ID", "Marque", "Modele", "Etat" };
        ArrayList<Materiel> materiels = mod.getMateriels(src, id);
        String[][] data = new String[materiels.size()][4];

        for (int i = 0; i < materiels.size(); i++) {
            data[i][0] = Integer.toString(materiels.get(i).getId());
            data[i][1] = materiels.get(i).getMarque();
            data[i][2] = materiels.get(i).getModele();
            data[i][3] = materiels.get(i).getEtat();
        }

        CenterTable table = new CenterTable(data, colonnes, this, "Matériels");
        table.addMouseListener(tableCtrl);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(CENTER, HEIGHT - TOPBAR);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        if (src == null)
            changeTitle("Tous les matériels");

        center.add(panneau);

    }

    public void showBatiments() {

        center.removeAll();

        String[] colonnes = { "Nom", "Adresse", "Propriétaire", "Responsable" };
        Object[][] data = { { "ENSiiE Evry", "Square de la résistance", "ENSiiE", "Tellier" },
                { "IBISC", "Evry", "UEVE", "Bouyer" } };

        CenterTable table = new CenterTable(data, colonnes, this, "batiments");

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(CENTER, HEIGHT - TOPBAR);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        changeTitle("Tous les bâtiments");

        center.add(panneau);

    }

    public void showEmprunts() {

        center.removeAll();

        String[] colonnes = { "Emprunteur", "Institution", "Statut" };
        Object[][] data = { { "Tellier", "UEVE", "En cours" }, { "Bouyer", "ENSiiE", "Terminé" } };

        CenterTable table = new CenterTable(data, colonnes, this, "emprunts");

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(CENTER, HEIGHT - TOPBAR);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        changeTitle("Tous les emprunts");

        center.add(panneau);

    }

    public void showSideInst(int id) {

        side.removeAll();

        Institution morale = mod.getInstitutions().get(id);

        JLabel rs = new JLabel(morale.getRaisonSociale());
        rs.setFont(sideTitleFont);
        rs.setForeground(Color.WHITE);
        rs.setSize(rs.getPreferredSize());
        rs.setLocation(SIDE / 2 - rs.getWidth() / 2, 50);
        side.add(rs);

        SideLabel idl = new SideLabel("ID : " + morale.getId(), this, 50, 150);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + morale.getAdresse() + "</html>", this, 50, 200);
        SideLabel email = new SideLabel("<html>Mail : <br>" + morale.getEmail() + "</html>", this, 50, 280);
        SideLabel telephone = new SideLabel("Tel : " + morale.getTelephone(), this, 50, 360);

        SideButton materiel = new SideButton("Institutions", menu.materiels, id, this, 25, 450);
        SideButton emprunts = new SideButton("Institutions", menu.emprunts, id, this, 215, 450);
        SideButton batiments = new SideButton("Institutions", menu.batiments, id, this, 25, 560);
        SideButton personnes = new SideButton("Institutions", menu.personnes, id, this, 215, 560);

        DeleteButton delete = new DeleteButton(morale);
        delete.addMouseListener(delCtrl);
        side.add(delete);

        side.repaint();

    }

    public void showSidePers(int id) {

        side.removeAll();

        Individu individu = mod.getIndividu(id);

        JLabel identite = new JLabel(individu.getPrenom() + " " + individu.getNom());
        identite.setFont(sideTitleFont);
        identite.setForeground(Color.WHITE);
        identite.setSize(identite.getPreferredSize());
        identite.setLocation(SIDE / 2 - identite.getWidth() / 2, 50);
        side.add(identite);

        SideLabel idl = new SideLabel("ID : " + individu.getId(), this, 50, 150);
        SideLabel status = new SideLabel("Status : " + individu.getStatus(), this, 50, 200);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + individu.getAdresse() + "</html>", this, 50, 250);
        SideLabel email = new SideLabel("<html>Mail : <br>" + individu.getEmail() + "</html>", this, 50, 330);
        SideLabel telephone = new SideLabel("Tel : " + individu.getTelephone(), this, 50, 410);

        /*SideButton materiel = new SideButton("Institutions", menu.materiels, id, this, 25, 450);
        SideButton emprunts = new SideButton("Institutions", menu.emprunts, id, this, 215, 450);
        SideButton batiments = new SideButton("Institutions", menu.batiments, id, this, 25, 560);
        SideButton personnes = new SideButton("Institutions", menu.personnes, id, this, 215, 560);

        DeleteButton delete = new DeleteButton(morale);
        delete.addMouseListener(delCtrl);
        side.add(delete);*/

        side.repaint();

    }

    public void showSideMat(int id) {

        side.removeAll();

        Materiel matos = mod.getMateriels(null,0).get(id);

        JLabel mat = new JLabel(matos.getModele());
        mat.setFont(sideTitleFont);
        mat.setForeground(Color.WHITE);
        mat.setSize(mat.getPreferredSize());
        mat.setLocation(SIDE / 2 - mat.getWidth() / 2, 50);
        side.add(mat);

        SideLabel idl = new SideLabel("ID : " + matos.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + matos.getProprietaire().getRaisonSociale(), this, 50,
                180);
        SideLabel marque = new SideLabel("Marque : " + matos.getMarque(), this, 50, 210);
        SideLabel prix = new SideLabel("Prix : " + matos.getPrixAchat(), this, 50, 240);
        SideLabel date = new SideLabel("<html>Date d'achat : <br>" + matos.getDateAchat() + "</html>", this, 50, 270);
        SideLabel etat = new SideLabel("Etat : " + matos.getEtat(), this, 50, 330);
        SideLabel connec = new SideLabel("Connectique : " + matos.getConnectique(), this, 50, 360);

        DeleteButton delete = new DeleteButton(matos);
        delete.addMouseListener(delCtrl);
        side.add(delete);

        side.repaint();

    }

}