package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClickLoginButton(View v) {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    public void onClickRegisterButton(View v) {
        Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(i);
    }
}
