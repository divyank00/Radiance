package com.pascw.radianceregistration;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class cash extends AppCompatActivity {
    FirebaseAuth mAuth;

    FirebaseUser user;
    Calendar c=Calendar.getInstance();
    String[] month={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    int month_int=c.get(Calendar.MONTH);
    int yeartoday=c.get(Calendar.YEAR);
    int date=c.get(Calendar.DATE);
    String todaydate = String.valueOf(date)+"-"+month[month_int]+"-"+String.valueOf(yeartoday);
    FirebaseFirestore fb=FirebaseFirestore.getInstance();
   CollectionReference cb= fb.collection(todaydate);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        Intent i = getIntent();
        final ParticipantInfo info = (ParticipantInfo) i.getSerializableExtra("Object");
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        if(user!=null)
        {
            String name=user.getEmail().toString();
            cb.document(name).collection(info.getCollegename().toString()).document().set(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        String message = "";
                        String events="";

                        Resources res = getResources();
                        Map<String,String> mp = new HashMap<>();
                        String email1 = info.getEmail().toString();
                        String name1 = info.getParticipant1().toString();
                        String college = info.getCollegename().toString();
                        mp=info.getMapi();
                        for (Map.Entry<String, String> entry : mp.entrySet()) {
                            events = events + entry.getKey() + "  " + entry.getValue()+"\n";
                        }
                        String subject = "PASCW:- RADIANCE-2020 receipt";
                        message = "Dear " + name1 + ",\n\n" + "Greetings from PASCW!!\n" +
                                "\n" +
                                "You have successfully registered for RADIANCE-2020\n" + "\n\n" +
                                "RECEIPT\n" +
                                "Name:- " + name1 + "\n" +
                                "College Name:- " + college + "\n" +
                                "Events:\n" + events + "\n" +
                                "\n" +
                                "All the best!!\n" +
                                "PICT ACM-W Student Chapter.";

                        //Creating SendMail object
                        SendEmail sm = new SendEmail(cash.this, email1, subject, message);

                        //Executing sendmail to send email
                        sm.execute();


                        Toast.makeText(cash.this, "User Registered into Database", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(cash.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(cash.this,Boommain.class);
        startActivity(intent);
        finish();
    }
}
