package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newyorkerapp.R;
import com.example.newyorkerapp.viewModel.MainActivityViewModel;

public class StartPage extends AppCompatActivity {
    Button contactButton, catalogButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start_page);
        catalogButton = findViewById(R.id.toCatalog);
        contactButton = findViewById(R.id.toContact);





        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartPage.this, ContactActivity.class);
                startActivity(myIntent);
            }
        });

        catalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartPage.this, CatalogActivity.class);
                startActivity(myIntent);
            }
        });

    }
}