package com.haishan.saleoa.OrdersSubFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haishan.saleoa.R;
import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Shipment;
import com.haishan.saleoa.tasks.uploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopcarActivity extends AppCompatActivity {
    public static List<String>  gooodIdList = new ArrayList<>();
    public static List<String>  goodNameList = new ArrayList<>();
    public static List<Float>  priceList = new ArrayList<>();
    public static List<Integer> tol = new ArrayList<>();

    public float PP ;
    public ListView listView ;
    Button  xiadan ;
    TextView kuang ,jiner;
    private float pp;
    List<Shipment> ship = new ArrayList<Shipment>();

   String sustomerName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);
        init();
        initEven();
    }

    private void initEven() {
        xiadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GsonBuilder gsonBuidler = new GsonBuilder();//使用GsonBuilder来创建Gson，可以设置时间转换格式。
                Gson gson = gsonBuidler.create();
                String str =  gson.toJson(ship)  ;
                String url = config.IP_url + "/SaleForAD/servlet/OrderServlet";
                String param =  "method=toCheckSu&userId="+config.user_Id+"&custorName="+sustomerName+"&json=" + str;
                uploadTask task = new uploadTask(ShopcarActivity.this);
                task.execute(url, param);


            }
        });
    }

    private void init() {
        this.listView = (ListView) findViewById(R.id.shopcar_list);
        ItemAdpater i = new ItemAdpater(ShopcarActivity.this, getData());
        listView.setAdapter(i);

        kuang = (TextView) findViewById(R.id.shopcar_bottom_totallist);
        jiner = (TextView) findViewById(R.id.totalPrice);
        xiadan = (Button) findViewById(R.id.shopcar_addorders);
        kuang.setText(String.valueOf(goodNameList.size()));
        for(int o=0; o<priceList.size();o++){
            tol.add(o, 1);//初始化为1
            this.pp += (priceList.get(o)*tol.get(o));

        }
        jiner.setText(String.valueOf(this.pp)+"元");
        for (int p=0; p<gooodIdList.size(); p++){
            Shipment shipment = new Shipment();
            shipment.setGoodId(gooodIdList.get(p));
            shipment.setAmount(1);//
            ship.add(shipment);

        }



    }
    private List<Map<String, Object>> getData(){
        List<Map<String, Object>> shipOfItem = new ArrayList<Map<String, Object>>();
        for (int i=0; i<goodNameList.size(); i++){
        HashMap<String, Object> map =   new HashMap<>();
            map.put("goodName",goodNameList.get(i));

            map.put("price",priceList.get(i));
            System.out.println(map);
            shipOfItem.add(map);
        }
   return shipOfItem;
    }
        class  ItemAdpater extends BaseAdapter {
            private LayoutInflater mInflater;
            private List<Map<String, Object>> goodlist;
            public ItemAdpater(Context context, List<Map<String, Object>> goodlist) {

                this.mInflater = LayoutInflater.from(context);
                this.goodlist = goodlist;
            }

            @Override
            public int getCount() {
                return this.goodlist.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertview, ViewGroup parentList) {
                ViewHolder holder=null;
                if (convertview==null){
                    holder=new ViewHolder();
                    convertview=mInflater.inflate(R.layout.orders_listew_shopcar,null);
                    holder.t1 = (TextView)convertview.findViewById(R.id.text3_item);
                    holder.t2 = (TextView)convertview.findViewById(R.id.text4_item) ;
                    holder.name_item=(TextView)convertview.findViewById(R.id.name_item);
                    holder.amount=(TextView)convertview.findViewById(R.id.text1_item);
                    holder.goodPrice=(TextView)convertview.findViewById(R.id.text_2_item);
                    holder.shanchu= (Button) convertview.findViewById(R.id.shopcar_delete);
                    holder.jia= (Button) convertview.findViewById(R.id.shopcar_jia);
                    holder.EditText= (EditText) convertview.findViewById(R.id.shopcar_number);
                    holder.jian= (Button) convertview.findViewById(R.id.shopcar_jian);

                 //   holder.shanchu= (Button) convertview.findViewById(R.id.shopcar_delete);
                    convertview.setTag(holder);
                }else {
                    holder=(ViewHolder)convertview.getTag();
                }
                holder.name_item.setText(goodlist.get(position).get("goodName").toString() );
                holder.amount.setText("1" );
                holder.goodPrice.setText(goodlist.get(position).get("price").toString());
                System.out.println(goodlist.get(position).get("price").toString());


                holder.shanchu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });



                return convertview;
            }
            public final class ViewHolder{
                public TextView t1;
                public TextView t2;
                public TextView name_item;
                public TextView amount;
                public TextView goodPrice;
                public Button jia;
                public Button jian;
                public EditText EditText;
                public Button shanchu;


            }
        }
}
