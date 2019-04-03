package com.liqun.atguigucode.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类Fragment
 * CommonFrameFragment、ThirdPartyFragment、CustomViewFragment、AnotherFragment等类继承它
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mCtx;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 强制子类重写,实现其特有的UI
     * @return
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要初始化数据、联网请求绑定数据以及展示数据的时候重写此方法
     */
    protected void initData() {

    }
}
