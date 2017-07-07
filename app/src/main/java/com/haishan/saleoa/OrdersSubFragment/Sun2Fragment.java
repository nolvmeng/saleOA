package com.haishan.saleoa.OrdersSubFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.RefreshableView;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sun2Fragment extends Fragment {
    private   ListView goods_list;
    RefreshableView refreshableView;

    public Sun2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sun2, container, false);
        View  view = inflater.inflate(R.layout.fragment_sun2,container, false);
        goods_list=(ListView)view.findViewById(R.id.addOrders_list);

        updata();//执行异步任务，更新货品展示列表
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
        }, 7);//int参数是记录刷新时间的标志，其它fragment使用时修改值
        return view;
    }
    /**
     * 更新数据**/
    private void updata(){
        String url = config.IP_url + "/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&category=all";

        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.goods_list , 1);
        getDataTask.execute(url, param);
    }








}
