package it.gov.scuolesuperioridizagarolo.model;

/**
 * Created by stefano on 03/01/2018.
 */
public class ProdottoBar {
    public final int id;
    public final String nomeUtente;
    public final String nomeProdotto;
    public final double prezzounitario;
    public int quantita;


    public ProdottoBar(int id, String nomeUtente, String nomeProdotto, double prezzounitario) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.nomeProdotto = nomeProdotto;
        this.prezzounitario = prezzounitario;
        quantita = 0;
    }
}
