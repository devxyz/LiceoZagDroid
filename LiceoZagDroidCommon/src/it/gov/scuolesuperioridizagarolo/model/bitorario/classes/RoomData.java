package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

/**
 * Created by stefano on 24/11/2017.
 */
public class RoomData {

    public static final String USCITA_DIDATTICA = ClassesAndRoomContainer.USCITA_DIDATTICA;
    public boolean isUscitaDidattica() {
        return name.equals(ClassesAndRoomContainer.USCITA_DIDATTICA);
    }

    public final String name;
    public final String usage;
    public final int maxStudents;
    public final int idRoom;
    public final ERoomArea location;
    public final boolean flagLIM;
    public final boolean flagPRESACORRENTE;
    public final int progressive;

    public RoomData(int progressive, String name, String usage, int maxStudents, int idRoom, ERoomArea location, boolean flagLIM, boolean flagPRESACORRENTE) {
        this.name = name;
        this.usage = usage;
        this.maxStudents = maxStudents;
        this.idRoom = idRoom;
        this.location = location;
        this.flagLIM = flagLIM;
        this.flagPRESACORRENTE = flagPRESACORRENTE;
        this.progressive = progressive;
    }

    public String simpleName() {
        return name.split("_")[0];
    }

    @Override
    public String toString() {
        return name;
    }

    //true se aula speciale
    public boolean flagSpecial() {
        return !usage.equalsIgnoreCase("Aula Didattica");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomData roomData = (RoomData) o;

        if (maxStudents != roomData.maxStudents) return false;
        if (idRoom != roomData.idRoom) return false;
        if (flagLIM != roomData.flagLIM) return false;
        if (flagPRESACORRENTE != roomData.flagPRESACORRENTE) return false;
        if (progressive != roomData.progressive) return false;
        if (name != null ? !name.equals(roomData.name) : roomData.name != null) return false;
        if (usage != null ? !usage.equals(roomData.usage) : roomData.usage != null) return false;
        return location == roomData.location;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (usage != null ? usage.hashCode() : 0);
        result = 31 * result + maxStudents;
        result = 31 * result + idRoom;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (flagLIM ? 1 : 0);
        result = 31 * result + (flagPRESACORRENTE ? 1 : 0);
        result = 31 * result + progressive;
        return result;
    }
}
