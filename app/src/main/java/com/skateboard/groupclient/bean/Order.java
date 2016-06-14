package com.skateboard.groupclient.bean;

import java.io.Serializable;

/**
 * Created by skateboard on 16-5-19.
 */
public class Order implements Serializable
{
    private String DATE;
    private String ID;
    private String ORDERNUMBER;
    private String TYPE;
    private String VOLTAGE;
    private String SIZE;
    private String AMOUNT;
    private String STATE;
    private String NOTE;


    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getORDERNUMBER()
    {
        return ORDERNUMBER;
    }

    public void setORDERNUMBER(String ORDERNUMBER)
    {
        this.ORDERNUMBER = ORDERNUMBER;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getVOLTAGE()
    {
        return VOLTAGE;
    }

    public void setVOLTAGE(String VOLTAGE)
    {
        this.VOLTAGE = VOLTAGE;
    }

    public String getSIZE()
    {
        return SIZE;
    }

    public void setSIZE(String SIZE)
    {
        this.SIZE = SIZE;
    }

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public String getSTATE()
    {
        return STATE;
    }

    public void setSTATE(String STATE)
    {
        this.STATE = STATE;
    }

    public String getNOTE()
    {
        return NOTE;
    }

    public void setNOTE(String NOTE)
    {
        this.NOTE = NOTE;
    }

    public String getDATE()
    {
        return DATE;
    }

    public void setDATE(String DATE)
    {
        this.DATE = DATE;
    }
}
