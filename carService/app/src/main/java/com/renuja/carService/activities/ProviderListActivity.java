package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.Database.DBHelperOwner;
import com.renuja.carService.R;
import com.renuja.carService.adapters.ProviderAdapter;
import com.renuja.carService.models.Owner;

import java.util.List;

public class ProviderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelperOwner dbOwner;
    ProviderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);


        recyclerView = findViewById(R.id.rvProviders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbOwner = new DBHelperOwner(this);
        List<Owner> list = dbOwner.getAllBusinesses();

        adapter = new ProviderAdapter(list, id);
        recyclerView.setAdapter(adapter);
    }
}