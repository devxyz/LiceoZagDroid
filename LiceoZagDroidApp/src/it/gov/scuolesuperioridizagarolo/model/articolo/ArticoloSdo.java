package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.AttachmentArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.TagArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloSdo<T extends ArticoloDetails> {
    public final WrapperArticoloDB<T> wrapperArticolo;
    public final List<TagArticoloDB> tags = new ArrayList<>();
    public final List<AttachmentArticoloDB> attachments = new ArrayList<>();

    public ArticoloSdo(WrapperArticoloDB<T> wrapperArticolo) {
        this.wrapperArticolo = wrapperArticolo;
    }


    public void detachAll() {
        for (TagArticoloDB tag : tags) {
            tag.__setDaoSession(null);
        }
        for (AttachmentArticoloDB attachment : attachments) {
            attachment.__setDaoSession(null);
        }
    }

    public Set<String> parole() {
        return wrapperArticolo.getParoleLowerCase();
    }
}
