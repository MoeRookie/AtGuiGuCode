package com.liqun.atguigucode.okhttp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
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
    private Button mBtnOkGet;
    private Button mBtnOkPost;
    private Button mBtnDownloadFile;
    private Button mBtnUploadFile;
    private Button mBtnDownloadImage;
    private Button mBtnDownloadImages;
    private ProgressBar mProgressBar;
    private ImageView mIvIcon;


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
        mBtnOkGet = findViewById(R.id.btn_ok_get);
        mBtnOkPost = findViewById(R.id.btn_ok_post);
        mBtnDownloadFile = findViewById(R.id.btn_download_file);
        mBtnUploadFile = findViewById(R.id.btn_upload_file);
        mBtnDownloadImage = findViewById(R.id.btn_download_image);
        mBtnDownloadImages = findViewById(R.id.btn_download_images);
        mProgressBar = findViewById(R.id.pb);
        mTvResult = findViewById(R.id.tv_result);
        mIvIcon = findViewById(R.id.iv_icon);
    }

    private void setListener() {
        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
        mBtnOkGet.setOnClickListener(this);
        mBtnOkPost.setOnClickListener(this);
        mBtnDownloadFile.setOnClickListener(this);
        mBtnUploadFile.setOnClickListener(this);
        mBtnDownloadImage.setOnClickListener(this);
        mBtnDownloadImages.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                // 原生OkHttp联网get请求文本数据
                mTvResult.setText("");
                getDataByGet();
                break;
            case R.id.btn_post:
                // 原生OkHttp联网post请求文本数据
                mTvResult.setText("");
                getDataByPost();
                break;
            case R.id.btn_ok_get:
                mTvResult.setText("");
                getDataByOkGet();
                break;
            case R.id.btn_ok_post:
                mTvResult.setText("");
                getDataByOkPost();
                break;
            case R.id.btn_download_file:
                downloadFile();
                break;
            case R.id.btn_upload_file:
                multiFileUpload();
                break;
            case R.id.btn_download_image:
                getImage();
                break;
            case R.id.btn_download_images:
                enterNewsActivity();
                break;
            default:
                break;
        }
    }

    private void enterNewsActivity() {
        Intent intent = NewsActivity.newIntent(this);
        startActivity(intent);
    }

    public void getImage()
    {
        mTvResult.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        mTvResult.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete");
                        mIvIcon.setImageBitmap(bitmap);
                    }
                });
    }

    public void multiFileUpload()
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "http-utils-test.mp4");
        if (!file.exists())
        {
            Toast.makeText(OkHttpActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        String url = "http://10.185.1.71:8080/FileUpload/FileUploadServlet";
        OkHttpUtils.post()//
                .addFile("mFile", "server-http-utils-test.mp4", file)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    public void downloadFile() {
        String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724055620533327_480.mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "http-utils-test.mp4")//
                {

                    @Override
                    public void onBefore(Request request, int id)
                    {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id)
                    {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id)
                    {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    private void getDataByOkPost() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    private void getDataByOkGet() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id)
        {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
            mTvResult.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
            Log.e(TAG, "onResponse：complete");
            mTvResult.setText("onResponse:" + response);

            switch (id)
            {
                case 100:
                    Toast.makeText(OkHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OkHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
            mProgressBar.setProgress((int) (100 * progress));
            Log.e(TAG, "inProgress:" + progress);
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
