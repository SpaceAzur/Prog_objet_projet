import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Side extends JPanel {

    Interface interf;
    SideControl sctrl;
    ArrayList<SideTextField> fields;

    public Side(Interface interf) {

        this.interf=interf;
        this.sctrl=new SideControl(interf);

        this.fields=new ArrayList<>();

        setLocation(interf.MENU + interf.CENTER, 0);
        setSize(interf.SIDE, interf.HEIGHT);
        setBackground(interf.blue);
        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, interf.yellow));

    }

    private void addButtons(A38Object obj) {

        EditButton edit = new EditButton(this, obj);
        add(edit);

        DeleteButton delete = new DeleteButton(this, obj);
        add(delete);

    }

    private void setTitle(String s) {

        JLabel title = new JLabel(s);
        title.setFont(interf.sideTitleFont);
        title.setForeground(Color.WHITE);
        title.setSize(title.getPreferredSize());
        title.setLocation(interf.SIDE / 2 - title.getWidth() / 2, 50);
        add(title);

    }

    public void showError(String e) {
        removeAll();
        setTitle("Erreur");
        JTextArea err = new JTextArea(e);
        err.setSize(300,100);
        err.setMargin(new Insets(10,10,10,10));
        err.setLocation(50,150);
        err.setLineWrap(true);
        err.setEditable(false);
        add(err);
        repaint();
    }

    public void showInstitution(Institution inst) {

        setTitle(inst.getRaisonSociale());

        SideLabel idl = new SideLabel("ID : " + inst.getId(), this, 50, 150);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + inst.getAdresse() + "</html>", this, 50, 200);
        SideLabel email = new SideLabel("<html>Mail : <br>" + inst.getEmail() + "</html>", this, 50, 280);
        SideLabel telephone = new SideLabel("Tel : " + inst.getTelephone(), this, 50, 360);

        SideButton materiel = new SideButton(inst, interf.menu.materiels, this, 25, 450);
        SideButton emprunts = new SideButton(inst, interf.menu.emprunts, this, 215, 450);
        SideButton batiments = new SideButton(inst, interf.menu.batiments,  this, 25, 560);
        SideButton personnes = new SideButton(inst, interf.menu.personnes, this, 215, 560);

        addButtons(inst);

    }

    public void showIndividu(Individu indiv) {

        setTitle(indiv.getPrenom() + " " + indiv.getNom());

        SideLabel idl = new SideLabel("ID : " + indiv.getId(), this, 50, 150);
        SideLabel status = new SideLabel("Status : " + indiv.getStatus(), this, 50, 200);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + indiv.getAdresse() + "</html>", this, 50, 250);
        SideLabel email = new SideLabel("<html>Mail : <br>" + indiv.getEmail() + "</html>", this, 50, 330);
        SideLabel telephone = new SideLabel("Tel : " + indiv.getTelephone(), this, 50, 410);

        addButtons(indiv);

    }

    public void showMateriel(Materiel mat) {

        setTitle(mat.getModele());

        SideLabel idl = new SideLabel("ID : " + mat.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + mat.getProprietaire().getRaisonSociale(), this, 50,
                180);
        SideLabel marque = new SideLabel("Marque : " + mat.getMarque(), this, 50, 210);
        SideLabel prix = new SideLabel("Prix : " + mat.getPrixAchat(), this, 50, 240);
        SideLabel date = new SideLabel("<html>Date d'achat : <br>" + mat.getDateAchat() + "</html>", this, 50, 270);
        SideLabel etat = new SideLabel("Etat : " + mat.getEtat(), this, 50, 330);
        SideLabel connec = new SideLabel("Connectique : " + mat.getConnectique(), this, 50, 360);

        addButtons(mat);

    }

    public void showEmprunt(Emprunt emprunt) { }

    public void showBatiment(Batiment batiment) { }

    public void showSalle(Salle salle) { }

    public void showArmoire(Armoire armoire) { }

    public void editIndividu(Individu individu) { }

    private void createField(String k, String v, int y) {

        SideLabel label = new SideLabel(k, this, 50, y);
        SideTextField field = new SideTextField(k, v, this, 50, y+30);
        fields.add(field);
        
    }

    public void editInstitution(Institution institution) { 

        setTitle(institution.getRaisonSociale());

        fields.clear();

        SideLabel idl = new SideLabel("ID : " + institution.getId(), this, 50, 150);

        createField("Raison sociale", institution.getRaisonSociale(), 200);
        createField("Adresse", institution.getAdresse(), 280);
        createField("Mail", institution.getEmail(), 360);
        createField("Téléphone", institution.getTelephone(), 440);

        SideSaveButton save = new SideSaveButton(this, institution, fields);
        SideCancelButton cancel = new SideCancelButton(this, institution);

    }

    public void editMateriel(Materiel materiel) { }

    public void editEmprunt(Emprunt emprunt) { }

    public void editBatiment(Batiment batiment) { }
    
    public void editSalle(Salle salle) { } 

    public void editArmoire(Armoire armoire) { }

    public void newIndividu(A38Object filter) { }

    public void newEmprunt(A38Object filter) { }

    public void newMateriel(A38Object filter) { }

    public void newInstitution(A38Object filter) { 

        setTitle("Nouvelle Institution");

        fields.clear();

        createField("Raison sociale", "", 200);
        createField("Adresse", "", 280);
        createField("Mail", "", 360);
        createField("Téléphone", "", 440);

        SideSaveButton save = new SideSaveButton(this, null, fields);
        
    }

    public void newBatiment(A38Object filter) { }

    public void newSalle(A38Object filter) { }

    public void newArmoire(A38Object filter) { }

}