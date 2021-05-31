package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newyorkerapp.R;
import com.example.newyorkerapp.model.data.Wallimpl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.jakebreen.sendgridandroid.SendGrid;
import uk.co.jakebreen.sendgridandroid.SendGridMail;
import uk.co.jakebreen.sendgridandroid.SendGridResponse;
import uk.co.jakebreen.sendgridandroid.SendTask;

public class MainActivity extends AppCompatActivity {
    EditText brede, hight;
    TextView price, width, height, fag, glas;
    Wallimpl wall = new Wallimpl();
    Spinner fagSpinner, glasspinner;
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        brede = findViewById(R.id.brede);
        hight = findViewById(R.id.højde);
        fagSpinner = findViewById(R.id.fagspinner);
        glasspinner = findViewById(R.id.glasspinner);


        price = (TextView) findViewById(R.id.price);
        width = (TextView) findViewById(R.id.widthOfGlas);
        height = (TextView) findViewById(R.id.heightOfGlas);
        fag = (TextView) findViewById(R.id.amountOfFag);
        glas = (TextView) findViewById(R.id.amountOfGlas);


    }

    public void mailsend(View view) {
       /* Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:chil0041@edu.easj.dk"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Væggen er"  + wall.getAllowedAmountOfFag()+ "fag bred og  Væggen er" + wall.getAllowedAmountOfGlas() +" glas høj ");
        startActivity(Intent.createChooser(emailIntent, "Send feedback"));*/


        SendGrid sendGrid = SendGrid.create("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");
        SendGridMail mail = new SendGridMail();
        mail.addRecipient("chil0041@edu.easj.dk", "Chilas");
        mail.setFrom("chil0041@edu.easj.dk", "Chilas");
        mail.setSubject("Hej fra sendgrid");
        mail.setContent("Hej, det virker nu. Måske.");
        SendTask task = new SendTask(sendGrid, mail);
        try {
            SendGridResponse response = task.execute().get();
            System.out.println(response.getErrorMessage());
            System.out.println(response.isSuccessful());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void send(View view) {
        wall.setHeight(Integer.parseInt((String.valueOf(hight.getText()))));
        wall.setLength(Integer.parseInt(String.valueOf(brede.getText())));

        ArrayList<Integer> fagliste = wall.getFagliste();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, fagliste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
        fagSpinner.setAdapter(adapter);
        fagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Position is " + position);
                System.out.println("id is " + id);
                wall.setFinalLengthOfGlas(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            ArrayList<Integer> glasListe = wall.getGlasliste();
            ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glasListe);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            glasspinner.setAdapter(adapter2);
            glasspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Position is " + position);
                    System.out.println("id is " + id);
                    wall.setFinalHeightOfGlas(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




}
    }
