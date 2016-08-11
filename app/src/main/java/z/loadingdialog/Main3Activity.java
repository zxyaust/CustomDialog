package z.loadingdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import z.loadingdialoglibrary.CustomDialog;
import z.loadingdialoglibrary.LoadingDialog;

public class Main3Activity extends AppCompatActivity {

    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void loading(View view) {
        Dia1.getInstance(this).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), "加载完成了", Toast.LENGTH_SHORT).show();
            }
        }).show(false);
//        LoadingDialog.getInstance(this).show(false);
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Dia1.getInstance(getApplicationContext()).cancel();
                    }
                });

            }
        }.start();
    }


    public void custom(final View view) {
        customDialog = new CustomDialog(this) {
            @Override
            protected void setWindowParams(Window window) {

            }

            @Override
            protected View inflateView() {
                View view1 = View.inflate(mContext, R.layout.activity_main3, null);
                return view1;
            }
        };
        customDialog/*.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //这里写对话框消失时的代码
            }
        })*/.show(false);
    }
}
