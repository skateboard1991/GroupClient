package com.skateboard.groupclient.network.base;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by skateboard on 16-5-5.
 */
public abstract class BaseRequest<T> extends Request<T> {

    private Response.Listener<T> listener;

    public BaseRequest(int method, String url, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        listener=successListener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String data= null;
        try {
            data = new String(response.data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError());
        }
        T result=parseData(data);
       return Response.success(result,HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
       listener.onResponse(response);
    }

    protected abstract T parseData(String data);
}
