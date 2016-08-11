package z.loadingdialog;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import z.loadingdialoglibrary.LoadingDialog;

/**
 * Created by max on 2016/8/11.
 */
public class Dia1 extends LoadingDialog {
    private Dia1(Context context) {
        super(context);
    }

    @Override
    protected void initImageResource(ImageView image, TextView text) {
        text.setText("跑啊跑,使劲的跑");
    }
}
