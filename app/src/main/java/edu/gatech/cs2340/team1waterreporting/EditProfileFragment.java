package edu.gatech.cs2340.team1waterreporting;

import android.content.Context;
import android.net.Uri;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {
    private static final String ARG_USER = "user";

    private EditText mName;
    private EditText mPassword;
    private EditText mId;
    private Spinner mRole;

    private User mUser;

    public EditProfileFragment() {

    }

    /**
     * New instance of form to edit a user.
     *
     * @param user User
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    public void onClickSaveButton(View v) {
        // check each field for changes and provide some feedback if it was updated
        if (!mName.getText().toString().equals(mUser.getName())) {
            try {
                User.validateName(mName.getText().toString());

            } catch (UserInputException e) {
                mName.setError(e.getMessage());
            }
        }
        // TODO: finish this section
    }
}
