
public class A38 {

    public static void main(String[] args) {

        Modele mod = new Modele();
        Personne p1 = new PersonnePhysique("Pierre", "Danel", "étudiant");
        mod.getEmprunts().add(new Emprunt(p1));

        Interface gui = new Interface(mod);

    }

}