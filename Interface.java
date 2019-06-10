import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Interface {

    JFrame fenetre;
    JPanel menu;
    JPanel center;
    JPanel side;
    Modele mod;
    
    public MenuItem currentMenu;

    public Font titleFont = new Font("Verdana", Font.BOLD, 50);
    public Font menuFont = new Font("Verdana", Font.PLAIN, 25);
    public Font headerFont = new Font("Verdana", Font.BOLD, 15);
    public Font tableFont = new Font("Verdana", Font.PLAIN, 15);

    public Color yellow = new Color(242, 190, 84);
    public Color blue = new Color(21, 62, 92);
    public Color lightBlue = new Color(135, 174, 180);

    public Interface(Modele mod) {

        this.mod = mod;
        initWindow();
        initMenu();
        initCenter();
        initSide();
        fenetre.add(menu);
        fenetre.add(center);
        fenetre.add(side);
        fenetre.setVisible(true);

    }

    public void initWindow() {

        fenetre = new JFrame();
        fenetre.setSize(1600, 900);
        fenetre.setLayout(null);
        fenetre.setResizable(false);
        fenetre.setLocation(100, 100);
        fenetre.setTitle("A38");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initSide() {

        side = new JPanel();
        side.setLocation(1100,0);
        side.setSize(500,900);
        side.setBackground(blue);
        side.setLayout(null);
        side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, yellow));

    }

    public void initCenter() {

        center = new JPanel();
        center.setLocation(150, 0);
        center.setSize(950, 900);
        center.setBackground(Color.WHITE);
        center.setLayout(null);

    }

    public void showPersonnes() {

        center.removeAll();

        String[] colonnes = { "Prénom", "Nom", "Statut" };
        Object[][] data = { { "Pierre", "Danel", "étudiant" }, { "Antoine", "Escobar", "étudiant" } };
       
        CenterTable table = new CenterTable(data, colonnes, this);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(950, 900);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        center.add(panneau);

    }

    public void showMateriel() {

        center.removeAll();

        String[] colonnes = { "Marque", "Nom", "Etat" };
        Object[][] data = { { "Samsung", "Galaxy S10+", "Fonctionnel" }, { "Apple", "iPhone XS", "En panne" } };

        CenterTable table = new CenterTable(data, colonnes, this);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(950, 900);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        center.add(panneau);

    }

    
    public void showEmprunts() {

        center.removeAll();

        String[] colonnes = { "Emprunteur", "Institution", "Statut" };
        Object[][] data = { { "Tellier", "UEVE", "En cours" }, { "Bouyer", "ENSiiE", "Terminé" } };

        CenterTable table = new CenterTable(data, colonnes, this);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(950, 900);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        center.add(panneau);

    }

    public void showBatiments() {

        center.removeAll();
        
        String[] colonnes = { "Nom", "Adresse", "Propriétaire", "Responsable" };
        Object[][] data = { { "ENSiiE Evry", "Square de la résistance", "ENSiiE", "Tellier" }, { "IBISC", "Evry", "UEVE", "Bouyer" } };

        CenterTable table = new CenterTable(data, colonnes, this);

        JScrollPane panneau = new JScrollPane(table);
        panneau.setSize(950, 900);
        panneau.setBorder(new LineBorder(yellow, 1));
        panneau.setLocation(0, 0);

        center.add(panneau);

    }

    public void initMenu() {

        menu = new JPanel();
        menu.setLocation(0, 0);
        menu.setSize(150, 900);
        menu.setBackground(blue);
        menu.setLayout(null);
        menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, yellow));

        MenuControl mctrl = new MenuControl(this);

        JLabel a38 = new JLabel(" A38");
        a38.setLocation(0, 0);
        a38.setSize(150, 100);
        a38.setFont(titleFont);
        a38.setForeground(yellow);

        MenuItem materiel = new MenuItem("materiel", mctrl);
        materiel.setLocation(0, 100);
        materiel.setSize(150, 40);
        materiel.setBackground(yellow);

        MenuItem emprunts = new MenuItem("emprunts", mctrl);
        emprunts.setLocation(0, 155);
        emprunts.setSize(150, 40);
        emprunts.setBackground(yellow);

        MenuItem personnes = new MenuItem("personnes", mctrl);
        personnes.setLocation(0, 210);
        personnes.setSize(150, 40);
        personnes.setBackground(yellow);

        MenuItem batiments = new MenuItem("batiments", mctrl);
        batiments.setLocation(0, 265);
        batiments.setSize(150, 40);
        batiments.setBackground(yellow);

        JLabel mat = new JLabel("Matériel");
        mat.setFont(menuFont);
        materiel.add(mat);

        JLabel emp = new JLabel("Emprunts");
        emp.setFont(menuFont);
        emprunts.add(emp);

        JLabel pers = new JLabel("Personnes");
        pers.setFont(menuFont);
        personnes.add(pers);

        JLabel bat = new JLabel("Bâtiments");
        bat.setFont(menuFont);
        batiments.add(bat);

        menu.add(a38);
        menu.add(materiel);
        menu.add(emprunts);
        menu.add(personnes);
        menu.add(batiments);

    }

}