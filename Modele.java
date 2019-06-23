import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Modele {

    Connection conn;

    HashMap<Integer, Emprunt> emprunts;
    HashMap<Integer, Materiel> materiels;
    HashMap<Integer, Institution> institutions;
    HashMap<Integer, Individu> individus;
    HashMap<Integer, Batiment> batiments;
    HashMap<Integer, Salle> salles;
    HashMap<Integer, Armoire> armoires;

    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

    public Modele(Connection conn) {

        this.conn = conn;
        initInstitutions();
        initIndividus();
        initBatiments();
        initSalles();
        initArmoires();
        initMateriels();
        initEmprunts();

    }

    ///// INITIALISATION /////

    private void initInstitutions() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM institutions");
            ResultSet rs = ps.executeQuery();
            institutions = new HashMap<Integer, Institution>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String raison = rs.getString("raisonsocial");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");

                institutions.put(id, new Institution(id, adresse, telephone, email, raison));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initIndividus() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM individus");
            ResultSet rs = ps.executeQuery();
            individus = new HashMap<Integer, Individu>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String status = rs.getString("status");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                int idInstitution = rs.getInt("institution");

                individus.put(id, new Individu(id, prenom, nom, status, adresse, telephone, email,
                        institutions.get(idInstitution)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initMateriels() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiels");
            ResultSet rs = ps.executeQuery();
            materiels = new HashMap<Integer, Materiel>();

            while (rs.next()) {

                String type = rs.getString("type");
                Materiel t = null;

                if (type.equals("terminaux"))
                    t = new Terminal();
                else
                    t = new Peripherique();

                t.setProprietaire(getInstitution(rs.getInt("proprietaire")));
                t.setId(rs.getInt("id"));
                t.setModele(rs.getString("modele"));
                t.setMarque(rs.getString("marque"));
                t.setPrixAchat(rs.getDouble("prixachat"));
                t.setDateAchat(dateF.parse(rs.getString("dateachat")));
                t.setEtat(rs.getString("etat"));
                t.setNature(rs.getString("nature"));
                Armoire armoire = getArmoire(rs.getInt("armoire"));
                t.setArmoire(armoire);

                if (t instanceof Terminal) {
                    ((Terminal) t).setOS(rs.getString("OS"));
                    ((Terminal) t).setTailleEcran(rs.getDouble("tailleecran"));
                    ((Terminal) t).setXResolution((int) rs.getDouble("xresolution"));
                    ((Terminal) t).setYResolution((int) rs.getDouble("yresolution"));
                }

                materiels.put(t.getId(), t);
                if (armoire != null)
                    armoire.addMateriel(t);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initBatiments() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM batiments");
            ResultSet rs = ps.executeQuery();
            batiments = new HashMap<Integer, Batiment>();

            while (rs.next()) {

                int id = rs.getInt("id");
                Institution proprietaire = getInstitution(rs.getInt("institution"));
                String nom = rs.getString("nombatiment");
                String adresse = rs.getString("adresse");
                Individu responsable = getIndividu(rs.getInt("responsable"));

                batiments.put(id, new Batiment(id, adresse, nom, proprietaire, responsable));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initSalles() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM salles");
            ResultSet rs = ps.executeQuery();
            salles = new HashMap<Integer, Salle>();

            while (rs.next()) {

                int id = rs.getInt("id");
                Batiment batiment = getBatiment(rs.getInt("batiment"));
                String nom = Integer.toString(rs.getInt("numsalle"));
                int surface = rs.getInt("surface");
                int etage = rs.getInt("etage");
                Salle salle = new Salle(id, etage, surface, nom, batiment);
                salles.put(id, salle);
                batiment.addSalle(salle);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initArmoires() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM armoires");
            ResultSet rs = ps.executeQuery();
            armoires = new HashMap<>();

            while (rs.next()) {

                int id = rs.getInt("id");
                Salle salle = getSalle(rs.getInt("salle"));
                String nom = rs.getString("nom");
                Armoire armoire = new Armoire(id, nom, salle);
                armoires.put(id, armoire);
                salle.addArmoire(armoire);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initEmprunts() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM emprunts");
            ResultSet rs = ps.executeQuery();
            emprunts = new HashMap<>();

            while (rs.next()) {

                int id = rs.getInt("numemprunt");
                boolean rendu = rs.getBoolean("rendu");
                Date debut = (Date) dateF.parse(rs.getString("datedebut"));
                Date fin = (Date) dateF.parse(rs.getString("datefin"));
                String raison = rs.getString("raison");
                Materiel mat = getMateriel(rs.getInt("materiel"));
                Individu emprunteur = getIndividu(rs.getInt("idemprunteur"));

                emprunts.put(id, new Emprunt(id, debut, fin, raison, rendu, emprunteur, mat));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///// SAUVEGARDE /////

    public A38Object save(A38Object obj, Map<String, String> values, String type) throws SQLException, ParseException {

        A38Object result = null;

        if (obj instanceof Institution || type.equals("Institutions"))
            result = saveInstitution((Institution) obj, values);
        if (obj instanceof Materiel || type.equals("Matériels"))
            saveMateriel((Materiel) obj, values);
        if (obj instanceof Batiment || type.equals("Bâtiments"))
            saveBatiment((Batiment) obj, values);
        if (obj instanceof Emprunt || type.equals("Emprunts"))
            saveEmprunt((Emprunt) obj, values);
        if (obj instanceof Salle || type.equals("Salles"))
            saveSalle((Salle) obj, values);
        if (obj instanceof Individu || type.equals("Personnes"))
            saveIndividu((Individu) obj, values);
        if (obj instanceof Armoire || type.equals("Armoires"))
            saveArmoire((Armoire) obj, values);

        return result;

    }

    public Institution saveInstitution(Institution obj, Map<String, String> values) throws SQLException {

        String ad = values.get("Adresse");
        String tel = values.get("Téléphone");
        String email = values.get("Mail");
        String rs = values.get("Raison sociale");

        PreparedStatement ps;

        Institution inst = null;

        if (obj != null) {
            ps = conn.prepareStatement(
                    "UPDATE institutions SET raisonsocial=?, adresse=?, telephone=?, email=? WHERE id=?");
            inst = obj;
        } else
            ps = conn.prepareStatement(
                    "INSERT INTO institutions(raisonsocial, adresse, telephone, email) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

        ps.setString(2, ad);
        ps.setString(3, tel);
        ps.setString(4, email);
        ps.setString(1, rs);
        if (obj != null)
            ps.setInt(5, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null)
            obj.setAll(ad, tel, email, rs);

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    institutions.put(id, new Institution(id, ad, tel, email, rs));
                    inst = institutions.get(id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

        return inst;

    }

    public void saveMateriel(Materiel obj, Map<String, String> values) throws SQLException, ParseException {

        int idProprio = getInstitution(values.get("Propriétaire")).getId();
        String modele = values.get("Modèle");
        String nature = values.get("Nature");
        String marque = values.get("Marque");
        double prix = Double.valueOf(values.get("Prix d'achat"));
        String date = values.get("Date d'achat");
        String etat = values.get("Etat");
        String type = values.get("Type");
        String os = values.get("OS");
        String taille = values.get("Taille écran");
        String xres = values.get("Résolution X");
        String yres = values.get("Résolution Y");

        PreparedStatement ps;

        Materiel mat = null;

        if (obj != null) {
            ps = conn.prepareStatement(
                    "UPDATE materiels SET proprietaire=?, nature=?, modele=?, marque=?, prixachat=?, dateachat=?, etat=?, type=?, OS=?, tailleecran=?, xresolution=?, yresolution=? WHERE id=?");
        } else {
            ps = conn.prepareStatement(
                    "INSERT INTO materiels(proprietaire, nature, modele, marque, prixachat, dateachat, etat, type, OS, tailleecran, xresolution, yresolution) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
        }

        ps.setInt(1, idProprio);
        ps.setString(2, modele);
        ps.setString(3, nature);
        ps.setString(4, marque);
        ps.setDouble(5, prix);
        ps.setString(6, date);
        ps.setString(7, etat);
        ps.setString(8, type);
        ps.setString(9, os);
        ps.setString(10, taille);
        ps.setString(11, xres);
        ps.setString(12, yres);
        if (obj != null)
            ps.setInt(13, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null) {
            obj.setAll(getInstitution(idProprio), modele, nature, marque, prix, dateF.parse(date), etat);
            if (obj instanceof Terminal) {
                ((Terminal) obj).setOS(os);
                ((Terminal) obj).setTailleEcran(Double.valueOf(taille));
                ((Terminal) obj).setXResolution(Integer.valueOf(xres));
                ((Terminal) obj).setYResolution(Integer.valueOf(yres));
            }
        }

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    if (type.equals("terminaux")) {
                        mat = new Terminal();
                        ((Terminal) mat).setOS(os);
                        ((Terminal) mat).setTailleEcran(Double.valueOf(taille));
                        ((Terminal) mat).setXResolution(Integer.valueOf(xres));
                        ((Terminal) mat).setYResolution(Integer.valueOf(yres));
                    } else if (type.equals("peripheriques"))
                        mat = new Peripherique();
                    mat.setId(id);
                    mat.setAll(getInstitution(idProprio), modele, nature, marque, prix, dateF.parse(date), etat);

                    materiels.put(id, mat);
                }
            }

        }

    }

    public void saveBatiment(Batiment obj, Map<String, String> values) {

    }

    public void saveArmoire(Armoire obj, Map<String, String> values) {

    }

    public void saveSalle(Salle obj, Map<String, String> values) {

    }

    public void saveIndividu(Individu obj, Map<String, String> values) {

    }

    public void saveEmprunt(Emprunt obj, Map<String, String> values) {

    }

    ///// GETTERS /////

    /// EMPRUNTS ///

    public ArrayList<Emprunt> getEmprunts(A38Object filter) {

        if (filter == null)
            return new ArrayList<Emprunt>(emprunts.values());

        ArrayList<Emprunt> result = new ArrayList<Emprunt>();

        for (Emprunt emp : emprunts.values()) {
            if (filter instanceof Individu && emp.getEmprunteur() == filter)
                result.add(emp);
            if (filter instanceof Institution && emp.getMateriel().getProprietaire() == filter)
                result.add(emp);
        }
        return result;
    }

    public Emprunt getEmprunt(int id) {
        return emprunts.get(id);
    }

    /// MATERIELS ///

    public ArrayList<Materiel> getMateriels(A38Object filter) {

        if (filter == null)
            return new ArrayList<Materiel>(materiels.values());

        ArrayList<Materiel> result = new ArrayList<Materiel>();

        for (Materiel mat : materiels.values()) {
            if (filter instanceof Institution && mat.getProprietaire() == filter)
                result.add(mat);
            if (filter instanceof Armoire && mat.getArmoire() == filter)
                result.add(mat);
            if (filter instanceof Batiment && mat.getArmoire().getLocalisation().getLocalisation() == filter)
                result.add(mat);
            if (filter instanceof Salle && mat.getArmoire().getLocalisation() == filter)
                result.add(mat);
            if (filter instanceof Emprunt && ((Emprunt) filter).getMateriel() == mat)
                result.add(mat);
        }

        return result;
    }

    public Materiel getMateriel(int id) {
        return materiels.get(id);
    }

    /// INDIVIDUS ///

    public ArrayList<Individu> getIndividus(A38Object filter) {

        if (filter == null)
            return new ArrayList<Individu>(individus.values());

        ArrayList<Individu> result = new ArrayList<Individu>();

        for (Individu individu : individus.values()) {
            if (filter instanceof Institution && individu.getInstitution() == filter)
                result.add(individu);
        }

        return result;

    }

    public Individu getIndividu(int id) {
        return individus.get(id);
    }

    /// BATIMENTS ///

    public ArrayList<Batiment> getBatiments(A38Object filter) {

        if (filter == null)
            return new ArrayList<Batiment>(batiments.values());

        ArrayList<Batiment> result = new ArrayList<>();

        for (Batiment batiment : batiments.values()) {
            if (filter instanceof Institution && batiment.getProprietaire() == filter)
                result.add(batiment);
            if (filter instanceof Individu && batiment.getResponsable() == filter)
                result.add(batiment);
        }

        return result;

    }

    public Batiment getBatiment(int id) {
        return batiments.get(id);
    }

    /// SALLES ///

    public ArrayList<Salle> getSalles(A38Object filter) {

        if (filter == null)
            return new ArrayList<Salle>(salles.values());

        ArrayList<Salle> result = new ArrayList<>();

        for (Salle salle : salles.values()) {
            if (filter instanceof Institution && salle.getLocalisation().getProprietaire() == filter)
                result.add(salle);
            if (filter instanceof Batiment && salle.getLocalisation() == filter)
                result.add(salle);
        }

        return result;

    }

    public Salle getSalle(int id) {
        return salles.get(id);
    }

    /// ARMOIRES ///

    public ArrayList<Armoire> getArmoires(A38Object filter) {

        if (filter == null)
            return new ArrayList<Armoire>(armoires.values());

        ArrayList<Armoire> result = new ArrayList<>();

        for (Armoire armoire : armoires.values()) {
            if (filter instanceof Institution
                    && armoire.getLocalisation().getLocalisation().getProprietaire() == filter)
                result.add(armoire);
            if (filter instanceof Batiment && armoire.getLocalisation().getLocalisation() == filter)
                result.add(armoire);
        }

        return result;

    }

    public Armoire getArmoire(int id) {
        return armoires.get(id);
    }

    /// INSTITUTIONS ///

    public HashMap<Integer, Institution> getInstitutions() {
        return this.institutions;
    }

    public Institution getInstitution(int id) {
        return institutions.get(id);
    }

    public Institution getInstitution(String name) {

        for (Institution institution : institutions.values()) {
            if (institution.getRaisonSociale().equals(name))
                return institution;
        }
        return null;

    }

    ///// SUPPRESSION /////

    public void deleteObject(A38Object obj) {

        if (obj instanceof Individu)
            deleteIndividu((Individu) obj);
        if (obj instanceof Institution)
            deleteInstitution((Institution) obj);
        if (obj instanceof Materiel)
            deleteMateriel((Materiel) obj);
        if (obj instanceof Emprunt)
            deleteEmprunt((Emprunt) obj);
        if (obj instanceof Batiment)
            deleteBatiment((Batiment) obj);
        if (obj instanceof Salle)
            deleteSalle((Salle) obj);
        if (obj instanceof Armoire)
            deleteArmoire((Armoire) obj);

    }

    public void deleteIndividu(Individu indiv) {
    }

    public void deleteEmprunt(Emprunt emp) {
    }

    public void deleteBatiment(Batiment bat) {
    }

    public void deleteSalle(Salle salle) {
    }

    public void deleteArmoire(Armoire armoire) {
    }

    public void deleteInstitution(Institution per) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM personnesmorales WHERE id=?");
            ps.setInt(1, per.getId());
            ps.executeUpdate();
            institutions.remove(per.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteMateriel(Materiel mat) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM materiels WHERE id=?");
            ps.setInt(1, mat.getId());
            ps.executeQuery();
            materiels.remove(mat.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}