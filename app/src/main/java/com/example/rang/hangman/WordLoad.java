package com.example.rang.hangman;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.HashSet;

public class WordLoad {
    Context mycontext;
    XmlPullParser parser;
    HashSet<String> list = new HashSet<>();
    HashSet<String> cloneSet = new HashSet <>();

    public WordLoad(Context context){
        mycontext = context;
        parser = mycontext.getResources().getXml(R.xml.words);
    }

HashSet <String> readFeed() throws XmlPullParserException, IOException {
    while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
        if (parser.getEventType() == XmlPullParser.START_TAG) {
            String name = parser.getName();
            // Starts by looking for the item tag
            if (name.equals("item")) {
                if(parser.next() == XmlPullParser.TEXT){
                    String item = parser.getText();
                    list.add(item);
                }
            }
        }
        parser.next();
    }
    return list;
}

    public HashSet copyHashSet(int wordLength){
        for(String items: list){
            Log.i("WordLoad", "In for loop copy hashset" + items);
            if (items.length() == wordLength){
                cloneSet.add(items);
                Log.i("WordLoad", "items = " + items);
            }
        }
        return cloneSet;
    }
}
