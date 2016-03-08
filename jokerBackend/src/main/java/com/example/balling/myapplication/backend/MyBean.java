package com.example.balling.myapplication.backend;

import com.example.joker;
/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String jokeString;

    public String getData() {
        joker jokerMaker = new joker();
        return jokerMaker.getJoke();
    }
}