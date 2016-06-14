package com.skateboard.groupclient.ui.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.skateboard.groupclient.K;
import com.skateboard.groupclient.R;
import com.skateboard.groupclient.fragment.TextDialogFragment;

/**
 * Created by skateboard on 16-5-24.
 */
public class SplashActivity extends AppCompatActivity
{
    private ImageView splashImg;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkInternet();
        splashImg= (ImageView) findViewById(R.id.splash_img);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                enterNextActivity();
            }
        },3000);
    }

    private void enterNextActivity()
    {
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean(K.HAS_SIGNED_IN,false))
        {
            openMainActivity();
        }
        else
        {
            openSignInActivity();
        }
    }

    private void openMainActivity()
    {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openSignInActivity()
    {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkInternet()
    {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable())
        {
            new TextDialogFragment().show(getSupportFragmentManager(), null);
        }
    }
}
