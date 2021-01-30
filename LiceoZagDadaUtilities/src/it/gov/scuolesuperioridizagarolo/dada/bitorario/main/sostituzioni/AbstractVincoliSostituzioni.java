package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_ClasseBloccataInArea;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_ClasseNonInAula_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import utility.aule2020_21.UtilizzoClassi;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 27/04/2018.
 */
public abstract class AbstractVincoliSostituzioni {
    public static final ClassData _1A = ClassData.CLASS_1A;
    public static final ClassData _1B = ClassData.CLASS_1B;
    public static final ClassData _1C = ClassData.CLASS_1C;
    public static final ClassData _1D = ClassData.CLASS_1D;
    public static final ClassData _1E = ClassData.CLASS_1E;
    public static final ClassData _1F = ClassData.CLASS_1F;
    public static final ClassData _2A = ClassData.CLASS_2A;
    public static final ClassData _2B = ClassData.CLASS_2B;
    public static final ClassData _2C = ClassData.CLASS_2C;
    public static final ClassData _2D = ClassData.CLASS_2D;
    public static final ClassData _2E = ClassData.CLASS_2E;
    public static final ClassData _2F = ClassData.CLASS_2F;
    public static final ClassData _2G = ClassData.CLASS_2G;
    public static final ClassData _2H = ClassData.CLASS_2H;
    public static final ClassData _3A = ClassData.CLASS_3A;
    public static final ClassData _3B = ClassData.CLASS_3B;
    public static final ClassData _3C = ClassData.CLASS_3C;
    public static final ClassData _3D = ClassData.CLASS_3D;
    public static final ClassData _3E = ClassData.CLASS_3E;
    public static final ClassData _3F = ClassData.CLASS_3F;
    public static final ClassData _3H = ClassData.CLASS_3H;
    public static final ClassData _4A = ClassData.CLASS_4A;
    public static final ClassData _4B = ClassData.CLASS_4B;
    public static final ClassData _4C = ClassData.CLASS_4C;
    public static final ClassData _4D = ClassData.CLASS_4D;
    public static final ClassData _4E = ClassData.CLASS_4E;
    public static final ClassData _4F = ClassData.CLASS_4F;
    public static final ClassData _4G = ClassData.CLASS_4G;
    public static final ClassData _4H = ClassData.CLASS_4H;
    public static final ClassData _5A = ClassData.CLASS_5A;
    public static final ClassData _5B = ClassData.CLASS_5B;
    public static final ClassData _5C = ClassData.CLASS_5C;
    public static final ClassData _5D = ClassData.CLASS_5D;
    public static final ClassData _5E = ClassData.CLASS_5E;
    public static final ClassData _5F = ClassData.CLASS_5F;
    public static final ClassData _5G = ClassData.CLASS_5G;
    public static final RoomData _NON_ASSEGNATO = RoomData.NON_ASSEGNATO;
    public static final RoomData _AULA_SCONOSCIUTA = RoomData.AULA_SCONOSCIUTA;
    public static final RoomData _USCITA_DIDATTICA = RoomData.USCITA_DIDATTICA;
    public static final RoomData _A1 = RoomData.A1;
    public static final RoomData _A2 = RoomData.A2;
    public static final RoomData _A3 = RoomData.A3;
    public static final RoomData _A4 = RoomData.A4;
    public static final RoomData _A6 = RoomData.A6;
    public static final RoomData _A5 = RoomData.A5;
    public static final RoomData _A8 = RoomData.A8;
    public static final RoomData _A7 = RoomData.A7;
    public static final RoomData _B3 = RoomData.B3;
    public static final RoomData _B4 = RoomData.B4;
    public static final RoomData _B5 = RoomData.B5;
    public static final RoomData _B6 = RoomData.B6;
    public static final RoomData _B7 = RoomData.B7;
    public static final RoomData _B1 = RoomData.B1;
    public static final RoomData _B2 = RoomData.B2;
    public static final RoomData _C6 = RoomData.C6;
    public static final RoomData _C7 = RoomData.C7;
    public static final RoomData _C1 = RoomData.C1;
    public static final RoomData _C2 = RoomData.C2;
    public static final RoomData _C3 = RoomData.C3;
    public static final RoomData _C4 = RoomData.C4;
    public static final RoomData _C5 = RoomData.C5;
    public static final RoomData _D1 = RoomData.D1;
    public static final RoomData _D2 = RoomData.D2;
    public static final RoomData _D3 = RoomData.D3;
    public static final RoomData _D4 = RoomData.D4;
    public static final RoomData _D5 = RoomData.D5;
    public static final RoomData _E1 = RoomData.E1;
    public static final RoomData _E2 = RoomData.E2;
    public static final RoomData _E3 = RoomData.E3;
    public static final RoomData _E4 = RoomData.E4;
    public static final RoomData _E5 = RoomData.E5;
    public static final RoomData _F1 = RoomData.F1;
    public static final RoomData _F2 = RoomData.F2;

    protected String dal;
    protected String al;
    protected String progressivo;

    final EGiorno cercaDDI(ArrayList<UtilizzoClassi> assegnazioni, ClassData c) {
        for (EGiorno d : EGiorno.valuesGiorniDiLezione()) {
            if (!cerca(assegnazioni, c, d)) return d;
        }
        return null;
    }

    public static void toUpperCase(Map<String, String> rename) {
        //tutto maiuscolo
        Map<String, String> rename2 = new TreeMap<>();
        for (Map.Entry<String, String> e : rename.entrySet()) {
            rename2.put(e.getKey().toUpperCase(), e.getValue().toUpperCase());
        }
        rename.clear();
        rename.putAll(rename2);
    }

     public static void renameDocente(BitOrarioGrigliaOrario orario, Map<String, String> rename) {
        toUpperCase(rename);
        TreeSet<String> docenti = orario.getDocenti();
        for (String nomiVecchi : rename.keySet()) {
            if (!docenti.contains(nomiVecchi)) {
                throw new IllegalArgumentException("Nome docente " + nomiVecchi + " non presente in orario");
            }
        }

        for (Map.Entry<String, String> s : rename.entrySet()) {
            String vecchioNome = s.getKey();
            String nuovoNome = s.getValue();
            ArrayList<BitOrarioOraLezione> lezioneConDocente = new ArrayList<>(orario.getLezioneConDocente(vecchioNome));
            for (BitOrarioOraLezione l : lezioneConDocente) {
                BitOrarioOraLezione l2 = l.clonaLezioneConAltroDocentePrincipale(nuovoNome);
                orario.removeLezione(l);
                orario.addLezione(l2);
            }
        }
    }

    //restituisce la lezione da rimuovere
    public static void rimuoviDisposizioni(BitOrarioGrigliaOrario orarioTotale, String docente, EGiorno g, EOra o) {
        BitOrarioOraLezione lezioneConDocente = orarioTotale.getLezioneConDocente(docente, g, o);
        if (lezioneConDocente == null)
            throw new IllegalArgumentException("Lezione non trovata in " + g + " " + o + " " + docente);
        if (lezioneConDocente.getTipoLezione()!=BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
            throw new IllegalArgumentException("Lezione non di disposizione in " + g + " " + o + " " + docente);
        orarioTotale.removeLezione(lezioneConDocente);
    }

    public static BitOrarioOraLezione[] spostaDisposizioni(BitOrarioGrigliaOrario orarioTotale, String docente, EGiorno daGiorno, EOra daOra, EGiorno aGiorno, EOra aOra) {
        docente = docente.toUpperCase();
        final BitOrarioOraLezione lezioneDaRimuovere = orarioTotale.getLezioneConDocente(docente, daGiorno, daOra);
        if (lezioneDaRimuovere == null)
            throw new NullPointerException("Lezione da rimuovere " + docente + " " + daGiorno + " " + daOra + " inesistente");
        if (lezioneDaRimuovere.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
            throw new IllegalArgumentException("Lezione da rimuovere " + docente + " " + daGiorno + " " + daOra + " non e' disposizione: " + lezioneDaRimuovere);
        }

        BitOrarioOraLezione lezioneDaAggiungere = orarioTotale.getLezioneConDocente(docente, aGiorno, aOra);
        if (lezioneDaAggiungere != null) {
            throw new IllegalArgumentException("Lezione da aggiungere " + docente + " " + aGiorno + " " + aOra + " e' gia' occupata: " + lezioneDaAggiungere);
        }

        lezioneDaAggiungere = BitOrarioOraLezione.creaOraDisposizione(docente, aOra, aGiorno);
        orarioTotale.removeLezione(lezioneDaRimuovere);
        orarioTotale.addLezione(lezioneDaAggiungere);
        return new BitOrarioOraLezione[]{lezioneDaRimuovere, lezioneDaAggiungere};
    }

    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        return new TreeMap<>();
    }

    final boolean cerca(ArrayList<UtilizzoClassi> assegnazioni, ClassData c, EGiorno day) {
        for (UtilizzoClassi a : assegnazioni) {
            if (a.day == day && a.clazz == c) return false;
        }
        return true;
    }


    protected final void assegnaAuleAdOrario(BitOrarioGrigliaOrario orarioTotale, ArrayList<UtilizzoClassi> assegnaule) {
        //ASSEGNAZIONE AULE
        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orarioTotale.getLezioni());
        for (BitOrarioOraLezione l : lezioni) {
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_CON_COMPRESENZA
                    || l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_SINGOLA) {
                ClassData classe = l.getClasse();
                EGiorno giorno = l.getGiorno();


                orarioTotale.removeLezione(l);
                UtilizzoClassi search = search(classe, giorno, assegnaule);
                if (search != null)
                    orarioTotale.addLezione(l.clonaLezioneInAltraAula(search.room));
                else
                    orarioTotale.addLezione(l.clonaLezioneInAltraAula(RoomData.DDI_da_casa));
            }
        }
    }


    public final UtilizzoClassi search(ClassData clazz, EGiorno giorno, ArrayList<UtilizzoClassi> aa) {
        for (UtilizzoClassi utilizzoClassi : aa) {
            if (utilizzoClassi.clazz.equals(clazz) && utilizzoClassi.day.equals(giorno))
                return utilizzoClassi;
        }
        return null;
        //return new UtilizzoClassi(RoomData.DDI_1, clazz, giorno);
        //throw new IllegalArgumentException("Aula non trovata per " + clazz + ", " + giorno);
    }

    /**
     * cambia le assegnazioni per gestire le quarantene
     *
     * @param assegnazioni
     * @param classi
     */
    public static final void quarantena(ArrayList<UtilizzoClassi> assegnazioni, ClassData... classi) {
        ArrayList<UtilizzoClassi> ris = new ArrayList<>();
        TreeSet<ClassData> quarantena = new TreeSet<>(Arrays.asList(classi));
        for (UtilizzoClassi a : assegnazioni) {
            if (!quarantena.contains(a.clazz)) {
                ris.add(a);
            }
        }
        assegnazioni.clear();
        assegnazioni.addAll(ris);
    }

    //rimuove le aule per i giorni di quarantena
    public static final void quarantena(ArrayList<UtilizzoClassi> assegnazioni, ClassData classe, EGiorno... giorni) {
        ArrayList<UtilizzoClassi> ris = new ArrayList<>();
        TreeSet<EGiorno> ggiorni = new TreeSet<>(Arrays.asList(giorni));
        for (UtilizzoClassi a : assegnazioni) {
            if (a.clazz.equals(classe)) {
                if (!ggiorni.contains(a.day)) {
                    ris.add(a);
                } else {
                    //NON RIPORTARE L'AULA
                }
            } else {
                ris.add(a);
            }
        }
        assegnazioni.clear();
        assegnazioni.addAll(ris);
    }

    public static void main(String[] args) throws IOException {
        for (ClassData classe : ClassData.values()) {
            String className = classe.className.replace(" ", "_").replace("#", "sharp");
            System.out.println("public static final ClassData _" + className + " = ClassData.CLASS_" + classe.className.toUpperCase() + ";");
        }
        for (RoomData room : RoomData.values()) {
            String roomName = room.roomName.replace(" ", "_").replace("#", "sharp").replace("(", "").replace(")", "");
            System.out.println("public static final RoomData _" + roomName + " = RoomData." + room.name() + ";");
        }
    }

    public AbstractVincoliSostituzioni() {

    }

    public static String checkAuleMultiple(final BitOrarioGrigliaOrario orarioTotale) {
        int anomalie = 0;
        StringBuilder sb = new StringBuilder();
        for (EGiorno g : EGiorno.values()) {
            if (!g.flagGiornoDiLezione()) continue;
            for (EOra o : EOra.values()) {
                if (!o.flagOraDiLezione()) continue;
                for (RoomData aula : orarioTotale.getAule()) {
                    if (aula.equals(_F1))
                        continue;
                    if ((aula) == RoomData.USCITA_DIDATTICA)
                        continue;
                    if ((aula).isDDI())
                        continue;
                    final List<BitOrarioOraLezione> lezioneInAula = orarioTotale.getLezioneInAula(o, g, aula);
                    if (lezioneInAula.size() > 1) {
                        sb.append("\n------------\n AULA " + aula + " " + lezioneInAula.size() + " lezioni il " + g + " " + o + " ora!!!\n");
                        for (BitOrarioOraLezione l : lezioneInAula) {
                            sb.append("   >> ").append(l);
                        }

                        anomalie++;
                    }
                }
            }
        }

        return (anomalie > 0) ? ("Anomalie: " + anomalie + "\n" + sb) : "Nessuna anomalia con uso multiplo aule";
    }

    protected static void classeConStampelle(LessonConstraintContainer l, ClassData classe, EGiorno... giorni) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D, ERoomArea.AREA_F}, giorni));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F2, giorni));
    }

    protected static void classeConStampelle(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D, ERoomArea.AREA_F}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F2, EGiorno.values()));
    }

    protected static void classeConStampellePianoTerra(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F2, EGiorno.values()));
    }


    /**
     * eseguito al termine dell'elaborazione, prima della verifica finale vincoli e stampa report
     *
     * @param orarioTotale
     * @param l
     */
    protected void postOrarioBeforeFinalCheck(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {

    }

    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {

    }

    /*
    modificare in casi specifici
     */
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{FilterAule.LABORATORI_MAI, FilterAule.LABORATORI_SOLO_COMPATIBILI, FilterAule.LABORATORI_SEMPRE};
    }

    public String getDal() {
        return dal;
    }

    public String getAl() {
        return al;
    }

    public abstract AbstractVincoliSostituzioni invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l);

}
