package com.iridium.bottomnavigationui.common;

public class House
{
    // house description
    private String houseDescription;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //house name
    private String houseName;
    // house location
    private String estate;
    // size (floor space)
    private String floorSpace;
    // number of bedrooms
    private String bedrooms;
    // bathrooms
    private String bathrooms;
    // number of parking lots
    private String parkingLots;
    // rent
    private String rent;
    // number of units
    private String units;

    private String agentName;

    private String agentPhone;

    private String houseID;



    public House()
    {

    }

    public House(String houseID, String imageUrl, String houseDescription, String houseName, String estate, String floorSpace, String bedrooms, String bathrooms, String parkingLots,
                 String rent, String units, String agentName, String agentPhone) {

        if(houseDescription.trim().equals(""))
        {
            houseDescription="No description";
        }
        if (houseName.trim().equals(""))
        {
            houseName="No name";
        }
        if (estate.trim().equals(""))
        {
            estate="No estate specified";
        }
        if (floorSpace.trim().equals(""))
        {
            floorSpace="Floor space not defined";
        }
        if (bedrooms.trim().equals(""))
        {
            bedrooms="Not specified";
        }
        if (bathrooms.trim().equals(""))
        {
            bathrooms="Not specified";
        }
        if (parkingLots.trim().equals(""))
        {
            parkingLots="Not specified";
        }
        if (rent.trim().equals(""))
        {
            rent="Not specified";
        }
        if (units.trim().equals(""))
        {
            units="Not Specified";
        }


        this.houseDescription = houseDescription;
        this.houseName = houseName;
        this.estate = estate;
        this.floorSpace = floorSpace;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.parkingLots = parkingLots;
        this.rent = rent;
        this.units = units;
        this.agentName = agentName;
        this.agentPhone = agentPhone;
        this.imageUrl = imageUrl;
        this.houseID = houseID;

    }


    public String getHouseDescription()
    {
        return houseDescription;
    }
    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }
    public String getHouseName() {
        return houseName;
    }
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    public void setEstate(String estate) {
        this.estate = estate;
    }
    public String getEstate() {
        return estate;
    }
    public void setFloorSpace(String floorSpace) {
        this.floorSpace = floorSpace;
    }
    public String getFloorSpace() {
        return floorSpace;
    }
    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }
    public String getBedrooms() {
        return bedrooms;
    }
    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }
    public String getBathrooms() {
        return bathrooms;
    }
    public void setParkingLots(String parkingLots) {
        this.parkingLots = parkingLots;
    }
    public String getParkingLots() {
        return parkingLots;
    }
    public void setRent(String rent) {
        this.rent = rent;
    }
    public String getRent() {
        return rent;
    }
    public void setUnits(String units) {
        this.units = units;
    }
    public String getUnits() {
        return units;
    }
    public String getAgentName() {
        return agentName;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public String getAgentPhone() {
        return agentPhone;
    }
    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }
}