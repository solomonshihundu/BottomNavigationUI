package com.iridium.bottomnavigationui.homefeed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iridium.bottomnavigationui.R;
import com.iridium.bottomnavigationui.common.Adapter;
import com.iridium.bottomnavigationui.common.House;

import java.util.ArrayList;


public class HomeFragment extends Fragment
{
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ArrayList<House> house;
    private SearchView search;
    private final String TAG = "HOME_FEED_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);

        recyclerView  = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("housesInformation/y31sLUJ3PkQPP01j0POsCv50IVS2");


        search =  rootView.findViewById(R.id.search);
        if(search != null)
        {

            Log.d(TAG, "SERCHVIEW NOT NULL");
            search.setQueryHint("Search property name");
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    searchForHouse(s);
                    Log.d(TAG, "TYPED " + s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    searchForHouse(s);
                    Log.d(TAG, "TYPED " + s);
                    return false;
                }
            });
        }

        return rootView;
    }

    private void fetch()
    {
        Log.d(TAG,"WE CAN FETCH DATA");
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    if (dataSnapshot.exists()) {
                        house = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            house.add(ds.getValue(House.class));
                            Log.d(TAG,ds.getValue(House.class).getImageUrl().toUpperCase());
                        }

                        Adapter adapter = new Adapter(house);
                        recyclerView.setAdapter(adapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {

                }
            });
        }

    }

    private void searchForHouse(String search)
    {
        String mySearch = search.toLowerCase();
        ArrayList<House> searchHouse = new ArrayList<>();
        for(House myHouse : house)
        {
            if(myHouse.getHouseName().toLowerCase().contains(mySearch) || myHouse.getEstate().toLowerCase().contains(mySearch))
            {
                searchHouse.add(myHouse);
            }
        }
        Adapter adapter = new Adapter(searchHouse);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        fetch();

    }


    @Override
    public void onStop()
    {
        super.onStop();

    }

}
