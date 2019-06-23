import java.awt.*;
import javax.swing.*;

public class Interface {

    Modele mod;

    public final int HEIGHT = 700;
    public final int WIDTH = 1150;
    public final int MENU = 150;
    public final int CENTER = 600;
    public final int SIDE = 400;
    public final int TOPBAR = 75;

    JFrame fenetre;

    Side side;
    Menu menu;
    Center center;
    Topbar topbar;

    public Font titleFont = new Font("Verdana", Font.BOLD, 50);
    public Font sideTitleFont = new Font("Verdana", Font.BOLD, 35);
    public Font topbarFont = new Font("Verdana", Font.BOLD, 28);
    public Font menuFont = new Font("Verdana", Font.PLAIN, 25);
    public Font headerFont = new Font("Verdana", Font.BOLD, 15);
    public Font tableFont = new Font("Verdana", Font.PLAIN, 15);
    public Font sideFont = new Font("Verdana", Font.PLAIN, 20);

    public Color yellow = new Color(242, 190, 84);
    public Color blue = new Color(21, 62, 92);
    public Color lightBlue = new Color(135, 174, 180);

    public Interface(Modele mod) {

        this.mod = mod;

        initWindow();

        menu = new Menu(this);
        side = new Side(this);
        center = new Center(this);
        topbar = new Topbar(this);

        fenetre.add(menu);
        fenetre.add(center);
        fenetre.add(side);
        fenetre.add(topbar);

        fenetre.setVisible(true);

        showObjects("Institutions", null);
        editItem(mod.getInstitution(2));

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

    public void showObject(A38Object obj) {

        System.out.println("plouf");
        side.removeAll();
        if (obj instanceof Individu)
            side.showIndividu((Individu) obj);
        if (obj instanceof Institution)
            side.showInstitution((Institution) obj);
        if (obj instanceof Materiel)
            side.showMateriel((Materiel) obj);
        if (obj instanceof Emprunt)
            side.showEmprunt((Emprunt) obj);
        if (obj instanceof Batiment)
            side.showBatiment((Batiment) obj);
        if (obj instanceof Salle)
            side.showSalle((Salle) obj);
        if (obj instanceof Armoire)
            side.showArmoire((Armoire) obj);
        side.repaint();
        
    }

    public void showObjects(String type, A38Object filter) {

        center.setCurrentType(type);
        center.setFilter(filter);

        if (type.equals("Personnes"))
            center.showIndividus();
        if (type.equals("Emprunts"))
            center.showEmprunts();
        if (type.equals("Matériels"))
            center.showMateriels();
        if (type.equals("Institutions"))
            center.showInstitutions();
        if (type.equals("Bâtiments"))
            center.showBatiments();
        if (type.equals("Salles"))
            center.showSalles();
        if (type.equals("Armoires"))
            center.showArmoires();

    }

    public void newObject(String type, A38Object filter) {

        side.removeAll();
        if (type.equals("Personnes"))
            side.newIndividu(filter);
        if (type.equals("Emprunts"))
            side.newEmprunt(filter);
        if (type.equals("Matériels"))
            side.newMateriel(filter);
        if (type.equals("Institutions"))
            side.newInstitution(filter);
        if (type.equals("Bâtiments"))
            side.newBatiment(filter);
        if (type.equals("Salles"))
            side.newSalle(filter);
        if (type.equals("Armoires"))
            side.newArmoire(filter);
        side.repaint();

    }

    public void changeTitle(String t) {
        topbar.changeTitle(t);
    }

    public void changeCurrentMenu(MenuButton b) {
        menu.changeCurrent(b);
    }

    public void refreshObjects() {
        showObjects(center.getCurrentType(), center.getFilter());
    }

    public void showError(String e) {
        side.showError(e);
    }

    public void editItem(A38Object obj) {
        
        side.removeAll();
        if (obj instanceof Individu)
            side.editIndividu((Individu) obj);
        if (obj instanceof Institution)
            side.editInstitution((Institution) obj);
        if (obj instanceof Materiel)
            side.editMateriel((Materiel) obj);
        if (obj instanceof Emprunt)
            side.editEmprunt((Emprunt) obj);
        if (obj instanceof Batiment)
            side.editBatiment((Batiment) obj);
        if (obj instanceof Salle)
            side.editSalle((Salle) obj);
        if (obj instanceof Armoire)
            side.editArmoire((Armoire) obj);
        side.repaint();

    }

}