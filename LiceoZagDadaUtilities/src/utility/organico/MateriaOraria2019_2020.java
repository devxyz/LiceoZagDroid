package utility.organico;

public class MateriaOraria2019_2020 implements Comparable<MateriaOraria2019_2020> {
    public final Classi2019_2020 classe;
    public final Materia2019_2020 materia;
    public int ore;
    private String note;


    public MateriaOraria2019_2020(Classi2019_2020 classe, Materia2019_2020 materia, int ore, String note) {
        this.classe = classe;
        this.materia = materia;
        this.ore = ore;
        this.note = note;
    }

    public MateriaOraria2019_2020(Classi2019_2020 classe, Materia2019_2020 materia, int ore) {
        this.classe = classe;
        this.materia = materia;
        this.ore = ore;
        note = null;
    }

    @Override
    public String toString() {
        return "MateriaOraria2019_2020{" +
                "classe=" + classe +
                ", materia=" + materia +
                ", ore=" + ore +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public int compareTo(MateriaOraria2019_2020 o) {
        int i = new Integer(ore).compareTo(o.ore);
        if (i != 0) return i;
        return classe.compareTo(o.classe);
    }
}
