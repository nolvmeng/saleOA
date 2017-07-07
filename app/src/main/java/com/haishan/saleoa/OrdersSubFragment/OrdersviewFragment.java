package com.haishan.saleoa.OrdersSubFragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.haishan.saleoa.DetailedActivity;
import com.haishan.saleoa.R;
import com.haishan.saleoa.RefreshableView;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.domain.GoodItem;
import com.haishan.saleoa.domain.OrderItem;
import com.haishan.saleoa.tasks.GetDataTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haishan.saleoa.config.config.user_Id;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersviewFragment extends Fragment {
    private ListView orders_list;
    private List<Map<String, Object>> ALLOrders;
    private RefreshableView refreshableView;

    public OrdersviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_subfm1, container, false);
        orders_list = (ListView) view.findViewById(R.id.orders_list);

        updata();
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(500);
                    updata();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 5);//int参数是记录刷新时间的标志，其它fragment使用时修改值
        return view;
    }
    //更新数据方法
    private void updata(){

        String url = config.IP_url + "/SaleForAD/servlet/OrderServlet";
        String param =  "method=getOrder&userId="+user_Id;

        Class<OrderItem> clazz = OrderItem.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.orders_list);
        getDataTask.execute(url, param);
    }



}
