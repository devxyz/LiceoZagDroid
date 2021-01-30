<?php
$codice = $_GET["code"];
$tipo = $_GET["type"];



if (strlen($codice) > 0) {
    print_r("<b>salvataggio...</b><hr>");
    $handle = fopen("used_code.txt", "a+") or die();
    fwrite( $handle, "".$codice. "\n" );
    fclose($handle);
    $handle2 = fopen("used_code_details.txt", "a+") or die();
    fwrite( $handle2, "".$codice."\t". $tipo. "\n" );
    fclose($handle2);
    print_r("<b>salvato...</b><hr>");
}

?>
    OK
