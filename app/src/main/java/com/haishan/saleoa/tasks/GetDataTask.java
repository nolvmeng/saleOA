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

public class GetDataTask extends AsyncTask<String, Integer, String> {
    private Context context;

    //构造方法
    public  GetDataTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String[] params) {
        String url = "http://192.168.30.1:8080/SaleForAD/servlet/GoodServlet";
        String param = "method=AllGoods&category=meat&pageNO=1";

        return   ResService.sendPost(url, param);
    }

    @Override
    protected void onPostExecute(String json) {
        /*super.onPostExecute(json);*/

        List<Good> listp = Json2List.forlistT(json, Good.class);
    /*    for (Good g : listp) {
            System.out.println(g.toString());
        }*/

    }
}
