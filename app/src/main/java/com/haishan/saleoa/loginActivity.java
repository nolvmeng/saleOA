package com.haishan.saleoa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.haishan.saleoa.config.config;
import com.haishan.saleoa.domain.Good;
import com.haishan.saleoa.tasks.GetDataTask;
import com.haishan.saleoa.tasks.LoginTask;

public class loginActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_id, et_password, url_login;
    CheckBox check_login,gaoji_login;
    private final static String filename="login";
    private final static String ID="id";
    private final static String PWD="password";

    public static String checkFlag ; //账号核验标识
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化界面控件
        initView();
        //添加监听响应事件
        addListener();


    }
    /**
     * 初始化所有控件
     */
    private void initView() {
        checkFlag = "fail";
        btn_login = (Button) findViewById(R.id.btn_login);
        gaoji_login = (CheckBox) findViewById(R.id.gaoji_login);
        et_id = (EditText) findViewById(R.id.login_id);
        et_password = (EditText) findViewById(R.id.login_pass);
        check_login = (CheckBox)findViewById(R.id.check_login);
        url_login = (EditText)findViewById(R.id.url_login);
    }
    /**
     * 添加按钮监听事件
     */
    private void addListener(){
        //登录按钮
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = et_id.getText().toString();
                String password = et_password.getText().toString();
                LoginTask loginTask = new LoginTask(loginActivity.this);
                loginTask.execute(id, password);
                config.user_Id = id;
                //是否记住密码
                if (check_login.isChecked()){
                    rememberMe(id,password);
                }
                else
                {
                    id=null;
                    password=null;
                    rememberMe(id,password);}
                System.out.println(checkFlag);


            }
        });

        //高级
        gaoji_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    url_login.setVisibility(View.VISIBLE);
                }else {
                    url_login.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 读取账号密码
     */
    @Override
    protected void onStart(){
        super.onStart();
        url_login.setVisibility(View.GONE);
        ifChecked();
    }
    /**
     * 读取账号密码方法
     */
    private void ifChecked(){
        SharedPreferences sp = getSharedPreferences(filename,MODE_PRIVATE);
        String id = sp.getString(ID,null);
        String password = sp.getString(PWD,null);
        if(id!=null&&password!=null){
            et_id.setText(id);
            et_password.setText(password);
            check_login.setChecked(true);
        }
    }
    /**
     * 保存账号密码
     */
    private void rememberMe(String id,String password){
        SharedPreferences sp = getSharedPreferences(filename,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(ID,id);
        editor.putString(PWD,password);
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
