package com.liqun.atguigucode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
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

    /**
     * 设置控件等的监听
     */
    private void setListener() {
        mRgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_common_frame: // 常用框架
                        mPosition = 0;
                        break;
                    case R.id.rb_third_party: // 第三方
                        mPosition = 1;
                        break;
                    case R.id.rb_custom_view: // 自定义View
                        mPosition = 2;
                        break;
                    case R.id.rb_another: // 其他
                        mPosition = 3;
                        break;
                    default:
                        mPosition = 0;
                        break;
                }
                // 根据position获取fragment
                BaseFragment fragment = getFragment();
                // 切换fragment
                switchFragment(fragment);
            }
        });
    }


    /**
     * 根据position获取要显示的fragment
     * @return
     */
    private BaseFragment getFragment() {
        return mFragmentList.get(mPosition);
    }

    /**
     * 切换到当前fragment以显示
     * @param fragment
     */
    private void switchFragment(BaseFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content,fragment);
        transaction.commit();
    }
}
