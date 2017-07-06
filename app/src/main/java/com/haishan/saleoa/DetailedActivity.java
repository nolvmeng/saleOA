package com.haishan.saleoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haishan.saleoa.config.config;
import com.haishan.saleoa.tasks.uploadTask;

import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    TextView id,name,reserve,price,date,category, id_detailed,name_detailed,reserve_detailed,price_detailed,date_detailed,category_detailed;
    /*EditText ;*/
    Button sure,change,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        init();
        initEvents();
        Intent intent = this.getIntent();
        HashMap<String, Object> map =  (HashMap<String, Object>) intent.getSerializableExtra("good");
     //   map =   intent.getSerializableExtra("good");

        if (map == null) System.out.println("map为空");

        name_detailed.setText(map.get("goodName").toString());
        reserve_detailed.setText(map.get("reserve").toString());
//        price_detailed.setText(good.getGoodPrice());

    }

    //删除货品方法
    private void  deleteGood(){
         String url = config.IP_url + "/SaleForAD/servlet/GoodServlet";
         String param = "method=delGood&id=" + id_detailed.getText().toString();

         uploadTask task = new uploadTask(DetailedActivity.this);
         task.execute(url, param);


    }
    //修改货品方法
    private void  changeGood(){
        String url = config.IP_url + "/SaleForAD/servlet/GoodServlet";
        // String param = "method=addGood&slCategory=meat&goodName="+"可乐"+"&goodPrice=18&goodNum=10";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("method=addGood&slCategory=");
        stringBuilder.append(category_detailed.getText().toString());
        stringBuilder.append("&goodName=");
        stringBuilder.append(name_detailed.getText().toString());
        stringBuilder.append("&goodPrice=");
        stringBuilder.append(price_detailed.getText().toString());
        stringBuilder.append("&goodNum=");
        stringBuilder.append(reserve_detailed.getText().toString());
        String params = stringBuilder.toString();
        System.out.println(params);

        uploadTask task = new uploadTask(DetailedActivity.this);
        task.execute(url, params);


    }


    public void init(){
        id=(TextView)findViewById(R.id.id);
        name=(TextView)findViewById(R.id.name);
        reserve=(TextView)findViewById(R.id.reserve);
        price=(TextView)findViewById(R.id.price);
        date=(TextView)findViewById(R.id.date);
        category=(TextView)findViewById(R.id.category);
        id_detailed=(TextView)findViewById(R.id.id_detailed);
        name_detailed=(TextView)findViewById(R.id.name_detailed);
        reserve_detailed=(TextView)findViewById(R.id.reserve_detailed);
        price_detailed=(TextView)findViewById(R.id.price_detailed);
        date_detailed=(TextView)findViewById(R.id.date_detailed);
        category_detailed=(TextView)findViewById(R.id.category_detailed);

        sure=(Button)findViewById(R.id.goodsDetailed_sure);
        change=(Button)findViewById(R.id.goodsDetailed_change);
        back=(Button)findViewById(R.id.goodsDetailed_back);
    }

    public void initEvents(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
