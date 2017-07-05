package com.haishan.saleoa.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.haishan.saleoa.MainActivity;

import com.haishan.saleoa.service.ResService;

/**
 * Created by Heisan on 2017/7/5.
 * 上传数据的异步任务方法
 * */
public class uploadTask extends AsyncTask<String, Integer, String> {
    private Context context;

    //构造方法
    public  uploadTask(Context context){
        this.context = context;
    }




    @Override
    protected String doInBackground(String[] params) {
        String url = params[0];
        String param = params[1];

        return   ResService.sendPost(url, param);

    }

    @Override
    protected void onPostExecute(String s) {

        if(("success").equals(s)) {
            Toast.makeText(this.context, s, Toast.LENGTH_LONG).show();
            ((Activity) this.context).finish();
        }
            }
}
