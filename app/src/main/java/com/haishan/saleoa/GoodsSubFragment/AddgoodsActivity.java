package com.haishan.saleoa.GoodsSubFragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haishan.saleoa.R;

public class AddgoodsActivity extends AppCompatActivity {
    TextView id,name,reserve,price,date,category,id_detailed,name_detailed,reserve_detailed,price_detailed,date_detailed,category_detailed;
    Button sure,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoods);
        init();
        initEvents();
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
