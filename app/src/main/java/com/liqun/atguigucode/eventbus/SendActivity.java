package com.liqun.atguigucode.eventbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.eventbus.event.MessageEvent;
import com.liqun.atguigucode.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SendActivity extends Activity
implements View.OnClickListener {

    private TextView mTvTitle,mTvResult;
    private Button mBtnSend, mBtnReceiveSticky;
    private boolean isFirst;

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
        isFirst = true;
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
                EventBus.getDefault().post(new MessageEvent("李鹏鹏","1357810"));
                finish();
                break;
            case R.id.btn_receive_sticky: // 注册粘性事件
                if (isFirst) {
                    EventBus.getDefault().register(this);
                    isFirst = false;
                }
                break;
        }
    }

    // sticky=true表示接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onReceivedStickyEvent(StickyEvent event){
        mTvResult.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents(); // 移除所有的粘性事件
        EventBus.getDefault().unregister(this);
    }
}
