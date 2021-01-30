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

    input[type=submit], .pulsante{
        width: 100%;
        font-size: 20px;
        background-color: #673ab7;
        color: white;
        padding: 14px 14px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-style: normal;
        text-decoration: none;

    }

    a{
        color: white;
        font-weight: bold;
        text-decoration: none;
    }

    input[type=submit]:hover {
        background-color: #673ab7;
    }

    h1 {
        text-align: center;
    }

    .div2{
        background-color: #ede7f6;
        height: 20px;
    }


    div {
        font-size: 20px;
        width: 100%;
    }

    body{
        background-color: #ede7f6;
    }


    .div1 {
        border-radius: 5px;
        background-color: white;
        padding: 20px;
        width: 60%;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<div class="div1">
    <h3>Elezioni 2020 rinnovo del Consiglio d'Istituto dell'I.I.S. "Paolo Borsellino e Giovanni Falcone" per il
        triennio 2020/2021, 2021/2022, 2022/2023</h3>

    <hr>

    <?php
    $codice = $_POST["codice"];
    $tipo = $_POST["tipo"];

    /*print "codice:" . $codice . "<br>";
    print "tipo:" . $tipo . "<br>";
*/
    $errore = "";
    if (strlen($codice) == 0) {
        if (strlen($errore) > 0) {
            $errore = $errore . "<br>";
        }
        $errore = $errore . " Codice non inserito";
    }
    if (strlen($tipo) == 0) {
        if (strlen($errore) > 0) {
            $errore = $errore . "<br>";
        }
        $errore = $errore . " Tipologia elettore non specificata";
    }

    ?>

    <?php

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

    if (strlen($codice)>0 && cerca_codice($codice, "used_code.txt") == 1) {
        if (strlen($errore) > 0) {
            $errore = $errore . "<br>";
        }
        $errore = $errore . " Codice già utilizzato";
    }



    if (strlen($errore) > 0) {
        print "<h3 style='color:red'> ERRORE:<br> " . $errore . "</h3>"
        ?>
        <form action="index.html" method="get">
            <input type="submit" value="Torna alla pagina di accesso">
        </form>
        <?php
    } else {
        $errore = "";
        $link_modulo = "";

        //cerca il codice nel file
        if (cerca_codice($codice, $tipo . ".txt") == 1) {
            switch ($tipo) {
                case "ATA":
                    $link_modulo = "https://docs.google.com/forms/d/e/1FAIpQLSfJVNsg45VWZn3UmhyTDq8FEVy5dqq1UxmRifQSJ2BmxRNMIg/viewform?entry.345901760=" . $codice;
                    break;
                case "DOCENTE":
                    $link_modulo = "https://docs.google.com/forms/d/e/1FAIpQLSe6AO5ySnXmsWsr5i_OZe6q8qXiRcWUYXrTvWnQl3UPlWzrxA/viewform?entry.345901760=" . $codice;
                    break;
                case "GENITORE-MADRE":
                    $link_modulo = "https://docs.google.com/forms/d/e/1FAIpQLSfrtfve0bVZ84PQnatGDbBJKAJ5Ynrs-Xdsf1ZBCQMPXE1ZMQ/viewform?usp=pp_url&entry.345901760=" . $codice;
                    break;
                case "GENITORE-PADRE":
                    $link_modulo = "https://docs.google.com/forms/d/e/1FAIpQLSe-tYjzHCrPARhyf2ycFcCGPbwyb01Elc-_sbrYpFO5AQ6FRQ/viewform?usp=pp_url&entry.345901760=" . $codice;
                    break;
                default:
                    $errore = "Tipologia elettore non valida";
                    break;

            }
        } else {
            $errore = "Codice non corretto. Verificare di averlo trascritto correttamente";
        }

        if (strlen($errore) > 0) {
            print "<h3 style='color:red'> ERRORE:<br> " . $errore . "</h3>"
            ?>

            <form action="index.html" method="get">
                <input type="submit" value="Torna alla pagina di accesso">
            </form>

            <?php
        } else {
            ?>



            <div class="pulsante">
            <a href="<?php print $link_modulo ?>">
                Accedi al modulo di voto
            </a>
                    </div>

            <?php
        }
    }

    ?>

</div>
</body>

</html>

