package Tema1;

public class Persoana {
    private int age;
    private String name;
    private String cnp;
    private String circusc;
    private String neindemanatic;
    private String frauda;
    private String votat;
    public boolean validCnp(String cnp) {
        return cnp.length() == 13;
    }
    public boolean validAge(int age) {
        return age >= 18;
    }
    public void setPersoana(String name, String cnp, int age, String circusc, String neindemanatic, String frauda, String votat) {
        this.name = name;
        this.cnp = cnp;
        this.age = age;
        this.circusc = circusc;
        this.neindemanatic = neindemanatic;
        this.frauda = frauda;
        this.votat = votat;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String getCnp() {
        return cnp;
    }
    public String getCircusc() {
        return circusc;
    }
    public void setFrauda(String frauda) {
        this.frauda = frauda;
    }
    public void setVotat(String votat) {
        this.votat = votat;
    }
    public String getVotat() {
        return votat;
    }
    public String getFrauda(){
        return frauda;
    }
}
