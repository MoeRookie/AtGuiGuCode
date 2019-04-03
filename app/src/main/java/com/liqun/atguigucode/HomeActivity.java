package com.liqun.atguigucode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import com.liqun.atguigucode.base.BaseFragment;
import com.liqun.atguigucode.fragment.AnotherFragment;
import com.liqun.atguigucode.fragment.CommonFrameFragment;
import com.liqun.atguigucode.fragment.CustomViewFragment;
import com.liqun.atguigucode.fragment.ThirdPartyFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {

    private RadioGroup mRgHome;
    private List<BaseFragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        setContentView(R.layout.activity_home);
        /**
         * 默认选中第一个Tab
         */
        mRgHome = findViewById(R.id.rg_home);
        mRgHome.check(R.id.rb_common_frame);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new CommonFrameFragment());
        mFragmentList.add(new ThirdPartyFragment());
        mFragmentList.add(new CustomViewFragment());
        mFragmentList.add(new AnotherFragment());
    }
}
