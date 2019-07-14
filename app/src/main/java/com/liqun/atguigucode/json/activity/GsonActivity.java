package com.liqun.atguigucode.json.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liqun.atguigucode.R;
import com.liqun.atguigucode.json.bean.ShopInfo;

public class GsonActivity extends Activity implements View.OnClickListener {

    private TextView mTvTitle,mTvOriginal,mTvTransformed;
    private Button mBtnJsonObj,mBtnArrList,mBtnObjJson,mBtnListArr;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, GsonActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        initView();
        setListener();
    }

    private void setListener() {
        mBtnJsonObj.setOnClickListener(this);
        mBtnArrList.setOnClickListener(this);
        mBtnObjJson.setOnClickListener(this);
        mBtnListArr.setOnClickListener(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText("Gson解析");
        mBtnJsonObj = findViewById(R.id.btn_json_obj);
        mBtnArrList = findViewById(R.id.btn_arr_list);
        mBtnObjJson = findViewById(R.id.btn_obj_json);
        mBtnListArr = findViewById(R.id.btn_list_arr);
        mTvOriginal = findViewById(R.id.tv_original);
        mTvTransformed = findViewById(R.id.tv_transformed);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // （1）将json格式的字符串{}转换为Java对象
            case R.id.btn_json_obj:
                jsonToObjByGson();
                break;
            // （2）将json格式的字符串[]转换为Java对象的List
            case R.id.btn_arr_list:
                break;
            // （3）将Java对象转换为json字符串{}
            case R.id.btn_obj_json:
                break;
            // （4）将Java对象的List转换为json字符串[]
            case R.id.btn_list_arr:
                break;
        }
    }

    private void jsonToObjByGson() {
        // [1]获取或创建json
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        // [2]json解析
        Gson gson = new Gson();
        ShopInfo info = gson.fromJson(json, ShopInfo.class);
        // [3]显示json数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(info.toString());
    }
}
