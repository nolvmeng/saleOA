package com.haishan.saleoa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haishan.saleoa.OrdersSubFragment.ShopcarActivity;
import com.haishan.saleoa.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Heisan on 2017/7/7.
 */

public class AddOrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    //private SubFragment1.ViewHolder holder;
    private List<Map<String, Object>> goodlist;



    public AddOrderAdapter(Context context, List<Map<String, Object>> goodlist) {

        this.mInflater = LayoutInflater.from(context);
        this.goodlist = goodlist;
    }


    @Override
    public int getCount() {
        return goodlist.size();
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
            convertview=mInflater.inflate(R.layout.order_listview_itemofadd,null);
            holder.goodId=(TextView)convertview.findViewById(R.id.name_item);
            holder.reserve=(TextView)convertview.findViewById(R.id.text1_item);
            holder.goodPrice=(TextView)convertview.findViewById(R.id.text_2_item);
            holder.imageView= (ImageView) convertview.findViewById(R.id.addOrders);
            convertview.setTag(holder);
        }else {
            holder=(ViewHolder)convertview.getTag();
        }
        holder.goodId.setText((String)goodlist.get(position).get("goodName"));
        holder.reserve.setText((String)goodlist.get(position).get("reserve").toString());
        holder.goodPrice.setText((String)goodlist.get(position).get("goodPrice").toString());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopcarActivity.gooodIdList.add(goodlist.get(position).get("goodId").toString());
                ShopcarActivity.goodNameList.add(goodlist.get(position).get("goodName").toString());
                ShopcarActivity.priceList.add((Float) goodlist.get(position).get("goodPrice"));
               // System.out.println(ShopcarActivity.orderList.size());
            }
        });

        return convertview;
    }
    public final class ViewHolder{
        public TextView goodId;
        public TextView reserve;
        public TextView goodPrice;
        public ImageView imageView;



    }
}
