package com.uds.joketeller;

import java.util.Random;

public class Joke {
    String[] jokes = {

    };

    public String getJokes() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}