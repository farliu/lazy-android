package com.ofcoder.lazy.window.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.ofcoder.lazy.util.ActivityUtil;

/**
 * Created by Administrator on 2017/7/3.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = "BaseActivity";


    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initLoad();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int viewId = getContentViewId();
        if (viewId != 0) {
            setContentView(viewId);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        initView();
        initLoad();
        Log.d(TAG, getClass().getSimpleName() + "onCreate");
        ActivityUtil.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.removeActivity(this);
    }
}
