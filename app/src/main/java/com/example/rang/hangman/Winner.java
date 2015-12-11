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
            value = extras.getString("currWord");
        //get the value based on the key
        }

        Bundle extras2 = getIntent().getExtras();
        if (extras != null) {
            value2 = extras2.getInt("turnsLeft");
            //get the value based on the key
        }

        word.setText(String.valueOf(value));
        turnsLeft.setText(String.valueOf(value2));

    }

    public void highscoreWinnerClicked(View view) {
        Intent homeScreen = new Intent(this, Highscore.class);
        homeScreen.putExtra("name", String.valueOf(name));
        startActivity(homeScreen);
    }
}