package it.gov.scuolesuperioridizagarolo.util;

import java.util.concurrent.Callable;

/**
 * Versione parametrica di callable
 */
public interface CallableParameter<T, P> extends Callable<T> {
    void setParameter(T param);

}
