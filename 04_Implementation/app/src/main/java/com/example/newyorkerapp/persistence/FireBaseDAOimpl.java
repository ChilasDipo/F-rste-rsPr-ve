package com.example.newyorkerapp.persistence;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class FireBaseDAOimpl{
   private double priceOfGlass = 985;
   private double feeForBigGlass = 485;
   private  HashMap<String, Integer> nameAndPricesForDoors = new HashMap<>();
   private  HashMap<String, Integer> nameAndPricesForGlas = new HashMap<>();
   private ArrayList<String> fetureList;
   private ArrayList<Integer> priceListForDoors;

   public FireBaseDAOimpl() {
      buildHashMapsForFetureNameAndPrice();
     // connect();
   }

   public double getPriceOfGlass(){
      System.out.println("Price is " + priceOfGlass);
      return priceOfGlass;
   }
   public ArrayList<String> getListOfDoors()  {
      //setPriceListForDoors(new ArrayList<>(nameAndPricesForDoors.values()));
      return new ArrayList<>(nameAndPricesForDoors.keySet());
   }
   public ArrayList<String> getListOfGlas()  {
     //setPriceListOfglas(nameAndPricesForglas.values())  Måske sætte værdien i pris klassen
     return new ArrayList<>(nameAndPricesForGlas.keySet());
   }
   void buildHashMapsForFetureNameAndPrice(){
      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
      //Bygger hashmap for alle typer døre med deres navn og pris
      DatabaseReference  ref = database.getReference("/door");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               nameAndPricesForDoors.put(key,value);
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });

      //Bygger hashmap for alle typer specielglas med deres navn og pris
      ref = database.getReference("/glas");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               nameAndPricesForGlas.put(key,value);
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });
      ref = database.getReference("/pris/glas");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            priceOfGlass = snapshot.getValue(Integer.class);
            //Set priceOfglass(snapshot.getValue(Integer.class))
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
         }
      });

   }
}
