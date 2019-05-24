import java.util.ArrayList;

public class Batiment {

    private String adresse;
    private ArrayList<Salle> salles;


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


}