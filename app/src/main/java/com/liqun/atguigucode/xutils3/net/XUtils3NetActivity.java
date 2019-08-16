package com.liqun.atguigucode.xutils3.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class XUtils3NetActivity extends Activity {
    private static final String TAG = XUtils3NetActivity.class.getSimpleName();
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
    @ViewInject(R.id.progress_bar)
    private ProgressBar mPb;
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
                reqGetPost();
                break;
            case R.id.btn_download_file:
                downloadFile();
                break;
            case R.id.btn_upload_file:
                Toast.makeText(this, "大文件上传", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void downloadFile() {
        RequestParams params = new RequestParams("http://vfx.mtime.cn/Video/2016/09/02/mp4/160902093947207009_480.mp4");
        /**
         * 配置下载
         */
        // 设置保存路径
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/atguigu/490.mp4");
        // 设置可以立即取消下载
        params.setCancelFast(true);
        // 设置不要自动根据头信息命名
        params.setAutoRename(false);
        // 设置可断点续传
        params.setAutoResume(true);
        // 设置自定义线程池(有效的值范围[1, 3], 设置为 3 时, 可能阻塞图片加载.)
        params.setExecutor(new PriorityExecutor(3, true));
        x.http().get(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法,并把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e(TAG, "onSuccess: " + file.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }

            @Override
            public void onWaiting() {
                Log.e(TAG, "onWaiting: ");
            }

            @Override
            public void onStarted() {
                Log.e(TAG, "onStarted: ");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                mPb.setMax((int) total);
                mPb.setProgress((int) current);
                Log.e(TAG, "onLoading: " + current + "/" + total + " " + isDownloading);
            }
        });
    }

    private void reqGetPost() {
        RequestParams params = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mTvResult.setText("Post请求结果:"+result);
                Log.e(TAG, "xUtils3联网请求成功:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mTvResult.setText("Post请求失败:"+ex.getMessage());
                Log.e(TAG, "xUtils3联网请求失败:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled:" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished");
            }
        });
    }
}
