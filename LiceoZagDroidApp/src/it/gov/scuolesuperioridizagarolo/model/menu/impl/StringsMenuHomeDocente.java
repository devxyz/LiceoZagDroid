package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuHomeDocente{
     public static final DataMenuInfo APRI_MENU= new DataMenuInfo(
"Apri Menu",
"Apri Menu",
it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class,
R.drawable._menu_openmenu_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.OpenMenuAction.class),
null);
     public static final DataMenuInfo SITO_WEB= new DataMenuInfo(
"Sito WEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class,
R.drawable._menu_web_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class),
null);
     public static final DataMenuInfo CONTATTI= new DataMenuInfo(
"Contatti",
"Contatta la scuola",
it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class,
R.drawable._menu_contatti_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class),
null);
     public static final DataMenuInfo ORARIO_DOCENTI= new DataMenuInfo(
"Orario Docenti",
"Orario Docenti",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteFragment.class),
null);
     public static final DataMenuInfo ORARIO_CLASSI= new DataMenuInfo(
"Orario Classi",
"Orario Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseFragment.class),
null);
     public static final DataMenuInfo R_E_DOCENTI= new DataMenuInfo(
"R.E. Docenti",
"Registro elettronico docenti",
it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class,
R.drawable.logo_re_docente_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class),
null);
     public static final DataMenuInfo AGGIORNA_DATI= new DataMenuInfo(
"Aggiorna Dati",
"Aggiorna i dati",
it.gov.scuolesuperioridizagarolo.action.UpdateAction.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.UpdateAction.class),
null);
     public static final DataMenuInfo BARCODE= new DataMenuInfo(
"Barcode",
"Barcode",
it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class),
null);
}
