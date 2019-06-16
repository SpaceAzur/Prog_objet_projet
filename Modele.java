import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Modele {

    Connection conn;

    ArrayList<Emprunt> emprunts;
    HashMap<Integer, Materiel> materiels;
    HashMap<Integer, Institution> institutions;
    HashMap<Integer, Individu> individus;
    HashMap<Integer, Batiment> batiments;
    ArrayList<Utilisateur> utilisateurs;

    public Modele(Connection conn) {
        emprunts = new ArrayList<Emprunt>();
        materiels = new HashMap<Integer, Materiel>();
        institutions = new HashMap<Integer, Institution>();
        individus = new HashMap<Integer, Individu>();
        batiments = new HashMap<Integer, Batiment>();
        utilisateurs = new ArrayList<Utilisateur>();

        this.conn = conn;
        initInstitutions();
        initIndividus();
        initMateriels();
    }

    private void initInstitutions() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM institutions");
            ResultSet rs = ps.executeQuery();
            
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
            
            while (rs.next()) {
                int id = rs.getInt("id");            
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String status = rs.getString("status");
                String adresse = rs.getString("adresse");                
                String telephone = rs.getString("telephone");                
                String email = rs.getString("email");
                int idInstitution = rs.getInt("institution");
                
                individus.put(id, new Individu(id, prenom, nom, status, adresse, telephone, email, institutions.get(idInstitution)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initMateriels() {

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM terminaux");
            ResultSet rs = ps.executeQuery();
            SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                
                Terminal t = new Terminal();

                t.setProprietaire(getInstitutions().get(rs.getInt("proprietaire")));
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

            ps = conn.prepareStatement("SELECT * FROM terminaux");
            rs = ps.executeQuery();

            while (rs.next()) {

                Peripherique t = new Peripherique();

                t.setProprietaire(getInstitutions().get(rs.getInt("proprietaire")));
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

    public ArrayList<Emprunt> getEmprunts() {
        return this.emprunts;
    }

    public ArrayList<Emprunt> getEmprunts(Personne emprunteur) {
        ArrayList<Emprunt> result = new ArrayList<Emprunt>();
        for (int i = 0; i < emprunts.size(); i++) {
            if (emprunts.get(i).getEmprunteur() == emprunteur) {
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

    public ArrayList<Materiel> getMateriels(String src, int id) {

        if (src==null && id==0) return new ArrayList<Materiel>(materiels.values());
        
        ArrayList<Materiel> result = new ArrayList<Materiel>();

        for (Materiel mat : materiels.values()) {
            if (src.equals("Institutions") && mat.getProprietaire().getId()==id) {
                result.add(mat);
            }
        }

        return result;
    }

    public HashMap<Integer, Batiment> getBatiments() {
        return this.batiments;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

    public HashMap<Integer, Institution> getInstitutions() {
        return this.institutions;
    }

    public HashMap<Integer, Individu> getIndividus() {
        return this.individus;
    }

    public ArrayList<Individu> getIndividus(String src, int id) {

        if (src==null && id==0) return new ArrayList<Individu>(individus.values());

        ArrayList<Individu> result = new ArrayList<Individu>();

        for (Individu individu : individus.values()) {
            if (src.equals("Institutions") && individu.getInstitution().getId()==id) {
                result.add(individu);
            }
        }

        return result;
        
    }

    public void deleteMateriel(Materiel mat) {

        PreparedStatement ps=null;
        try {
            if (mat instanceof Terminal) ps=conn.prepareStatement("DELETE FROM terminaux WHERE id=?");
            else if (mat instanceof Peripherique) ps=conn.prepareStatement("DELETE FROM peripheriques WHERE id=?");
            ps.setInt(1, mat.getId());
            ps.executeQuery();
            materiels.remove(mat.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteInstitution(Institution per) {

        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("DELETE FROM personnesmorales WHERE id=?");
            ps.setInt(1, per.getId());
            ps.executeUpdate();
            institutions.remove(per.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}