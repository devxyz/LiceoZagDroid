package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.util.*;

/**
 * dati specifici per tipo di articolo
 */
public abstract class ArticoloDetails {
    protected final TreeSet<String> wordsLowercase;
    protected final ArrayList<ArticoloTagDetails> tags;
    protected final ArrayList<ArticoloAttachmentDetails> attachments;

    public ArticoloDetails() {
        this.wordsLowercase = new TreeSet<>();
        tags = new ArrayList<>();
        attachments = new ArrayList<>();
    }

    public ArrayList<ArticoloAttachmentDetails> getAttachments() {
        return attachments;
    }

    public void addTag(ArticoloTagDetails tag) {
        tags.add(tag);
    }

    public void addArticoloAttachment(ArticoloAttachmentDetails a) {
        if (a != null) {
            attachments.add(a);
        }
    }

    public List<ArticoloTagDetails> getTags() {
        return tags;
    }

    public Set<String> getWordsLowercase() {
        return Collections.unmodifiableSet(wordsLowercase);
    }

    public void addWordsLowerCase(String p) {
        if (p != null)
            wordsLowercase.add(p.trim().toLowerCase());
    }

    public void addWordsLowerCase(Collection<String> p) {
        for (String s : p) {
            addWordsLowerCase(s);
        }

    }

    public abstract void check() throws IllegalArgumentException;


    @Override
    public String toString() {
        return "ArticoloType{" +
                "paroleLowerCase=" + wordsLowercase +
                '}';
    }
}
