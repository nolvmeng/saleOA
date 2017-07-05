package com.haishan.saleoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haishan.saleoa.domain.Good;

import java.util.HashMap;
import java.util.Map;

public class DetailedActivity extends AppCompatActivity {
    TextView id,name,reserve,price,date,category,id_detailed,name_detailed,reserve_detailed,price_detailed,date_detailed,category_detailed;
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

        if (map==null) System.out.println("map为空");

        name_detailed.setText(map.get("goodName").toString());
        reserve_detailed.setText(map.get("reserve").toString());
//        price_detailed.setText(good.getGoodPrice());

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
