package com.liqun.atguigucode.json.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liqun.atguigucode.R;

/**
 * （1）将json格式的字符串{}转换为Java对象
 * （2）将json格式的字符串[]转换为Java对象的List
 * （3）复杂json数据解析
 * （4）特殊json数据解析
 */
public class NativeParseActivity extends AppCompatActivity {
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, NativeParseActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_parse);
    }
}
