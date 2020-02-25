package com.example.radianceregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        androidx.appcompat.widget.Toolbar tb= findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFF3988e1);
        getSupportActionBar().setTitle("ABOUT US");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
