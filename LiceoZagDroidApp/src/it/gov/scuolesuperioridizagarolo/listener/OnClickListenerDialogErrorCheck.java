package it.gov.scuolesuperioridizagarolo.listener;

import android.content.DialogInterface;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

/**
 * Created by stefano on 19/03/15.
 */
public abstract class OnClickListenerDialogErrorCheck implements DialogInterface.OnClickListener {
    protected final AbstractActivity context;

    public OnClickListenerDialogErrorCheck(AbstractActivity context) {
        this.context = context;
    }

    @Override
    public final void onClick(DialogInterface dialog, int which) {
        try {
            onClickImpl(dialog, which);
        } catch (Throwable throwable) {
            try {
                onErrorImpl(dialog, which, throwable);
            } catch (Throwable e) {

            }
        }
    }

    /**
     * implementazione base in caso di errore
     *
     * @param ex
     */
    protected void onErrorImpl(DialogInterface dialog, int which, Throwable ex) {
        DialogUtil.openErrorDialog(context.getActivity(), "Errore", "Errore durante l'esecuzione dell'operazione", ex);
    }

    protected abstract void onClickImpl(DialogInterface dialog, int which) throws Throwable;
}
