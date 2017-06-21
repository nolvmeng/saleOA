package com.haishan.saleoa.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        return this.sendPost("http://10.0.2.10:8080/SaleForAD/servlet/UserServlet", param);
    }

    public String sendPost(String url, String params) {
        String result = "";
        try {
            URL realurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realurl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result = line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(this.context, s, Toast.LENGTH_LONG).show();
    }
}

