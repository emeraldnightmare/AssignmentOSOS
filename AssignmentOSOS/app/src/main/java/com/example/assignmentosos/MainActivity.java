package com.example.assignmentosos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://console.firebase.google.com/u/0/project/assignmentosos-2fa06/database/assignmentosos-2fa06-default-rtdb/data/~2F");
    EditText username , password;
    Button submit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = username.getText().toString();
                String Pass = password.getText().toString();
                if(!User.isEmpty()){
                    username.setError(null);
                    if(!Pass.isEmpty()){
                        password.setError(null);

                        final String username_data = username.getText().toString();
                        final String password_data = password.getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.exists()){
                                    username.setError(null);

                                    String passwordCheck = snapshot.child(username_data).getValue(String.class);
                                    if(passwordCheck.equals(password_data)){
                                        password.setError(null);
                                        Toast.makeText(MainActivity.this, "login sucessfully", Toast.LENGTH_SHORT).show();

                                        finish();

                                    }else{
                                        username.setError("wrong password ");
                                    }

                                }else{
                                    username.setError("user does not exist");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else{
                        password.setError(" Please enter password ");
                    }
                }else{
                    username.setError(" please enter username ");
                }
            }
        });



    }
}