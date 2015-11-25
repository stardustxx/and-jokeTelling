package com.example.Eric.myapplication.backend;

import com.example.Joke;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;
    private Joke joke;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public Joke getJoke(){
        return joke;
    }

    public void setJoke(Joke joke){
        this.joke = joke;
    }
}