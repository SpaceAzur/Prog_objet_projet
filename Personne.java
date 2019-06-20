public class Personne extends A38Object {

    protected String adresse;
    protected String telephone;
    protected String email;

    public Personne() {

    }

    public Personne(int id, String adresse, String telephone, String email) {
        this.id=id;
        this.adresse=adresse;
        this.telephone=telephone;
        this.email=email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
