package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment;
import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuPrincipale{
     public static final DataMenuInfo HOME_1= new DataMenuInfo(
1,
"Home",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class,
R.drawable._menu_home,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class),
null);
     public static final DataMenuInfo SITO_WEB_912= new DataMenuInfo(
912,
"Sito WEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.action.SitoWebScuolaAction.class,
R.drawable._contatti_web_60x60,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.SitoWebScuolaAction.class),
null);
     public static final DataMenuInfo SITO_WEB_2_913= new DataMenuInfo(
913,
"Sito WEB 2",
"IIS Borsellino Falcone - Zagarolo",
SitoWebFragment.class,
R.drawable._contatti_web_60x60,
DataMenuInfoType.search(SitoWebFragment.class),
null);
     public static final DataMenuInfo CONTATTI_2= new DataMenuInfo(
2,
"Contatti",
"Contatta la scuola",
it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class,
R.drawable.icon_info_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class),
null);
     public static final DataMenuInfo ORARIO_CLASSI_302= new DataMenuInfo(
302,
"Orario Classi",
"Orario Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseFragment.class,
R.drawable._menu_orari_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseFragment.class),
null);
     public static final DataMenuInfo ORARIO_DOCENTI_303= new DataMenuInfo(
303,
"Orario Docenti",
"Orario Docenti",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteFragment.class,
R.drawable._menu_orari_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteFragment.class),
null);
     public static final DataMenuInfo FOTO_TODO_8= new DataMenuInfo(
8,
"Foto (TODO)",
"Foto Gallery",
it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class,
R.drawable._menu_photos,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class),
null);
     public static final DataMenuInfo LIBRI_DI_TESTO_LICEO_15= new DataMenuInfo(
15,
"Libri di testo LICEO",
"Libri di testo LICEO",
it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class),
null);
     public static final DataMenuInfo LIBRI_DI_TESTO_IPIA_151= new DataMenuInfo(
151,
"Libri di testo IPIA",
"Libri di testo IPIA",
it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class),
null);
     public static final DataMenuInfo ISCRIZIONE_ONLINE_17= new DataMenuInfo(
17,
"Iscrizione Online",
"Iscrizione Online",
it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class,
R.drawable.logo_iscrizioni_online_50x50,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class),
null);
     public static final DataMenuInfo IN_EVIDENZA_OGGI_6= new DataMenuInfo(
6,
"In evidenza oggi",
"Circolari del Giorno",
it.gov.scuolesuperioridizagarolo.fragment.CircolariGiornaliereFragment.class,
R.drawable.calendar_preferences_50x50,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.CircolariGiornaliereFragment.class),
null);
     public static final DataMenuInfo NEWS_4= new DataMenuInfo(
4,
"News",
"Ultime Notizie dalla scuola",
it.gov.scuolesuperioridizagarolo.fragment.NewsFragmentRss.class,
R.drawable.id_tab_news,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.NewsFragmentRss.class),
null);
     public static final DataMenuInfo CIRCOLARI_5= new DataMenuInfo(
5,
"Circolari",
"Circolari scolastiche",
it.gov.scuolesuperioridizagarolo.fragment.CircolariSearchFragment.class,
R.drawable.file_search,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.CircolariSearchFragment.class),
null);
     public static final DataMenuInfo R_E_DOCENTI_12= new DataMenuInfo(
12,
"R.E. Docenti",
"Registro elettronico docenti",
it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class,
R.drawable.logo_re_docente_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class),
null);
     public static final DataMenuInfo R_E_FAMIGLIE_13= new DataMenuInfo(
13,
"R.E. Famiglie",
"Registro elettronico famiglie",
it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class,
R.drawable.logo_re_famiglie_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class),
null);
     public static final DataMenuInfo CHIUDI_22= new DataMenuInfo(
22,
"Chiudi",
"Chiudi",
it.gov.scuolesuperioridizagarolo.action.CloseAction.class,
R.drawable.cancel_red,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.CloseAction.class),
null);
}
