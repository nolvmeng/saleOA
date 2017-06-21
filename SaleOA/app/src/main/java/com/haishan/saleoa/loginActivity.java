package com.haishan.saleoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.haishan.saleoa.R;
import com.haishan.saleoa.tasks.LoginTask;

public class loginActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_id, et_password;

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
        btn_login = (Button) findViewById(R.id.btn_login);
        et_id = (EditText) findViewById(R.id.login_id);
        et_password = (EditText) findViewById(R.id.login_pass);
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
            }
        });

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
