package com.liqun.atguigucode.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.json.activity.GsonActivity;
import com.liqun.atguigucode.json.activity.NativeParseActivity;
import com.liqun.atguigucode.okhttp.activity.OkHttpActivity;
import com.liqun.atguigucode.adapter.CommonFrameFragmentAdapter;
import com.liqun.atguigucode.base.BaseFragment;

public class CommonFrameFragment extends BaseFragment {
    private static final String TAG = "CommonFrameFragment";
    private static final String OKHTTP = "okhttp";
    private static final String NATIVE_PARSE = "nativeparse";
    private static final String GSON = "gson";
    private static final String FAST_JSON = "fastjson";
    private String[] mDatas;
    private ListView mListView;
    private CommonFrameFragmentAdapter mAdapter;

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架页面被初始化了");
        View view = View.inflate(mCtx, R.layout.fragment_common_frame, null);
        mListView = view.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = mDatas[position];
                if (OKHTTP.equals(data.toLowerCase())) {
                    Intent intent = new Intent(mCtx, OkHttpActivity.class);
                    startActivity(intent);
                } else if (NATIVE_PARSE.equals(data.toLowerCase())) {
                    Intent intent = NativeParseActivity.newIntent(mCtx);
                    startActivity(intent);
                } else if (GSON.equals(data.toLowerCase())) {
                    Intent intent = GsonActivity.newIntent(mCtx);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "常用框架页面的数据被初始化了");
        // 初始化数据
        mDatas = new String[]{
                "OKHttp", "NativeParse","Gson","FastJson","xUtils3","Retrofit2","Fresco",
                "Glide","greenDao","RxJava","volley",
                "picasso","evenBus",
                "jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView",
                "....."
        };
        // 设置adapter
        mAdapter = new CommonFrameFragmentAdapter(mCtx, mDatas);
        mListView.setAdapter(mAdapter);
    }
}
