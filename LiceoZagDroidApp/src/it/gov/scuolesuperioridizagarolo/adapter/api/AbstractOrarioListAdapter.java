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
    protected  BitOrarioGrigliaOrario orario;
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

    public void updateOrario(BitOrarioGrigliaOrario orario){
        this.orario = orario;
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

            o.textViewLezione.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));
        }


        if (!ora.flagOraDiLezione()) {
            //NON ORA DI LEZIONE ma inizio/fine giornata


            o.textViewLezione.setText("" + ora.name() + " alle ore " + ora.printOra());
            o.textViewLezione.setTextColor(a.getResources().getColor(R.color.color_red));

            o.textViewDocenteClasse.setVisibility(View.GONE);
            o.textViewFasciaOraria.setVisibility(View.GONE);
            o.textViewOra.setVisibility(View.GONE);
            o.textViewAula.setVisibility(View.GONE);

            if (ora.isActive() && giorno.isToday()) {
                o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente));
            }


        } else {


            if (item == null) {
                if (ora.isActive() && giorno.isToday()) {
                    o.layout.setBackground(a.getResources().getDrawable(R.drawable.listview_orario_border_ora_corrente_vuota));
                }

                //se ora NULLA
                o.textViewOra.setText(ora.getProgressivOra() + "° ora");
                o.textViewFasciaOraria.setText(ora.fascia());
                o.textViewAula.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));

            } else if (item.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {

                if (ora.isActive() && giorno.isToday()) {
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

                if (ora.isActive() && giorno.isToday()) {
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

                    switch (room.location) {
                        case AREA_A: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_a));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_a_foreground));
                            break;
                        }
                        case AREA_B: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_b));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_b_foreground));
                            break;
                        }
                        case AREA_C: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_c));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_c_foreground));
                            break;
                        }
                        case AREA_D: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_d));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_d_foreground));
                            break;
                        }
                        case AREA_E: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_e));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_e_foreground));
                            break;
                        }
                        case AREA_F: {
                            o.textViewAula.setBackground(a.getResources().getDrawable(R.drawable.background_aule_f));
                            o.textViewAula.setTextColor(a.getResources().getColor(R.color.color_aule_f_foreground));
                            break;
                        }
                        default: {
                            break;
                        }
                    }


                }
            }
        }


        return convertView;
    }


}
