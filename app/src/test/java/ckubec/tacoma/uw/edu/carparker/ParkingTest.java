package ckubec.tacoma.uw.edu.carparker;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ckubec.tacoma.uw.edu.carparker.model.Parking;

/**
 * Created by philm on 5/31/2016.
 */
public class ParkingTest extends TestCase {

    Parking park;

    @Before
    public void setUp() throws Exception {
        park = new Parking("fragment", "spots", "address", "spotsTaken");
    }

    @Test
    public void testConstructor()
    {
        Parking parking = new Parking(null, "100", "Street Street", "54");
        assertNotNull(parking);
    }


    @Test
    public void testSetSpotsToNull() {
        try {
            park.setNumSpots(null);
            fail("Number of spots can't be null");
        }
        catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSetFragmentToNull() {
        try {
            park.setAllSpacesFragment(null);
            fail("Fragment can't be null");
        }
        catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSetSpotsTakenToNull() {
        try {
            park.setNumSpotsTaken(null);
            fail("Number of Spots Taken can't be null");
        }
        catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSetAddressToNull() {
        try {
            park.setParkingLocation(null);
            fail("Address can't be set to null");
        }
        catch (IllegalArgumentException e) {}
    }


    @Test
    public void testGetNumberOfSpots()
    {
        assertEquals("100", park.getNumSpots());
    }

    @Test
    public void testGetNumberOfTakenSpots()
    {
        assertEquals("23", park.getNumSpotsTaken());
    }

    @Test
    public void testGetAddress()
    {
        assertEquals("Street Street", park.getParkingLocation());
    }

    @Test
    public void testGetFragment()
    {
        assertEquals(park.getAllSpacesFragment(), park.getAllSpacesFragment());
    }

    @Test
    public void testParseParkingJSON() {
        String parkingJSON = "[{\"fragment\":\"fragment\",\"spots\":\"spots\",\"address\":\"address\"," +
                "\"spotsTaken\":\"spotsTaken\"}]";

        String message =  park.parseParkingJSON(parkingJSON, new ArrayList<Parking>());

        assertTrue("JSON With Valid String", message == null);
    }

}
