package com.haishan.saleoa.service;

/**
 * Created by Heisan on 2017/7/4.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于发送数据请求**/
public class ResService {
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
            out.write(params.toString().getBytes());
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            result = read(in);
        }

    catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String read(InputStream in) throws IOException {
        byte[] data;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[]buf = new byte[1024];
        int len = 0;
        while((len = in.read(buf))!=-1){
            bout.write(buf, 0, len);
        }
        data = bout.toByteArray();
        return new String(data,"UTF-8");
    }
}
