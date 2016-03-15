package io.wellbeings.anatome;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bettinaalexieva on 11/03/2016.
 */
public class DisplayAppointment extends AppCompatActivity {

    private Button mBackFromBalloon;
    private TextView mBalloonTime, mBalloonDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_appointment_details);

        mBackFromBalloon = (Button)findViewById(R.id.backFromBalloon);
        mBalloonTime = (TextView)findViewById(R.id.balloonTime);
        mBalloonDate = (TextView)findViewById(R.id.balloonDate);

        AssetManager assetManager = getAssets();
        Typeface customFont = Typeface.createFromAsset(assetManager, "fonts/champagne.ttf");

        //mBackFromBalloon.setTypeface(customFont);
        mBalloonTime.setTypeface(customFont);
        mBalloonDate.setTypeface(customFont);

        mBackFromBalloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainScroll.class);
                startActivity(intent);

            }
        });

    }
}
