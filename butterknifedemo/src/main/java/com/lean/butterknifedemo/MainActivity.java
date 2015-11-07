package com.lean.butterknifedemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.btn_one)
    Button btnOne;
    @Nullable
    @Bind(R.id.btn_two)
    Button btnTwo;
    @Nullable
    @Bind(R.id.btn_three)
    Button btnThree;

    @Bind({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    List<TextView> tvs;

    private Fragment03 fg3;
    private Fragment02 fg2;
    private Fragment01 fg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fg1 = new Fragment01();
        fg2 = new Fragment02();
        fg3 = new Fragment03();

        changeFragment(fg1);

        ButterKnife.apply(tvs, DISABLE);
        ButterKnife.apply(tvs, ENABLED, true);
        ButterKnife.apply(tvs, View.ALPHA, 0.0f);
    }

    @OnClick({R.id.btn_one, R.id.btn_two})
    public void onClick(View v) {
        if (v == btnOne) {
            changeFragment(fg1);
        } else if (v == btnTwo) {
            changeFragment(fg2);
        }
    }

    @OnClick(R.id.btn_three)
    public void onClick3() {
        changeFragment(fg3);
    }

    @OnLongClick(R.id.btn_three)
    public boolean onLongClick() {
        Toast.makeText(this, "long press", Toast.LENGTH_SHORT).show();
        return true;
    }


    private void changeFragment(Fragment fg) {
        //把fragment01的界面显示至帧布局中
        //获取fragment管理器
        FragmentManager fm = getFragmentManager();
        //打开事务
        FragmentTransaction ft = fm.beginTransaction();
        //把内容显示至帧布局
        ft.replace(R.id.fl_body, fg);
        //提交
        ft.commit();
    }


    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
            TextView v = (TextView)view;
            v.setText("heihei~" + index);
        }
    };
}
