import java.util.ArrayList;

public class Armoire {

    private String nom;
    private int nbEtageres;
    private ArrayList<Materiel> materiels;


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbEtageres() {
        return this.nbEtageres;
    }

    public void setNbEtageres(int nbEtageres) {
        this.nbEtageres = nbEtageres;
    }

    public ArrayList<Materiel> getMateriels() {
        return this.materiels;
    }

    public void setMateriels(ArrayList<Materiel> materiels) {
        this.materiels = materiels;
    }


}