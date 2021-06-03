package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newyorkerapp.R;
import com.example.newyorkerapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText width, height;
    private TextView  fag;
    private Spinner amountOfFagSelection, amountOfGlasSelection, doorSelection,glasSelection;
    private CheckBox glassCheckBox, wetRoomCheckBox, doorCheckBox;
     MainActivityViewModel mMainActivityViewModel;
     private Button buttonKontaktKontaktOs,buttonKontaktKatalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);

        amountOfFagSelection = findViewById(R.id.fagspinner);
        amountOfGlasSelection = findViewById(R.id.glasspinner);
        doorSelection = findViewById(R.id.doorSelection);
        glasSelection = findViewById(R.id.glasSelection);

        glassCheckBox = findViewById(R.id.glass);
        wetRoomCheckBox = findViewById(R.id.wetroom);
        doorCheckBox = findViewById(R.id.door);

        buttonKontaktKontaktOs = findViewById(R.id.buttonKontaktKontaktOs2);
        buttonKontaktKatalog = findViewById(R.id.buttonKontaktKatalog2);

        initializeOnClickListeners();


        fag = (TextView) findViewById(R.id.amountOfFag);


        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.getWall().observe(this, wallimpls -> {
            fag.setText(mMainActivityViewModel.getInfoAboutWall());
        });




}

void  initializeOnClickListeners(){
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
                        mMainActivityViewModel.selectDoor(position);
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

                ArrayAdapter<String> adapterForGlas = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getListOfGlas());
                adapterForGlas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
                glasSelection.setAdapter(adapterForGlas);
                glasSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mMainActivityViewModel.selectGlass(position);
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
    wetRoomCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.setWetRoom(glassCheckBox.isChecked());
        }
    });


    buttonKontaktKontaktOs.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(myIntent);

        }
    });
    buttonKontaktKatalog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(myIntent);
        }
    });


}



    public void send(View view) {
        mMainActivityViewModel.setHeight(Integer.parseInt((String.valueOf(height.getText()))));
        mMainActivityViewModel.setlenght(Integer.parseInt((String.valueOf(width.getText()))));

        amountOfFagSelection.setVisibility(View.VISIBLE);
        amountOfGlasSelection.setVisibility(View.VISIBLE);
        doorCheckBox.setVisibility(View.VISIBLE);
        glassCheckBox.setVisibility(View.VISIBLE);
        wetRoomCheckBox.setVisibility(View.VISIBLE);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getAdapterForFag());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
        amountOfFagSelection.setAdapter(adapter);
        amountOfFagSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMainActivityViewModel.widthPickedFromDropDown(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            ArrayList<Integer> glasListe = mMainActivityViewModel.getAdapterForGlas();
            ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glasListe);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            amountOfGlasSelection.setAdapter(adapter2);
            amountOfGlasSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   mMainActivityViewModel.heightPickedFromDropDown(position);
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
