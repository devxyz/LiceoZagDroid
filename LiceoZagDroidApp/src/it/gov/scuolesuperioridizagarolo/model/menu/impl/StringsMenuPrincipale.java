package it.gov.scuolesuperioridizagarolo.model.menu.impl;
import it.gov.scuolesuperioridizagarolo.R;

import java.util.*;

import it.gov.scuolesuperioridizagarolo.model.menu.*;
public class StringsMenuPrincipale{
     public static final DataMenuInfo HOME= new DataMenuInfo(
"Home",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class,
R.drawable._menu_home,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.HomeFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.DONT_SHOW_IN_HOME) ));
     public static final DataMenuInfo SITO_WEB= new DataMenuInfo(
"Sito WEB",
"IIS Borsellino Falcone - Zagarolo",
it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class,
R.drawable._menu_web_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoWebFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo CONTATTI= new DataMenuInfo(
"Contatti",
"Contatta la scuola",
it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class,
R.drawable._menu_contatti_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ContattiFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo IL_MIO_ORARIO_DOCENTE= new DataMenuInfo(
"Il mio orario (docente)",
"Il mio orario (docente)",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocentePersistenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocentePersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo ORARIO_DELLA_CLASSE= new DataMenuInfo(
"Orario della classe",
"Orario della classe",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClassePersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_STUDENTI, DataMenuInfoFlag.SHOW_GENITORI) ));
     public static final DataMenuInfo ORARIO_CLASSI= new DataMenuInfo(
"Orario Classi",
"Orario Classi",
it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class,
R.drawable._menu_student_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioClasseNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ATA, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo ORARIO_DOCENTI= new DataMenuInfo(
"Orario Docenti",
"Orario Docenti",
it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class,
R.drawable._menu_teaching_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioDocenteNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_STUDENTI, DataMenuInfoFlag.SHOW_ATA, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo ORARIO_AULE= new DataMenuInfo(
"Orario Aule",
"Orario Aule",
it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class,
R.drawable._menu_room_timetable_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.OrarioAulaNonPersistenteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ATA, DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_STUDENTI, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo AULE_LIBERE= new DataMenuInfo(
"Aule Libere",
"Aule Libere",
it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class,
R.drawable._menu_rooms_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.AuleVuoteFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ATA, DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ADMIN, DataMenuInfoFlag.SHOW_STUDENTI) ));
     public static final DataMenuInfo ORARIO_WEB= new DataMenuInfo(
"Orario\nWEB",
"Orario WEB",
it.gov.scuolesuperioridizagarolo.fragment.SitoOrarioFragment.class,
R.drawable._menu_orari_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.SitoOrarioFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ATA, DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ADMIN, DataMenuInfoFlag.SHOW_STUDENTI) ));
     public static final DataMenuInfo LIBRI_DI_TESTO_LICEO= new DataMenuInfo(
"Libri di testo LICEO",
"Libri di testo LICEO",
it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoLiceoAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo LIBRI_DI_TESTO_IPIA= new DataMenuInfo(
"Libri di testo IPIA",
"Libri di testo IPIA",
it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class,
R.drawable.library_icon,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.LibriTestoIpiaAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM) ));
     public static final DataMenuInfo ISCRIZIONE_ONLINE= new DataMenuInfo(
"Iscrizione Online",
"Iscrizione Online",
it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class,
R.drawable.logo_iscrizioni_online_50x50,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.IscrizioneOnlineAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_GENITORI) ));
     public static final DataMenuInfo R_E_DOCENTI= new DataMenuInfo(
"R.E. Docenti",
"Registro elettronico docenti",
it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class,
R.drawable.logo_re_docente_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReDocentiAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo R_E_FAMIGLIE= new DataMenuInfo(
"R.E. Famiglie",
"Registro elettronico famiglie",
it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class,
R.drawable.logo_re_famiglie_45x45,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.ReStudentiAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SHOW_GENITORI, DataMenuInfoFlag.SHOW_STUDENTI, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo AGGIORNA= new DataMenuInfo(
"Aggiorna",
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
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo BAR= new DataMenuInfo(
"BAR",
"BAR",
it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.ProdottiBarFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo BACKUP_DATABASE= new DataMenuInfo(
"BACKUP DATABASE",
"BACKUP DATABASE",
it.gov.scuolesuperioridizagarolo.action.BackupDatabaseAction.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.BackupDatabaseAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo DOWNLOAD_ARTICLES= new DataMenuInfo(
"DOWNLOAD ARTICLES",
"DOWNLOAD ARTICLES",
it.gov.scuolesuperioridizagarolo.action.DownloadArticlesAction.class,
R.drawable.close_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.DownloadArticlesAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo DEBUG_MENU= new DataMenuInfo(
"DEBUG MENU",
"DEBUG MENU",
it.gov.scuolesuperioridizagarolo.action.OpenDebugMenuAction.class,
R.drawable._menu_room_timetable_48x48,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.OpenDebugMenuAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.SHOW_ADMIN) ));
     public static final DataMenuInfo FOTO_TODO= new DataMenuInfo(
"Foto (TODO)",
"Foto Gallery",
it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class,
R.drawable._menu_photos,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.fragment.PhotosExpandibleFragment.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.SUB_ITEM, DataMenuInfoFlag.NOT_ACTIVE) ));
     public static final DataMenuInfo CAMBIA_PROFILO= new DataMenuInfo(
"Cambia Profilo",
"Cambia Profilo",
it.gov.scuolesuperioridizagarolo.action.AppUserChooserAction.class,
R.drawable._menu_photos,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.AppUserChooserAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList() ));
     public static final DataMenuInfo CHIUDI= new DataMenuInfo(
"Chiudi",
"Chiudi",
it.gov.scuolesuperioridizagarolo.action.CloseAction.class,
R.drawable.cancel_red,
DataMenuInfoType.search(it.gov.scuolesuperioridizagarolo.action.CloseAction.class),
new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(DataMenuInfoFlag.DONT_SHOW_IN_HOME) ));
}
