package Tema1;

public class Circumscriptie {
    private String id_circusc;
    private String circumscriptie;
    private String regiune;
    private int nr_voturi;
    public String getCircumscriptie() {
        return circumscriptie;
    }
    public String getRegiune() {
        return regiune;
    }
    public int getNr_voturi() {
        return nr_voturi;
    }
    public void setNr_voturi() {
        this.nr_voturi++;
    }
    public String getIDcirc() {
        return id_circusc;
    }
    public void setCircumscriptie(String id_circusc ,String circumscriptie, String regiune, int nr_voturi) {
        this.id_circusc = id_circusc;
        this.circumscriptie = circumscriptie;
        this.regiune = regiune;
        this.nr_voturi = nr_voturi;
    }
    public String getCircus_info() {
        return id_circusc + " " + circumscriptie + " " + regiune + " " + nr_voturi;
    }
}
