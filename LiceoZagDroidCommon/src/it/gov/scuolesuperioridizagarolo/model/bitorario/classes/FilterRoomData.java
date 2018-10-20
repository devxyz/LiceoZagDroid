package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 19/10/2018.
 */
public abstract class FilterRoomData {
    public final RoomData[] filterBy() {
        Set<RoomData> ris = new TreeSet<>();
        for (RoomData roomData : RoomData.values()) {
            if (accept(roomData)) {
                ris.add(roomData);
            }
        }
        return ris.toArray(new RoomData[ris.size()]);
    }

    protected abstract boolean accept(RoomData r);
}
