package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * A registration screen that offers the opportunity to register for the app
 */

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Finish writing method

        // Relating to the registration button
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        // Relating to the cancel button
        Button mCancelRegistration = (Button) findViewById(R.id.registration_cancel_button);
        mCancelRegistration.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRegistration();
            }
        });
    }

    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    private void attemptRegistration() {
        // TODO: Write method
    }

    /**
     * Cancels registration and returns to the welcome screen
     */
    private void cancelRegistration() {
        // TODO: Finish writing method
        setContentView(R.layout.activity_welcome);
    }
}
