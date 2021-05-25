package com.example.newyorkerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newyorkerapp.model.data.Wall;
import com.example.newyorkerapp.model.data.Wallimpl;

public class MainActivity extends AppCompatActivity {
    EditText brede , hight;
    Button send;
    TextView price;
    Wallimpl wall = new Wallimpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brede = findViewById(R.id.brede);
        hight = findViewById(R.id.højde);
         price = (TextView) findViewById(R.id.price);





    }

    public void send(View view) {
        wall.setHeight(Integer.parseInt((String.valueOf(hight.getText()))));
        wall.setLength(Integer.parseInt(String.valueOf(hight.getText())));

        System.out.println(wall.getPriceOfWall());
        price.setText(String.valueOf(wall.getPriceOfWall()));

    }
}