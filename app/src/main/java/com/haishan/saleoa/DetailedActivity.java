package com.haishan.saleoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {
TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        test=(TextView)findViewById(R.id.test);
        test.setText(bundle.getString("item"));

    }
}
