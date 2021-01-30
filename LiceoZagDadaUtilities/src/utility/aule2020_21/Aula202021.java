package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.ArrayList;

public class Aula202021 {
    public final String aula;
    public final int capienza;
    public final int idCasuale = SingletonRandomizer.getSingleton().nextInt(1000);//numero casuale utile per le elaborzioni

    public int getIdCasuale() {
        return idCasuale;
    }

    public Aula202021(String aula, int capienza) {
        this.aula = aula;
        this.capienza = capienza;
    }

    public Aula202021(RoomData aula) {
        this.aula = aula.roomName;
        this.capienza = aula.maxStudents;
    }

    public RoomData toRoomData() {
        return RoomData.search(aula);
    }

    public String getAula() {
        return aula;
    }

    public boolean laboratorioSpeciale() {
        return aula.contains("*");
    }

    public int getCapienza() {
        return capienza;
    }

    @Override
    public String toString() {
        return "Aula202021{" +
                "aula='" + aula + '\'' +
                ", capienza=" + capienza +
                '}';
    }

    public static String toString(ArrayList<Aula202021> a) {
        StringBuilder sb = new StringBuilder();
        for (Aula202021 aula202021 : a) {
            sb.append(aula202021).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aula202021 that = (Aula202021) o;

        if (capienza != that.capienza) return false;
        return aula != null ? aula.equals(that.aula) : that.aula == null;
    }

    @Override
    public int hashCode() {
        int result = aula != null ? aula.hashCode() : 0;
        result = 31 * result + capienza;
        return result;
    }
}
