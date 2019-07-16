package com.liqun.atguigucode.xutils3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.liqun.atguigucode.R;

public class XUtils3MainActivity extends Activity {
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, XUtils3MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils3_main);
    }
}
