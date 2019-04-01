package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.corso_sicurezza;

public class StudenteCorsoSicurezza {
    public String nomeStudente;
    public String cognomeStudente;
    public String codicefiscaleStudente;
    public String nomeCorsoEsteso;
    public String progresso;
    public String idcorso;
    public String stato;

    public StudenteCorsoSicurezza(String nomeStudente, String cognomeStudente, String codicefiscaleStudente, String nomeCorsoEsteso, String progresso, String idcorso, String stato) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.codicefiscaleStudente = codicefiscaleStudente;
        this.nomeCorsoEsteso = nomeCorsoEsteso;
        this.progresso = progresso;
        this.idcorso = idcorso;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "StudenteCorsoSicurezza{" +
                "nomeStudente='" + nomeStudente + '\'' +
                ", cognomeStudente='" + cognomeStudente + '\'' +
                ", codicefiscaleStudente='" + codicefiscaleStudente + '\'' +
                ", nomeCorsoEsteso='" + nomeCorsoEsteso + '\'' +
                ", progresso='" + progresso + '\'' +
                ", idcorso='" + idcorso + '\'' +
                ", stato='" + stato + '\'' +
                '}';
    }

    public static String toExcelHeader() {
        return "nomeStudente;" +
                "cognomeStudente;" +
                "codicefiscaleStudente;" +
                "nomeCorsoEsteso;" +
                "progresso;" +
                "idcorso;" +
                "stato";
    }

    public String toExcel() {
        return nomeStudente + ';' +
                cognomeStudente + ';' +
                codicefiscaleStudente + ';' +
                nomeCorsoEsteso + ';' +
                progresso + ';' +
                idcorso + ';' +
                stato;
    }
}
