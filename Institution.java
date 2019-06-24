package a38;

public class Institution extends Personne {
    private String raisonSociale;

    public Institution(int id, String ad, String tel, String email, String rs) {
        super(id, ad, tel, email);
        this.raisonSociale=rs;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public void setAll(String ad, String tel, String email, String rs) {
        this.adresse=ad;
        this.telephone=tel;
        this.email=email;
        this.raisonSociale=rs;
    }

}