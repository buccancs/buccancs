package com.infisense.usbir.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.infisense.usbir.R;
import com.infisense.usbir.view.WaterMark;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        if (!getResources().getBoolean(R.bool.isReleaseVersion)) {
            try {
                WaterMark.getInstance().show(this, getPackageManager().getPackageInfo(
                        getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        initView();
        init(savedInstanceState);
    }

    protected abstract View getContentView();

    public abstract void initView();

    protected abstract void init(Bundle savedInstanceState);
}
