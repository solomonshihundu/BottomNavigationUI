package com.iridium.bottomnavigationui.account;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iridium.bottomnavigationui.R;
import com.iridium.bottomnavigationui.common.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment
{

    private Button buttonLogout;
    private Uri imageUrl;
    private DatabaseReference databaseReference;

    TextView textViewPersonName;
    TextView textViewPhone;
    TextView textViewEmail;
    CircleImageView profileImage;
    TextView numHousesListed;

    private static final String TAG = "PROFILE_FRAGMENT";

    private static String name;
    private static String phone;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private int houseCount = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initializing views
        textViewPersonName =  view.findViewById(R.id.profile_name_txt);
        textViewPhone =  view.findViewById(R.id.profile_phone);
        textViewEmail =  view.findViewById(R.id.profile_email_txt);
        profileImage = view.findViewById(R.id.profile_image);
        buttonLogout =  view.findViewById(R.id.profile_logout_btn);
        numHousesListed = view.findViewById(R.id.numHousesListedTxt);
        numHousesListed.setText(Integer.toString(houseCount));

        // getting an instance of Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);

                        textViewPersonName.setText(user.getUserName());
                        textViewPhone.setText(user.getUserPhone());
                        textViewEmail.setText(user.getUserEmail());
                        Picasso.get().load(user.getImageURL()).into(profileImage);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}
