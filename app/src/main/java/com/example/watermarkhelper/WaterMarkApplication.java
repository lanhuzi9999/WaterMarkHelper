package com.example.watermarkhelper;

import android.app.Application;
import android.graphics.Color;

import com.example.watermarkhelper.BuildConfig;
import com.example.watermarkhelper.util.WaterMarkHelper;

/**
 * @author: robin
 * @description:
 * @date: 2015/7/8
 **/
public class WaterMarkApplication extends Application {
    private WaterMarkHelper mWaterMarkHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            mWaterMarkHelper = new WaterMarkHelper();
            mWaterMarkHelper.installWaterMark(this, 0, "Debug测试版", 25 * 3f, Color.BLUE & 0x19ffffff);
        }
    }

    @Override
    public void onTerminate() {
        if (mWaterMarkHelper != null) {
            mWaterMarkHelper.uninstallWaterMark(this);
        }
        super.onTerminate();
    }
}
