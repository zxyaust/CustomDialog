package z.loadingdialoglibrary;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;

/**
 * Created by max on 2016/8/11.
 * 用法:继承此类重写两个方法,或者是直接new出来,然后重新两个方法,使用非常方便,
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

    public void cancel() {
        if (alertDialog != null) {
            alertDialog.cancel();
        }
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
