package com.uds.joketeller;

import java.util.Random;

public class Joke {
    String[] jokes = {
            "I ate a clock yesterday, it was very time-consuming.",
            "Have you played the updated kids' game? I Spy With My Little Eye . . . Phone.",
            "A perfectionist walked into a bar...apparently, the bar wasn’t set high enough.",
            "A computer once beat me at chess, but it was no match for me at kick boxing.",
            "As long as there are tests, there will be prayer in schools.",
            "What did one ocean say to the other ocean? Nothing, they just waved."
    };

    public String getJokes() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}