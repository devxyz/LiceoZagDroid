package it.gov.scuolesuperioridizagarolo.model.bitorario;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.griglia.GrigliaOrariaMultiValore;
import it.gov.scuolesuperioridizagarolo.model.bitorario.griglia.IndexedGrigliaOrariaMultiValore;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;

/**
 * Created by stefano on 16/09/2017.
 */
public class BitOrarioGrigliaOrario implements Cloneable, Externalizable {
    private final IndexedGrigliaOrariaMultiValore<String> _lezioniPerDocente = new IndexedGrigliaOrariaMultiValore<>();
    private final IndexedGrigliaOrariaMultiValore<RoomData> _lezioniPerAula = new IndexedGrigliaOrariaMultiValore<>();
    private final IndexedGrigliaOrariaMultiValore<ClassData> _lezioniPerClasse = new IndexedGrigliaOrariaMultiValore<>();
    private final GrigliaOrariaMultiValore _lezioni = new GrigliaOrariaMultiValore();
    private transient boolean readOnly = false;
    private String titolo;
    private transient TreeMap<GiornoOra, Set<String>> oreDisposizione = null;
    private transient TreeMap<GiornoOra, Set<InfoDocente_DisposizioneCompresenzaProgetto>> oreDisposizioneCompresenzeProgetti = null;

    public void cleanUpDocenti() {
        _lezioniPerDocente.cleanupUnusedKeys();
    }

    public boolean isFullDDI(String docente, EGiorno g, boolean ignoreDisposizioni) {
        ArrayList<BitOrarioOraLezione> lezioniGiornaliere = getLezioneConDocente(docente, g);
        for (BitOrarioOraLezione l : lezioniGiornaliere) {
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                if (!ignoreDisposizioni) return false;
                else continue;
            }
            if (!l.aula.isDDI()) return false;
        }
        return true;
    }

    //ora inizio didattica a scuola, ora fine didattica a scuola
    public EOra[] intervalloDidatticaScuola__conTrasferimento(String docente, EGiorno g) {
        EOra ultimaOraBuco = EOra.USCITA;
        {
            ArrayList<EOra> oraDallUltimaAllaPrima = new ArrayList<>(Arrays.asList(EOra.valuesOreDiLezione()));
            Collections.sort(oraDallUltimaAllaPrima, new Comparator<EOra>() {
                @Override
                public int compare(EOra o1, EOra o2) {
                    return -o1.compareTo(o2);
                }
            });


            for (EOra eOra : oraDallUltimaAllaPrima) {
                BitOrarioOraLezione l = getLezioneConDocente(docente, g, eOra);
                if (l == null) {
                    ultimaOraBuco = eOra;
                    continue;
                }
                if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                    break;
                }
                if (!l.getAula().isDDI()) {
                    break;
                }
                if (eOra == EOra.PRIMA) {
                    ultimaOraBuco = EOra.PRIMA;
                    break;
                }
            }
        }

        EOra primaOraBuco = EOra.ENTRATA;
        {
            ArrayList<EOra> oraDallaPrimaAllUltima = new ArrayList<>(Arrays.asList(EOra.valuesOreDiLezione()));

            for (EOra eOra : oraDallaPrimaAllUltima) {
                BitOrarioOraLezione l = getLezioneConDocente(docente, g, eOra);
                if (l == null) {
                    primaOraBuco = eOra;
                    continue;
                }
                if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                    break;
                }
                if (!l.getAula().isDDI()) {
                    break;
                }
                if (eOra == EOra.SESTA) {
                    primaOraBuco = EOra.SESTA;
                    break;
                }
                //se raggiunge l'altro lato...
                if (eOra == ultimaOraBuco) {
                    break;
                }
            }

        }

        return new EOra[]{primaOraBuco, ultimaOraBuco};
    }

    public static class InsegnamentiDocente {
        public InsegnamentiDocente(String docente) {
            this.docente = docente;
        }

        public final String docente;
        public final TreeSet<String> materie = new TreeSet<>();
    }

    public TreeMap<String, InsegnamentiDocente> getConsiglioDiClasse(ClassData classe) {
        TreeMap<String, InsegnamentiDocente> ris = new TreeMap<>();
        List<BitOrarioOraLezione> lezioni = getLezioni(classe);
        for (BitOrarioOraLezione l : lezioni) {
            String docente;
            String materia;
            materia = l.getMateriaPrincipale();
            docente = l.getDocentePrincipale();
            if (docente != null) {
                if (ris.get(docente) == null)
                    ris.put(docente, new InsegnamentiDocente(docente));
                ris.get(docente).materie.add(materia);
            }

            materia = l.getMateriaCompresenza();
            docente = l.getDocenteCompresenza();
            if (docente != null) {
                if (ris.get(docente) == null)
                    ris.put(docente, new InsegnamentiDocente(docente));
                ris.get(docente).materie.add(materia + "(compresenza)");
            }

            docente = l.getDocenteSostegno();
            if (docente != null) {
                if (ris.get(docente) == null)
                    ris.put(docente, new InsegnamentiDocente(docente));
                ris.get(docente).materie.add("sostegno");
            }
        }


        return ris;
    }

    public static class InfoDocente_DisposizioneCompresenzaProgetto implements Comparable<InfoDocente_DisposizioneCompresenzaProgetto> {
        public static InfoDocente_DisposizioneCompresenzaProgetto disposizione(String docente) {
            return new InfoDocente_DisposizioneCompresenzaProgetto(docente, "Disposizione", InfoDocente_DisposizioneCompresenzaProgetto.DISPOSIZIONE);
        }

        public static InfoDocente_DisposizioneCompresenzaProgetto progetto(String docente, String descrizione) {
            return new InfoDocente_DisposizioneCompresenzaProgetto(docente, "Progetto: " + descrizione, InfoDocente_DisposizioneCompresenzaProgetto.PROGETTO);
        }

        public static InfoDocente_DisposizioneCompresenzaProgetto compresenza(String docente, String descrizione) {
            return new InfoDocente_DisposizioneCompresenzaProgetto(docente, "Compresenza: " + descrizione, InfoDocente_DisposizioneCompresenzaProgetto.COMPRESENZA);
        }

        public static InfoDocente_DisposizioneCompresenzaProgetto sostegno(String docente, String descrizione) {
            return new InfoDocente_DisposizioneCompresenzaProgetto(docente, "Sostegno: " + descrizione, InfoDocente_DisposizioneCompresenzaProgetto.SOSTEGNO);
        }

        public static final int DISPOSIZIONE = 1;
        public static final int PROGETTO = 2;
        public static final int COMPRESENZA = 3;
        public static final int SOSTEGNO = 4;
        public final String docente;
        public final String descrizione;
        public final int tipo;


        private InfoDocente_DisposizioneCompresenzaProgetto(String docente, String descrizione, int tipo) {
            this.docente = docente;
            this.descrizione = descrizione;
            this.tipo = tipo;
        }

        public boolean isDisposizione() {
            return tipo == DISPOSIZIONE;
        }

        public boolean isCompresenza() {
            return tipo == COMPRESENZA;
        }

        public boolean isProgetto() {
            return tipo == PROGETTO;
        }

        public boolean isSostegno() {
            return tipo == SOSTEGNO;
        }

        @Override
        public int compareTo(InfoDocente_DisposizioneCompresenzaProgetto o) {
            int i1;
            i1 = Integer.compare(this.tipo, o.tipo);
            if (i1 != 0) return i1;
            i1 = this.docente.compareTo(o.docente);
            if (i1 != 0) return i1;
            i1 = this.descrizione.compareTo(o.descrizione);
            return i1;
        }
    }

    public BitOrarioOraLezione testLezione() {
        return getLezioneInClasse(EOra.SESTA, EGiorno.VENERDI, ClassData.CLASS_1D);
    }

    public TreeSet<String> getDocenti(ClassData classe) {
        TreeSet<String> r = new TreeSet<>();
        ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione l : lezioni) {
            if (l.getClasse() != null && l.getClasse() == classe) {
                if (l.getDocentePrincipale() != null)
                    r.add(l.getDocentePrincipale());
                if (l.getDocenteCompresenza() != null)
                    r.add(l.getDocenteCompresenza());
            }
        }
        return r;
    }

    public BitOrarioGrigliaOrario(String titolo) {
        this.titolo = titolo;
    }

    public void setReadOnly(boolean f) {
        this.readOnly = f;
    }

    private void checkReadOnly() {
        if (readOnly)
            throw new IllegalArgumentException("Read only mode");
        oreDisposizione = null;
    }

    public List<BitOrarioOraLezione> getLezioni(ClassData c) {
        final GrigliaOrariaMultiValore r = _lezioniPerClasse.get(c);
        return new ArrayList<>(r.get());
    }

    public Set<String> getDocentiPerClasse(ClassData c) {
        final GrigliaOrariaMultiValore r = _lezioniPerClasse.get(c);
        Set<String> ris = new TreeSet<>();
        for (BitOrarioOraLezione l : r.get()) {
            if (l.getDocentePrincipale() != null) {
                ris.add(l.getDocentePrincipale());
            }
            if (l.getDocenteCompresenza() != null) {
                ris.add(l.getDocenteCompresenza());
            }
        }
        return ris;
    }

    /**
     * per ogni ora sono presenti i docenti a disposizione
     *
     * @return
     */
    public TreeMap<GiornoOra, Set<String>> getDocentiDisposizione() {
        if (oreDisposizione != null) return oreDisposizione;
        oreDisposizione = new TreeMap<>();
        final ArrayList<BitOrarioOraLezione> ll = getLezioni();
        for (BitOrarioOraLezione l : ll) {
            final BitOrarioOraEnumTipoLezione tipoLezione = l.getTipoLezione();
            if (l.isDisposizionePura()) {
                GiornoOra g = new GiornoOra(l.getGiorno(), l.getOra());
                Set<String> docenti = oreDisposizione.get(g);
                if (docenti == null) {
                    docenti = new TreeSet<>();
                    oreDisposizione.put(g, docenti);
                }
                if (l.getDocentePrincipale() != null)
                    docenti.add(l.getDocentePrincipale());

                if (l.getDocenteCompresenza() != null)
                    docenti.add(l.getDocenteCompresenza());
            }
        }
        return oreDisposizione;
    }

    /**
     * docenti impegnati in disposizioni, progetti, compresenze
     *
     * @return
     */
    public TreeMap<GiornoOra, Set<InfoDocente_DisposizioneCompresenzaProgetto>> getDocentiDisposizioneProgettiCompresenze() {
        if (oreDisposizioneCompresenzeProgetti != null) return oreDisposizioneCompresenzeProgetti;
        oreDisposizioneCompresenzeProgetti = new TreeMap<>();
        final ArrayList<BitOrarioOraLezione> ll = getLezioni();
        for (BitOrarioOraLezione l : ll) {

            GiornoOra g = new GiornoOra(l.getGiorno(), l.getOra());
            final BitOrarioOraEnumTipoLezione tipoLezione = l.getTipoLezione();
            Set<InfoDocente_DisposizioneCompresenzaProgetto> docenti = oreDisposizioneCompresenzeProgetti.get(g);
            if (docenti == null) {
                docenti = new TreeSet<>();
                oreDisposizioneCompresenzeProgetti.put(g, docenti);
            }

            if (l.isDisposizionePura()) {
                if (l.getDocentePrincipale() != null)
                    docenti.add(InfoDocente_DisposizioneCompresenzaProgetto.disposizione(l.getDocentePrincipale()));
            } else {
                if (l.isDisposizioneProgetto()) {
                    if (l.getDocentePrincipale() != null)
                        docenti.add(InfoDocente_DisposizioneCompresenzaProgetto.progetto(l.getDocentePrincipale(), l.getMateriaPrincipale()));
                } else {
                    //compresenza o sostegno
                    if (l.getDocenteCompresenza() != null)
                        docenti.add(InfoDocente_DisposizioneCompresenzaProgetto.compresenza(l.getDocenteCompresenza(), l.toStringClasseAulaDocente()));
                    if (l.getDocenteSostegno() != null)
                        docenti.add(InfoDocente_DisposizioneCompresenzaProgetto.compresenza(l.getDocenteSostegno(), l.toStringClasseAulaDocente()));
                }
            }
        }
        return oreDisposizioneCompresenzeProgetti;
    }


    /**
     * elenco materie
     *
     * @param classe
     * @return
     */
    public Set<String> getMaterie(ClassData classe) {
        Set<String> ris = new TreeSet<>();
        for (BitOrarioOraLezione l : _lezioni.get()) {
            if (l.getClasse() != null && l.getClasse().equals(classe))
                if (l.getMateriaPrincipale() != null) {
                    ris.add(l.getMateriaPrincipale().trim().toUpperCase());
                }
        }
        return ris;

    }

    public Set<String> getMaterieDocente(String docente) {
        Set<String> ris = new TreeSet<>();
        for (BitOrarioOraLezione l : _lezioni.get()) {
            if (l.getDocentePrincipale() != null && l.getDocentePrincipale().equals(docente))
                if (l.getMateriaPrincipale() != null) {
                    ris.add(l.getMateriaPrincipale().trim().toUpperCase());
                }
            if (l.getDocenteCompresenza() != null && l.getDocenteCompresenza().equals(docente))
                if (l.getMateriaCompresenza() != null) {
                    ris.add(l.getMateriaCompresenza().trim().toUpperCase());
                }
        }
        return ris;

    }

    public Set<String> getMaterieDocenteClasse(String docente, ClassData classe) {
        Set<String> ris = new TreeSet<>();
        for (BitOrarioOraLezione l : getLezioni(classe)) {

            if (l.getDocentePrincipale() != null && l.getDocentePrincipale().equals(docente))
                if (l.getMateriaPrincipale() != null) {
                    ris.add(l.getMateriaPrincipale().trim().toUpperCase());
                }
            if (l.getDocenteCompresenza() != null && l.getDocenteCompresenza().equals(docente))
                if (l.getMateriaCompresenza() != null) {
                    ris.add(l.getMateriaCompresenza().trim().toUpperCase());
                }
        }
        return ris;

    }

    public Set<String> getMaterie() {
        Set<String> ris = new TreeSet<>();
        for (BitOrarioOraLezione l : _lezioni.get()) {
            if (l.getMateriaPrincipale() != null) {
                ris.add(l.getMateriaPrincipale().trim().toUpperCase());
            }
        }
        return ris;
    }

    public BitOrarioGrigliaOrario clone() {
        try {
            return (BitOrarioGrigliaOrario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * specifica usita didattica per le lezioni per la classe specificata
     *
     * @param nomeClasse
     * @param giorno
     * @param ore
     */

    public void classeInVisitaDidattica(String note, ClassData nomeClasse, EGiorno giorno, EOra... ore) {
        checkReadOnly();
        if (!getClassi().contains(nomeClasse))
            throw new IllegalArgumentException("Classe " + nomeClasse + " inesistente");


        for (EOra eOra : ore) {
            //if (!eOra.flagOraDiLezione()) continue;
            final BitOrarioOraLezione lezioneInClasse = getLezioneInClasse(eOra, giorno, nomeClasse);
            if (lezioneInClasse == null) continue;

            removeLezione(lezioneInClasse);
            final BitOrarioOraLezione nuovaLezioneInClasse = lezioneInClasse.clonaLezioneInAltraAula(RoomData.USCITA_DIDATTICA);
            nuovaLezioneInClasse.setNote(note);
            addLezione(nuovaLezioneInClasse);
        }
    }

    /**
     * rimuove le lezioni per la classe specificata
     *
     * @param nomeClasse
     * @param giorno
     * @param ore
     */

    public void classeInSospensioneDidattica(ClassData nomeClasse, EGiorno giorno, EOra... ore) {
        checkReadOnly();
        if (!getClassi().contains(nomeClasse))
            throw new IllegalArgumentException("Classe " + nomeClasse + " inesistente");

        for (EOra eOra : ore) {
            //if (!eOra.flagOraDiLezione()) continue;
            final BitOrarioOraLezione lezioneInClasse = getLezioneInClasse(eOra, giorno, nomeClasse);
            if (lezioneInClasse == null) continue;

            removeLezione(lezioneInClasse);

            //aggiunge disposizione per il docente
            if (lezioneInClasse.getDocentePrincipale() != null)
                addLezione(BitOrarioOraLezione.creaOraDisposizione(lezioneInClasse.getDocentePrincipale(), eOra, giorno));
            if (lezioneInClasse.getDocenteCompresenza() != null)
                addLezione(BitOrarioOraLezione.creaOraDisposizione(lezioneInClasse.getDocenteCompresenza(), eOra, giorno));
        }
    }

    public void removeLezione(BitOrarioOraLezione l) {
        checkReadOnly();

        if (l == null) return;
        if (l._parent != this) {
            throw new IllegalArgumentException("Lezione non associata a questo orario");
        }
        l._parent = null;


        _lezioni.remove(l);

        if (l.getAula() != null)
            _lezioniPerAula.remove(l.getAula(), l);

        if (l.getClasse() != null)
            _lezioniPerClasse.remove(l.getClasse(), l);

        if (l.getDocentePrincipale() != null)
            _lezioniPerDocente.remove(l.getDocentePrincipale(), l);

        if (l.getDocenteCompresenza() != null)
            _lezioniPerDocente.remove(l.getDocenteCompresenza(), l);

        if (l.getDocenteSostegno() != null)
            _lezioniPerDocente.remove(l.getDocenteSostegno(), l);

    }

    //rimuove docenti non utilizzati
    public void trim() {
        _lezioniPerDocente.trim();
        _lezioniPerAula.trim();
        _lezioniPerClasse.trim();
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * elenco delle classi che cambiano aula alla fine dell'ora/giorno specificato
     *
     * @param ora
     * @param settimana
     * @return
     */
    public TreeSet<ClassData> cambiDiAula(EOra ora, EGiorno settimana) {
        TreeSet<ClassData> ris = new TreeSet<>();
        for (ClassData c : getClassi()) {
            if (cambiaAula(c, ora, settimana))
                ris.add(c);
        }
        return ris;
    }

    public boolean oraLezioneChiusuraPC(RoomData aula, EGiorno settimana, EOra o) {


        final List<BitOrarioOraLezione> lezioneInAula = getLezioneInAula(o, settimana, aula);
        if (lezioneInAula.size() == 0) return false;
        if (!o.flagOraDiLezione())
            return true;
        EOra controlla = o.next();

        while (controlla != null) {
            if (!controlla.flagOraDiLezione())
                return true;
            if (getLezioneInAula(controlla, settimana, aula).size() > 0)
                return false;
            controlla = controlla.next();
        }


        return true;

    }

    public boolean oraLezioneConsegnaRegistro(ClassData classe, EGiorno settimana, EOra o) {


        final BitOrarioOraLezione lezioneInAula = getLezioneInClasse(o, settimana, classe);
        if (lezioneInAula == null) return false;
        return !o.flagOraDiLezione() || getLezioneInClasse(o.next(), settimana, classe) == null;

    }

    public boolean cambiaAula(ClassData classe, EOra ora, EGiorno settimana) {
        //prende l'ora corrente
        final BitOrarioOraLezione l1 = getLezioneInClasse(ora, settimana, classe);
        if (l1 == null) return false;

        if (ora.next() == null)
            return true;//la classe esce

        final BitOrarioOraLezione l2 = getLezioneInClasse(ora.next(), settimana, classe);
        if (l2 == null) return true;
        if (l1.getAula() == null) return false;
        return !l1.getAula().equals(l2.getAula());

    }

    public String dettaglioCambiaAula(ClassData classe, EOra ora, EGiorno settimana) {
        //prende l'ora corrente
        final BitOrarioOraLezione l1 = getLezioneInClasse(ora, settimana, classe);
        if (l1 == null) return null;

        if (ora.next() == null)
            return "uscita";//la classe esce

        final BitOrarioOraLezione l2 = getLezioneInClasse(ora.next(), settimana, classe);
        if (l2 == null) return "uscita";
        if (l1.getAula() == null) return null;
        if (l1.getAula().equals(l2.getAula())) return null;
        return l1.getAula() + " ->" + l2.getAula();

    }

    public TreeSet<RoomData> getAuleVuote(EOra ora, EGiorno settimana) {
        TreeSet<RoomData> ris = new TreeSet<>();

        for (RoomData a : getAule()) {
            if (a.isAulaFittizia())
                continue;

            if (a.maxStudents <= 0)
                continue;

            final List<BitOrarioOraLezione> l = getLezioneInAula(ora, settimana, a);
            if (l.size() == 0)
                ris.add(a);
        }
        return ris;
    }


    /**
     * aule vuote considerando anche l'occupazione della DDI
     *
     * @param ora
     * @param settimana
     * @return
     */
    public TreeSet<RoomData> getAuleVuoteIncludeDDI(EOra ora, EGiorno settimana) {
        TreeSet<RoomData> ris = new TreeSet<>();

        for (RoomData a : getAule()) {
            if (a.isAulaFittizia())
                continue;

            if (a.maxStudents <= 0)
                continue;

            final List<BitOrarioOraLezione> l = getLezioneInAula(ora, settimana, a, true);
            if (l.size() == 0)
                ris.add(a);
        }
        return ris;
    }

    public TreeSet<RoomData> getAuleUtilizzate(EOra ora, EGiorno settimana) {
        TreeSet<RoomData> ris = new TreeSet<>();

        for (RoomData a : getAule()) {
            if (a.isAulaFittizia())
                continue;

            if (a.maxStudents <= 0)
                continue;

            final List<BitOrarioOraLezione> l = getLezioneInAula(ora, settimana, a);
            if (l.size() > 0)
                ris.add(a);
        }
        return ris;
    }

    public TreeSet<RoomData> getAuleOccupate(EOra ora, EGiorno settimana) {
        TreeSet<RoomData> ris = new TreeSet<>();

        for (RoomData a : getAule()) {

            if (a.isAulaFittizia())
                continue;

            final List<BitOrarioOraLezione> l = getLezioneInAula(ora, settimana, a);
            if (l.size() > 0)
                ris.add(a);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioni() {
        return new ArrayList<>(_lezioni.get());
    }

    public List<BitOrarioOraLezione> getLezioneInAula(EOra o, EGiorno s, RoomData aula) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni(o, s);
        List<BitOrarioOraLezione> ris = new ArrayList<>();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getAula() != null && lezione.getAula().equals(aula))
                ris.add(lezione);
        }
        return ris;
    }

    public List<BitOrarioOraLezione> getLezioneInAula(EOra o, EGiorno s, RoomData aula, boolean flagIncludeDDI) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni(o, s);
        List<BitOrarioOraLezione> ris = new ArrayList<>();


        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getAula() == null) continue;
            if (lezione.getAula().equals(aula))
                ris.add(lezione);
            else if (flagIncludeDDI && lezione.getAula().isDDI() && lezione.getAula().getDDI_linkedRoom() == aula) {
                ris.add(lezione);
            }

        }
        return ris;
    }

    public BitOrarioOraLezione getLezionePrimaInClasse(EGiorno s, ClassData classe) {
        for (EOra eOra : EOra.valuesOreDiLezione()) {
            BitOrarioOraLezione l = getLezioneInClasse(eOra, s, classe);
            if (l != null) return l;
        }
        return null;
    }

    public BitOrarioOraLezione getLezioneInClasse(EOra o, EGiorno s, ClassData classe) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni(o, s);
        for (BitOrarioOraLezione lezione : lezioni) {
            if ((lezione.getClasse() != null) && lezione.getClasse().equals(classe))
                return lezione;
        }
        return null;
    }

    public BitOrarioOraLezione getLezioneConDocente(String docente, EGiorno s, EOra o) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioneConDocente(docente);
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getOra() == o && lezione.getGiorno() == s)
                return lezione;
        }
        return null;
    }

    public boolean isOraBucoDocente(String docente, EGiorno s, EOra o) {
        if (o == EOra.PRIMA) return false;
        if (o == EOra.SESTA) return false;
        BitOrarioOraLezione l = getLezioneConDocente(docente, s, o);
        if (l != null) return false;
        BitOrarioOraLezione l_next = getLezioneConDocente(docente, s, o.next());
        BitOrarioOraLezione l_prec = getLezioneConDocente(docente, s, o.prev());
        return l_next != null && l_prec != null;
    }

    public int getOreTotaliDocenteClasseMateria(String docente, String materia, String classe) {
        final ArrayList<BitOrarioOraLezione> lezioneConDocente = getLezioneConDocente(docente);
        int count = 0;
        for (BitOrarioOraLezione x : lezioneConDocente) {
            if (x.getClasse() == null || x.getMateriaPrincipale() == null) continue;
            if (x.getClasse().equals(classe) && x.getMateriaPrincipale().equalsIgnoreCase(materia))
                count++;
        }
        return count;
    }

    public int getOreTotaliDocenteClasse(String docente, String classe) {
        final ArrayList<BitOrarioOraLezione> lezioneConDocente = getLezioneConDocente(docente);
        int count = 0;
        for (BitOrarioOraLezione x : lezioneConDocente) {
            if (x.getClasse() == null || x.getMateriaPrincipale() == null) continue;
            if (x.getClasse().equals(classe))
                count++;
        }
        return count;
    }


    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(String docente, EGiorno s) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getGiorno() != s) continue;
            if (lezione.containsDocenteLowerCase(docente))
                ris.add(lezione);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(String docente, EOra s) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getOra() != s) continue;
            if (lezione.containsDocenteLowerCase(docente))
                ris.add(lezione);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(String docente) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.containsDocenteLowerCase(docente))
                ris.add(lezione);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioni(EOra o, EGiorno s) {
        return new ArrayList<>(_lezioni.get(o, s));
    }

    public void addLezione(Collection<BitOrarioOraLezione> l) {
        checkReadOnly();
        for (BitOrarioOraLezione x : l) {
            addLezione(x);
        }
    }

    /**
     * sostituisce la lezione
     */
    public void addInsegnanteSostegno(EGiorno g, EOra o, ClassData classe, String insegnanteSostegno) {
        final BitOrarioOraLezione lezioneInClasse = getLezioneInClasse(o, g, classe);
        if (lezioneInClasse == null) {
            throw new IllegalArgumentException("lezione non presente");
        }
        removeLezione(lezioneInClasse);
        final BitOrarioOraLezione l = BitOrarioOraLezione.creaOraCompresenzaAndSostegno(lezioneInClasse.getDocentePrincipale(),
                lezioneInClasse.getMateriaPrincipale(),
                lezioneInClasse.getDocenteCompresenza(), lezioneInClasse.getMateriaCompresenza(),
                insegnanteSostegno,
                lezioneInClasse.getAula(),
                lezioneInClasse.getClasse(),
                lezioneInClasse.getOra(),
                lezioneInClasse.getGiorno()
        );
        addLezione(l);
    }

    public void addInsegnanteCompresenza(EGiorno g, EOra o, ClassData classe, String insegnanteCompresenza, String materiaCompresenza) {
        final BitOrarioOraLezione lezioneInClasse = getLezioneInClasse(o, g, classe);
        if (lezioneInClasse == null) {
            throw new IllegalArgumentException("lezione non presente");
        }
        removeLezione(lezioneInClasse);
        final BitOrarioOraLezione l = BitOrarioOraLezione.creaOraCompresenzaAndSostegno(lezioneInClasse.getDocentePrincipale(),
                lezioneInClasse.getMateriaPrincipale(),
                insegnanteCompresenza, materiaCompresenza,
                lezioneInClasse.getDocenteSostegno(),
                lezioneInClasse.getAula(),
                lezioneInClasse.getClasse(),
                lezioneInClasse.getOra(),
                lezioneInClasse.getGiorno()
        );
        addLezione(l);
    }

    public void removeInsegnanteCompresenza(EGiorno g, EOra o, ClassData classe) {
        final BitOrarioOraLezione lezioneInClasse = getLezioneInClasse(o, g, classe);
        if (lezioneInClasse == null) {
            throw new IllegalArgumentException("lezione non presente");
        }
        removeLezione(lezioneInClasse);
        final BitOrarioOraLezione l = BitOrarioOraLezione.creaOraCompresenzaAndSostegno(lezioneInClasse.getDocentePrincipale(),
                lezioneInClasse.getMateriaPrincipale(),
                null, null,
                lezioneInClasse.getDocenteSostegno(),
                lezioneInClasse.getAula(),
                lezioneInClasse.getClasse(),
                lezioneInClasse.getOra(),
                lezioneInClasse.getGiorno()
        );
        addLezione(l);
    }

    public void addLezione(BitOrarioOraLezione l) {
        checkReadOnly();
        if (l._parent != null) {
            throw new IllegalArgumentException("Lezione gia' assegnata ad altro orario");
        }
        if (l.getClasse() != null) {
            BitOrarioOraLezione ris = getLezioneInClasse(l.getOra(), l.getGiorno(), l.getClasse());
            if (ris != null) {
                throw new IllegalArgumentException("Lezione per la classe " + l.getClasse() + " " + l.getOra() + " " + l.getGiorno() + " duplicata. Vecchia lezione:" + ris + ", Nuova lezione:" + l);
            }
        }

        if (l.getDocentePrincipale() != null) {
            String docente = l.getDocentePrincipale();
            BitOrarioOraLezione ris = getLezioneConDocente(docente, l.getGiorno(), l.getOra());
            if (ris != null) {
                throw new IllegalArgumentException("Lezione per il docente " + docente + " " + l.getOra() + " " + l.getGiorno() + " duplicata. Vecchia lezione:" + ris + ", Nuova lezione:" + l);
            }
        }

        if (l.getDocenteCompresenza() != null) {
            String docente = l.getDocenteCompresenza();
            BitOrarioOraLezione ris = getLezioneConDocente(docente, l.getGiorno(), l.getOra());
            if (ris != null) {
                throw new IllegalArgumentException("Lezione per il docente " + docente + " " + l.getOra() + " " + l.getGiorno() + " duplicata.\nVecchia lezione:" + ris + ",\nNuova lezione:" + l);
            }
        }

        if (l.getDocenteSostegno() != null) {
            String docente = l.getDocenteSostegno();
            BitOrarioOraLezione ris = getLezioneConDocente(docente, l.getGiorno(), l.getOra());
            if (ris != null) {
                throw new IllegalArgumentException("Lezione per il docente " + docente + " " + l.getOra() + " " + l.getGiorno() + " duplicata. Vecchia lezione:" + ris + ", Nuova lezione:" + l);
            }
        }

        l._parent = this;
        _lezioni.add(l);
        if (l.getAula() != null)
            _lezioniPerAula.add(l.getAula(), l);

        if (l.getClasse() != null)
            _lezioniPerClasse.add(l.getClasse(), l);

        if (l.getDocentePrincipale() != null)
            _lezioniPerDocente.add(l.getDocentePrincipale(), l);

        if (l.getDocenteCompresenza() != null)
            _lezioniPerDocente.add(l.getDocenteCompresenza(), l);

        if (l.getDocenteSostegno() != null)
            _lezioniPerDocente.add(l.getDocenteSostegno(), l);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            sb.append("*******************");
            sb.append(s).append("\n");
            for (EOra o : EOra.values()) {
                sb.append(o.getOraInizio()).append(":").append(o.getMinutiInizio()).append(": ");
                for (BitOrarioOraLezione x : getLezioni(o, s)) {
                    sb.append("\n   - " + x);

                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public TreeSet<String> getDocenti() {
        return new TreeSet<>(_lezioniPerDocente.keys());
    }

    public TreeSet<String> getDocentiUppercase() {
        TreeSet<String> ris = new TreeSet<>();
        for (String s : getDocenti()) {
            ris.add(s.toUpperCase().trim());
        }
        return ris;
    }

    public TreeSet<ClassData> getClassi() {
        return new TreeSet<>(_lezioniPerClasse.keys());
    }

    public TreeSet<RoomData> getAule() {
        //elenco di tutte le aule disponibili sia presenti nell'orario che nell'ENUM
        final TreeSet<RoomData> ris = new TreeSet<>();//new TreeSet<>(_lezioniPerAula.keys());
        for (RoomData r : RoomData.values()) {
            if (r.isAulaFittizia() && !r.isDDI()) continue;
            ris.add(r);
        }

        return ris;


    }

    public String toStringConDocente(String docente) {
        StringBuilder sb = new StringBuilder();

        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            sb.append("*******************");
            sb.append(s).append("\n");
            for (EOra o : EOra.values()) {
                sb.append(o.getOraInizio()).append(":").append(o.getMinutiInizio()).append(": ");
                sb.append("\n   - " + getLezioneConDocente(docente, s, o));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String toStringInAula(RoomData aula) {
        StringBuilder sb = new StringBuilder();

        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            sb.append("*******************");
            sb.append(s).append("\n");
            for (EOra o : EOra.values()) {
                sb.append(o.getOraInizio()).append(":").append(o.getMinutiInizio()).append(": ");
                sb.append("\n   - " + getLezioneInAula(o, s, aula));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String toStringInClasse(ClassData classe) {
        StringBuilder sb = new StringBuilder();

        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            sb.append("*******************");
            sb.append(s).append("\n");
            for (EOra o : EOra.values()) {
                sb.append(o.getOraInizio()).append(":").append(o.getMinutiInizio()).append(": ");
                sb.append("\n   - " + getLezioneInClasse(o, s, classe));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        final List<BitOrarioOraLezione> bitOrarioOraLezione = _lezioni.get();
        out.writeObject(bitOrarioOraLezione);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        @SuppressWarnings("unchecked") final List<BitOrarioOraLezione> bitOrarioOraLezione = (List<BitOrarioOraLezione>) in.readObject();

        for (BitOrarioOraLezione x : bitOrarioOraLezione) {
            addLezione(x);
        }
    }

    public static class GiornoOra implements Comparable<GiornoOra> {
        public final EGiorno giorno;
        public final EOra ora;

        public GiornoOra(EGiorno giorno, EOra ora) {
            this.giorno = giorno;
            this.ora = ora;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GiornoOra giornoOra = (GiornoOra) o;

            if (giorno != giornoOra.giorno) return false;
            return ora == giornoOra.ora;

        }

        @Override
        public int hashCode() {
            int result = giorno != null ? giorno.hashCode() : 0;
            result = 31 * result + (ora != null ? ora.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(GiornoOra o) {
            final int i = giorno.compareTo(o.giorno);
            if (i != 0) return i;

            return ora.compareTo(o.ora);
        }
    }
}
