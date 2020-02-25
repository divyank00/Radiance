package com.example.radianceregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        androidx.appcompat.widget.Toolbar tb= findViewById(R.id.tb);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("CONTACT US");
        tb.setTitleTextColor(0xFF3988e1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void linkedin(View view)
    {
        Uri uri = Uri.parse("https://www.linkedin.com/in/pict-acm-student-chapter-09004a132");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void insta(View view)
    {
        Uri uri = Uri.parse("http://instagram.com/acm.pict");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void web(View view)
    {
        Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
        myWebLink.setData(Uri.parse("http://pict.acm.org/radiance"));
        startActivity(myWebLink);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
