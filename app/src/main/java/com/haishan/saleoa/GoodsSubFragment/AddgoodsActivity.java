package com.haishan.saleoa.GoodsSubFragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.tasks.uploadTask;

public class AddgoodsActivity extends AppCompatActivity {
    TextView id,name,reserve,price,date,category;
    EditText id_detailed,name_detailed,reserve_detailed,price_detailed;
    Spinner category_detailed;
    Button sure,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoods);
        init();
        initEvents();//初始化监听器


    }

    //添加货品方法
    private void  addGood(){
        String url = config.IP_url + "/SaleForAD/servlet/GoodServlet";
       // String param = "method=addGood&slCategory=meat&goodName="+"可乐"+"&goodPrice=18&goodNum=10";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("method=addGood&slCategory=");
        stringBuilder.append(Cn2En(category_detailed.getSelectedItem().toString()));
        stringBuilder.append("&goodName=");
        stringBuilder.append(name_detailed.getText().toString());
        stringBuilder.append("&goodPrice=");
        stringBuilder.append(price_detailed.getText().toString());
        stringBuilder.append("&goodNum=");
        stringBuilder.append(reserve_detailed.getText().toString());
        String params = stringBuilder.toString();
        System.out.println(params);

        uploadTask task = new uploadTask(AddgoodsActivity.this);
        task.execute(url, params);


    }






    public void init(){
      //  id=(TextView)findViewById(R.id.id);
        name=(TextView)findViewById(R.id.name);
        reserve=(TextView)findViewById(R.id.reserve);
        price=(TextView)findViewById(R.id.price);
       // date=(TextView)findViewById(R.id.date);
        category=(TextView)findViewById(R.id.category);
       // id_detailed=(EditText)findViewById(R.id.id_detailed);
        name_detailed=(EditText)findViewById(R.id.name_detailed);
        reserve_detailed=(EditText)findViewById(R.id.reserve_detailed);
        price_detailed=(EditText)findViewById(R.id.price_detailed);
       // date_detailed=(EditText)findViewById(R.id.date_detailed);
        category_detailed=(Spinner) findViewById(R.id.category_detailed);

        sure=(Button)findViewById(R.id.goodsDetailed_sure);
        back=(Button)findViewById(R.id.goodsDetailed_back);

        String[] category={"肉类","蔬菜类","主食","其它"};
    //    String[] cate = {"","","",""};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_detailed.setAdapter(adapter);
    }

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


    public void initEvents(){
        //注册监听器
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGood();
            }
        });

    }
}
