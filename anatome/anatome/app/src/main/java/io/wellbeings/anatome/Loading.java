package io.wellbeings.anatome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import static io.wellbeings.anatome.R.drawable.gyro_animation;

/**
 * Created by bettinaalexieva on 25/03/2016.
 */
public class Loading extends Activity {

    private boolean backbtnPress;
    private static final int SPLASH_DURATION = 1600;
    private Handler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);


        ImageView mImageViewFilling = (ImageView) findViewById(R.id.eyeball_image);
        ((AnimationDrawable) mImageViewFilling.getBackground()).start();


        myHandler = new Handler();

        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {


                //ImageView mImageViewFilling = (ImageView) findViewById(R.id.eyeball_image);
                //((AnimationDrawable) mImageViewFilling.getBackground()).start();

                //finish();

                if(!backbtnPress) {

                    Intent i = new Intent(Loading.this, MainScroll.class);
                    Loading.this.startActivity(i);
                }
            }
        }, SPLASH_DURATION);
    }

    @Override
    public void onBackPressed() {
        backbtnPress = true;
        super.onBackPressed();
    }
}





