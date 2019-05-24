public class Interface {

    private Controleur ctrl;

    public void setControleur(Controleur ctrl) {
        this.ctrl=ctrl;
    }

    public void go() {

        System.out.println("Bienvenue dans A38 !");
        System.out.println("What do you wanna dooooo ?");
        ctrl.getMateriels().add(new Materiel())
    
    }

}