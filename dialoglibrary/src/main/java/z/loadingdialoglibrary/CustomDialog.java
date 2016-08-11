package z.loadingdialoglibrary;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;

/**
 * Created by max on 2016/8/11.
 */
public abstract class CustomDialog {

    private AlertDialog alertDialog;
    public static Context mContext;
    private Window window;

    public CustomDialog(Context context) {
        this.mContext = context;
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(inflateView());
            alertDialog = builder.create();
            window = alertDialog.getWindow();
            setWindowParams(window);
        }
    }


    public void show(boolean canceledOnTouchOutside) {
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        alertDialog.show();
    }

    /**
     * 对话框取消时的监听
     *
     * @param listener
     */
    public CustomDialog setOnCancelListener(AlertDialog.OnCancelListener listener) {
        alertDialog.setOnCancelListener(listener);
        return this;
    }

    protected abstract void setWindowParams(Window window);

    protected abstract View inflateView();
}
