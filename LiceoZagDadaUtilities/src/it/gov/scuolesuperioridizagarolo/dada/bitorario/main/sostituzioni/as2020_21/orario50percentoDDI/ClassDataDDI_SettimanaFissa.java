package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import utility.aule2020_21.*;

import javax.swing.table.TableRowSorter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class ClassDataDDI_SettimanaFissa implements IClassDataDDI {
    public final TreeSet<ClassData> ddi = new TreeSet<>();
    public final TreeSet<ClassData> presenza = new TreeSet<>();
    public final TreeSet<ClassData> fisse = new TreeSet<>();//classi prime fisse


    public static void checkPresenzeNonDistinti(IClassDataDDI[] aa) {
        for (int i = 0; i < aa.length; i++) {
            for (int j = i + 1; j < aa.length; j++) {
                TreeSet<ClassData> p1 = new TreeSet<>(((ClassDataDDI_SettimanaFissa) aa[i]).presenza);
                TreeSet<ClassData> p2 = new TreeSet<>(((ClassDataDDI_SettimanaFissa) aa[j]).presenza);

                for (ClassData c1 : p1) {
                    for (ClassData c2 : p2) {
                        if (c1.equals(c2)) {
                            throw new IllegalArgumentException("Settimana " + i + " e " + j + " non distinte.\n" + p1 + "\n" + p2);
                        }
                    }
                }
            }
        }
    }


    public ClassDataDDI_SettimanaFissa() {
    }

    public ClassDataDDI_SettimanaFissa(ClassData[] _ddi, ClassData[] _presenza, ClassData[] _fisse) {
        ddi.addAll(Arrays.asList(_ddi));
        presenza.addAll(Arrays.asList(_presenza));
        fisse.addAll(Arrays.asList(_fisse));
    }

    public TreeSet<ClassData> getFisse() {
        return fisse;
    }

    public TreeSet<ClassData> getDdi() {
        return ddi;
    }

    public TreeSet<ClassData> getPresenza() {
        return presenza;
    }

    private String toStringCodeClassData(Collection<ClassData> d) {
        StringBuilder sb = new StringBuilder();
        for (ClassData c : d) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("ClassData." + c.name());
        }
        return "new ClassData[] {" + sb.toString() + "} ";
    }

    public String generateCodeClassDataDDI() {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bout);
        out.println("IClassDataDDI cd=new ClassDataDDI_SettimanaFissa(");
        out.println("  " + toStringCodeClassData(ddi) + ",");
        out.println("  " + toStringCodeClassData(presenza) + ",");
        out.println("  " + toStringCodeClassData(fisse));
        out.println(");");
        out.close();
        return bout.toString();

    }

    public int numeroStudentiDDI() {
        int i = 0;
        for (ClassData c : ddi) {
            i += c.numberOfStudents;
        }
        return i;
    }


    public int numeroStudentiPresenze() {
        int i = 0;
        for (ClassData c : presenza) {
            i += c.numberOfStudents;
        }
        return i;
    }

    public int numeroStudentiFissi() {
        int i = 0;
        for (ClassData c : fisse) {
            i += c.numberOfStudents;
        }
        return i;
    }


    @Override
    public String generateCodeAssegnazioni(final ArrayList<UtilizzoClassi> a) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bout);
        out.println("ArrayList<UtilizzoClassi>assegnazioni=new ArrayList<>();");
        for (UtilizzoClassi u : a) {
            out.println("assegnazioni.add(" + u.toStringGenerateCode() + ");");
        }
        out.close();
        return bout.toString();
    }

    @Override
    public ArrayList<UtilizzoClassi> computeAssegnazioniPRESENZA_E_FISSE(final Set<RoomData> skippedRoom) {
        return computeAssegnazioni(presenza, fisse, skippedRoom);
    }


    @Override
    public boolean isDDI(ClassData c, EGiorno giorno) {
        return ddi.contains(c);
    }

    @Override
    public String toString() {
        return "ClassDataDDI" +
                "\n - ddi=" + ddi +
                "\n - presenza=" + presenza +
                "\n - fisse=" + fisse;
    }

    @Override
    public ClassDataDDI_SettimanaFissa inverso() {
        ClassDataDDI_SettimanaFissa c2 = new ClassDataDDI_SettimanaFissa();
        c2.ddi.addAll(presenza);
        c2.presenza.addAll(ddi);
        c2.fisse.addAll(fisse);
        return c2;
    }

    //assegnazioni per le classi per l'intera settimana
    private ArrayList<UtilizzoClassi> computeAssegnazioni(Collection<ClassData> classi, Collection<ClassData> fisse, Set<RoomData> skippedRoom) {


        //==================================================================
        final ArrayList<Aula202021> aule202021 = new ArrayList<>();
        {
            ArrayList<RoomData> roomArray = new ArrayList<>((RoomData.filter(new RoomData.RoomDataFilter() {
                @Override
                public boolean accept(RoomData c) {
                    return c.getMaxStudents() > 0 && !skippedRoom.contains(c);
                }
            })));
            for (RoomData r : roomArray) {
                aule202021.add(new Aula202021(r));
            }
        }

        //==================================================================
        final ArrayList<Classe202021> classi202021 = new ArrayList<>();
        {
            for (ClassData c : classi) {
                classi202021.add(new Classe202021(c));
            }
            for (ClassData c : fisse) {
                classi202021.add(new Classe202021(c));
            }
        }


        final ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();


        //ordina dalla piu' grande alla piu' piccola
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            //System.out.println("GIORNO: " + g);
            AssegnazioneClasseAulaGiornaliera202021 r = RipartizioneAuleClassiEngine.assegnaClassiAule(new ArrayList<>(aule202021), new ArrayList<>(classi202021), g);
            //System.out.println(r.assegnazioni);
            assegnazioni.addAll(r.toUtilizzoClassi());

            //printCodiceAssegnazioneSettimanaPerClasse
        }

        return assegnazioni;
    }

}
