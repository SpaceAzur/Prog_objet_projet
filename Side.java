import java.awt.Color;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

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

        SideButton materiel = new SideButton(inst, interf.menu.materiels, this, 25, 440);
        SideButton emprunts = new SideButton(inst, interf.menu.emprunts, this, 215, 440);
        SideButton batiments = new SideButton(inst, interf.menu.batiments, this, 25, 520);
        SideButton personnes = new SideButton(inst, interf.menu.personnes, this, 215, 520);
        SideButton salles = new SideButton(inst, interf.menu.salles, this, 25, 600);
        SideButton armoires = new SideButton(inst, interf.menu.armoires, this, 215, 600);

        addButtons(inst);

    }

    public void showIndividu(Individu indiv) {

        setTitle(indiv.getPrenom() + " " + indiv.getNom());

        SideLabel idl = new SideLabel("ID : " + indiv.getId(), this, 50, 150);
        SideLabel status = new SideLabel("Status : " + indiv.getStatus(), this, 50, 200);
        SideLabel adresse = new SideLabel("<html>Adresse : <br>" + indiv.getAdresse() + "</html>", this, 50, 250);
        SideLabel email = new SideLabel("<html>Mail : <br>" + indiv.getEmail() + "</html>", this, 50, 330);
        SideLabel telephone = new SideLabel("Tel : " + indiv.getTelephone(), this, 50, 410);
        SideLabel institution = new SideLabel("<html>Institution : <br>" + indiv.getInstitution().getRaisonSociale(),
                this, 50, 460);

        SideButton materiel = new SideButton(indiv, interf.menu.emprunts, this, 25, 600);
        SideButton batiment = new SideButton(indiv, interf.menu.batiments, this, 215, 600);

        addButtons(indiv);

    }

    public void showMateriel(Materiel mat) {

        setTitle(mat.getModele());

        SideLabel idl = new SideLabel("ID : " + mat.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + mat.getProprietaire().getRaisonSociale(), this, 50, 180);
        SideLabel marque = new SideLabel("Marque : " + mat.getMarque(), this, 50, 210);
        SideLabel prix = new SideLabel("Prix : " + mat.getPrixAchat(), this, 50, 240);
        SideLabel date = new SideLabel("<html>Date d'achat : <br>" + dateF.format(mat.getDateAchat()) + "</html>", this,
                50, 270);
        SideLabel etat = new SideLabel("Etat : " + mat.getEtat(), this, 50, 330);
        SideLabel connec = new SideLabel("Connectique : " + mat.getConnectique(), this, 50, 360);
        if (mat instanceof Terminal) {
            Terminal ter = (Terminal) mat;
            SideLabel os = new SideLabel("OS : " + ter.getOS(), this, 50, 390);
            SideLabel taille = new SideLabel("Ecran(\") : " + ter.getTailleEcran(), this, 50, 420);
            SideLabel reso = new SideLabel("Resolution : " + ter.getXResolution() + "*" + ter.getYResolution(), this,
                    50, 450);
        }

        SideButton emp = new SideButton(mat, interf.menu.emprunts, this, 215, 600);

        addButtons(mat);

    }

    public void showEmprunt(Emprunt emprunt) {

        setTitle("Emprunt n°" + emprunt.getId());

        SideLabel debut = new SideLabel("<html>Début :<br>" + dateF.format(emprunt.getDebut()) + "</html>", this, 50,
                150);
        SideLabel fin = new SideLabel("<html>Fin :<br>" + dateF.format(emprunt.getFin()) + "</html>", this, 50, 210);
        SideLabel materiel = new SideLabel("Matériel : " + emprunt.getMateriel().getModele(), this, 50, 270);
        SideLabel emprunteur = new SideLabel("<html>Emprunteur : <br>" + emprunt.getEmprunteur().getPrenom() + " "
                + emprunt.getEmprunteur().getNom(), this, 50, 300);
        String statuts = emprunt.isRendu() ? "Rendu" : "Non rendu";
        SideLabel statut = new SideLabel("Statut : " + statuts, this, 50, 360);
        SideLabel raison = new SideLabel("Raison : " + emprunt.getRaison(), this, 50, 390);

        SideButton mat = new SideButton(emprunt, interf.menu.materiels, this, 215, 600);

        addButtons(emprunt);

    }

    public void showBatiment(Batiment batiment) {

        setTitle(batiment.getNom());

        SideLabel idl = new SideLabel("ID : " + batiment.getId(), this, 50, 150);
        SideLabel proprio = new SideLabel("Propriétaire : " + batiment.getProprietaire().getRaisonSociale(), this, 50,
                200);
        SideLabel adresse = new SideLabel("Adresse : " + batiment.getAdresse(), this, 50, 250);
        SideLabel respo = new SideLabel(
                "Responsable : " + batiment.getResponsable().getPrenom() + " " + batiment.getResponsable().getNom(),
                this, 50, 300);

        SideButton salle = new SideButton(batiment, interf.menu.salles, this, 215, 600);
        SideButton armoire = new SideButton(batiment, interf.menu.armoires, this, 25, 600);
        SideButton materiel = new SideButton(batiment, interf.menu.materiels, this, 215, 520);

        addButtons(batiment);

    }

    public void showSalle(Salle salle) {

        setTitle(salle.getNom());

        SideLabel idl = new SideLabel("ID : " + salle.getId(), this, 50, 150);
        SideLabel batiment = new SideLabel("Batiment : " + salle.getLocalisation().getNom(), this, 50, 200);
        SideLabel etage = new SideLabel("Etage : " + salle.getEtage(), this, 50, 250);
        SideLabel surface = new SideLabel("Surface : " + salle.getSurface(), this, 50, 300);

        SideButton armoire = new SideButton(salle, interf.menu.armoires, this, 25, 600);
        SideButton materiel = new SideButton(salle, interf.menu.materiels, this, 215, 600);

        addButtons(salle);

    }

    public void showArmoire(Armoire armoire) {

        setTitle(armoire.getNom());

        SideLabel idl = new SideLabel("ID : " + armoire.getId(), this, 50, 150);
        SideLabel salle = new SideLabel("Salle : " + armoire.getLocalisation().getNom(), this, 50, 200);
        SideLabel bat = new SideLabel("Batiment : " + armoire.getLocalisation().getLocalisation().getNom(), this, 50,
                250);

        SideButton materiel = new SideButton(armoire, interf.menu.materiels, this, 215, 600);

        addButtons(armoire);

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

    public void editBatiment(Batiment batiment) {
    }

    public void editEmprunt(Emprunt emprunt) {

        setTitle("Emprunt n°" + emprunt.getId());

        fields.clear();

        ArrayList<Individu> individus = interf.mod.getIndividus(null);
        String[] indivs = new String[individus.size()];

        for (int i = 0; i < individus.size(); i++) {
            indivs[i] = individus.get(i).getPrenom() + " " + individus.get(i).getNom();
        }

        SideComboBox emprunteur = createDropdownLateral("Emprunteur", indivs, 185, true);
        emprunteur.setSelectedItem(emprunt.getEmprunteur().getPrenom() + " " + emprunt.getEmprunteur().getNom());

        ArrayList<Materiel> materiels = interf.mod.getMateriels(null);
        String[] matos = new String[materiels.size()];

        for (int i = 0; i < materiels.size(); i++) {
            matos[i] = materiels.get(i).getMarque() + " " + materiels.get(i).getModele() + " ("
                    + materiels.get(i).getId() + ")";
        }

        SideComboBox materiel = createDropdownLateral("Materiel", matos, 235, false);
        materiel.setSelectedItem(emprunt.getMateriel().getMarque() + " " + emprunt.getMateriel().getModele() + " (" + emprunt.getMateriel().getId() + ")");

        createFieldLateral("Date de début", dateF.format(emprunt.getDebut()), 285, true);
        createFieldLateral("Date de fin", dateF.format(emprunt.getFin()), 335, true);
        createFieldLateral("Raison", emprunt.getRaison(), 385, true);
        SideComboBox rendu = createDropdownLateral("Rendu", new String[] { "Oui", "Non" }, 435, true);
        String renduS = emprunt.isRendu() ? "Oui" : "Non";
        rendu.setSelectedItem(renduS);

        SideSaveButton save = new SideSaveButton(this, emprunt, fields);
        SideCancelButton cancel = new SideCancelButton(this, emprunt);

    }

    public void editSalle(Salle salle) {
    }

    public void editArmoire(Armoire armoire) {
    }

    public void newIndividu(A38Object filter) {
    }

    public void newEmprunt(A38Object filter) {

        setTitle("Nouvel emprunt");

        fields.clear();

        ArrayList<Individu> individus = interf.mod.getIndividus(null);
        String[] indivs = new String[individus.size()];

        for (int i = 0; i < individus.size(); i++) {
            indivs[i] = individus.get(i).getPrenom() + " " + individus.get(i).getNom();
        }

        SideComboBox emprunteur = createDropdownLateral("Emprunteur", indivs, 185, true);
        if (filter instanceof Individu) {
            Individu ind = (Individu) filter;
            emprunteur.setSelectedItem(ind.getPrenom() + " " + ind.getNom());
        }

        ArrayList<Materiel> materiels = interf.mod.getMateriels(null);
        String[] matos = new String[materiels.size()];

        for (int i = 0; i < materiels.size(); i++) {
            matos[i] = materiels.get(i).getMarque() + " " + materiels.get(i).getModele() + " ("
                    + materiels.get(i).getId() + ")";
        }

        SideComboBox materiel = createDropdownLateral("Materiel", matos, 235, false);
        if (filter instanceof Materiel) {
            Materiel mater = (Materiel) filter;
            materiel.setSelectedItem(mater.getMarque() + " " + mater.getModele() + " (" + mater.getId() + ")");
        }

        Date today = Calendar.getInstance().getTime();
        Calendar tom = Calendar.getInstance();
        tom.setTime(today);
        tom.add(Calendar.DATE, 1);
        Date tomorrow=tom.getTime();

        createFieldLateral("Date de début", dateF.format(today), 285, true);
        createFieldLateral("Date de fin", dateF.format(tomorrow), 335, true);
        createFieldLateral("Raison", "", 385, true);
        SideComboBox rendu = createDropdownLateral("Rendu", new String[] { "Oui", "Non" }, 435, true);

        SideSaveButton save = new SideSaveButton(this, null, fields);

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

    public void editMateriel(Materiel materiel) {

        setTitle(materiel.getModele());
        title.setLocation((int) title.getLocation().getX(), 15);

        fields.clear();

        SideLabel idl = new SideLabel("ID : " + materiel.getId(), this, 50, 75);

        ArrayList<Institution> institutions = new ArrayList<Institution>(interf.mod.getInstitutions().values());
        String[] proprietaires = new String[institutions.size()];
        for (int i = 0; i < institutions.size(); i++) {
            proprietaires[i] = institutions.get(i).getRaisonSociale();
        }

        SideComboBox proprio = createDropdownLateral("Propriétaire", proprietaires, 115, true);
        proprio.setSelectedItem(materiel.getProprietaire().getRaisonSociale());

        createFieldLateral("Nature", materiel.getNature(), 155, false);
        createFieldLateral("Modèle", materiel.getModele(), 195, false);
        createFieldLateral("Marque", materiel.getMarque(), 235, false);
        createFieldLateral("Prix d'achat", Double.toString(materiel.getPrixAchat()), 275, true);
        createFieldLateral("Date d'achat", dateF.format(materiel.getDateAchat()), 315, true);
        createFieldLateral("Etat", materiel.getEtat(), 355, false);

        if (materiel instanceof Terminal)
            showTerminalOptions((Terminal) materiel);

        SideSaveButton save = new SideSaveButton(this, materiel, fields);
        SideCancelButton cancel = new SideCancelButton(this, materiel);

    }

    public void showTerminalOptions(Terminal t) {

        createFieldLateral("OS", t == null ? "" : t.getOS(), 395, false);
        createFieldLateral("Taille écran", t == null ? "" : Double.toString(t.getTailleEcran()), 435, true);
        createFieldLateral("Résolution X", t == null ? "" : Integer.toString(t.getXResolution()), 475, true);
        createFieldLateral("Résolution Y", t == null ? "" : Integer.toString(t.getYResolution()), 515, true);
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

    public void newBatiment(A38Object filter) {
    }

    public void newSalle(A38Object filter) {
    }

    public void newArmoire(A38Object filter) {
    }

}