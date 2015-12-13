package com.example.rang.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Winner extends AppCompatActivity {
    TextView turnsLeft, word;
    EditText name;
    public String value;
    public int value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);

        name = (EditText) findViewById(R.id.nameWinner);
        turnsLeft = (TextView) findViewById(R.id.turnsWinner);
        word = (TextView) findViewById(R.id.wordWinner);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //get the value based on the key
            value = extras.getString("currWord");
        }

        Bundle extras2 = getIntent().getExtras();
        if (extras != null) {
            //get the value based on the key
            value2 = extras2.getInt("turnsLeft");
        }

        word.setText(String.valueOf(value));
        turnsLeft.setText(String.valueOf(value2));
    }

    public void highscoreWinnerClicked(View view) {
        Intent homeScreen = new Intent(this, Highscore.class);
        String turnsLeftHighscore = String.valueOf(value2);
        // new string to define name
        String nameInput = name.getText().toString().trim();
        // send name, turnsLeft and word to highscore
        homeScreen.putExtra("name", String.valueOf(nameInput));
        homeScreen.putExtra("turnsLeft", turnsLeftHighscore);
        homeScreen.putExtra("word", String.valueOf(value));

        startActivity(homeScreen);
    }
}