package com.example.newyorkerapp.persistence;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBaseDAOimpl{
   double priceOfGlass = 985;
   double feeForBigGlass = 485;

   public double getPriceOfGlass(Boolean glassIsBig){
      //connect();
      System.out.println("Price is " + priceOfGlass);
      if (glassIsBig){
      return priceOfGlass+feeForBigGlass;
      }
      return priceOfGlass;
   }

   void getListOfExtraFeturesFromDB(){
      DatabaseReference ref = connect();


      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });




   }

   DatabaseReference connect(){

      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
      DatabaseReference ref = database.getReference("/extra/glas");

      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             Double post = snapshot.child("price").getValue(Double.class);
            System.out.println("price from database " + post);
            System.out.println("Price from database plus 5 " + (post + 5.0));
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
