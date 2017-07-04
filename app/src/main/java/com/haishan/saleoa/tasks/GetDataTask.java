package com.haishan.saleoa.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.haishan.saleoa.Tools.Json2List;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.service.ResService;

import java.lang.reflect.Member;
import java.util.List;

/**
 * Created by Heisan on 2017/7/4.
 */

public class GetDataTask<T> extends AsyncTask<String, Integer, String> {
    private Context context;
    private Class<T> ct;

    //构造方法
    public  GetDataTask(Context context, Class<T> ct ){
        this.context = context;
        this.ct = ct;
    }

    @Override
    protected String doInBackground(String[] params) {
        String url = params[0];// "http://10.0.2.12:8080/SaleForAD/servlet/GoodServlet";
        String param = params[1];//  "method=AllGoods&category=meat&pageNO=1";

        return   ResService.sendPost(url, param);
    }

    @Override
    protected void onPostExecute(String json) {
        /*super.onPostExecute(json);*/

       // Class<Good> clazz = Good.class;
        //Good b = new Good();
        //利用类加载加载泛型的具体类型

        //    Class<T> classT = (Class<T>) Class.forName(ct.getClass().getName());
            List<T> listp = Json2List.forlistT(json, this.ct );
            System.out.println(ct.getClass() );
            //System.out.println(clazz.getClass() );
            for (T g : listp) {
                System.out.println(g.toString());
            }




    }
}
