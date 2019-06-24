package a38;

import java.util.ArrayList;
import java.util.Date;

public class Emprunt extends A38Object {

    private Date debut;
    private Date fin;
    private String raison;
    private boolean rendu;
    private Individu emprunteur;
    private Materiel materiel;

    public Emprunt(Individu p) {
        this.emprunteur=p;
    }

    public Emprunt(int id, Date debut, Date fin, String raison, boolean rendu, Individu emprunteur, Materiel materiel) {
        this.debut=debut;
        this.fin=fin;
        this.raison=raison;
        this.rendu=rendu;
        this.emprunteur=emprunteur;
        this.materiel=materiel;
        this.id=id;
    }

    public void setAll(Date debut, Date fin, String raison, boolean rendu, Individu emprunteur, Materiel materiel) {

        this.debut=debut;
        this.fin=fin;
        this.raison=raison;
        this.rendu=rendu;
        this.emprunteur=emprunteur;
        this.materiel=materiel;
        
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

    public Individu getEmprunteur() {
        return this.emprunteur;
    }

    public void setEmprunteur(Individu emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Materiel getMateriel() {
        return this.materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

}