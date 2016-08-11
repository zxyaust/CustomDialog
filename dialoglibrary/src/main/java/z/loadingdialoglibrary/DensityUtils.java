package z.loadingdialoglibrary;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class DensityUtils {
    /**
     * 返回px值
     *
     * @param ctx
     * @param dp
     * @return
     */
    public static int dip2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        //dp = px/density
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    /**
     * 返回dp值
     */
    public static float px2dip(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        //dp = px/density
        float dp = px / density;
        return dp;
    }

    public static Display getDisplay(Activity mActivity) {
        WindowManager windowManager = mActivity.getWindowManager();
        return windowManager.getDefaultDisplay();
    }

    public static int getStatusBarHeight(Context ctx) {
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        //状态栏高度
        int heightStatusBar = 0;
        if (resourceId > 0) {
            heightStatusBar = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return heightStatusBar;
    }
}
