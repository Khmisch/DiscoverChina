package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.manager.LanguageManager;
import com.example.myapplication.service.BackgroundMusicService;

public abstract class BaseActivity extends AppCompatActivity {
    private Intent backgroundMusicIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusicIntent = new Intent(this, BackgroundMusicService.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        startBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopBackgroundMusic();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        LanguageManager.loadLanguage(this);
    }

    protected void startBackgroundMusic() {
        startService(backgroundMusicIntent);
    }

    protected void stopBackgroundMusic() {
        stopService(backgroundMusicIntent);
    }


    public void callHomeActivity(Context context) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
