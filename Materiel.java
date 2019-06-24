package a38;

import java.util.Date;

public class Materiel extends A38Object {

    protected Institution proprietaire;
    protected String modele;
    protected String marque;
    protected String nature;
    protected double prixAchat;
    protected Date dateAchat;
    protected String etat;
    protected String connectique;
    protected Armoire armoire;

    public void setAll(Institution proprio, String modele, String nature, String marque, double prixAchat,
            Date dateAchat, String etat, String conn) {

                this.proprietaire=proprio;
                this.modele=modele;
                this.nature=nature;
                this.marque=marque;
                this.prixAchat=prixAchat;
                this.dateAchat=dateAchat;
                this.etat=etat;
                this.connectique=conn;

    }

    public Institution getProprietaire() {
        return this.proprietaire;
    }

    public void setProprietaire(Institution proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setArmoire(Armoire armoire) {
        this.armoire=armoire;
    }

    public Armoire getArmoire() {
        return this.armoire;
    }

    public String getModele() {
        return this.modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return this.marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getPrixAchat() {
        return this.prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Date getDateAchat() {
        return this.dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getConnectique() {
        return this.connectique;
    }

    public void setConnectique(String connectique) {
        this.connectique = connectique;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNature() {
        return this.nature;
    }

}