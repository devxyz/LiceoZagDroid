package it.gov.scuolesuperioridizagarolo.cache;

import it.gov.scuolesuperioridizagarolo.model.dto.C_NormalizedURL;

import java.io.File;

/**
 *xz interfaccia di callback
 */
public interface AsyncUrlLoaderCallback {
    void onLoadFinished(C_NormalizedURL url, File f);

    void onLoadError(C_NormalizedURL url, Throwable error);

    void onQueueForDownload(C_NormalizedURL url);

    void onCancelled(C_NormalizedURL url);

    /**
     * true if task is cancelled
     *
     * @return
     */
    boolean isCancelled();
}
