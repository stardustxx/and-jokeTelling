/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Eric.myapplication.backend;

import com.example.Joke;
import com.example.JokeClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import sun.rmi.runtime.Log;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.Eric.example.com",
    ownerName = "backend.myapplication.Eric.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "joke")
    public MyBean joke(@Named("num") int num){
        Joke joke;

        JokeClass jokeClass = new JokeClass();
        jokeClass.setCount(num);
        joke = jokeClass.getJokes();

        MyBean response = new MyBean();
        response.setJoke(joke);
        return response;
    }

}
