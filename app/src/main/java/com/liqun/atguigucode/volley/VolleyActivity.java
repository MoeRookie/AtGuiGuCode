package com.liqun.atguigucode.volley;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.liqun.atguigucode.R;

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
        String tempStr = null;
        switch (view.getId()) {
            case R.id.btn_get:
                tempStr = "get请求";
                break;
            case R.id.btn_post:
                tempStr = "post请求";
                break;
            case R.id.btn_request_json:
                tempStr = "请求json数据";
                break;
            case R.id.btn_image_request:
                tempStr = "ImageRequest加载图片";
                break;
            case R.id.btn_image_loader:
                tempStr = "ImageLoader加载图片";
                break;
            case R.id.btn_network_image_view:
                tempStr = "NetworkImageView加载图片";
                break;
            default:
                break;
        }
        Toast.makeText(this, tempStr, Toast.LENGTH_SHORT).show();
    }
}
