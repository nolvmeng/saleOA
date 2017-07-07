package com.haishan.saleoa.OrdersSubFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.domain.OrderItem;
import com.haishan.saleoa.tasks.GetDataTask;

import static com.haishan.saleoa.config.config.user_Id;

public class OrderSearchActivity extends AppCompatActivity {
    ListView search_lsw;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);
        search_lsw=(ListView)findViewById(R.id.search_lsw);
        Intent intent=this.getIntent();
        search=intent.getStringExtra("search");
        updata();
    }

    //更新数据方法
    private void updata(){

        String url = config.IP_url + "/SaleForAD/servlet/OrderServlet";
        String param =  "method=getOrder&userId="+user_Id+"&search="+search;
        Class<OrderItem> clazz = OrderItem.class;
        GetDataTask<Good> getDataTask = new GetDataTask (OrderSearchActivity.this,clazz, this.search_lsw);
        getDataTask.execute(url, param);
    }
}
