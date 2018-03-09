package it.gov.scuolesuperioridizagarolo.model;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.AttachmentArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.TagArticoloDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 08/03/2018.
 */
public class ArticoloSdoContainer {

    public final List<ArticoloSdo> articoli;

    public ArticoloSdoContainer() {
        articoli = new ArrayList<>();
    }

    public static class ArticoloSdo {
        public final ArticoloDB articolo;
        public final List<TagArticoloDB> tags = new ArrayList<>();
        public final List<AttachmentArticoloDB> attachments = new ArrayList<>();

        public ArticoloSdo(ArticoloDB articolo) {
            this.articolo = articolo;
        }


        public void detachAll() {
            for (TagArticoloDB tag : tags) {
                tag.__setDaoSession(null);
            }
            for (AttachmentArticoloDB attachment : attachments) {
                attachment.__setDaoSession(null);
            }

        }
    }
}
