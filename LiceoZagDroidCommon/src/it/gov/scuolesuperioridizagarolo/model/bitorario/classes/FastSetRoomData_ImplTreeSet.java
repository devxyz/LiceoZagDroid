package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class FastSetRoomData_ImplTreeSet implements FastSetRoomData {
    private final TreeSet<RoomData> usedRooms=new TreeSet<>();

    @Override
    public void useRoom(RoomData r) {
        usedRooms.add(r);
    }

    @Override
    public void freeRoom(RoomData r) {
        usedRooms.remove(r);
    }

    @Override
    public boolean isRoomUsed(RoomData r) {
        return usedRooms.contains(r);
    }

    public String toString() {
        return usedRooms.toString();
    }

    @Override
    public String toStringAule() {
        ArrayList<String> ss = new ArrayList<>();
        for (RoomData s : RoomData.values()) {
            if (isRoomUsed(s)) {
                ss.add(s.roomName);
            }
        }

        return ss.toString();
    }

    private static String printBit(long x) {
        return String.format("%64s", Long.toBinaryString(x)).replaceAll(" ", "0");
    }

    public static void main(String[] args) {
        FastSetRoomData f = new FastSetRoomData_ImplTreeSet();
        f.useRoom(RoomData.A1);
        f.useRoom(RoomData.A2);
        f.freeRoom(RoomData.A2);
        System.out.println(f);
        System.out.println(f.toStringAule());

        System.out.println("=======================================");
        for (RoomData xx : f) {
            System.out.println(xx);
        }
    }

    @Override
    public Iterator<RoomData> iterator() {
        Iterator<RoomData> ris = new RoomDataIterator();
        return ris;
    }

    private class RoomDataIterator implements Iterator<RoomData> {
        int index = -1;


        public RoomDataIterator() {
            RoomData[] values = RoomData.values();
            for (int i = 0; i < values.length; i++) {
                RoomData value = values[i];
                if (isRoomUsed(value)) {
                    index = i;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index >= 0 && index < RoomData.values().length;
        }

        @Override
        public RoomData next() {
            if (!hasNext())
                throw new NoSuchElementException();
            RoomData[] array = RoomData.values();
            RoomData val = array[index];
            index++;

            while (index < array.length) {

                RoomData value = array[index];
                if (isRoomUsed(value)) {
                    break;
                }
                index++;
            }
            return val;
        }

        @Override
        public void remove() {
            if (!hasNext())
                throw new NoSuchElementException();
            FastSetRoomData_ImplTreeSet.this.freeRoom(RoomData.values()[index]);
        }
    }
}
