package com.ashitakalax.jodis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JoDisActivity extends AppCompatActivity {

    public static final String JODIS_JOKE_STRING = "JokeStringKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jo_dis);

        TextView jokeTextView = (TextView)this.findViewById(R.id.jokeTextView);

        Intent jokeIntent = this.getIntent();
        String joke = String.valueOf(R.string.default_joke);//default joke
        if(jokeIntent != null && jokeIntent.getStringExtra(JODIS_JOKE_STRING) != null)
        {
            joke = jokeIntent.getStringExtra(JODIS_JOKE_STRING);

        }

        if(jokeTextView != null)
        {
            jokeTextView.setText(joke);
        }
        //start timer to return to the activity that started this one
    }
}
