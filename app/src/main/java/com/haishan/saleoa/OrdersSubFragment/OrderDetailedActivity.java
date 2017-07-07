package com.haishan.saleoa.OrdersSubFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.haishan.saleoa.Adapter.GoodAdapter;
import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.tasks.uploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailedActivity extends AppCompatActivity {

    ListView orders_detailed_list;
    TextView order_Id,orderId,kehu,user_Id,userId,customer_adrress,total_Price,totalPrice,customer,adrress,kehuphone,phone ,riqi,orderDate;
    Button ordersDetailed_delete,ordersDetailed_back,boda;
    String customerName,customerAdd,customerNum, tPrice, oId, date;//订单各信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        init();
        deal();
        initInfo();
        invents();
    }

    private void deal(){
        Intent intent = this.getIntent();
        // HashMap<String, Object> map =  (HashMap<String, Object>) intent.getSerializableExtra("goodMap");

        List<Map<String, Object>> goodlist  = ( List<Map<String, Object>>)intent.getSerializableExtra("goodMap");
        List<Map<String, Object>> shiplist  = ( List<Map<String, Object>>)intent.getSerializableExtra("shipMap");
        Map<String, Object> infolist  = ( Map<String, Object>)intent.getSerializableExtra("infoMap");

        List<Map<String, Object>> showGood  = new ArrayList<Map<String, Object>>();

        if(goodlist!=null){
            for (Map m : goodlist){
                System.out.println(m.get("goodId")+""+m.get("goodName"));
            }
        }
        if(shiplist!=null){
            for ( int i = 0;i<shiplist.size();i++){
                Map m = shiplist.get(i);
                System.out.println(m.get("prive")+""+m.get("amount"));
                HashMap<String, Object> map =   new HashMap<>();
                map.put("goodName", goodlist.get(i).get("goodName"));
                map.put("reserve", "数量："+ m.get("amount"));
                map.put("goodPrice", "单价："+ m.get("prive"));
                showGood.add(map);
            }

            GoodAdapter adapter = new GoodAdapter(OrderDetailedActivity.this, showGood);
            orders_detailed_list.setAdapter(adapter);

        }


        System.out.println(infolist.size());
        customerName = infolist.get("customerName").toString();//客户名
        customerAdd =  infolist.get("customerAdd" ).toString();//客户地址
        customerNum =   infolist.get("customerNum" ).toString();//客户电话
        tPrice =  infolist.get("tatalPrice" ).toString();//订单总额
        oId = infolist.get("orderId" ).toString();//订单编号
        date = infolist.get("gate").toString();//订单时间, 下版本需加入
        //  user = infolist.get("uesrId" ).toString();//订单操作员编号


    }

    private void initInfo(){
        orderId.setText(oId);
        phone.setText(customerNum);
        totalPrice.setText(tPrice + "元");
        customer.setText(customerName);
        adrress.setText(customerAdd);
        orderDate.setText(date);
    }
    public void init(){
        order_Id=(TextView)findViewById(R.id.order_Id);
        orderId=(TextView)findViewById(R.id.orderId);
        kehu=(TextView)findViewById(R.id.kehu);
        //  user_Id=(TextView)findViewById(R.id.user_Id);
        //userId=(TextView)findViewById(R.id.userId);
        riqi=(TextView)findViewById(R.id.riqi);
        orderDate=(TextView)findViewById(R.id.orderDate);


        customer_adrress=(TextView)findViewById(R.id.customer_adrress);
        total_Price=(TextView)findViewById(R.id.total_Price);
        totalPrice=(TextView)findViewById(R.id.totalPrice);
        customer=(TextView) findViewById(R.id.customer);
        adrress = (TextView) findViewById(R.id.adrress);
        kehuphone = (TextView) findViewById(R.id.kehuphone);
        phone = (TextView) findViewById(R.id.phone);
        ordersDetailed_delete=(Button)findViewById(R.id.ordersDetailed_delete);
        ordersDetailed_back=(Button)findViewById(R.id.ordersDetailed_back);
        boda=(Button)findViewById(R.id.boda);

        orders_detailed_list=(ListView)findViewById(R.id.orders_detailed_list);
    }
    //删除订单方法
    private void  deleteGood(){
        String url = config.IP_url + "/SaleForAD/servlet/OrderServlet";
        String param = "method=delOrder&id=" + orderId.getText().toString();

        uploadTask task = new uploadTask(OrderDetailedActivity.this);
        task.execute(url, param);


    }

    public void invents(){
        boda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+customerNum));
                startActivity(intent);
            }
        });
        ordersDetailed_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ordersDetailed_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGood();
            }
        });
    }


}
