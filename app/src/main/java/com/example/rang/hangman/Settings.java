package com.example.rang.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class Settings extends AppCompatActivity {

    public SeekBar wordLength;
    public TextView lengthWord;
    public SeekBar numberOfTurns;
    public TextView turnsLength;
    public Button evilOnOff;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        pref = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);

        seekbarVariables();

        // Initialize the TextViews
        lengthWord.setText(wordLength.getProgress() + "/" + wordLength.getMax());
        turnsLength.setText(numberOfTurns.getProgress() + "/" + numberOfTurns.getMax());

        wordLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int wordProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                wordProgress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                lengthWord.setText(wordProgress + "/" + seekBar.getMax());
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("word", wordProgress);
                editor.commit();
            }
        });

        numberOfTurns.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int turnsProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                turnsProgress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                turnsLength.setText(turnsProgress + "/" + seekBar.getMax());
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("turns", turnsProgress);
                editor.commit();
            }
        });
    }


    public void seekbarVariables(){
        wordLength = (SeekBar) findViewById(R.id.lengthSeekbar);
        lengthWord = (TextView) findViewById(R.id.textviewLengthWord);
        numberOfTurns = (SeekBar) findViewById(R.id.turnsSeekbar);
        turnsLength = (TextView) findViewById(R.id.textviewlengthTurns);
        evilOnOff = (Button) findViewById(R.id.onOff);
    }

    public void saveClicked(View view) {
        Intent homeScreen = new Intent(this, GamePlayActivity.class);
        startActivity(homeScreen);
    }
}