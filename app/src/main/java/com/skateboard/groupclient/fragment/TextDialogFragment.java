package com.skateboard.groupclient.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.skateboard.groupclient.R;


/**
 * Created by skateboard on 16-5-20.
 */
public class TextDialogFragment extends DialogFragment
{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.network_error_title));
        builder.setMessage(getString(R.string.network_error_message));
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                getActivity().finish();
            }
        });
        builder.setCancelable(false);
        return builder.create();
    }
}
