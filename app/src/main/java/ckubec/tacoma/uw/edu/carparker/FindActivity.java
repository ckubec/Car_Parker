/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * FindActivity.java
 *
 * This activity shows up when you click the find a parking button.
 */
package ckubec.tacoma.uw.edu.carparker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
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
    public double locationX;
    public double locationY;

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
        //These two doubles are for the SMS thing. If something breaks then remove this.
        double locationX = 47.2447187;
        double locationY = -122.43925539999998;
        LatLng tacoma = new LatLng(locationX, locationY);//The Swiss

        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(47.244032, -122.438185),
                        new LatLng(47.24409, -122.437445),
                        new LatLng(47.243071, -122.437649),
                        new LatLng(47.243012, -122.438464));
        rectOptions.fillColor(Color.parseColor("#905DFC0A"));
        rectOptions.strokeColor(Color.RED);

        PolygonOptions rect2 = new PolygonOptions()
                .add(new LatLng(47.244352, -122.438979),
                        new LatLng(47.244236, -122.4384),
                        new LatLng(47.24299, -122.438636),
                        new LatLng(47.242881, -122.439119));
        rect2.fillColor(Color.parseColor("#90FFFF00"));
        rect2.strokeColor(Color.RED);

        PolygonOptions rect3 = new PolygonOptions()
                .add(new LatLng(60,-150),
                        new LatLng(60, 0),
                        new LatLng(0, 0),
                        new LatLng(0, -150));

        PolygonOptions rect4 = new PolygonOptions()
                .add(new LatLng(47.244768,-122.439805),
                        new LatLng(47.244768, -122.439505),
                        new LatLng(47.243799, -122.43928),
                        new LatLng(47.243719, -122.439526));
        rect4.fillColor(Color.parseColor("#90FFFF00"));
        rect4.strokeColor(Color.YELLOW);


        PolygonOptions rect5 = new PolygonOptions()
                .add(new LatLng(47.246736,-122.440921),
                        new LatLng(47.246756, -122.440739),
                        new LatLng(47.244856, -122.44031),
                        new LatLng(47.244845, -122.440465));
        rect5.fillColor(Color.parseColor("#90FF0000"));
        rect5.strokeColor(Color.GREEN);


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
        mMap.addPolygon(rect4);
        mMap.addPolygon(rect5);
        polygon2.setGeodesic(true);



        polygon2.setFillColor(Color.parseColor("#90A9A9A9"));
        polygon.setClickable(true);
        polygon1.setClickable(true);
        //polygon1.setFillColor(255);



        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                /*Toast.makeText(getApplicationContext(), "TEXT HERE" + polygon.toString()
                        , Toast.LENGTH_SHORT)
                        .show();*/
                DialogFragment fragment = null;

                fragment = new CustomDialogFragment();

                if (fragment != null)
                    fragment.show(getSupportFragmentManager(), "launch");

                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.spot), Context.MODE_PRIVATE);

                sharedPreferences.edit().putBoolean("parked", true).apply();

                sharedPreferences.edit().putInt(getString(R.string.spot), 5).apply();
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

    public double getLocationX()
    {
        return locationX;
    }

    public double getLocationY()
    {
        return locationY;
    }

    protected void sendSMS() {

        EditText destination;
        destination = (EditText) findViewById(R.id.toPhoneNumberET);


        String toPhoneNumber = destination.getText().toString();
        String smsMessage = "These are the coordinates I've parked in using the Car Parker App: " + getLocationX() + ", " + getLocationY();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed." + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
