package com.felix.zhiban;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;

import java.util.Random;

/**
 * Created by XiaGF on 2017/5/2.
 */

public class TransitionActivity extends BaseActivity {

    private boolean animationEnd;
    private int[] mDrawables = new int[]{R.drawable.b_1, R.drawable.b_2,
            R.drawable.b_3, R.drawable.b_4, R.drawable.b_5, R.drawable.b_6,
    };

    private ImageView imageView;

    private TextView textView;
    private boolean isIn;
    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        initView();
        initData();
        initEvent();
    }

    private void initView(){
        imageView=(ImageView)findViewById(R.id.img_pic);
        textView=(TextView)findViewById(R.id.tv_jump);

    }

    private void initData(){
        int i=new Random().nextInt(mDrawables.length);
        imageView.setImageDrawable(getResources().getDrawable(mDrawables[i]));
    }
    private void initEvent(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    jumpMainActivity();
            }
        },5000);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpMainActivity();
            }
        });

    }

    private void jumpMainActivity(){
        if(isIn){
            return;
        }

        Intent intent=new Intent(this,MainActivity.class);
        startThActivityByIntent(intent);
        finish();
        isIn=true;
    }


}
