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

public class EventBusActivity extends Activity
implements View.OnClickListener {

    private TextView mTvTitle,mTvResult;
    private Button mBtnSend,mBtnSendSticky;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EventBusActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtnSend.setOnClickListener(this);
        mBtnSendSticky.setOnClickListener(this);
    }

    private void initData() {
        mTvTitle.setText("EventBus");
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnSend = findViewById(R.id.btn_send);
        mBtnSendSticky = findViewById(R.id.btn_send_sticky);
        mTvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send: // 跳转到发送界面
                enterSend();
                break;
            case R.id.btn_send_sticky: // 发送粘性事件并跳转到发送界面
                EventBus.getDefault().postSticky(new StickyEvent("粘性事件"));
                enterSend();
                break;
        }
    }

    private void enterSend() {
        Intent intent = SendActivity.newIntent(this);
        startActivity(intent);
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onReceivedEvent(MessageEvent event){
        mTvResult.setText("username = " + event.name + "\npassword = " + event.password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
