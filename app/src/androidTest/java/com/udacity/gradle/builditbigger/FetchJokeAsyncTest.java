package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

/**
 * Created by balling on 3/8/2016.
 * Tests the Fetch Joke Async Task
 */
public class FetchJokeAsyncTest extends AndroidTestCase {

    public void testFetchJokeAsyncTask() throws Exception {


        FetchJokeAsyncTask jokeFetcher = new FetchJokeAsyncTask(null);
        jokeFetcher.execute();
        //wait for response
        jokeFetcher.get(10000, TimeUnit.MILLISECONDS);

        //check if the next activity was kicked off

        String joke = jokeFetcher.getJokeString();
        assertNotNull(joke);
        assertFalse(joke.isEmpty());

    }
}