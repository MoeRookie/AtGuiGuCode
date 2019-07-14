package com.liqun.atguigucode.json.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.json.bean.DataInfo;
import com.liqun.atguigucode.json.bean.DataInfo.DataBean;
import com.liqun.atguigucode.json.bean.DataInfo.DataBean.ItemsBean;
import com.liqun.atguigucode.json.bean.FilmInfo;
import com.liqun.atguigucode.json.bean.FilmInfo.FilmBean;
import com.liqun.atguigucode.json.bean.ShopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * （1）将json格式的字符串{}转换为Java对象
 * （2）将json格式的字符串[]转换为Java对象的List
 * （3）复杂json数据解析
 * （4）特殊json数据解析
 */
public class NativeParseActivity extends Activity implements View.OnClickListener {
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
                jsonToObjByNative();
                break;
            // 将json格式的字符串[]转换为Java对象的List
            case R.id.btn_arr_list:
                arrToListNative();
                break;
            // 复杂json数据解析
            case R.id.btn_complex:
                complexNative();
                break;
            // 特殊json数据解析
            case R.id.btn_special:
                specialNative();
                break;
        }
    }

    private void specialNative() {
        // [1]获取或创建JSON数据
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"6008965\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:34\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"6008938\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 404,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:33\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        // [2]解析json
        FilmInfo info = new FilmInfo();
        try {
            JSONObject jsonObj = new JSONObject(json);
            // 第一层解析
            int code = jsonObj.optInt("code");
            JSONObject list = jsonObj.optJSONObject("list");
            info.setCode(code);
            ArrayList<FilmBean> filmList = new ArrayList<>();
            info.setList(filmList);
            // 第二层解析
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObj1 = list.optJSONObject(Integer.toString(i));
                if (jsonObj1 != null) {
                    String aid = jsonObj1.optString("aid");
                    String author = jsonObj1.optString("author");
                    int coins = jsonObj1.optInt("coins");
                    String copyright = jsonObj1.optString("copyright");
                    String create = jsonObj1.optString("create");
                    FilmBean film = new FilmBean();
                    film.setAid(aid);
                    film.setAuthor(author);
                    film.setCoins(coins);
                    film.setCopyright(copyright);
                    film.setCreate(create);
                    filmList.add(film);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // [3]显示JSON数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(info.toString());
    }

    private void complexNative() {
        // [1]获取或创建JSON数据
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"count\": 5,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"坚果\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 132,\n" +
                "                \"title\": \"炒货\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 166,\n" +
                "                \"title\": \"蜜饯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 195,\n" +
                "                \"title\": \"果脯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 196,\n" +
                "                \"title\": \"礼盒\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"rs_code\": \"1000\",\n" +
                "    \"rs_msg\": \"success\"\n" +
                "}";
        // [2]解析json
        DataInfo infoBean = new DataInfo();
        try {
            JSONObject jsonObj = new JSONObject(json);
            // 第一层解析
            JSONObject data = jsonObj.optJSONObject("data");
            String rsCode = jsonObj.optString("rs_code");
            String rsMsg = jsonObj.optString("rs_msg");
            infoBean.setRs_code(rsCode);
            infoBean.setRs_msg(rsMsg);
            DataBean dataBean = new DataBean();
            infoBean.setData(dataBean);
            // 第二层解析
            int count = data.optInt("count");
            JSONArray items = data.optJSONArray("items");
            dataBean.setCount(count);
            ArrayList<ItemsBean> itemList = new ArrayList<>();
            dataBean.setItems(itemList);
            // 第三层解析
            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonObj1 = items.optJSONObject(i);
                if (jsonObj1 != null) {
                    int id = jsonObj1.optInt("id");
                    String title = jsonObj1.optString("title");
                    ItemsBean itemsBean = new ItemsBean();
                    itemsBean.setId(id);
                    itemsBean.setTitle(title);
                    itemList.add(itemsBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // [3]显示JSON数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(infoBean.toString());
    }

    private void arrToListNative() {
        // [1]获取或创建JSON数据
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
        // [2]解析json
        ArrayList<ShopInfo> shopList = new ArrayList<>();
        try {
            JSONArray jsonArr = new JSONArray(json);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                if (jsonObj != null) {
                    int id = jsonObj.optInt("id");
                    String name = jsonObj.optString("name");
                    double price = jsonObj.optDouble("price");
                    String imagePath = jsonObj.optString("imagePath");
                    ShopInfo shop = new ShopInfo(id, name, price, imagePath);
                    shopList.add(shop);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // [3]显示JSON数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(shopList.toString());
    }

    private void jsonToObjByNative() {
        // [1]获取或创建JSON数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        ShopInfo shop = null;
        try {
            // [2]解析json
            JSONObject jsonObj = new JSONObject(json);
            int id = jsonObj.optInt("id");
            String name = jsonObj.optString("name");
            double price = jsonObj.optDouble("price");
            String imagePath = jsonObj.optString("imagePath");
            shop = new ShopInfo(id, name, price, imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // [3]显示JSON数据
        mTvOriginal.setText(json);
        mTvTransformed.setText(shop.toString());
    }
}
