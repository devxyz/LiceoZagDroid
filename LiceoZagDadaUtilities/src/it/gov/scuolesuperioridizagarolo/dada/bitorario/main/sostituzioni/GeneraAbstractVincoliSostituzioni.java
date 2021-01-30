package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.io.IOException;

public class GeneraAbstractVincoliSostituzioni {

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
}
