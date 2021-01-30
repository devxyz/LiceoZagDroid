package utility.test_db;

public class Test1 {
    static String db = "--\n" +
            "1 drop table if exists titolo_studio;" +
            "drop table if exists scuola;" +
            "drop table if exists conoscenza_lingua;" +
            "drop table if exists lingua;" +
            "drop table if exists esame;" +
            "drop table if exists corso;" +
            "drop table if exists docente;" +
            "drop table if exists studente;" +
            "drop table if exists corso_laurea;" +
            "drop table if exists dipartimento;\n" +
            "" +
            "16 -- Creazione tabella DIPARTIMENTO\n" +
            "17 --\n" +
            "18 create table dipartimento (\n" +
            "19 id int not null auto increment primary key,\n" +
            "20 nome char(30) not null\n" +
            "21 ) engine=innodb;\n" +
            "22\n" +
            "23 --\n" +
            "24 -- Creazione tabella CORSO LAUREA\n" +
            "25 --\n" +
            "26 create table corso laurea (\n" +
            "27 id int not null auto increment primary key,\n" +
            "28 nome char(30),\n" +
            "29 id dipartimento int,\n" +
            "30 area int,\n" +
            "31 foreign key (id dipartimento) references dipartimento(id)\n" +
            "32 ) engine=innodb;\n" +
            "33\n" +
            "34 --\n" +
            "35 -- Creazione tabella STUDENTE\n" +
            "36 --\n" +
            "37 create table studente (\n" +
            "38 matricola char(10) not null primary key,\n" +
            "39 nome char(20),\n" +
            "4\n" +
            "40 cognome char(30) not null,\n" +
            "41 email char(30),\n" +
            "42 data nascita datetime,\n" +
            "43 comune nascita char(30),\n" +
            "44 telefono char(15),\n" +
            "45 id laurea int,\n" +
            "46 foreign key (id laurea) references corso laurea(id)\n" +
            "47 ) engine=innodb;\n" +
            "48\n" +
            "49 --\n" +
            "50 -- Creazione tabella DOCENTE\n" +
            "51 --\n" +
            "52 create table docente (\n" +
            "53 id int not null auto increment primary key,\n" +
            "54 nome char(30),\n" +
            "55 cognome char(30) not null,\n" +
            "56 email char(40)\n" +
            "57 ) engine=innodb;\n" +
            "58\n" +
            "59 --\n" +
            "60 -- Creazione tabella CORSO\n" +
            "61 --\n" +
            "62 create table corso (\n" +
            "63 sigla char(10) not null primary key,\n" +
            "64 titolo char(50) not null,\n" +
            "65 id docente int,\n" +
            "66 ssd char(10),\n" +
            "67 cfu int,\n" +
            "68 foreign key (id docente) references docente(id)\n" +
            "69 ) engine=innodb;\n" +
            "70\n" +
            "71 --\n" +
            "72 -- Creazione tabella ESAME\n" +
            "73 --\n" +
            "74 create table esame (\n" +
            "75 matricola studente char(10) not null,\n" +
            "76 sigla corso char(10) not null,\n" +
            "77 anno int,\n" +
            "78 voto int,\n" +
            "79 primary key (matricola studente, sigla corso),\n" +
            "80 CONSTRAINT xx1 foreign key (matricola studente) references studente(matricola)\n" +
            "81 on update cascade on delete cascade,\n" +
            "82 CONSTRAINT xx2 foreign key (sigla corso) references corso(sigla) on update cascade on delete restrict\n" +
            "83 ) engine=innodb;\n" +
            "84\n" +
            "85 --\n" +
            "86 -- Creazione tabella LINGUA\n" +
            "5\n" +
            "87 --\n" +
            "88 create table lingua (\n" +
            "89 id int not null auto increment primary key,\n" +
            "90 nome char(30)\n" +
            "91 ) engine=innodb;\n" +
            "92\n" +
            "93 --\n" +
            "94 -- Creazione tabella CONOSCENZA LINGUA\n" +
            "95 --\n" +
            "96 create table conoscenza lingua (\n" +
            "97 matricola studente char(10) not null,\n" +
            "98 id lingua int,\n" +
            "99 scritto int,\n" +
            "100 orale int,\n" +
            "101 primary key (matricola studente, id lingua),\n" +
            "102 foreign key (matricola studente) references studente(matricola),\n" +
            "103 foreign key (id lingua) references lingua(id)\n" +
            "104 ) engine=innodb;\n" +
            "105\n" +
            "106 --\n" +
            "107 -- Creazione tabella SCUOLA\n" +
            "108 --\n" +
            "109 create table scuola (\n" +
            "110 id int not null auto increment primary key,\n" +
            "111 nome char(40) not null,\n" +
            "112 citta char(40) not null,\n" +
            "113 titolo char(40) not null\n" +
            "114 ) engine=innodb;\n" +
            "115\n" +
            "116 --\n" +
            "117 -- Creazione tabella TITOLO STUDIO\n" +
            "118 --\n" +
            "119 create table titolo studio (\n" +
            "120 matricola studente char(10) not null,\n" +
            "121 id scuola int,\n" +
            "122 voto int,\n" +
            "123 primary key (matricola studente, id scuola),\n" +
            "124 foreign key (matricola studente) references studente(matricola),\n" +
            "125 foreign key (id scuola) references scuola(id)\n" +
            "126 ) engine=innodb;\n" +
            "\n" +
            "\n" +
            "\n" +
            "12 insert into dipartimento (id, nome) values (1, 'Matematica e Fisica');\n" +
            "13 insert into dipartimento (id, nome) values (2, 'Scienze della formazione');\n" +
            "14 insert into dipartimento (id, nome) values (3, 'Ingegneria');\n" +
            "15 insert into dipartimento (id, nome) values (4, 'Lettere e Filosofia');\n" +
            "16 insert into dipartimento (id, nome) values (5, 'Economia e Commercio');\n" +
            "17 insert into dipartimento (id, nome) values (6, 'Architettura');\n" +
            "18\n" +
            "19 --\n" +
            "20 -- Inserimento dati Corso di laurea\n" +
            "21 --\n" +
            "22 insert into corso laurea (id, nome, id dipartimento, area) values (1, 'Matematica', 1, 1);\n" +
            "23 insert into corso laurea (id, nome, id dipartimento, area) values (2, 'Fisica', 1, 2);\n" +
            "24 insert into corso laurea (id, nome, id dipartimento, area) values (3, 'Scienze formazione', 2, 11);\n" +
            "25 insert into corso laurea (id, nome, id dipartimento, area) values (4, 'Ingegneria meccanica', 3, 9);\n" +
            "26 insert into corso laurea (id, nome, id dipartimento, area) values (5, 'Ingegneria informatica', 3, 9);\n" +
            "27 insert into corso laurea (id, nome, id dipartimento, area) values (6, 'Ingegneria gestionale', 3, 9);\n" +
            "28 insert into corso laurea (id, nome, id dipartimento, area) values (7, 'Letteratura italiana', 4, 10);\n" +
            "29 insert into corso laurea (id, nome, id dipartimento, area) values (8, 'Storia Vicino Oriente', 4, 10);\n" +
            "30 insert into corso laurea (id, nome, id dipartimento, area) values (9, 'Storia moderna', 4, 10);\n" +
            "31 insert into corso laurea (id, nome, id dipartimento, area) values (10, 'Economia', 5, 13);\n" +
            "32 insert into corso laurea (id, nome, id dipartimento, area) values (11, 'Architettura', 6, 8);\n" +
            "33 insert into corso laurea (id, nome, id dipartimento, area) values (12, 'Restauro', 6, 8);\n" +
            "34\n" +
            "35 --\n" +
            "36 -- Inserimento dati Scuola\n" +
            "37 --\n" +
            "38 insert into scuola (id, nome, citta, titolo) values\n" +
            "39 (1, 'Liceo Scientifico ”Peano”', 'Roma', 'Maturita'' scientifica');\n" +
            "40 insert into scuola (id, nome, citta, titolo) values\n" +
            "7\n" +
            "41 (2, 'Liceo Scientifico ”Morgagni”', 'Roma', 'Maturita'' scientifica');\n" +
            "42 insert into scuola (id, nome, citta, titolo) values\n" +
            "43 (3, 'Liceo Classico ”Virgilio”', 'Roma', 'Maturita'' classica');\n" +
            "44 insert into scuola (id, nome, citta, titolo) values (4, 'ITIS ”Armellini”', 'Roma', 'Perito industriale');\n" +
            "45 insert into scuola (id, nome, citta, titolo) values (5, 'ITIS ”Fermi”', 'Roma', 'Perito industriale');\n" +
            "46\n" +
            "47 --\n" +
            "48 -- Inserimento dati Docente\n" +
            "49 --\n" +
            "50 insert into docente (id, nome, cognome, email) values (1, 'Marco', 'Liverani', 'liverani@uniroma3.it');\n" +
            "51 insert into docente (id, nome, cognome, email) values (2, 'Marco', 'Pedicini', 'pedicini@uniroma3.it');\n" +
            "52 insert into docente (id, nome, cognome, email) values (3, 'Gianni', 'Mancini', 'gianni@uniroma3.it');\n" +
            "53 insert into docente (id, nome, cognome, email) values (4, 'Marco', 'Fontana', 'fontana@uniroma3.it');\n" +
            "54 insert into docente (id, nome, cognome, email) values (5, 'Roberto', 'Ferretti', 'ferretti@uniroma3.it');\n" +
            "55 insert into docente (id, nome, cognome, email) values (6, 'Michele', 'Abrusci', 'abrusci@uniroma3.it');\n" +
            "56\n" +
            "57 --\n" +
            "58 -- Inserimento dati Corso\n" +
            "59 --\n" +
            "60 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('IN110', 'Informatica 1', 1, 'INF/01', 10);\n" +
            "61 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('AL110', 'Algebra 1', 4, 'MAT/02', 10);\n" +
            "62 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('LM410', 'Logica 1', 6, 'MAT/01', 7);\n" +
            "63 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('IN440', 'Ottim. Combin.', 1, 'INF/01', 7);\n" +
            "64 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('IN530', 'Sist.Elab.Inform.', 1, 'INF/01', 4);\n" +
            "65 insert into corso (sigla, titolo, id docente, ssd, cfu) values ('AM210', 'Analisi Mat. 3', 3, 'MAT/05', 9);\n" +
            "66\n" +
            "67 --\n" +
            "68 -- Inserimento dati Lingue\n" +
            "69 --\n" +
            "70 insert into lingua (id, nome) values (1, 'Inglese');\n" +
            "71 insert into lingua (id, nome) values (2, 'Francese');\n" +
            "72 insert into lingua (id, nome) values (3, 'Spagnolo');\n" +
            "73 insert into lingua (id, nome) values (4, 'Tedesco');\n" +
            "74 insert into lingua (id, nome) values (5, 'Cinese');\n" +
            "75 insert into lingua (id, nome) values (6, 'Portoghese');\n" +
            "76\n" +
            "77 --\n" +
            "78 -- Inserimento dati studenti\n" +
            "79 --\n" +
            "80 insert into studente (matricola, nome, cognome, email, data nascita,\n" +
            "81 comune nascita, telefono, id laurea) values ('101010', 'Giovanni',\n" +
            "82 'Rossi', 'giovannired@gmail.com', '1993-04-21', 'Roma', '335-123456', 1);\n" +
            "83 insert into studente (matricola, nome, cognome, email, data nascita,\n" +
            "84 comune nascita, telefono, id laurea) values ('202020', 'Giulia', 'Verdi',\n" +
            "85 'giulia94@yahoo.com', '1994-11-05', 'Napoli', '06-907632', 1);\n" +
            "86 insert into studente (matricola, nome, cognome, email, data nascita,\n" +
            "87 comune nascita, telefono, id laurea) values ('303030', 'Chiara', 'Verdi',\n" +
            "8\n" +
            "88 'chiaretta@libero.it', '1992-01-15', 'Napoli', '333-344556', 1);\n" +
            "89 insert into studente (matricola, nome, cognome, email, data nascita,\n" +
            "90 comune nascita, telefono, id laurea) values ('404040', 'Nicola', 'Leone',\n" +
            "91 'n.leone@libero.it', '1995-03-28', 'Roma', '333-102938', 1);\n" +
            "92 insert into studente (matricola, nome, cognome, email, data nascita,\n" +
            "93 comune nascita, telefono, id laurea) values ('505050', 'Mario',\n" +
            "94 'Dell''Acqua', 'dellacqua@gmail.com', '1994-06-20', 'Roma', '335-918273', 1);\n" +
            "95\n" +
            "96 --\n" +
            "97 -- Inserimento dati Titolo di studio\n" +
            "98 --\n" +
            "99 insert into titolo studio (matricola studente, id scuola, voto) values ('101010', 1, 100);\n" +
            "100 insert into titolo studio (matricola studente, id scuola, voto) values ('202020', 2, 90);\n" +
            "101 insert into titolo studio (matricola studente, id scuola, voto) values ('303030', 1, 87);\n" +
            "102 insert into titolo studio (matricola studente, id scuola, voto) values ('404040', 4, 100);\n" +
            "103 insert into titolo studio (matricola studente, id scuola, voto) values ('505050', 5, 85);\n" +
            "104\n" +
            "105 --\n" +
            "106 -- Inserimento dati sulle Conoscenze linguistiche\n" +
            "107 --\n" +
            "108 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('101010', 1, 8, 7);\n" +
            "109 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('101010', 3, 7, 5);\n" +
            "110 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('202020', 1, 6, 6);\n" +
            "111 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('303030', 1, 5, 6);\n" +
            "112 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('303030', 2, 7, 7);\n" +
            "113 insert into conoscenza lingua (matricola studente, id lingua, scritto, orale) values ('404040', 3, 8, 8);\n" +
            "114\n" +
            "115 --\n" +
            "116 -- Inserimento dati sugli Esami\n" +
            "117 --\n" +
            "118 insert into esame (matricola studente, sigla corso, anno, voto) values ('101010', 'IN110', 2013, 30);\n" +
            "119 insert into esame (matricola studente, sigla corso, anno, voto) values ('101010', 'AL110', 2013, 28);\n" +
            "120 insert into esame (matricola studente, sigla corso, anno, voto) values ('101010', 'IN440', 2014, 28);\n" +
            "121 insert into esame (matricola studente, sigla corso, anno, voto) values ('202020', 'IN110', 2012, 22);\n" +
            "122 insert into esame (matricola studente, sigla corso, anno, voto) values ('202020', 'IN440', 2014, 26);\n" +
            "123 insert into esame (matricola studente, sigla corso, anno, voto) values ('202020', 'AM210', 2014, 30);\n" +
            "124 insert into esame (matricola studente, sigla corso, anno, voto) values ('202020', 'LM410', 2014, 27);\n" +
            "125 insert into esame (matricola studente, sigla corso, anno, voto) values ('303030', 'IN110', 2012, 24);\n" +
            "126 insert into esame (matricola studente, sigla corso, anno, voto) values ('404040', 'IN110', 2013, 29);";

    private static String gestisci_spazi(String s, String... sostituzioni) {
        for (String sost : sostituzioni) {
            s = s.replace(sost, sost.replace(" ", "_"));
        }
        return s;
    }

    public static void main(String[] args) {
        String[] split = db.split("\n");
        for (String s : split) {

            {
                int i = s.indexOf(" ");
                if (i > 0) {
                    String substring = s.substring(i).trim();
                    substring = gestisci_spazi(substring,
                            "sigla corso",
                            "matricola studente",
                            "conoscenza lingua",
                            "titolo studio",
                            "data nascita",
                            "comune nascita",
                            "sigla corso",
                            "corso laurea",
                            "id dipartimento",
                            "id docente",
                            "id laurea",
                            "id lingua",
                            "id scuola",
                            "auto increment"


                    );
                    if (substring.startsWith("-")) continue;
                    for (int j = 0; j < substring.length(); j++) {
                        char c = substring.charAt(j);
                        if (c >= 32)
                            System.out.print(c);
                        if (c == ';' && j != substring.length() - 1) {
                            System.out.println();
                        }
                    }
                }
                System.out.println();
            }

        }
    }
}
