public class Terminal extends Materiel {

    private String OS;
    private double tailleEcran;
    private int xResolution;
    private int yResolution;

    public String getOS() {
        return this.OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getTailleEcran() {
        return this.tailleEcran;
    }

    public void setTailleEcran(double tailleEcran) {
        this.tailleEcran = tailleEcran;
    }

    public int getXResolution() {
        return this.xResolution;
    }

    public void setXResolution(int xResolution) {
        this.xResolution = xResolution;
    }

    public int getYResolution() {
        return this.yResolution;
    }

    public void setYResolution(int yResolution) {
        this.yResolution = yResolution;
    }

}