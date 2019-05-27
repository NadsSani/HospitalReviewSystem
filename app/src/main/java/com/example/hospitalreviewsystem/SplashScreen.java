package com.example.hospitalreviewsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
 SharedPreferences sharedPref;


 FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");




    }

 @Override
    public void onStart() {
        super.onStart();
     
        sharedPref = getApplicationContext().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String email1 =  sharedPref.getString("userid","d");
        String password = sharedPref.getString("password","d");
        logMethod(email1 ,password);

}



 public void logMethod(final String user, final String pass) {
     DatabaseReference myRef = database.getReference("users");
         myRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (dataSnapshot.hasChild(user) && pass.equals(dataSnapshot.child(user).child("password").getValue())) {
                      Intent intent =  new Intent(SplashScreen.this,Hospitals.class);
                      startActivity(intent);

                 } else {

                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                             startActivity(intent);
                             finish();
                         }
                     },2500);




                 }


             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


     }



}
