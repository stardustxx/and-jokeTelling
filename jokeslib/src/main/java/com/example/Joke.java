package com.example;

public class Joke {

    String jokeQuestion, jokeAnswer;

    public Joke(){

    }

    public Joke(String jokeQuestion, String jokeAnswer) {
        this.jokeQuestion = jokeQuestion;
        this.jokeAnswer = jokeAnswer;
    }

    public String getJokeQuestion() {
        return jokeQuestion;
    }

    public String getJokeAnswer() {
        return jokeAnswer;
    }
}
