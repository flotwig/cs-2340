package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.NoSuchElementException;

import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;

/**
 * A registration screen that offers the opportunity to register for the app
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPassword;
    private EditText mId;
    private Spinner mRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mName = (EditText) findViewById(R.id.register_name);
        mPassword = (EditText) findViewById(R.id.register_password);
        mId = (EditText) findViewById(R.id.register_username);
        mRole = (Spinner) findViewById(R.id.account_type_spinner);

        ArrayAdapter<UserRole> standings =
            new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserRole.values());

        standings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRole.setAdapter(standings);
    }

    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     * @param v Register button
     */
    @SuppressWarnings("OverlyLongMethod")
    public void onClickRegisterButton(View v) {
        String name = mName.getText().toString();
        String id = mId.getText().toString();
        String password = mPassword.getText().toString();
        UserRole role = (UserRole) mRole.getSelectedItem();
        View errorView = null;

        mName.setError(null);
        mPassword.setError(null);
        mId.setError(null);

        try {
            User.validatePassword(password);
        } catch (UserInputException e) {
            mPassword.setError(e.getMessage());
            errorView = mPassword;
        }

        try {
            User.validateId(id);
        } catch (UserInputException e) {
            mId.setError(e.getMessage());
            errorView = mId;
        }

        try {
            User.validateName(name);
        } catch (UserInputException e) {
            mName.setError(e.getMessage());
            errorView = mName;
        }

        if (errorView != null) {
            errorView.requestFocus();
        } else {
            // no errors, add the user & log them in if the username doesn't already exist
            try {
                Model.getInstance().getUserById(id);
                mId.setError(getString(R.string.error_username_in_use));
                mId.requestFocus();
            } catch (NoSuchElementException e) {
                User user = new User(name, id, password, role);
                Model.getInstance().addUser(user);
                Model.getInstance().setCurrentUser(user);
                launchMainActivity();
                finish();
            }
        }
    }

    private void launchMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainDrawerActivity.class);
        startActivity(i);
    }
}
