import java.util.ArrayList;
import java.util.Date;

public class Emprunt extends A38Object {

    private Date debut;
    private Date fin;
    private String raison;
    private boolean rendu;
    private Personne emprunteur;
    private ArrayList<Materiel> materiel;

    public Emprunt(Personne p) {
        this.emprunteur=p;

    }

    public Date getDebut() {
        return this.debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return this.fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getRaison() {
        return this.raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public boolean isRendu() {
        return this.rendu;
    }

    public void setRendu(boolean rendu) {
        this.rendu = rendu;
    }

    public Personne getEmprunteur() {
        return this.emprunteur;
    }

    public void setEmprunteur(Personne emprunteur) {
        this.emprunteur = emprunteur;
    }

    public ArrayList<Materiel> getMateriel() {
        return this.materiel;
    }

    public void setMateriel(ArrayList<Materiel> materiel) {
        this.materiel = materiel;
    }

}