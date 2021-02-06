package com.example.homeworkone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String final_story = intent.getExtras().getString("final_story");

        TextView story = new TextView(this);
        story.setText(final_story);
        story.setTextSize(30);
        story.setPadding(5, 50, 5, 100 );
        LinearLayout ll_story = findViewById(R.id.third_ll);

        ll_story.addView(story);

        //Grab button
        Button back_button = findViewById(R.id.back_to_home);
        back_button.setOnClickListener(v -> {
            launchNextActivity(v);
        });

    }

    public void launchNextActivity(View view) {
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
