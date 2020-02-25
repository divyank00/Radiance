package com.example.radianceregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.r0adkll.slidr.Slidr;

public class FifthPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_page);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        Slidr.attach(this);
    }
}
