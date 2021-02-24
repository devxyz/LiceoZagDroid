package utility.compiti_in_classe_gsuite;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CompitiInClasse1B {
    public static void main(String[] args) throws IOException {
        String s = "alessandro.colazingari.00002078@scuolesuperioridizagarolo.edu.it\n" +
                "alessandro.ronci.00002001@scuolesuperioridizagarolo.edu.it\n" +
                "alessio.baiocchi.00002033@scuolesuperioridizagarolo.edu.it\n" +
                "andrea.cicerchia.00002092@scuolesuperioridizagarolo.edu.it\n" +
                "biancamaria.grocianu.00002099@scuolesuperioridizagarolo.edu.it\n" +
                "cristian.brenda.00002025@scuolesuperioridizagarolo.edu.it\n" +
                "daniel.garzotto.00002072@scuolesuperioridizagarolo.edu.it\n" +
                "daniele.massaro.00002098@scuolesuperioridizagarolo.edu.it\n" +
                "davide.terzoni.00002014@scuolesuperioridizagarolo.edu.it\n" +
                "emma.desantis.00002074@scuolesuperioridizagarolo.edu.it\n" +
                "federico.brecci.00002087@scuolesuperioridizagarolo.edu.it\n" +
                "flavio.cerboni.00001979@scuolesuperioridizagarolo.edu.it\n" +
                "gabriele.camilloni.00002094@scuolesuperioridizagarolo.edu.it\n" +
                "gabriele.tomasi.00002019@scuolesuperioridizagarolo.edu.it\n" +
                "giordano.casaroli.00002043@scuolesuperioridizagarolo.edu.it\n" +
                "lorenzoconstantin.stefan.00002021@scuolesuperioridizagarolo.edu.it\n" +
                "luca.craciun.00001997@scuolesuperioridizagarolo.edu.it\n" +
                "lucrezia.brini.00002103@scuolesuperioridizagarolo.edu.it\n" +
                "mariafrancesca.radu.00002026@scuolesuperioridizagarolo.edu.it\n" +
                "matteo.dicarlo.00002121@scuolesuperioridizagarolo.edu.it\n" +
                "matteo.faraone.00002027@scuolesuperioridizagarolo.edu.it\n" +
                "nikolas.rudaj.00002015@scuolesuperioridizagarolo.edu.it\n" +
                "sabingabriele.mari.00002080@scuolesuperioridizagarolo.edu.it\n" +
                "samuele.protani.00002062@scuolesuperioridizagarolo.edu.it\n" +
                "sara.cardarelli.00002088@scuolesuperioridizagarolo.edu.it\n" +
                "tommaso.puliti.00002002@scuolesuperioridizagarolo.edu.it";
        String[] studenti = s.split("\n");
        File root = new File("/Users/stefano/Google Drive (stefano.millozzi@scuolesuperioridizagarolo.edu.it)/COMPITI IN CLASSE 2020-21/1B/01-febbraio");
        File src = new File(root, "_src");
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(out1);
        out.println("function addSharePermission() {");
        out.println("var files;\n");
        for (String s1 : studenti) {
            File f2 = new File(root, s1);
            System.out.println(f2);
            //f2.mkdirs();
            FileUtils.copyDirectory(src, f2);
            out.println("  " +
                    "  // Log the name of every file in the user's Drive.\n" +
                    "  files = DriveApp.getFoldersByName(\"" + s1 + "\");\n" +
                    "  while (files.hasNext()) {\n" +
                    "    var file = files.next();\n" +
                    "    file.addEditor(\"" + s1 + "\");\n" +
                    "    Logger.log(file.getName());\n" +
                    "  }\n");

        }
        out.println("}");


        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println(out1.toString());
        //==============
        System.out.println();
        System.out.println();
        System.out.println();

        out1=new ByteArrayOutputStream();
        out = new PrintStream(out1);

        out.println("function revokeSharePermission() {");
        out.println("var files;\n");
        for (String s1 : studenti) {
            out.println("  " +
                    "  // Log the name of every file in the user's Drive.\n" +
                    "  files = DriveApp.getFoldersByName(\"" + s1 + "\");\n" +
                    "  while (files.hasNext()) {\n" +
                    "    var file = files.next();\n" +
                    "    file.revokePermissions(\"" + s1 + "\");\n" +
                    "    Logger.log(file.getName());\n" +
                    "  }\n");

        }
        out.println("}");

        System.out.println(out1.toString());



    }


}
