package com.example.newyorkerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newyorkerapp.model.data.Wall;
import com.example.newyorkerapp.model.data.Wallimpl;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    EditText brede , hight;
    TextView price, width, height, fag, glas;
    Wallimpl wall = new Wallimpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brede = findViewById(R.id.brede);
        hight = findViewById(R.id.højde);
        price = (TextView) findViewById(R.id.price);
        width = (TextView) findViewById(R.id.widthOfGlas);
        height = (TextView) findViewById(R.id.heightOfGlas);
        fag = (TextView) findViewById(R.id.amountOfFag);
        glas = (TextView) findViewById(R.id.amountOfGlas);


    }

    public void send(View view) {
       wall.setHeight(Integer.parseInt((String.valueOf(hight.getText()))));
        wall.setLength(Integer.parseInt(String.valueOf(hight.getText())));

        System.out.println(wall.getPriceOfWall());
        price.setText(String.valueOf(wall.getPriceOfWall()));
        glas.setText("Væggen er " + wall.getAllowedAmountOfGlas() +" glas høj");
        fag.setText("Væggen er " + wall.getAllowedAmountOfFag()+ " fag bred");
        height.setText(("Højden af glaset er " + String.valueOf(wall.getHeightOfGlass())));
        width.setText(("Breden af glaset er " + String.valueOf(wall.getLengthOfGlass())));
    }

    public void mailsend(View view){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL,  "chil0041@edu.easj.dk");
        email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        email.putExtra(Intent.EXTRA_TEXT,  "Væggen er " + wall.getAllowedAmountOfFag()+ "fag bred og  Væggen er " + wall.getAllowedAmountOfGlas() +" glas høj"  );

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Send mail..."));
    }
}