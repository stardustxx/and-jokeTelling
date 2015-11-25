package com.example;

public class JokeClass {
    String question, answer;
    Joke joke;
    int count = -1;

    public Joke getJokes() {
        if (count != -1){
            switch (count){
                case 0:
                    question = "How many South Americans does it take to change a lightbulb?";
                    answer = "A Brazilian";
                    break;
                case 1:
                    question = "What kind of bagel can fly?";
                    answer = "A plain bagel";
                    break;
                case 2:
                    question = "Where do animals go when their tails fall off?";
                    answer = "The retail store";
                    break;
                default:
                    System.out.println("There is nothing");
            }
        }
        else {
            question = "There is no joke";
            answer = " ";
        }
        joke = new Joke(question, answer);
        return joke;
    }

    public void setCount(int count){
        this.count = count;
    }

}
