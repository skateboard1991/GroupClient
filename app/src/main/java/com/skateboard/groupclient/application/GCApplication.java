package com.skateboard.groupclient.application;

import android.app.Application;

import com.skateboard.groupclient.K;
import com.skateboard.groupclient.network.base.CVolley;
import com.skateboard.groupclient.state.AccountStateManager;
import com.skateboard.groupclient.xiaomi.XiaoMiPushManager;

/**
 * Created by skateboard on 16-5-24.
 */
public class GCApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        CVolley.init(getApplicationContext());
        XiaoMiPushManager.registerPush(getApplicationContext().getApplicationContext());
        AccountStateManager.init(getApplicationContext());
    }

}
