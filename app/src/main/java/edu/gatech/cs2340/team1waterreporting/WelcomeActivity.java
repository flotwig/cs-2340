package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This activity is what first greets users when they enter the app.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * onClick handler to transition to login screen.
     * @param v Login button
     */
    @SuppressWarnings("UnusedParameters")
    public void onClickLoginButton(View v) {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    /**
     * onClick handler to transition to register screen
     * @param v Register button
     */
    @SuppressWarnings("UnusedParameters")
    public void onClickRegisterButton(View v) {
        Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(i);
    }
}
