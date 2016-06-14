package com.skateboard.groupclient.xiaomi;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;


import com.skateboard.groupclient.K;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

import java.util.List;

/**
 * Created by skateboard on 16-5-20.
 */
public class XiaoMiPushManager
{

    public static void registerPush(Context context)
    {
        if (shouldInit(context))
        {
            MiPushClient.registerPush(context, K.APPID, K.APPKEY);
        }
    }

    public static void unRegisterPush(Context context)
    {
        MiPushClient.unregisterPush(context);
    }

    public static void setUserAccount(Context context, String account)
    {
        MiPushClient.setUserAccount(context, account, null);
    }

    private static boolean shouldInit(Context context)
    {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos)
        {
            if (info.pid == myPid && mainProcessName.equals(info.processName))
            {
                return true;
            }
        }
        return false;
    }

    public static void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage)
    {

    }

    public static void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage)
    {

    }

    public static void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage)
    {

    }

    public static void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage)
    {
        if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS)
        {
            System.out.println("registre success");
//            sendBroadCast(context,K.PUSH_REGISTER_SUCCESS);
        } else
        {
            sendBroadCast(context, K.PUSH_REGISTER_FAILED);
        }
    }

    private static void sendBroadCast(Context contexst, String action)
    {
        Intent intent = new Intent(action);
        contexst.sendBroadcast(intent);
    }

    public static void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage)
    {
        String command = miPushCommandMessage.getCommand();
        List<String> commandArguments = miPushCommandMessage.getCommandArguments();
        if (!TextUtils.isEmpty(command))
        {
            if ("set-account".equalsIgnoreCase(command))
            {
                if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS)
                {
                    System.out.println("set user account success");
                    sendBroadCast(context, K.SET_USER_ACCOUNT_SUCCESS);
                    System.out.println("set user account success");
                } else
                {
                    System.out.println("set user account failed");
                    sendBroadCast(context, K.SET_USER_ACCOUNT_FAILED);
                }
            } else if ("unset-account".equalsIgnoreCase(command))
            {
                if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS)
                {
                    System.out.println("unset account success");
                    sendBroadCast(context, K.UNSET_ACCOUNT_SUCCESS);
                }
                else
                {
                    sendBroadCast(context,K.UNSET_ACCOUNT_FAILED);
                }
            }
        }

    }

    public static void onReceiveMessage(Context context, MiPushMessage miPushMessage)
    {
        Intent intent = new Intent(K.ON_MESSAGE_ARRIVED);
        context.sendBroadcast(intent);
    }

    public static void unsetUserAccount(Context context, String userAccount)
    {
        MiPushClient.unsetUserAccount(context, userAccount, null);
    }

}
