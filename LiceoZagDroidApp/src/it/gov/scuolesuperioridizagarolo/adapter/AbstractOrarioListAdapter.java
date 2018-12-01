package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_orario_aula_lezione_xml;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by stefano on 17/12/2017.
 */
public abstract class AbstractOrarioListAdapter extends BaseAdapter {
    protected final Activity a;
    private final boolean printInsegnante;
    private final boolean printInsegnanteSeCompresenza;
    private final boolean printClasse;
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected OnlyDate giorno;
    private BitOrarioGrigliaOrario orario;
    private BitOrarioGrigliaOrario orarioDefault;
    private ArrayList<ContainerBitOrarioOraLezione> lezioniOrario = new ArrayList<>();

    public AbstractOrarioListAdapter(Activity a, BitOrarioGrigliaOrarioContainer containerOrari, OnlyDate giorno, boolean printInsegnante, boolean printClasse, boolean printInsegnanteSeCompresenza) {
        this.a = a;
        this.containerOrari = containerOrari;
        this.giorno = giorno;
        this.printInsegnante = printInsegnante;
        this.printClasse = printClasse;
        this.printInsegnanteSeCompresenza = printInsegnanteSeCompresenza;


        __updateInternalData();


    }

    public static void coloraViewAula(TextView textViewAula, ERoomArea location, Context a) {
        if (location == null) {
            textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_disposizione));
            textViewAula.setTextColor(a.getResources().getColor(R.color.color_white));
            return;
        }
        switch (location) {
            case AREA_A: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_a));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_a_foreground));
                break;
            }
            case AREA_B: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_b));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_b_foreground));
                break;
            }
            case AREA_C: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_c));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_c_foreground));
                break;
            }
            case AREA_D: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_d));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_d_foreground));
                break;
            }
            case AREA_E: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_e));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_e_foreground));
                break;
            }
            case AREA_F: {
                textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_f));
                textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_f_foreground));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void __caricaLezioniDaOrario(BitOrarioGrigliaOrario o, EGiorno giorno, ArrayList<ContainerBitOrarioOraLezione> lezioniOrario) {
        for (EOra ora : EOra.values()) {
            if (!ora.flagOraDiLezione()) {
                lezioniOrario.add(new ContainerBitOrarioOraLezione(ora, null));
            } else {

                final List<BitOrarioOraLezione> lezioni = getItem(o, giorno, ora);
                for (BitOrarioOraLezione l : lezioni) {
                    lezioniOrario.add(new ContainerBitOrarioOraLezione(ora, l));
                }
            }
        }
    }

    public abstract List<BitOrarioOraLezione> getItem(BitOrarioGrigliaOrario o, EGiorno giorno, EOra ora);

    /**
     * true se risulta un cambiamento di aula
     *
     * @return
     */
    public boolean cambiamentoAula(BitOrarioOraLezione lezione, BitOrarioOraLezione lezioneDefault) {
        final boolean cambioAula = !Objects.equals(lezione, lezioneDefault);
        return cambioAula;
    }

    public String getDetails(int position) {
        final BitOrarioOraLezione item = getItem(position).lezione;

        StringBuilder info = new StringBuilder();

        final String note = item == null || item.getNote() == null ? "" : (item.getNote() + "\n");
        if (item == null || item.getAula() == null) {
            if (note.trim().length() > 0)
                return "Note: " + note;
            else
                return null;
        }


        final RoomData room = item.getAula();
        if (room != null) {

            info.append("Aula: ").append(room.simpleName()).append("\n");
            info.append(" - ").append(room.usage).append(room.flagLIM ? " con LIM" : "").append("\n");
            info.append(" - ").append(room.maxStudents).append(" posti").append("\n");

            info.append(" - ").append(room.location == null ? "-" : room.location.description);
            if (note.trim().length() > 0) {
                info.append("\n - note: ").append(note);
            }
            return info.toString();
        }
        return null;
    }

    public void updateOrario(BitOrarioGrigliaOrarioContainer o) {
        this.containerOrari = o;
        __updateInternalData();
        notifyDataSetChanged();
    }

    public final BitOrarioGrigliaOrarioContainer getContainerOrari() {
        return containerOrari;
    }

    public OnlyDate getGiorno() {
        return giorno;
    }

    public final void setGiorno(OnlyDate g) {
        giorno = g;
        __updateInternalData();
        notifyDataSetChanged();
    }

    private void __updateInternalData() {
        orario = containerOrari.getOrario(giorno);
        orarioDefault = containerOrari.getOrarioDefault();


        lezioniOrario.clear();

        __caricaLezioniDaOrario(orario, giorno.getGiorno(), lezioniOrario);


    }

    @Override
    public final int getCount() {

        return lezioniOrario.size();
    }

    @Override
    public final ContainerBitOrarioOraLezione getItem(int position) {
        return lezioniOrario.get(position);
        //return getItem(orario, position);
    }


    @Override
    public final long getItemId(int position) {
        return position;
    }

    /**
     * ritorna la lezione di default corrispondente
     *
     * @param l
     * @return
     */
    private BitOrarioOraLezione getDefaultCorrelatedLesson(BitOrarioOraLezione l) {
        if (l == null) return null;
        final ClassData classe = l.getClasse();
        if (classe == null) return null;
        final EGiorno giorno = l.getGiorno();
        if (giorno == null) return null;
        final EOra ora = l.getOra();
        if (ora == null) return null;
        return orarioDefault.getLezioneInClasse(ora, giorno, classe);
    }

    // create a new ImageView for each item referenced by the Adapter
    public final View getView(int position, View convertView, ViewGroup parent) {
        //orario


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_orario_aula_lezione, null);
        }
        final LayoutObjs_listview_orario_aula_lezione_xml o = new LayoutObjs_listview_orario_aula_lezione_xml(convertView);

        final ContainerBitOrarioOraLezione item = getItem(position);
        final BitOrarioOraLezione lezione = item.lezione;

        //final BitOrarioOraLezione lezioneDefault = getDefaultCorrelatedLesson(lezione);

        final EOra ora = item.ora;


        //RESTORE
        {
            o.textViewFasciaOraria.setText("");
            o.textViewLezione.setText("");
            o.textViewDocenteClasse.setText("");
            o.textViewOra.setText("");
            o.textViewAula.setText("");

            o.layout.setBackground(null);

            o.textViewDocenteClasse.setVisibility(View.VISIBLE);
            o.textViewFasciaOraria.setVisibility(View.VISIBLE);
            o.textViewLezione.setVisibility(View.VISIBLE);
            o.textViewOra.setVisibility(View.VISIBLE);
            o.textViewAula.setVisibility(View.VISIBLE);


            o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_black));
            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_black));

            o.textViewLezione.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));
            o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_black));
        }


        String note = lezione == null ? null : lezione.getNote();

        /*
        if (cambiamentoAula(lezione,lezioneDefault)) {
            o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_red));
            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_red));

            String msg = "Lezione modificata rispetto all'originale:\n -> " + (lezioneDefault == null ? "-" : lezioneDefault.toStringShort());

            if (note == null) {
                note = msg;
            } else {
                note = note + "\n\n" + msg;
            }
        }
        */


        //todo debug
        // note += " xxxxx x x x x x x xxxxx x xx x x x ";

        if (!ora.flagOraDiLezione()) {
            //NON ORA DI LEZIONE ma inizio/fine giornata


            o.textViewLezione.setText("" + ora.name() + " alle ore " + ora.printOra());
            o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_red));

            o.textViewDocenteClasse.setVisibility(View.GONE);
            o.textViewFasciaOraria.setVisibility(View.GONE);
            o.textViewOra.setVisibility(View.GONE);
            o.textViewAula.setVisibility(View.GONE);

            if (ora.isNowHour() && giorno.isToday()) {
                o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente));
            }
        } else {


            if (lezione == null) {
                if (ora.isNowHour() && giorno.isToday()) {
                    o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente_vuota));
                }

                //se ora NULLA
                o.textViewOra.setText(ora.getProgressivOra() + "° ora");
                o.textViewFasciaOraria.setText(ora.fascia());
                o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));

            } else if (lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {

                if (ora.isNowHour() && giorno.isToday()) {
                    o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente));
                }

                //SE ORA DISPOSIZIONE
                o.textViewOra.setText(ora.getProgressivOra() + "° ora");
                o.textViewFasciaOraria.setText(ora.fascia());

                o.textViewDocenteClasse.setText("");
                o.textViewLezione.setText(C_TextUtil.capitalize("Disposizione"));
                o.textViewAula.setText(C_TextUtil.capitalize("DISP"));

                o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_disposizione));
                o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_yellow));


            } else {

                if (ora.isNowHour() && giorno.isToday()) {
                    o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente));
                }

                //ORA NORMALE
                o.textViewOra.setText(ora.getProgressivOra() + "° ora");
                o.textViewFasciaOraria.setText(ora.fascia());


                ClassData classe = lezione.getClasse();
                String insegnanti;


                if (lezione.getDocenti().size() > 0)
                    insegnanti = C_TextUtil.capitalize(lezione.getDocentiFormatted());
                else
                    insegnanti = C_TextUtil.capitalize(lezione.getDocentePrincipale());

                StringBuilder sb = new StringBuilder();
                if (lezione.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                    if (printClasse) {
                        if (sb.length() > 0)
                            sb.append(" - ");
                        sb.append(classe);
                    }
                    if (printInsegnante || (lezione.getDocenti().size() > 1 && printInsegnanteSeCompresenza)) {
                        if (sb.length() > 0)
                            sb.append(" - ");
                        sb.append(insegnanti);
                    }

                    o.textViewDocenteClasse.setText(sb.toString().trim());
                    o.textViewLezione.setText(C_TextUtil.capitalize(lezione.getMateriaPrincipale().replace("_", " ")));
                }


                //disegno aula
                RoomData nomeAula = lezione.getAula();
                final RoomData room;
                if (nomeAula == null) {


                    o.textViewAula.setText("--");
                    coloraViewAula(o.textViewAula, null, a);


                } else {
                    room = (nomeAula);
                    o.textViewAula.setText(nomeAula.simpleName());

                    final ERoomArea location = room.location;
                    coloraViewAula(o.textViewAula, location, a);


                }
            }
        }

        //eventuali note
        if (note != null) {
            o.textView_Note.setText((note).trim());
            o.textView_Note.setVisibility(View.VISIBLE);
        } else {
            o.textView_Note.setVisibility(View.GONE);
        }


        return convertView;
    }

    public static class ContainerBitOrarioOraLezione {
        public final EOra ora;
        public final BitOrarioOraLezione lezione;

        private ContainerBitOrarioOraLezione(EOra ora, BitOrarioOraLezione lezione) {
            this.ora = ora;
            this.lezione = lezione;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ContainerBitOrarioOraLezione that = (ContainerBitOrarioOraLezione) o;

            if (ora != that.ora) return false;
            return lezione != null ? lezione.equals(that.lezione) : that.lezione == null;

        }

        @Override
        public int hashCode() {
            int result = ora != null ? ora.hashCode() : 0;
            result = 31 * result + (lezione != null ? lezione.hashCode() : 0);
            return result;
        }
    }


}
