package com.example.android.calculator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Splash extends Activity {

    TextView splashText;
    LinearLayout splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        MediaPlayer splashSound= MediaPlayer.create(this,R.raw.sound);
        splashSound.start();
        splash = (LinearLayout) findViewById(R.id.splash) ;
        splashText = (TextView) findViewById(R.id.splashText);
        Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);
        splash.startAnimation(alpha);
        Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate);
        splashText.startAnimation(translate);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent main = new Intent("android.intent.action.DEFAULT");
                    startActivity(main);
                    }
                }
            };
        timer.start();


        }
        @Override
        protected void onPause(){
        super.onPause();
        finish();
    }

    }


