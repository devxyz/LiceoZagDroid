<html>
<head>
    <style>
        input[type=text], select {
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 110%;
        }

        input[type=submit] {

            font-size: 110%;
            background-color: #4CAF50;

            color: white;
            padding: 5px 5px;

            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin: auto;
            margin-top: 5px;
        }

        input[type=submit]:hover {
            background-color: #01c900;

        }

        h1 {
            text-align: center;
        }

        h2 {
            text-align: center;
        }

        td{
            padding:0;
            text-align: left;
        }
        div.main {
            border-radius: 5px;
            background-color: #000000;
            color: white;
            padding: 5px;

            margin-left: auto;
            margin-right: auto;
        }

        div.button {
            margin: auto;
            width: 50%;
        }
    </style>


</head>

<body>

<?php
$nome_classe = $_GET["classe"];
$nome_docente = $_GET["docente"];
$aule_vuote = $_GET["aule_vuote"];
$nome_aula = $_GET["aula"];
$variazioni = $_GET["variazioni"];
?>

<div class="main">
    <table>

        <?php
        if (strlen($nome_classe) + strlen($nome_docente) + strlen($aule_vuote) + strlen($variazioni) + strlen($nome_aula) == 0) {
            ?>

            <tr>
                <td>

                    <form action="orario2.php" method="get">

                        <input type="hidden" name="rand" value="<?php echo rand(1, 10000); ?>">
                        <select id="classe" name="classe">
                            <option value="">Seleziona una classe...</option>
                            <?php
                            $dir = "./CLASSI";
                            $files1 = scandir($dir);
                            foreach ($files1 as $key => $value) {
                                if (!in_array($value, array(".", ".."))) {
                                    if ($nome_classe == $value)
                                        echo "<option selected='selected' value='" . $value . "'>" . $value . "</option>";
                                    else
                                        echo "<option value='" . $value . "'>" . $value . "</option>";
                                }
                            }
                            ?>

                        </select>

                        <input type="submit" value="Orario Classe">

                    </form>
                </td>
            </tr>
            <tr>
                <td>

                    <form action="orario2.php" method="get">


                        <input type="hidden" name="rand" value="<?php echo rand(1, 10000); ?>">
                        <select id="docente" name="docente">
                            <option value="">Seleziona un docente...</option>
                            <?php
                            $dir = "./DOCENTI";
                            $files1 = scandir($dir);
                            foreach ($files1 as $key => $value) {
                                if (!in_array($value, array(".", ".."))) {
                                    if ($nome_docente == $value)
                                        echo "<option selected='selected' value='" . $value . "'>" . $value . "</option>";
                                    else
                                        echo "<option value='" . $value . "'>" . $value . "</option>";
                                }
                            }
                            ?>

                        </select>

                        <input type="submit" value="Orario docente">

                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="orario2.php" method="get">


                        <input type="hidden" name="rand" value="<?php echo rand(1, 10000); ?>">
                        <select id="aula" name="aula">
                            <option value="">Seleziona un' aula...</option>
                            <?php
                            $dir = "./AULE";
                            $files1 = scandir($dir);
                            foreach ($files1 as $key => $value) {
                                if (!in_array($value, array(".", ".."))) {
                                    if ($nome_aula == $value)
                                        echo "<option selected='selected' value='" . $value . "'>" . $value . "</option>";
                                    else
                                        echo "<option value='" . $value . "'>" . $value . "</option>";
                                }
                            }
                            ?>
                        </select>
                        <input type="submit" value="Orario Aula">

                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="orario2.php" method="get">
                        <input type="hidden" name="rand" value="<?php echo rand(1, 10000); ?>">
                        <input type="submit" name="aule_vuote" value="Aule vuote">
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="orario2.php" method="get">
                        <input type="hidden" name="rand" value="<?php echo rand(1, 10000); ?>">
                        <input type="submit" name="variazioni" value="Variazioni Orario">
                    </form>
                </td>
            </tr>

            <?php
        } else {
            ?>
            <tr>
                <td style="width: 33%">
                    <div style='align:left'>

                        <form action="orario2.php" method="post">
                            <input type="submit" name="indice" value="Ritorna al menu">
                        </form>
                    </div>
                </td>
            </tr>
            <?php
        }
        ?>


    </table>
</div>

<div style="margin: 0;width: 100%">
    <br>
    <?php
    if (strlen($nome_classe) > 0) {
        $file = file_get_contents("CLASSI/" . $nome_classe, true);
        echo $file;
    }
    ?>


    <?php
    if (strlen($nome_docente) > 0) {
        $file = file_get_contents("DOCENTI/" . $nome_docente, true);
        echo $file;
    }
    ?>

    <?php
    if (strlen($nome_aula) > 0) {
        $file = file_get_contents("AULE/" . $nome_aula, true);
        echo $file;
    }
    ?>

    <?php
    if (strlen($aule_vuote) > 0) {
        $file = file_get_contents("EXTRA/" . "AULE-VUOTE", true);
        echo $file;
    }
    ?>

    <?php
    if (strlen($variazioni) > 0) {
        $file = file_get_contents("EXTRA/" . "VARIAZIONI", true);
        echo $file;
    }
    ?>

</div>


</body>

</html>
