package it.gov.scuolesuperioridizagarolo.adapter.api;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraEnumTipoLezione;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.classes.ClassesAndRoomContainer;
import dada.bitorario.data.classes.RoomData;
import dada.bitorario.data.enum_values.EGiorno;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_orario_aula_lezione_xml;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

/**
 * Created by stefano on 17/12/2017.
 */
public abstract class AbstractOrarioListAdapter extends BaseAdapter {
    protected final Activity a;
    protected final BitOrarioGrigliaOrario orario;
    private final boolean printInsegnante;
    private final boolean printInsegnanteSeCompresenza;
    private final boolean printClasse;
    protected EGiorno giorno;

    public AbstractOrarioListAdapter(Activity a, BitOrarioGrigliaOrario orario, EGiorno giorno, boolean printInsegnante, boolean printClasse, boolean printInsegnanteSeCompresenza) {
        this.a = a;
        this.orario = orario;
        this.giorno = giorno;
        this.printInsegnante = printInsegnante;
        this.printClasse = printClasse;
        this.printInsegnanteSeCompresenza = printInsegnanteSeCompresenza;
    }


    public final BitOrarioGrigliaOrario getOrario() {
        return orario;
    }

    public EGiorno getGiorno() {
        return giorno;
    }

    public final void setGiorno(EGiorno g) {
        giorno = g;
        super.notifyDataSetChanged();
    }

    @Override
    public final int getCount() {

        return EOra.values().length;
    }

    @Override
    public abstract BitOrarioOraLezione getItem(int position);

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

        final EOra ora = EOra.values()[position];

        o.textViewOra.setText(ora.getProgressivOra() + "° ora");


        o.textViewFasciaOraria.setText(ora.fascia());


        if (item != null) {

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
            } else {
                o.textViewDocenteClasse.setText("");
                o.textViewLezione.setText(C_TextUtil.capitalize("Disposizione"));
            }


            //disegno aula
            String nomeAula = item.getNomeAula();
            final RoomData room;
            if (nomeAula == null || nomeAula.length() == 0) {

                if (item.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                    o.textViewAula.setText("#");
                    o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_black));
                    o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_white));
                } else {
                    o.textViewAula.setText("");
                    o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_white));
                    o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_white));
                }


            } else {
                room = ClassesAndRoomContainer.getRoom(nomeAula);
                o.textViewAula.setText(nomeAula.split("_")[0]);
                switch (room.location) {
                    case AREA_A: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_a_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_a_foreground));
                        break;
                    }
                    case AREA_B: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_b_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_b_foreground));
                        break;
                    }
                    case AREA_C: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_c_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_c_foreground));
                        break;
                    }
                    case AREA_D: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_d_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_d_foreground));
                        break;
                    }
                    case AREA_E: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_e_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_e_foreground));
                        break;
                    }
                    case AREA_F: {
                        o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_aule_f_background));
                        o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_f_foreground));
                        break;
                    }
                    default: {
                        break;
                    }
                }


            }


            if (ora.isActive() && giorno.isToday()) {
                o.layout.setBackgroundColor(a.getResources().getColor(R.color.color_ora_corrente));
                o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_red));
                o.textViewFasciaOraria.setTextColor(a.getResources().getColor(R.color.color_red));
            } else {
                o.layout.setBackgroundColor(a.getResources().getColor(R.color.color_white));
                o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_black));
                o.textViewFasciaOraria.setTextColor(a.getResources().getColor(R.color.color_black));
            }

        } else {
            o.textViewOra.setTextColor(a.getResources().getColor(R.color.color_black));
            o.textViewFasciaOraria.setTextColor(a.getResources().getColor(R.color.color_black));

            o.textViewAula.setText("");
            o.textViewDocenteClasse.setText("");
            o.textViewLezione.setText("");
            o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));
            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_black));

            o.layout.setBackgroundColor(a.getResources().getColor(R.color.color_gray));

        }

        if (!ora.flagOraDiLezione()) {
            o.textViewOra.setText("");
            o.textViewFasciaOraria.setText("");
            o.textViewLezione.setText("" + ora.name() + " alle ore " + ora.printOra());

            o.textViewDocenteClasse.setVisibility(View.GONE);
            o.textViewFasciaOraria.setVisibility(View.GONE);
            o.textViewOra.setVisibility(View.GONE);
            o.textViewAula.setVisibility(View.GONE);

            if (ora.isActive() && giorno.isToday()) {
                o.layout.setBackgroundColor(a.getResources().getColor(R.color.color_ora_corrente));
                o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_white));
            } else {
                o.layout.setBackgroundColor(a.getResources().getColor(R.color.color_white));
                o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_ora_corrente));
            }

        } else {
            o.textViewDocenteClasse.setVisibility(View.VISIBLE);
            o.textViewFasciaOraria.setVisibility(View.VISIBLE);
            o.textViewOra.setVisibility(View.VISIBLE);
            o.textViewAula.setVisibility(View.VISIBLE);

            o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_black));
        }


        return convertView;
    }


}
