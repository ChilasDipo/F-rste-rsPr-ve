package com.example.newyorkerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newyorkerapp.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.jakebreen.sendgridandroid.SendGrid;
import uk.co.jakebreen.sendgridandroid.SendGridMail;
import uk.co.jakebreen.sendgridandroid.SendGridResponse;
import uk.co.jakebreen.sendgridandroid.SendTask;

public class ContactActivity extends AppCompatActivity {

    private String name, address, message, email;
    private int phonenumber;

    Button buttonKontaktMeasure, buttonKontaktKatalog, buttonKontaktSend;
    Spinner storeDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().hide();

        buttonKontaktKatalog = findViewById(R.id.buttonKontaktKatalog);
        buttonKontaktMeasure = findViewById(R.id.buttonKontaktMeasure);
        buttonKontaktSend = findViewById(R.id.buttonKontaktSend);
        storeDropDown = findViewById(R.id.storeDropDown);
        initializeOnClickListeners();
    }

    public int getPhonenumber() {
        EditText editText = findViewById(R.id.editTextKontaktPhonenumber);
        String temp = editText.getText().toString();
        if (!"".equals(temp)){
            phonenumber = Integer.parseInt(temp);
        }
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        EditText editText = findViewById(R.id.editTextKontaktName);
        name = editText.getText().toString();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        EditText editText = findViewById(R.id.editTextKontaktAdress);
        address = editText.getText().toString();
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        EditText editText = findViewById(R.id.editTextTextMultiLine);
        message = editText.getText().toString();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        EditText editText = findViewById(R.id.editTextKontaktEmail);
        email = editText.getText().toString();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    void initializeOnClickListeners(){
        buttonKontaktMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ContactActivity.this, CatalogActivity.class);
                startActivity(myIntent);

            }
        });
        buttonKontaktKatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ContactActivity.this, CatalogActivity.class);
                startActivity(myIntent);
            }
        });
        buttonKontaktSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        ArrayList<String> storeList = new ArrayList<>();
        storeList.add("X-byg");
        storeList.add("Stark");
        storeList.add("Flere Forhandlere");
        storeList.add("Flere Forhandlere");
    ArrayAdapter<String> adapterForStore = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, storeList);
        adapterForStore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //https://stackoverflow.com/questions/34798967/use-object-array-list-as-spinner-adapter
        storeDropDown.setAdapter(adapterForStore);
        storeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            System.out.println(storeList.get(position));
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });

}

private void sendMail(){
   SendGrid sendGrid = SendGrid.create("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");
    SendGridMail mail = new SendGridMail();
    mail.addRecipient("chil0041@edu.easj.dk", "Chilas");
    mail.setFrom("chil0041@edu.easj.dk", "Chilas");
    mail.setSubject("Tilbud for " + getName());
    String string = "Navn: " + getName()  + "\n" + "Telefon: " + getPhonenumber()  + "\n" + "Email: " + getEmail() + "\n" + "Besked: " + getMessage();
    mail.setContent(string);
    SendTask task = new SendTask(sendGrid, mail);
    try {
        SendGridResponse response = task.execute().get();
        System.out.println(response.isSuccessful());
        Toast toast = Toast.makeText(ContactActivity.this,"Email er blevet sendt", Toast.LENGTH_SHORT);
        toast.show();
        System.out.println("Working");
    } catch (ExecutionException e) {
        e.printStackTrace();
        Toast toast = Toast.makeText(ContactActivity.this,"Error I sending af mail", Toast.LENGTH_SHORT);
        toast.show();
    } catch (InterruptedException e) {
        Toast toast = Toast.makeText(ContactActivity.this,"Error I sending af mail", Toast.LENGTH_SHORT);
        toast.show();
        e.printStackTrace();
    }
}

}
