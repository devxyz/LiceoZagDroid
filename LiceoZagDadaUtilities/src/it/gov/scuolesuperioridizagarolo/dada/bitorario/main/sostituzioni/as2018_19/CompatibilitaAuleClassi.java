package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.*;

/**
 * Created by stefano on 10/09/2018.
 */
public class CompatibilitaAuleClassi {
    public static void main(String[] args) {

        //aule ordinate per num studenti, crescenti
        ArrayList<RoomData> aule = new ArrayList<>(Arrays.asList(RoomData.values()));
        Collections.sort(aule, new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                final int i = new Integer(o1.maxStudents).compareTo(o2.maxStudents);
                if (i != 0) return i;
                return o1.roomName.compareTo(o2.roomName);
            }
        });
        ArrayList<ClassData> classi = new ArrayList<>(Arrays.asList(ClassData.values()));
        Collections.sort(classi, new Comparator<ClassData>() {
            @Override
            public int compare(ClassData o1, ClassData o2) {
                final int i = Integer.valueOf(o1.numberOfStudents).compareTo(o2.numberOfStudents);
                if (i != 0) return i;
                return o1.className.compareTo(o2.className);
            }
        });


        System.out.println("AULE ORDINATE SENSO CRESCENTE");
        for (RoomData x : aule) {
            System.out.println(x + " " + x.maxStudents);
        }

        System.out.println();
        System.out.println("CLASSI ORDINATE SENSO CRESCENTE");
        for (ClassData x : classi) {
            System.out.println(x + " " + x.numberOfStudents);
        }
        System.out.println();

        for (RoomData roomData : RoomData.values()) {
            if (
                    roomData.flagAulaLaboratorioPalestra() ||
                            roomData.flagAulaFittizia() ||
                            roomData.maxStudents == 0
                    ) {
                aule.remove(roomData);
            }
        }

        ArrayList<ClassData> classi2 = new ArrayList<>(classi);
        Map<String, String> reportClassi = new LinkedHashMap<>();
        for (ClassData x : classi2) {

            String report = "NUM STUDENTI:" + x.numberOfStudents + "\n";
            //cerca aula predefinita
            for (RoomData roomData : aule) {
                if (roomData.flagAulaFittizia()) continue;
                if (roomData.flagAulaLaboratorioPalestra()) continue;
                if (roomData.maxStudents == 0) continue;

                if (roomData.maxStudents >= x.numberOfStudents) {
                    aule.remove(roomData);
                    report += "AULA PREDEFINITA: " + roomData.roomName + "(" + roomData.maxStudents + ")\n";
                    break;
                }
            }

            StringBuilder auleIncompatibili = new StringBuilder();
            for (RoomData roomData : RoomData.values()) {
                if (roomData.flagAulaFittizia()) continue;
                if (roomData.flagAulaLaboratorioPalestra()) continue;
                if (roomData.maxStudents == 0) continue;

                if (roomData.maxStudents < x.numberOfStudents) {
                    if (auleIncompatibili.length() > 0) {
                        auleIncompatibili.append(", ");
                    }
                    auleIncompatibili.append(roomData.roomName + "(" + roomData.maxStudents + ")");
                }


            }
            report += "AULE INCOMPATIBILI: " + auleIncompatibili;
            reportClassi.put(x.className, report);
        }

        System.out.println();
        System.out.println("AULE NON UTILIZZABILI");
        System.out.println(aule);


        System.out.println();
        System.out.println("REPORT");
        for (Map.Entry<String, String> e : reportClassi.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println("============================================");

        }

    }
}
