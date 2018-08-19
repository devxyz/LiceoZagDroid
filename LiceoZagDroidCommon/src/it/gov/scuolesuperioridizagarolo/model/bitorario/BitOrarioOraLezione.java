package it.gov.scuolesuperioridizagarolo.model.bitorario;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 16/09/2017.
 */
public class BitOrarioOraLezione implements Comparable<BitOrarioOraLezione> {
    protected transient BitOrarioGrigliaOrario _parent;
    private String docentePrincipale;
    private String materiaPrincipale;
    private String docenteCompresenza;
    private String materiaCompresenza;
    private String nomeAula;
    private String classe;
    private EOra ora;
    private EGiorno giorno;
    private String note;
    private BitOrarioOraEnumTipoLezione tipoLezione;

    //ora lezione
    private BitOrarioOraLezione(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String nomeAula, String classe, EOra ora, EGiorno giorno, BitOrarioOraEnumTipoLezione t) {
        this.docentePrincipale = normalize(docentePrincipale);
        this.materiaPrincipale = normalize(materiaPrincipale);
        this.docenteCompresenza = normalize(docenteCompresenza);
        this.materiaCompresenza = normalize(materiaCompresenza);
        this.nomeAula = normalize(nomeAula);
        this.classe = normalize(classe);
        this.ora = ora;
        this.giorno = giorno;
        this.tipoLezione = t;

    }

    public static boolean saveEquals(BitOrarioOraLezione a, BitOrarioOraLezione b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }


    //gestire note
    //ora lab
    public static BitOrarioOraLezione creaOraCompresenza(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String nomeAula, String classe, EOra ora, EGiorno giorno) {
        return new BitOrarioOraLezione(docentePrincipale, materiaPrincipale, docenteCompresenza, materiaCompresenza, nomeAula, classe, ora, giorno, BitOrarioOraEnumTipoLezione.LEZIONE_CON_COMPRESENZA);
    }

    public static BitOrarioOraLezione creaOraDocenteSingolo(String docentePrincipale, String materiaPrincipale, String nomeAula, String classe, EOra ora, EGiorno giorno) {
        return new BitOrarioOraLezione(docentePrincipale, materiaPrincipale, null, null, nomeAula, classe, ora, giorno, BitOrarioOraEnumTipoLezione.LEZIONE_SINGOLA);
    }

    public static BitOrarioOraLezione creaOraDisposizione(String docentePrincipale, EOra ora, EGiorno giorno) {
        return new BitOrarioOraLezione(docentePrincipale, "D", null, null, null, null, ora, giorno, BitOrarioOraEnumTipoLezione.DISPOSIZIONE);
    }

    //controlla se ha un parent
    public boolean isActive() {
        return _parent != null;
    }

    public RoomData getAula() {
        return ClassesAndRoomContainer.getRoom(this);
    }

    public void appendNote(String note) {
        if (this.note == null) {
            this.note = note;
        } else {
            this.note += " " + note;

        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BitOrarioOraLezione clonaLezioneInAltraAula(String nuovaaula) {
        return new BitOrarioOraLezione(
                getDocentePrincipale(), getMateriaPrincipale(),
                getDocenteCompresenza(), getMateriaCompresenza(),
                nuovaaula, getClasse(),
                getOra(), getGiorno(),
                getTipoLezione());
    }

    private String normalize(String docentePrincipale) {
        return docentePrincipale == null ? null : docentePrincipale.trim().toUpperCase();
    }

    public String toString() {
        return toStringShort();
    }

    private <K extends Comparable<K>> int compare(K a, K b) {
        if (a == b && a == null) return 0;
        if (a == null) return 1;
        if (b == null) return -1;
        return a.compareTo(b);
    }


    public String toStringComplete() {
        return ora + " " + giorno + " " + toStringShort();
    }

    public String toStringShort() {
        if (getDocenteCompresenza() == null)
            return getMateriaPrincipale() + "-" + getDocentePrincipale() + " (" + getNomeAula() + ") classe:" + classe;
        else
            return getMateriaPrincipale() + "-" + getDocentePrincipale() + "-" + getDocenteCompresenza() + " (" + getNomeAula() + ") classe:" + classe;
    }

    @Override
    public int compareTo(BitOrarioOraLezione o) {
        int i = compare(this.giorno, o.giorno);
        if (i == 0) {
            i = compare(this.ora, o.ora);
        }
        if (i == 0) {
            i = compare(this.classe, o.classe);
        }
        if (i == 0) {
            i = compare(this.materiaPrincipale, o.materiaPrincipale);
        }
        if (i == 0) {
            i = compare(this.docentePrincipale, o.docentePrincipale);
        }
        if (i == 0) {
            i = compare(this.docenteCompresenza, o.docenteCompresenza);
        }
        if (i == 0) {
            i = compare(this.materiaCompresenza, o.materiaCompresenza);
        }

        return i;
    }

    public String getDocentePrincipale() {
        return docentePrincipale;
    }

    public String getMateriaPrincipale() {
        return materiaPrincipale;
    }

    public String getDocenteCompresenza() {
        return docenteCompresenza;
    }

    public String getMateriaCompresenza() {
        return materiaCompresenza;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public boolean uscitaDidattica() {
        return classe != null && nomeAula != null && nomeAula.equals(ClassesAndRoomContainer.USCITA_DIDATTICA);
    }

    public String getClasse() {
        return classe;
    }

    public EOra getOra() {
        return ora;
    }

    public EGiorno getGiorno() {
        return giorno;
    }

    public BitOrarioOraEnumTipoLezione getTipoLezione() {
        return tipoLezione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitOrarioOraLezione that = (BitOrarioOraLezione) o;

        if (docentePrincipale != null ? !docentePrincipale.equals(that.docentePrincipale) : that.docentePrincipale != null)
            return false;
        if (materiaPrincipale != null ? !materiaPrincipale.equals(that.materiaPrincipale) : that.materiaPrincipale != null)
            return false;
        if (docenteCompresenza != null ? !docenteCompresenza.equals(that.docenteCompresenza) : that.docenteCompresenza != null)
            return false;
        if (materiaCompresenza != null ? !materiaCompresenza.equals(that.materiaCompresenza) : that.materiaCompresenza != null)
            return false;
        if (nomeAula != null ? !nomeAula.equals(that.nomeAula) : that.nomeAula != null) return false;
        if (classe != null ? !classe.equals(that.classe) : that.classe != null) return false;
        if (ora != that.ora) return false;
        if (giorno != that.giorno) return false;
        return tipoLezione == that.tipoLezione;

    }

    @Override
    public int hashCode() {
        int result = docentePrincipale != null ? docentePrincipale.hashCode() : 0;
        result = 31 * result + (materiaPrincipale != null ? materiaPrincipale.hashCode() : 0);
        result = 31 * result + (docenteCompresenza != null ? docenteCompresenza.hashCode() : 0);
        result = 31 * result + (materiaCompresenza != null ? materiaCompresenza.hashCode() : 0);
        result = 31 * result + (nomeAula != null ? nomeAula.hashCode() : 0);
        result = 31 * result + (classe != null ? classe.hashCode() : 0);
        result = 31 * result + (ora != null ? ora.hashCode() : 0);
        result = 31 * result + (giorno != null ? giorno.hashCode() : 0);
        result = 31 * result + (tipoLezione != null ? tipoLezione.hashCode() : 0);
        return result;
    }
}
