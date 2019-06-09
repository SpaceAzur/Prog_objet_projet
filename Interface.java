import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Interface {

    JFrame fenetre;
    JPanel menu;
    JPanel center;
    JPanel side;
    Modele mod;

    Font titleFont = new Font("Verdana", Font.BOLD, 50);
    Font menuFont = new Font("Verdana", Font.PLAIN, 25);
    Font headerFont = new Font("Verdana", Font.BOLD, 15);
    Font tableFont = new Font("Verdana", Font.PLAIN, 15);
    Color yellow = new Color(242, 190, 84);
    Color blue = new Color(21, 62, 92);
    Color lightBlue = new Color(135, 174, 180);

    public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
 
        public SimpleHeaderRenderer() {
            setFont(headerFont);
            setForeground(Color.WHITE);
            setBackground(blue);
            setOpaque(true);
            setHorizontalAlignment(JLabel.CENTER);
            setBorder(BorderFactory.createMatteBorder(0, 1, 1, 2, yellow));
        }
         
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());

            return this;
        }
     
    }

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
        side.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, yellow));

    }

    public void initCenter() {

        center = new JPanel();
        center.setLocation(150, 0);
        center.setSize(950, 900);
        center.setBackground(Color.WHITE);
        center.setLayout(null);

    }

    public void showMateriel() {

        String[] colonnes = { "Prénom", "Nom", "Statut" };
        Object[][] data = { { "Pierre", "Danel", "étudiant" }, { "Antoine", "Escobar", "étudiant" } };

        JTable table = new JTable(data, colonnes) {
            @Override
            public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
                return false;
            }
            @Override
            public Component prepareRenderer( TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
                jc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(yellow, 1), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                return jc;
            }

        };
        table.setRowHeight(35);
        table.setGridColor(yellow);
        table.setFont(tableFont);
        table.setBackground(lightBlue);
        table.setSelectionBackground(blue);
        table.setSelectionForeground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, yellow));

        JLabel a38 = new JLabel(" A38");
        a38.setLocation(0, 0);
        a38.setSize(150, 100);
        a38.setFont(titleFont);
        a38.setForeground(yellow);

        JPanel materiel = new JPanel();
        materiel.setLocation(0, 100);
        materiel.setSize(150, 40);
        materiel.setBackground(yellow);

        JPanel emprunts = new JPanel();
        emprunts.setLocation(0, 155);
        emprunts.setSize(150, 40);
        emprunts.setBackground(yellow);

        JPanel personnes = new JPanel();
        personnes.setLocation(0, 210);
        personnes.setSize(150, 40);
        personnes.setBackground(yellow);

        JPanel batiments = new JPanel();
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