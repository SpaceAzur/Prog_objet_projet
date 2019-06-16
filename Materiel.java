import java.util.Date;

public class Materiel {

    protected Institution proprietaire;
    protected String UUID;
    protected String modele;
    protected String marque;
    protected String nature;
    protected double prixAchat;
    protected Date dateAchat;
    protected String etat;
    protected String[] connectique;
    protected int id;

    public Institution getProprietaire() {
        return this.proprietaire;
    }

    public void setProprietaire(Institution proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id=id;
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

    public String[] getConnectique() {
        return this.connectique;
    }

    public void setConnectique(String[] connectique) {
        this.connectique = connectique;
    }

    public void setNature(String nature) {
        this.nature=nature;
    }

}