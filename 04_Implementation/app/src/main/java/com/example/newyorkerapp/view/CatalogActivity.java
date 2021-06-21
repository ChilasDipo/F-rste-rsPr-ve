package com.example.newyorkerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newyorkerapp.R;

public class CatalogActivity extends AppCompatActivity {

    Button buttonKontaktKontaktOs, buttonKontaktMeasure;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_catalog);
            buttonKontaktKontaktOs = findViewById(R.id.button11);
            buttonKontaktMeasure = findViewById(R.id.button9);

            imageView1 = findViewById(R.id.imageView1);
            imageView2 = findViewById(R.id.imageView2);
            imageView3 = findViewById(R.id.imageView3);
            imageView4 = findViewById(R.id.imageView4);
            imageView5 = findViewById(R.id.imageView5);
            imageView6 = findViewById(R.id.imageView6);
            imageView7 = findViewById(R.id.imageView7);

            initializeOnClickListeners();
        }
    void  initializeOnClickListeners(){
            //Opsætter vores knap til at sende brugeren til Kontakt os siden
        buttonKontaktKontaktOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CatalogActivity.this, ContactActivity.class);
                startActivity(myIntent);
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opsætter vores intent til at sende brugeren til MainActivity når de har valgt en væg
                Intent myIntent = new Intent(CatalogActivity.this, MainActivity.class);
                /* Vi bruger en switch til at give intenten en værdi afhængelig af hvilket imageview som er blevet
                kliket, v.getId giver switchen Idet for det obejkt som er blevet klikket */
                switch (v.getId()) {
                    case R.id.imageView1:
                        myIntent.putExtra("imageID", 0);
                        break;
                    case R.id.imageView2:
                        myIntent.putExtra("imageID", 1);
                        break;
                    case R.id.imageView3:
                        myIntent.putExtra("imageID", 2);
                        break;
                    case R.id.imageView4:
                        myIntent.putExtra("imageID", 3);
                        break;
                    case R.id.imageView5:
                        myIntent.putExtra("imageID", 4);
                        break;
                    case R.id.imageView6:
                        myIntent.putExtra("imageID", 5);
                        break;
                    case R.id.imageView7:
                        myIntent.putExtra("imageID", 6);
                        break;
                }
                startActivity(myIntent);
            }
        };

        //Alle Imageviews har den sammen onclickListener
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        imageView5.setOnClickListener(onClickListener);
        imageView6.setOnClickListener(onClickListener);
        imageView7.setOnClickListener(onClickListener);
    }

}
