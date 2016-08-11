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
