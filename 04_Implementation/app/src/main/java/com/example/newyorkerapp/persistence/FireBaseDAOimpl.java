package com.example.newyorkerapp.persistence;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class FireBaseDAOimpl implements DAO{
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
   @Override
   public HashMap<String, Integer> getNamesAndPriceForDoors(){
      return nameAndPricesForDoors;
   }
   @Override
   public HashMap<String, Integer> getNamesAndPriceForGlass(){
      return nameAndPricesForGlas;
   }
   @Override
   public HashMap<String, Integer> getNamesAndPriceForDoorHandel(){
      return nameAndPricesForDoorsHandel;
   }
   @Override
   public double getFeeForBigGlass(){
      return feeForBigGlass;
   }
   @Override
   public double getFeeForWetRoom(){
      return feeForWetRoom;
   }
   @Override
   public double getPriceOfGlass(){
      return priceOfGlass;
   }
   @Override
   public double getFeeForTransport(){
      return feeForTransport;
   }

   //Metoder som byugger HashMaps til Døre, glas, DørHåndtag og hente enkle værdier ned og sætter sætte dem så de er klar til at bleve gettet.
   //Denne metode bliver kaldt i konstruktor så den har tid til at hente alle værdier ned før de skal bruges så der ikke kommer en nulpointerexception og værdien er blevet sat til det rigte før den bliver gettet
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
