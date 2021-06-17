package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.newyorkerapp.R;
import com.example.newyorkerapp.model.data.ListOfWallsFromCatalog;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_front_page);


        //Very bad Practice need to find a better place to call it
        FireBaseDAOimpl.buildHashMapsForFetureNameAndPrice();
        ListOfWallsFromCatalog.buildListOfWallsFromCatalog();

        //TODO read up on this thing
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(FrontPage.this, CatalogActivity.class);
                startActivity(myIntent);
            }
        }, 3000);
    }
}