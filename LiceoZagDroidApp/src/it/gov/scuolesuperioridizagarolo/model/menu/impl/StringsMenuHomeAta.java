package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import java.util.*;

import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuHomeAta{
     public static final DataMenuInfo CONTATTI= new DataMenuInfo(
"Contatti",
"Contatta la scuola",
it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class,
R.drawable._menu_contatti_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo SITO_WEB= new DataMenuInfo(
"Sito WEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class,
R.drawable._menu_web_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo CERCA_DOCENTI= new DataMenuInfo(
"Cerca Docenti",
"Cerca Docenti",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo CERCA_CLASSI= new DataMenuInfo(
"Cerca Classi",
"Cerca Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo AULE_LIBERE= new DataMenuInfo(
"Aule Libere",
"Aule Libere",
it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class,
R.drawable._menu_rooms_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo AGGIORNA_DATI= new DataMenuInfo(
"Aggiorna Dati",
"Aggiorna i dati",
it.gov.scuolesuperioridizagarolo.action.UpdateAction.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.UpdateAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
}
