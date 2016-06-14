package com.skateboard.groupclient.network.base;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by skateboard on 16-5-6.
 */
public class CVolley {

    public static RequestQueue requestQueue;

    public static void init(Context context)
    {
        requestQueue= Volley.newRequestQueue(context);
    }
}
