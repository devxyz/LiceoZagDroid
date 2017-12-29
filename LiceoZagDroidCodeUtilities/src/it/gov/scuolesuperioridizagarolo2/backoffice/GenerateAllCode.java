package it.gov.scuolesuperioridizagarolo2.backoffice;

import it.gov.scuolesuperioridizagarolo2.backoffice.utilities.codegen.BatchCodeGenerator_forLayout;
import it.gov.scuolesuperioridizagarolo2.backoffice.utilities.codegen.MenuAndroidGenerator;
import it.gov.scuolesuperioridizagarolo2.backoffice.utilities.greendao.GreenDaoGenerator;

/**
 * Created by stefano on 08/01/16.
 */
public class GenerateAllCode {
    public static void main(String[] args) throws Exception {
        BatchCodeGenerator_forLayout.main(args);
        GreenDaoGenerator.main(args);
        MenuAndroidGenerator.main(args);
    }
}
