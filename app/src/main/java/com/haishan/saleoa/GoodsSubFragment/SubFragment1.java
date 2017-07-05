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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment1 extends Fragment {
    private ListView goods_list;
    private List<Map<String,Object>> Allgoods;
    public SubFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.goods_subfm1,container, false);
        goods_list=(ListView)view.findViewById(R.id.goods_list);
        Allgoods=getData();
        MyAdapter adapter=new MyAdapter(getActivity());
        goods_list.setAdapter(adapter);
        return view;
    }

    //列表名称
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> goodlist=new ArrayList<Map<String,Object>>();
        Map<String,Object> goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodmap.put("text1_item","1001110");
        goodmap.put("text2_item","6");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","猪肉大白菜");
        goodlist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","牛肉火锅");
        goodlist.add(goodmap);


        return goodlist;
    }

    public final class ViewHolder{
        public TextView name_item;
        public TextView text1_item;
        public TextView text2_item;
        public TextView text3_item;
        public TextView text4_item;

    }
    public class MyAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        public MyAdapter(Context context){
            this.mInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return Allgoods.size();
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
                convertview=mInflater.inflate(R.layout.goods_listview_item,null);
                holder.name_item=(TextView)convertview.findViewById(R.id.name_item);
                holder.text1_item=(TextView)convertview.findViewById(R.id.text1_item);
                holder.text2_item=(TextView)convertview.findViewById(R.id.text_2_item);


                convertview.setTag(holder);
            }else {
                holder=(ViewHolder)convertview.getTag();
            }
            holder.name_item.setText((String)Allgoods.get(position).get("name_item"));
            holder.text1_item.setText((String)Allgoods.get(position).get("text1_item"));
            holder.text2_item.setText((String)Allgoods.get(position).get("text2_item"));

            goods_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            bundle.putString("name",Allgoods.get(position).get("name_item").toString());
            bundle.putString("reserve",Allgoods.get(position).get("text1_item").toString());
            bundle.putString("price",Allgoods.get(position).get("text2_item").toString());
            Intent intent=new Intent(getActivity(), DetailedActivity.class);
           intent.putExtras(bundle);
           startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade,R.anim.hold);


        }





}
