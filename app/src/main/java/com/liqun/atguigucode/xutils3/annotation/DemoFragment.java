package com.liqun.atguigucode.xutils3.annotation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.liqun.atguigucode.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.fragment_demo)
class DemoFragment extends Fragment {
    @ViewInject(R.id.btn_button)
    private Button mBtnButton;
    @ViewInject(R.id.tv_text)
    private TextView mTvText;
    private Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mTvText.setText("在fragment中初始化文本");
        mBtnButton.setText("我是fragment中的按钮");
        mBtnButton.setOnClickListener(view -> {
            Toast.makeText(mContext, "fragment中的按钮被点击了 ...", Toast.LENGTH_SHORT).show();
        });
        super.onActivityCreated(savedInstanceState);
    }
}
