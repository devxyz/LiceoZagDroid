package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import java.util.*;

import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuHomeDocenti{
     public static final DataMenuInfo APRI_MENU= new DataMenuInfo(
"Apri\nMenu",
"Apri Menu",
it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class,
R.drawable._menu_openmenu_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo SITO_WEB= new DataMenuInfo(
"Sito\nWEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class,
R.drawable._menu_web_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo CONTATTI_ISTITUTO= new DataMenuInfo(
"Contatti\nIstituto",
"Contatta la scuola",
it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class,
R.drawable._menu_contatti_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo IL_MIO_ORARIO= new DataMenuInfo(
"Il mio\nOrario",
"Il mio Orario",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocentePersistenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocentePersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo CERCA_DOCENTI= new DataMenuInfo(
"Cerca\nDocenti",
"Cerca altri Docenti",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class,
R.drawable._menu_school_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo ORARIO_CLASSI= new DataMenuInfo(
"Orario\nClassi",
"Cerca Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo ORARIO_AULE= new DataMenuInfo(
"Orario\nAule",
"Orario Aule",
it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class,
R.drawable._menu_room_timetable_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo AULE_LIBERE= new DataMenuInfo(
"Aule\nLibere",
"Aule Libere",
it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class,
R.drawable._menu_rooms_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo ORARIO_WEB= new DataMenuInfo(
"Orario\nWEB",
"Orario WEB",
it.gov.scuolesuperioridizagarolo.fragment.SitoOrarioFragment.class,
R.drawable._menu_orari_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoOrarioFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo R_E__DOCENTI= new DataMenuInfo(
"R.E.\nDocenti",
"Registro elettronico docenti",
it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class,
R.drawable.logo_re_docente_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo CIRCOLARI_ISTITUTO= new DataMenuInfo(
"Circolari\nIstituto",
"Elenco delle circolari",
it.gov.scuolesuperioridizagarolo.fragment.ArticoloCircolariFragment.class,
R.drawable._menu_photos,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ArticoloCircolariFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.NOT_ACTIVE, DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo AGGIORNA_DATI= new DataMenuInfo(
"Aggiorna\nDati",
"Aggiorna i dati",
it.gov.scuolesuperioridizagarolo.action.UpdateAction.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.UpdateAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo BARCODE= new DataMenuInfo(
"Barcode",
"Barcode",
it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.NOT_ACTIVE) ));
     public static final DataMenuInfo BAR= new DataMenuInfo(
"BAR",
"BAR",
it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.NOT_ACTIVE) ));
}
