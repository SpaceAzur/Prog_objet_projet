public class PersonnePhysique extends Personne {
    protected String prenom;
    protected String nom;
    protected String status;

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + " (" + this.status + ")";
    }

    public PersonnePhysique(String prenom, String nom, String status) {
        this.prenom=prenom;
        this.nom=nom;
        this.status=status;
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

    

}