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

    h1{
        text-align: center;
    }

    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
        width: 50%;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<body>



<div>
    <h1>Questionario chiusura sabato</h1>
<?php
  function normalizeData($s){
    $v=trim($s);
    $v=str_replace("-","/",$v);;
    $v=str_replace(":","/",$v);;
    $v=str_replace(".","/",$v);;

    return $v;
  }

  function normalizeString($s){
      $v=trim($s);
      $v=str_replace("'","",$v);
      $unwanted_array = array(    'Š'=>'S', 'š'=>'s', 'Ž'=>'Z', 'ž'=>'z', 'À'=>'A', 'Á'=>'A', 'Â'=>'A', 'Ã'=>'A', 'Ä'=>'A', 'Å'=>'A', 'Æ'=>'A', 'Ç'=>'C', 'È'=>'E', 'É'=>'E',
                                  'Ê'=>'E', 'Ë'=>'E', 'Ì'=>'I', 'Í'=>'I', 'Î'=>'I', 'Ï'=>'I', 'Ñ'=>'N', 'Ò'=>'O', 'Ó'=>'O', 'Ô'=>'O', 'Õ'=>'O', 'Ö'=>'O', 'Ø'=>'O', 'Ù'=>'U',
                                  'Ú'=>'U', 'Û'=>'U', 'Ü'=>'U', 'Ý'=>'Y', 'Þ'=>'B', 'ß'=>'Ss', 'à'=>'a', 'á'=>'a', 'â'=>'a', 'ã'=>'a', 'ä'=>'a', 'å'=>'a', 'æ'=>'a', 'ç'=>'c',
                                  'è'=>'e', 'é'=>'e', 'ê'=>'e', 'ë'=>'e', 'ì'=>'i', 'í'=>'i', 'î'=>'i', 'ï'=>'i', 'ð'=>'o', 'ñ'=>'n', 'ò'=>'o', 'ó'=>'o', 'ô'=>'o', 'õ'=>'o',
                                  'ö'=>'o', 'ø'=>'o', 'ù'=>'u', 'ú'=>'u', 'û'=>'u', 'ý'=>'y', 'þ'=>'b', 'ÿ'=>'y' );
      $v = strtr( $v, $unwanted_array );
     // $v=strtoupper($v);
      return $v;

  }


  $nome=normalizeString($_GET["nome"]);
  $cognome=normalizeString($_GET["cognome"]);
  $data=normalizeData($_GET["giorno"]."/".$_GET["mese"]."/".$_GET["anno"]);
  $classe=normalizeString($_GET["classe"]);


  $t=time();

//$nome;cognome;$data;$classe
  $csvline=strtoupper($nome.";".$cognome.";".$data.";".$classe);

  $idt=strtoupper(
  "".$nome.
  "\t".$cognome.
  "\t".$data.
  "\t".$classe.
  "\t". date("d/m/Y H:i:s",$t).
  "\t".$_SERVER['REMOTE_ADDR']
  );
  $idtransazione= base64_encode($idt);


  $handle = fopen("alunni.csv", "r");
  $trovato=0;
  if ($handle) {
      while (($line = fgets($handle)) !== false) {
          if (strtoupper($line)==$csvline){
            $trovato=1;
            break;
          }
      }
      fclose($handle);
  }

  $fp = fopen( "log.txt", "a" );
  fputs( $fp, $idt."\n" );
  fclose($fp);



  //echo "Cerca riga:" . $csvline."<br>";
  //echo "Trovato:" . $trovato;
  //echo "<hr>";

  //echo "ID TRANSAZIONE: " . $idtransazione ."";
  echo "<h4>Nominativo: " . $nome. " " . cognome."</h4>";
  echo "<h4>Nato il :" . $data."</h4>";
  echo "<h4>Classe :" . $classe."</h4>";

  echo "<br>";
  if ($trovato==1){

     $url_google="https://docs.google.com/forms/d/e/1FAIpQLScmaqaSeEENsILNI8-JIFCkydyPi1z-oSFJ8LCmKOIoPPSjhA/viewform?usp=pp_url&entry.1552855616=".
         $idtransazione. "&entry.538566811=".$_GET["cognome"]." ".$_GET["nome"].
         "&entry.2051490316=". $_GET["classe"];
     echo "<h3>Credenziali corrette.</h3> <a href='".$url_google."'>Clicca qui per compilare il questionario</a>";
  }else{
     echo "<h3 style='color:red'>Credenziali errate.</h3> <a href='questionario.html'>Cliccare qui per ripetere l'inserimento dei dati</a>";
  }

?>

</div>