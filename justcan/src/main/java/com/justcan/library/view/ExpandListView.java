package com.justcan.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 
 * 作者： James
 * 日期：2015年11月2日 下午8:43:23 
 * 版本：V1.0   
 * 功能：listview 套入listview
 */
public class ExpandListView extends ListView {
	public ExpandListView(Context context) {
		super(context);
	}
	
	public ExpandListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public ExpandListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
