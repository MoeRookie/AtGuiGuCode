package com.liqun.atguigucode.afinal;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class AFinalActivity extends FinalActivity {
    @ViewInject(id = R.id.tv_title)
    private TextView mTvTitle;
    @ViewInject(id = R.id.btn_image,click = "onBtnImageClicked")
    private Button mBtnImage;
    @ViewInject(id = R.id.btn_text,click = "onBtnTextClicked")
    private Button mBtnText;
    @ViewInject(id = R.id.btn_download,click = "onBtnDownloadClicked")
    private Button mBtnDownload;
    @ViewInject(id = R.id.btn_upload,click = "onBtnUploadClicked")
    private Button mBtnUpload;
    @ViewInject(id = R.id.iv_image)
    private ImageView mIvImage;
    @ViewInject(id = R.id.tv_result)
    private TextView mTvResult;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AFinalActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
        initData();
    }

    private void initData() {
        mTvTitle.setText("Afinal");
    }

    // 加载图片
    public void onBtnImageClicked(View view){
        Toast.makeText(this, "加载图片", Toast.LENGTH_SHORT).show();
    }

    // 文本请求
    public void onBtnTextClicked(View view){
        Toast.makeText(this, "文本请求", Toast.LENGTH_SHORT).show();
    }

    // 文件下载
    public void onBtnDownloadClicked(View view){
        Toast.makeText(this, "文件下载", Toast.LENGTH_SHORT).show();
    }

    // 文件上传
    public void onBtnUploadClicked(View view){
        Toast.makeText(this, "文件上传", Toast.LENGTH_SHORT).show();
    }
}
