package utility.scrutini.engine.data;

import java.util.TreeMap;

public class DatiStudente {

    private String nome;
    private String esito="NON PRESENTE";
    private Integer voto_comportamento;
    private TreeMap<String, ScrutinioMateria> materie = new TreeMap<>();


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

    @Override
    public String toString() {
        return "DatiStudente{" +
                "nome='" + nome + '\'' +
                ", esito='" + esito + '\'' +
                ", voto_comportamento=" + voto_comportamento +
                ", materie=" + materie +
                '}';
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
    }
}
