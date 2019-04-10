package com.liqun.atguigucode.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liqun.atguigucode.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends Activity
implements View.OnClickListener {
    private static final String TAG = "OkHttpActivity";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static final int GET = 1;
    public static final int POST = 2;
    private Button mBtnGet,mBtnPost;
    private TextView mTvResult;
    private OkHttpClient mClient = new OkHttpClient();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    // 将result设置给mTvResult以显示
                    mTvResult.setText((String) msg.obj);
                    break;
                case POST:
                    // 将result设置给mTvResult以显示
                    mTvResult.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();
        setListener();
    }

    private void initView() {
        mBtnGet = findViewById(R.id.btn_get);
        mBtnPost = findViewById(R.id.btn_post);
        mTvResult = findViewById(R.id.tv_result);
    }

    private void setListener() {
        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                // 原生OkHttp联网get请求文本数据
                getDataByGet();
                break;
            case R.id.btn_post:
                // 原生OkHttp联网post请求文本数据
                mTvResult.setText("");
                getDataByPost();
                break;
            default:
                break;
        }
    }

    /**
     * 原生OkHttp联网get请求文本数据
     */
    private void getDataByGet() {
        new Thread(){
            @Override
            public void run() {
                try {
                    String result = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Log.e(TAG, "result = " + result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 原生OkHttp联网post请求文本数据
     */
    private void getDataByPost() {
        new Thread(){
            @Override
            public void run() {
                try {
                    String result = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api","");
                    Log.e(TAG, "result = " + result);
                    Message message = Message.obtain();
                    message.what = POST;
                    message.obj = result;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
