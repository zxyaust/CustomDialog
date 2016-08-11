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
 * 用法:1.直接使用LoadingDialog.getInstance(context).show(false),或者自定义图片和文字,重写initImageResource方法
 * 2.可以设置取消dialog的监听,非常方便
 */
public class LoadingDialog extends CustomDialog {


    private static LoadingDialog dialog;
    private ImageView image;
    private TextView text;

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
        image = (ImageView) view.findViewById(R.id.iv);
        text = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    public static synchronized LoadingDialog getInstance(Context context) {
        mContext = context;
        dialog = dialog == null ? new LoadingDialog(context) : dialog;
        return dialog;
    }

    /**
     * 显示的时候加动画
     *
     * @param canceledOnTouchOutside
     */
    @Override
    public void show(boolean canceledOnTouchOutside) {
        super.show(canceledOnTouchOutside);
        initImageResource(image, text);
        Animation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1600);
        animation.setRepeatCount(-1);//无限循环
        image.setAnimation(animation);
    }

    /**
     * 取消的时候去除动画
     */
    @Override
    public void cancel() {
        super.cancel();
        image.clearAnimation();
    }

    /**
     * 可以重写此方法,但不建议
     *
     * @return
     */
    protected int getLayoutId() {
        return R.layout.layout_loading;
    }

    /**
     * 可以重写此方法,对imageview的参数和text的参数进行修改,例如大小,颜色等数据,
     *
     * @param image
     */
    protected void initImageResource(ImageView image, TextView text) {

    }
}
