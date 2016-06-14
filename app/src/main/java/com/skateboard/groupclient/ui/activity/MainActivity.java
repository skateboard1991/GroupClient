package com.skateboard.groupclient.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.skateboard.groupclient.K;
import com.skateboard.groupclient.R;
import com.skateboard.groupclient.state.AccountStateManager;
import com.skateboard.groupclient.state.AccountStateManager.StateManager;
import com.skateboard.groupclient.ui.fragment.OrderListFragment;
import com.skateboard.groupclient.xiaomi.XiaoMiPushManager;


public class MainActivity extends BaseActivity
{

    private OrderListFragment orderListFragment;
    private BroadcastReceiver unsetAccountReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initUnsetAccountReceiver();
        registerUnsetAccountReceiver();
    }

    private void initView()
    {
        initToolbar();
        initOrderListFragment();
    }

    private void initUnsetAccountReceiver()
    {
        unsetAccountReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                if (K.UNSET_ACCOUNT_SUCCESS.equalsIgnoreCase(intent.getAction()))
                {
                    StateManager.clearSaveStep();
                    StateManager.clearSignInFlag();
                    Intent showLogInIntent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(showLogInIntent);
                    finish();
                } else if (K.UNSET_ACCOUNT_FAILED.equalsIgnoreCase(intent.getAction()))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.logout_failed), Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    private void registerUnsetAccountReceiver()
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(K.UNSET_ACCOUNT_SUCCESS);
        intentFilter.addAction(K.UNSET_ACCOUNT_FAILED);
        registerReceiver(unsetAccountReceiver, intentFilter);
    }

    private void initToolbar()
    {
        toolbar.setNavigationIcon(null);
        toolbar.setTitle(getString(R.string.order_list));
    }

    private void initOrderListFragment()
    {
        orderListFragment = new OrderListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, orderListFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.account_state:
                showLogIn(item);
                break;
        }
        return true;
    }

    private void showLogIn(MenuItem item)
    {
        if (getString(R.string.signout).equalsIgnoreCase(item.getTitle().toString()))
        {
            XiaoMiPushManager.unsetUserAccount(MainActivity.this.getApplicationContext(), K.USER_ACCOUNT);

        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unRegisterunsetAccountReceiver();
    }

    private void unRegisterunsetAccountReceiver()
    {
        unregisterReceiver(unsetAccountReceiver);
    }
}
