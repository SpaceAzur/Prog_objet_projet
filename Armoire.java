import java.util.ArrayList;

public class Armoire extends A38Object {

    private String nom;
    private ArrayList<Materiel> materiels;
    private Salle localisation;

    public Armoire(int id, String nom, Salle localisation) {
        this.id=id;
        this.nom=nom;
        this.localisation=localisation;
        this.materiels=new ArrayList<Materiel>();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Materiel> getMateriels() {
        return this.materiels;
    }

    public void setMateriels(ArrayList<Materiel> materiels) {
        this.materiels = materiels;
    }

    public void addMateriel(Materiel m) {
        this.materiels.add(m);
    }

    public void removeMateriel(Materiel m) {
        this.materiels.remove(m);
    }

    public Salle getLocalisation() {
        return this.localisation;
    }


}