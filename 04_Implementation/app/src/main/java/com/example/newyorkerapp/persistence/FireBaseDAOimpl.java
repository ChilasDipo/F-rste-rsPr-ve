package com.example.newyorkerapp.persistence;

import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FireBaseDAOimpl{
   double price = 5;

   public double getPriceOfGlass(){
      connect();
      System.out.println("PRice is " + price);
      return 985;
   }


   void connect(){

      FirebaseDatabase database = FirebaseDatabase.getInstance("https://testing-cf64a-default-rtdb.europe-west1.firebasedatabase.app/");
      DatabaseReference ref = database.getReference("/extra/glas");

      ref.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             Double post = snapshot.child("price").getValue(Double.class);
            System.out.println("price from database " + post);
            System.out.println("Price from database plus 5 " + (post + 5.0));
            price = post;
            System.out.println(price);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("The read failed: " + error.getCode());
         }
      });




   }




}
