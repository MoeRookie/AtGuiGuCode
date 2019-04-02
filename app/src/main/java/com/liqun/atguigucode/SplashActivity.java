package com.liqun.atguigucode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 1、软件框架搭建
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * 延时跳转主界面
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * 在主线程中执行(postDelayed()方法在主线程中被调用)
                 * -> 跳转主界面
                 */
                enterHome();

            }
        },2000);
    }

    /**
     * 跳转主界面
     */
    private void enterHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
