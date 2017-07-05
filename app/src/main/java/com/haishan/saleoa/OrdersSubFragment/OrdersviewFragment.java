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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersviewFragment extends Fragment {
    private ListView orders_list;
    private List<Map<String, Object>> ALLOrders;

    public OrdersviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_subfm1, container, false);
        orders_list = (ListView) view.findViewById(R.id.orders_list);
        ALLOrders = getData();
        MyAdapter adapter = new MyAdapter(getActivity());
        orders_list.setAdapter(adapter);
        return view;
    }

    //列表名称
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> orderlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> ordermap = new HashMap<String, Object>();
        ordermap.put("name_item_detailed", "000001");
        ordermap.put("text3_item", "周博");
        ordermap.put("text4_item", "2017-7-4");
        orderlist.add(ordermap);

        return orderlist;
    }

    public final class ViewHolder{
        public TextView name_item_detailed;
        public TextView text3_item;
        public TextView text4_item;

    }
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyAdapter(Context context){
            this.mInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return ALLOrders.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertview, ViewGroup parentList) {
            ViewHolder holder=null;
            if (convertview==null){
                holder=new ViewHolder();
                convertview=mInflater.inflate(R.layout.orders_listview_item,null);
                holder.name_item_detailed=(TextView)convertview.findViewById(R.id.name_item_detailed);
                holder.text3_item=(TextView)convertview.findViewById(R.id.text3_item);
                holder.text4_item=(TextView)convertview.findViewById(R.id.text4_item);


                convertview.setTag(holder);
            }else {
                holder=(ViewHolder)convertview.getTag();
            }
            holder.name_item_detailed.setText((String)ALLOrders.get(position).get("name_item_detailed"));
            holder.text3_item.setText((String)ALLOrders.get(position).get("text3_item"));
            holder.text4_item.setText((String)ALLOrders.get(position).get("text4_item"));

            orders_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    showDetailed(position);
                }
            });
            return convertview;
        }
    }
    public void showDetailed(int position){
        Bundle bundle=new Bundle();
        bundle.putString("name",ALLOrders.get(position).get("name_item_detailed").toString());
        bundle.putString("reserve",ALLOrders.get(position).get("text3_item").toString());
        bundle.putString("price",ALLOrders.get(position).get("text4_item").toString());
        Intent intent=new Intent(getActivity(), DetailedActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade,R.anim.hold);


    }
}
