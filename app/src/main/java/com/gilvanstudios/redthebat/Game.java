package com.gilvanstudios.redthebat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.MobileAds;


import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;


public class Game extends AppCompatActivity {

    private InterstitialAd interstitial;
    private AdRequest adRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // get last bestScore from shared preferences
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int currentHighScore = sharedPref.getInt("best", 0);

        GamePanel panel = new GamePanel(this, currentHighScore);


        // set listener for handling new high score
        panel.setHighScoreListener(new GamePanel.HighScoreListener() {
            @Override
            public void onHighScoreUpdated(int best) {
                // code to handle updates

                // Update shared preferences
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("best", best);
                editor.commit();
            }

        });



        setContentView(panel);

        //Main AD
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-4957836647276069/5689591289");

        //Create request
        AdRequest adRequest = new AdRequest.Builder().build();

        //Loading start
        interstitial.loadAd(adRequest);

        //Once loaded, display ad:
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

    }

    public void displayInterstitial(){

        if(interstitial.isLoaded()){
            interstitial.show();
        }

    }



}
