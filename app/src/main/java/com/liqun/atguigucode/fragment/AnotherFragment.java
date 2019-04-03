package com.liqun.atguigucode.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.liqun.atguigucode.base.BaseFragment;

public class AnotherFragment extends BaseFragment {
    private static final String TAG = "AnotherFragment";
    private TextView textView;
    @Override
    protected View initView() {
        Log.e(TAG, "其他页面被初始化了");
        textView = new TextView(mCtx);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "其他页面的数据被初始化了");
        textView.setText("其他");
    }
}
