package utility.aule2020_21.data;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

public class RipartizioneAuleClassiData_08_aule_definitive extends RipartizioneAuleClassiData {

    @Override
    String getAuleImpl() {
        StringBuilder sb = new StringBuilder();
        for (RoomData c : RoomData.values()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(c.maxStudents + "\t" + c.roomName);
        }
        return sb.toString();

     /*
        return

                "23\tF32\n" +//lab chimica
                        //"30\tF31-a\n" +//1 aula palestra
                        //"30\tF31-b\n" +//1 aula palestra
                        "17\tA6\n" +
                        "12\tA7\n" +
                        "30\tA5\n" +//aula disegno
                        "0\tA4\n" +//lab informatica
                        "24\tA3\n" +
                        "18\tA2\n" +
                        "24\tA1\n" +
                        "24\tB8\n" +
                        "24\tB9\n" +
                        "24\tB10\n" +
                        "25\tB11\n" +
                        "24\tB12\n" +
                        "25\tB13\n" +
                        "22\tC21\n" +
                        "26\tC20\n" +
                        "26\tC19\n" +
                        "26\tC18\n" +
                        "26\tC17\n" +
                        "18\tC14\n" +
                        "24\tC16\n" +

                        "25\tE28\n" +
                        "25\tE29\n" +
                        "25\tE29#\n" +
                        "25\tE30\n" +
                        "22\tD26\n" +
                        "26\tD25\n" +
                        "26\tD24\n" +
                        "26\tD23\n" +
                        "26\tD22\n" +
                        "22\tE27\n" +
                        "15\tA5#\n" +
                        "30\tB13#";*/
    }

    @Override
    String getClassiImpl() {
        StringBuilder sb = new StringBuilder();
        for (ClassData c : ClassData.values()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(c.numberOfStudents + "\t" + c.className);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RipartizioneAuleClassiData_08_aule_definitive d = new RipartizioneAuleClassiData_08_aule_definitive();
        System.out.println(d.getAuleImpl());
    }
}
