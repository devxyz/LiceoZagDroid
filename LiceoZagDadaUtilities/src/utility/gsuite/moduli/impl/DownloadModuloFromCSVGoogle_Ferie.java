package utility.gsuite.moduli.impl;

import utility.gsuite.moduli.DownloadModuloFromCSVGoogle;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DownloadModuloFromCSVGoogle_Ferie extends DownloadModuloFromCSVGoogle {
    public DownloadModuloFromCSVGoogle_Ferie() {
    }

    @Override
    protected String getName(String[] row) {
        return row[3].toUpperCase();
    }

    @Override
    protected String getLinkFileName(String[] row, List<String> links, int linkIndex) {
        return "domanda-ferie(" + getIdFile() + ").pdf";
    }

    @Override
    protected String getLinkFolderName(String[] row, List<String> links, int linkIndex) {
        return getSurname(row).replaceAll(" ", "") + "-" + getName(row).replaceAll(" ", "");
    }


    @Override
    protected String getEmail(String[] row) {
        return row[1];
    }

    @Override
    protected String getSurname(String[] row) {
        return row[2].toUpperCase();
    }

    @Override
    protected List<String> getLinks(String[] row) {
        return Arrays.asList(row[4]);
    }


    @Override
    protected int skipLinkes() {
        return 1;
    }
}
