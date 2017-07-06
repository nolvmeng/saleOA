package com.haishan.saleoa.OrdersSubFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.domain.Shipment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailedActivity extends AppCompatActivity {
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        textView = (TextView) findViewById(R.id.zhishi);
        deal();
    }

    private void deal(){
        Intent intent = this.getIntent();
        HashMap<String, Object> map =  (HashMap<String, Object>) intent.getSerializableExtra("good");
        List<Map<String, Object>> goodlist  = ( List<Map<String, Object>>)intent.getSerializableExtra("listobj");
       if(goodlist!=null){
           for (Map m : goodlist)
           System.out.println(m.get("goodId"));
       }
    }
}
