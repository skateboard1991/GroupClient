package com.skateboard.groupclient.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.skateboard.groupclient.K;
import com.skateboard.groupclient.R;
import com.skateboard.groupclient.xiaomi.XiaoMiPushManager;

/**
 * Created by skateboard on 16-6-14.
 */
public class TipDialogFragment extends DialogFragment
{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.push_register_failed_tip_title));
        builder.setMessage(getString(R.string.push_register_failed_tip_message));
        builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                XiaoMiPushManager.registerPush(getContext().getApplicationContext());
                XiaoMiPushManager.setUserAccount(getContext().getApplicationContext(), K.USER_ACCOUNT);
            }
        });
        return builder.create();
    }
}
