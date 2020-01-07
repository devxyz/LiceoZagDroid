package utility.corso_sicurezza;

public class StudenteSidi {
    public String CodiceAlunno;
    public String CodiceFiscale;
    public String Cognome;
    public String Nome;
    public String AnnoCorso;
    public String Sezione;
    public String Scuola;
    public String Sede;
    public String StatoAlunno;//T trasferito, F frequenta

    @Override
    public String toString() {
        return "StudenteSidi{" +
                "CodiceAlunno='" + CodiceAlunno + '\'' +
                ", CodiceFiscale='" + CodiceFiscale + '\'' +
                ", Cognome='" + Cognome + '\'' +
                ", Nome='" + Nome + '\'' +
                ", AnnoCorso='" + AnnoCorso + '\'' +
                ", Sezione='" + Sezione + '\'' +
                ", Scuola='" + Scuola + '\'' +
                '}';
    }

    public StudenteSidi(String codiceAlunno, String codiceFiscale, String cognome, String nome, String annoCorso, String sezione, String scuola, String sede,String statoAlunno) {
        CodiceAlunno = codiceAlunno;
        CodiceFiscale = codiceFiscale;
        Cognome = cognome;
        Nome = nome;
        AnnoCorso = annoCorso;
        Sezione = sezione;
        Scuola = scuola;
        Sede = sede;
        StatoAlunno = statoAlunno;
    }


    public static String toExcelHeader() {
        return "CodiceFiscale;"  +
                "CodiceAlunno;" +
                "Nome;" +
                "Cognome;" +
                "AnnoCorso;" +
                "Sezione;" +
                "Scuola;" +
                "Sede;"+
                "StatoAlunno"
                ;
    }

    public String toExcel() {
        return CodiceFiscale + ';' +
                CodiceAlunno + ';' +
                Nome + ';' +
                Cognome + ';' +
                AnnoCorso + ';' +
                Sezione + ';' +
                Scuola + ';' +
                Sede + ';' +
                StatoAlunno
                ;
    }

}
