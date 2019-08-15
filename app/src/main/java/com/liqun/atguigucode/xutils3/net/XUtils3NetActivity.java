package com.liqun.atguigucode.xutils3.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.xutils3.XUtils3MainActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.activity_xutils3_net)
public class XUtils3NetActivity extends Activity {
    @ViewInject(R.id.tv_title)
    private TextView mTvTitle;
    @ViewInject(R.id.btn_get_post)
    private Button mBtnGetPost;
    @ViewInject(R.id.btn_download_file)
    private Button mBtnDownloadFile;
    @ViewInject(R.id.btn_upload_file)
    private Button mBtnUploadFile;
    @ViewInject(R.id.tv_result)
    private TextView mTvResult;
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, XUtils3NetActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_get_post,R.id.btn_download_file,R.id.btn_upload_file})
    private void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_get_post:
                Toast.makeText(this, "Get和Post请求", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_download_file:
                Toast.makeText(this, "大文件下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_upload_file:
                Toast.makeText(this, "大文件上传", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
