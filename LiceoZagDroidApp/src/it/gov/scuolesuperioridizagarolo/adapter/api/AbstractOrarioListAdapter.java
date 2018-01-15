package it.gov.scuolesuperioridizagarolo.adapter.api;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraEnumTipoLezione;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.classes.ClassesAndRoomContainer;
import dada.bitorario.data.classes.RoomData;
import dada.bitorario.data.enum_values.EOra;
import dada.bitorario.data.enum_values.ERoomArea;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_orario_aula_lezione_xml;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

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

    public AbstractOrarioListAdapter(Activity a, BitOrarioGrigliaOrarioContainer containerOrari, OnlyDate giorno, boolean printInsegnante, boolean printClasse, boolean printInsegnanteSeCompresenza) {
        this.a = a;
        this.containerOrari = containerOrari;
        this.giorno = giorno;
        this.printInsegnante = printInsegnante;
        this.printClasse = printClasse;
        this.printInsegnanteSeCompresenza = printInsegnanteSeCompresenza;
        this.orario = containerOrari.getOrario(giorno);
    }

    public static void coloraViewAula(TextView textViewAula, ERoomArea location, Context a) {
        if (location == null) {
            textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_white));
            textViewAula.setTextColor(a.getResources().getColor(R.color.color_black));
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

    /**
     * true se risulta un cambiamento di aula
     *
     * @param position
     * @return
     */
    public boolean cambiamentoAula(int position) {
        final BitOrarioOraLezione item = getItem(position);
        final BitOrarioOraLezione itemDefault = getItem(orarioDefault, position);
        final boolean cambioAula = !Objects.equals(item, itemDefault);
        return cambioAula;
    }

    public String getDetails(int position) {
        final BitOrarioOraLezione item = getItem(position);
        final BitOrarioOraLezione itemDefault = getItem(orarioDefault, position);

        StringBuilder info = new StringBuilder();

        final String note = item == null ? "" : item.getNote();
        if (item == null || item.getNomeAula() == null) {
            if (note != null && note.trim().length() > 0)
                return "Note: " + note;
            else
                return null;
        }


        final RoomData room = ClassesAndRoomContainer.getRoom(item.getNomeAula());
        if (room != null) {

            info.append("Aula: ").append(room.simpleName()).append("\n");
            info.append(" - ").append(room.usage).append(room.flagLIM ? " con LIM" : "").append("\n");
            info.append(" - ").append(room.maxStudents).append(" posti").append("\n");

            info.append(" - ").append(room.location == null ? "-" : room.location.description);
            if (note != null && note.trim().length() > 0) {
                info.append("\n - note: ").append(note);
            }
            return info.toString();
        }
        return null;
    }

    public void updateOrario(BitOrarioGrigliaOrarioContainer o) {
        this.containerOrari = o;
        orario = containerOrari.getOrario(giorno);
        orarioDefault = containerOrari.getOrarioDefault();

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
        orario = containerOrari.getOrario(g);
        orarioDefault = containerOrari.getOrarioDefault();
        super.notifyDataSetChanged();
    }

    @Override
    public final int getCount() {

        return EOra.values().length;
    }

    @Override
    public final BitOrarioOraLezione getItem(int position) {
        return getItem(orario, position);
    }

    protected abstract BitOrarioOraLezione getItem(BitOrarioGrigliaOrario o, int position);

    @Override
    public final long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public final View getView(int position, View convertView, ViewGroup parent) {
        //orario


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_orario_aula_lezione, null);
        }
        final LayoutObjs_listview_orario_aula_lezione_xml o = new LayoutObjs_listview_orario_aula_lezione_xml(convertView);
        final BitOrarioOraLezione item = getItem(position);
        final BitOrarioOraLezione itemDefault = getItem(orarioDefault, position);

        final boolean cambioAula = !Objects.equals(item, itemDefault);


        final EOra ora = EOra.values()[position];


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

        if (cambioAula) {
            o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_red));
            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_red));
        }


        String note = item == null ? null : item.getNote();

        if (cambiamentoAula(position)) {

            String msg = "Questa lezione risulta modificata rispetto all'orario standard per esigenze didattiche/logistiche.\nOriginale: " + itemDefault.toStringShort();

            if (note == null) {
                note = msg;
            } else {
                note = note + "\n" + msg;
            }
        }


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


            if (item == null) {
                if (ora.isNowHour() && giorno.isToday()) {
                    o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente_vuota));
                }

                //se ora NULLA
                o.textViewOra.setText(ora.getProgressivOra() + "° ora");
                o.textViewFasciaOraria.setText(ora.fascia());
                o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));

            } else if (item.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {

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


                String classe = item.getClasse();
                String insegnanti;


                if (item.getDocenteCompresenza() != null)
                    insegnanti = C_TextUtil.capitalize(item.getDocentePrincipale() + " - " + item.getDocenteCompresenza());
                else
                    insegnanti = C_TextUtil.capitalize(item.getDocentePrincipale());

                StringBuilder sb = new StringBuilder();
                if (item.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                    if (printClasse) {
                        if (sb.length() > 0)
                            sb.append(" - ");
                        sb.append(classe);
                    }
                    if (printInsegnante || (item.getDocenteCompresenza() != null && printInsegnanteSeCompresenza)) {
                        if (sb.length() > 0)
                            sb.append(" - ");
                        sb.append(insegnanti);
                    }

                    o.textViewDocenteClasse.setText(sb.toString().trim());
                    o.textViewLezione.setText(C_TextUtil.capitalize(item.getMateriaPrincipale().replace("_", " ")));
                }


                //disegno aula
                String nomeAula = item.getNomeAula();
                final RoomData room;
                if (nomeAula == null || nomeAula.length() == 0) {


                    o.textViewAula.setText("");
                    o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_white));
                    o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_white));


                } else {
                    room = ClassesAndRoomContainer.getRoom(nomeAula);
                    o.textViewAula.setText(nomeAula.split("_")[0]);

                    final ERoomArea location = room.location;
                    coloraViewAula(o.textViewAula, location, a);


                }
            }
        }

        //eventuali note
        if (note != null) {
            o.textView_Note.setText((o.textViewDocenteClasse.getText() + " " + note).trim());
            o.textView_Note.setVisibility(View.VISIBLE);
        } else {
            o.textView_Note.setVisibility(View.GONE);
        }


        return convertView;
    }


}
