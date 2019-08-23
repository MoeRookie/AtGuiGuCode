package com.liqun.atguigucode.volley;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liqun.atguigucode.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends Activity
implements View.OnClickListener {

    private TextView mTvTitle,mTvResult;
    private Button mBtnGet,mBtnPost,mBtnRequestJson,mBtnImageRequest,mBtnImageLoader,mBtnNetworkImageView;
    private ImageView mIvImage;
    private NetworkImageView mNiv;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, VolleyActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
        mBtnRequestJson.setOnClickListener(this);
        mBtnImageRequest.setOnClickListener(this);
        mBtnImageLoader.setOnClickListener(this);
        mBtnNetworkImageView.setOnClickListener(this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnGet = findViewById(R.id.btn_get);
        mBtnPost = findViewById(R.id.btn_post);
        mBtnRequestJson = findViewById(R.id.btn_request_json);
        mBtnImageRequest = findViewById(R.id.btn_image_request);
        mBtnImageLoader = findViewById(R.id.btn_image_loader);
        mBtnNetworkImageView = findViewById(R.id.btn_network_image_view);
        mIvImage = findViewById(R.id.iv_image);
        mNiv = findViewById(R.id.niv);
        mTvResult = findViewById(R.id.tv_result);
    }

    private void initData() {
        mTvTitle.setText("Volley");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                reqGet();
                break;
            case R.id.btn_post:
                reqPost();
                break;
            case R.id.btn_request_json:
                reqJson();
                break;
            case R.id.btn_image_request:
                reqImage();
                break;
            case R.id.btn_image_loader:
                loadImage();
                break;
            case R.id.btn_network_image_view:
                break;
            default:
                break;
        }
    }

    private void loadImage() {
        // 创建一个请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        // 创建一个imageLoader
        ImageLoader loader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });
        // 请求图片
        mIvImage.setVisibility(View.GONE);
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ImageLoader.ImageListener listener =
                loader.getImageListener(mIvImage, R.drawable.atguigu_logo, R.drawable.atguigu_logo);
        mIvImage.setVisibility(View.VISIBLE);
        loader.get(url,listener);
    }

    private void reqImage() {
        // 1.创建一个请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        // 2.创建一个图片请求
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ImageRequest request = new ImageRequest(url, bitmap -> {// 正确接收数据的回调
            mIvImage.setVisibility(View.VISIBLE);
            mIvImage.setImageBitmap(bitmap);
        }, 0, 0, Bitmap.Config.RGB_565, volleyError -> {// 请求失败或发生异常后的回调
            mIvImage.setImageResource(R.drawable.atguigu_logo);
        });
        mIvImage.setVisibility(View.GONE);
        // 3.将请求添加到请求队列中
        queue.add(request);
    }

    private void reqJson() {
        // 1. 创建一个请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        // 2. 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                jsonObject -> {// 正确接收数据的回调
                    mTvResult.setText(jsonObject.toString());
                }, volleyError -> {// 请求失败或发生异常后的回调
            mTvResult.setText("加载失败:" + volleyError);
        });
        mTvResult.setText("");
        // 3. 将请求添加到请求队列中
        queue.add(request);
    }

    private void reqPost() {
        // 1. 创建一个请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        // 2. 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        StringRequest request = new StringRequest(Request.Method.POST, url, s -> { // 正确接收数据的回调
            mTvResult.setText(s);
        }, volleyError -> {
            mTvResult.setText("加载失败:" + volleyError);
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // params.put("key","value");
                return params;
            }
        };
        mTvResult.setText("");
        // 3. 将请求添加到请求队列中
        queue.add(request);
    }

    private void reqGet() {
        // 1. 创建一个请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        // 2. 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        // 请求失败或发生异常后的回调
        StringRequest request = new StringRequest(url, s -> { // 正确接收数据的回调
            mTvResult.setText(s);
        }, volleyError -> mTvResult.setText("加载失败:"+volleyError));
        mTvResult.setText("");
        // 3. 将请求添加到请求队列中
        queue.add(request);
    }
}
