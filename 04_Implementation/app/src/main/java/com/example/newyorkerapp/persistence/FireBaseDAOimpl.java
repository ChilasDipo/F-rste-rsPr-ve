package com.example.newyorkerapp.persistence;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class FireBaseDAOimpl{
   private double priceOfGlass;
   private double feeForBigGlass ;
   private double feeForWetRoom;
   private double feeForTransport;
   private final HashMap<String, Integer> nameAndPricesForDoors = new HashMap<>();
   private final HashMap<String, Integer> nameAndPricesForGlas = new HashMap<>();
   private final HashMap<String, Integer> nameAndPricesForDoorsHandel = new HashMap<>();

   public FireBaseDAOimpl() {
      buildHashMapsForFetureNameAndPrice();
   }

   public HashMap<String, Integer> getNamesAndPriceForDoors(){
      return nameAndPricesForDoors;
   }
   public HashMap<String, Integer> getNamesAndPriceForGlass(){
      return nameAndPricesForGlas;
   }
   public HashMap<String, Integer> getNamesAndPriceForDoorHandel(){
      return nameAndPricesForDoorsHandel;
   }

   public double getFeeForBigGlass(){
      return feeForBigGlass;
   }

   public double getFeeForWetRoom(){
      return feeForWetRoom;
   }

   public double getPriceOfGlass(){
      return priceOfGlass;
   }
   public double getFeeForTransport(){
      return feeForTransport;
   }
   void buildHashMapsForFetureNameAndPrice(){
      //Bygger hashmap for alle typer døre med deres navn og pris
      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
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
     ref = database.getReference("/doorgrip");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            for ( DataSnapshot datasnapshot:snapshot.getChildren())
            {
               String key = datasnapshot.getKey();
               Integer value=datasnapshot.getValue(Integer.class);
               nameAndPricesForDoorsHandel.put(key,value);
               System.out.println("something is ha");
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
            priceOfGlass = snapshot.getValue(Double.class);
            //Set priceOfglass(snapshot.getValue(Integer.class))
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
         }
      });
      ref = database.getReference("/pris/stortfelt");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            feeForBigGlass = snapshot.getValue(Double.class);
            //Set priceOfglass(snapshot.getValue(Integer.class))
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
         }
      });

      ref = database.getReference("/pris/wetroom");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            feeForWetRoom = snapshot.getValue(Double.class);
            System.out.println("fee is " + feeForWetRoom);
            //Set priceOfglass(snapshot.getValue(Integer.class))
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });
      ref = database.getReference("/pris/fragt");
      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            feeForTransport = snapshot.getValue(Double.class);
         }
         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });

   }
}
