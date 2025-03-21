package Tema1;

public class Alegeri {
    private String id_alegeri;
    private String nume_alegeri;
    public static boolean verificare_existenta = false;
    public String getId_alegeri() {
        return id_alegeri;
    }
    public String getNume_alegeri() {
        return nume_alegeri;
    }
    public void setAlegeri(String id_alegeri ,String nume_alegeri) {
        this.id_alegeri = id_alegeri;
        this.nume_alegeri = nume_alegeri;
    }
    public boolean verificare_out() {
        return !verificare_existenta;
    }
    public String getAlegeri() {
        return id_alegeri + " " + nume_alegeri;
    }
}
