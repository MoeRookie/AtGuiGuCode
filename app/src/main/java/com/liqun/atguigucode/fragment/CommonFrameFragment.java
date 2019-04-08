package com.liqun.atguigucode.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.adapter.CommonFrameFragmentAdapter;
import com.liqun.atguigucode.base.BaseFragment;

public class CommonFrameFragment extends BaseFragment {
    private static final String TAG = "CommonFrameFragment";
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
                Toast.makeText(mCtx, "data = " + mDatas[position], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "常用框架页面的数据被初始化了");
        // 初始化数据
        mDatas = new String[]{
                "OKHttp", "xUtils3","Retrofit2","Fresco",
                "Glide","greenDao","RxJava","volley",
                "Gson","FastJson","picasso","evenBus",
                "jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView",
                "....."
        };
        // 设置adapter
        mAdapter = new CommonFrameFragmentAdapter(mCtx, mDatas);
        mListView.setAdapter(mAdapter);
    }
}
