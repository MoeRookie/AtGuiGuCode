package com.liqun.atguigucode.xutils3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liqun.atguigucode.R;
import com.liqun.atguigucode.xutils3.annotation.FragmentXUtils3Activity;
import com.liqun.atguigucode.xutils3.net.XUtils3NetActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.activity_xutils3_main)
public class XUtils3MainActivity extends Activity {
    @ViewInject(R.id.tv_title)
    private TextView mTvTitle;
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, XUtils3MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mTvTitle.setText("xUtils3的使用");
    }

    @Event({R.id.btn_annotation,R.id.btn_net,R.id.btn_image,R.id.btn_image_list})
    private void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_annotation:
                Intent intent = FragmentXUtils3Activity.newIntent(XUtils3MainActivity.this);
                startActivity(intent);
                break;
            case R.id.btn_net:
                intent = XUtils3NetActivity.newIntent(XUtils3MainActivity.this);
                startActivity(intent);
                break;
            case R.id.btn_image:
                Toast.makeText(this, "xUtils3加载单张图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_image_list:
                Toast.makeText(this, "xUtils3加载列表图片", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
