package com.skateboard.groupclient.state;

import android.content.Context;
import android.preference.PreferenceManager;

import com.skateboard.groupclient.K;

/**
 * Created by skateboard on 16-6-10.
 */
public class AccountStateManager
{

    private Context context;
    private static AccountStateManager accountStateManager;

    public static void init(Context context)
    {
        accountStateManager=new AccountStateManager(context);
    }

    private AccountStateManager(Context context)
    {
        this.context=context;
    }

    public static AccountStateManager getDefault()
    {
        return accountStateManager;
    }

    private void savehasSignInFlag()
    {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putBoolean(K.HAS_SIGNED_IN, true).commit();
    }

    private void saveStep(String step)
    {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putString(K.PERMISSION_STEP,step).commit();
    }

    private void clearSignInFlag()
    {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putBoolean(K.HAS_SIGNED_IN,false).commit();
    }

    private void clearSaveStep()
    {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putString(K.PERMISSION_STEP,"").commit();
    }

    private String getStep()
    {
        String step=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString(K.PERMISSION_STEP,"");
        return step;
    }

    public static class StateManager
    {
        public static void savehasSignInFlag()
        {
            AccountStateManager.getDefault().savehasSignInFlag();
        }

        public static void saveStep(String step)
        {
            AccountStateManager.getDefault().saveStep(step);
        }

        public static void clearSignInFlag()
        {
            AccountStateManager.getDefault().clearSignInFlag();
        }

        public static void clearSaveStep()
        {
            AccountStateManager.getDefault().clearSaveStep();
        }

        public static String getStep()
        {
            String step=AccountStateManager.getDefault().getStep();
            return step;
        }

    }

}
