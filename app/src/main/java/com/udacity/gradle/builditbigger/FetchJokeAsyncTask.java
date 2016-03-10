package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ashitakalax.jodis.JoDisActivity;
import com.example.balling.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by balling on 3/7/2016.
 * This class will fetch a joke asynchronously from the backend server
 */
public class FetchJokeAsyncTask extends AsyncTask<Void, Void, String>{
    private static MyApi myApiService = null;

    private Context mContext;
    private String mJokeString;

    public FetchJokeAsyncTask(Context context)
    {
        this.mContext = context;
    }
    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        this.mJokeString = joke;
        if(this.mContext != null) {
            Intent jodisIntent = new Intent(this.mContext, JoDisActivity.class);
            jodisIntent.putExtra(JoDisActivity.JODIS_JOKE_STRING, joke);
            this.mContext.startActivity(jodisIntent);
        }
    }

    /**
     * This is to validate getting the joke
     * @return joke string
     */
    public String getJokeString() {
        return mJokeString;
    }
}
