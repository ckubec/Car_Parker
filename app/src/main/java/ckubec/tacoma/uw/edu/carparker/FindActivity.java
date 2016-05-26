/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * FindActivity.java
 *
 * This activity shows up when you click the find a parking button.
 */
package ckubec.tacoma.uw.edu.carparker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class FindActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    /**
     * This is the onCreate method.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /**
     * This method sets up the map
     *
     * @param googleMap This is the Google Map. This is what shows up when you click the button.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       /* mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2));

        // Polylines are useful for marking paths and routes on the map.
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))  // Mountain View
        );*/

        // Add a marker in Tacoma and move the camera
        LatLng sydney = new LatLng(47.24323076, -122.43845344);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));



        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(47.24418492, -122.43816376),
                        new LatLng(47.24413029, -122.43772924),
                        new LatLng(47.24297219, -122.43783653),
                        new LatLng(47.24291028, -122.43843734));

// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);
        polygon.setClickable(true);

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Toast.makeText(getApplicationContext(), "TEXT HERE" + polygon.toString()
                        , Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    /**
     * This method goes back to the back button. Basically moves the screen back.
     *
     * @param view This is the view that goes back.
     */
    public void back(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
