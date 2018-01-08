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
import dada.bitorario.data.enum_values.EOra;
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

    public String getDetails(int position) {
        final BitOrarioOraLezione item = getItem(position);
        final BitOrarioOraLezione itemDefault = getItem(orarioDefault, position);
        final boolean cambioAula = !Objects.equals(item, itemDefault);
        StringBuilder info = new StringBuilder();
        if (item == null || item.getNomeAula() == null) {
            if (cambioAula)
                return ("ATTENZIONE: Questa lezione risulta modificata rispetto all'orario standard per esigenze didattiche/logistiche." +
                        "\nLezione predefinita:" + (itemDefault == null ? null : itemDefault.toStringShort())
                );
            else
                return null;
        }


        final RoomData room = ClassesAndRoomContainer.getRoom(item.getNomeAula());
        if (room != null) {

            info.append("Aula: ").append(room.name.split("_")[0]);
            info.append("Tipologia: ").append(room.flagSpecial() ? "Aula Attrezzata" : "Aula didattica");
            info.append("\nLIM: ").append(room.flagLIM ? "SI" : "NO");
            info.append("\nDove si trova:").append(room.location == null ? "-" : room.location.description);
            if (cambioAula) {
                info.append("\n\nATTENZIONE: Questa lezione risulta modificata rispetto all'orario standard per esigenze didattiche/logistiche." + "\nLezione predefinita:").
                        append(itemDefault == null ? null : itemDefault.toStringShort());
            }
            return info.toString();
        }
        return null;
    }

    public void updateOrario(BitOrarioGrigliaOrarioContainer orario) {
        this.containerOrari = orario;
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
