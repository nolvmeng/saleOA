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
        maingoods=getData();
        MyAdapter adapter=new MyAdapter(getActivity());
        mainggoods_list.setAdapter(adapter);
        return view;
    }

    //列表名称
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> maingoodslist=new ArrayList<Map<String,Object>>();
        Map<String,Object> goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","米饭");
        maingoodslist.add(goodmap);
        goodmap=new HashMap<String, Object>();
        goodmap.put("name_item","面条");
        maingoodslist.add(goodmap);




        return maingoodslist;
    }

    public final class ViewHolder{
        public TextView name_item;

    }
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyAdapter(Context context){
            this.mInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return maingoods.size();
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
                convertview=mInflater.inflate(R.layout.listview_item,null);
                holder.name_item=(TextView)convertview.findViewById(R.id.name_item);

                convertview.setTag(holder);
            }else {
                holder=(ViewHolder)convertview.getTag();
            }
            holder.name_item.setText((String)maingoods.get(position).get("name_item"));

            mainggoods_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        bundle.putString("item",maingoods.get(position).get("name_item").toString());
        Intent intent=new Intent(getActivity(), DetailedActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade,R.anim.hold);


    }
}
