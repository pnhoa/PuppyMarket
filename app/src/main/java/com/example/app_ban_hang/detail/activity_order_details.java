package com.example.app_ban_hang.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_ban_hang.R;


public class activity_order_details extends AppCompatActivity {

    private TextView  count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mapping();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
     //   int Dog_count=bundle.getInt("Dog_count");
        if(bundle!=null)
            retrieveIntent(bundle);

    }
    void mapping(){
        count=findViewById(R.id.no_of_items);

    }
    void retrieveIntent(Bundle bundle){
        int no_of_items=bundle.getInt("Dog_count");
        count.setText(""+no_of_items);

    }
}
