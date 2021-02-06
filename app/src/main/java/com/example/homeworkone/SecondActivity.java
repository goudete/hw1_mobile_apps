package com.example.homeworkone;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private String blanks_array;

    EditText input_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        LinearLayout ll_blanks = findViewById(R.id.ll_blanks);
        //reference of editText fields
        List<EditText> allEds = new ArrayList<EditText>();


        Intent intent = getIntent();
        String data = intent.getExtras().getString("json_response");


        try {
            JSONObject jsn = new JSONObject(data);
            String title = jsn.getString("title");
            JSONArray blanks_array = jsn.getJSONArray("blanks");
            JSONArray story = jsn.getJSONArray("value");

            displayTitle(ll_blanks, title);
            displayBlanks(ll_blanks, blanks_array, allEds);
            Button btn = createButton(ll_blanks);
            onButtonClick(btn, allEds, story, blanks_array.length());


        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    public void displayTitle(LinearLayout ll, String title) {
        TextView text_view_title = new TextView(this);
        text_view_title.setText(title);
        text_view_title.setTextSize(30);
        text_view_title.setPadding(0,5,0,10);
        text_view_title.setGravity(Gravity.CENTER);
        ll.addView(text_view_title);
    }

    public void displayBlanks(LinearLayout ll, JSONArray blanks, List<EditText> allEds) {

        for (int i = 0; i < blanks.length(); i++) {

            try {
                TextView label = new TextView(this);
                input_text = new EditText(this);
                allEds.add(input_text);

                input_text.setId(i);

                label.setText(blanks.getString(i));

                ll.addView(label);
                ll.addView(input_text);

            } catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

    public Button createButton(LinearLayout ll) {
        Button btn = new Button(this);
        btn.setText("Create");
        btn.setGravity(Gravity.CENTER);

        ll.addView(btn);
        return btn;
    }

    public void onButtonClick(Button btn, List<EditText> allEds, JSONArray story, int blanks_length) {
        //User input strings
        String[] user_strings = new String[allEds.size()];

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < allEds.size(); i ++) {
                    user_strings[i] = allEds.get(i).getText().toString();
                    Log.d("MAMMOTH", allEds.get(i).getText().toString());
                }
                addToStory(user_strings, story, blanks_length);
            }
        });
    }

    public void addToStory(String[] user_strings, JSONArray story, int blanks_length) {
        String final_story = "";
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        int inputSize = 0;

        //count number of inputs and trim inputs
        for (int k = 0; k < user_strings.length; k++) {
            if (!user_strings[k].isEmpty()) {
                inputSize++;
                user_strings[k] = user_strings[k].trim();
            }
        }

        //Show toast if not all inputs filled
        if (inputSize != blanks_length) {
            //display toast
            CharSequence text = "Please fill in all Inputs";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        } else {
            //iterate through story array, append user string to end
            for (int i = 0; i < story.length()-2; i ++) {
                try {
                    String sentence = (String) story.get(i);
                    String line = sentence + user_strings[i];
                    final_story += line;

                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
            try {
                final_story += (String) story.get(story.length()-2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            intent.putExtra("final_story", final_story);
            startActivity(intent);
        }

    }
}
