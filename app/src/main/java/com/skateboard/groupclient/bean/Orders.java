package com.skateboard.groupclient.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by skateboard on 16-5-18.
 */
public class Orders implements Serializable
{

    private ArrayList<Order> ORDER_LIST;

    public ArrayList<Order> getORDER_LIST()
    {
        return ORDER_LIST;
    }

    public void setORDER_LIST(ArrayList<Order> ORDER_LIST)
    {
        this.ORDER_LIST = ORDER_LIST;
    }
}
