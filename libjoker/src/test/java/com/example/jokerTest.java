package com.example;

import static org.junit.Assert.*;

/**
 * This files is to test jokes made by Jokes class
 * Created by Levi balling on 3/2/2016.
 */
public class jokerTest {

    @org.junit.Test
    public void testGetJoke() throws Exception {
        joker jokeMaker = new joker();
        //this test is just to make sure that we won't go past the limits of the array
        for (int i = 0; i < 1000; i++) {
            String jokeStr = jokeMaker.getJoke();
            assertNotNull(jokeStr);
            assertFalse(jokeStr.isEmpty());
        }

    }
}