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
    public static HashSet wordHash;

    public GoodGamePlay(WordLoad word, int wordLength, int turnsAllowed){
        setMaxTurns(turnsAllowed);
        words = word;
        try {
            wordHash = words.readFeed();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordHash = words.copyHashSet(wordLength);
        wordList = (String[]) wordHash.toArray(new String[wordHash.size()]);
        currWord = getRandomWord(wordList);
        invisibleWord();
    }

    public String getRandomWord(String[] wordList) {
        random = new Random();
        randomWord = wordList[random.nextInt(wordList.length)];
        return randomWord;
    }

    public void invisibleWord(){
        showLetter = new StringBuilder();
        wrongLetters = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
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
        setMaxTurns(turnsLeft);
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
        wrongLetters.append(C + " ");
        turnsLeft--;
        return true;
    }
}