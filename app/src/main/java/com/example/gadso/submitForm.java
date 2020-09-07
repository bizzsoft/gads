package com.example.gadso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class submitForm extends AppCompatActivity {
  EditText firstname,lastname,email,project;
  String firstnames,lastnames,emails,projects;
    ArrayList<skill> list;
    private static String  Json_url = "https://docs.google.com/forms/d/e/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        project = findViewById(R.id.project);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the values
                firstnames = firstname.getText().toString();
                lastnames = lastname.getText().toString();
                emails = email.getText().toString();
                projects = project.getText().toString();
                // sending data
                HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
                logger.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(logger)
                        .build();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Json_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttp)
                        .build();
                skillservices skillservices = retrofit.create(com.example.gadso.skillservices.class);


                Call<List<skill>> leaderRequest = skillservices.submitProject(
                        emails,firstnames,lastnames,projects
                );
                leaderRequest.enqueue(new Callback<List<skill>>() {
                                          @Override
                                          public void onResponse(Call<List<skill>> call, Response<List<skill>> response) {
                                              
                                          }

                                          @Override
                                          public void onFailure(Call<List<skill>> call, Throwable t) {

                                          }
                                      }
                );



            }
        });

        
    }
}