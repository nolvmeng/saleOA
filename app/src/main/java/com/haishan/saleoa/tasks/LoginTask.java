package com.haishan.saleoa.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.haishan.saleoa.MainActivity;
import com.haishan.saleoa.loginActivity;


import java.util.Map;


/**
 * Created by Heisan on 2017/6/21.
 */

public class LoginTask extends AsyncTask<String, Integer, String> {
    private Context context;

    //构造方法
    public  LoginTask(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(String[] params) {
        String param = "method=login" + "&id=" + params[0] + "&password=" + params[1];
        return null;
                //postService.sendPost("http://10.0.2.13:8080/SaleForAD/servlet/UserServlet", param);
    }


    @Override
    protected void onPostExecute(String s) {
      //  Toast.makeText(this.context, s, Toast.LENGTH_LONG).show();
        if(/*s.equals("success")*/true) {
            loginActivity.checkFlag = "success";
            Intent intent = new Intent(this.context, MainActivity.class);
            this.context.startActivity(intent);
            ((Activity) context).finish();
        }
    }


}

