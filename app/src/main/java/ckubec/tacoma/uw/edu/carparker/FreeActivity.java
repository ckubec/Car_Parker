/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * FreeActivity.java
 *
 * This is the activity that goes to the free screen.
 */

package ckubec.tacoma.uw.edu.carparker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FreeActivity extends FragmentActivity  {

    private GoogleMap mMap;

    /**
     * This is the onCreate method.
     *
     * @param savedInstanceState The Saved INstance State.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);
    }

    public void yes(View view){
        Toast.makeText(getApplicationContext(), "You have successfully freed your parking spot.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void no(View view){
        finish();
    }

}
