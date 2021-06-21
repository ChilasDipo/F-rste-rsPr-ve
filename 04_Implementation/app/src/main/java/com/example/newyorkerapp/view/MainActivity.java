package com.example.newyorkerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
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
    private Spinner amountOfFagSelection, amountOfGlasSelection, doorSelection,glasSelection,doorHandleSelection;
    MainActivityViewModel mMainActivityViewModel;
    private CheckBox glassCheckBox, wetRoomCheckBox, doorCheckBox;
     private Button buttonKontaktKontaktOs,buttonKontaktKatalog,sendToContact;

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
        doorHandleSelection = findViewById(R.id.doorHandleSelection);

        glassCheckBox = findViewById(R.id.glass);
        wetRoomCheckBox = findViewById(R.id.wetroom);
        doorCheckBox = findViewById(R.id.door);

        buttonKontaktKontaktOs = findViewById(R.id.buttonKontaktKontaktOs2);
        buttonKontaktKatalog = findViewById(R.id.buttonKontaktKatalog2);
        sendToContact = findViewById(R.id.sendToContact);

        fag = (TextView) findViewById(R.id.amountOfFag);


        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.setSelectedWallFromCatalog(getIntent().getExtras().getInt("imageID"));

        mMainActivityViewModel.getWall().observe(this, wallimpls -> {
            fag.setText(mMainActivityViewModel.getInfoAboutWall());
        });


        width.setText(String.valueOf(mMainActivityViewModel.getLengthOfTheWall()));
        height.setText(String.valueOf(mMainActivityViewModel.getHeightOfTheWall()));

        initializeOnClickListeners();
}
void  initializeOnClickListeners(){

    wetRoomCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.setWetRoom(wetRoomCheckBox.isChecked());
        }
    });

    sendToContact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(myIntent);

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
            Intent myIntent = new Intent(MainActivity.this, CatalogActivity.class);
            startActivity(myIntent);
        }
    });

    ArrayAdapter<Integer> adapterForFag = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getAdapterForFag());
    adapterForFag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
    amountOfFagSelection.setAdapter(adapterForFag);
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
    ArrayAdapter<Integer> adapterForGlasListe = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glasListe);
    adapterForGlasListe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    amountOfGlasSelection.setAdapter(adapterForGlasListe);
    amountOfGlasSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mMainActivityViewModel.heightPickedFromDropDown(position);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
    glassCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.sethasSpecielGlass(glassCheckBox.isChecked());
            if (glassCheckBox.isChecked()){
                System.out.println(v.getId());
                glasSelection.setVisibility(View.VISIBLE);

                ArrayAdapter<String> adapterForGlas = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getListOfGlas());
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



    doorCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.setHasDoor(doorCheckBox.isChecked());
            if (doorCheckBox.isChecked()) {
                doorSelection.setVisibility(View.VISIBLE);
                doorHandleSelection.setVisibility((View.VISIBLE));

                ArrayAdapter<String> adapterForDoors = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getListOfDoors());
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



                ArrayAdapter<String> adapterForDoorGrip = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getListOfDoorgrips());
                adapterForDoorGrip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
                doorHandleSelection.setAdapter(adapterForDoorGrip);
                doorHandleSelection.setSelection(0);
                doorHandleSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else{
                doorSelection.setVisibility(View.INVISIBLE);
                doorHandleSelection.setVisibility(View.INVISIBLE);
            }

        }
    });
//sætter checkmark hvis det er det i katalog
    if (mMainActivityViewModel.gethasDoor()){
        doorCheckBox.performClick();
    }

}
    public void changeHeightAndLenght(View view) {
       mMainActivityViewModel.setHeight(Integer.parseInt((String.valueOf(height.getText()))));
       mMainActivityViewModel.setlenght(Integer.parseInt((String.valueOf(width.getText()))));
}
    }
