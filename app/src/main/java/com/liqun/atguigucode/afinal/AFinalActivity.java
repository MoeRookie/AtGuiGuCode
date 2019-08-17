package com.liqun.atguigucode.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.liqun.atguigucode.R;

public class AFinalActivity extends Activity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AFinalActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
    }
}
