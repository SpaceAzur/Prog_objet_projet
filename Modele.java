package a38;

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

    }

    ///// SAUVEGARDE /////

    public A38Object save(A38Object obj, Map<String, String> values, String type) throws SQLException, ParseException {

        A38Object result = null;

        if (obj instanceof Institution || type.equals("Institutions"))
            result = saveInstitution((Institution) obj, values);
        if (obj instanceof Materiel || type.equals("Matériels"))
            result = saveMateriel((Materiel) obj, values);
        if (obj instanceof Batiment || type.equals("Bâtiments"))
            result = saveBatiment((Batiment) obj, values);
        if (obj instanceof Emprunt || type.equals("Emprunts"))
            result = saveEmprunt((Emprunt) obj, values);
        if (obj instanceof Salle || type.equals("Salles"))
            result = saveSalle((Salle) obj, values);
        if (obj instanceof Individu || type.equals("Personnes"))
            result = saveIndividu((Individu) obj, values);
        if (obj instanceof Armoire || type.equals("Armoires"))
            result = saveArmoire((Armoire) obj, values);

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
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return inst;

    }

    public Materiel saveMateriel(Materiel obj, Map<String, String> values) throws SQLException, ParseException {

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
            mat = obj;
            type = obj instanceof Terminal ? new String("terminaux") : new String("peripheriques");
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
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }

        }

        return mat;

    }

    public Batiment saveBatiment(Batiment obj, Map<String, String> values) throws SQLException {

        String ad = values.get("Adresse");
        String nom = values.get("Nom");
        int respo = getIndividu(values.get("Responsable")).getId();
        int inst = getInstitution(values.get("Propriétaire")).getId();

        PreparedStatement ps;

        Batiment bat = null;

        if (obj != null) {
            ps = conn.prepareStatement(
                    "UPDATE batiments SET nombatiment=?, adresse=?, institution=?, responsable=? WHERE id=?");
            bat = obj;
        } else
            ps = conn.prepareStatement(
                    "INSERT INTO batiments(nombatiment, adresse, institution, responsable) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, nom);
        ps.setString(2, ad);
        ps.setInt(3, inst);
        ps.setInt(4, respo);

        if (obj != null)
            ps.setInt(5, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null)
            obj.setAll(ad, nom, getInstitution(values.get("Propriétaire")), getIndividu(values.get("Responsable")));

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    batiments.put(id, new Batiment(id, ad, nom, getInstitution(values.get("Propriétaire")),
                            getIndividu(values.get("Responsable"))));
                    bat = batiments.get(id);
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return bat;

    }

    public Armoire saveArmoire(Armoire obj, Map<String, String> values) throws SQLException {

        String nom = values.get("Nom");
        int salle = Integer.valueOf(values.get("Salle").split("[\\(\\)]")[1]);

        PreparedStatement ps;

        Armoire arm = null;

        if (obj != null) {
            ps = conn.prepareStatement("UPDATE armoires SET nom=?, salle=? WHERE id=?");
            arm = obj;
        } else
            ps = conn.prepareStatement("INSERT INTO armoires(nom, salle) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, nom);
        ps.setInt(2, salle);

        if (obj != null)
            ps.setInt(3, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null) {
            obj.setNom(nom);
            obj.getLocalisation().removeArmoire(obj);
            obj.setLocalisation(getSalle(salle));
            getSalle(salle).addArmoire(obj);
        }

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    armoires.put(id, new Armoire(id, nom, getSalle(salle)));
                    getSalle(salle).addArmoire(armoires.get(id));
                    arm = armoires.get(id);
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return arm;

    }

    public Salle saveSalle(Salle obj, Map<String, String> values) throws SQLException {

        int num = Integer.valueOf(values.get("Num"));
        int etage = Integer.valueOf(values.get("Etage"));
        int surface = Integer.valueOf(values.get("Surface"));
        int bat = getBatiment(values.get("Batiment")).getId();

        PreparedStatement ps;

        Salle salle = null;

        if (obj != null) {
            ps = conn.prepareStatement("UPDATE salles SET numsalle=?, etage=?, surface=?, batiment=? WHERE id=?");
            salle = obj;
        } else
            ps = conn.prepareStatement("INSERT INTO salles(numsalle, etage, surface, batiment) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, num);
        ps.setInt(2, etage);
        ps.setInt(3, surface);
        ps.setInt(4, bat);

        if (obj != null)
            ps.setInt(5, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null) {
            obj.getLocalisation().removeSalle(obj);
            obj.setAll(etage, surface, Integer.toString(num), getBatiment(bat));
            getBatiment(bat).addSalle(obj);
        }

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    salles.put(id, new Salle(id, etage, surface, Integer.toString(num), getBatiment(bat)));
                    getBatiment(bat).addSalle(salles.get(id));
                    salle = salles.get(id);
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return salle;

    }

    public Individu saveIndividu(Individu obj, Map<String, String> values) throws SQLException {

        String ad = values.get("Adresse");
        String tel = values.get("Téléphone");
        String email = values.get("Mail");
        String status = values.get("Status");
        String prenom = values.get("Prénom");
        String nom = values.get("Nom");
        int inst = getInstitution(values.get("Institution")).getId();

        PreparedStatement ps;

        Individu indiv = null;

        if (obj != null) {
            ps = conn.prepareStatement(
                    "UPDATE individus SET nom=?, prenom=?, status=?, adresse=?, telephone=?, email=?, institution=? WHERE id=?");
            indiv = obj;
        } else
            ps = conn.prepareStatement(
                    "INSERT INTO individus(nom, prenom, status, adresse, telephone, email, institution) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, status);
        ps.setString(4, ad);
        ps.setString(5, tel);
        ps.setString(6, email);
        ps.setInt(7, inst);

        if (obj != null)
            ps.setInt(8, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null)
            obj.setAll(prenom, nom, status, ad, tel, email, getInstitution(values.get("Institution")));

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    individus.put(id, new Individu(id, prenom, nom, status, ad, tel, email,
                            getInstitution(values.get("Institution"))));
                    indiv = individus.get(id);
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return indiv;

    }

    public Emprunt saveEmprunt(Emprunt obj, Map<String, String> values) throws SQLException, ParseException {

        int idEmprunteur = getIndividu(values.get("Emprunteur")).getId();
        int idMateriel = Integer.valueOf(values.get("Materiel").split("[\\(\\)]")[1]);
        String debut = values.get("Date de début");
        String fin = values.get("Date de fin");
        String raison = values.get("Raison");
        boolean rendu = values.get("Rendu").equals("Oui");

        if (dateF.parse(debut).after(dateF.parse(fin))) {
            throw new SQLException("Date constraint not respected : beginning must precede end date");
        }

        PreparedStatement ps;

        Emprunt emp = null;

        if (obj != null) {
            ps = conn.prepareStatement(
                    "UPDATE emprunts SET datedebut=?, datefin=?, raison=?, rendu=?, idemprunteur=?, materiel=? WHERE numemprunt=?");
            emp = obj;
        } else
            ps = conn.prepareStatement(
                    "INSERT INTO emprunts(datedebut, datefin, raison, rendu, idemprunteur, materiel) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, debut);
        ps.setString(2, fin);
        ps.setString(3, raison);
        ps.setBoolean(4, rendu);
        ps.setInt(5, idEmprunteur);
        ps.setInt(6, idMateriel);

        if (obj != null)
            ps.setInt(7, obj.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0)
            throw new SQLException("Creation/update failed, no rows affected.");

        if (obj != null)
            emp.setAll(dateF.parse(debut), dateF.parse(fin), raison, rendu, getIndividu(idEmprunteur),
                    getMateriel(idMateriel));

        else {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    emprunts.put(id, new Emprunt(id, dateF.parse(debut), dateF.parse(fin), raison, rendu,
                            getIndividu(idEmprunteur), getMateriel(idMateriel)));
                    emp = getEmprunt(id);
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }
        }

        return emp;

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
            if (filter instanceof Materiel && emp.getMateriel() == filter)
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
            if (filter instanceof Batiment && mat.getArmoire() != null
                    && mat.getArmoire().getLocalisation().getLocalisation() == filter)
                result.add(mat);
            if (filter instanceof Salle && mat.getArmoire() != null && mat.getArmoire().getLocalisation() == filter)
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

    public Individu getIndividu(String prenomnom) {

        for (Individu individu : individus.values()) {
            if ((individu.getPrenom() + " " + individu.getNom()).equals(prenomnom))
                return individu;
        }
        return null;

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

    public Batiment getBatiment(String nom) {

        for (Batiment batiment : batiments.values()) {
            if ((batiment.getNom().equals(nom)))
                return batiment;
        }
        return null;

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
            if (filter instanceof Salle && armoire.getLocalisation() == filter)
                result.add(armoire);
            if (filter instanceof Materiel && armoire.getMateriels().contains(filter))
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

    public void deleteObject(A38Object obj) throws SQLException {

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

    public void deleteIndividu(Individu indiv) throws SQLException {

        if (getBatiments(indiv).size() != 0)
            throw new SQLException("Cet individu est responsable d'un ou plusieurs bâtiments, corrigez cela d'abord.");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM individus WHERE id=?");
        ps.setInt(1, indiv.getId());
        ps.executeUpdate();

        ArrayList<Emprunt> emp = getEmprunts(indiv);

        for (Emprunt emprunt : emp)
            if (emprunt.getEmprunteur() == indiv)
                deleteEmprunt(emprunt);

        individus.remove(indiv.getId());

    }

    public void deleteEmprunt(Emprunt emp) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM emprunts WHERE numemprunt=?");
        ps.setInt(1, emp.getId());
        ps.executeUpdate();
        emprunts.remove(emp.getId());

    }

    public void deleteBatiment(Batiment bat) throws SQLException {

        if (getSalles(bat).size() != 0)
            throw new SQLException("Supprimez d'abord les salles de ce bâtiment");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM batiments WHERE id=?");
        ps.setInt(1, bat.getId());
        ps.executeUpdate();

        batiments.remove(bat.getId());

    }

    public void deleteSalle(Salle salle) throws SQLException {

        if (getArmoires(salle).size() != 0)
            throw new SQLException("Des armoires sont présentes dans cette salle");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM salles WHERE id=?");
        ps.setInt(1, salle.getId());
        ps.executeUpdate();

        salle.getLocalisation().removeSalle(salle);
        salles.remove(salle.getId());

    }

    public void deleteArmoire(Armoire armoire) throws SQLException {

        if (getMateriels(armoire).size() != 0)
            throw new SQLException("Des matériels sont contenus dans cette armoire");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM armoires WHERE id=?");
        ps.setInt(1, armoire.getId());
        ps.executeUpdate();

        armoire.getLocalisation().removeArmoire(armoire);
        armoires.remove(armoire.getId());

    }

    public void deleteInstitution(Institution per) throws SQLException {

        if (getEmprunts(per).size() != 0)
            throw new SQLException("Des emprunts sont liés à cette institution");
        if (getMateriels(per).size() != 0)
            throw new SQLException("Des matériels sont liés à cette institution");
        if (getBatiments(per).size() != 0)
            throw new SQLException("Des bâtiments sont liés à cette institution");
        if (getIndividus(per).size() != 0)
            throw new SQLException("Des individus sont liés à cette institution");
        if (getSalles(per).size() != 0)
            throw new SQLException("Des salles sont liés à cette institution");
        if (getArmoires(per).size() != 0)
            throw new SQLException("Des armoires sont liés à cette institution");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM institutions WHERE id=?");
        ps.setInt(1, per.getId());
        ps.executeUpdate();
        institutions.remove(per.getId());

    }

    public void deleteMateriel(Materiel mat) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM materiels WHERE id=?");
        ps.setInt(1, mat.getId());
        ps.executeUpdate();

        if (mat.getArmoire() != null)
            mat.getArmoire().removeMateriel(mat);

        ArrayList<Emprunt> emp = getEmprunts(mat);

        for (Emprunt emprunt : emp)
            if (emprunt.getMateriel() == mat)
                deleteEmprunt(emprunt);

        materiels.remove(mat.getId());

    }

}