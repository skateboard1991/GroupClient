package com.skateboard.groupclient.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.skateboard.groupclient.K;
import com.skateboard.groupclient.R;
import com.skateboard.groupclient.bean.Order;
import com.skateboard.groupclient.bean.Orders;
import com.skateboard.groupclient.network.base.BaseListener;
import com.skateboard.groupclient.network.base.RequestHolder;
import com.skateboard.groupclient.network.request.OrdersRequest;
import com.skateboard.groupclient.network.request.UpdateStateRequest;
import com.skateboard.groupclient.state.AccountStateManager.StateManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by skateboard on 16-5-24.
 */
public class OrderListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    private FrameLayout loadingLayout;
    private RecyclerView orderList;
    private ArrayList<Order> data;
    private SwipeRefreshLayout refreshLayout;
    private String orderNumber;
    private HashMap<String,String> params=new HashMap<>();
    private int dealPosition;
    private BroadcastReceiver onMessageArrivedReceiver;
    private String state;

    private RequestHolder holder = new RequestHolder()
    {
        @Override
        public Request newRequest()
        {
            return new OrdersRequest(Request.Method.GET, K.ORDERS_PATH, new OrderRequestListener());
        }
    };

    @Override
    public void onRefresh()
    {
        loadingLayout.setVisibility(View.VISIBLE);
        holder.execute();
    }

    private class OrderRequestListener extends BaseListener<Orders>
    {
        @Override
        public void onResponse(Orders response)
        {
            super.onResponse(response);
            loadingLayout.setVisibility(View.GONE);
            data = response.getORDER_LIST();
            initOrderList();
            refreshLayout.setRefreshing(false);
        }

        @Override
        public void onErrorResponse(VolleyError error)
        {
            super.onErrorResponse(error);
            loadingLayout.setVisibility(View.GONE);
            refreshLayout.setRefreshing(false);

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initOnMessageArrivedReceiver();
        setState(getString(R.string.start));
    }

    private void setState(String state)
    {
        this.state=state;
    }

    private void initOnMessageArrivedReceiver()
    {
        onMessageArrivedReceiver=new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                 onRefresh();
            }
        };
        IntentFilter intentFilter=new IntentFilter(K.ON_MESSAGE_ARRIVED);
        getActivity().registerReceiver(onMessageArrivedReceiver,intentFilter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_orderlist, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        orderList = (RecyclerView) view.findViewById(R.id.order_list);
        loadingLayout = (FrameLayout) view.findViewById(R.id.loading_layout);
        loadingLayout.setVisibility(View.VISIBLE);
        holder.execute();
        initRefreshLayout(view);
    }



    private void initRefreshLayout(View view)
    {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setRefreshing(false);
        refreshLayout.setOnRefreshListener(this);
    }


    private void initOrderList()
    {
        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.setAdapter(new OrdersAdapter());
    }


    private class OrdersAdapter extends RecyclerView.Adapter<ItemViewHolder>
    {

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position)
        {
            holder.orderNumber.setText(data.get(position).getORDERNUMBER());
            holder.orderState.setText(data.get(position).getSTATE());
            orderNumber=data.get(position).getORDERNUMBER();
//              holder.itemView.setOnClickListener(onItemClickListener);
            if (data.get(position).getSTATE().equals(StateManager.getStep()))
            {
                dealPosition=position;
                holder.btn.setText(state);
                holder.btn.setEnabled(true);
                holder.btn.setVisibility(View.VISIBLE);
                holder.btn.setOnClickListener(onButtonClickListener);
            }

        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderNumber;
        TextView orderState;
        Button btn;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            orderNumber = (TextView) itemView.findViewById(R.id.order_number);
            orderState = (TextView) itemView.findViewById(R.id.order_state);
            btn = (Button) itemView.findViewById(R.id.btn);
        }
    }

    private OnClickListener onButtonClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btn:
                    if (getResources().getString(R.string.start).equalsIgnoreCase(state))
                    {
                        setState(getString(R.string.complete));
                        ((Button) v).setText(getResources().getString(R.string.complete));
                        params.put("ordernumber",orderNumber);
                        params.put("state",data.get(dealPosition).getSTATE()+"开始");
                        updateStateHolder.execute();

                    } else if (getResources().getString(R.string.complete).equalsIgnoreCase(state))
                    {
                        v.setEnabled(false);
                        params.put("ordernumber",orderNumber);
                        params.put("state",data.get(dealPosition).getSTATE()+"完成");
                        updateStateHolder.execute();
                    }
                    break;
            }
        }
    };

    private RequestHolder updateStateHolder=new RequestHolder()
    {
        @Override
        public Request newRequest()
        {
            return new UpdateStateRequest(Request.Method.POST,K.UPDATE_ORDER_STATE_PATH,new UpdateStateListener(),params);
        }
    };

    private class UpdateStateListener extends BaseListener<String>
    {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            super.onErrorResponse(error);
        }

        @Override
        public void onResponse(String response)
        {
            super.onResponse(response);
        }
    }

    private View.OnLongClickListener onItemLongClickListener = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            return false;
        }
    };

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterOnMessageArrivedReceiver();

    }

    private void unregisterOnMessageArrivedReceiver()
    {
        getActivity().unregisterReceiver(onMessageArrivedReceiver);
    }
}
