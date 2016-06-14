package com.skateboard.groupclient.network.request;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.skateboard.groupclient.bean.Orders;
import com.skateboard.groupclient.network.base.BaseListener;
import com.skateboard.groupclient.network.base.BaseRequest;

/**
 * Created by skateboard on 16-6-9.
 */
public class OrdersRequest extends BaseRequest<Orders>
{
    public OrdersRequest(int method, String url, Response.Listener successListener, Response.ErrorListener errorListener)
    {
        super(method, url, successListener, errorListener);
    }

    public OrdersRequest(int method, String url, BaseListener listener)
    {
        this(method,url,listener,listener);
    }

    @Override
    protected Orders parseData(String data)
    {
        Gson gson=new Gson();
        Orders orders=gson.fromJson(data, Orders.class);
        return orders;
    }
}
