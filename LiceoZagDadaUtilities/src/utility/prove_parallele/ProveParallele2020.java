package utility.prove_parallele;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.*;

public class ProveParallele2020 {
    final static String studenti =
            "Accordino Giulia\t3^E SCIENTIFICO\n" +
                    "Angelucci Giulia\t3^E SCIENTIFICO\n" +
                    "Arquilla Flavio\t3^E SCIENTIFICO\n" +
                    "Baltag Maria Luiza\t3^E SCIENTIFICO\n" +
                    "Carosi Giulia\t3^E SCIENTIFICO\n" +
                    "Cellini Nicolo'\t3^E SCIENTIFICO\n" +
                    "Chialastri Lavinia\t3^E SCIENTIFICO\n" +
                    "Duca Ilaria\t3^E SCIENTIFICO\n" +
                    "Ferracci Alice\t3^E SCIENTIFICO\n" +
                    "Gaffi Valerio\t3^E SCIENTIFICO\n" +
                    "Giuliani Federico\t3^E SCIENTIFICO\n" +
                    "Ianni Noemi\t3^E SCIENTIFICO\n" +
                    "Manni Erica\t3^E SCIENTIFICO\n" +
                    "Mastrofini Chiara\t3^E SCIENTIFICO\n" +
                    "Moriga Giulia\t3^E SCIENTIFICO\n" +
                    "Rossetti Benedetta\t3^E SCIENTIFICO\n" +
                    "Ruggeri Matteo\t3^E SCIENTIFICO\n" +
                    "Scopa Luca\t3^E SCIENTIFICO\n" +
                    "Scripca Gabriel\t3^E SCIENTIFICO\n" +
                    "Simonini Sara\t3^E SCIENTIFICO\n" +
                    "Travaglini Matteo\t3^E SCIENTIFICO\n" +
                    "Trinca Carlotta\t3^E SCIENTIFICO\n" +
                    "Venditti Christian\t3^E SCIENTIFICO\n" +
                    "Auricchio Lorenzo\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Craciun Matteo\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Di Pietro Niccolo'\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Doraci Zaklina\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Evangelista Alessandro\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fiorentini Giorgia\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gentile Gabriel\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lullo Valerio\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mandolfo Michael\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mecani Ketlin\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Monaco Mario\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Parolari Kevin\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pasquazi Elena\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pinci Rachele\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Popa Cristian Eduard\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sanita' Asia\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Santoni Yuri\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Stocco Matteo\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tango Lorenzo\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Velletrani Andrea\t3^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Aleandri Beatrice\t3^C SCIENTIFICO\n" +
                    "Angelucci Filippo\t3^C SCIENTIFICO\n" +
                    "Cossero Sofia\t3^C SCIENTIFICO\n" +
                    "D'Amico Gioia\t3^C SCIENTIFICO\n" +
                    "Esposito Benedetta\t3^C SCIENTIFICO\n" +
                    "Fulcri Michela\t3^C SCIENTIFICO\n" +
                    "Funaro Paolo\t3^C SCIENTIFICO\n" +
                    "Giglio Rachele\t3^C SCIENTIFICO\n" +
                    "Iadonisi Nicola Matteo\t3^C SCIENTIFICO\n" +
                    "Latala Kamil Krzysztof\t3^C SCIENTIFICO\n" +
                    "Marino Federico\t3^C SCIENTIFICO\n" +
                    "Minzocchi Gabriele\t3^C SCIENTIFICO\n" +
                    "Novelli Francesco\t3^C SCIENTIFICO\n" +
                    "Novelli Irene\t3^C SCIENTIFICO\n" +
                    "Pasquazi Maila\t3^C SCIENTIFICO\n" +
                    "Pedrazzini Giorgia\t3^C SCIENTIFICO\n" +
                    "Pette Francesco\t3^C SCIENTIFICO\n" +
                    "Ricci Costantino\t3^C SCIENTIFICO\n" +
                    "Romani Alessandro\t3^C SCIENTIFICO\n" +
                    "Scarozza Luca\t3^C SCIENTIFICO\n" +
                    "Ascenzi Gianmarco\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Beltrami Lorenzo\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bitsch Flaviano\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bonafede Giuseppe\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cicerchia Nicolas\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Correnti Asia\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Curzi Nicolo'\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Alessandro Antonio\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Delle Fratte Flavio\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Di Carlo Francesca\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Donca Andrea Denisa\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ferracci Ludovica\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Frusta Alessandro\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gaetano Davide\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gheorghiu Marco Davide\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Giordano Matteo\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Giuliani Federica\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Glielmi Mattia\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ligori Paolo\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marignoli Mario\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mastrofini Valerio\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nicolaus Leonardo\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pop Maximilian Ioan\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Romagnoli Nicolo'\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ruggieri Sofia\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Schioppa Matteo\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Wehbe Latifa\t3^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bonafede Samuele\t5^A SCIENTIFICO\n" +
                    "Campana Lorenzo\t5^A SCIENTIFICO\n" +
                    "Ciampaglia Gabriele\t5^A SCIENTIFICO\n" +
                    "De Angelis Simone\t5^A SCIENTIFICO\n" +
                    "Del Vescovo Matteo\t5^A SCIENTIFICO\n" +
                    "Di Bernardini Stefano\t5^A SCIENTIFICO\n" +
                    "Di Girolamo Eleonora\t5^A SCIENTIFICO\n" +
                    "Facchini Giulia\t5^A SCIENTIFICO\n" +
                    "Fornari Ludovica\t5^A SCIENTIFICO\n" +
                    "Giovannetti Alessandra\t5^A SCIENTIFICO\n" +
                    "Marian Patrick\t5^A SCIENTIFICO\n" +
                    "Marrocco Matteo\t5^A SCIENTIFICO\n" +
                    "Mattogno Francesco\t5^A SCIENTIFICO\n" +
                    "Michelini Luca\t5^A SCIENTIFICO\n" +
                    "Nardi Leonardo\t5^A SCIENTIFICO\n" +
                    "Nepi Martina\t5^A SCIENTIFICO\n" +
                    "Passeri Alessio\t5^A SCIENTIFICO\n" +
                    "Sesto Nicolo'\t5^A SCIENTIFICO\n" +
                    "Turianelli Mattia\t5^A SCIENTIFICO\n" +
                    "Venerando Marco\t5^A SCIENTIFICO\n" +
                    "Bassani Elisa\t4^E SCIENTIFICO\n" +
                    "Battistelli Beatrice\t4^E SCIENTIFICO\n" +
                    "Berlenghi Dario\t4^E SCIENTIFICO\n" +
                    "Caiazza Riccardo  Francesco Maria\t4^E SCIENTIFICO\n" +
                    "Crocenzi Federico\t4^E SCIENTIFICO\n" +
                    "De Santis Alessandra\t4^E SCIENTIFICO\n" +
                    "Di Berardino Ludovico\t4^E SCIENTIFICO\n" +
                    "Fiscella Elisa\t4^E SCIENTIFICO\n" +
                    "Ilardi Cristiano\t4^E SCIENTIFICO\n" +
                    "Impavidi Gabriele\t4^E SCIENTIFICO\n" +
                    "Laurenti Camilla\t4^E SCIENTIFICO\n" +
                    "Lulli Leonardo\t4^E SCIENTIFICO\n" +
                    "Nanni Camilla\t4^E SCIENTIFICO\n" +
                    "Palombi Asia\t4^E SCIENTIFICO\n" +
                    "Pasquazi Luca\t4^E SCIENTIFICO\n" +
                    "Pastacaldi Lorenzo\t4^E SCIENTIFICO\n" +
                    "Romersi Chiara\t4^E SCIENTIFICO\n" +
                    "Schembri Enrico\t4^E SCIENTIFICO\n" +
                    "Tosto Lorenzo\t4^E SCIENTIFICO\n" +
                    "Zisu Adrian Daniel\t4^E SCIENTIFICO\n" +
                    "Bernassola Gabriele\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cianfriglia Michele\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cipolletta Matteo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Colagrossi Iacopo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fabiani Leonardo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Giovannini Francesca\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Longo Beatrice\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marani Lorenzo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mazzenga Nicolo'\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Menasci' Giulia\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nardi Marco\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pucciarelli Matteo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pulsoni Andrea\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scacco Giovanni\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Schiavella Pierluca\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Serra Lorenzo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Terenzi Paolo\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tomasi Daniele\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tulli Christian\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ugolini Alessio\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zippo Gabriele\t4^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bianco Carlo Vedran Merce'\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bulloni Diego\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carfora Andrea\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ciani Samuele\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Corgnale Nicole\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cunti Giovanni\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Ottavi Federico Maria\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "De Nardis Luca\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fiore Mirco\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Jubril Betty\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Leoni Francesco\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lubello Martina\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Masi Alessio\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mauri Federico\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mencherini Davide\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mureddu Mattia\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nardi Simone\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Rotondi Cristiano\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Santodonato Valerio\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scaramella Diego\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Severoni Alex\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tarquini Eliseo\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tarquini Orlando\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Terradura Erik\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zabayo Emma Osaruonamen\t1^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Alese Federico\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Antonelli Valerio\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Barone Alessio\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Basciani Diego\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Brozzesi Marco\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bussi Samuele\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Buttinelli Sara\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Capodaglio Lorenzo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Castrechini Flavio\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Conejo Lema Wynay Yaryna\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Andrea Margherita\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Attilia Andrea\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Luciani Giorgia\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Luciani Jacopo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Malito Leonardo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mariottini Ivan\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mengarelli Davide\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Moscatelli Samuele\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Paolacci Giacomo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pierini Leonardo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pretelli Nikita\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Proietti Viola\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Radu Sabina\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ruggero Leonardo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tarantino Lorenzo\t1^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Begaj Marko\t1^A SCIENTIFICO\n" +
                    "Conti Veronica\t1^A SCIENTIFICO\n" +
                    "Cusano Dalila\t1^A SCIENTIFICO\n" +
                    "Danaj Matilda\t1^A SCIENTIFICO\n" +
                    "Dervishaliaj Isabela\t1^A SCIENTIFICO\n" +
                    "Digiaro Arianna\t1^A SCIENTIFICO\n" +
                    "Galluccio Ludovica\t1^A SCIENTIFICO\n" +
                    "Giambarveri Giada\t1^A SCIENTIFICO\n" +
                    "Ionascu Valentina\t1^A SCIENTIFICO\n" +
                    "Marzano Francesco\t1^A SCIENTIFICO\n" +
                    "Melli Aurora\t1^A SCIENTIFICO\n" +
                    "Papuc Alessio Gabriele\t1^A SCIENTIFICO\n" +
                    "Petrinca Alessandro\t1^A SCIENTIFICO\n" +
                    "Piretti Davide\t1^A SCIENTIFICO\n" +
                    "Piscopo Angela\t1^A SCIENTIFICO\n" +
                    "Russo Lorenzo\t1^A SCIENTIFICO\n" +
                    "Scacco Alessandro\t1^A SCIENTIFICO\n" +
                    "Tamburrini Camilla\t1^A SCIENTIFICO\n" +
                    "Tassa Paolo\t1^A SCIENTIFICO\n" +
                    "Trovo' Valeria\t1^A SCIENTIFICO\n" +
                    "Verzino Alice\t1^A SCIENTIFICO\n" +
                    "Adamo Beatrice\t4^C SCIENTIFICO\n" +
                    "Brumar Sofia Nicol\t4^C SCIENTIFICO\n" +
                    "Casale Irene\t4^C SCIENTIFICO\n" +
                    "Cilia Silvia\t4^C SCIENTIFICO\n" +
                    "Conte Benedetta\t4^C SCIENTIFICO\n" +
                    "Croppi Giuliano\t4^C SCIENTIFICO\n" +
                    "De Matteis Giorgio\t4^C SCIENTIFICO\n" +
                    "Del Duca Federica\t4^C SCIENTIFICO\n" +
                    "Filosa Chiara\t4^C SCIENTIFICO\n" +
                    "Magistri Lorenzo\t4^C SCIENTIFICO\n" +
                    "Mancinelli Matteo\t4^C SCIENTIFICO\n" +
                    "Moriconi Tommaso Claudio\t4^C SCIENTIFICO\n" +
                    "Pacifici Daniele\t4^C SCIENTIFICO\n" +
                    "Petricciuolo Matteo\t4^C SCIENTIFICO\n" +
                    "Pizzi Caterina\t4^C SCIENTIFICO\n" +
                    "Rosicarelli Matteo\t4^C SCIENTIFICO\n" +
                    "Santapaola Manuel\t4^C SCIENTIFICO\n" +
                    "Scaramella Benedetta\t4^C SCIENTIFICO\n" +
                    "Serri Alessandro\t4^C SCIENTIFICO\n" +
                    "Tagliacozzo Camilla\t4^C SCIENTIFICO\n" +
                    "Tucci Beatrice\t4^C SCIENTIFICO\n" +
                    "Zammarrelli Gabriele\t4^C SCIENTIFICO\n" +
                    "Bravi Sofia\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Callegari Luca\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Coccia Luigi\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Costea Andreea Alexandra\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Di Vito Andrea\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Dodaro Manuel\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gallotti Fatima\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Guidi Francesco\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Hozoc Cristian Fabio\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Igiri Lorenzo\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mecchia Eduardo\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Paniccia Leonardo\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pasquazi Rucsandra Ioana\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Petrassi Francesco\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Romeo Salvatore\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sanna Ilaria\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tucci Matteo\t4^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Arduini Silvia\t1^G SCIENTIFICO\n" +
                    "Battaglini Enrico\t1^G SCIENTIFICO\n" +
                    "Ciccolella Sofia\t1^G SCIENTIFICO\n" +
                    "Coman Adrian Pavel\t1^G SCIENTIFICO\n" +
                    "Gentili Diego\t1^G SCIENTIFICO\n" +
                    "Iadonisi Andrea\t1^G SCIENTIFICO\n" +
                    "Iovino Lucrezia\t1^G SCIENTIFICO\n" +
                    "Lombardo Diego\t1^G SCIENTIFICO\n" +
                    "Lucarelli Federico\t1^G SCIENTIFICO\n" +
                    "Lucia Lorenzo\t1^G SCIENTIFICO\n" +
                    "Mari Diego\t1^G SCIENTIFICO\n" +
                    "Marta Matteo\t1^G SCIENTIFICO\n" +
                    "Nardi Michela\t1^G SCIENTIFICO\n" +
                    "Padovani Tommaso\t1^G SCIENTIFICO\n" +
                    "Raganato Antonio\t1^G SCIENTIFICO\n" +
                    "Scacco Gianmarco\t1^G SCIENTIFICO\n" +
                    "Silvestrini Edoardo\t1^G SCIENTIFICO\n" +
                    "Spina Marco\t1^G SCIENTIFICO\n" +
                    "Zampatori Domiziana\t1^G SCIENTIFICO\n" +
                    "Belli Francesco\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carpineta Federico\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Castellucci Daniel\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cavaricci Noemi\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ceccobelli Serena\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cerbara Matteo\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "De Angelis Fabrizio\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "De Vito Francesco\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fazi Aurora\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gramiccia Giulia\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Graziani Marco\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Kruja Devid\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Miron Laura Romina\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Profeta Miriam\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Romersi Irene\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Topsa Irina\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tulli Marco\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vincenzi Luca\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zefi Emiliano\t2^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bidetta Alessio\t2^A SCIENTIFICO\n" +
                    "Blasi Mattia\t2^A SCIENTIFICO\n" +
                    "Cangini Tiziano\t2^A SCIENTIFICO\n" +
                    "Cappilli Alessandro\t2^A SCIENTIFICO\n" +
                    "Cardone Alessandro\t2^A SCIENTIFICO\n" +
                    "Carletti Gaia\t2^A SCIENTIFICO\n" +
                    "Cecconi Camilla\t2^A SCIENTIFICO\n" +
                    "Coccia Giulia\t2^A SCIENTIFICO\n" +
                    "Colagrossi Arianna\t2^A SCIENTIFICO\n" +
                    "Di Nunzio Aurora\t2^A SCIENTIFICO\n" +
                    "Ferrera Elisa\t2^A SCIENTIFICO\n" +
                    "Gambini Aurora\t2^A SCIENTIFICO\n" +
                    "Lupicuti Francesco\t2^A SCIENTIFICO\n" +
                    "Madera Francesco\t2^A SCIENTIFICO\n" +
                    "Mauri Pietro Francesco\t2^A SCIENTIFICO\n" +
                    "Mizzon Luca\t2^A SCIENTIFICO\n" +
                    "Moccia Davide\t2^A SCIENTIFICO\n" +
                    "Palmiero Salvatore Matteo\t2^A SCIENTIFICO\n" +
                    "Piermattei Adriano\t2^A SCIENTIFICO\n" +
                    "Scarozza Luca\t2^A SCIENTIFICO\n" +
                    "Aiftincai Giulia\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Alicata Mattia\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bangrazi Lorenzo Maria\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Berardi Leonardo\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bernardini Marco\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Birzu Emanuela\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Calcagna Chiara\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carbonari Claudio\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Chrichmi Idriss\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Conforti Michela\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Corradino Michele\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Della Bella Gioele\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ermini Mauro\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Hriscu Giulia Teodora\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ionascu Alessandra\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mannara Lorenzo\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Oliverio Denise\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Oliverio Desire'E\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pasquariello Antonio\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sabelli Simone\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Schiona Giorgia\t2^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Alese Nico\t5^C SCIENTIFICO\n" +
                    "Bagatti Francesco\t5^C SCIENTIFICO\n" +
                    "Belmonte Damiano\t5^C SCIENTIFICO\n" +
                    "Bencivenga Agnese\t5^C SCIENTIFICO\n" +
                    "Bonanni Rachele\t5^C SCIENTIFICO\n" +
                    "Cantelli Mario\t5^C SCIENTIFICO\n" +
                    "Cecconi Andrea\t5^C SCIENTIFICO\n" +
                    "Cetorelli Andrea\t5^C SCIENTIFICO\n" +
                    "D'Attilia Martina\t5^C SCIENTIFICO\n" +
                    "De Rocchis Alessio\t5^C SCIENTIFICO\n" +
                    "Di Nunzio Marcello\t5^C SCIENTIFICO\n" +
                    "Faraglia Irene\t5^C SCIENTIFICO\n" +
                    "Ianniccari Roberta\t5^C SCIENTIFICO\n" +
                    "Panzironi Gabriele\t5^C SCIENTIFICO\n" +
                    "Remigi Mattia\t5^C SCIENTIFICO\n" +
                    "Spagnulo Laura\t5^C SCIENTIFICO\n" +
                    "Zappia Lorenzo\t5^C SCIENTIFICO\n" +
                    "Antonellini Federico\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Boccia Valentina\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carrarini Jacopo\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cepoiu Rares Claudiu\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fadda Emanuele\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Giacomobono Francesco\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Giovannetti Simone\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Granata Simone\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Loreti Federica\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Malito Alessia\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pancelli Valerio\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Petriglia Andrea\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ruffa Marcus\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sorrenti Alessio\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sperandio Dario\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vania Nicolo'\t5^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Buhna Artur\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Caporossi Tiziano\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carrarini Federico\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Casale Livia\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Angelo Giacomo\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Dal Vecchio Dylan\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Domos Bianca Elena\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ferracci Federico\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ilari Matteo\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lisi Silvia\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Macarra Giulia\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mazzenga Federica\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Meoli Alice\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Milana Manila\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Neagoe Denis Lorenzo\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Oddi Samuele\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pinci Marta\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Proietti Jessica\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scaramella Andrea\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sellaroli Matteo\t3^H SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Aglietti Valentina\t3^A SCIENTIFICO\n" +
                    "Catalani Filippo\t3^A SCIENTIFICO\n" +
                    "Cristofari Daniele\t3^A SCIENTIFICO\n" +
                    "D'Angelo Antonio\t3^A SCIENTIFICO\n" +
                    "Di Loreto Francesco\t3^A SCIENTIFICO\n" +
                    "Dinu Mihai Sebastian\t3^A SCIENTIFICO\n" +
                    "Giusti Anna\t3^A SCIENTIFICO\n" +
                    "Hranaceru Andreea Larisa\t3^A SCIENTIFICO\n" +
                    "Ingrosso Angelo Emanuele\t3^A SCIENTIFICO\n" +
                    "Macarra Aurora\t3^A SCIENTIFICO\n" +
                    "Mancini Manuel\t3^A SCIENTIFICO\n" +
                    "Meoli Gianluigi\t3^A SCIENTIFICO\n" +
                    "Minna Danilo\t3^A SCIENTIFICO\n" +
                    "Nanni Ilaria\t3^A SCIENTIFICO\n" +
                    "Nardi Camilla\t3^A SCIENTIFICO\n" +
                    "Pasqualini Thomas\t3^A SCIENTIFICO\n" +
                    "Pecetta David\t3^A SCIENTIFICO\n" +
                    "Piras Mauro\t3^A SCIENTIFICO\n" +
                    "Rosetti Lucia\t3^A SCIENTIFICO\n" +
                    "Salomone Chiara\t3^A SCIENTIFICO\n" +
                    "Serra Alessandro\t3^A SCIENTIFICO\n" +
                    "Tumminello Chiara\t3^A SCIENTIFICO\n" +
                    "Alexandru Martina\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Argiro' Flavio Vincenzo\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Colagrossi Lorenzo\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "D'Uffizi Roberto\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ercoli Daniele\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Falco Ilaria Pia\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Forgione Cristiano\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Haval Andrij\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lore' Luca\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Luciani Gianmarco\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lupi Pier Giorgio\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Muzio Adriana\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nati Federico\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pacini Luca\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Proietti Beatrice\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scaringi Chiara\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sciacovelli Davide\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Simone Pierluigi\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sternini Alessandra\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vannini Emanuele\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vlusha Kristi\t3^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Anzellotti Emanuele\t5^E SCIENTIFICO\n" +
                    "Baldari Alessia\t5^E SCIENTIFICO\n" +
                    "Biancucci Roberto\t5^E SCIENTIFICO\n" +
                    "Dell'Orme Alessia\t5^E SCIENTIFICO\n" +
                    "Eson Ifeoluwa Olorunfe\t5^E SCIENTIFICO\n" +
                    "Federici Andrea\t5^E SCIENTIFICO\n" +
                    "Fornari Simone\t5^E SCIENTIFICO\n" +
                    "Fratini Giulio\t5^E SCIENTIFICO\n" +
                    "Lombardo Mirko\t5^E SCIENTIFICO\n" +
                    "Marcu Gabriel\t5^E SCIENTIFICO\n" +
                    "Monni Giorgio\t5^E SCIENTIFICO\n" +
                    "Pacifici Valerio\t5^E SCIENTIFICO\n" +
                    "Penza Gabriele\t5^E SCIENTIFICO\n" +
                    "Porretta Matteo\t5^E SCIENTIFICO\n" +
                    "Sbardella Mattia\t5^E SCIENTIFICO\n" +
                    "Torresan Camilla\t5^E SCIENTIFICO\n" +
                    "Zattini Aurora\t5^E SCIENTIFICO\n" +
                    "Benegiano Denise\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Campoli Francesco\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cesarotti Emma\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cipriani Marco\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Del Signore Alessandro\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Di Cesare Simone\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Libianchi Chiara Maria\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lunati Edoardo\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mas Emanuelcarlo\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nestola Matteo\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nicolosi Gabriele\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pescetelli Alessandro\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ricci Alessia\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Rossi Gemma\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scipioni Simone\t5^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bramati Alessio\t3^G SCIENTIFICO\n" +
                    "Candido Andrea\t3^G SCIENTIFICO\n" +
                    "Catalani Paolo\t3^G SCIENTIFICO\n" +
                    "Ceracchi Bruno\t3^G SCIENTIFICO\n" +
                    "Cerci Rachele\t3^G SCIENTIFICO\n" +
                    "Cesaretti Greta\t3^G SCIENTIFICO\n" +
                    "Cosma Fabiana Giorgiana\t3^G SCIENTIFICO\n" +
                    "De Marzi Chiara\t3^G SCIENTIFICO\n" +
                    "Dodaro Chiara\t3^G SCIENTIFICO\n" +
                    "Gallo Gabriele\t3^G SCIENTIFICO\n" +
                    "Giannini Valentino\t3^G SCIENTIFICO\n" +
                    "Havaraj Fiorela\t3^G SCIENTIFICO\n" +
                    "Ianniccari Gabriele\t3^G SCIENTIFICO\n" +
                    "Istode Gabriela\t3^G SCIENTIFICO\n" +
                    "Lo Vetere Mia\t3^G SCIENTIFICO\n" +
                    "Marini Beatrice\t3^G SCIENTIFICO\n" +
                    "Marrone Marco\t3^G SCIENTIFICO\n" +
                    "Moretti Michele\t3^G SCIENTIFICO\n" +
                    "Quaranta Gianluca\t3^G SCIENTIFICO\n" +
                    "Ronci Erica\t3^G SCIENTIFICO\n" +
                    "Rossi Giulia\t3^G SCIENTIFICO\n" +
                    "Sbardella Nicolo'\t3^G SCIENTIFICO\n" +
                    "Spina Francesco\t3^G SCIENTIFICO\n" +
                    "Trovato Irene\t3^G SCIENTIFICO\n" +
                    "Valente Sofia\t3^G SCIENTIFICO\n" +
                    "Cardarelli Chiara\t2^C SCIENTIFICO\n" +
                    "Checconi Marta\t2^C SCIENTIFICO\n" +
                    "Cinili Serena\t2^C SCIENTIFICO\n" +
                    "Crisan Isabella\t2^C SCIENTIFICO\n" +
                    "De Angelis Luna\t2^C SCIENTIFICO\n" +
                    "De Nicola Sophia\t2^C SCIENTIFICO\n" +
                    "Federici Giulia\t2^C SCIENTIFICO\n" +
                    "Giacco Emma\t2^C SCIENTIFICO\n" +
                    "Giannetti Vanessa\t2^C SCIENTIFICO\n" +
                    "Lagana' Giulia\t2^C SCIENTIFICO\n" +
                    "Maraldi Alessia\t2^C SCIENTIFICO\n" +
                    "Marini Matteo\t2^C SCIENTIFICO\n" +
                    "Mattogno Nicolo'\t2^C SCIENTIFICO\n" +
                    "Moretti Isabella\t2^C SCIENTIFICO\n" +
                    "Passi Marika\t2^C SCIENTIFICO\n" +
                    "Petrassi Francesco Maria\t2^C SCIENTIFICO\n" +
                    "Salituro Sara\t2^C SCIENTIFICO\n" +
                    "Salvati Andrea\t2^C SCIENTIFICO\n" +
                    "Spataro Beatrice\t2^C SCIENTIFICO\n" +
                    "Tolli Giulia\t2^C SCIENTIFICO\n" +
                    "Volpe Francesca\t2^C SCIENTIFICO\n" +
                    "Aron Daniele\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Attilia Desire'E\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bartalini Lorenzo\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Celletti Cristina\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ciavardini Gianluca\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Coccia Luca\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cristino Francesco Mattia\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Dantimi Damiano\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "De Francesco Marco\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Del Duca Gabriele\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Fortunato Gabriele\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Kozar Bartosz\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Manca Rocco\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mancinelli Jacopo\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mastrantonio Valerio\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Orlandi Tiziano\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Popa Razvan\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pucci Alessandro\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Rosicarelli Emanuele\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sarandrea Gabriele\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scacco Federico\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scarano Luca\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tolfa Benedetta\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Trinchieri Luca\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vittucci Damiano\t2^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bascandura Dennis Andrei\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Basto Martina\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Benini Valentina\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Boccia Gabriele\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carbone Andrea\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Carbone Matteo\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Chiapparelli Paolo\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Chiapparelli Simone\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cristofari Alessandro\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Danaj Mateo\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Esposito Alessio\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Federici Gianluca\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Foschi Francesco\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Leone Francesca\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Lucarelli Gabriele\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mammetti Filippo\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mancinotti Christian\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marcelli Alessandro\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Michelessi Francesco\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mistura Diego\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nobile Luca\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Orsi Angelica\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Penna Isabel\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sabellico Giovanna\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Scaramella Federico\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sgro' Lorenzo\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tamiro Federico\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zimpi Tommaso\t1^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Brandolini Giulia\t1^E SCIENTIFICO\n" +
                    "Camilloni Giulia\t1^E SCIENTIFICO\n" +
                    "Campoli Alessandro\t1^E SCIENTIFICO\n" +
                    "Chiacchiararelli Erica\t1^E SCIENTIFICO\n" +
                    "Docan Denisa Alexandra\t1^E SCIENTIFICO\n" +
                    "Filosa Federica\t1^E SCIENTIFICO\n" +
                    "Fratini Davide\t1^E SCIENTIFICO\n" +
                    "Gionti Pierluigi\t1^E SCIENTIFICO\n" +
                    "Giuliani Diego\t1^E SCIENTIFICO\n" +
                    "Marcotulli Daniele\t1^E SCIENTIFICO\n" +
                    "Masi Francesco\t1^E SCIENTIFICO\n" +
                    "Monteiro Soares Emanuele\t1^E SCIENTIFICO\n" +
                    "Pacifici Mirko\t1^E SCIENTIFICO\n" +
                    "Parisi Giovanni\t1^E SCIENTIFICO\n" +
                    "Piras Sofia\t1^E SCIENTIFICO\n" +
                    "Rata Enrika\t1^E SCIENTIFICO\n" +
                    "Rossetti Davide\t1^E SCIENTIFICO\n" +
                    "Salerno Gabriele\t1^E SCIENTIFICO\n" +
                    "Spurio Polini Francesco\t1^E SCIENTIFICO\n" +
                    "Tempesta Anna\t1^E SCIENTIFICO\n" +
                    "Venturini Damiano\t1^E SCIENTIFICO\n" +
                    "Vitiello Roberta\t1^E SCIENTIFICO\n" +
                    "Bartolomucci Giulia\t4^A SCIENTIFICO\n" +
                    "Blasi Anastasia\t4^A SCIENTIFICO\n" +
                    "Cafaro Pierfrancesco\t4^A SCIENTIFICO\n" +
                    "Caliment Sandra Gabriella\t4^A SCIENTIFICO\n" +
                    "Calvano Luisa\t4^A SCIENTIFICO\n" +
                    "Cappilli Antonio\t4^A SCIENTIFICO\n" +
                    "Carpineta Lia\t4^A SCIENTIFICO\n" +
                    "D'Antonio Lorenzo\t4^A SCIENTIFICO\n" +
                    "Di Tullio Roberta\t4^A SCIENTIFICO\n" +
                    "Durastanti Davide\t4^A SCIENTIFICO\n" +
                    "Maiorano Irene\t4^A SCIENTIFICO\n" +
                    "Mariani Alessandro\t4^A SCIENTIFICO\n" +
                    "Massaro Massimo\t4^A SCIENTIFICO\n" +
                    "Menichelli Chiara\t4^A SCIENTIFICO\n" +
                    "Minna Manila\t4^A SCIENTIFICO\n" +
                    "Pinci Emanuele\t4^A SCIENTIFICO\n" +
                    "Sapienza Mattia\t4^A SCIENTIFICO\n" +
                    "Sebastianelli Giammarco\t4^A SCIENTIFICO\n" +
                    "Stivala Lorenzo\t4^A SCIENTIFICO\n" +
                    "Trionfera Alessio\t4^A SCIENTIFICO\n" +
                    "Trionfera Francesco\t4^A SCIENTIFICO\n" +
                    "Trovo' Christian\t4^A SCIENTIFICO\n" +
                    "Abunei David\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Andronic George\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bielli Valerio\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bortoloni Ilaria\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bruni Leonardo\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Censi Marco\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Cerboni Federico\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mariani Dennis\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Oberi Nicolas\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Okshtuni Denis\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Oliverio Erika\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Passa Nikolas\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Petrucci Lorenzo\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sepehry Sam\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Verginelli Edoardo\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Vinattieri Mattia\t4^F SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Aguiari Luca\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Alese Alessio\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Bocci Francesco\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Buo Manuel\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Caloiaro Matteo\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ceccobelli Mattia\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ceresini Nicolo'\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ciubotariu Eric Amiel\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Corciulo Nicholas\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Dappi Francesco\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Draghia Robert Andrei\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Leo Ludovico\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marchetti Alessio\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marsili Giorgia Erminia\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marta Alessandro\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mattozzi Giorgia\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Negro Ludovica\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Nicoletti Valerio\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Onorati Francesco\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Palazzi Alessia\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Palone Francesco\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Paunescu Cosmin Andreea\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Petrolati Leonardo\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Picarelli Matteo\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pichi Lorenzo\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pompilio Alessandro\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Randolfi Luca\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Staiti Alessio\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tombolillo Danilo\t1^D SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Antonelli Leonardo\t1^C SCIENTIFICO\n" +
                    "Aveni Aurora\t1^C SCIENTIFICO\n" +
                    "Bartolomei Beatrice\t1^C SCIENTIFICO\n" +
                    "Caminiti Simone\t1^C SCIENTIFICO\n" +
                    "Colaiacomo Chiara\t1^C SCIENTIFICO\n" +
                    "Ferrera Lorenzo\t1^C SCIENTIFICO\n" +
                    "Florio Gaia\t1^C SCIENTIFICO\n" +
                    "Ghergheluca Noemi\t1^C SCIENTIFICO\n" +
                    "Giacoppo Elia\t1^C SCIENTIFICO\n" +
                    "Gradozzi Ernesto\t1^C SCIENTIFICO\n" +
                    "Ianniccari Emanuele\t1^C SCIENTIFICO\n" +
                    "Kola Laura\t1^C SCIENTIFICO\n" +
                    "Mancinelli Giacomo\t1^C SCIENTIFICO\n" +
                    "Paci Martina\t1^C SCIENTIFICO\n" +
                    "Pompilio Silvia\t1^C SCIENTIFICO\n" +
                    "Recchia Giordano\t1^C SCIENTIFICO\n" +
                    "Riccardi Federica\t1^C SCIENTIFICO\n" +
                    "Ricozzi Emanuele\t1^C SCIENTIFICO\n" +
                    "Sambucini Riccardo\t1^C SCIENTIFICO\n" +
                    "Scardella Alessio\t1^C SCIENTIFICO\n" +
                    "Toaca Savelie\t1^C SCIENTIFICO\n" +
                    "Visconti Edoardo\t1^C SCIENTIFICO\n" +
                    "Bacci Matteo\t2^E SCIENTIFICO\n" +
                    "Cartoni Alessia\t2^E SCIENTIFICO\n" +
                    "Chialastri Lorenzo\t2^E SCIENTIFICO\n" +
                    "Coccia Lorenzo\t2^E SCIENTIFICO\n" +
                    "Di Vincenzo Samuele\t2^E SCIENTIFICO\n" +
                    "Gordiani Giorgia\t2^E SCIENTIFICO\n" +
                    "Lippi Lorenzo\t2^E SCIENTIFICO\n" +
                    "Macarra Federico\t2^E SCIENTIFICO\n" +
                    "Manea Laurentiu Valentin\t2^E SCIENTIFICO\n" +
                    "Mariani Federica\t2^E SCIENTIFICO\n" +
                    "Mecchia Simone\t2^E SCIENTIFICO\n" +
                    "Nucci Sara\t2^E SCIENTIFICO\n" +
                    "Omana Altuve Alejandro Jose\t2^E SCIENTIFICO\n" +
                    "Pucci Martina\t2^E SCIENTIFICO\n" +
                    "Regoli Patrizio\t2^E SCIENTIFICO\n" +
                    "Scarciotti Alessandro\t2^E SCIENTIFICO\n" +
                    "Schiavella Beatrice\t2^E SCIENTIFICO\n" +
                    "Vecchi Elvira\t2^E SCIENTIFICO\n" +
                    "Ziffi Alessio\t2^E SCIENTIFICO\n" +
                    "Achitei Fabrizio\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Aloe Luna\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Andronic Marta\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Capatina Eduard\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Careri Lorenzo\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Filacchione Gabriele\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Frattarola Rebecca\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Gagliardi Mattia\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Ienne Gianluigi\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Maccaroni Daniele\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Maier Tiziano\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Mariani Azzurra\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Marta Elena\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Martini Nicolo'\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Massimi Diego Alessio\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Petrucci Sara\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Piazzai Francesco\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Piccirilli Daniele\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Pizziconi Leonardo\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Sbordone Andrey\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Tololoi Claudio Markus\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Trani Alessio\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zaratti Davide\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Zarlenga Samuele\t2^B SCIENTIFICO OPZIONE SCIENZE APPLICATE\n" +
                    "Boccia Michela\t4^G SCIENTIFICO\n" +
                    "Cosco Mirko\t4^G SCIENTIFICO\n" +
                    "Di Serafino Roberta\t4^G SCIENTIFICO\n" +
                    "Fazi Flavia\t4^G SCIENTIFICO\n" +
                    "Fioretti Matteo\t4^G SCIENTIFICO\n" +
                    "Galuppi Matteo\t4^G SCIENTIFICO\n" +
                    "Matrigiani Francesca\t4^G SCIENTIFICO\n" +
                    "Milea Valeria Alexandra\t4^G SCIENTIFICO\n" +
                    "Morgante Flavia\t4^G SCIENTIFICO\n" +
                    "Olenic Cezaria Larisa\t4^G SCIENTIFICO\n" +
                    "Passeri Valeria\t4^G SCIENTIFICO\n" +
                    "Pinci Anna Rita\t4^G SCIENTIFICO\n" +
                    "Pizziconi Sara\t4^G SCIENTIFICO\n" +
                    "Pucciarelli Martina\t4^G SCIENTIFICO\n" +
                    "Ripanti Elisa\t4^G SCIENTIFICO\n" +
                    "Tagliaferri Giulia\t4^G SCIENTIFICO";

    public static List<StudenteProveParallele> leggiStudenti() {
        List<StudenteProveParallele> ris = new ArrayList<>();
        String[] split = studenti.split("\n");
        for (String x : split) {
            if (x.trim().length() == 0) continue;
            String[] split1 = x.trim().split("\t");
            String classe = split1[1].substring(0, 3).replace("^", "");
            ris.add(new StudenteProveParallele(split1[0], ClassData.search(classe)));
        }
        Collections.sort(ris);
        return ris;
    }

    public static Map<RoomData, List<StudenteProveParallele>> assegnaAule(List<StudenteProveParallele> studenti, RoomData... aule) {
        return assegnaAule(studenti, Arrays.asList(aule));
    }

    public static Map<RoomData, List<StudenteProveParallele>> assegnaAule(List<StudenteProveParallele> studenti, List<RoomData> aule) {
        int tot = 0;
        for (RoomData classData : aule) {
            tot += classData.maxStudents;
        }
        if (tot < studenti.size()) {
            throw new IllegalArgumentException("Errore occupazione. Numero studenti: " + studenti.size() + ", posti:" + tot);
        }

        Map<RoomData, List<StudenteProveParallele>> ris = new TreeMap<>();
        for (RoomData z : aule) {
            ris.put(z, new ArrayList<>());
        }

        int i = 0;
        for (Map.Entry<RoomData, List<StudenteProveParallele>> e : ris.entrySet()) {
            RoomData aula = e.getKey();
            for (int j = 0; j < aula.maxStudents; j++) {
                if (i >= studenti.size()) break;
                e.getValue().add(studenti.get(i));
                i++;
            }
            if (i >= studenti.size()) break;
        }

        return ris;
    }

    private static StudenteProveParallele searchFirst(List<StudenteProveParallele> aa, ClassData c) {
        for (StudenteProveParallele x : aa) {
            if (x.classe == c) return x;
        }
        return null;
    }

    private static StudenteProveParallele searchLast(List<StudenteProveParallele> aa, ClassData c) {
        for (int i = aa.size() - 1; i >= 0; i--) {
            StudenteProveParallele x = aa.get(i);
            if (x.classe == c) return x;
        }
        return null;
    }

    public static String report(String note, Map<RoomData, List<StudenteProveParallele>> e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3> " + note + " </h3>").append("\n");
        sb.append("<ul>");
        for (Map.Entry<RoomData, List<StudenteProveParallele>> x : e.entrySet()) {
            sb.append("<li><b>Aula " + x.getKey().roomName).append(" (" + x.getKey().maxStudents + " posti, " + x.getValue().size() + " studenti)").append("</b><ul>\n");
            List<StudenteProveParallele> value = new ArrayList<>(x.getValue());
            Collections.sort(value);
            if (value.size() > 0) {
                Set<ClassData> classi = new TreeSet<>();
                for (StudenteProveParallele ee : value) {
                    classi.add(ee.classe);
                }


                for (ClassData c : classi) {
                    StudenteProveParallele from = searchFirst(value, c);
                    StudenteProveParallele to = searchLast(value, c);
                    sb.append("<li> <b>CLASSE " + from.classe + "</b>");
                    sb.append(" da " + from.cognome_nome + " ").append(" - ");
                    sb.append(" a " + to.cognome_nome + " ").append("</li>\n");
                }

            }
            sb.append("</ul>");
        }
        sb.append("</ul>");

        return sb.toString();
    }

    public static String reportPerAula(Map<String, StringBuilder> ris, String note, Map<RoomData, List<StudenteProveParallele>> e) {

        for (Map.Entry<RoomData, List<StudenteProveParallele>> x : e.entrySet()) {
            StringBuilder sb = ris.get(x.getKey().roomName);
            if (sb == null) {
                sb = new StringBuilder();
                ris.put(x.getKey().roomName, sb);
            }
            sb.append("<h3><b> " + note + " </b></h3>").append("\n");
            //sb.append("<b>Aula " + x.getKey().roomName).append(" (" + x.getKey().maxStudents + " posti, " + x.getValue().size() + " studenti)").append("</b>\n");
            List<StudenteProveParallele> value = new ArrayList<>(x.getValue());
            Collections.sort(value);
            if (value.size() > 0) {
                Set<ClassData> classi = new TreeSet<>();
                for (StudenteProveParallele ee : value) {
                    classi.add(ee.classe);
                }


                for (ClassData c : classi) {
                    StudenteProveParallele from = searchFirst(value, c);
                    StudenteProveParallele to = searchLast(value, c);
                    sb.append("<li><b>CLASSE " + from.classe + "</b>");
                    sb.append(" da " + from.cognome_nome + " ").append(" - ");
                    sb.append(" a " + to.cognome_nome + " ").append("</li>\n");
                }

            }
        }

        StringBuilder sss = new StringBuilder();
        for (Map.Entry<String, StringBuilder> e2 : ris.entrySet()) {
            sss.append("<h2>" + e2.getKey() + "</h2");
            sss.append("<ul>" + e2.getValue() + "</ul>");
            sss.append("\n<div style='page-break-after:always'></div>\n");
        }

        return sss.toString();
    }

    public static String reportShort(String note, Map<RoomData, List<StudenteProveParallele>> e) {
        StringBuilder sb = new StringBuilder();
        sb.append(note + ":").append("\n");

        for (Map.Entry<RoomData, List<StudenteProveParallele>> x : e.entrySet()) {
            sb.append(" -- Aula " + x.getKey().roomName + " (");
            List<StudenteProveParallele> value = new ArrayList<>(x.getValue());
            Collections.sort(value);
            if (value.size() > 0) {
                Set<ClassData> classi = new TreeSet<>();
                for (StudenteProveParallele ee : value) {
                    classi.add(ee.classe);
                }


                for (ClassData c : classi) {
                    StudenteProveParallele from = searchFirst(value, c);
                    StudenteProveParallele to = searchLast(value, c);
                    sb.append(from.classe + " ");
                }

            }
            sb.append(")\n");
        }

        return sb.toString();
    }


    public static String reportFull(String note, Map<RoomData, List<StudenteProveParallele>> e) {
        StringBuilder sb = new StringBuilder();
        sb.append(note).append("\n");
        for (Map.Entry<RoomData, List<StudenteProveParallele>> x : e.entrySet()) {
            sb.append("Aula " + x.getKey().roomName).append(" (" + x.getKey().maxStudents + " posti, " + x.getValue().size() + " studenti)").append("\n");
            List<StudenteProveParallele> value = new ArrayList<>(x.getValue());
            Collections.sort(value);
            for (StudenteProveParallele xx : value) {
                sb.append(xx.cognome_nome + " " + xx.classe).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public static List<StudenteProveParallele> filter(List<StudenteProveParallele> ss, ClassData... list) {
        return filter(ss, Arrays.asList(list));
    }

    public static List<StudenteProveParallele> filter(List<StudenteProveParallele> ss, List<ClassData> list) {
        List<StudenteProveParallele> ris = new ArrayList<>();
        for (StudenteProveParallele s : ss) {
            if (list.contains(s.classe)) {
                ris.add(s);
            }
        }
        return ris;
    }

    public static void main(String[] args) {

/*        RoomData[] aule_prime = new RoomData[]{RoomData.C16, RoomData.C17, RoomData.C18, RoomData.C19, RoomData.C20, RoomData.E27, RoomData.E28};
        RoomData[] aule_seconde = new RoomData[]{RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B12, RoomData.B13,};

        List<StudenteProveParallele> studenti = leggiStudenti();
        String note = "test";
        List<StudenteProveParallele> studentix = filter(studenti, ClassData.searchPerAnnoCorso(1));
        String report = assegnaAuleReportFull(note, studentix, aule_prime);
        String report2 = assegnaAuleReport(note, studentix, aule_prime);
        System.out.println("=============================");
        System.out.println(report);
        System.out.println("=============================");
        System.out.println(report2);*/
    }

    public static String assegnaAuleReportFull(String note, List<StudenteProveParallele> studentix, RoomData[] aule_prime) {
        return reportFull(note, assegnaAule(studentix, aule_prime));
    }

    public static String assegnaAuleReport(String note, List<StudenteProveParallele> studentix, RoomData[] aule_prime) {
        return report(note, assegnaAule(studentix, aule_prime));
    }

    public static String assegnaAuleReportShort(String note, List<StudenteProveParallele> studentix, RoomData[] aule_prime) {
        return reportShort(note, assegnaAule(studentix, aule_prime));
    }

    public static String assegnaAuleReportPerAula(Map<String, StringBuilder> ris, String note, List<StudenteProveParallele> studentix, RoomData[] aule_prime) {
        return reportPerAula(ris, note, assegnaAule(studentix, aule_prime));
    }
}
