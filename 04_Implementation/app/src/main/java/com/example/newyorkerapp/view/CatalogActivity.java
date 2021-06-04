package com.example.newyorkerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newyorkerapp.R;

public class CatalogActivity extends AppCompatActivity {

    Button buttonKontaktKontaktOs, buttonKontaktMeasure;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_catalog);
            buttonKontaktKontaktOs = findViewById(R.id.button11);
            buttonKontaktMeasure = findViewById(R.id.button9);

            buttonKontaktKontaktOs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(CatalogActivity.this, ContactActivity.class);
                    startActivity(myIntent);

                }
            });
            buttonKontaktMeasure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(CatalogActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }
            });
    }

}
