package com.haishan.saleoa.GoodsSubFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.config.ipconfig;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;



/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment2 extends Fragment {
    private ListView meat_list;
    //private List<Map<String,Object>> meatgoods;

    public SubFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.goods_subfm2,container, false);
        meat_list=(ListView)view.findViewById(R.id.meat_list);

        updata();//执行异步任务，更新货品展示列表

        return view;
    }
    /**
     * 更新数据**/
    private void updata(){
        String url = ipconfig.IP_url + "/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&category=meat ";

        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (getActivity(),clazz, this.meat_list);
        getDataTask.execute(url, param);
    }








}
