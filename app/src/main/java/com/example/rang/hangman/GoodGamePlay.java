package com.example.rang.hangman;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class GoodGamePlay{
    WordLoad words;
    public int turnsLeft;
    StringBuilder showLetter;
    StringBuilder wrongLetters;
    String[] wordList;
    String randomWord;
    String currWord;
    Random random;
    int turns;
    public static HashSet wordHash;

    public GoodGamePlay(WordLoad word, int wordLength, int turnsAllowed){
        turns = turnsAllowed;
        setMaxTurns(turnsAllowed);
        words = word;
        try {
            wordHash = words.readFeed();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get list with words from copyHashSet
        wordHash = words.copyHashSet(wordLength);
        // convert hashset to string array
        wordList = (String[]) wordHash.toArray(new String[wordHash.size()]);
        currWord = getRandomWord(wordList);
        invisibleWord();
    }


    public String getRandomWord(String[] wordList) {
        random = new Random();
        // get random word from the wordList and return
        randomWord = wordList[random.nextInt(wordList.length)];
        return randomWord;
    }

    public void invisibleWord(){
        showLetter = new StringBuilder();
        wrongLetters = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
            // show * based on the length of the current word(currWord)
            showLetter.append("*");
        }
    }

    public void setMaxTurns(int turns){
        turnsLeft = turns;
    }

    public boolean noTurnsLeft() {
        return (turnsLeft == 0);
    }

    public boolean checkChar(Character C) {
        return currWord.contains(C.toString());
    }

    public boolean guess(Character C){
        if (checkChar(C)) {
            for (int i = 0; i < currWord.length(); i++) {
                // if letter in word, show letter at i place
                if (checkChar(i, C)) {
                    showLetter.setCharAt(i, C);
                }
            }
            return true;
        }
        else {
            wrongChar(C);
            return false;
        }
    }

    public boolean checkChar(int i, Character C){
        return currWord.charAt(i) == C;
    }

    public void restart() {
        currWord = getRandomWord(wordList);
        setMaxTurns(turns);
    }

    public boolean checkWord() {
        return (showLetter.toString().equals(currWord));
    }

    public boolean wrongChar(Character C){
        for (int i = 0; i < wrongLetters.length(); i++){
            Character wrongLetter = wrongLetters.charAt(i);
            if(C == wrongLetter){
                return false;
            }
        }
        // add wrongLetter on screen
        wrongLetters.append(C + " ");
        // minus one turn
        turnsLeft--;
        return true;
    }
}