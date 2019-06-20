import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Side extends JPanel {

    Interface interf;
    SideControl sctrl;
    DeleteControl delctrl;

    public Side(Interface interf) {

        this.interf=interf;
        this.sctrl=new SideControl(interf);
        this.delctrl=new DeleteControl(interf);

        setLocation(interf.MENU + interf.CENTER, 0);
        setSize(interf.SIDE, interf.HEIGHT);
        setBackground(interf.blue);
        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, interf.yellow));

    }

    public void showInstitution(Institution inst) {

        removeAll();

        JLabel rs = new JLabel(inst.getRaisonSociale());
        rs.setFont(interf.sideTitleFont);
        rs.setForeground(Color.WHITE);
        rs.setSize(rs.getPreferredSize());
        rs.setLocation(interf.SIDE / 2 - rs.getWidth() / 2, 50);
        add(rs);

        SideLabel idl = new SideLabel("ID : " + inst.getId(), this, 50, 150);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + inst.getAdresse() + "</html>", this, 50, 200);
        SideLabel email = new SideLabel("<html>Mail : <br>" + inst.getEmail() + "</html>", this, 50, 280);
        SideLabel telephone = new SideLabel("Tel : " + inst.getTelephone(), this, 50, 360);

        SideButton materiel = new SideButton(inst, interf.menu.materiels, this, 25, 450);
        SideButton emprunts = new SideButton(inst, interf.menu.emprunts, this, 215, 450);
        SideButton batiments = new SideButton(inst, interf.menu.batiments,  this, 25, 560);
        SideButton personnes = new SideButton(inst, interf.menu.personnes, this, 215, 560);

        DeleteButton delete = new DeleteButton(inst);
        delete.addMouseListener(delctrl);
        add(delete);

        repaint();

    }

    public void showIndividu(Individu indiv) {

        removeAll();

        JLabel identite = new JLabel(indiv.getPrenom() + " " + indiv.getNom());
        identite.setFont(interf.sideTitleFont);
        identite.setForeground(Color.WHITE);
        identite.setSize(identite.getPreferredSize());
        identite.setLocation(interf.SIDE / 2 - identite.getWidth() / 2, 50);
        add(identite);

        SideLabel idl = new SideLabel("ID : " + indiv.getId(), this, 50, 150);
        SideLabel status = new SideLabel("Status : " + indiv.getStatus(), this, 50, 200);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + indiv.getAdresse() + "</html>", this, 50, 250);
        SideLabel email = new SideLabel("<html>Mail : <br>" + indiv.getEmail() + "</html>", this, 50, 330);
        SideLabel telephone = new SideLabel("Tel : " + indiv.getTelephone(), this, 50, 410);

        /*SideButton materiel = new SideButton("Institutions", menu.materiels, id, this, 25, 450);
        SideButton emprunts = new SideButton("Institutions", menu.emprunts, id, this, 215, 450);
        SideButton batiments = new SideButton("Institutions", menu.batiments, id, this, 25, 560);
        SideButton personnes = new SideButton("Institutions", menu.personnes, id, this, 215, 560);*/

        DeleteButton delete = new DeleteButton(indiv);
        delete.addMouseListener(delctrl);
        add(delete);

        repaint();

    }

    public void showMateriel(Materiel mat) {

        removeAll();

        JLabel matl = new JLabel(mat.getModele());
        matl.setFont(interf.sideTitleFont);
        matl.setForeground(Color.WHITE);
        matl.setSize(matl.getPreferredSize());
        matl.setLocation(interf.SIDE / 2 - matl.getWidth() / 2, 50);
        add(matl);

        SideLabel idl = new SideLabel("ID : " + mat.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + mat.getProprietaire().getRaisonSociale(), this, 50,
                180);
        SideLabel marque = new SideLabel("Marque : " + mat.getMarque(), this, 50, 210);
        SideLabel prix = new SideLabel("Prix : " + mat.getPrixAchat(), this, 50, 240);
        SideLabel date = new SideLabel("<html>Date d'achat : <br>" + mat.getDateAchat() + "</html>", this, 50, 270);
        SideLabel etat = new SideLabel("Etat : " + mat.getEtat(), this, 50, 330);
        SideLabel connec = new SideLabel("Connectique : " + mat.getConnectique(), this, 50, 360);

        DeleteButton delete = new DeleteButton(mat);
        delete.addMouseListener(delctrl);
        add(delete);

        repaint();

    }

}