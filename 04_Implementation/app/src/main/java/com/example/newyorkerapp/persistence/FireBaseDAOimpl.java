package com.example.newyorkerapp.persistence;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;



public class FireBaseDAOimpl{
   private static double priceOfGlass;
   private static double feeForBigGlass ;
   private static double feeForWetRoom;
   private static double feeForTransport;
   private static final HashMap<String, Integer> nameAndPricesForDoors = new HashMap<>();
   private static final HashMap<String, Integer> nameAndPricesForGlass = new HashMap<>();
   private static final HashMap<String, Integer> nameAndPricesForDoorsHandel = new HashMap<>();

   public static HashMap<String, Integer> getNamesAndPriceForDoors(){
      return nameAndPricesForDoors;
   }

   public static HashMap<String, Integer> getNamesAndPriceForGlass(){
      return nameAndPricesForGlass;
   }

   public static HashMap<String, Integer> getNamesAndPriceForDoorHandel(){
      return nameAndPricesForDoorsHandel;
   }

   public static double getFeeForBigGlass(){
      return feeForBigGlass;
   }

   public static double getFeeForWetRoom(){
      return feeForWetRoom;
   }

   public static double getPriceOfGlass(){
      return priceOfGlass;
   }

   public static double getFeeForTransport(){
      return feeForTransport;
   }

   //Metoder som bugger HashMaps til Døre, glas, DørHåndtag og hente enkle værdier ned og sætter sætte dem så de er klar til at bleve gettet.
   //Denne metode bliver kaldt i konstruktor så den har tid til at hente alle værdier ned før de skal bruges så der ikke kommer en nulpointerexception og værdien er blevet sat til det rigte før den bliver gettet
   public static void buildHashMapsForFetureNameAndPrice(){
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
               nameAndPricesForGlass.put(key,value);
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
