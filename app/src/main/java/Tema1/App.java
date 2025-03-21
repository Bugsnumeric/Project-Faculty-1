package Tema1;

import java.io.*;
import java.util.*;

public class App {
    private Scanner scanner;
    int k = 0;
    public ArrayList<Alegeri> alegeriList = new ArrayList<Alegeri>();
    public ArrayList<Circumscriptie> circumscriptieList = new ArrayList<Circumscriptie>();
    public ArrayList<Candidat> info_candidati = new ArrayList<Candidat>();
    public ArrayList<Persoana> info_pers = new ArrayList<Persoana>();
    public ArrayList<Vot> voturi = new ArrayList<Vot>();

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }
    public void run() {
        while (scanner.hasNextLine()) {
                String code = scanner.nextLine();

                Comanda comanda = new Comanda();
                comanda.setCommand(code);

                if (Objects.equals(comanda.getCommand(), "0")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);

                    for(int i = 0; i < alegeriList.size(); i++) {
                        if (Objects.equals(alegeriList.get(i).getId_alegeri(), parts[0])) {
                            System.out.println("EROARE: Deja exista alegeri cu id " + alegeriList.get(i).getId_alegeri());
                            break;
                        }
                    }

                    if(alegeriList.isEmpty()) {
                        Alegeri alegeri = new Alegeri();
                        alegeri.setAlegeri(parts[0], parts[1]);
                        alegeriList.add(alegeri);
                        System.out.println("S-au creat alegerile " + alegeri.getNume_alegeri());
                    }
                    ///////////////////////comanda 1
                } else if(Objects.equals(comanda.getCommand(), "1")) {
                    String input = scanner.nextLine();

                    for(int i = 0; i < alegeriList.size(); i++) {
                        if (Objects.equals(alegeriList.get(i).getId_alegeri(), input) && k < 1) {
                            System.out.println("Au pornit alegerile "+ alegeriList.get(i).getNume_alegeri());
                            k++;
                        } else if(k > 0){
                            System.out.println("EROARE: Alegerile deja au inceput");
                        } else {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        }
                    }
                    //////////////////////comanda 2
                } else if(Objects.equals(comanda.getCommand(), "2")) {
                    String circumsc_in = scanner.nextLine();
                    String[] parts = circumsc_in.split(" ", 3);
                    String id_in = parts[0];
                    String nume_in = parts[1];
                    String nume_reg = parts[2];
                    boolean cauta = false;

                    for(int i = 0; i < alegeriList.size(); i++) {
                        if(Objects.equals(alegeriList.get(i).getId_alegeri(), id_in)) {
                            cauta = true;
                            break;
                        }
                    }

                    if(k == 0) {
                        System.out.println("EROARE: Nu este perioada de votare");
                        cauta = false;
                    }

                    for(int i = 0; i < circumscriptieList.size(); i++) {
                        if(Objects.equals(circumscriptieList.get(i).getCircumscriptie(), nume_in)) {
                            System.out.println("EROARE: Deja exista o circumscriptie cu numele "+ nume_in);
                            cauta = false;
                            break;
                        }
                    }

                    for (int i = 0; i < alegeriList.size(); i++) {
                        if (!Objects.equals(alegeriList.get(i).getId_alegeri(), id_in)) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                            cauta = false;
                            break;
                        }
                    }

                    if(cauta) {
                        Circumscriptie circ = new Circumscriptie();
                        circ.setCircumscriptie(id_in, nume_in, nume_reg, 0);
                        circumscriptieList.add(circ);
                        System.out.println("S-a adaugat circumscriptia "+ nume_in);
                    }
                    ///////////////comanda 3
                } else if(Objects.equals(comanda.getCommand(), "3")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    String input_ID = parts[0];
                    String input_circumscriptie = parts[1];
                    boolean cauta = false;

                    for(int i = 0; i < circumscriptieList.size(); i++) {
                        if(Objects.equals(circumscriptieList.get(i).getCircumscriptie(), input_circumscriptie)) {
                            cauta = true;
                            break;
                        }
                    }

                    if(!cauta) {
                        System.out.println("EROARE: Nu exista o circumscriptie cu numele "+ input_circumscriptie);
                    }
                    for (int i = 0; i < alegeriList.size(); i++) {
                        if (Objects.equals(alegeriList.get(i).getId_alegeri(), input_ID)) {
                            cauta = true;
                            break;
                        }
                    }

                    if(!cauta) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    }

                    Alegeri verificare = new Alegeri();
                    if(verificare.verificare_out()) {
                        System.out.println("EROARE: Nu este perioada de votare");
                    }

                    for(int i = 0; i < circumscriptieList.size(); i++) {
                        if(Objects.equals(circumscriptieList.get(i).getIDcirc(), input_ID) &&
                                Objects.equals(circumscriptieList.get(i).getCircumscriptie(), input_circumscriptie)) {
                            circumscriptieList.remove(i);
                            System.out.println("S-a sters circumscriptia " + input_circumscriptie);
                            break;
                        }
                    }
                    ////////////////comanda 4
                } else if(Objects.equals(comanda.getCommand(), "4")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 4);
                    String input_ID = parts[0];
                    String input_CNP = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String name = parts[3];
                    Candidat candidat = new Candidat();
                    boolean verificator = true;

                    if(!candidat.validAge(age)) {
                        System.out.println("EROARE: Varsta invalida");
                        break;
                    }

                    if(!candidat.validCnp(input_CNP)) {
                        System.out.println("EROARE: CNP invalid");
                        break;
                    }

                    for(int i = 0; i < info_candidati.size(); i++) {
                        if(Objects.equals(info_candidati.get(i).getCnp(), input_CNP)) {
                            System.out.println("EROARE: Candidatul "+ info_candidati.get(i).getCandidat() +" are deja acelasi CNP");
                            verificator = false;
                            break;
                        }
                    }

                    if(verificator) {
                        System.out.println("S-a adaugat candidatul " + name);
                        candidat.setCandidat(input_ID, input_CNP, age, name, 0);
                        info_candidati.add(candidat);
                    }

                    for(Alegeri x : alegeriList) {
                        if(!Objects.equals(x.getId_alegeri(), input_ID)) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                            break;
                        }
                    }

                    if(k == 0) {
                        System.out.println("EROARE: Nu este perioada de votare");
                        break;
                    }
                    ///////////////comanda 5
                } else if(Objects.equals(comanda.getCommand(), "5")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    String input_ID = parts[0];
                    String input_CNP = parts[1];

                    for(int i = 0; i < info_candidati.size(); i++) {
                        if(Objects.equals(info_candidati.get(i).getCnp(), input_CNP)) {
                            System.out.println("S-a sters candidatul " + info_candidati.get(i).getCandidat());
                            info_candidati.remove(i);
                        }
                    }

                    if(info_candidati.isEmpty()) {
                        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + input_CNP);
                    }

                    for(int i = 0; i < info_candidati.size(); i++) {
                        if(Objects.equals(info_candidati.get(i).getCnp(), input_CNP)) {
                            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + input_CNP);
                        }
                    }

                    for(Alegeri x : alegeriList) {
                        if(!Objects.equals(x.getId_alegeri(), input_ID)) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        }
                    }

                    Alegeri verificare = new Alegeri();
                    if(verificare.verificare_out()) {
                        System.out.println("EROARE: Nu este perioada de votare");
                    }
                    //////////////////////comanda 6
                } else if(Objects.equals(comanda.getCommand(), "6")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 6);
                    String input_ID = parts[0];
                    String input_circ = parts[1];
                    String input_cnp = parts[2];
                    int age = Integer.parseInt(parts[3]);
                    String neindemanatic = parts[4];
                    String name = parts[5];
                    boolean verificator = true;

                    Persoana persoana = new Persoana();
                    if(!persoana.validAge(age)) {
                        System.out.println("EROARE: Varsta invalida");
                        verificator = false;
                    }

                    if(!persoana.validCnp(input_cnp)) {
                        System.out.println("EROARE: CNP invalid");
                        verificator = false;
                    }

                    if(!existaAlegeriCuId(input_ID)) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                        verificator = false;
                    }

                    for(int i = 0; i < info_pers.size(); i++) {
                        if(Objects.equals(info_pers.get(i).getCnp(), input_cnp)) {
                            System.out.println("EROARE: Votantul "+ name +" are deja acelasi CNP");
                            verificator = false;
                            break;
                        }
                    }

                    if(verificator) {
                        Persoana ps_pers = new Persoana();
                        ps_pers.setPersoana(name, input_cnp, age, input_circ, neindemanatic, "", "");
                        info_pers.add(ps_pers);
                        System.out.println("S-a adaugat votantul " + name);
                    }

                    boolean cauta = false;
                    for(Circumscriptie c : circumscriptieList) {
                        if(Objects.equals(c.getCircumscriptie(), input_circ)) {
                            cauta = true;
                            break;
                        }
                    }
                    if(!cauta) {
                        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + input_circ);
                    }
                    //////////////////comanda 7
                } else if(Objects.equals(comanda.getCommand(), "7")) {
                        if(info_candidati.isEmpty()) {
                            System.out.println("GOL: Nu sunt candidati");
                        }

                        boolean verificator = false;
                        String input = scanner.nextLine();

                        for(int i = 0; i < circumscriptieList.size(); i++) {
                            if (Objects.equals(circumscriptieList.get(i).getIDcirc(), input)) {
                                verificator = true;
                                break;
                            }
                        }

                        if(!verificator) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        }

                        if(verificator) {
                            System.out.println("Candidatii:");
                            for(Candidat c : info_candidati) {
                                System.out.println(c.getCandidat_info());
                            }
                        }
                        ////////////////comanda 8
                } else if(Objects.equals(comanda.getCommand(), "8")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    String input_ID = parts[0];
                    String input_circ = parts[1];

            if(info_pers.isEmpty()) {
                System.out.println("GOL: Nu sunt votanti in Bucuresti");
            }
            boolean cauta = false;

            for(Circumscriptie c : circumscriptieList) {
                if(Objects.equals(c.getCircumscriptie(), input_circ)) {
                    cauta = true;
                    break;
                }
            }

            if(!cauta) {
                System.out.println("EROARE: Nu exista o circumscriptie cu numele " + input_circ);
            }

            if(!existaAlegeriCuId(input_ID)) {
                System.out.println("EROARE: Nu exista alegeri cu acest id");
            }
            if(cauta) {
                System.out.println("Votantii din " + input_circ + ":");
                for(int i = 0; i < info_pers.size(); i++) {
                    if(Objects.equals(info_pers.get(i).getCircusc(), input_circ)) {
                        System.out.println(info_pers.get(i).getName() + " " + info_pers.get(i).getCnp() + " " + info_pers.get(i).getAge());
                    }
                }
            }
            ////////////////comanda 9
                } else if(Objects.equals(comanda.getCommand(), "9")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 4);
                    String input_ID = parts[0];
                    String input_circ = parts[1];
                    String cnp_votant = parts[2];
                    String cnp_candidat = parts[3];
                    boolean cauta = false;

                    for (int i = 0; i < info_candidati.size(); i++) {
                        if (Objects.equals(info_candidati.get(i).getCnp(), cnp_candidat)) {
                            cauta = true;
                            break;
                        }
                    }

                    if (!cauta) {
                        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp_candidat);
                        break;
                    }

                    if (!existaAlegeriCuId(input_ID)) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    }
                    if(cauta) {
                        for (int i = 0; i < info_pers.size(); i++) {
                            if (Objects.equals(info_pers.get(i).getVotat(), "")) {
                                info_pers.get(i).setVotat("nevotat");
                                info_pers.get(i).setFrauda("fara_frauda");
                            }
                        }

                        for (int i = 0; i < info_pers.size(); i++) {
                            if (Objects.equals(info_pers.get(i).getVotat(), "nevotat") &&
                                Objects.equals(info_pers.get(i).getFrauda(), "fara_frauda") &&
                                Objects.equals(info_pers.get(i).getCircusc(), input_circ) &&
                                Objects.equals(info_pers.get(i).getCnp(), cnp_votant)) {

                            for (int j = 0; j < info_candidati.size(); j++) {
                                if (Objects.equals(info_candidati.get(j).getCnp(), cnp_candidat)) {
                                    info_candidati.get(j).setNr_voturi();
                                    System.out.println(info_pers.get(i).getName() + " a votat pentru " + info_candidati.get(j).getCandidat());

                                    for (int k = 0; k < circumscriptieList.size(); k++) {
                                        if (Objects.equals(circumscriptieList.get(k).getCircumscriptie(), input_circ)) {
                                            circumscriptieList.get(k).setNr_voturi();
                                            Vot vot = new Vot();
                                            vot.setVoturi(circumscriptieList.get(k).getCircumscriptie(), info_candidati.get(j).getCandidat(), info_candidati.get(j).getCnp(), 0, circumscriptieList.get(k).getRegiune());
                                            voturi.add(vot);
                                        }
                                    }

                                    if (voturi.size() > info_candidati.size() * circumscriptieList.size()) {
                                        voturi.remove(voturi.size() - 1);
                                    }

                                    for (int k = 0; k < voturi.size(); k++) {
                                        if (Objects.equals(voturi.get(k).getCircumscriptie(), input_circ) &&
                                                Objects.equals(voturi.get(k).getCnp(), cnp_candidat)) {
                                            voturi.get(k).setNr_voturi();
                                        }
                                    }
                                }
                            }
                            info_pers.get(i).setVotat("votat");
                        } else if (Objects.equals(info_pers.get(i).getCnp(), cnp_votant) &&
                                Objects.equals(info_pers.get(i).getVotat(), "votat")) {
                            info_pers.get(i).setFrauda("frauda");
                        }
                    }
                }

                    for(int i = 0; i < info_pers.size(); i++) {
                        if(Objects.equals(info_pers.get(i).getFrauda(), "frauda")) {
                            System.out.println("FRAUDA: Votantul cu CNP-ul "+ cnp_votant +" a incercat sa comita o frauda. Votul a fost anulat");
                            break;
                        }
                    }

                    for(int i = 0; i < info_pers.size(); i++) {
                        if(Objects.equals(info_pers.get(i).getCnp(), cnp_votant) &&
                                Objects.equals(info_pers.get(i).getVotat(), "nevotat")) {
                            if(Objects.equals(info_pers.get(i).getCircusc(), input_circ)) {
                                break;
                            } else {
                                System.out.println("FRAUDA: Votantul cu CNP-ul "+ cnp_votant +" a incercat sa comita o frauda. Votul a fost anulat");
                            }
                        }
                    }

                boolean verificator = false;
                for(int k = 0; k < circumscriptieList.size(); k++) {
                    if(Objects.equals(circumscriptieList.get(k).getCircumscriptie(), input_circ)) {
                        verificator = true;
                        break;
                    }
                }

                if(!verificator) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + input_circ);
                }

                /////////comanda 10
                } else if(Objects.equals(comanda.getCommand(), "10")) {
                    String input = scanner.nextLine();

                    for(int i = 0; i < alegeriList.size(); i++) {
                        if(Objects.equals(alegeriList.get(i).getId_alegeri(), input)) {
                            System.out.println("S-au terminat alegerile " + alegeriList.get(i).getNume_alegeri());
                            break;
                        } else {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        }
                    }
                    ////////////////comanda 11
                } else if(Objects.equals(comanda.getCommand(), "11")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    String input_ID = parts[0];
                    String input_circ = parts[1];

                    Collections.sort(voturi);

                    System.out.println("Raport voturi Bucuresti:");
                    for(int i = 0; i < voturi.size(); i++) {
                        if(Objects.equals(voturi.get(i).getCircumscriptie(), input_circ) && existaAlegeriCuId(input_ID)) {
                            System.out.println( voturi.get(i).getNume() + " " + voturi.get(i).getCnp() + " - " + voturi.get(i).getNr_voturi());
                        } else if(!existaAlegeriCuId(input_ID)) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        } else {
                            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + input_circ);
                        }
                    }
                    if(voturi.isEmpty()) {
                        System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + input_circ);
                    }/////////////comanda 12
                } else if(Objects.equals(comanda.getCommand(), "12")) {
                    boolean nu_eroare = false;

                    if(voturi.isEmpty()) {
                        System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                        nu_eroare = true;
                    }
                    String input = scanner.nextLine();
                    if(!existaAlegeriCuId(input)) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                        nu_eroare = true;
                    }
                    if(!nu_eroare) {
                        Collections.sort(info_candidati);
                        System.out.println("Raport voturi Romania:");
                        for(int i = 0; i < info_candidati.size(); i++) {
                            System.out.println(info_candidati.get(i).getCandidat() + " " + info_candidati.get(i).getCnp() + " - " + info_candidati.get(i).getNr_voturi());
                        }
                    }/////////////comanda 13
                } else if(Objects.equals(comanda.getCommand(), "13")) {
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    String input_ID = parts[0];
                    String input_circ = parts[1];

                    if(voturi.isEmpty()) {
                        System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + input_circ);
                    }
                    if(!existaAlegeriCuId(input_ID)) {
                        System.out.println("ERRORE: Nu exista alegeri cu acest id");
                    }

                    int vot_total = 0;
                    for(int i = 0; i < info_candidati.size(); i++) {
                        vot_total += info_candidati.get(i).getNr_voturi();
                    }

                    int vot_total_circ = 0;
                    for(int i = 0; i < circumscriptieList.size(); i++) {
                        if(Objects.equals(circumscriptieList.get(i).getCircumscriptie(), input_circ)) {
                            vot_total_circ += circumscriptieList.get(i).getNr_voturi();
                        }
                    }

                    for(int i = 0; i < voturi.size(); i++) {
                        if(Objects.equals(voturi.get(i).getCircumscriptie(), input_circ)) {
                            System.out.println("In " + input_circ + " au fost " +
                                    voturi.get(i).getNr_voturi() + " voturi din " + vot_total +
                                    ". Adica " + voturi.get(i).getNr_voturi() * 100 / vot_total +
                                    "%. Cele mai multe voturi au fost stranse de " +
                                    voturi.get(i).getCnp() + " " + voturi.get(i).getNume() +
                                    ". Acestea constituie " + vot_total_circ * 100 / circumscriptieList.get(i).getNr_voturi() + "%" + " din voturile circumscriptiei.");
                            break;
                        }
                    }//////////////////comanda 14
                } else if(Objects.equals(comanda.getCommand(), "14")) {
                    if(voturi.isEmpty()) {
                        System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                        break;
                    }
                    String input = scanner.nextLine();
                    if(!existaAlegeriCuId(input)) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                        break;
                    }

                    int vot_total = 0;
                    for(int i = 0; i < info_candidati.size(); i++) {
                        vot_total += info_candidati.get(i).getNr_voturi();
                    }

                   for(int i = 0; i < voturi.size() - 1; i++) {
                       for(int j = i + 1; j < voturi.size(); j++) {
                           if(Objects.equals(voturi.get(i).getCircumscriptie(), voturi.get(j).getCircumscriptie()) &&
                           Objects.equals(voturi.get(i).getNume(), voturi.get(j).getNume()) &&
                           Objects.equals(voturi.get(i).getCnp(), voturi.get(j).getCnp())) {
                               voturi.remove(j);
                           }
                       }
                   }

                   ArrayList<Integer> valori = new ArrayList<>();
                   Collections.sort(info_candidati);
                   Map<String, Integer> voturiPeRegiune = new HashMap<>();

                   for(int i = 0; i < circumscriptieList.size(); i++) {
                       String[] parts = circumscriptieList.get(i).getCircus_info().split(" ");
                       String regiune = parts[2];
                       int voturi = Integer.parseInt(parts[3]);
                       voturiPeRegiune.put(regiune, voturiPeRegiune.getOrDefault(regiune, 0) + voturi);
                   }

                   System.out.println("In Romania au fost " + vot_total + " voturi.");
                   Collections.sort(voturi);
                    for (Map.Entry<String, Integer> entry : voturiPeRegiune.entrySet()) {
                       int voturi_regiune = 0;
                    for(int i = 0; i < voturi.size() - 1; i++) {
                        for(int j = i + 1; j < voturi.size(); j++) {
                            if(Objects.equals(voturi.get(i).getRegiune(), voturi.get(j).getRegiune()) &&
                            !Objects.equals(voturi.get(i).getCnp(), voturi.get(j).getCnp())) {
                                voturi_regiune += voturi.get(i).getNr_voturi();
                                break;
                                }
                            }
                        voturi.remove(i);
                        }
                        valori.add(voturi_regiune);
                   }

                    valori.sort(Collections.reverseOrder());
                    for (Map.Entry<String, Integer> entry : voturiPeRegiune.entrySet()) {
                        for(int i = info_candidati.size() - 1; i >= 0;) {
                            System.out.println("In " + entry.getKey() + " au fost " + entry.getValue() +
                                " voturi din " + vot_total + ". Adica " + entry.getValue() * 100 / vot_total + "%. Cele mai multe voturi au fost stranse de " +
                                    info_candidati.get(i).getCnp() + " " + info_candidati.get(i).getCandidat() + ". Acestea constituie " + valori.get(i) * 100 / info_candidati.get(i).getNr_voturi() +
                                "% din voturile regiunii.");
                            info_candidati.remove(i);
                            valori.remove(i);
                            break;
                            }
                        }
                    //////////////////////comanda 15
                } else if(Objects.equals(comanda.getCommand(), "15")) {
                    boolean cauta = true;
                    System.out.println("Fraude comise:");
                    for(int i = 0; i < info_pers.size(); i++) {
                        if(Objects.equals(info_pers.get(i).getFrauda(), "frauda")) {
                            System.out.println("In " + info_pers.get(i).getCircusc() + ": " +
                                    info_pers.get(i).getCnp() + " " + info_pers.get(i).getName());
                            cauta = false;
                        }
                    }
                    if(cauta) {
                        System.out.println("GOL: Romanii sunt cinstiti");
                    }
                    //////////////////comanda 16
                } else if(Objects.equals(comanda.getCommand(), "16")) {
                    String input = scanner.nextLine();

                    if(existaAlegeriCuId(input)) {
                        for(Alegeri stergere : alegeriList) {
                            System.out.println("S-au sters alegerile " + stergere.getNume_alegeri());
                            alegeriList.clear();
                            info_candidati.clear();
                            info_pers.clear();
                            voturi.clear();
                            circumscriptieList.clear();
                            break;
                        }
                    } else {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    }
                    ////////////////////comanda 17
                } else if(Objects.equals(comanda.getCommand(), "17")) {
                    if(alegeriList.isEmpty()) {
                        System.out.println("GOL: Nu sunt alegeri");
                    } else {
                        System.out.println("Alegeri:");
                        for(int i = 0; i < alegeriList.size(); i++) {
                            System.out.println(alegeriList.get(i).getAlegeri());
                        }
                    }
                    ////////////////comanda 18
                } else if(Objects.equals(comanda.getCommand(), "18")) {
                    break;
                }
            }
        scanner.close();
    }

    private boolean existaAlegeriCuId(String id_alegeri) {
        for (Alegeri alegeri : alegeriList) {
            if (alegeri.getId_alegeri().equals(id_alegeri)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}
