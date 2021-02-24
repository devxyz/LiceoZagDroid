package utility.compiti_in_classe_gsuite;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CompitiInClasse1D {
    public static void main(String[] args) throws IOException {
        String s = "adriano.mocci.00002057@scuolesuperioridizagarolo.edu.it\n" +
                "alessandro.dicesare.00002123@scuolesuperioridizagarolo.edu.it\n" +
                "alexander.casaburi.00002000@scuolesuperioridizagarolo.edu.it\n" +
                "andrea.diloreto.00002022@scuolesuperioridizagarolo.edu.it\n" +
                "cherif.gouda.00008275@scuolesuperioridizagarolo.edu.it\n" +
                "cornel.ciubotaru.00001973@scuolesuperioridizagarolo.edu.it\n" +
                "daniel.spina.00002009@scuolesuperioridizagarolo.edu.it\n" +
                "deborah.merlo.00002165@scuolesuperioridizagarolo.edu.it\n" +
                "edoardo.mastrantoni.00002178@scuolesuperioridizagarolo.edu.it\n" +
                "eduardvalentin.irimia.00002050@scuolesuperioridizagarolo.edu.it\n" +
                "elena.carosi.00002106@scuolesuperioridizagarolo.edu.it\n" +
                "eleonora.dematteis.00001976@scuolesuperioridizagarolo.edu.it\n" +
                "emanuele.cicerchia.00002066@scuolesuperioridizagarolo.edu.it\n" +
                "flavio.tomei.00002113@scuolesuperioridizagarolo.edu.it\n" +
                "gabriele.rossi.00002058@scuolesuperioridizagarolo.edu.it\n" +
                "leonardo.tronti.00001991@scuolesuperioridizagarolo.edu.it\n" +
                "lorenzo.depaola.00002011@scuolesuperioridizagarolo.edu.it\n" +
                "ludovico.dileonardo.00001994@scuolesuperioridizagarolo.edu.it\n" +
                "manuela.cenciarini@scuolesuperioridizagarolo.edu.it\n" +
                "mario.morici.00002122@scuolesuperioridizagarolo.edu.it\n" +
                "mykola.marchenko.00002068@scuolesuperioridizagarolo.edu.it\n" +
                "raffaello.petruzzelli.00008271@scuolesuperioridizagarolo.edu.it\n" +
                "sara.anzalone.00002069@scuolesuperioridizagarolo.edu.it\n" +
                "sara.blushi.00002013@scuolesuperioridizagarolo.edu.it\n" +
                "sophia.favale.00002042@scuolesuperioridizagarolo.edu.it\n" +
                "tommaso.marchetti.00002090@scuolesuperioridizagarolo.edu.it\n" +
                "viola.macchi.00002118@scuolesuperioridizagarolo.edu.it\n" +
                "zoe.borzi.00002006@scuolesuperioridizagarolo.edu.it";
        String[] studenti = s.split("\n");
        File root = new File("/Users/stefano/Google Drive (stefano.millozzi@scuolesuperioridizagarolo.edu.it)/COMPITI IN CLASSE 2020-21/1D/01-febbraio");
        File src = new File(root, "_src");
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(out1);
        out.println("function addSharePermission() {");
        out.println("var files;\n");
        for (String s1 : studenti) {
            File f2 = new File(root, s1);
            System.out.println(f2);
            f2.mkdirs();
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

        out1 = new ByteArrayOutputStream();
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
