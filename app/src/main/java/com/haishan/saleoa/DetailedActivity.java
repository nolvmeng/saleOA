package com.haishan.saleoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.haishan.saleoa.config.config;
import com.haishan.saleoa.tasks.uploadTask;

import java.util.HashMap;

import static com.haishan.saleoa.R.id.category_detailed;

public class DetailedActivity extends AppCompatActivity {

    TextView id,name,reserve,price,date,category,id_detailed,date_detailed, category_detailed_tsw;
    EditText name_detailed,reserve_detailed,price_detailed;
    Button sure,change,back,detele;
    Spinner spinner;

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
        id_detailed.setText(map.get("goodId").toString());
        name_detailed.setText(map.get("goodName").toString());
        reserve_detailed.setText(map.get("reserve").toString());
        price_detailed.setText(map.get("goodPrice").toString());
        date_detailed.setText(map.get("productDate").toString());
        category_detailed_tsw.setText(map.get("category").toString());


        String[] category={"肉类","蔬菜类","主食","其它"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    //种类名中文转英文
    private String Cn2En(String ch){
        String[] category={"肉类","蔬菜类","主食","其它"};
        String[] cate = {"meat","vegetable","main","other"};
        int i = 0;
        for(int j =0;j<3;j++){
            if(category[j].equals(ch)){
                i = j ;
                break;
            }

        }
        return cate[i];
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

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("method=addGood&slCategory=");
        stringBuilder.append(Cn2En(spinner.getSelectedItem().toString()));//种类参数
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
        id_detailed=(TextView) findViewById(R.id.id_detailed);
        name_detailed=(EditText) findViewById(R.id.name_detailed);
        reserve_detailed=(EditText) findViewById(R.id.reserve_detailed);
        price_detailed=(EditText) findViewById(R.id.price_detailed);
        date_detailed=(TextView) findViewById(R.id.date_detailed);
        category_detailed_tsw=(TextView)findViewById(R.id.category_detailed_tsw);
        spinner=(Spinner) findViewById(category_detailed);

        sure=(Button)findViewById(R.id.goodsDetailed_sure);
        change=(Button)findViewById(R.id.goodsDetailed_change);
        detele=(Button)findViewById(R.id.goodsDetailed_delete);
        back=(Button)findViewById(R.id.goodsDetailed_back);
    }

    public void initEvents(){
        spinner.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sure.setVisibility(View.INVISIBLE);
                change.setVisibility(View.VISIBLE);
                name_detailed.setFocusable(false);
                name_detailed.setFocusableInTouchMode(false);
                reserve_detailed.setFocusable(false);
                reserve_detailed.setFocusableInTouchMode(false);
                price_detailed.setFocusable(false);
                price_detailed.setFocusableInTouchMode(false);
                spinner.setVisibility(View.INVISIBLE);
                category_detailed_tsw.setVisibility(View.VISIBLE);

                //修改货品
                changeGood();
            }
        });
        name_detailed.setFocusable(false);
        name_detailed.setFocusableInTouchMode(false);
        reserve_detailed.setFocusable(false);
        reserve_detailed.setFocusableInTouchMode(false);
        price_detailed.setFocusable(false);
        price_detailed.setFocusableInTouchMode(false);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change.setVisibility(View.INVISIBLE);
                sure.setVisibility(View.VISIBLE);
                name_detailed.setFocusableInTouchMode(true);
                name_detailed.setFocusable(true);
                name_detailed.requestFocus();
                reserve_detailed.setFocusableInTouchMode(true);
                reserve_detailed.setFocusable(true);
                reserve_detailed.requestFocus();
                price_detailed.setFocusableInTouchMode(true);
                price_detailed.setFocusable(true);
                price_detailed.requestFocus();
                spinner.setVisibility(View.VISIBLE);
                category_detailed_tsw.setVisibility(View.INVISIBLE);
            }
        });
        detele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGood();
            }
        });
    }
}
