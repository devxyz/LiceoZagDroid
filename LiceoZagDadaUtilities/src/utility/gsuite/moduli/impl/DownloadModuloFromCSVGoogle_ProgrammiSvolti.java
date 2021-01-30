package utility.gsuite.moduli.impl;

import utility.gsuite.moduli.DownloadModuloFromCSVGoogle;

import java.util.Arrays;
import java.util.List;

public class DownloadModuloFromCSVGoogle_ProgrammiSvolti extends DownloadModuloFromCSVGoogle {

    @Override
    protected String getName(String[] row) {
        return row[3];
    }

    @Override
    protected String getLinkFileName(String[] row, List<String> links, int linkIndex) {
        if (isLiceo(row))
            return row[11] + "-" + getSurname(row) + "(" + getIdFile() + ").pdf";
        else
            return row[7] + "-" + getSurname(row) + "(" + getIdFile() + ").pdf";
    }

    @Override
    protected String getLinkFolderName(String[] row, List<String> links, int linkIndex) {
        if (isLiceo(row))
            return "LICEO/" + row[9] + row[10];
        else
            return "IPIA/" + row[5] + row[6];
    }

    @Override
    protected String getEmail(String[] row) {
        return row[1];
    }

    @Override
    protected String getSurname(String[] row) {
        return row[2];
    }

    private boolean isLiceo(String[] row) {
        return row[12] != null && row[12].trim().length() > 0;
    }

    @Override
    protected List<String> getLinks(String[] row) {
        if (isLiceo(row))
            return Arrays.asList(row[12]);
        else
            return Arrays.asList(row[8]);
    }

    @Override
    protected int skipLinkes() {
        return 1;
    }
}
