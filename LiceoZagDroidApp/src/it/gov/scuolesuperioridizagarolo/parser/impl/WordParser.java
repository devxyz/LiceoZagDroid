/* WordParser.java */
/* Generated By:JavaCC: Do not edit this line. WordParser.java */
package it.gov.scuolesuperioridizagarolo.parser.impl;

import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloTypeAvviso;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloTypeCircolare;

import java.util.ArrayList;

/** Simple brace matcher. */
public class WordParser implements WordParserConstants {

  /** Main entry point. */
  public static void main(String args[]) throws ParseException {
    WordParser parser = new WordParser(System.in);
    parser.Input(new ArrayList());
  }

  final public void Input(ArrayList<Object> a) throws ParseException {//javacode
  ArticoloTypeCircolare ac=new ArticoloTypeCircolare();
  ArticoloTypeAvviso av=new ArticoloTypeAvviso();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case CIRCOLARE:{
      jj_consume_token(CIRCOLARE);
a.add(ac);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        jj_consume_token(ALTRO);
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMERO_PUNTATO:{
        jj_consume_token(NUMERO_PUNTATO);
ac.parseNumeroCircolare(token.image);
        break;
        }
      case NUMERO:{
        jj_consume_token(NUMERO);
ac.parseNumeroCircolare(token.image);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(ALTRO);
      }
      jj_consume_token(DATA);
ac.parseDataCircolare(token.image);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[3] = jj_gen;
          break label_3;
        }
        jj_consume_token(ALTRO);
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AVVISO:{
        jj_consume_token(AVVISO);
ac.appendOggetto(token.image);
        break;
        }
      case DATA:{
        jj_consume_token(DATA);
ac.appendOggetto(token.image);
        break;
        }
      case CIRCOLARE:{
        jj_consume_token(CIRCOLARE);
ac.appendOggetto(token.image);
        break;
        }
      case NUMERO_PUNTATO:{
        jj_consume_token(NUMERO_PUNTATO);
ac.appendOggetto(token.image);
        break;
        }
      case PAROLA:{
        jj_consume_token(PAROLA);
ac.appendOggetto(token.image);
        break;
        }
      case EMAIL:{
        jj_consume_token(EMAIL);
ac.appendOggetto(token.image);
        break;
        }
      case TELEFONO:{
        jj_consume_token(TELEFONO);
ac.appendOggetto(token.image);
        break;
        }
      case CLASSE:{
        jj_consume_token(CLASSE);
ac.appendOggetto(token.image);
        break;
        }
      case NUMERO:{
        jj_consume_token(NUMERO);
ac.appendOggetto(token.image);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DATA:
        case CLASSE:
        case NUMERO:
        case TELEFONO:
        case CIRCOLARE:
        case NUMERO_PUNTATO:
        case AVVISO:
        case EMAIL:
        case PAROLA:
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[5] = jj_gen;
          break label_4;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case AVVISO:{
          jj_consume_token(AVVISO);
ac.appendOggetto(token.image);
          break;
          }
        case DATA:{
          jj_consume_token(DATA);
ac.appendOggetto(token.image);
          break;
          }
        case CIRCOLARE:{
          jj_consume_token(CIRCOLARE);
ac.appendOggetto(token.image);
          break;
          }
        case NUMERO_PUNTATO:{
          jj_consume_token(NUMERO_PUNTATO);
ac.appendOggetto(token.image);
          break;
          }
        case ALTRO:{
          jj_consume_token(ALTRO);
ac.appendOggetto(token.image);
          break;
          }
        case PAROLA:{
          jj_consume_token(PAROLA);
ac.appendOggetto(token.image);
          break;
          }
        case EMAIL:{
          jj_consume_token(EMAIL);
ac.appendOggetto(token.image);
          break;
          }
        case TELEFONO:{
          jj_consume_token(TELEFONO);
ac.appendOggetto(token.image);
          break;
          }
        case CLASSE:{
          jj_consume_token(CLASSE);
ac.appendOggetto(token.image);
          break;
          }
        case NUMERO:{
          jj_consume_token(NUMERO);
ac.appendOggetto(token.image);
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
      }
    case AVVISO:{
      jj_consume_token(AVVISO);
a.add(av);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          break label_5;
        }
        jj_consume_token(ALTRO);
      }
      jj_consume_token(DATA);
av.parseDataAvviso(token.image);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[8] = jj_gen;
          break label_6;
        }
        jj_consume_token(ALTRO);
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AVVISO:{
        jj_consume_token(AVVISO);
av.appendOggetto(token.image);
        break;
        }
      case DATA:{
        jj_consume_token(DATA);
av.appendOggetto(token.image);
        break;
        }
      case CIRCOLARE:{
        jj_consume_token(CIRCOLARE);
av.appendOggetto(token.image);
        break;
        }
      case NUMERO_PUNTATO:{
        jj_consume_token(NUMERO_PUNTATO);
av.appendOggetto(token.image);
        break;
        }
      case PAROLA:{
        jj_consume_token(PAROLA);
av.appendOggetto(token.image);
        break;
        }
      case EMAIL:{
        jj_consume_token(EMAIL);
av.appendOggetto(token.image);
        break;
        }
      case TELEFONO:{
        jj_consume_token(TELEFONO);
av.appendOggetto(token.image);
        break;
        }
      case CLASSE:{
        jj_consume_token(CLASSE);
av.appendOggetto(token.image);
        break;
        }
      case NUMERO:{
        jj_consume_token(NUMERO);
av.appendOggetto(token.image);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DATA:
        case CLASSE:
        case NUMERO:
        case TELEFONO:
        case CIRCOLARE:
        case NUMERO_PUNTATO:
        case AVVISO:
        case EMAIL:
        case PAROLA:
        case ALTRO:{
          ;
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          break label_7;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case AVVISO:{
          jj_consume_token(AVVISO);
av.appendOggetto(token.image);
          break;
          }
        case DATA:{
          jj_consume_token(DATA);
av.appendOggetto(token.image);
          break;
          }
        case CIRCOLARE:{
          jj_consume_token(CIRCOLARE);
av.appendOggetto(token.image);
          break;
          }
        case NUMERO_PUNTATO:{
          jj_consume_token(NUMERO_PUNTATO);
av.appendOggetto(token.image);
          break;
          }
        case ALTRO:{
          jj_consume_token(ALTRO);
av.appendOggetto(token.image);
          break;
          }
        case PAROLA:{
          jj_consume_token(PAROLA);
av.appendOggetto(token.image);
          break;
          }
        case EMAIL:{
          jj_consume_token(EMAIL);
av.appendOggetto(token.image);
          break;
          }
        case TELEFONO:{
          jj_consume_token(TELEFONO);
av.appendOggetto(token.image);
          break;
          }
        case CLASSE:{
          jj_consume_token(CLASSE);
av.appendOggetto(token.image);
          break;
          }
        case NUMERO:{
          jj_consume_token(NUMERO);
av.appendOggetto(token.image);
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  /** Generated Token Manager. */
  public WordParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[13];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200000,0x24000,0x200000,0x200000,0x1ff000,0x3ff000,0x3ff000,0x200000,0x200000,0x1ff000,0x3ff000,0x3ff000,0x50000,};
   }

  /** Constructor with InputStream. */
  public WordParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public WordParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new WordParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public WordParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new WordParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public WordParser(WordParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(WordParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 13; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[22];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 13; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 22; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
