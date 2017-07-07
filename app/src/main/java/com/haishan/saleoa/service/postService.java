package com.haishan.saleoa.service;

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

public class postService {


    /**
     * dopost方法
     * params：String , String params
     * */
        public static  String sendPost(String url, String params) {
        String result = "";
        try {
            URL realurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realurl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(params.getBytes());
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





}
