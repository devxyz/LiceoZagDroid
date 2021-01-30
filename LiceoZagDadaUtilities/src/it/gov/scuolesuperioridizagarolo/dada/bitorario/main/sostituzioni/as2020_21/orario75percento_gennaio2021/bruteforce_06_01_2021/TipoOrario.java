package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

enum TipoOrario {
    GIORNO_LIBERO(" - "),
    DAD("DAD"),
    ORE8("P08"),
    ORE10("P10"),
    ;
    final String descrizione;

    TipoOrario(String s) {
        this.descrizione = s;
    }

}
