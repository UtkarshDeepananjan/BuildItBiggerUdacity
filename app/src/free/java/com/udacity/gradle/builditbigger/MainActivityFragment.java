package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.uds.displayjokesactivity.DisplayJokesActivity;


public class MainActivityFragment extends Fragment {
    private final boolean mIsTesting = false;
    private String mJoke;
    private ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;
    private MainActivity mainActivity;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity)
            mainActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);
        AdView mAdView = root.findViewById(R.id.adView);
        Button jokeButton = root.findViewById(R.id.jokeButton);
        progressBar = root.findViewById(R.id.progressBar);
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd = new InterstitialAd(mainActivity);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                displayJokeActivity();
            }
        });
        mAdView.loadAd(adRequest);


        jokeButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            prepareJoke();
        });
        return root;
    }

    private void prepareJoke() {
        EndtPointAsyncTask endtPointAsyncTask = new EndtPointAsyncTask((EndtPointAsyncTask.OnEventListener<String>) joke -> {
            mJoke = joke;
            progressBar.setVisibility(View.GONE);
            if (!mIsTesting) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }


        });
        endtPointAsyncTask.execute();
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