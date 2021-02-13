package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import com.uds.displayjokesactivity.DisplayJokesActivity;

public class MainActivityFragment extends Fragment {
    private boolean mIsTesting = false;
    private String mJoke;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);
        Button jokeButton = root.findViewById(R.id.jokeButton);
        progressBar = root.findViewById(R.id.progressBar);

        jokeButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            if (!mIsTesting)
            prepareJoke();
        });
        return root;
    }

    private void prepareJoke() {
        EndtPointAsyncTask getJokeAsyncTask = new EndtPointAsyncTask((EndtPointAsyncTask.OnEventListener<String>) joke -> {
            mJoke = joke;
            progressBar.setVisibility(View.GONE);
            if (!mIsTesting) {
                displayJokeActivity();
            }


        });
        getJokeAsyncTask.execute();
    }

    private void displayJokeActivity() {
        Intent intent = new Intent(getActivity(), DisplayJokesActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, mJoke);
        startActivity(intent);
    }

    @VisibleForTesting
    public String getJoke() {
        return mJoke;
    }

    @VisibleForTesting
    public void setJoke(String joke) {
        mJoke = joke;
    }


}