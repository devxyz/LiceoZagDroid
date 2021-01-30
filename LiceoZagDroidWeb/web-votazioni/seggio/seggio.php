<html>
<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 20px;
    }

    input[type=submit], .pulsante {
        width: 100%;
        font-size: 20px;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-style: normal;
        text-decoration: none;

    }

    a {
        color: white;
        font-weight: bold;
        text-decoration: none;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    h1 {
        text-align: center;
    }

    div {
        font-size: 20px;
    }

    .div1 {
        border-radius: 5px;
        background-color: #faffbb;
        padding: 20px;
        width: 90%;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<div class="div1">
    <h3>Elezioni 2020 rinnovo del Consiglio d'Istituto dell'<br>I.I.S. "Paolo Borsellino e Giovanni Falcone"<br> per il
        triennio 2020/2021, 2021/2022, 2022/2023</h3>


    <?php
    $tipo = $_POST["tipo"];

    $errore = "";
    if (strlen($tipo) == 0) {
        if (strlen($errore) > 0) {
            $errore = $errore . "<br>";
        }
        $errore = $errore . " Tipologia elettore non specificata";
    }

    ?>

    <?php

    //aggiunge il codice al file
    function aggiungi_codice($code, $file)
    {
        $handle = fopen($file, "a+") or die();
        fwrite($handle, "" . $code . "\n");
        fclose($handle);
    }

    //ritorna 1 se trova il codice nel file, 0 altimenti
    function cerca_codice($code, $file)
    {
        if (!file_exists($file)) {
            return 0;
        }

        $fn = fopen($file, "r");
        $trovato = 0;
        while (!feof($fn)) {

            $result = ltrim(rtrim(fgets($fn)));
            //print $result . "<br>";
            if (strtoupper($result) == strtoupper($code)) {
                $trovato = 1;
                break;
            }
        }
        fclose($fn);
        return $trovato;
    }

    //ritorna un array con tutte le righe del file
    function read_file($file)
    {
        $ris = array();
        if (!file_exists($file)) {
            return $ris;
        }

        $fn = fopen($file, "r");
        while (!feof($fn)) {

            $row = ltrim(rtrim(fgets($fn)));
            $ris[$row] = $row;
        }
        fclose($fn);
        return $ris;
    }

    if (strlen($errore) > 0) {
        print "<h3 style='color:red'> ERRORE:<br> " . $errore . "</h3>"
        ?>
        <div class="pulsante">
            <a href="index.html">
                Torna alla pagina precedente
            </a>
        </div>
        <?php
    } else {
        $errore = "";
        $link_modulo = "";

        //leggo i codici disponibili
        $codici_disponibili = read_file($tipo . ".txt");
        $codici_usati = read_file($tipo . "_used.txt");
/*
        print_r("<br>codici disponibili: ");
        print_r($codici_disponibili);
        print_r("<br>codici usati: ");
        print_r($codici_usati);
*/
        $codice_trovato = "";

        foreach ($codici_disponibili as &$codice_seggio) {
            if (!isset($codici_usati[$codice_seggio])) {
                $codice_trovato = $codice_seggio;
                break;
            }
        }

        if (strlen($codice_seggio) > 0) {
            aggiungi_codice($codice_seggio, $tipo . "_used.txt");

        } else {
            $errore = "Codici terminati. Contattare l'amministratore";
        }



        if (strlen($errore) > 0) {
            print "<h3 style='color:red'> ERRORE:<br> " . $errore . "</h3>"
            ?>

            <div class="pulsante">
                <a href="index.html">
                    Torna alla pagina precedente
                </a>
            </div>

            <?php
        } else {

            $d = new DateTime();



            ?>



            <DIV style="font-size: 40px;font-weight: bold"> <?php echo $d->format('d/m/Y  H:i:s');  ?> </DIV>
            <br>
            <DIV style="font-size: 40px">CODICE PER <?php print $tipo ?> :</DIV>
            <br>
            <DIV style="font-size: 120px;color: red;alignment: center;border: 4px solid black;padding: 5px"><?php print $codice_seggio ?> </DIV>

            <div class="pulsante">
                <a href="index.html">
                    Torna alla pagina precedente
                </a>
            </div>

            <?php
        }
    }

    ?>

</div>
</body>

</html>

