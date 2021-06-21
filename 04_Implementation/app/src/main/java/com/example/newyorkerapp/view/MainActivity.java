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
    private Spinner amountOfFagSelection, amountOfGlasSelection, doorSelection,glasSelection,doorHandleSelection;
    MainActivityViewModel mMainActivityViewModel;
    private CheckBox glassCheckBox, wetRoomCheckBox, doorCheckBox;
    private Button buttonKontaktKontaktOs,buttonKontaktKatalog,sendToContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //<editor-folddesc="FindByView">
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

        //</editor-fold>

        //Skaber forbinelse mellem vores Activity og vores ViewModel
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //Fortæller vores ViewModel hvilken væg som er blevet valgt i vores katalog
        mMainActivityViewModel.setSelectedWallFromCatalog(getIntent().getExtras().getInt("imageID"));
        /* Opsætter en observer på det Livedata Obejkt som bliver retuneret i getWall() metoden */
        mMainActivityViewModel.getWall().observe(this, wallimpls -> {
            fag.setText(mMainActivityViewModel.getInfoAboutWall());
        });

        //Udfylder vores editText felter med det rigtige værdier fra den valgte væg
        width.setText(String.valueOf(mMainActivityViewModel.getLengthOfTheWall()));
        height.setText(String.valueOf(mMainActivityViewModel.getHeightOfTheWall()));

        initializeOnClickListeners();
}
void  initializeOnClickListeners(){
    //<editor-folddesc="Navigations knapper ">
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
    //</editor-fold>

    wetRoomCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.setWetRoom(wetRoomCheckBox.isChecked());
        }
    });
    //Opsætter Arraylist som skal ind i vores dropdown
    ArrayList<Integer> glasListe = mMainActivityViewModel.getAdapterForGlas();
    //Sætter vores Arraylist ind i en ArrayAdapter som fungere som en bro mellem datasourse og UIet
    ArrayAdapter<Integer> adapterForGlasListe = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, glasListe);
    //Definere hvordan layoutet skal være når dropdownen er valgt
    adapterForGlasListe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Sætter adapteren til vores dropdown
    amountOfGlasSelection.setAdapter(adapterForGlasListe);
    //Sætter en OnItemSelectedListener som bliver kaldt når man vægler noget i dropdownen
    amountOfGlasSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Den valge position bliver sendt vidre til ViewModel så den ved hvilken højde er valgt
            mMainActivityViewModel.selectAmountOfGlassPerFag(position);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    //Det sammen sker for breden
    ArrayAdapter<Integer> adapterForFag = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mMainActivityViewModel.getAdapterForFag());
    adapterForFag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
    amountOfFagSelection.setAdapter(adapterForFag);
    amountOfFagSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mMainActivityViewModel.selectAmountOfFag(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    glassCheckBox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMainActivityViewModel.setHasSpecielGlass(glassCheckBox.isChecked());
            if (glassCheckBox.isChecked()){
               //Gør vores dropdown for speciel glas synlig hvis boxen er checket
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
                //Gør vores dropdown for speciel glas usynlig hvis boxen ikke er checket
                glasSelection.setVisibility(View.INVISIBLE);
            }
        }
    });


    //Det sammen som med glas, det er bare 2 dropdowns, da dørhåndtag er sammen med dør
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
//sætter checkmark hvis det er det i katalog, dette skal ske efter OnclickListener er sat ellers vil checkmark ikke ædnre logik
    if (mMainActivityViewModel.getHasDoor()){
        //PerformClick gør så systemet tror der er blevet klikket
        doorCheckBox.performClick();
    }

}
    //Bliver kaldt på klik af knap fra XML
    public void changeHeightAndLenght(View view) {
        //Ændre højde og længe i vores logik nå der bliver klikket på knappen
       mMainActivityViewModel.setHeight(Integer.parseInt((String.valueOf(height.getText()))));
       mMainActivityViewModel.setlenght(Integer.parseInt((String.valueOf(width.getText()))));
}
    }