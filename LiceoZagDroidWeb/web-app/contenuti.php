<?php
include './pasw4/configuration.php'
?>
<?php header("Content-Type: application/xml; charset=utf-8"); ?>
<?php
error_reporting(-1);

$min_id_article=1*$_GET["id"];

//confgurazioni
$config=new JConfig;
$servername = $config->host;
$username = $config->user;
$password = $config->password;
$dbname = $config->db;
$prefix = $config->dbprefix;
echo '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>';

echo "\n<data>\n";
echo "<start-article-id-request>".$min_id_article."</start-article-id-request>\n";

//mysql_set_charset("UTF8");

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
$conn->set_charset("utf8");
// Check connection
if ($conn->connect_error) {
    echo "<errore>ERRORE</errore>";
    die("Connection failed: " . $conn->connect_error);
}
$sql_subselection_id_article=
"   SELECT c1.id".
"   FROM jhost_content c1 inner join jhost_categories as c2 on c1.catid=c2.id  ".
"   WHERE c1.id>". $min_id_article ." and state=1 and catid in (145,1013,1023) ";


//=========================================================================================
//=========================================================================================

$sql =
"   SELECT min(c1.id) as minimo ".
"   FROM jhost_content c1 inner join jhost_categories as c2 on c1.catid=c2.id  ".
"   WHERE state=1 and catid in (145,1013,1023) ";
echo "<debug>sql min article: <![CDATA[".$sql."]]></debug>\n";
$result = $conn->query($sql);
//echo '<info>' . $sql.'</info>';


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      echo "<min-article-id>".$row["minimo"]."</min-article-id>\n";
    }
}


$sql =
"SELECT c1.id, c1.title, c1.introtext, c1.fulltext, c1.created, c1.alias as alias_article, c1.catid as id_cat, c2.path as path_cat, c2.alias as alias_cat, c2.title as title_cat".
"\n FROM jhost_content c1 inner join jhost_categories as c2 on c1.catid=c2.id  ".
"\n WHERE c1.id in (". $sql_subselection_id_article .") ".
"\n order by c1.id desc";
echo "\n<articles>\n";
echo "<debug>sql article: <![CDATA[".$sql."]]></debug>\n";


$result = $conn->query($sql);
//echo '<info>' . $sql.'</info>';


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<article>\n";
        echo "  <article-id>" . $row["id"]. "</article-id>\n";
        echo "  <article-created>" . $row["created"]. "</article-created>\n";
        echo "  <category-id>" . $row["id_cat"]. "</category-id>\n";
        echo "  <category-title>" . $row["title_cat"]. "</category-title>\n";

        echo "  <article-url><![CDATA[http://www.scuolesuperioridizagarolo.gov.it/pasw4/index.php?option=com_content&view=article&id=".$row["id"]."]]></article-url>\n";

        $h=$row["title"];
		echo "  <article-title><![CDATA[" . $h. "]]></article-title>\n";

		$t=$row["fulltext"];
		if (strlen($t)>0){
  			$h=base64_encode($row["fulltext"]);
		   	echo "  <article-content><![CDATA[" . $h. "]]></article-content>\n";
		}
		else{
			$h=base64_encode($row["introtext"]);
  			echo "  <article-content><![CDATA[" . $h. "]]></article-content>\n";
  		}

        //$sql2 = "SELECT * FROM ".$prefix."content ".
        //$conn->query($sql2)

        echo "</article>\n";
    }
} else {
    //echo "<error>0 results</error>";
}

echo "</articles>\n";


echo "<tags>\n";



//=========================================================================================
//=========================================================================================


$sql="SELECT m.content_item_id as id_article, t.id as id_tag, t.title as title_tag".
" FROM jhost_contentitem_tag_map as m inner join jhost_tags as t on m.tag_id=t.id".
" where m.content_item_id in (".$sql_subselection_id_article.")".
" order by m.content_item_id desc ";
echo "<debug>sql tags: <![CDATA[".$sql."]]></debug>\n";


$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    	echo "<tag-map>\n";
    	echo "  <article-id>" . $row["id_article"]. "</article-id>\n";
    	echo "  <tag-id>" . $row["id_tag"]. "</tag-id>\n";
    	echo "  <tag-title><![CDATA[" . $row["title_tag"]. "]]></tag-title>\n";
    	echo "</tag-map>\n";
    }
} else {
   // echo "<error>0 results</error>";
}

echo "</tags>\n";


//=========================================================================================
//=========================================================================================

echo "<attachments>\n";
$sql="SELECT filename, url, file_size,file_type,access,parent_id ".
" FROM jhost_attachments".
" WHERE parent_entity='article' and parent_id in (".$sql_subselection_id_article.")";
echo "<debug>sql attachment: <![CDATA[".$sql."]]></debug>\n";



$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    	echo "<attachment-map>\n";
    	echo "  <attachment-filename><![CDATA[" . $row["filename"]. "]]></attachment-filename>\n";
    	echo "  <attachment-url><![CDATA[http://www.scuolesuperioridizagarolo.gov.it/pasw4/" . $row["url"]. "]]></attachment-url>\n";
    	echo "  <attachment-filesize>" . $row["file_size"]. "</attachment-filesize>\n";
    	echo "  <attachment-filetype>" . $row["file_type"]. "</attachment-filetype>\n";
    	echo "  <attachment-access>" . $row["access"]. "</attachment-access>\n";
    	echo "  <attachment-article-id>" . $row["parent_id"]. "</attachment-article-id>\n";


    	echo "</attachment-map>\n";
    }
} else {
   // echo "<error>0 results</error>";
}


echo "</attachments>\n";

echo "\n</data>\n";


$conn->close();





?>