package com.liqun.atguigucode.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommonFrameFragmentAdapter extends BaseAdapter {
    private Context ctx;
    private String[] datas;

    public CommonFrameFragmentAdapter(Context ctx, String[] datas) {
        this.ctx = ctx;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null || datas.length == 0 ? 0 : datas.length;
    }

    @Override
    public String getItem(int position) {
        return datas != null && datas.length != 0 ? datas[position] : "暂无数据";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(ctx);
        textView.setPadding(10,10,0,10);
        textView.setText(getItem(position));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;
    }
}
