package com.liqun.atguigucode.butterknife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ButterKnifeActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_text)
    TextView mTvText;
    private Unbinder bind;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ButterKnifeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        bind = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mTvTitle.setText("ButterKnife");
        mTvText.setText("ButterKnife我喜欢你");
    }

    @OnClick({R.id.cb_check, R.id.btn_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_check:
                Toast.makeText(this, "CheckBox被点击了 ...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_button:
                Toast.makeText(this, "Button被点击了 ...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }
}
