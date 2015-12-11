package com.example.rang.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// iets geprobeerd voor highscore om tenminste iets te hebben maar het werkt niet helemaal helaas...
public class Highscore extends AppCompatActivity {

    TextView name, word, turnsLeft;
    public String value, value3;
    public int value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        name = (TextView) findViewById(R.id.nameHighscore);
        word = (TextView) findViewById(R.id.wordHighscore);
        turnsLeft = (TextView) findViewById(R.id.turnleftHighscore);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("currWord");
            //get the value based on the key
        }

        Bundle extras2 = getIntent().getExtras();
        if (extras != null) {
            value2 = extras2.getInt("turnsLeft");
            //get the value based on the key
        }

        Bundle extras3 = getIntent().getExtras();
        if (extras3 != null) {
            value3 = extras3.getString("name");
            //get the value based on the key
        }

        word.setText(String.valueOf(value));
        turnsLeft.setText(String.valueOf(value2));
        name.setText(String.valueOf(value3));
    }

    public void playClicked(View view) {
        Intent homeScreen = new Intent(this, GamePlayActivity.class);
        startActivity(homeScreen);
    }
}