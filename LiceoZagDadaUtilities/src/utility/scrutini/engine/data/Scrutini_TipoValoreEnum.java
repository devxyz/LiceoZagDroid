package utility.scrutini.engine.data;

public enum Scrutini_TipoValoreEnum {
    VOTO() {
        @Override
        boolean accept(String materia, String tipologia) {
            return tipologia.equalsIgnoreCase("O") || tipologia.equalsIgnoreCase("U");
        }
    },
    ASSENZA() {
        @Override
        boolean accept(String materia, String tipologia) {
            return tipologia.equalsIgnoreCase("Ass");
        }
    },
    ESITO_FINALE() {
        @Override
        boolean accept(String materia, String tipologia) {
            return
                    materia.trim().equalsIgnoreCase("ESITO FINALE")||
                    materia.trim().equalsIgnoreCase("ESITO AMMISSIONE")
                    ;
        }
    },
    COMPORTAMENTO() {
        @Override
        boolean accept(String materia, String tipologia) {
            return materia.trim().equalsIgnoreCase("COMPORTAMENTO");
        }
    },
    CREDITO() {
        @Override
        boolean accept(String materia, String tipologia) {
            return materia.trim().equalsIgnoreCase("CREDITO");
        }
    },
    CREDITO_ANNI_PREC() {
        @Override
        boolean accept(String materia, String tipologia) {
            return materia.trim().equalsIgnoreCase("Credito anni precedenti");
        }
    },
    ALUNNO() {
        @Override
        boolean accept(String materia, String tipologia) {
            return materia.trim().equalsIgnoreCase("ALUNNI");
        }
    },
    RECUPERO_DEBITI() {
        @Override
        boolean accept(String materia, String tipologia) {
            return false;
        }
    },
    SCONOSCIUTO() {
        @Override
        boolean accept(String materia, String tipologia) {
            return false;
        }
    },
    ;

    abstract boolean accept(String materia, String tipologia);


    Scrutini_TipoValoreEnum() {
    }

    public static Scrutini_TipoValoreEnum searchBy(String materia, String tipologia) {
        for (Scrutini_TipoValoreEnum value : values()) {
            if (value.accept(materia, tipologia)) return value;
        }
        return SCONOSCIUTO;
    }
}
