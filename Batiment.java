import java.util.ArrayList;

public class Batiment extends A38Object {

    private String adresse;
    private String nom;
    private Institution proprietaire;
    private ArrayList<Salle> salles;
    private Individu responsable;

    public Batiment(int id, String adresse, String nom, Institution proprietaire, Individu responsable) {

        this.id=id;
        this.adresse=adresse;
        this.nom=nom;
        this.proprietaire=proprietaire;
        this.responsable=responsable;

    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Salle> getSalles() {
        return this.salles;
    }

    public void setSalles(ArrayList<Salle> salles) {
        this.salles = salles;
    }

    public String getNom() {
        return this.nom;
    }

    public Institution getProprietaire() {
        return this.proprietaire;
    }

    public Individu getResponsable() {
        return this.responsable;
    }


}