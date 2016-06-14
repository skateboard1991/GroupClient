package com.skateboard.groupclient.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.skateboard.groupclient.bean.LogInResult;
import com.skateboard.groupclient.network.base.BaseListener;
import com.skateboard.groupclient.network.base.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skateboard on 16-5-24.
 */
public class SignInRequest extends BaseRequest<LogInResult>
{
    private HashMap<String, String> params;

    public SignInRequest(int method, String url, Response.Listener successListener, Response.ErrorListener errorListener)
    {
        super(method, url, successListener, errorListener);
    }

    public SignInRequest(int method, String url, BaseListener listener)
    {
        super(method, url, listener, listener);
    }

    public SignInRequest(int method, String url, BaseListener listener, HashMap<String, String> params)
    {
        super(method, url, listener, listener);
        this.params = params;
    }

    @Override
    protected LogInResult parseData(String data)
    {
        Gson gson=new Gson();
        LogInResult result=gson.fromJson(data,LogInResult.class);
        return result;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return params;
    }

}

