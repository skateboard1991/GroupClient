package com.skateboard.groupclient;

/**
 * Created by skateboard on 16-5-24.
 */
public class K
{
    public static final String HAS_SIGNED_IN="has_signed_in";
    public static final String PERMISSION_STEP="permission_step";
    //server path
    public static final String SERVER_PATH="http://10.10.20.190/flowcontrol/groupclient/";
    public static final String LOGIN_PATH=SERVER_PATH+"login.php";
    public static final String ORDERS_PATH=SERVER_PATH+"orders.php";
    public static final String UPDATE_ORDER_STATE_PATH=SERVER_PATH+"updatestate.php";
    //push server
    public static final String APPID="2882303761517473531";
    public static final String APPKEY="5121747323531";
    public static final String USER_ACCOUNT="group";

    //push message
    public static final String ON_MESSAGE_ARRIVED="on_message_arrived";
    public static final String PUSH_REGISTER_FAILED="push_register_failed";
    public static final String PUSH_REGISTER_SUCCESS="push_register_success";
    public static final String SET_USER_ACCOUNT_FAILED="set_user_account_failed";
    public static final String SET_USER_ACCOUNT_SUCCESS="set_user_account_success";
    public static final String UNSET_ACCOUNT_SUCCESS="unset_account_success";
    public static final String UNSET_ACCOUNT_FAILED="unset_account_failed";

}
