package com.example.rang.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GamePlayActivity extends AppCompatActivity {
    GoodGamePlay gamePlay;
    TextView viewLetter, turnsLeft, wrongLetters;
    EditText inputLetter;
    Button info, settings, highscore,restart, submit;
    WordLoad wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        wordList = new WordLoad(this);

        SharedPreferences prefs = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);

        int turnsAllowed = prefs.getInt("turns", 10);

        int wordLength = prefs.getInt("word", 4);

        gamePlay = new GoodGamePlay(wordList, wordLength, turnsAllowed);

        viewLetter = (TextView) findViewById(R.id.viewLetter);
        turnsLeft = (TextView) findViewById(R.id.turnsLeft);
        wrongLetters = (TextView) findViewById(R.id.wrongLetters);

        inputLetter = (EditText) findViewById(R.id.inputLetter);

        info = (Button) findViewById(R.id.infoHome);
        settings = (Button) findViewById(R.id.settingsHome);
        highscore = (Button) findViewById(R.id.highscoreHome);
        restart = (Button) findViewById(R.id.restartHome);
        submit = (Button) findViewById(R.id.sendLetter);

        turnsLeft.setText(String.valueOf(gamePlay.turnsLeft));
        viewLetter.setText(String.valueOf(gamePlay.showLetter));
    }

    public void won(){
        Intent winnerScreen = new Intent(this, Winner.class);
        winnerScreen.putExtra("currWord", gamePlay.currWord);
        winnerScreen.putExtra("turnsLeft", gamePlay.turnsLeft);
        startActivity(winnerScreen);
    }

    public void lost(){
        Intent loserScreen = new Intent(this, Loser.class);
        startActivity(loserScreen);
    }

    public void submitClicked(View v) {
        final Character C = inputLetter.getText().toString().toUpperCase().charAt(0);
        boolean guess = gamePlay.guess(C);
        if(guess == true) {
            viewLetter.setText(String.valueOf(gamePlay.showLetter));
            if (gamePlay.checkWord()) {
                won();
            }
        } else{
            wrongLetters.setText(gamePlay.wrongLetters.toString());
            turnsLeft.setText(String.valueOf(gamePlay.turnsLeft));
            if (gamePlay.noTurnsLeft()) {
                lost();
            }
        }
    }

    public void restartClicked(View view){
        gamePlay.restart();
        gamePlay.invisibleWord();
        turnsLeft.setText((String.valueOf(gamePlay.turnsLeft)));
        wrongLetters.setText(String.valueOf(gamePlay.wrongLetters));
        viewLetter.setText(String.valueOf(gamePlay.showLetter));
    }

    public void infoClicked(View view) {
        Intent infoScreen = new Intent(this, Instructions.class);
        startActivity(infoScreen);
    }

    public void settingsClicked(View view) {
        Intent settingsScreen = new Intent(this, Settings.class);
        startActivity(settingsScreen);
    }

    public void highscoreClicked(View view) {
        Intent highscoreScreen = new Intent(this, Highscore.class);
        startActivity(highscoreScreen);
    }
}