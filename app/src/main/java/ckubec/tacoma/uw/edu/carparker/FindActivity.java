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
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    @Override
    public void onBackPressed()
    {
        /*if ( ! getIntent().getExtras().getBoolean())
            moveTaskToBack(true); // exist app
        else*/
            finish();
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        final LatLngBounds TACOMA = LatLngBounds.builder()
                .include(new LatLng(47.2409, -122.444859))
                .include(new LatLng(47.252903, -122.434559))
                .build();

        // Add a marker in Tacoma and move the camera
        LatLng tacoma = new LatLng(47.2447187, -122.43925539999998);//The Swiss

        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(47.244032, -122.438185),
                        new LatLng(47.24409, -122.437445),
                        new LatLng(47.243071, -122.437649),
                        new LatLng(47.243012, -122.438464));

        PolygonOptions rect2 = new PolygonOptions()
                .add(new LatLng(47.244352, -122.438979),
                        new LatLng(47.244236, -122.4384),
                        new LatLng(47.24299, -122.438636),
                        new LatLng(47.242881, -122.439119));

        PolygonOptions rect3 = new PolygonOptions()
                .add(new LatLng(60,-150),
                        new LatLng(60, 0),
                        new LatLng(0, 0),
                        new LatLng(0, -150));

        rect3.fillColor(-16777216);
        ArrayList<LatLng> list = new ArrayList<>();
        list.add(new LatLng(TACOMA.northeast.latitude, TACOMA.southwest.longitude));
        list.add(new LatLng(TACOMA.northeast.latitude, TACOMA.northeast.longitude));
        list.add(new LatLng(TACOMA.southwest.latitude, TACOMA.northeast.longitude));
        list.add(new LatLng(TACOMA.southwest.latitude, TACOMA.southwest.longitude));
        PolygonOptions polygonOptions = rect3.addHole(list);




        /*.add(new LatLng(TACOMA.northeast.latitude, TACOMA.southwest.longitude),
                        new LatLng(TACOMA.northeast.latitude, TACOMA.northeast.longitude),
                        new LatLng(TACOMA.southwest.latitude, TACOMA.northeast.longitude),
                        new LatLng(TACOMA.southwest.latitude, TACOMA.southwest.longitude));*/





// Get back the mutable Polygon
        final Polygon polygon = mMap.addPolygon(rectOptions);
        Polygon polygon1 = mMap.addPolygon(rect2);
        Polygon polygon2 = mMap.addPolygon(rect3);
        polygon2.setGeodesic(true);



        polygon2.setFillColor(Color.parseColor("#90A9A9A9"));
        polygon.setClickable(true);
        polygon1.setClickable(true);
        polygon1.setFillColor(255);



        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Toast.makeText(getApplicationContext(), "TEXT HERE" + polygon.toString()
                        , Toast.LENGTH_SHORT)
                        .show();


            }
        });


        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(TACOMA, 0));

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                //Toast.makeText(getApplicationContext(), "TEXT HERE BS : " + arg0.zoom, Toast.LENGTH_SHORT).show();

                if(TACOMA.northeast.latitude < arg0.target.latitude
                        || TACOMA.northeast.longitude < arg0.target.longitude
                        || TACOMA.southwest.latitude > arg0.target.latitude
                        || TACOMA.southwest.longitude > arg0.target.longitude) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(47.2409, -122.444859), new LatLng(47.252903, -122.434559)), 15));
                }

                if(arg0.zoom < 15){

                } else if (arg0.zoom > 20) {

                }

                //mMap.setOnCameraChangeListener(null);

            }
        });



        //mMap.setPadding(30, 30, 30, 30);
        ActivityCompat.requestPermissions(this,new String[]{"android.permission.ACCESS_FINE_LOCATION"},1);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tacoma, 16));
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
