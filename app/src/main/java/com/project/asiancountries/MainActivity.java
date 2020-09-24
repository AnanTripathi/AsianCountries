package com.project.asiancountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CountryListAdapter myAdapter;
    private RecyclerView recyclerView;
    int page=1,limit=3;
    private NestedScrollView nestedScrollView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nestedScrollView=findViewById(R.id.nestedScrollView);
        getDataOnline(page,limit);
        progressBar=findViewById(R.id.progress_bar);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getDataOnline(page,limit);
                }
            }
        });
    }

    private void getDataOnline(int page,int limit) {
        GetCountryService service=RetrofitClientInstance.getRetrofitInstance().create(GetCountryService.class);
        Call<List<Country>> call=service.getAllCountries(page,limit);
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                generateDataList(response.body());
                Log.i(TAG, "onResponse: "+response.body().toString());
                Toast.makeText(MainActivity.this, "all is well", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<Country> songList) {
        recyclerView = findViewById(R.id.recycler);
        myAdapter = new CountryListAdapter(songList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

    }
}