package com.example.rang.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Loser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loser);
    }

    public void restartClicked(View view) {
        Intent homeScreen = new Intent(this, GamePlayActivity.class);
        startActivity(homeScreen);
    }
}