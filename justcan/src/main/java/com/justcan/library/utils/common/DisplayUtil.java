package com.justcan.library.utils.common;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by James on 2016/8/23.
 * Note:
 */
public class DisplayUtil {
    private static int DisplayWidthPixels = 0;
    private static int DisplayheightPixels = 0;

    public static int dip2px(Context context, float f) {
        if (context != null)
            return (int) (0.5f + f * context.getResources().getDisplayMetrics().density);
        return 0;
    }

    public static float getScale(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private static void getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        DisplayUtil.DisplayWidthPixels = displayMetrics.widthPixels;
        DisplayUtil.DisplayheightPixels = displayMetrics.heightPixels;
    }

    public static int getDisplayWidthPixels(Context context) {
        if (context == null) {
            return -1;
        }
        if (DisplayUtil.DisplayWidthPixels != 0) return DisplayUtil.DisplayWidthPixels;
        DisplayUtil.getDisplayMetrics(context);
        return DisplayUtil.DisplayWidthPixels;
    }

    public static int getDisplayheightPixels(Context context) {
        if (context == null) {
            return -1;
        }
        if (DisplayUtil.DisplayheightPixels != 0) return DisplayUtil.DisplayheightPixels;
        DisplayUtil.getDisplayMetrics(context);
        return DisplayUtil.DisplayheightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int n = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int n2 = 0;
        if (n <= 0) return n2;
        n2 = context.getResources().getDimensionPixelSize(n);
        return n2;
    }

    public static int px2dip(Context context, float f) {
        return (int) (0.5f + f / context.getResources().getDisplayMetrics().density);
    }

    public static int px2sp(Context context, float f) {
        return (int) (0.5f + f / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static void setViewHeightDp(View view, int n) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = DisplayUtil.dip2px(view.getContext(), n);
        view.setLayoutParams(layoutParams);
    }

    public static void setViewWidthDp(View view, int n) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = DisplayUtil.dip2px(view.getContext(), n);
        view.setLayoutParams(layoutParams);
    }

    public static int sp2px(Context context, float f) {
        return (int) (0.5f + f * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); //获得Adapter
        if (listAdapter == null) {  //判断是否为空
            return;
        }
        int totalHeight = 0;  //定义总高度
        //根据listAdapter.getCount()获取当前拥有多少个item项，然后进行遍历对每一个item获取高度再相加最终获得总的高度。
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //获取到list的布局属性
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listview最终高度为item的高度+分隔线的高度，这是重新设置listview的属性
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //将重新设置的params再应用到listview中
        listView.setLayoutParams(params);
    }

    /**
     * 获取半个屏幕宽度(px)
     */
    public static int getHalfScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public static int getPercentage(int n, int n2) {
        return Math.min((int) (100.0 * (double) n / (double) n2), 100);
    }

    public static int getScreenMaxWidth(Context context) {
        Display localDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int i = localDisplay.getHeight();
        int j = localDisplay.getWidth();
        if (i > j)
            return i;
        return j;
    }
}
