package com.example.eric.jokesprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokeQStoryText, jokeAnswerText;
    String jokeQuestionExtra, jokeAnswerExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);

        Intent intent = getIntent();
        jokeQuestionExtra = intent.getStringExtra("jokeQuestion");
        jokeAnswerExtra = intent.getStringExtra("jokeAnswer");

        jokeQStoryText = (TextView) findViewById(R.id.joke_question);
        jokeAnswerText = (TextView) findViewById(R.id.joke_answer);

        jokeQStoryText.setText(jokeQuestionExtra);
        jokeAnswerText.setText(jokeAnswerExtra);

    }
}
