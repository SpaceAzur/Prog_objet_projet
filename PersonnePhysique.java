public class PersonnePhysique extends Personne {
    protected String prenom;
    protected String nom;
    protected enum status {etudiant, enseignant, enseignant_chercheur, chercheur}

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