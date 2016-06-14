package com.skateboard.groupclient.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.skateboard.groupclient.network.base.BaseListener;
import com.skateboard.groupclient.network.base.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skateboard on 16-6-11.
 */
public class UpdateStateRequest extends BaseRequest<String>
{

    private HashMap<String,String> params;

    public UpdateStateRequest(int method, String url, Response.Listener successListener, Response.ErrorListener errorListener)
    {
        super(method, url, successListener, errorListener);
    }

    public UpdateStateRequest(int method, String url, BaseListener listener, HashMap<String,String> params)
    {
        this(method,url,listener,listener);
        this.params=params;
    }

    @Override
    protected String parseData(String data)
    {
        return data;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return this.params;
    }
}
