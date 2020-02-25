package com.pascw.radianceregistration;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.text.TextUtils.isEmpty;

public class Registration extends AppCompatActivity {
    private Button pay;
    private CheckBox FE, SE, TE, BE, Codewars, Recodeit, Shutterup, Quizmaster;
    private EditText Participant1, Participant2, Contact, Email, College, Date;
    private String name1, name2, contact1, mail1, college1, team1, team2;
    private String year="", eventcodewars, eventrecodeit, eventshutterup, eventquizmaster;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3;
    private RadioButton radio1, radio2, radio3, radio4, one, two, three, upinew, cashnew;
    private EditText editText2, editText3;
    private TextView result, yearE;
    private FirebaseAuth mAuth;
    private ProgressBar p;
    int sum = 0;
    float sumPay=0;
    private Toolbar stool;

    FirebaseUser user;
    Calendar c=Calendar.getInstance();
    String[] month={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    int month_int=c.get(Calendar.MONTH);
    int yeartoday=c.get(Calendar.YEAR);
    int date=c.get(Calendar.DATE);
    String todaydate = String.valueOf(date)+"-"+month[month_int]+"-"+String.valueOf(yeartoday);
    FirebaseFirestore fb = FirebaseFirestore.getInstance();
    CollectionReference cb = fb.collection(todaydate);
    ParticipantInfo info = new ParticipantInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FE = (CheckBox) findViewById(R.id.FE);
        stool = findViewById(R.id.stool);
        p = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        SE = (CheckBox) findViewById(R.id.SE);
        TE = (CheckBox) findViewById(R.id.TE);
        BE = (CheckBox) findViewById(R.id.BE);
        pay = (Button) findViewById(R.id.pay);
        upinew = findViewById(R.id.upinew);
        cashnew = findViewById(R.id.cashnew);
        Participant1 = (EditText) findViewById(R.id.Participant1);
        Contact = (EditText) findViewById(R.id.Contact);
        Email = (EditText) findViewById(R.id.Email);
        College = (EditText) findViewById(R.id.College);
        Codewars = (CheckBox) findViewById(R.id.Codewars);
        Recodeit = (CheckBox) findViewById(R.id.Recodeit);
        Quizmaster = (CheckBox) findViewById(R.id.Quizmaster);
        Shutterup = (CheckBox) findViewById(R.id.Shutterup);
        editText2 = findViewById(R.id.name3);
        editText3 = findViewById(R.id.name4);
        radioGroup1 = findViewById(R.id.rg1);
        radioGroup2 = findViewById(R.id.rg2);
        radioGroup3 = findViewById(R.id.rg3);
        radio1 = findViewById(R.id.ind1);
        radio2 = findViewById(R.id.team1);
        radio3 = findViewById(R.id.ind2);
        radio4 = findViewById(R.id.team2);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        result = findViewById(R.id.result);
        yearE = findViewById(R.id.Year);
        setSupportActionBar(stool);
        getSupportActionBar().setTitle("REGISTRATION");
        stool.setTitleTextColor(0xFF3988e1);
        p.setVisibility(View.GONE);
        String TransRefId = RefIDGen();
        String TransId = IDGen();
        //Create instance of EasyUpiPayment
        amo();

        Codewars.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    radioGroup1.setVisibility(View.VISIBLE);
                    radio1.setChecked(true);
                    amo();
                } else {
                    amo();
                    editText2.setText("");
                    radioGroup1.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                }
            }
        });
        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amo();
            }
        });
        radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editText2.setVisibility(View.VISIBLE);
                    amo();
                } else {
                    editText2.setVisibility(View.GONE);
                    amo();
                }
            }
        });
        Recodeit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    radioGroup2.setVisibility(View.VISIBLE);
                    radio3.setChecked(true);
                    amo();
                } else {
                    amo();
                    editText3.setText("");
                    radioGroup2.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                }
            }
        });
        radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amo();
            }
        });
        radio4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    amo();
                    editText3.setVisibility(View.VISIBLE);
                } else {
                    amo();
                    editText3.setVisibility(View.GONE);
                }
            }
        });

        Shutterup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    radioGroup3.setVisibility(View.VISIBLE);
                    one.setChecked(true);
                    amo();
                } else {
                    amo();
                    radioGroup3.setVisibility(View.GONE);
                }
            }
        });
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amo();
            }
        });
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amo();
            }
        });
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                amo();
            }
        });
        Quizmaster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    amo();
                } else {
                    amo();
                }
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = Participant1.getText().toString().trim();
                contact1 = Contact.getText().toString().trim();
                mail1 = Email.getText().toString().trim();
                college1 = College.getText().toString().trim();
                team1 = editText2.getText().toString().trim();
                team2 = editText3.getText().toString().trim();

                Boolean flagCW = true;
                Boolean flagRI = true;
                Boolean flagSU = true;


                if (isEmpty(name1)) {
                    Participant1.setError("This field cannot be empty.");
                    Participant1.requestFocus();
                }
                if (isEmpty(contact1)) {
                    Contact.setError("This field cannot be empty.");
                    Contact.requestFocus();
                } else if (!isContactValid(contact1)) {
                    Contact.setError("Mobile no. is incorrect.");
                    Contact.requestFocus();
                }
                if (isEmpty(mail1)) {
                    Email.setError("This field cannot be empty.");
                    Email.requestFocus();
                } else if (!isEmailValid(mail1)) {
                    Email.setError("Email is incorrect.");
                    Email.requestFocus();
                }
                if (isEmpty(college1)) {
                    College.setError("This field cannot be empty.");
                    College.requestFocus();
                }
                if (Codewars.isChecked()) {
                    if (radio2.isChecked()) {
                        if (isEmpty(team1)) {
                            editText2.setError("This field cannot be empty.");
                            editText2.requestFocus();
                            flagCW = false;
                        } else flagCW = true;
                    }
                }
                if (Recodeit.isChecked()) {
                    if (radio4.isChecked()) {
                        if (isEmpty(team2)) {
                            editText3.setError("This field cannot be empty.");
                            editText3.requestFocus();
                            flagRI = false;
                        } else flagRI = true;
                    }
                }
                if (Shutterup.isChecked()) {
                    if (one.isChecked() || two.isChecked() || three.isChecked()) {
                        flagSU = true;
                    } else {
                        flagSU = false;
                    }
                }
                if(!(FE.isChecked() || SE.isChecked() || TE.isChecked() || BE.isChecked())){
                    yearE.setError("This field cannot be empty.");
                    yearE.requestFocus();
                    year = "";
                }

                if (!isEmpty(name1) && !isEmpty(contact1) && !isEmpty(mail1) && !isEmpty(college1) && isContactValid(contact1) && isEmailValid(mail1) && !year.isEmpty()) {
                    if (sum != 0) {
                        if (flagCW && flagRI && flagSU) {
                            p.setVisibility(View.VISIBLE);
                            info.setParticipant1(name1);
                            info.setCollegename(college1);
                            info.setContactno(contact1);
                            info.setEmail(mail1);
                            info.setYear(year);
                            info.setAmount(sum);
                            Map<String, String> ev = new HashMap<String, String>();
                            if (Codewars.isChecked()) {
                                if (radio2.isChecked())
                                    ev.put("Code Wars", team1);
                                if (radio1.isChecked())
                                    ev.put("Code Wars", " ");
                            }
                            if (Recodeit.isChecked()) {
                                if (radio4.isChecked()) {
                                    ev.put("Recode It", team2);
                                }
                                if (radio3.isChecked())
                                    ev.put("Recode It", " ");
                            }
                            if (Shutterup.isChecked()) {
                                if (one.isChecked()) {
                                    ev.put("Shutter UP", "one");
                                } else if (two.isChecked()) {
                                    ev.put("Shutter Up", "two");
                                } else {
                                    ev.put("Shutter Up", "three");
                                }
                            }
                            if (Quizmaster.isChecked()) {
                                ev.put("QuizMaster", " ");
                            }
                            info.setMapi(ev);
                            info.setPaystatus("UPI");
                            UPI_trans();
                            //Register Listener for Events
                            p.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(Registration.this, "PLEASE SELECT ATLEAST ONE EVENT", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void amo() {
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;

        if (Codewars.isChecked()) {
            eventcodewars = "Codewars";

            if (radio1.isChecked()) {
                sum1 = sum1 + 80;

            }
            if (radio2.isChecked()) {
                sum1 = sum1 + 100;
            }
        }
        if (Recodeit.isChecked()) {
            eventrecodeit = "RecodeIt";

            if (radio3.isChecked())
                sum2 = sum2 + 80;
            if (radio4.isChecked())
                sum2 = sum2 + 100;
        }
        if (Quizmaster.isChecked()) {
            eventquizmaster = "Quiz Master";

            sum3 = sum3 + 80;
        }

        if (Shutterup.isChecked()) {
            eventshutterup = "ShutterUp";

            if (one.isChecked())
                sum4 = sum4 + 50;
            if (two.isChecked())
                sum4 = sum4 + 80;
            if (three.isChecked()) {
                sum4 = sum4 + 100;
            }
        }
        sum = sum1 + sum2 + sum3 + sum4;
        sumPay=sum;
        result.setText(String.valueOf(sum)+" Rs");
    }

    public void onYearClicked(View view) {
        switch (view.getId()) {

            case R.id.FE:
                yearE.setError(null);
                SE.setChecked(false);
                TE.setChecked(false);
                BE.setChecked(false);
                year = "FE";
                break;

            case R.id.SE:
                yearE.setError(null);
                FE.setChecked(false);
                TE.setChecked(false);
                BE.setChecked(false);
                year = "SE";
                break;

            case R.id.TE:
                yearE.setError(null);
                SE.setChecked(false);
                FE.setChecked(false);
                BE.setChecked(false);
                year = "TE";
                break;

            case R.id.BE:
                yearE.setError(null);
                SE.setChecked(false);
                TE.setChecked(false);
                FE.setChecked(false);
                year = "BE";
                break;

            default:
                FE.setChecked(false);
                SE.setChecked(false);
                FE.setChecked(false);
                BE.setChecked(false);
                year = "";

        }
    }

    boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isContactValid(String contact) {
        if (contact.length() == 10)
            return true;
        else
            return false;
    }
    private void UPI_trans(){
        String TransRefId = RefIDGen();
        String TransId = IDGen();

        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa("9404094923@ybl")
                .setPayeeName("Pranav Kadam")
                .setTransactionId(TransId)
                .setTransactionRefId(TransRefId)
                .setDescription("Radiance 2020")
                .setAmount(Float.toString(sumPay))
                .build();
        //Register Listener for Events
        easyUpiPayment.setPaymentStatusListener(new PaymentStatusListener() {
            @Override
            public void onTransactionCompleted(TransactionDetails transactionDetails) {
                // Transaction Completed

            }

            @Override
            public void onTransactionSuccess() {
                // Payment Success

                Toast.makeText(Registration.this, "Successful", Toast.LENGTH_SHORT).show();
                Mail();
            }

            @Override
            public void onTransactionSubmitted() {
                // Payment Pending
                Toast.makeText(Registration.this, "Pending | Submitted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransactionFailed() {
                // Payment Failed
                Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransactionCancelled() {
                // Payment Cancelled by User
                Toast.makeText(Registration.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAppNotFound() {
                Toast.makeText(Registration.this, "No App Found", Toast.LENGTH_SHORT).show();
            }
        });
        easyUpiPayment.startPayment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_recents) {
            mAuth.signOut();
            Toast toast = Toast.makeText(Registration.this, "SIGNED OUT", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(Registration.this, Sign.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    void Mail() {
        user = mAuth.getCurrentUser();
        if (user != null && info != null) {
            String name = user.getEmail();
            String collection = info.getCollegename().toLowerCase();
            cb.document(name).collection(collection).document().set(info).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {


                                String message = "";

                                String email1 = info.getEmail().toString();
                                String name1 = info.getParticipant1().toString();
                                String college = info.getCollegename().toString();
                                Map<String, String> mp = info.getMapi();
                                String events = " ";
                                for (Map.Entry<String, String> entry : mp.entrySet()) {
                                    events = events + entry.getKey() + " " + entry.getValue() + "\n";
                                }
                                String subject = "PASCW:- RADIANCE-2020 receipt";
                                message = "Dear " + name1 + ",\n\n" + "Greetings from PASCW!!\n" +
                                        "\n" +
                                        "You have successfully registered for RADIANCE-2020\n" + "\n\n" +
                                        "RECEIPT\n" +
                                        "Name:-" + name1 + "\n" +
                                        "College Name:-" + college + "\n" +
                                        "Events:-" + events + "\n" +
                                        "\n" +
                                        "All the best!!\n" +
                                        "PICT ACM-W Student Chapter.";

                                //Creating SendMail object
                                SendEmail sm = new SendEmail(Registration.this, email1, subject, message);

                                //Executing sendmail to send email
                                sm.execute();


                                Toast.makeText(Registration.this, "User Registered into Database", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registration.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    String RefIDGen() {
        StringBuilder RefId = new StringBuilder();
        final int min = 1000;
        final int max = 9999;
        int i = 0;
        while (i < 3) {
            final int random = new Random().nextInt((max - min) + 1) + min;
            RefId.append(random);
            i++;
        }
        return RefId.toString();
    }

    String IDGen() {
        int i = 0;
        StringBuilder Id = new StringBuilder();
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Random r = new Random();
        Id.append(alphabet.charAt(r.nextInt(N)));
        final int min = 1000;
        final int max = 9999;
        while (i < 5) {
            final int random = new Random().nextInt((max - min) + 1) + min;
            Id.append(random);
            i++;
        }
        final int min1 = 10;
        final int max1 = 99;
        final int random1 = new Random().nextInt((max1 - min1) + 1) + min1;
        Id.append(random1);
        return Id.toString();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Registration.this, Boommain.class);
        startActivity(i);
        finish();
    }
}
