package a38;

public class Individu extends Personne {

    protected String prenom;
    protected String nom;
    protected String status;
    protected Institution institution;

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + " (" + this.status + ")";
    }

    public Individu(int id, String prenom, String nom, String status, String adresse, String telephone, String email, Institution institution) {
        super(id, adresse, telephone, email);
        this.prenom=prenom;
        this.nom=nom;
        this.status=status;
        this.institution=institution;
    }

    public void setAll(String prenom, String nom, String status, String adresse, String telephone, String email, Institution institution) {
        this.prenom=prenom;
        this.nom=nom;
        this.status=status;
        this.adresse=adresse;
        this.telephone=telephone;
        this.email=email;
        this.institution=institution;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatus() {
        return this.status;
    }

    public Institution getInstitution() {
        return this.institution;
    }

    

}