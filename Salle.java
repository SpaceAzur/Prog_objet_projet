import java.util.ArrayList;

public class Salle extends A38Object {

    private String nom;
    private int etage;
    private double surface;
    private ArrayList<Armoire> armoires;


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtage() {
        return this.etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public double getSurface() {
        return this.surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public ArrayList<Armoire> getArmoires() {
        return this.armoires;
    }

    public void setArmoires(ArrayList<Armoire> armoires) {
        this.armoires = armoires;
    }


}