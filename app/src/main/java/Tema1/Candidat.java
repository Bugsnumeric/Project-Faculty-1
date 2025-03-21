package Tema1;

public class Candidat implements Comparable<Candidat>{
    private String candidat;
    private String cnp;
    private int age;
    private String id_candidat;
    private int nr_voturi;

    public String getCandidat() {
        return candidat;
    }
    public String getCnp() {
        return cnp;
    }
    public boolean validCnp(String cnp) {
        return cnp.length() == 13;
    }
    public boolean validAge(int age) {
        return age >= 35;
    }
    public void setCandidat(String id_candidat, String cnp, int age, String candidat, int nr_voturi) {
        this.candidat = candidat;
        this.cnp = cnp;
        this.age = age;
        this.id_candidat = id_candidat;
        this.nr_voturi = nr_voturi;
    }
    public String getCandidat_info() {
        return candidat + " " + cnp + " " + age;
    }
    public int getNr_voturi() {
        return nr_voturi;
    }
    public void setNr_voturi() {
        this.nr_voturi++;
    }
    public int compareTo(Candidat other) {
        int votComparison = Integer.compare(other.nr_voturi, this.nr_voturi);
        if (votComparison == 0) {
            return other.cnp.compareTo(this.cnp);
        }
        return votComparison;
    }
}
