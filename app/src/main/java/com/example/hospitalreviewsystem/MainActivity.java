package com.example.hospitalreviewsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText userid,password;
Button login,signup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
SharedPreferences sharedPref;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid = (EditText)findViewById(R.id.userid);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userid.getText().toString();
                String pass = password.getText().toString();
                logMethod(user,pass);
            }
        });




    }



    public void logMethod(final String user, final String pass){
        DatabaseReference myRef = database.getReference("users");
        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(MainActivity.this,"userid or password cannot be empty",Toast.LENGTH_LONG).show();
            return;
        }
        else{
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      if (dataSnapshot.hasChild(user) && pass.equals( dataSnapshot.child(user).child("password").getValue())) {
                          sharedPref = getApplicationContext().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                          SharedPreferences.Editor editor;
                    editor = sharedPref.edit();
                          editor.putString("userid",user);
                          editor.putString("password",pass);
                          editor.apply();
                          Intent intent =  new Intent(MainActivity.this,Hospitals.class);
                          startActivity(intent);



                    } else {
                          for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                              Toast.makeText(MainActivity.this, "userid or password is not matching" , Toast.LENGTH_LONG).show();
                          }
                }




                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





        }


    }











}
