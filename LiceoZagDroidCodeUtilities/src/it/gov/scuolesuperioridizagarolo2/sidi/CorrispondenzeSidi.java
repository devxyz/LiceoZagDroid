package it.gov.scuolesuperioridizagarolo2.sidi;

import java.util.TreeMap;

/**
 * Created by stefano on 14/11/2018.
 */
public class CorrispondenzeSidi {

    public static final String map =
            "6312521#PICCIRILLI DANIELE#1B#10/2/2003\n" +
                    "2245474#ACANFORA GIORGIO#2A#16/12/2002\n" +
                    "2245189#AGLIETTI VALENTINA#2A#19/6/2003\n" +
                    "9720838#ALOIA MIRKO GIUSEPPE#2A#5/10/2002\n" +
                    "8987470#CHORAZAK SZYMON#2A#7/10/2002\n" +
                    "7678850#CRISTOFARI DANIELE#2A#21/4/2003\n" +
                    "2245286#D'ANGELO ANTONIO#2A#19/5/2003\n" +
                    "2245387#DI LORETO FRANCESCO#2A#11/6/2003\n" +
                    "7678891#PASQUALINI THOMAS#2A#4/5/2003\n" +
                    "8772243#PECETTA DAVID#2A#14/2/2003\n" +
                    "4403257#ROSETTI LUCIA#2A#16/7/2003\n" +
                    "4403260#TUMMINELLO CHIARA#2A#2/1/2003\n" +
                    "9623494#CIRILLO ADRIANO#2B#4/12/2002\n" +
                    "7778127#DI PIETRO NICCOLO'#2B#6/7/2003\n" +
                    "6312568#DORACI ZAKLINA#2B#11/5/2003\n" +
                    "7778132#FIORENTINI GIORGIA#2B#6/6/2003\n" +
                    "8772260#KRUJA DAMJAN#2B#2/7/2003\n" +
                    "7777094#MECANI KETLIN#2B#11/1/2003\n" +
                    "7778150#MONACO MARIO#2B#15/1/2003\n" +
                    "7778158#PAROLARI KEVIN#2B#15/4/2003\n" +
                    "4048539#PASQUAZI ELENA#2B#23/7/2003\n" +
                    "6312577#POPA CRISTIAN EDUARD#2B#5/7/2003\n" +
                    "9289372#TANGO LORENZO#2B#10/4/2003\n" +
                    "8772226#ALEANDRI BEATRICE#2C#26/1/2003\n" +
                    "3844722#COSSERO SOFIA#2C#11/3/2003\n" +
                    "9905822#D'AMICO GIOIA#2C#18/4/2003\n" +
                    "2245287#ESPOSITO BENEDETTA#2C#14/3/2003\n" +
                    "8050564#LATALA KAMIL KRZYSZTOF#2C#17/9/2002\n" +
                    "7778155#NOVELLI FRANCESCO#2C#12/3/2003\n" +
                    "7678886#NOVELLI IRENE#2C#10/3/2003\n" +
                    "4048541#PASQUAZI MAILA#2C#26/3/2003\n" +
                    "3820310#PETTE FRANCESCO#2C#11/7/2003\n" +
                    "3820316#ROMANI ALESSANDRO#2C#5/6/2003\n" +
                    "10041284#SCACCO SOFIA#2C#23/3/2003\n" +
                    "4048548#SCAROZZA LUCA#2C#1/5/2003\n" +
                    "7678829#ASCENZI GIANMARCO#2D#31/7/2003\n" +
                    "7384124#BITSCH FLAVIANO#2D#15/2/2003\n" +
                    "4048528#CICERCHIA NICOLAS#2D#21/7/2003\n" +
                    "7131460#D'ALESSANDRO ANTONIO#2D#10/5/2003\n" +
                    "6312507#DELLE FRATTE FLAVIO#2D#12/6/2003\n" +
                    "2245483#FERRACCI LUDOVICA#2D#3/10/2002\n" +
                    "9905834#GAETANO DAVIDE#2D#25/2/2003\n" +
                    "9331182#GHEORGHIU MARCO DAVIDE#2D#14/2/2003\n" +
                    "4585558#GIORDANO MATTEO#2D#11/2/2003\n" +
                    "2245389#GLIELMI MATTIA#2D#30/4/2003\n" +
                    "7678870#HALILI CRISTIAN#2D#30/3/2003\n" +
                    "7131471#LIGORI PAOLO#2D#11/2/2003\n" +
                    "7384115#MASTROFINI VALERIO#2D#6/12/2002\n" +
                    "8987466#ROMAGNOLI NICOLO'#2D#30/7/2003\n" +
                    "6769216#RUGGIERI SOFIA#2D#17/4/2003\n" +
                    "9905808#SCHIOPPA MATTEO#2D#21/4/2003\n" +
                    "7778177#VALENTE MANUEL#2D#25/6/2003\n" +
                    "4048524#ANGELUCCI GIULIA#2E#22/7/2003\n" +
                    "4403442#BALTAG MARIA LUIZA#2E#18/10/2002\n" +
                    "2245406#CELLINI NICOLO'#2E#4/12/2002\n" +
                    "4048776#CHIALASTRI LAVINIA#2E#14/1/2003\n" +
                    "8987132#DUCA ILARIA#2E#26/6/2003\n" +
                    "7397535#GIULIANI FEDERICO#2E#13/3/2003\n" +
                    "8527896#IANNI NOEMI#2E#7/4/2003\n" +
                    "7383868#MASTROFINI CHIARA#2E#24/3/2003\n" +
                    "8538805#MORIGA GIULIA#2E#20/5/2003\n" +
                    "7383826#ROSSETTI BENEDETTA#2E#27/4/2003\n" +
                    "12620073#RUGGERI MATTEO#2E#18/8/2002\n" +
                    "7777617#SCRIPCA GABRIEL#2E#12/8/2002\n" +
                    "7678906#SIMONINI SARA#2E#20/2/2003\n" +
                    "7383829#TRINCA CARLOTTA#2E#20/3/2003\n" +
                    "9754701#ALEXANDRU MARTINA#2F#10/5/2003\n" +
                    "7591181#ARGIRO' FLAVIO VINCENZO#2F#13/5/2003\n" +
                    "6321978#CAMPOFIORITO GIOIA#2F#7/9/2002\n" +
                    "7678843#COLAGROSSI LORENZO#2F#30/5/2003\n" +
                    "4403387#MUZIO ADRIANA#2F#7/6/2003\n" +
                    "7778154#NATI FEDERICO#2F#13/7/2003\n" +
                    "8991367#PACINI LUCA#2F#1/10/2002\n" +
                    "8772268#PROIETTI BEATRICE#2F#14/7/2003\n" +
                    "11425955#ROSALES GRAZIA ESTHER#2F#26/8/2002\n" +
                    "8115823#SCIACOVELLI DAVIDE#2F#2/1/2003\n" +
                    "7678907#SQUATRITO DAMIANO VINCENT#2F#23/5/2003\n" +
                    "4403258#STERNINI ALESSANDRA#2F#24/6/2003\n" +
                    "7383858#BRAMATI ALESSIO#2G#3/4/2003\n" +
                    "4048583#COSMA FABIANA GIORGIANA#2G#26/4/2003\n" +
                    "7131463#DODARO CHIARA#2G#21/6/2003\n" +
                    "4344553#GALLO GABRIELE#2G#5/2/2003\n" +
                    "4048615#HAVARAJ FIORELA#2G#3/2/2003\n" +
                    "8772259#IANNICCARI GABRIELE#2G#29/7/2003\n" +
                    "6312570#ISTODE GABRIELA#2G#7/2/2003\n" +
                    "7778137#LO VETERE MIA#2G#26/1/2003\n" +
                    "7678877#MARINI BEATRICE#2G#29/1/2003\n" +
                    "4403409#MARRONE MARCO#2G#12/10/2002\n" +
                    "4403251#MORETTI MICHELE#2G#22/3/2003\n" +
                    "7131478#QUARANTA GIANLUCA#2G#18/5/2003\n" +
                    "3958337#ROSSI GIULIA#2G#28/6/2003\n" +
                    "7678904#SBARDELLA NICOLO'#2G#18/6/2003\n" +
                    "11396257#TROVATO IRENE#2G#10/2/2003\n" +
                    "9819270#CASALE LIVIA#2H#2/2/2003\n" +
                    "4585553#DOMOS BIANCA ELENA#2H#11/3/2003\n" +
                    "8772256#FERRACCI FEDERICO#2H#26/7/2003\n" +
                    "7383846#ILARI MATTEO#2H#15/4/2003\n" +
                    "4048537#MAZZENGA FEDERICA#2H#20/7/2003\n" +
                    "7678883#NEAGOE DENIS LORENZO#2H#15/1/2003\n" +
                    "4585530#ODDI SAMUELE#2H#7/3/2003\n" +
                    "4048791#PROIETTI JESSICA#2H#28/5/2003\n" +
                    "8987444#SCARAMELLA ANDREA#2H#29/1/2003\n" +
                    "8772246#SELLAROLI MATTEO#2H#18/5/2003\n" +
                    "6321999#BLASI ANASTASIA#3A#16/10/2002\n" +
                    "9819317#CAFARO PIERFRANCESCO#3A#4/3/2003\n" +
                    "8991097#CALABRO' LUCA#3A#22/10/2002\n" +
                    "4050542#CALIMENT SANDRA GABRIELLA#3A#20/11/2002\n" +
                    "7777686#CARPINETA LIA#3A#14/1/2003\n" +
                    "9720862#D'ANTONIO LORENZO#3A#28/10/2002\n" +
                    "9819297#DI TULLIO ROBERTA#3A#24/10/2002\n" +
                    "2245433#DURASTANTI DAVIDE#3A#16/11/2002\n" +
                    "4403438#TRIONFERA ALESSIO#3A#14/3/2003\n" +
                    "4403439#TRIONFERA FRANCESCO#3A#14/3/2003\n" +
                    "2245496#TROVO' CHRISTIAN#3A#22/8/2002\n" +
                    "4268820#CIANFRIGLIA MICHELE#3B#30/12/2002\n" +
                    "7777688#CIPOLLETTA MATTEO#3B#11/9/2002\n" +
                    "7777670#LONGO BEATRICE#3B#28/8/2002\n" +
                    "7777644#LULLI TOMMASO#3B#25/10/2002\n" +
                    "4050505#MAZZENGA NICOLO'#3B#28/9/2002\n" +
                    "4018427#TERENZI PAOLO#3B#16/9/2002\n" +
                    "7777638#TOMASI DANIELE#3B#5/9/2002\n" +
                    "4403462#TULLI CHRISTIAN#3B#14/8/2002\n" +
                    "2245447#UGOLINI ALESSIO#3B#23/11/2002\n" +
                    "8991292#ZIPPO GABRIELE#3B#8/8/2002\n" +
                    "2245500#BRUMAR SOFIA NICOL#3C#12/12/2002\n" +
                    "7777666#CROPPI GIULIANO#3C#12/10/2002\n" +
                    "6321982#DE MATTEIS GIORGIO#3C#17/11/2002\n" +
                    "4524761#DEL DUCA FEDERICA#3C#22/10/2002\n" +
                    "6321990#MANCINELLI MATTEO#3C#21/8/2002\n" +
                    "4916008#MORICONI TOMMASO CLAUDIO#3C#22/10/2002\n" +
                    "9720888#PETRICCIUOLO MATTEO#3C#15/9/2002\n" +
                    "6321992#PIZZI CATERINA#3C#27/2/2003\n" +
                    "9720915#SANTAPAOLA MANUEL#3C#8/8/2002\n" +
                    "2245472#SERRI ALESSANDRO#3C#13/12/2002\n" +
                    "9720839#BRAVI SOFIA#3D#6/12/2002\n" +
                    "11886099#CALLEGARI LUCA#3D#8/11/2002\n" +
                    "7481094#GALLOTTI FATIMA#3D#27/12/2002\n" +
                    "3846332#GUIDI FRANCESCO#3D#22/9/2002\n" +
                    "7896490#HOZOC CRISTIAN FABIO#3D#13/12/2002\n" +
                    "2245416#PANICCIA LEONARDO#3D#16/1/2003\n" +
                    "4524811#SANNA ILARIA#3D#4/12/2002\n" +
                    "4050526#BATTISTELLI BEATRICE#3E#7/8/2002\n" +
                    "4403419#BERLENGHI DARIO#3E#22/9/2002\n" +
                    "6477992#CAIAZZA RICCARDO FRANCESCO M#3E#7/1/2003\n" +
                    "4018264#CROCENZI FEDERICO#3E#3/11/2002\n" +
                    "3364538#ILARDI CRISTIANO#3E#11/9/2002\n" +
                    "7384132#LAURENTI CAMILLA#3E#8/11/2002\n" +
                    "4050577#PASQUAZI LUCA#3E#13/9/2002\n" +
                    "4403435#PASTACALDI LORENZO#3E#26/10/2002\n" +
                    "7384139#ROMERSI CHIARA#3E#29/9/2002\n" +
                    "2245424#TOSTO LORENZO#3E#22/12/2002\n" +
                    "8778548#ANDRONIC GEORGE#3F#20/11/2002\n" +
                    "8778530#BIELLI VALERIO#3F#5/11/2002\n" +
                    "4050513#ERCOLI DANIELE#3F#17/9/2002\n" +
                    "4018366#OLIVERIO ERIKA#3F#27/12/2002\n" +
                    "8778554#PETRUCCI LORENZO#3F#28/4/2003\n" +
                    "4050585#SEPEHRY SAM#3F#5/10/2002\n" +
                    "9720855#VERGINELLI EDOARDO#3F#23/10/2002\n" +
                    "4524801#CERACCHI BRUNO#3G#15/10/2002\n" +
                    "7370883#FIORETTI MATTEO#3G#28/11/2002\n" +
                    "9720866#GALUPPI MATTEO#3G#28/8/2002\n" +
                    "4403367#MATRIGIANI FRANCESCA#3G#5/2/2003\n" +
                    "6081286#MILEA VALERIA ALEXANDRA#3G#10/10/2002\n" +
                    "9720870#MORGANTE FLAVIA#3G#2/10/2002\n" +
                    "2245492#PASSERI VALERIA#3G#9/11/2002\n" +
                    "2245418#PIZZICONI SARA#3G#25/11/2002\n" +
                    "4050581#PUCCIARELLI MARTINA#3G#3/9/2002\n" +
                    "4050509#RIPANTI ELISA#3G#7/11/2002\n" +
                    "4524812#SPINA FRANCESCO#3G#26/12/2002\n" +
                    "9720875#TACMEANU SARA GIORGIA#3G#2/9/2002\n" +
                    "9720876#TAGLIAFERRI GIULIA#3G#28/9/2002";


    public static TreeMap<String, String> map() {
        TreeMap<String, String> ris = new TreeMap<>();
        final String[] split = map.split("\n");
        for (String s : split) {
            final String[] elems = s.split("#");
            String sidi = elems[0];
            String nominativo = elems[1];
            String classe = elems[2];
            String data = elems[3];
            ris.put(sidi, nominativo + " (" + data + ") " + classe);
        }

        return ris;
    }
}
