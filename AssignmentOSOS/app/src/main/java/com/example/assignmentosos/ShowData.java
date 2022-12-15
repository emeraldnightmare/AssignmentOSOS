package com.example.assignmentosos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowData extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static final String SHARED_PREFS = "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        Fragment fragment = new Map_Fragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();



        setContentView(R.layout.activity_show_data);
        recyclerView = findViewById(R.id.recyclerview);


        linearLayoutManager = new LinearLayoutManager(ShowData.this , LinearLayoutManager.HORIZONTAL , false );

        recyclerView.setLayoutManager(linearLayoutManager);


        //retro fit api fetch
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<post>> call = jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(ShowData.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<post> postList = response.body();
                PostAdapter postAdapter = new PostAdapter(ShowData.this,postList);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {

                Toast.makeText(ShowData.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    //menu for logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void logout(){

        //shares prefrences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name","");
        editor.apply();

        Intent intent = new Intent(getApplicationContext() , LoginActivity.class );
        startActivity(intent);
        finish();

    }
}