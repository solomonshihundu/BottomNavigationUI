package com.iridium.bottomnavigationui.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iridium.bottomnavigationui.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private static int count = 0;

    ArrayList<House> house;

    public Adapter(ArrayList<House> house)
    {
        this.house = house;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_model, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setHouseName(house.get(position).getHouseName());
        holder.setHouseDescription(house.get(position).getHouseDescription());
        holder.setLocation(house.get(position).getEstate());
        holder.setImage(house.get(position).getImageUrl());
        holder.setRent(house.get(position).getRent()+" KSH");

    }

    @Override
    public int getItemCount() {
        return count = house.size();
    }

    public static int numOfHouses()
    {
        return count;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "VIEW_HOLDER";

        View mView;
        private TextView houseName;
        private TextView houseDescription;
        private TextView bedrooms;
        private TextView floorSpace;
        private TextView parkingSlots;
        private TextView rent;
        private ImageView image;
        private TextView location;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setHouseName(String houseName) {

            this.houseName = mView.findViewById(R.id.property_name);
            this.houseName.setText(houseName);

        }

        public void setHouseDescription(String houseDescription) {
            this.houseDescription = mView.findViewById(R.id.hse_description);
            this.houseDescription.setText(houseDescription);

        }

        public void setBedrooms(String bedrooms) {
            this.bedrooms = mView.findViewById(R.id.hse_description);
            this.bedrooms.setText(bedrooms);

        }

        public void setFloorSpace(String floorSpace) {
            this.floorSpace = mView.findViewById(R.id.hse_description);
            this.floorSpace.setText(floorSpace);

        }

        public void setParkingSlots(String parkingSlots) {
            this.parkingSlots = mView.findViewById(R.id.hse_description);
            this.parkingSlots.setText(parkingSlots);

        }

        public void setRent(String rent) {
            this.rent = mView.findViewById(R.id.display_rent);
            this.rent.setText(rent);

        }

        public void setImage(String imageUrl) {
            image = mView.findViewById(R.id.house_image);
      //      Picasso.get().load(imageUrl).into(image);


            Glide
                    .with(mView.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.house_photo_placeholder)
                    .into(image);

        }

        public void setLocation(String location) {
            this.location = mView.findViewById(R.id.hse_location_txt);
            this.location.setText(location);

        }
    }
}
