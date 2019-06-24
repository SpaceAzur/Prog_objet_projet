package a38;

import java.util.ArrayList;

public class Salle extends A38Object {

    private String nom;
    private int etage;
    private int surface;
    private ArrayList<Armoire> armoires;
    private Batiment localisation;

    public void addArmoire(Armoire armoire) {
        this.armoires.add(armoire);
    }

    public Salle(int id, int etage, int surface, String nom, Batiment localisation) {
        this.id=id;
        this.etage=etage;
        this.surface=surface;
        this.nom=nom;
        this.localisation=localisation;
        this.armoires=new ArrayList<Armoire>();
    }

    public void setAll(int etage, int surface, String nom, Batiment localisation) {
        this.etage=etage;
        this.surface=surface;
        this.nom=nom;
        this.localisation=localisation;
    }

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

    public int getSurface() {
        return this.surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public ArrayList<Armoire> getArmoires() {
        return this.armoires;
    }

    public void setArmoires(ArrayList<Armoire> armoires) {
        this.armoires = armoires;
    }

    public void removeArmoire(Armoire arm) {
        this.armoires.remove(arm);
    }

    public Batiment getLocalisation() {
        return this.localisation;
    }

}