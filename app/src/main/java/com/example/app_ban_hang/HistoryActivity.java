package com.example.app_ban_hang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhuhoa.puppystore.model.OrderInformation;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ArrayList<OrderInformation> orders;
    private RecyclerView rv_order;
    private HistoryAdapter myAdapter;

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rv_order = findViewById(R.id.rv_history);
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        myDatabaseHelper = new MyDatabaseHelper(this);
        orders = new ArrayList<OrderInformation>();
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {

                List<OrderInformation> dborders = myDatabaseHelper.getAllOrders();
                if(dborders.size() == 0){
                    Log.d("request!", "starting database");
                    //Toast.makeText(HistoryActivity.this, "Order History Empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    for(OrderInformation order: dborders){
                        orders.add(order);
                    }
                    myAdapter.notifyDataSetChanged();
                }

            }
        });
        myAdapter = new HistoryAdapter(orders, this);
        rv_order.setAdapter(myAdapter);
    }
}