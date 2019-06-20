import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public Modele(Connection conn) {

        this.conn = conn;
        initInstitutions();
        initIndividus();
        initMateriels();
        initBatiments();
        initSalles();
        initArmoires();
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

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM terminaux");
            ResultSet rs = ps.executeQuery();
            materiels = new HashMap<Integer, Materiel>();
            SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {

                Terminal t = new Terminal();

                t.setProprietaire(getInstitution(rs.getInt("proprietaire")));
                t.setId(rs.getInt("id"));
                t.setModele(rs.getString("modele"));
                t.setMarque(rs.getString("marque"));
                t.setPrixAchat(rs.getDouble("prixachat"));
                t.setDateAchat(dateF.parse(rs.getString("dateachat")));
                t.setEtat(rs.getString("etat"));
                t.setOS(rs.getString("OS"));
                t.setTailleEcran(rs.getDouble("tailleecran"));
                t.setXResolution((int) rs.getDouble("xresolution"));
                t.setYResolution((int) rs.getDouble("yresolution"));

                materiels.put(t.getId(), t);

            }

            ps = conn.prepareStatement("SELECT * FROM peripheriques");
            rs = ps.executeQuery();

            while (rs.next()) {

                Peripherique t = new Peripherique();

                t.setProprietaire(getInstitution(rs.getInt("proprietaire")));
                t.setId(rs.getInt("id"));
                t.setModele(rs.getString("modele"));
                t.setMarque(rs.getString("marque"));
                t.setPrixAchat(rs.getDouble("prixachat"));
                t.setDateAchat(dateF.parse(rs.getString("dateachat")));
                t.setEtat(rs.getString("etat"));
                t.setNature(rs.getString("nature"));

                materiels.put(t.getId(), t);

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

    private void initEmprunts() { // TO FINISH

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM emprunts");
            ResultSet rs = ps.executeQuery();
            emprunts = new HashMap<>();

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

    

    ///// SAUVEGARDE /////

    public void save(A38Object obj, Map<String, String> values) {

        if (obj instanceof Institution)
            saveInstitution((Institution) obj, values);
        if (obj instanceof Materiel)
            saveMateriel((Materiel) obj, values);
        if (obj instanceof Batiment)
            saveBatiment((Batiment) obj, values);
        if (obj instanceof Emprunt)
            saveEmprunt((Emprunt) obj, values);
        if (obj instanceof Salle)
            saveSalle((Salle) obj, values);
        if (obj instanceof Individu)
            saveIndividu((Individu) obj, values);
        if (obj instanceof Armoire)
            saveArmoire((Armoire) obj, values);

    }

    public void saveInstitution(Institution obj, Map<String, String> values) {

        try {

            String ad = values.get("Adresse");
            String tel = values.get("Téléphone");
            String email = values.get("Mail");
            String rs = values.get("Raison sociale");

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE institutions SET adresse=?, telephone=?, email=?, raisonsocial=? WHERE id=?");
            ps.setString(1, values.get("Adresse"));
            ps.setString(2, values.get("Téléphone"));
            ps.setString(3, values.get("Mail"));
            ps.setString(4, values.get("Raison sociale"));
            ps.setInt(5, obj.getId());
            ps.executeUpdate();

            obj.setAll(ad, tel, email, rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveMateriel(Materiel obj, Map<String, String> values) { 



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
        for (int i = 0; i < emprunts.size(); i++) {
            if (filter instanceof Individu && emprunts.get(i).getEmprunteur() == filter) {
                result.add(emprunts.get(i));
            }
        }
        return result;
    }

    public ArrayList<Emprunt> getEmprunts(boolean isRendu) {
        ArrayList<Emprunt> result = new ArrayList<Emprunt>();
        for (int i = 0; i < emprunts.size(); i++) {
            if (emprunts.get(i).isRendu() == isRendu) {
                result.add(emprunts.get(i));
            }
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
            if (filter instanceof Institution && mat.getProprietaire().getId() == filter.getId())
                result.add(mat);
        }

        return result;
    }

    public Materiel getMateriel(int id) {
        return materiels.get(id);
    }

    public void deleteMateriel(Materiel mat) {

        PreparedStatement ps = null;
        try {
            if (mat instanceof Terminal)
                ps = conn.prepareStatement("DELETE FROM terminaux WHERE id=?");
            else if (mat instanceof Peripherique)
                ps = conn.prepareStatement("DELETE FROM peripheriques WHERE id=?");
            ps.setInt(1, mat.getId());
            ps.executeQuery();
            materiels.remove(mat.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /// INDIVIDUS ///

    public ArrayList<Individu> getIndividus(A38Object filter) {

        if (filter == null)
            return new ArrayList<Individu>(individus.values());

        ArrayList<Individu> result = new ArrayList<Individu>();

        for (Individu individu : individus.values()) {
            if (filter instanceof Institution && individu.getInstitution().getId() == filter.getId())
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

        return null;

    }

    public Batiment getBatiment(int id) {
        return batiments.get(id);
    }

    /// SALLES ///

    public ArrayList<Salle> getSalles(A38Object filter) {

        if (filter == null)
            return new ArrayList<Salle>(salles.values());

        return null;

    }

    public Salle getSalle(int id) {
        return salles.get(id);
    }

    /// ARMOIRES ///

    public ArrayList<Armoire> getArmoires(A38Object filter) {

        if (filter == null)
            return new ArrayList<Armoire>(armoires.values());

        return null;

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

}