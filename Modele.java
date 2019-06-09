import java.util.ArrayList;

public class Modele {

    ArrayList<Emprunt> emprunts;
    ArrayList<Materiel> materiels;
    ArrayList<Personne> personnes;
    ArrayList<Batiment> batiments;
    ArrayList<Utilisateur> utilisateurs;

    public Modele() {
        emprunts=new ArrayList<Emprunt>();
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

    public ArrayList<Materiel> getMateriels() {
        return this.materiels;
    }

    public ArrayList<Personne> getPersonnes() {
        return this.personnes;
    }

    public ArrayList<Batiment> getBatiments() {
        return this.batiments;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

}