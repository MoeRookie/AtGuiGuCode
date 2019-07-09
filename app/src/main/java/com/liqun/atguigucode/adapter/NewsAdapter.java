package com.liqun.atguigucode.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.domain.NewsBean.TrailersBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<TrailersBean> newsList;

    public NewsAdapter(Context context, List<TrailersBean> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList != null?newsList.size():0;
    }

    @Override
    public TrailersBean getItem(int i) {
        return newsList != null && newsList.size() > 0 ? newsList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_news,null);
            holder = new ViewHolder();
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvDesc = convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        TrailersBean trailer = newsList.get(i);
        holder.tvName.setText(trailer.getMovieName());
        holder.tvDesc.setText(trailer.getVideoTitle());
        OkHttpUtils
                .get()//
                .url(trailer.getCoverImg())//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete");
                        holder.ivIcon.setImageBitmap(bitmap);
                    }
                });
        return convertView;
    }

    private class ViewHolder{
        ImageView ivIcon;
        TextView tvName;
        TextView tvDesc;
    }
}
