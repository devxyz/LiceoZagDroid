package it.gov.scuolesuperioridizagarolo.model.bitorario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

/**
 * utility di gestione leioni (ignorando i vincoli dell'orario)
 */
public class BitOrarioOraLezioneManipulation {
    public static void setAula(BitOrarioOraLezione l, RoomData a) {
        l.aula = a;
    }

    public static BitOrarioOraLezione clonaFuoriOrario(BitOrarioOraLezione l) {
        BitOrarioOraLezione clone = l.clone();
        return clone;
    }

}
