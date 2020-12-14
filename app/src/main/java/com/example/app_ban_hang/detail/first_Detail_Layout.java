package com.example.app_ban_hang.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app_ban_hang.model.DogClass;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_ban_hang.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class first_Detail_Layout extends AppCompatActivity {
    private static ArrayList<DogClass> dogClass;
    private Button bt;
    private ImageView img;
    private TextView productName;
    private TextView productPrice,lifeSpan,origin,temperament,bred_for,breed_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product);
        bt = findViewById(R.id.buy_now);
        mapping();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int Dog_count=bundle.getInt("Dog_count");
        if(bundle!=null)
        retrieveIntent(bundle);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), activity_order_details.class);
                Bundle bundle2=new Bundle();
                bundle2.putInt("Dog_count",Dog_count);
                intent2.putExtras(bundle2);
                                startActivity(intent2);
            }
        });
    }
    public void retrieveIntent(Bundle bundle){
        String url=bundle.getString("url");


        Picasso.get().load(url).placeholder(R.drawable.progress_animation).
                resize(180, 180).centerCrop().into(img);
        productName.setText(bundle.getString("name"));
        lifeSpan.setText(bundle.getString("life"));
       origin.setText(bundle.getString("origin"));
        bred_for.setText(bundle.getString("bred_for"));
        breed_group.setText(bundle.getString("breed_group"));
        temperament.setText((bundle.getString("temperament")));
        int price=bundle.getInt("productprice");
        productPrice.setText("$"+price);







    }
    public void mapping(){
        lifeSpan=findViewById(R.id.lifeSpan);
        origin=findViewById(R.id.origin);
        temperament=findViewById(R.id.temperament);
        bred_for=findViewById(R.id.bred_for);
        breed_group=findViewById(R.id.breed_group);
        productName=findViewById(R.id.productname);
        productPrice=findViewById(R.id.productprice);
        img =findViewById(R.id.img_dog_individual);

    }
}
