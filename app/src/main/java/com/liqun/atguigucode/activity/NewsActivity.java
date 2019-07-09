package com.liqun.atguigucode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.liqun.atguigucode.R;
import com.liqun.atguigucode.adapter.NewsAdapter;
import com.liqun.atguigucode.domain.NewsBean;
import com.liqun.atguigucode.domain.NewsBean.TrailersBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class NewsActivity extends AppCompatActivity {

    private ProgressBar mPb;
    private ListView mLvNews;
    private TextView mTvEmpty;
    private ArrayList<TrailersBean> mNewsList = new ArrayList<>();
    private NewsAdapter mAdapter;
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, NewsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initUI();
    }

    private void initUI() {
        mPb = findViewById(R.id.pb);
        mLvNews = findViewById(R.id.lv_news);
        mAdapter = new NewsAdapter(this, mNewsList);
        mLvNews.setAdapter(mAdapter);
        mTvEmpty = findViewById(R.id.tv_empty);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        initNative();
        loadNetWork();
    }

    private void loadNetWork() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
            setTitle("loading...");
            setLayoutVisibility(true,false,false);
        }

        @Override
        public void onAfter(int id)
        {
            setTitle("News");
            setLayoutVisibility(false,true,false);
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            setLayoutVisibility(false,false,true);
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id)
        {
            // [1]解析response获取到list
            NewsBean newsBean = new Gson().fromJson(response, NewsBean.class);
            // [2]获取视频信息列表
            List<TrailersBean> newsList = newsBean.getTrailers();
            // [2]清空全局list并添加上面list的所有并更新adapter
            if (mNewsList.size() != 0) {
                mNewsList.clear();
            }
            mNewsList.addAll(newsList);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
        }
    }

    private void setLayoutVisibility(boolean pb, boolean lv, boolean tv) {
        mPb.setVisibility(pb?View.VISIBLE:View.GONE);
        mLvNews.setVisibility(lv?View.VISIBLE:View.GONE);
        mTvEmpty.setVisibility(tv?View.VISIBLE:View.GONE);
    }

    /**
     * 暂无要初始化的本地数据,空实现
     */
    private void initNative() {
    }
}
