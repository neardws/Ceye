package com.qq.vip.singleangel.v2i_information;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by singl on 2018/4/12.
 */

public class DBApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
