package com.iridium.bottomnavigationui.account;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.iridium.bottomnavigationui.R;
import com.iridium.bottomnavigationui.common.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment
{
    private CircleImageView changeUserPhoto;
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;

    private Button buttonSave;
    private TextView editProfileImageTxt;
    private String imageUrl;
    private String userEmail;
    private String userName;
    private String userPhone;

    public static final String PERSON_NAME = "name";
    public static final String PERSON_PHONE = "phone";
    public static final String IMAGE_NAME = "profileImage";
    public static final String IMAGE_URL = "imageUrl";
    private static final String TAG = "EDIT_PROFILE_FRAGMENT";

    private FirebaseAuth firebaseAuth;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference databaseReference;

    private  Uri path;
    private boolean photoChanged = false;
    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();

        buttonSave =  view.findViewById(R.id.save_profile_changes);
        editProfileImageTxt = view.findViewById(R.id.change_profile_image_txt);
        editTextFullName = view.findViewById(R.id.editprofile_name_txt);
        editTextPhoneNumber =  view.findViewById(R.id.editprofile_phone);
        changeUserPhoto = view.findViewById(R.id.editprofile_image);

        // attaching listeners to button
        changeUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showFileChooser();
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mFirebaseUser.getUid());

        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists())
                    {
                        User user = dataSnapshot.getValue(User.class);

                        editTextFullName.setText(user.getUserName());
                        editTextPhoneNumber.setText(user.getUserPhone());
                        userEmail = user.getUserEmail();
                        Picasso.get().load(user.getImageURL()).into(changeUserPhoto);

                        if(path != null)
                        {
                            path = Uri.parse(user.getImageURL());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        EditProfileFragment.this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Picasso.get().load(filePath).into(changeUserPhoto);
            photoChanged = true;
            // Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            // changeUserPhoto.setImageBitmap(bitmap);

        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
