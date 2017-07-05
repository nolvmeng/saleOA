package com.haishan.saleoa.GoodsSubFragment;


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
import com.haishan.saleoa.config.ipconfig;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OthersGoodsFragment extends Fragment {
    private ListView othersggoods_list;
    private List<Map<String,Object>> othersggoods;

    public OthersGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.others_goodsfragment,container, false);
        othersggoods_list=(ListView)view.findViewById(R.id.othersgoods_list);

        updata();//执行异步任务，更新货品展示列表

        return view;
    }
    /**
     * 更新数据**/
    private void updata(){
        String url =  ipconfig.IP_url + "/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&category=other ";

        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.othersggoods_list);
        getDataTask.execute(url, param);
    }
}
