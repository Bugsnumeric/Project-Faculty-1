package Tema1;

public class Vot implements Comparable<Vot>{
    private int nr_voturi;
    private String circumscriptie;
    private String nume;
    private String cnp;
    private String regiune;
    public void setVoturi(String circumscriptie, String nume, String cnp, int nr_voturi, String regiune) {
        this.nume = nume;
        this.cnp = cnp;
        this.circumscriptie = circumscriptie;
        this.nr_voturi = nr_voturi;
        this.regiune = regiune;
    }
    public int getNr_voturi() {
        return nr_voturi;
    }
    public String getCircumscriptie() {
        return circumscriptie;
    }
    public String getNume() {
        return nume;
    }
    public String getCnp() {
        return cnp;
    }
    public void setNr_voturi(){
        this.nr_voturi++;
    }
    public int compareTo(Vot other) {
        int votComparison = Integer.compare(other.nr_voturi, this.nr_voturi);
        if (votComparison == 0) {
            return other.cnp.compareTo(this.cnp);
        }
        return votComparison;
    }
    public String getRegiune() {
        return regiune;
    }
}
