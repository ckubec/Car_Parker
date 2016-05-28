package ckubec.tacoma.uw.edu.carparker.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by philm on 5/28/2016.
 */
public class Parking implements Serializable{

    public String getAllSpacesFragment() { return allSpacesFrag; }
    public void setAllSpacesFragment(String allSpacesFrag) { this.allSpacesFrag = allSpacesFrag; }

    public String getParkingLocation() {return mParkLocation; }
    public void setParkingLocation(String mParkLocation) { this.mParkLocation = mParkLocation; }

    public String getNumSpots() { return mNumSpots; }
    public void setNumSpots(String mNumSpots) { this.mNumSpots = mNumSpots; }

    public String getNumSpotsTaken() { return mNumSpotsTaken; }
    public void setNumSpotsTaken(String mNumSpotsTaken) { this.mNumSpotsTaken = mNumSpotsTaken; }

    private String mNumSpots;
    private String mNumSpotsTaken;
    private String mParkLocation;
    private String allSpacesFrag;

    public static final String ADDRESS = "address";
    public static final String SPOTS = "spots";
    public static final String SPOTSTAKEN = "Spots Taken";

    /**
     * This is the parking spot constructor method
     * @param
     */
    public Parking(String allSpacesFrag, String mNumSpots, String mParkLocation, String mNumSpotsTaken)
    {
        this.mNumSpots = mNumSpots;
        this.mParkLocation = mParkLocation;
        this.mNumSpotsTaken = mNumSpotsTaken;
    }

    public static String parseParkingJSON(String parkingJSON, List<Parking> parkingList)
    {
        String reason = null;
        if (parkingJSON != null)
        {
            try
            {
                JSONArray array = new JSONArray(parkingJSON);

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject object = array.getJSONObject(i);
                    Parking park = new Parking("fragment"
                            ,object.getString(Parking.ADDRESS)
                            ,object.getString(Parking.SPOTS)
                            ,object.getString(Parking.SPOTSTAKEN));
                    parkingList.add(park);
                }
            }
            catch (JSONException e)
            {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        return reason;
    }
}
