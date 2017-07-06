package com.haishan.saleoa.GoodsSubFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment3 extends Fragment {
    private ListView vegetables_list;
    private List<Map<String,Object>> vegetablesgoods;

    public SubFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.goods_subfm3,container, false);
        vegetables_list=(ListView)view.findViewById(R.id.vegetables_list);


        updata();//执行异步任务，更新货品展示列表

        return view;
    }
    /**
     * 更新数据**/
    private void updata(){
        String url =  config.IP_url + "/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&category=vegetable ";

        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.vegetables_list);
        getDataTask.execute(url, param);
    }

}
