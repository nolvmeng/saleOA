package com.haishan.saleoa.GoodsSubFragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;

public class SearchActivity extends AppCompatActivity {
    private ListView search_lsw;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_lsw=(ListView)findViewById(R.id.search_lsw);
        Intent intent=this.getIntent();
        search=intent.getStringExtra("search");
        updata();


    }
    /**
     * 更新数据**/
    private void updata(){
        String url = config.IP_url + "/SaleForAD/servlet/GoodServlet";
        String param =  "method=AllGoods&search="+search;
        System.out.println(search);
        Class<Good> clazz = Good.class;
        GetDataTask<Good> getDataTask = new GetDataTask (SearchActivity.this,clazz, this.search_lsw);
        getDataTask.execute(url, param);
    }
}
