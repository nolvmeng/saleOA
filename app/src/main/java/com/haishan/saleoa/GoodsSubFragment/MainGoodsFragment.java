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
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainGoodsFragment extends Fragment {
    private ListView mainggoods_list;
    private List<Map<String,Object>> maingoods;

    public MainGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.main_goodsfragment,container, false);
        mainggoods_list=(ListView)view.findViewById(R.id.maingoods_list);

        updata();//执行异步任务，更新货品展示列表

        return view;
    }
    /**
     * 更新数据**/
    private void updata(){
        String url =  "http://10.0.2.10:8080/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&category=main ";

        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.mainggoods_list);
        getDataTask.execute(url, param);
    }
}
