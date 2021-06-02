package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newyorkerapp.R;
import com.example.newyorkerapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText brede, hight;
    private TextView price, width, height, fag, glas;
    private Spinner fagSpinner, glasspinner,glasspinner2, doorSelection,glasSelection;
    private CheckBox glassCheckBox, wetroomCheckBox, doorCheckBox;
     MainActivityViewModel mMainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        brede = findViewById(R.id.width);
        hight = findViewById(R.id.height);
        fagSpinner = findViewById(R.id.fagspinner);
        glasspinner = findViewById(R.id.glasspinner);
        doorSelection = findViewById(R.id.doorSelection);
        glasSelection = findViewById(R.id.glasSelection);


        glassCheckBox = findViewById(R.id.glass);
        wetroomCheckBox = findViewById(R.id.wetroom);
        doorCheckBox = findViewById(R.id.door);

       doorCheckBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (doorCheckBox.isChecked()){
                   doorSelection.setVisibility(View.VISIBLE);

                   ArrayList<String> doorlist = mMainActivityViewModel.getListOfDoors();
                   ArrayAdapter<String> adapterForDoors = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, doorlist);
                   adapterForDoors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
                   doorSelection.setAdapter(adapterForDoors);
                   doorSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                       }

                       @Override
                       public void onNothingSelected(AdapterView<?> parent) {

                       }
                   });

               }else{
                   doorSelection.setVisibility(View.INVISIBLE);
               }
           }
       });

        glassCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (glassCheckBox.isChecked()){
                    glasSelection.setVisibility(View.VISIBLE);

                    ArrayList<String> glaslist = mMainActivityViewModel.getListOfGlas();
                    ArrayAdapter<String> adapterForGlas = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glaslist);
                    adapterForGlas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
                    glasSelection.setAdapter(adapterForGlas);
                    glasSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }else{
                    glasSelection.setVisibility(View.INVISIBLE);
                }
            }
        });


        price = (TextView) findViewById(R.id.price);
        width = (TextView) findViewById(R.id.widthOfGlas);
        height = (TextView) findViewById(R.id.heightOfGlas);
        fag = (TextView) findViewById(R.id.amountOfFag);
        glas = (TextView) findViewById(R.id.amountOfGlas);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.getWall().observe(this, wallimpls -> {
            fag.setText(mMainActivityViewModel.getInfoAboutWall());
        });


}
    public void send(View view) {
        mMainActivityViewModel.setheight(Integer.parseInt((String.valueOf(hight.getText()))));
        mMainActivityViewModel.setlenght(Integer.parseInt((String.valueOf(brede.getText()))));


        ArrayList<Integer> fagliste = mMainActivityViewModel.getAdapterForFag();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, fagliste);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
        fagSpinner.setAdapter(adapter);
        fagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMainActivityViewModel.widthPicedFromDropDown(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            ArrayList<Integer> glasListe = mMainActivityViewModel.getAdapterForGlas();
            ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glasListe);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            glasspinner.setAdapter(adapter2);
            glasspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   mMainActivityViewModel.heightPicedFromDropDown(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
}
public void mailsend(View view) {
       /* Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:chil0041@edu.easj.dk"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject Here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Væggen er"  + wall.getAllowedAmountOfFag()+ "fag bred og  Væggen er" + wall.getAllowedAmountOfGlas() +" glas høj ");
        startActivity(Intent.createChooser(emailIntent, "Send feedback"));*/



    /* sending gennem API
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
        }*/

    Intent myIntent = new Intent(this, FrontPage.class);
    startActivity(myIntent);


    }
    }
