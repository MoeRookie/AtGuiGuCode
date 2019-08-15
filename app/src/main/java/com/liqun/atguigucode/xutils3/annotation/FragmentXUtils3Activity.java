package com.liqun.atguigucode.xutils3.annotation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.liqun.atguigucode.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_fragment_xutils3)
public class FragmentXUtils3Activity extends FragmentActivity {
    @ViewInject(R.id.tv_title)
    private TextView mTvTitle;
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, FragmentXUtils3Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mTvTitle.setText("在Fragment中使用注解");

        // 获取fragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 替换显示目标fragment
        transaction.replace(R.id.fl_content,new DemoFragment());
        // 提交事务
        transaction.commit();
    }
}
