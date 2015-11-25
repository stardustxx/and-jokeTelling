package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Pair;

import com.example.eric.myapplication.backend.myApi.model.Joke;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() throws ExecutionException, TimeoutException {
        super(Application.class);
        try {
            Pair<Context, Integer> pair = new Pair(this, 0);
            EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
            endpointAsyncTask.execute(pair);
            Joke joke = endpointAsyncTask.get(21, TimeUnit.SECONDS);
            assertNotNull("Joke", joke);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}