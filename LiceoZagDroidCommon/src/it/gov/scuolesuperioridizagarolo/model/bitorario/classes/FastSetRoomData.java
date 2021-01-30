package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.Iterator;

public interface FastSetRoomData extends Iterable<RoomData> {
    void useRoom(RoomData r);

    void freeRoom(RoomData r);

    boolean isRoomUsed(RoomData r);

    String toStringAule();

    @Override
    Iterator<RoomData> iterator();
}
