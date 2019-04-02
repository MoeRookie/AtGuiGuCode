package com.liqun.atguigucode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

public class HomeActivity extends FragmentActivity {

    private RadioGroup mRgHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /**
         * 默认选中第一个Tab
         */
        mRgHome = findViewById(R.id.rg_home);
        mRgHome.check(R.id.rb_common_frame);
    }
}
