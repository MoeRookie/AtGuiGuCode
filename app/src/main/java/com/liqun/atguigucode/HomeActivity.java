package com.liqun.atguigucode;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

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
    private BaseFragment tempFragment; // 暂存当前的fragment

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
                switchFragment(tempFragment,fragment);
            }
        });
        // 设置默认显示常用框架的fragment
        mRgHome.check(R.id.rb_common_frame);
    }


    /**
     * 根据position获取要显示的fragment
     * @return
     */
    private BaseFragment getFragment() {
        return mFragmentList.get(mPosition);
    }


    private void switchFragment(BaseFragment fromFragment,BaseFragment toFragment) {
        // 判断fromFragment与toFragment是否为同一个fragment,不是才需要切换
        if (tempFragment != toFragment) {
            // 暂存将要显示的fragment
            tempFragment = toFragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 判断当前要显示的fragment是否被添加过
            if (!toFragment.isAdded()) { // 没有被添加过
                // 隐藏from
                if (fromFragment != null) {
                    transaction.hide(fromFragment);
                }
                // 添加to并提交
                if (toFragment != null) {
                    transaction.add(R.id.fl_content,toFragment).commit();
                }
            }else{ // 被添加过
                // 隐藏from
                if (fromFragment != null) {
                    transaction.hide(fromFragment);
                }
                // 显示to并提交
                if (toFragment != null) {
                    transaction.show(toFragment).commit();
                }
            }
        }
    }
}
