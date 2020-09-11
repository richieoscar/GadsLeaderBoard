package com.example.gadspracticeproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static String TAG = SplashActivity.class.getName();
    private static  long SLEEP_TIME = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //remove title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes notification bar

        setContentView(R.layout.splash_layout);

        //start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }
    private  class IntentLauncher extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                //sleeping
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
            //start main activity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
