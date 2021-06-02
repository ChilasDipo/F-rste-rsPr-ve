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
   private  HashMap<String, Integer> nameAndPricesForItems = new HashMap<>();
   private  HashMap<String, Integer> nameAndPricesForDoors = new HashMap<>();
   private  HashMap<String, Integer> nameAndPricesForglas = new HashMap<>();
   private ArrayList<String> fetureList;
   private ArrayList<String> nameListForDoors;
   private ArrayList<Integer> priceListForDoors;
   private  ArrayList<Integer> priceListForFetures;

   public FireBaseDAOimpl() {
      buildHashMapsForFetureNameAndPrice();
     // connect();
   }

   public double getPriceOfGlass(){
      System.out.println("Price is " + priceOfGlass);
      return priceOfGlass;
   }
   public HashMap<String,Integer> getListOfDoors()  {
      return nameAndPricesForDoors;
   }
   public HashMap<String,Integer> getListOfGlas()  {
      return nameAndPricesForglas;
   }
   public ArrayList<Integer> getPriceOfdoors() {
      priceListForDoors  = new ArrayList<>(nameAndPricesForItems.values());
      System.out.println("Feturelist" + fetureList.toString());
      return priceListForFetures;
   }

   void buildHashMapsForFetureNameAndPrice(){
      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
      DatabaseReference ref = database.getReference("/pris");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               System.out.println("Imworking");
               nameAndPricesForItems.put(key,value);
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });
       ref = database.getReference("/door");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               System.out.println("Imworking");
               nameAndPricesForDoors.put(key,value);
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });

      System.out.println(nameAndPricesForDoors.toString());

      ref = database.getReference("/glas");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               System.out.println("Imworking");
               nameAndPricesForglas.put(key,value);
            }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });


   }

   DatabaseReference connect(){

      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
      DatabaseReference ref = database.getReference("/pris/glas");

      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             Double post = snapshot.child("price").getValue(Double.class);
            System.out.println("price from database " + post);
            priceOfGlass = post;
            System.out.println(priceOfGlass);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("The read failed: " + error.getCode());
         }
      });



      return ref;
   }




}
