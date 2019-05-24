import java.util.Date;

public class Materiel {

    enum Etat {
        NEUF, USE, ABIME, PANNE;
    }

    protected Personne proprietaire;
    protected String UUID;
    protected String modele;
    protected String marque;
    protected double prixAchat;
    protected Date dateAchat;
    protected Etat etat;
    protected String[] connectique;

    public Personne getProprietaire() {
        return this.proprietaire;
    }

    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public Etat getEtat() {
        return this.etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String[] getConnectique() {
        return this.connectique;
    }

    public void setConnectique(String[] connectique) {
        this.connectique = connectique;
    }

}