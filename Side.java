import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Side extends JPanel {

    Interface interf;
    SideControl sctrl;
    ArrayList<JComponent> fields;
    JLabel title;

    public Side(Interface interf) {

        this.interf = interf;
        this.sctrl = new SideControl(interf);

        this.fields = new ArrayList<>();

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

        title = new JLabel(s);
        title.setFont(interf.sideTitleFont);
        title.setForeground(Color.WHITE);
        title.setSize(title.getPreferredSize());
        title.setLocation(interf.SIDE / 2 - title.getWidth() / 2, 65);
        add(title);

    }

    public void showError(String e) {
        removeAll();
        setTitle("Erreur");
        JTextArea err = new JTextArea(e);
        err.setSize(300, 100);
        err.setMargin(new Insets(10, 10, 10, 10));
        err.setLocation(50, 150);
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
        SideButton batiments = new SideButton(inst, interf.menu.batiments, this, 25, 560);
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
        SideLabel proprio = new SideLabel("Propriétaire : " + mat.getProprietaire().getRaisonSociale(), this, 50, 180);
        SideLabel marque = new SideLabel("Marque : " + mat.getMarque(), this, 50, 210);
        SideLabel prix = new SideLabel("Prix : " + mat.getPrixAchat(), this, 50, 240);
        SideLabel date = new SideLabel("<html>Date d'achat : <br>" + mat.getDateAchat() + "</html>", this, 50, 270);
        SideLabel etat = new SideLabel("Etat : " + mat.getEtat(), this, 50, 330);
        SideLabel connec = new SideLabel("Connectique : " + mat.getConnectique(), this, 50, 360);
        if (mat instanceof Terminal) {
            Terminal ter = (Terminal) mat;
            SideLabel os = new SideLabel("OS : " + ter.getOS() , this, 50, 390);
            SideLabel taille = new SideLabel("Ecran(\") : " + ter.getTailleEcran(), this, 50, 420);
            SideLabel reso = new SideLabel("Resolution : " + ter.getXResolution() + "*" + ter.getYResolution(), this, 50, 450);

        }

        addButtons(mat);

    }

    public void showEmprunt(Emprunt emprunt) {
    }

    public void showBatiment(Batiment batiment) {

        setTitle(batiment.getNom());

        SideLabel idl = new SideLabel("ID : " + batiment.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + batiment.getProprietaire().getRaisonSociale(), this, 50, 200);
        SideLabel adresse = new SideLabel("Adresse : " + batiment.getAdresse(), this, 50, 250);
        SideLabel respo = new SideLabel("Responsable : " + batiment.getResponsable().getPrenom() + " " + batiment.getResponsable().getNom(), this, 50, 300);
        
    }

    public void showSalle(Salle salle) {

        setTitle(salle.getNom());

        SideLabel idl = new SideLabel("ID : " + salle.getId(), this, 50, 150);
        SideLabel batiment = new SideLabel("Batiment : " + salle.getLocalisation().getNom(), this, 50, 200);
        SideLabel etage = new SideLabel("Etage : " + salle.getEtage(), this, 50, 250);
        SideLabel surface = new SideLabel("Surface : " + salle.getSurface(), this, 50, 300);
        
    }

    public void showArmoire(Armoire armoire) {

        setTitle(armoire.getNom());

        SideLabel idl = new SideLabel("ID : " + armoire.getId(), this, 50, 150);
        SideLabel batiment = new SideLabel("Salle : " + armoire.getLocalisation().getNom(), this, 50, 200);

    }

    public void editIndividu(Individu individu) {
    }

    private void createField(String k, String v, int y) {

        SideLabel label = new SideLabel(k, this, 50, y);
        SideTextField field = new SideTextField(k, v, this, 50, y + 30);
        fields.add(field);

    }

    private void createFieldLateral(String k, String v, int y, boolean little) {
        SideLabel label = new SideLabel(k, this, 50, y);
        SideTextField field;
        if (little) {
            field = new SideTextField(k, v, this, 200, y - 7);
            field.setSize(150, 30);
        } else {
            field = new SideTextField(k, v, this, 150, y - 7);
            field.setSize(200, 30);
        }
        fields.add(field);
    }

    private SideComboBox createDropdownLateral(String k, String[] vals, int y, boolean little) {
        SideLabel label = new SideLabel(k, this, 50, y);
        SideComboBox combo = new SideComboBox(k, vals);
        if (little) {
            combo.setLocation(200, y - 7);
            combo.setSize(150, 30);
        } else {
            combo.setLocation(150, y - 7);
            combo.setSize(200, 30);
        }
        add(combo);
        fields.add(combo);
        return combo;
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

    public void editMateriel(Materiel materiel) {
    }

    public void editEmprunt(Emprunt emprunt) {
    }

    public void editBatiment(Batiment batiment) {
    }

    public void editSalle(Salle salle) {
    }

    public void editArmoire(Armoire armoire) {
    }

    public void newIndividu(A38Object filter) {
    }

    public void newEmprunt(A38Object filter) {
    }

    public void newMateriel(A38Object filter) {

        setTitle("Nouveau matériel");
        title.setLocation((int) title.getLocation().getX(), 15);

        fields.clear();

        ArrayList<Institution> institutions = new ArrayList<Institution>(interf.mod.getInstitutions().values());
        String[] proprietaires = new String[institutions.size()];
        for (int i = 0; i < institutions.size(); i++) {
            proprietaires[i] = institutions.get(i).getRaisonSociale();
        }

        SideComboBox proprio = createDropdownLateral("Propriétaire", proprietaires, 75, true);
        if (filter instanceof Institution) {
            Institution inst = (Institution) filter;
            proprio.setSelectedItem(inst.getRaisonSociale());
        }
        createFieldLateral("Nature", "", 115, false);
        createFieldLateral("Modèle", "", 155, false);
        createFieldLateral("Marque", "", 195, false);
        SideComboBox type = createDropdownLateral("Type", new String[] { "peripheriques", "terminaux" }, 235, false);
        type.addItemListener(new MaterielComboControl(this));
        createFieldLateral("Prix d'achat", "", 275, true);
        createFieldLateral("Date d'achat", "", 315, true);
        createFieldLateral("Etat", "", 355, false);

        SideSaveButton save = new SideSaveButton(this, null, fields);

    }

    public void showTerminalOptions() {

        createFieldLateral("OS", "", 395, false);
        createFieldLateral("Taille écran", "", 435, true);
        createFieldLateral("Résolution X", "", 475, true);
        createFieldLateral("Résolution Y", "", 515, true);
        repaint();

    }

    public void newInstitution(A38Object filter) {

        setTitle("Nouvelle Institution");

        fields.clear();

        createField("Raison sociale", "", 200);
        createField("Adresse", "", 280);
        createField("Mail", "", 360);
        createField("Téléphone", "", 440);

        SideSaveButton save = new SideSaveButton(this, null, fields);

    }

    public void newBatiment(A38Object filter) {
    }

    public void newSalle(A38Object filter) {
    }

    public void newArmoire(A38Object filter) {
    }

}