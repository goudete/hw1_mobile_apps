package com.example.homeworkone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayout ll_story = findViewById(R.id.third_ll);

        ll_story.addView(story);

    }
}
