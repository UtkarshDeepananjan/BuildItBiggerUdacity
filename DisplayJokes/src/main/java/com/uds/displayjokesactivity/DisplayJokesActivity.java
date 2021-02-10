package com.uds.displayjokesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayJokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_jokes);
        TextView jokesTextView = findViewById(R.id.tv_jokes);
        String joke = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        jokesTextView.setText(joke);
    }
}