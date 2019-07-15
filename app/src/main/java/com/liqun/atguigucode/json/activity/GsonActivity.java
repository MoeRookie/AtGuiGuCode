package com.liqun.atguigucode.json.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liqun.atguigucode.R;
import com.liqun.atguigucode.json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

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
                arrToListByGson();
                break;
            // （3）将Java对象转换为json字符串{}
            case R.id.btn_obj_json:
                objToJsonByGson();
                break;
            // （4）将Java对象的List转换为json字符串[]
            case R.id.btn_list_arr:
                listToArrByGson();
                break;
        }
    }

    private void listToArrByGson() {
        // [1]获取或创建Java集合
        ArrayList<ShopInfo> shopList = new ArrayList<>();
        ShopInfo fish = new ShopInfo(110, "鲍鱼", 250.0, "baoyu.jpg");
        ShopInfo shrimp = new ShopInfo(120, "龙虾", 251.0, "longxia.jpg");
        shopList.add(fish);
        shopList.add(shrimp);
        // [2]生成json
        Gson gson = new Gson();
        String json = gson.toJson(shopList);
        // [3]展示json数据
        mTvOriginal.setText(shopList.toString());
        mTvTransformed.setText(json);
    }

    private void objToJsonByGson() {
        // [1]获取或创建Java对象
        ShopInfo shop = new ShopInfo(110, "鲍鱼", 250.0, "baoyu.jpg");
        // [2]生成json
        Gson gson = new Gson();
        String json = gson.toJson(shop);
        // [3]展示json数据
        mTvOriginal.setText(shop.toString());
        mTvTransformed.setText(json);
    }

    private void arrToListByGson() {
        // [1]获取或创建json
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";
        // [2]json解析
        Gson gson = new Gson();
        List<ShopInfo> shopList = gson.fromJson(json,new TypeToken<List<ShopInfo>>(){}.getType());
        // [3]显示json数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(shopList.toString());
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
