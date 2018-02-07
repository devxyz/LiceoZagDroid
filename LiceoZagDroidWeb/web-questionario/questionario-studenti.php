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
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;

    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    h1 {
        text-align: center;
    }

    div {
        border-radius: 5px;
        background-color: #ddff84;
        padding: 20px;
        width: 50%;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<body>


<div>

    <h1>Organizzazione oraria su cinque giorni settimanali <br> "Settimana Corta"</h1>
    <h3 style="padding: 1px;text-align: center;color: blue;font-size: 30px;border-top: 2px dashed red;;border-bottom:  2px dashed red;: ">Sondaggio Studenti</h3>
    <br>


<?php
function normalizeData($s)
{
    $v = trim($s);
    $v = str_replace("-", "/", $v);;
    $v = str_replace(":", "/", $v);;
    $v = str_replace(".", "/", $v);;

    return $v;
}

function normalizeString($s)
{
    $v = trim($s);
    $v = str_replace("'", "", $v);
    $v = str_replace(" ", "", $v);
    $unwanted_array = array('Š' => 'S', 'š' => 's', 'Ž' => 'Z', 'ž' => 'z', 'À' => 'A', 'Á' => 'A', 'Â' => 'A', 'Ã' => 'A', 'Ä' => 'A', 'Å' => 'A', 'Æ' => 'A', 'Ç' => 'C', 'È' => 'E', 'É' => 'E',
        'Ê' => 'E', 'Ë' => 'E', 'Ì' => 'I', 'Í' => 'I', 'Î' => 'I', 'Ï' => 'I', 'Ñ' => 'N', 'Ò' => 'O', 'Ó' => 'O', 'Ô' => 'O', 'Õ' => 'O', 'Ö' => 'O', 'Ø' => 'O', 'Ù' => 'U',
        'Ú' => 'U', 'Û' => 'U', 'Ü' => 'U', 'Ý' => 'Y', 'Þ' => 'B', 'ß' => 'Ss', 'à' => 'a', 'á' => 'a', 'â' => 'a', 'ã' => 'a', 'ä' => 'a', 'å' => 'a', 'æ' => 'a', 'ç' => 'c',
        'è' => 'e', 'é' => 'e', 'ê' => 'e', 'ë' => 'e', 'ì' => 'i', 'í' => 'i', 'î' => 'i', 'ï' => 'i', 'ð' => 'o', 'ñ' => 'n', 'ò' => 'o', 'ó' => 'o', 'ô' => 'o', 'õ' => 'o',
        'ö' => 'o', 'ø' => 'o', 'ù' => 'u', 'ú' => 'u', 'û' => 'u', 'ý' => 'y', 'þ' => 'b', 'ÿ' => 'y');
    $v = strtr($v, $unwanted_array);
    // $v=strtoupper($v);
    return $v;

}


$codice = trim(strtoupper(normalizeString($_POST["codice"])));



$t = time();

$csvline = strtoupper($codice);


$handle = fopen("alunni.csv", "r");
$trovato = 0;
$split = explode(";", "n;n");


if ($handle) {
    while (($line = fgets($handle)) !== false) {
        $split = explode(";", $line);
        if ($codice == $split[0]) {
            $trovato = 1;
            break;
        }
    }
    fclose($handle);
}

$fp = fopen("log.txt", "a");
fputs($fp, $codice . " ; " . $_SERVER['REMOTE_ADDR']." ; ".date("d/M/Y G:i") . " ; STUDENTI; ACCESSO=".$trovato ."\n");
fclose($fp);


echo "<br>";
if ($trovato == 1) {
    $nome = $split[1];
    $cognome = $split[2];
    $classe = $split[3];
    $idtransazione = base64_encode($codice . ";" . $nome . ";" . $cognome . ";" . $classe . ";" . "GENITORI");


    $url_google = "https://docs.google.com/forms/d/e/1FAIpQLSeZXqNvZA5aYwoP4rFgtFKfX3-h5j6VkUk57UTuqmVL56yZ3g/viewform?usp=pp_url&".
        "entry.1552855616=" . $idtransazione .
        "&entry.1923755697=" . $cognome. " " . $nome . " (".$classe.")".
        "&entry.2051490316=" . $split[3];

    echo
         "<a style='font-size: 24px;font-weight:bolder' href='" . $url_google . "'>"
        . "Codice corretto. Cliccare qui per iniziare la compilazione</a>";

} else {
    echo "<h3 style='color:red'>Codice di accesso " . $codice . " errato.</h3>"
        . "<form action='questionario-studenti.html'>"
        . "<input type='submit' value='Cliccare qui per riprovare' >"
        . "</form>";

}

?>