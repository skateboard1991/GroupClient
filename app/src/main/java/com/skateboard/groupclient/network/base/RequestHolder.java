package com.skateboard.groupclient.network.base;


import com.android.volley.Request;


/**
 * Created by skateboard on 16-5-5.
 */
public abstract class RequestHolder<T> {


    public void execute()
    {
       CVolley.requestQueue.add(newRequest());
    }

    public abstract  Request<T> newRequest();

}
