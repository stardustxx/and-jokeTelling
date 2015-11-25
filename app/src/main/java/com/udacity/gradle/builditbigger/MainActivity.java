package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eric.jokesprovider.JokeActivity;
import com.example.eric.myapplication.backend.myApi.MyApi;
import com.example.eric.myapplication.backend.myApi.model.Joke;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    int counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        // Get jokes and ready from endpoint and pass them to new activity via intent
        counter += 1;
        if (counter > 2) {
            counter = 0;
        }
        Pair<Context, Integer> pair = new Pair(MainActivity.this, counter);
        EndpointAsyncTask jokeTask = new EndpointAsyncTask();
        if (jokeTask.getStatus() == AsyncTask.Status.RUNNING){
            jokeTask.cancel(true);
        }
        jokeTask.execute(pair);
    }
}

class EndpointAsyncTask extends AsyncTask<Pair<Context, Integer>, Void, Joke>{
    private static MyApi myApiService = null;
    private Context context;
    int counter = 0;

    @Override
    protected Joke doInBackground(Pair<Context, Integer>... params) {
        if (myApiService == null){  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
            // options for running against local devappserver
            // - 10.0.2.2 is localhost's IP address in Android emulator
            // - turn off compression when running against local devappserver
            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                    request.setDisableGZipContent(true);
                }
            });
            myApiService = builder.build();
        }

        context = params[0].first;
        counter = params[0].second;

        Joke joke = new Joke();

        try {
            joke = myApiService.joke(counter).execute().getJoke();
            Log.d("Joke Question", joke.getJokeQuestion());
            Log.d("Joke Answer", joke.getJokeAnswer());
        }
        catch (IOException e){
            Log.d("Endpoint Error", e.toString());
        }

        return joke;
    }

    @Override
    protected void onPostExecute(Joke result) {
        super.onPostExecute(result);
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("jokeQuestion", result.getJokeQuestion());
        intent.putExtra("jokeAnswer", result.getJokeAnswer());
        context.startActivity(intent);
    }
}
