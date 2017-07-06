package com.haishan.saleoa.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.haishan.saleoa.Adapter.GoodAdapter;
import com.haishan.saleoa.Adapter.OrderAdapter;
import com.haishan.saleoa.DetailedActivity;
import com.haishan.saleoa.OrdersSubFragment.OrderDetailedActivity;
import com.haishan.saleoa.R;
import com.haishan.saleoa.Tools.Json2List;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.domain.GoodItem;
import com.haishan.saleoa.domain.Order;
import com.haishan.saleoa.domain.OrderItem;
import com.haishan.saleoa.service.ResService;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heisan on 2017/7/4.
 * 获取数据的异步任务
 */

public class GetDataTask<T> extends AsyncTask<String, Integer, String> {
    private Context context;
    private Class<T> ct;
    private ListView listView;

    //构造方法
    public  GetDataTask(Context context, Class<T> ct , ListView listView){
        this.context = context;
        this.ct = ct;
        this.listView = listView;
    }

    @Override
    protected String doInBackground(String[] params) {
        String url = params[0];// "http://10.0.2.12:8080/SaleForAD/servlet/GoodServlet";
        String param = params[1];//  "method=AllGoods&category=meat&pageNO=1";

        return   ResService.sendPost(url, param);
    }

    @Override
    protected void onPostExecute(String json) {

            final List<T> listp = Json2List.forlistT(json, this.ct );//从json获取对象列表
            System.out.println(ct.getClass() );
            final List<Map<String, Object>> obList = new ArrayList<Map<String,Object>>();//用于存放用于适配器的列表


        if((Good.class).equals(ct)) {//如果请求的是装配Good的适配器
            if(listp != null) {//将对象列表转成Map列表
                for (T g : listp) {
                    System.out.println(g.toString());
                    Map<String, Object> map = null;
                    try {
                        map = objectToMap(g);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    obList.add(map);
                    System.out.println(map);
                }
            }
            System.out.println(obList.size());
            GoodAdapter goodAdapter = new GoodAdapter(this.context, obList);
            this.listView.setAdapter(goodAdapter);

            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Intent intent = new Intent(GetDataTask.this.context, DetailedActivity.class);
                    if (obList.get(position) == null) System.out.println("map为空");
                    intent.putExtra("good", (Serializable) obList.get(position));
                    GetDataTask.this.context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);//以淡入模式进入activity
                }
            });
        }

        if((OrderItem.class).equals(ct)){//如果请求的是装配GoodItem的适配器
            System.out.println("请求的是装配GoodItem的适配器");
            OrderAdapter orderAdapter = new OrderAdapter(this.context, (List<OrderItem>) listp);
            this.listView.setAdapter(orderAdapter);
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Intent intent = new Intent(GetDataTask.this.context, OrderDetailedActivity.class);
                    if (listp.get(position) == null) System.out.println("map为空");
                    OrderItem orderItem = (OrderItem) listp.get(position);


                    try {
                        //intent.putExtra("good", (Serializable) objectToMap(orderItem.getGoodList()));//将选中的订单项获取其货品列表并转成Map
                     //   intent.putExtra("shipment", (Serializable)  orderItem.getShipmentList());//将选中的订单项获取其货品列表并转成Map
                      /*  intent.putExtra("good", (Serializable) objectToMap(orderItem.getGoodList()));//将选中的订单项获取其货品列表并转成Map*/
                        intent.putExtra("listobj", (Serializable)toList( orderItem.getGoodList()));


                        GetDataTask.this.context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.fade, R.anim.hold);//以淡入模式进入activity
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        }

    /**
     * 将对象转成List<map>
     *  **/
    public   List< Map<String, Object>> toList(List<Good> obi){
        List<Map<String, Object>> goodOfItem = new ArrayList<Map<String, Object>>();
        if(obi == null){
            return null;
        }

        for (Good good : obi) {

            Map<String, Object> map = null;
            try {
                map = objectToMap(good);
            } catch (Exception e) {
                e.printStackTrace();
            }
            goodOfItem.add(map);
        }
        return goodOfItem;
    }

     /**
     * 将对象转成Map
     * **/
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }



}
