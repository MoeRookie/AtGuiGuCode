package com.liqun.atguigucode.json.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liqun.atguigucode.R;

/**
 * （1）将json格式的字符串{}转换为Java对象
 * （2）将json格式的字符串[]转换为Java对象的List
 * （3）复杂json数据解析
 * （4）特殊json数据解析
 */
public class NativeParseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvTitle;
    private Button mBtnJsonObj;
    private Button mBtnArrList;
    private Button mBtnComplex;
    private Button mBtnSpecial;
    private TextView mTvOriginal;
    private TextView mTvTransformed;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, NativeParseActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_parse);
        initView();
        setListener();
    }

    private void setListener() {
        mBtnJsonObj.setOnClickListener(this);
        mBtnArrList.setOnClickListener(this);
        mBtnComplex.setOnClickListener(this);
        mBtnSpecial.setOnClickListener(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("手动Json解析");
        mBtnJsonObj = findViewById(R.id.btn_json_obj);
        mBtnArrList = findViewById(R.id.btn_arr_list);
        mBtnComplex = findViewById(R.id.btn_complex);
        mBtnSpecial = findViewById(R.id.btn_special);
        mTvOriginal = findViewById(R.id.tv_original);
        mTvTransformed = findViewById(R.id.tv_transformed);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 将json格式的字符串{}转换为Java对象
            case R.id.btn_json_obj:
                break;
            // 将json格式的字符串[]转换为Java对象的List
            case R.id.btn_arr_list:
                break;
            // 复杂json数据解析
            case R.id.btn_complex:
                break;
            // 特殊json数据解析
            case R.id.btn_special:
                break;
        }
    }
}
