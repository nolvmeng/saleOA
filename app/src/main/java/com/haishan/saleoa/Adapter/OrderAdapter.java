package com.haishan.saleoa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.domain.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heisan on 2017/7/6.
 */

public class OrderAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<OrderItem> orderItemList;
    private List<Map<String, Object>> Orderlist;

    public OrderAdapter(Context context, List<OrderItem> orderItemList) {

        this.mInflater = LayoutInflater.from(context);
        this.orderItemList = orderItemList;
        this.Orderlist = toList(orderItemList);//初始化Map// 列表
    }

    private List<Map<String, Object>> toList(List<OrderItem> orderItemList){
        List<Map<String, Object>> orderlist = new ArrayList<Map<String, Object>>();
        if (orderItemList != null)
        for (OrderItem or : orderItemList){
            /*OrderItem orderItem = (OrderItem)or;*/
            System.out.println(or.getOrder().getOrderId());
            System.out.println(or.getCustomer().getCustomerName());
            System.out.println(or.getTatalPriceS());

            Map<String, Object> ordermap = new HashMap<String, Object>();
            ordermap.put("name_item_detailed", or.getOrder().getOrderId());
            ordermap.put("text3_item", or.getCustomer().getCustomerName());
            ordermap.put("text4_item", or.getTatalPriceS());
            orderlist.add(ordermap);

        }
        return orderlist;
    }




    @Override
    public int getCount() {
        return Orderlist.size();
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
        holder.name_item_detailed.setText((String)Orderlist.get(position).get("name_item_detailed").toString());
        holder.text3_item.setText((String)Orderlist.get(position).get("text3_item").toString());
        holder.text4_item.setText((String)Orderlist.get(position).get("text4_item").toString());

        return convertview;
    }

    public final class ViewHolder{
        public TextView name_item_detailed;
        public TextView text3_item;
        public TextView text4_item;

    }
}
