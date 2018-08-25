package it.gov.scuolesuperioridizagarolo.model.menu.impl;

import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoFlag;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoType;

import java.util.Arrays;
import java.util.TreeSet;
public class StringsMenuHomeStudenti{
     public static final DataMenuInfo APRI_MENU= new DataMenuInfo(
"Apri Menu",
"Apri Menu",
it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class,
R.drawable._menu_openmenu_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo SITO_WEB= new DataMenuInfo(
"Sito WEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class,
R.drawable._menu_web_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo ORARIO_CLASSI= new DataMenuInfo(
"Orario Classi",
"Orario Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo ORARIO_AULE= new DataMenuInfo(
"Orario Aule",
"Orario Aule",
it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class,
R.drawable._menu_room_timetable_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo R_E_FAMIGLIE= new DataMenuInfo(
"R.E. Famiglie",
"Registro elettronico famiglie",
it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class,
R.drawable.logo_re_famiglie_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo AGGIORNA= new DataMenuInfo(
"Aggiorna",
"Aggiorna i dati",
it.gov.scuolesuperioridizagarolo.action.UpdateAction.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.UpdateAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
}
