package com.example.gadspracticeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private Button submit;
    ImageView sucess;
    ImageView failure;
    Toolbar toolbar;
    Button yes;
    private static final String TAG = "PostActivity";
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String gitHubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sucess = findViewById(R.id.success_image);
        failure = findViewById(R.id.failure_image);


        //initialize views and get String values
        EditText firstName = findViewById(R.id.editText_first_name);
        EditText lastName = findViewById(R.id.editText_last_name);
        EditText email = findViewById(R.id.editText_email);
        EditText githubLink = findViewById(R.id.editText_github_link);


        submit = findViewById(R.id.button);
        submit.setOnClickListener((v)-> {
            firstname = firstName.getText().toString();
            lastname = lastName.getText().toString();
            gitHubLink = githubLink.getText().toString();
            emailAddress = email.getText().toString();
            if (firstname.isEmpty() || lastname.isEmpty() || emailAddress.isEmpty() || gitHubLink.isEmpty()) {
                Toast.makeText(PostActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            } else {
                showAlert();
            }
        });
    }

    private void sendPost() {
        //Make Api call
        PostRetrofitInterface getApi = PostRetrofitClient.getPostRetrofit().create(PostRetrofitInterface.class);
        Call<Void> call = getApi.submitPost(firstname, lastname, emailAddress, gitHubLink);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TAG", "success");
                // Toast.makeText(PostActivity.this, "success", Toast.LENGTH_SHORT).show();
               // showAlert();
                showSuccesAlert();
                //handler takes two parameters
                new Handler().postDelayed(()->{ goBackHome(); }, 5000);

            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showFailureAlert();
                Log.d("TAG", "failure");
                Toast.makeText(PostActivity.this, "Turn on Internet", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void goBackHome() {
        Intent intent = new Intent(PostActivity.this, MainActivity.class);
        startActivity(intent);

    }

    private void showSuccesAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.success, null);
        builder.setView(view);
        builder.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.confirmation, null);
        builder.setView(view);
        ImageButton close = view.findViewById(R.id.close);
        Button yesButton =   view.findViewById(R.id.yes_button);

        //Used Langa in java 8
        yesButton.setOnClickListener((v)-> sendPost());
        close.setOnClickListener((v)-> finish());

        builder.show();
    }

    private void showFailureAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.failure, null);
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }

}