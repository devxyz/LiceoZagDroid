package utility.orario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ElenchiClassi {
    static String testo = "1\tAcanfora Giorgio\tCLASSE 2A\n" +
            "2\tBidetta Alessio\t\n" +
            "3\tBlasi Mattia\t\n" +
            "4\tCangini Tiziano\t\n" +
            "5\tCappilli Alessandro\t\n" +
            "6\tCardone Alessandro\t\n" +
            "7\tCarletti Gaia\t\n" +
            "8\tCecconi Camilla\t\n" +
            "9\tCoccia Giulia\t\n" +
            "10\tColagrossi Arianna\t\n" +
            "11\tDi Nunzio Aurora\t\n" +
            "12\tFebbi Pietro\t\n" +
            "13\tFerrera Elisa\t\n" +
            "14\tGambini Aurora\t\n" +
            "15\tLupicuti Francesco\t\n" +
            "16\tMadera Francesco\t\n" +
            "17\tMauri Pietro Francesco\t\n" +
            "18\tMINNA Danilo\t\n" +
            "19\tMizzon Luca\t\n" +
            "20\tMoccia Davide\t\n" +
            "21\tORIANA Alessandro\t\n" +
            "22\tPalmiero Salvatore Matteo\t\n" +
            "23\tPiermattei Adriano\t\n" +
            "24\tScarozza Luca\t\n" +
            "25\tStella Diego\t\n" +
            "1\tAchitei Fabrizio\tCLASSE 2B\n" +
            "2\tAloe Luna\t\n" +
            "3\tAndronic Marta\t\n" +
            "4\tBalducci Martina\t\n" +
            "5\tCapatina Eduard\t\n" +
            "6\tCareri Lorenzo\t\n" +
            "7\tCarletti Flavio\t\n" +
            "8\tFilacchione Gabriele\t\n" +
            "9\tFrattarola Rebecca\t\n" +
            "10\tGagliardi Mattia\t\n" +
            "11\tIenne Gianluigi\t\n" +
            "12\tMaccaroni Daniele\t\n" +
            "13\tMaier Tiziano\t\n" +
            "14\tMariani Azzurra\t\n" +
            "15\tMarta Elena\t\n" +
            "16\tMartini Nicolo'\t\n" +
            "17\tMassimi Diego Alessio\t\n" +
            "18\tPetrucci Sara\t\n" +
            "19\tPiazzai Francesco\t\n" +
            "20\tPiccirilli Daniele\t\n" +
            "21\tPizziconi Leonardo\t\n" +
            "22\tSbordone Andrey\t\n" +
            "23\tTololoi Claudio Markus\t\n" +
            "24\tTrani Alessio\t\n" +
            "25\tZaratti Davide\t\n" +
            "26\tZarlenga Samuele\t\n" +
            "1\tCardarelli Chiara\tCLASSE 2C\n" +
            "2\tCavaiola Giada\t\n" +
            "3\tChecconi Marta\t\n" +
            "4\tCINILI Serena\t\n" +
            "5\tCrisan Isabella\t\n" +
            "6\tDe Angelis Luna\t\n" +
            "7\tDe Nicola Sophia\t\n" +
            "8\tFederici Giulia\t\n" +
            "9\tGiacco Emma\t\n" +
            "10\tGiannetti Vanessa\t\n" +
            "11\tLagana' Giulia\t\n" +
            "12\tMaraldi Alessia\t\n" +
            "13\tMarini Matteo\t\n" +
            "14\tMattogno Nicolo'\t\n" +
            "15\tMoretti Isabella\t\n" +
            "16\tPassi Marika\t\n" +
            "17\tPetrassi Francesco Maria\t\n" +
            "18\tSalituro Sara\t\n" +
            "19\tSalvati Andrea\t\n" +
            "20\tSpataro Beatrice\t\n" +
            "21\tTolli Giulia\t\n" +
            "22\tVolpe Francesca\t\n" +
            "1\tAron Daniele\tCLASSE 2D\n" +
            "2\tAttilia Desire'E\t\n" +
            "3\tAvella Alessia\t\n" +
            "4\tBartalini Lorenzo\t\n" +
            "5\tCelletti Cristina\t\n" +
            "6\tCIAVARDINI Gianluca\t\n" +
            "7\tCoccia Luca\t\n" +
            "8\tCristino Francesco Mattia\t\n" +
            "9\tDantimi Damiano\t\n" +
            "10\tDe Francesco Marco\t\n" +
            "11\tDel Duca Gabriele\t\n" +
            "12\tFortunato Gabriele\t\n" +
            "13\tKozar Bartosz\t\n" +
            "14\tManca Rocco\t\n" +
            "15\tMancinelli Jacopo\t\n" +
            "16\tMastrantonio Valerio\t\n" +
            "17\tOrlandi Tiziano\t\n" +
            "18\tPopa Razvan\t\n" +
            "19\tPucci Alessandro\t\n" +
            "20\tRosicarelli Emanuele\t\n" +
            "21\tSarandrea Gabriele\t\n" +
            "22\tScacco Federico\t\n" +
            "23\tScarano Luca\t\n" +
            "24\tTolfa Benedetta\t\n" +
            "25\tTrinchieri Luca\t\n" +
            "26\tVittucci Damiano\t\n" +
            "1\tBacci Matteo\tCLASSE 2E\n" +
            "2\tCartoni Alessia\t\n" +
            "3\tChialastri Lorenzo\t\n" +
            "4\tCoccia Lorenzo\t\n" +
            "5\tDi Vincenzo Samuele\t\n" +
            "6\tFilippetti Chiara Elettra\t\n" +
            "7\tGordiani Giorgia\t\n" +
            "8\tLippi Lorenzo\t\n" +
            "9\tMacarra Federico\t\n" +
            "10\tManea Laurentiu Valentin\t\n" +
            "11\tMariani Federica\t\n" +
            "12\tMecchia Simone\t\n" +
            "13\tPucci Martina\t\n" +
            "14\tRegoli Patrizio\t\n" +
            "15\tScarciotti Alessandro\t\n" +
            "16\tSchiavella Beatrice\t\n" +
            "17\tVecchi Elvira\t\n" +
            "18\tZiffi Alessio\t\n" +
            "1\tAiftincai Giulia\tCLASSE 2F\n" +
            "2\tAlicata Mattia\t\n" +
            "3\tBangrazi Lorenzo Maria\t\n" +
            "4\tBerardi Leonardo\t\n" +
            "5\tBernardini Marco\t\n" +
            "6\tBirzu Emanuela\t\n" +
            "7\tCalcagna Chiara\t\n" +
            "8\tCarbonari Claudio\t\n" +
            "9\tChrichmi Idriss\t\n" +
            "10\tConforti Michela\t\n" +
            "11\tCorradino Michele\t\n" +
            "12\tDella Bella Gioele\t\n" +
            "13\tErmini Mauro\t\n" +
            "14\tHriscu Giulia Teodora\t\n" +
            "15\tIonascu Alessandra\t\n" +
            "16\tMannara Lorenzo\t\n" +
            "17\tOliverio Denise\t\n" +
            "18\tOliverio Desire'E\t\n" +
            "19\tPasquariello Antonio\t\n" +
            "20\tPenza Emanuele\t\n" +
            "21\tSabelli Simone\t\n" +
            "22\tSchiona Giorgia\t\n" +
            "23\tTonani Gabriele\t\n" +
            "1\tBelli Francesco\tCLASSE 2H\n" +
            "2\tCarpineta Federico\t\n" +
            "3\tCastellucci Daniel\t\n" +
            "4\tCavaricci Noemi\t\n" +
            "5\tCeccobelli Serena\t\n" +
            "6\tCerbara Matteo\t\n" +
            "7\tDe Angelis Fabrizio\t\n" +
            "8\tDe Vito Francesco\t\n" +
            "9\tFazi Aurora\t\n" +
            "10\tGramiccia Giulia\t\n" +
            "11\tGraziani Marco\t\n" +
            "12\tHuza Katia\t\n" +
            "13\tKruja Devid\t\n" +
            "14\tMancinelli Yuri\t\n" +
            "15\tMiron Laura Romina\t\n" +
            "16\tProfeta Miriam\t\n" +
            "17\tRomersi Irene\t\n" +
            "18\tTopsa Irina\t\n" +
            "19\tTulli Marco\t\n" +
            "20\tVincenzi Luca\t\n" +
            "21\tZefi Emiliano\t\n" +
            "1\tAglietti Valentina\tCLASSE 3A\n" +
            "2\tCatalani Filippo\t\n" +
            "3\tChorazak Szymon\t\n" +
            "4\tCristofari Daniele\t\n" +
            "5\tD'Angelo Antonio\t\n" +
            "6\tDi Loreto Francesco\t\n" +
            "7\tGiusti Anna\t\n" +
            "8\tHranaceru Andreea Larisa\t\n" +
            "9\tIngrosso Angelo Emanuele\t\n" +
            "10\tMacarra Aurora\t\n" +
            "11\tMeoli Gianluigi\t\n" +
            "12\tNanni Ilaria\t\n" +
            "13\tNardi Camilla\t\n" +
            "14\tPasqualini Thomas\t\n" +
            "15\tPecetta David\t\n" +
            "16\tPiras Mauro\t\n" +
            "17\tRosetti Lucia\t\n" +
            "18\tSalomone Chiara\t\n" +
            "19\tSerra Alessandro\t\n" +
            "20\tTumminello Chiara\t\n" +
            "1\tAuricchio Lorenzo\tCLASSE 3B\n" +
            "2\tCraciun Matteo\t\n" +
            "3\tDi Pietro Niccolo'\t\n" +
            "4\tDoraci Zaklina\t\n" +
            "5\tEvangelista Alessandro\t\n" +
            "6\tFiorentini Giorgia\t\n" +
            "7\tGentile Gabriel\t\n" +
            "8\tLullo Valerio\t\n" +
            "9\tMecani Ketlin\t\n" +
            "10\tMonaco Mario\t\n" +
            "11\tParolari Kevin\t\n" +
            "12\tPasquazi Elena\t\n" +
            "13\tPinci Rachele\t\n" +
            "14\tPopa Cristian Eduard\t\n" +
            "15\tSanita' Asia\t\n" +
            "16\tSantoni Yuri\t\n" +
            "17\tStocco Matteo\t\n" +
            "18\tTango Lorenzo\t\n" +
            "19\tVelletrani Andrea\t\n" +
            "1\tAleandri Beatrice\tCLASSE 3C\n" +
            "2\tAngelucci Filippo\t\n" +
            "3\tCossero Sofia\t\n" +
            "4\tD'Amico Gioia\t\n" +
            "5\tEsposito Benedetta\t\n" +
            "6\tFulcri Michela\t\n" +
            "7\tFunaro Paolo\t\n" +
            "8\tGiglio Rachele\t\n" +
            "9\tIadonisi Nicola Matteo\t\n" +
            "10\tLatala Kamil Krzysztof\t\n" +
            "11\tLupascu Roxana Ioana\t\n" +
            "12\tMarino Federico\t\n" +
            "13\tMinzocchi Gabriele\t\n" +
            "14\tNovelli Francesco\t\n" +
            "15\tNovelli Irene\t\n" +
            "16\tPasquazi Maila\t\n" +
            "17\tPedrazzini Giorgia\t\n" +
            "18\tPette Francesco\t\n" +
            "19\tRicci Costantino\t\n" +
            "20\tRomani Alessandro\t\n" +
            "21\tRosu Claudiu Marian\t\n" +
            "22\tScarozza Luca\t\n" +
            "23\tTudorache Florin Cornel\t\n" +
            "1\tAscenzi Gianmarco\tCLASSE 3D\n" +
            "2\tBeltrami Lorenzo\t\n" +
            "3\tBitsch Flaviano\t\n" +
            "4\tBonafede Giuseppe\t\n" +
            "5\tCeccarelli Lorenzo\t\n" +
            "6\tCicerchia Nicolas\t\n" +
            "7\tCorrenti Asia\t\n" +
            "8\tCurzi Nicolo'\t\n" +
            "9\tD'Alessandro Antonio\t\n" +
            "10\tDelle Fratte Flavio\t\n" +
            "11\tDi Carlo Francesca\t\n" +
            "12\tDonca Andrea Denisa\t\n" +
            "13\tFerracci Ludovica\t\n" +
            "14\tFrusta Alessandro\t\n" +
            "15\tGaetano Davide\t\n" +
            "16\tGheorghiu Marco Davide\t\n" +
            "17\tGiordano Matteo\t\n" +
            "18\tGiuliani Federica\t\n" +
            "19\tGlielmi Mattia\t\n" +
            "20\tHalili Cristian\t\n" +
            "21\tLigori Paolo\t\n" +
            "22\tMarignoli Mario\t\n" +
            "23\tMastrofini Valerio\t\n" +
            "24\tNicolaus Leonardo\t\n" +
            "25\tPop Maximilian Ioan\t\n" +
            "26\tRomagnoli Nicolo'\t\n" +
            "27\tRuggieri Sofia\t\n" +
            "28\tSchioppa Matteo\t\n" +
            "29\tWehbe Latifa\t\n" +
            "1\tAccordino Giulia\tCLASSE 3E\n" +
            "2\tAngelucci Giulia\t\n" +
            "3\tArquilla Flavio\t\n" +
            "4\tBaltag Maria Luiza\t\n" +
            "5\tCarosi Giulia\t\n" +
            "6\tCellini Nicolo'\t\n" +
            "7\tChialastri Lavinia\t\n" +
            "8\tDuca Ilaria\t\n" +
            "9\tFerracci Alice\t\n" +
            "10\tGaffi Valerio\t\n" +
            "11\tGiuliani Federico\t\n" +
            "12\tIanni Noemi\t\n" +
            "13\tManni Erica\t\n" +
            "14\tMastrofini Chiara\t\n" +
            "15\tMoriga Giulia\t\n" +
            "16\tRossetti Benedetta\t\n" +
            "17\tRuggeri Matteo\t\n" +
            "18\tScopa Luca\t\n" +
            "19\tScripca Gabriel\t\n" +
            "20\tSimonini Sara\t\n" +
            "21\tTravaglini Matteo\t\n" +
            "22\tTrinca Carlotta\t\n" +
            "23\tVenditti Christian\t\n" +
            "1\tAlexandru Martina\tCLASSE 3F\n" +
            "2\tArgiro' Flavio Vincenzo\t\n" +
            "3\tColagrossi Lorenzo\t\n" +
            "4\tD'Uffizi Roberto\t\n" +
            "5\tErcoli Daniele\t\n" +
            "6\tFalco Ilaria Pia\t\n" +
            "7\tForgione Cristiano\t\n" +
            "8\tHaval Andrij\t\n" +
            "9\tLore' Luca\t\n" +
            "10\tLuciani Gianmarco\t\n" +
            "11\tLupi Pier Giorgio\t\n" +
            "12\tMuzio Adriana\t\n" +
            "13\tNati Federico\t\n" +
            "14\tPacini Luca\t\n" +
            "15\tProietti Beatrice\t\n" +
            "16\tScaringi Chiara\t\n" +
            "17\tSciacovelli Davide\t\n" +
            "18\tSimone Pierluigi\t\n" +
            "19\tSternini Alessandra\t\n" +
            "20\tVannini Emanuele\t\n" +
            "21\tVlusha Kristi\t\n" +
            "1\tBramati Alessio\tCLASSE 3G\n" +
            "2\tCandido Andrea\t\n" +
            "3\tCatalani Paolo\t\n" +
            "4\tCeracchi Bruno\t\n" +
            "5\tCerci Rachele\t\n" +
            "6\tCesaretti Greta\t\n" +
            "7\tCosma Fabiana Giorgiana\t\n" +
            "8\tCotroneo Matteo\t\n" +
            "9\tDe Marzi Chiara\t\n" +
            "10\tDinu Mihai Sebastian\t\n" +
            "11\tDodaro Chiara\t\n" +
            "12\tGallo Gabriele\t\n" +
            "13\tGiannini Valentino\t\n" +
            "14\tHavaraj Fiorela\t\n" +
            "15\tIanniccari Gabriele\t\n" +
            "16\tIstode Gabriela\t\n" +
            "17\tLo Vetere Mia\t\n" +
            "18\tMancini Manuel\t\n" +
            "19\tMarini Beatrice\t\n" +
            "20\tMarrone Marco\t\n" +
            "21\tMoretti Michele\t\n" +
            "22\tQuaranta Gianluca\t\n" +
            "23\tRonci Erica\t\n" +
            "24\tRossi Giulia\t\n" +
            "25\tSbardella Nicolo'\t\n" +
            "26\tSpina Francesco\t\n" +
            "27\tTrovato Irene\t\n" +
            "28\tValente Sofia\t\n" +
            "1\tBuhna Artur\tCLASSE 3H\n" +
            "2\tCaporossi Tiziano\t\n" +
            "3\tCarrarini Federico\t\n" +
            "4\tCasale Livia\t\n" +
            "5\tD'Angelo Giacomo\t\n" +
            "6\tDal Vecchio Dylan\t\n" +
            "7\tDomos Bianca Elena\t\n" +
            "8\tFerracci Federico\t\n" +
            "9\tIlari Matteo\t\n" +
            "10\tLisi Silvia\t\n" +
            "11\tMacarra Giulia\t\n" +
            "12\tMazzenga Federica\t\n" +
            "13\tMeoli Alice\t\n" +
            "14\tMilana Manila\t\n" +
            "15\tNeagoe Denis Lorenzo\t\n" +
            "16\tOddi Samuele\t\n" +
            "17\tPinci Marta\t\n" +
            "18\tProietti Jessica\t\n" +
            "19\tScaramella Andrea\t\n" +
            "20\tSellaroli Matteo\t\n" +
            "1\tBartolomucci Giulia\tCLASSE 4A\n" +
            "2\tBlasi Anastasia\t\n" +
            "3\tCafaro Pierfrancesco\t\n" +
            "4\tCaliment Sandra Gabriella\t\n" +
            "5\tCalvano Luisa\t\n" +
            "6\tCappilli Antonio\t\n" +
            "7\tCarpineta Lia\t\n" +
            "8\tD'Antonio Lorenzo\t\n" +
            "9\tDi Tullio Roberta\t\n" +
            "10\tDurastanti Davide\t\n" +
            "11\tMaiorano Irene\t\n" +
            "12\tMariani Alessandro\t\n" +
            "13\tMassaro Massimo\t\n" +
            "14\tMenichelli Chiara\t\n" +
            "15\tMinna Manila\t\n" +
            "16\tPinci Emanuele\t\n" +
            "17\tSapienza Mattia\t\n" +
            "18\tSebastianelli Giammarco\t\n" +
            "19\tStivala Lorenzo\t\n" +
            "20\tTrionfera Alessio\t\n" +
            "21\tTrionfera Francesco\t\n" +
            "22\tTrovo' Christian\t\n" +
            "1\tBernassola Gabriele\tCLASSE 4B\n" +
            "2\tCianfriglia Michele\t\n" +
            "3\tCipolletta Matteo\t\n" +
            "4\tColagrossi Iacopo\t\n" +
            "5\tFabiani Leonardo\t\n" +
            "6\tGiovannini Francesca\t\n" +
            "7\tLongo Beatrice\t\n" +
            "8\tMarani Lorenzo\t\n" +
            "9\tMazzenga Nicolo'\t\n" +
            "10\tMenasci' Giulia\t\n" +
            "11\tNardi Marco\t\n" +
            "12\tPucciarelli Matteo\t\n" +
            "13\tPulsoni Andrea\t\n" +
            "14\tScacco Giovanni\t\n" +
            "15\tSchiavella Pierluca\t\n" +
            "16\tSerra Lorenzo\t\n" +
            "17\tTerenzi Paolo\t\n" +
            "18\tTomasi Daniele\t\n" +
            "19\tTulli Christian\t\n" +
            "20\tUgolini Alessio\t\n" +
            "21\tZippo Gabriele\t\n" +
            "1\tAdamo Beatrice\tCLASSE 4C\n" +
            "2\tBrumar Sofia Nicol\t\n" +
            "3\tCasale Irene\t\n" +
            "4\tCilia Silvia\t\n" +
            "5\tConte Benedetta\t\n" +
            "6\tCroppi Giuliano\t\n" +
            "7\tDe Matteis Giorgio\t\n" +
            "8\tDel Duca Federica\t\n" +
            "9\tFilosa Chiara\t\n" +
            "10\tMagistri Lorenzo\t\n" +
            "11\tMancinelli Matteo\t\n" +
            "12\tMoriconi Tommaso Claudio\t\n" +
            "13\tPacifici Daniele\t\n" +
            "14\tPetricciuolo Matteo\t\n" +
            "15\tPizzi Caterina\t\n" +
            "16\tRosicarelli Matteo\t\n" +
            "17\tSantapaola Manuel\t\n" +
            "18\tScaramella Benedetta\t\n" +
            "19\tSerri Alessandro\t\n" +
            "20\tTagliacozzo Camilla\t\n" +
            "21\tTucci Beatrice\t\n" +
            "22\tZammarrelli Gabriele\t\n" +
            "1\tBravi Sofia\tCLASSE 4D\n" +
            "2\tCallegari Luca\t\n" +
            "3\tCarboni Federico Augusto\t\n" +
            "4\tCoccia Luigi\t\n" +
            "5\tCostea Andreea Alexandra\t\n" +
            "6\tDi Vito Andrea\t\n" +
            "7\tDodaro Manuel\t\n" +
            "8\tGallotti Fatima\t\n" +
            "9\tGuidi Francesco\t\n" +
            "10\tHozoc Cristian Fabio\t\n" +
            "11\tIgiri Lorenzo\t\n" +
            "12\tLepri Giada\t\n" +
            "13\tMecchia Eduardo\t\n" +
            "14\tPaniccia Leonardo\t\n" +
            "15\tPanzironi Gian Marco\t\n" +
            "16\tPasquazi Rucsandra Ioana\t\n" +
            "17\tPetrassi Francesco\t\n" +
            "18\tRomeo Salvatore\t\n" +
            "19\tSanna Ilaria\t\n" +
            "20\tSquadrilli Vincenzo Maria\t\n" +
            "21\tTittozzi Matteo\t\n" +
            "22\tTucci Matteo\t\n" +
            "1\tBassani Elisa\tCLASSE 4E\n" +
            "2\tBattistelli Beatrice\t\n" +
            "3\tBerlenghi Dario\t\n" +
            "4\tCaiazza Riccardo  Francesco\t\n" +
            "5\tCrocenzi Federico\t\n" +
            "6\tDe Santis Alessandra\t\n" +
            "7\tDi Berardino Ludovico\t\n" +
            "8\tFiscella Elisa\t\n" +
            "9\tIlardi Cristiano\t\n" +
            "10\tImpavidi Gabriele\t\n" +
            "11\tLaurenti Camilla\t\n" +
            "12\tLulli Leonardo\t\n" +
            "13\tNanni Camilla\t\n" +
            "14\tPalombi Asia\t\n" +
            "15\tPasquazi Luca\t\n" +
            "16\tPastacaldi Lorenzo\t\n" +
            "17\tRomersi Chiara\t\n" +
            "18\tSchembri Enrico\t\n" +
            "19\tTosto Lorenzo\t\n" +
            "20\tZisu Adrian Daniel\t\n" +
            "1\tAbunei David\tCLASSE 4F\n" +
            "2\tAndronic George\t\n" +
            "3\tBielli Valerio\t\n" +
            "4\tBortoloni Ilaria\t\n" +
            "5\tBruni Leonardo\t\n" +
            "6\tCensi Marco\t\n" +
            "7\tCerboni Federico\t\n" +
            "8\tMariani Dennis\t\n" +
            "9\tOberi Nicolas\t\n" +
            "10\tOkshtuni Denis\t\n" +
            "11\tOliverio Erika\t\n" +
            "12\tPassa Nikolas\t\n" +
            "13\tPetrucci Lorenzo\t\n" +
            "14\tSepehry Sam\t\n" +
            "15\tVerginelli Edoardo\t\n" +
            "16\tVinattieri Mattia\t\n" +
            "1\tBoccia Michela\tCLASSE 4G\n" +
            "2\tCosco Mirko\t\n" +
            "3\tDi Serafino Roberta\t\n" +
            "4\tFazi Flavia\t\n" +
            "5\tFioretti Matteo\t\n" +
            "6\tGaluppi Matteo\t\n" +
            "7\tLemezhi Viviana\t\n" +
            "8\tMatrigiani Francesca\t\n" +
            "9\tMilea Valeria Alexandra\t\n" +
            "10\tMorgante Flavia\t\n" +
            "11\tOlenic Cezaria Larisa\t\n" +
            "12\tPasseri Valeria\t\n" +
            "13\tPinci Anna Rita\t\n" +
            "14\tPizziconi Sara\t\n" +
            "15\tPucciarelli Martina\t\n" +
            "16\tRipanti Elisa\t\n" +
            "17\tTagliaferri Giulia\t\n" +
            "1\tBonafede Samuele\tCLASSE 5A\n" +
            "2\tCampana Lorenzo\t\n" +
            "3\tCiampaglia Gabriele\t\n" +
            "4\tDe Angelis Simone\t\n" +
            "5\tDel Vescovo Matteo\t\n" +
            "6\tDi Bernardini Stefano\t\n" +
            "7\tDi Girolamo Eleonora\t\n" +
            "8\tFacchini Giulia\t\n" +
            "9\tFornari Ludovica\t\n" +
            "10\tGiovannetti Alessandra\t\n" +
            "11\tIdemudia Silvia\t\n" +
            "12\tMarian Patrick\t\n" +
            "13\tMarrocco Matteo\t\n" +
            "14\tMattogno Francesco\t\n" +
            "15\tMichelini Luca\t\n" +
            "16\tNardi Leonardo\t\n" +
            "17\tNepi Martina\t\n" +
            "18\tPasseri Alessio\t\n" +
            "19\tSesto Nicolo'\t\n" +
            "20\tTurianelli Mattia\t\n" +
            "21\tVenerando Marco\t\n" +
            "1\tBenegiano Denise\tCLASSE 5B\n" +
            "2\tCampoli Francesco\t\n" +
            "3\tCesarotti Emma\t\n" +
            "4\tCipriani Marco\t\n" +
            "5\tDi Cesare Simone\t\n" +
            "6\tLibianchi Chiara Maria\t\n" +
            "7\tLunati Edoardo\t\n" +
            "8\tMas Emanuelcarlo\t\n" +
            "9\tNestola Matteo\t\n" +
            "10\tNicolosi Gabriele\t\n" +
            "11\tPescetelli Alessandro\t\n" +
            "12\tRicci Alessia\t\n" +
            "13\tRossi Gemma\t\n" +
            "14\tScipioni Simone\t\n" +
            "1\tAlese Nico\tCLASSE 5C\n" +
            "2\tBagatti Francesco\t\n" +
            "3\tBelmonte Damiano\t\n" +
            "4\tBencivenga Agnese\t\n" +
            "5\tBonanni Rachele\t\n" +
            "6\tCantelli Mario\t\n" +
            "7\tCecconi Andrea\t\n" +
            "8\tCetorelli Andrea\t\n" +
            "9\tD'Attilia Martina\t\n" +
            "10\tDe Rocchis Alessio\t\n" +
            "11\tDi Nunzio Marcello\t\n" +
            "12\tFaraglia Irene\t\n" +
            "13\tIanniccari Roberta\t\n" +
            "14\tPanzironi Gabriele\t\n" +
            "15\tRemigi Mattia\t\n" +
            "16\tSpagnulo Laura\t\n" +
            "17\tZappia Lorenzo\t\n" +
            "1\tAntonellini Federico\tCLASSE 5D\n" +
            "2\tBaroni Leonardo\t\n" +
            "3\tBoccia Valentina\t\n" +
            "4\tCarrarini Jacopo\t\n" +
            "5\tCepoiu Rares Claudiu\t\n" +
            "6\tFadda Emanuele\t\n" +
            "7\tGiacomobono Francesco\t\n" +
            "8\tGiovannetti Simone\t\n" +
            "9\tGranata Simone\t\n" +
            "10\tLoreti Federica\t\n" +
            "11\tMalito Alessia\t\n" +
            "12\tPagano Davide\t\n" +
            "13\tPancelli Valerio\t\n" +
            "14\tPetriglia Andrea\t\n" +
            "15\tRuffa Marcus\t\n" +
            "16\tSorrenti Alessio\t\n" +
            "17\tSperandio Dario\t\n" +
            "18\tVania Nicolo'\t\n" +
            "1\tAnzellotti Emanuele\tCLASSE 5E\n" +
            "2\tBaldari Alessia\t\n" +
            "3\tBiancucci Roberto\t\n" +
            "4\tDell'Orme Alessia\t\n" +
            "5\tEson Ifeoluwa Olorunfe\t\n" +
            "6\tFederici Andrea\t\n" +
            "7\tFornari Simone\t\n" +
            "8\tFratini Giulio\t\n" +
            "9\tLombardo Mirko\t\n" +
            "10\tMarcu Gabriel\t\n" +
            "11\tPacifici Valerio\t\n" +
            "12\tPenza Gabriele\t\n" +
            "13\tPorretta Matteo\t\n" +
            "14\tSbardella Mattia\t\n" +
            "15\tTorresan Camilla\t\n" +
            "16\tZattini Aurora\t\n" +
            "1\tDel Signore Alessandro\tCLASSE 5F";

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/orario/1.html"));
        out.println("<html><body>");
        String[] split = testo.split("\n");
        for (String s : split) {
            String[] riga = s.split("\t");
            String num = riga[0];
            String nome = riga[1];
            String classe = riga.length < 3 ? "" : riga[2];
            if (classe.length() > 0) {
                out.println("</table>");
                out.println("<p style=\"page-break-after: always;\">&nbsp;</p>");
                out.println("<h1>" + classe + "</h1>");
                out.println("<table>");
            }
            out.println("<tr><td style='font-size:26px;border:1px solid black'>" + num + "</td><td style='font-size:14.px;border:1px solid black'>" + nome + "</td></tr>");

        }
        out.println("</body></html>");
    }
}
