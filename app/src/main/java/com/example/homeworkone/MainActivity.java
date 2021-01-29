package com.example.homeworkone;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends AppCompatActivity {

    private static final String api_url = "https://icanhazdadjoke.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void launchNextActivity(View view) {
        //Lecture 4
        //when button is clicked, send get request to api
        //parse data, and add it to intent and
        // send intent to second activity to be displayed

        //set header
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // missing some code here to process response

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //missing code here to log error
            }
        });

    }


}