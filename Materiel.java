import java.util.Date;

public class Materiel {

    enum Etat {
        NEUF, USE, ABIME, PANNE;
    }

    private Personne proprietaire;
    private String UUID;
    private String modele;
    private String marque;
    private double prixAchat;
    private Date dateAchat;
    private Etat etat;
    private String[] connectique;

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