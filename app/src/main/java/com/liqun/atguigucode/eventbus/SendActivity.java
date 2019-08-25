package com.liqun.atguigucode.eventbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liqun.atguigucode.R;

public class SendActivity extends Activity
implements View.OnClickListener {

    private TextView mTvTitle,mTvResult;
    private Button mBtnSend, mBtnReceiveSticky;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SendActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtnSend.setOnClickListener(this);
        mBtnReceiveSticky.setOnClickListener(this);
    }

    private void initData() {
        mTvTitle.setText("EventBus发送数据");
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnSend = findViewById(R.id.btn_send);
        mBtnReceiveSticky = findViewById(R.id.btn_receive_sticky);
        mTvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                break;
            case R.id.btn_receive_sticky:
                break;
        }
    }
}
