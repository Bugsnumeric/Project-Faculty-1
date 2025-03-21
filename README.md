# Project-Faculty-1
Primul proiect 
Pentru rezolvarea temei am implementat urmatoarele clase separate:
    -Alegeri => se declara campuri private cu setter si getter pentru fiecare;
    -Candidat => in aceasta clasa se gasesc informatii pentru candidat, dar si nr. de voturi
pe care le-a strans. Pe langa informatii, este implementata o metoda ce compara cnp-ul candidatilor.
    -Circumscriptie => in clasa aceasta se retin mai multe date precum regiunea si nr. de voturi
pe acea circumscriptie.
    -Comanda => pentru a manevra comanda introdusa de user
    -Persoana => in aceasta clasa am adaugat datele caracteristice unei persoane,
anume a votantului; pe langa datele cerute in cerinta am mai adaugat informatii precum
"votat" si "frauda". Initial am declarat acest camp cu "", pe parcurs daca exista persoana
ii atribui "nevotat" si "fara_frauda", in cazul in care voteaza atunci i se schimba
statutul in "votat", daca incearca sa voteze de mai multe ori sau in alta circumscriptie decat
cea asignata atunci campul frauda trece in valoarea "frauda".
Pe langa acestea se regasesc si doua metode care verifica daca votantul este major si cu cnp valid.
    -Vot => in clasa aceasta se afla o metoda ce afla nr. total de voturi si o metoda ce compara
dupa nr. de voturi, in caz ca sunt egale se ia dupa cnp.

In clasa App am implementat o singura metoda pentru a verifica existenta unui ID;
Sunt definite cinci ArrayList pentru fiecare clasa pentru a retine datele despre fiecare si
a le modifica mai tarziu in comenzile date de user.

Comenzi:
    > 0: se creaza alegeri in cazul in care lista mea este goala sau ID introdus este diferit de cel din lista;
Implementarea consta in a compara prin Objects.equals(ceva) ID-ul din lista cu cel dat ca input;
id-ul dat ca input este luat folosind functia .split;
    > 1: aici se foloseste contorul si este incrementat atunci cand ID din lista este egal cu ID dat ca input;
in cazul in care k este mai mare ca 1 atunci se afiseaza eroare din cauza ca alegerile au inceput deja, cealalta eroare fiind data de inexistenta ID-ului;
    > 2: prin valoarea data de "cauta" aflam daca exista ID dat ca input, in cazul in care exista se adauga o circumscriptie
in lista;
Daca exista deja o circumscriptie in lista precum cea data ca input atunci se afiseaza eroare;
    > 3: conceptul este acelasi ca la comanda 2, se verifica daca exista o cricumscriptie cu acelasi nume si ID ca cel dat la input,
in cazul in care exista atunci se sterge din lista acea circumscriptie;
    > 4: comanda aceasta adauga un candidat, verifica daca are varsta si cnp valid, in cazul in care
verificatorul se respecta (nu intra pe o ramura de eroare) atunci se adauga candidatul;
    > 5: conceptul este acelasi ca la comenzile precedente, se sterge candidatul in cazul in care se gaseste
ID-ul respectiv prin lista.
    > 6: in aceasta comanda se adauga un votant si i se atribuie valorile mentionate mai sus cum ar fi "frauda" si "votat";
intai se verifica pentru erori, daca nu exista erori atunci se face atribuirea;
    > 7: comanda afiseaza toti candidatii care au fost adaugati si sunt disponibili;
daca verificatorul trece de erori atunci se executa comanda;
    > 8: acelasi concept ca la comanda 7, dar pentru votanti;
    > 9: in aceasta comanda se seteaza valorile pentru fiecare votant;
am ales sa le adaug aici intrucat pot fi persoane care sa nu isi exercite dreptul la vot,
deci era inutil sa le declar mai sus in comenzi;
aici folosesc 3 for-uri, dar asta deoarece adaug si nr. de voturi pe circumscriptie, dar si nr. de voturi ale candidatilor;
for(j) asigura incrementarea tuturor voturilor aferente candidatului j folosing metoda setNr_voturi() {info_candidati.get(j).setNr_voturi();};
ultimul for(k) asigneaza pe circumscriptie (k) si regiune (k) cate voturi are candidatul j;
ma asigur ca marimea listei de voturi este egala cu marimea listei circumscriptiei * lista candidatilor (exista posibilitatea sa fie cu 1 in plus datorita unei duplicari);
la final atribui "votat" votantului, iar daca incearca sa voteze din nou i se atribuite "frauda";
    > 10: anunta ca s-au terminat alegerile;
    > 11: comanda afiseaza cate voturi din circumscriptie are fiecare candidat, folosind ArrayList voturi mentionat anterior;
    > 12: se afiseaza pe nivel national cate voturi are fiecare candidat folosind ArrayList info_candidati;
    > 13: se afiseaza nr. total de voturi din circumscriptie si cine a strans cele mai multe; se face si procentajul
folosind formula vot_total_circ * 100 / circumscriptieList.get(i).getNr_voturi();
    > 14: aceasta comanda implementeaza in mare parte aceeasi logica ca si comanda 13, dar sortand si iterand invers;
deoarece aveam de calculat nr. total de voturi pe regiune, am folosit un Map pentru a avea regiuni unice si a aduna voturile
din regiunile duplicat;
Iterez prin acest Map, care arata cati candidati au fost si afisez datele cerute cu formula: entry.getValue() * 100 / vot_total;
    > 15: se itereaza prin lista si se verifica daca exista "frauda" in dreptul unui votant, daca nu exista atunci afiseaza GOL;
    > 16: comanda sterge toate datele din liste;
    > 17: comanda aceasta listeaza alegerile disponibile;
    > 18: iese din program.
