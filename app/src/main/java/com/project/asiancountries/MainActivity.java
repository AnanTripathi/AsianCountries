package com.project.asiancountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.asiancountries.retrofit.GetCountryService;
import com.project.asiancountries.retrofit.RetrofitClientInstance;
import com.project.asiancountries.room.CountriesDatabase;
import com.project.asiancountries.util.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements InternetBroadcastReceiver.ConnectivityChanged {
    private static final String TAG = "MainActivity";
    private CountryListAdapter myAdapter;
    private RecyclerView recyclerView;
    private CountriesDatabase countriesDatabase;
    private Button deleteDatabase;
    private TextView emptyTextView;
    private InternetBroadcastReceiver internetBroadcastReceiver = new InternetBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyTextView = findViewById(R.id.emptyView);
        deleteDatabase = findViewById(R.id.deleteSavedData);
        recyclerView = findViewById(R.id.recycler);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetBroadcastReceiver, intentFilter);
        //we initialize the database
        setDatabase();
        //we are intially filling the  data from database
        getDataFromDataBase();
        //them its possible for the database to be empty so we implement this
        fillDataIntoTheRecyclerView();
        deleteDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countriesDatabase.dao().deleteCompleteTable();
                recyclerView.removeAllViews();
                myAdapter = null;
                recyclerView.setAdapter(null);
                if (isConnectedToInternet()) {
                    emptyTextView.setText(getResources().getString(R.string.data_loading));
                } else
                    emptyTextView.setText(getResources().getString(R.string.network_error));
                fillDataIntoTheRecyclerView();
            }
        });
    }

    private void fillDataIntoTheRecyclerView() {
        if (isConnectedToInternet()) {
            if (myAdapter == null )
                emptyTextView.setVisibility(View.VISIBLE);
            else if(myAdapter.getItemCount() == 0)
                emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText(getResources().getString(R.string.data_loading));
            getDataOnline();
        } else {
            getDataFromDataBase();
        }
    }

    private void getDataFromDataBase() {
        List<Country> countries = countriesDatabase.dao().getCountries();
        if (countries.size() != 0) {
            emptyTextView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.VISIBLE);
        }
        generateDataList(countries);
    }

    private void getDataOnline() {
        GetCountryService service = RetrofitClientInstance.getRetrofitInstance().create(GetCountryService.class);
        Call<List<Country>> call = service.getAllCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> latestResponse = response.body();
                List<Country> savedResponse = countriesDatabase.dao().getCountries();
                for (int i = 0; i < latestResponse.size(); i++) {
                    if (!savedResponse.contains(latestResponse.get(i)))
                        countriesDatabase.dao().countryInsert(latestResponse.get(i));
                }
                emptyTextView.setVisibility(View.GONE);
                if (latestResponse.size() > savedResponse.size()) {
                    generateDataList(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                getDataFromDataBase();
            }
        });
    }

    private void generateDataList(List<Country> songList) {
        myAdapter = new CountryListAdapter(songList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void setDatabase() {
        countriesDatabase = Room.databaseBuilder(MainActivity.this, CountriesDatabase.class, "CountriesDb").allowMainThreadQueries().build();
    }

    @Override
    public void changeInNetworkStatus() {

        if (isConnectedToInternet()) {
            if (myAdapter == null)
                emptyTextView.setVisibility(View.VISIBLE);
            else if(myAdapter.getItemCount()==0)
                emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText(getResources().getString(R.string.data_loading));
            getDataOnline();
        }
        if (myAdapter == null && !isConnectedToInternet()) {
            emptyTextView.setText(getResources().getString(R.string.network_error));
            emptyTextView.setVisibility(View.VISIBLE);
        }
        else if(myAdapter.getItemCount()==0 && !isConnectedToInternet()){
            emptyTextView.setText(getResources().getString(R.string.network_error));
            emptyTextView.setVisibility(View.VISIBLE);
        }
    }


    private boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        return connected;
    }
}