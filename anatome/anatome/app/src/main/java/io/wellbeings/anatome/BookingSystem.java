package io.wellbeings.anatome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class BookingSystem extends AppCompatActivity {

    private TextView mSetDate, mSetTime, mBookingTitle;
    private Button mBackFromBooking, mBook;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);


        mSetDate = (TextView) findViewById(R.id.setDate);
        mSetTime = (TextView) findViewById(R.id.setTime);
        mBackFromBooking = (Button) findViewById(R.id.backFromBooking);
        mBook = (Button) findViewById(R.id.bookFromBooking);
        mBookingTitle = (TextView) findViewById(R.id.bookingTitle);

        AssetManager assetManager = getAssets();
        Typeface customFont = Typeface.createFromAsset(assetManager, "fonts/champagne.ttf");

        mSetDate.setTypeface(customFont);
        mSetTime.setTypeface(customFont);
        mBackFromBooking.setTypeface(customFont);
        mBook.setTypeface(customFont);
        mBookingTitle.setTypeface(customFont);

        mBackFromBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainScroll();
                // postBooking();
            }
        });

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBooking();
                disableBookButton();
                switchView();
            }
        });

        setCurrentDateOnView();
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            setCurrentDateOnView();
        }
    };

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setCurrentDateOnView();
        }
    };

    // true - 24-hour format
    public void timeOnClick(View view) {

        new TimePickerDialog(BookingSystem.this, TimePickerDialog.THEME_HOLO_LIGHT, time,
                c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false).show();

    }

    public void dateOnClick(View view) {

        DatePickerDialog dp = new DatePickerDialog(BookingSystem.this, TimePickerDialog.THEME_HOLO_LIGHT, date,
                c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        Date newdate = c.getTime();
        dp.getDatePicker().setMinDate(newdate.getTime());
        dp.getDatePicker().setMaxDate(newdate.getTime() + 1209600000);
        dp.show();

    }

    // Formats date & time
    public void setCurrentDateOnView() {

        String dateFormat = "dd-MM-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        mSetDate.setText(sdf.format(c.getTime()));

        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.UK);
        mSetTime.setText(stf.format(c.getTime()));
    }

    public void postBooking() {
        //get data from booking page
        String date = mSetDate.getText().toString();
        String time = mSetTime.getText().toString();
        String newDate = null;

        System.out.println("///////////////" + date);
        System.out.println("///////////////" + time);

        SimpleDateFormat initial = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat needed = new SimpleDateFormat("yyyy-MM-dd");

        try {
            newDate = needed.format(initial.parse(date));

            System.out.println("///////////////" + newDate);
        }catch(Exception e) {
            System.out.println("Error: Unable to parse date");
        }
        //defines fact we are posting an appointment
        String param = "app";

        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("time", time));
        data.add(new BasicNameValuePair("date", newDate));

        DatabaseUtility db = new DatabaseUtility();

        db.addToDb(data, param);
        Toast.makeText(BookingSystem.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
    }

    private void disableBookButton() {

        mBook.setEnabled(false);
        mBook.setTextColor(Color.parseColor("#BBBBBB"));
    }

    private void switchView() {

        setContentView(R.layout.test_layout);
        Button backBtn = (Button)findViewById(R.id.backFromBooked);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainScroll();
            }
        });
    }

    private void toMainScroll() {
        Intent i = new Intent(BookingSystem.this, MainScroll.class);
        startActivity(i);
    }
}
