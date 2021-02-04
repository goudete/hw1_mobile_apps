package com.example.homeworkone;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button main_button;
    private static final String api_url = "http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private String json_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grab button
        main_button = findViewById(R.id.home_button);

        //add event listener to button, when clicked, call launchNextActivity
        main_button.setOnClickListener(v -> {
            launchNextActivity(v);
        });

    }

    public void launchNextActivity(View view) {
        //set header
        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("api response", new String(responseBody));

                try {
                    JSONObject json = new JSONObject(new String(responseBody));

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("json_response", json.toString());

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // when you get a 400-499 status code
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // add the joke into the intent
                intent.putExtra("title", "API failed");
            }
        });

    }


}