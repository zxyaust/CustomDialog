# CustomDialog
快速自定义对话框,加载中对话框,方便使用,安卓快速开发利器
#1.1.0版本
使用方法
```
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
                Toast.makeText(getApplicationContext(), "取消了", Toast.LENGTH_SHORT).show();
            }
        }).show(true);//参数代表是否可以点击外部取消
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

```
#版本1.0.0
##1.继承CustomDialog来写一个对话框
快速实现

```

       new CustomDialog(this) {
            @Override
            protected void setWindowParams(Window window) {
			//这里可以拿到window对象,设置对话框的大小,背景等信息
			// window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            @Override
            protected View inflateView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("我是自定义对话框,可以是任意布局");
                return textView;
            }
        }/*.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //这里写对话框消失时的代码
            }
        })*/.show(false);
```
继承CustomDialog

```
	
package z.loadingdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import z.loadingdialoglibrary.CustomDialog;

public class Main3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Dia1.create(this).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), "取消了", Toast.LENGTH_SHORT).show();
            }
        }).show(true);
//        Dia1.create(this).show(false);
        new CustomDialog(this) {
            @Override
            protected void setWindowParams(Window window) {

            }

            @Override
            protected View inflateView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("我是自定义对话框,可以是任意布局");
                return textView;
            }
        }/*.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //这里写对话框消失时的代码
            }
        })*/.show(false);
    }
}

```

##2.下面是加载中对话框代码,使用非常方便
```
package z.loadingdialoglibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by max on 2016/8/11.
 * 用法:1.直接使用LoadingDialog.create(context).show(false),或者自定义图片和文字,重写initImageResource方法
 * 2.可以设置取消dialog的监听,非常方便
 */
public class LoadingDialog extends CustomDialog {


    private static LoadingDialog dialog;

    protected LoadingDialog(Context context) {
        super(context);
    }

    @Override
    public void setWindowParams(Window window) {
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected View inflateView() {
        View view = View.inflate(mContext, getLayoutId(), null);
        ImageView image = (ImageView) view.findViewById(R.id.iv);
        TextView text = (TextView) view.findViewById(R.id.tv);
        initImageResource(image, text);
        Animation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1600);
        animation.setRepeatCount(-1);//无限循环
        image.setAnimation(animation);
        return view;
    }
    public static LoadingDialog create(Context context){
        mContext = context;
        dialog = dialog==null?new LoadingDialog(context):dialog;
        return dialog;
    }
    /**
     * 可以重写此方法,但不建议
     * @return
     */
    protected  int getLayoutId() {
        return R.layout.layout_loading;
    }

    /**
     * 可以重写此方法,对imageview的参数和text的参数进行修改,例如大小,颜色等数据,
     *
     * @param image
     */
    protected  void initImageResource(ImageView image, TextView text) {

    }
}

```
##3.加载中对话框的使用方法
单例模式,可以放心使用,

```
//带监听的使用
 LoadingDialog.create(this).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), "取消了", Toast.LENGTH_SHORT).show();
            }
        }).show(true);
//简单使用
 LoadingDialog.create(this).show(false);

```

##4.加载中对话框的自定义方法

```

public class Dia1 extends LoadingDialog {
    private Dia1(Context context) {
        super(context);
    }

    @Override
    protected void initImageResource(ImageView image, TextView text) {
        text.setText("跑啊跑,使劲的跑");
	//这里可以改变里面转动的图片和文字,自定义很简单,使用方法和LoadingDialog完全相同
    }
}
```