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
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

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
        FinalBitmap bitmap = FinalBitmap.create(this);
        // 设置网络加载图片是默认显示的图片
        bitmap.configLoadfailImage(R.drawable.atguigu_logo);
        // 加载网络图片
        bitmap.display(mIvImage,"http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg");
    }

    // 文本请求
    public void onBtnTextClicked(View view){
        FinalHttp http = new FinalHttp();
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        http.get(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                mTvResult.setText("开始加载");
                super.onStart();
            }

            @Override
            public void onSuccess(String s) {
                // 显示加载成功后的结果
                mTvResult.setText(s);
                super.onSuccess(s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                mTvResult.setText("加载失败");
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    // 文件下载
    public void onBtnDownloadClicked(View view){
        FinalHttp http = new FinalHttp();
        // 请求网络资源的地址
        String url = "http://vfx.mtime.cn/Video/2016/10/11/mp4/161011092841270064_480.mp4";
        // 存放视频文件到本地
        String target = getFilesDir()+"/afinal_music.mp4";
        http.download(url, target, new AjaxCallBack<File>() {
            @Override
            public void onStart() {
                mTvResult.setText("开始下载");
                super.onStart();
            }

            @Override
            public void onSuccess(File file) {
                mTvResult.setText("下载文件成功");
                super.onSuccess(file);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                mTvResult.setText("下载文件失败");
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    // 文件上传
    public void onBtnUploadClicked(View view){
        FinalHttp http = new FinalHttp();
        // 文件上传到服务器的位置
        String url = "http://192.168.1.110:8080/FileUpload/FileUploadServlet";
        AjaxParams params = new AjaxParams();
        // 设置要上传的本地资源
        try {
            params.put("File",new File(getFilesDir()+"/afinal_music.mp4"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        http.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onStart() {
                mTvResult.setText("开始上传");
                super.onStart();
            }

            @Override
            public void onSuccess(Object o) {
                mTvResult.setText("上传成功");
                super.onSuccess(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                mTvResult.setText("上传失败");
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
