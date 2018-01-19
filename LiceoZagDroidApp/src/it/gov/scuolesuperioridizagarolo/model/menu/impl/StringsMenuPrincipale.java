package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import it.gov.scuolesuperioridizagarolo.fragment.OrarioDocentePersistenteFragment;
import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuPrincipale{
     public static final DataMenuInfo HOME= new DataMenuInfo(
"Home",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class,
R.drawable._menu_home,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class),
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
     public static final DataMenuInfo ORARIO_CLASSI= new DataMenuInfo(
"Orario Classi",
"Orario Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class),
null);
     public static final DataMenuInfo ORARIO_DOCENTI= new DataMenuInfo(
"Orario Docenti",
"Orario Docenti",
OrarioDocentePersistenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(OrarioDocentePersistenteFragment.class),
null);
     public static final DataMenuInfo AULE_LIBERE= new DataMenuInfo(
"Aule Libere",
"Aule Libere",
it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class,
R.drawable._menu_rooms_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class),
null);
     public static final DataMenuInfo FOTO_TODO= new DataMenuInfo(
"Foto (TODO)",
"Foto Gallery",
it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class,
R.drawable._menu_photos,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class),
null);
     public static final DataMenuInfo LIBRI_DI_TESTO_LICEO= new DataMenuInfo(
"Libri di testo LICEO",
"Libri di testo LICEO",
it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class),
null);
     public static final DataMenuInfo LIBRI_DI_TESTO_IPIA= new DataMenuInfo(
"Libri di testo IPIA",
"Libri di testo IPIA",
it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class),
null);
     public static final DataMenuInfo ISCRIZIONE_ONLINE= new DataMenuInfo(
"Iscrizione Online",
"Iscrizione Online",
it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class,
R.drawable.logo_iscrizioni_online_50x50,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class),
null);
     public static final DataMenuInfo IN_EVIDENZA_OGGI= new DataMenuInfo(
"In evidenza oggi",
"Circolari del Giorno",
it.gov.scuolesuperioridizagarolo.fragment.CircolariGiornaliereFragment.class,
R.drawable.calendar_preferences_50x50,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.CircolariGiornaliereFragment.class),
null);
     public static final DataMenuInfo NEWS= new DataMenuInfo(
"News",
"Ultime Notizie dalla scuola",
it.gov.scuolesuperioridizagarolo.fragment.NewsFragmentRss.class,
R.drawable.id_tab_news,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.NewsFragmentRss.class),
null);
     public static final DataMenuInfo CIRCOLARI= new DataMenuInfo(
"Circolari",
"Circolari scolastiche",
it.gov.scuolesuperioridizagarolo.fragment.CircolariSearchFragment.class,
R.drawable.file_search,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.CircolariSearchFragment.class),
null);
     public static final DataMenuInfo R_E_DOCENTI= new DataMenuInfo(
"R.E. Docenti",
"Registro elettronico docenti",
it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class,
R.drawable.logo_re_docente_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class),
null);
     public static final DataMenuInfo R_E_FAMIGLIE= new DataMenuInfo(
"R.E. Famiglie",
"Registro elettronico famiglie",
it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class,
R.drawable.logo_re_famiglie_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class),
null);
     public static final DataMenuInfo AGGIORNA= new DataMenuInfo(
"Aggiorna",
"Aggiorna i dati",
it.gov.scuolesuperioridizagarolo.action.UpdateAction.class,
R.drawable._menu_update_service_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.UpdateAction.class),
null);
     public static final DataMenuInfo BARCODE= new DataMenuInfo(
"Barcode",
"Barcode",
it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.BarCodeFragment.class),
null);
     public static final DataMenuInfo BAR= new DataMenuInfo(
"BAR",
"BAR",
it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class),
null);
     public static final DataMenuInfo CHIUDI= new DataMenuInfo(
"Chiudi",
"Chiudi",
it.gov.scuolesuperioridizagarolo.action.CloseAction.class,
R.drawable.cancel_red,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.CloseAction.class),
null);
}
