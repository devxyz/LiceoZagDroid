package utility.scrutini.engine.data;

import java.util.TreeMap;

public class DatiStudente {

    private String nome;

    public String getClasse() {
        return classe;
    }

    public int getClasseNumero() {
        return Integer.parseInt(classe.charAt(0) + "");
    }

    public char getClasseSezione() {
        return classe.charAt(2);
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    private String classe;
    private String esito = "NON PRESENTE";

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public void setCredito(String credito) {
        if(credito==null)return;
        String trim = credito.trim();
        if(trim.length()==0)return;
        this.credito = Integer.parseInt(trim);
    }

    private int credito = 0;
    private Integer voto_comportamento;
    private TreeMap<String, ScrutinioMateria> materie = new TreeMap<>();

    public EsitoScrutinioEnum getEsitoEnum() {
        return EsitoScrutinioEnum.searchByEtichetta(esito);
    }


    public ScrutinioMateria get(String materia) {
        ScrutinioMateria m = materie.get(materia);
        if (m == null) {
            m = new ScrutinioMateria();
            materie.put(materia, m);
        }
        return m;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "DatiStudente{" +
                "nome='" + nome + '\'' +
                ", classe='" + classe + '\'' +
                ", esito='" + esito + '\'' +
                ", voto_comportamento=" + voto_comportamento +
                ", materie=" + materie +
                '}';
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public Integer getVoto_comportamento() {
        return voto_comportamento;
    }

    public void setVoto_comportamento(Integer voto_comportamento) {
        this.voto_comportamento = voto_comportamento;
    }

    public TreeMap<String, ScrutinioMateria> getMaterie() {
        return materie;
    }

    public static class ScrutinioMateria {
        public Integer voto;
        public String votoGiudizio;
        public Integer assenze;
        public String modalitaRecupero;

        private ScrutinioMateria() {
            this.voto = null;
            this.assenze = null;
        }

        @Override
        public String toString() {
            return "ScrutinioMateria{" +
                    "voto=" + voto +
                    ", votoGiudizio='" + votoGiudizio + '\'' +
                    ", assenze=" + assenze +
                    ", modalitaRecupero='" + modalitaRecupero + '\'' +
                    '}';
        }
    }
}
