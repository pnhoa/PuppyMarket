package com.example.app_ban_hang;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.app_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



//import dogFragment
import com.example.app_ban_hang.R;
import com.example.app_ban_hang.viewmodel.DogApiService;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import com.example.app_ban_hang.model.DogClass;
import com.example.app_ban_hang.viewmodel.DogApiService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ListView listView,lv_item;
    ArrayAdapter<String> adapter;
    TextView testFragment;
    Toolbar toolbar;
    SearchView sv;
    DrawerLayout dr_layout;
    NavigationView navigationView;
    Menu menu;
    String tempt,key,way;
    ImageView search_volume;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        actionToolBar();
       fragmentDog();
    }
    private void fragmentDog(){
        testFragment.setText("CALL FRAGMENT");
         DogApiService apiService;
        apiService= new DogApiService();


        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogClass>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogClass> dogBreeds) {
                        Log.d("DEBUG1","Success");

                        for(DogClass dog: dogBreeds){
                            Log.d("DEBUG1", dog.getName());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });




    }
    private void mapping(){
        testFragment=findViewById(R.id.testMainFragment);
        toolbar = findViewById(R.id.toolbar);
        dr_layout=findViewById(R.id.drawer_layout);}

    private void actionToolBar(){
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.home_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr_layout.openDrawer(Gravity.START);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}