package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;


/**
 * A fragment which handles the editing of a user object.
 */
public class EditProfileFragment extends Fragment {
    private static final String ARG_USER = "user";

    private EditText mName;
    private EditText mPassword;
    private EditText mId;
    private Spinner mRole;

    private User mUser;

    /**
     * Creates an empty EditProfileFragment.
     */
    public EditProfileFragment() {

    }

    /**
     * New instance of form to edit a user.
     *
     * @param user User
     * @return A new instance of fragment EditProfileFragment.
     */
    public static EditProfileFragment newInstance(User user) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        if (user != null) {
            args.putSerializable(ARG_USER, user);
        } else {
            args.putSerializable(ARG_USER, Model.getInstance().getCurrentUser());
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_edit_profile));

        Button mSaveButton = (Button) v.findViewById(R.id.edit_profile_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveButton(v);
            }
        });

        mName = (EditText) v.findViewById(R.id.register_name);
        mPassword = (EditText) v.findViewById(R.id.register_password);
        mId = (EditText) v.findViewById(R.id.register_username);
        mRole = (Spinner) v.findViewById(R.id.account_type_spinner);

        ArrayAdapter<UserRole> standings = new ArrayAdapter(super.getContext(),android.R.layout.simple_spinner_item, UserRole.values());
        standings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRole.setAdapter(standings);

        if (mUser != null) {
            // we are editing an existing user, fill in their data except password
            mName.setText(mUser.getName());
            mId.setText(mUser.getId());
            mRole.setSelection(standings.getPosition(mUser.getUserRole()));
        }

        return v;
    }

    /**
     * onClick handler that will save any edited fields.
     * @param v Save button
     */
    public void onClickSaveButton(View v) {
        // check each field for changes and provide some feedback if it was updated
        EditText errorControl = null;
        if (!mPassword.getText().toString().isEmpty()) {
            try {
                User.validatePassword(mPassword.getText().toString());
                mUser.setPassword(mPassword.getText().toString());
            } catch (UserInputException e) {
                mPassword.setError(e.getMessage());
                errorControl = mPassword;
            }
        }

        if (!mId.getText().toString().equals(mUser.getId())) {
            try {
                User.validateId(mId.getText().toString());
                mUser.setId(mId.getText().toString());
            } catch (UserInputException e) {
                mId.setError(e.getMessage());
                errorControl = mId;
            }
        }

        if (!mName.getText().toString().equals(mUser.getName())) {
            try {
                User.validateName(mName.getText().toString());
                mUser.setName(mName.getText().toString());
            } catch (UserInputException e) {
                mName.setError(e.getMessage());
                errorControl = mName;
            }
        }

        if (!mRole.getSelectedItem().equals(mUser.getUserRole())) {
            mUser.setUserRole((UserRole) mRole.getSelectedItem());
        }

        if (errorControl != null) {
            errorControl.requestFocus();
        }
    }
}
