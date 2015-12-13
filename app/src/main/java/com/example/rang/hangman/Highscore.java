package com.example.rang.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Highscore extends AppCompatActivity {

    public ArrayList<String> arrayListScore;
    public ArrayAdapter<String> arrayAdapterScore;
    public String value, value3;
    public String value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        arrayListScore = new ArrayList<>();
        arrayAdapterScore = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListScore);

        ListView listViewHighscore = (ListView) findViewById(R.id.listView);

        listViewHighscore.setAdapter(arrayAdapterScore);

        // to get word from winnerScreen
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("word");
            //get the value based on the key
        }

        // to get turnsLeft from winnerScreen
        Bundle extras2 = getIntent().getExtras();
        if (extras != null) {
            value2 = extras2.getString("turnsLeft");
            //get the value based on the key
        }

        // to get name from winnerScreen
        Bundle extras3 = getIntent().getExtras();
        if (extras != null) {
            value3 = extras3.getString("name");
            //get the value based on the key
        }
        // add values to listView
        arrayAdapterScore.add(value3 + "                            " + value + "                           " + (value2));
    }


    public void playClicked(View view) {
        Intent homeScreen = new Intent(this, GamePlayActivity.class);
        startActivity(homeScreen);
    }
}