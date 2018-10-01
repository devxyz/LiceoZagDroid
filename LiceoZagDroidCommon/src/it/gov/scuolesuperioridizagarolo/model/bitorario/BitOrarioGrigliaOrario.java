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

    public BitOrarioGrigliaOrario(String titolo) {
        this.titolo = titolo;
    }

    public void setReadOnly(boolean f) {
        this.readOnly = f;
    }

    private void checkReadOnly() {
        if (readOnly)
            throw new IllegalArgumentException("Read only mode");
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
            if (a.flagAulaFittizia())
                continue;

            final List<BitOrarioOraLezione> l = getLezioneInAula(ora, settimana, a);
            if (l.size() == 0)
                ris.add(a);
        }
        return ris;
    }

    public TreeSet<RoomData> getAuleOccupate(EOra ora, EGiorno settimana) {
        TreeSet<RoomData> ris = new TreeSet<>();

        for (RoomData a : getAule()) {

            if (a.flagAulaFittizia())
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

    public BitOrarioOraLezione getLezioneInClasse(EOra o, EGiorno s, ClassData classe) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni(o, s);
        for (BitOrarioOraLezione lezione : lezioni) {
            if ((lezione.getClasse() != null) && lezione.getClasse().equals(classe))
                return lezione;
        }
        return null;
    }


    public BitOrarioOraLezione getLezioneConDocente(EOra o, EGiorno s, String docente) {
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioneConDocente(docente);
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getOra() == o && lezione.getGiorno() == s)
                return lezione;
        }
        return null;
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

    @Deprecated
    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(EGiorno s, String docente) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getGiorno() != s) continue;
            if (lezione.getDocenteCompresenza() != null && lezione.getDocenteCompresenza().equalsIgnoreCase(docente) || lezione.getDocentePrincipale().equalsIgnoreCase(docente))
                ris.add(lezione);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(EOra s, String docente) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getOra() != s) continue;
            if (lezione.getDocenteCompresenza() != null && lezione.getDocenteCompresenza().equalsIgnoreCase(docente) || lezione.getDocentePrincipale().equalsIgnoreCase(docente))
                ris.add(lezione);
        }
        return ris;
    }

    public ArrayList<BitOrarioOraLezione> getLezioneConDocente(String docente) {
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        final ArrayList<BitOrarioOraLezione> lezioni = getLezioni();
        for (BitOrarioOraLezione lezione : lezioni) {
            if (lezione.getDocenteCompresenza() != null && lezione.getDocenteCompresenza().equalsIgnoreCase(docente) || lezione.getDocentePrincipale().equalsIgnoreCase(docente))
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

    public void addLezione(BitOrarioOraLezione l) {
        checkReadOnly();
        if (l._parent != null) {
            throw new IllegalArgumentException("Lezione gia' assegnata ad altro orario");
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

    public TreeSet<ClassData> getClassi() {
        return new TreeSet<>(_lezioniPerClasse.keys());
    }

    public TreeSet<RoomData> getAule() {
        //elenco di tutte le aule disponibili sia presenti nell'orario che nell'ENUM
        final TreeSet<RoomData> ris = new TreeSet<>();//new TreeSet<>(_lezioniPerAula.keys());
        for (RoomData r : RoomData.values()) {
            if (r.flagAulaFittizia()) continue;
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
                sb.append("\n   - " + getLezioneConDocente(o, s, docente));
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
}
