public class Personne {

    private String adresse;
    private String telephone;
    private String email;
    private int id;

    public Personne() {
        
    }

    public Personne(int id, String ad, String tel, String email) {
        this.id=id;
        this.adresse=ad;
        this.telephone=tel;
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

    public int getId() {
        return id;
    }

}
