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

    Side side;
    Menu menu;
    Center center;
    Topbar topbar;

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
        if (obj instanceof Individu)
            side.showIndividu((Individu) obj);
        if (obj instanceof Institution)
            side.showInstitution((Institution) obj);
        if (obj instanceof Materiel)
            side.showMateriel((Materiel) obj);
    }

    public void showObjects(String type, A38Object filter) {

        if (type.equals("Personnes")) center.showIndividus(filter);
        if (type.equals("Emprunts")) center.showEmprunts(filter);
        if (type.equals("Matériels")) center.showMateriels(filter);
        if (type.equals("Institutions")) center.showInstitutions(filter);
        if (type.equals("Bâtiments")) center.showBatiments(filter);

    }

    public void changeTitle(String t) {
        topbar.changeTitle(t);
    }

    public void changeCurrentMenu(MenuButton b) {
        menu.changeCurrent(b);
    }

}