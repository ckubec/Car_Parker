/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * MainActivity.java
 *
 * This is the Main activity that goes in the main screen.
 */

package ckubec.tacoma.uw.edu.carparker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import authenticate.SignUpActivity;
import ckubec.tacoma.uw.edu.carparker.model.SendTextActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * This is the onCreate method.
     *
     * @param savedInstanceState This is the Saved Instance State variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    /**
     * This creates the options menu bar at the top right.
     *
     * @param menu The menu item.
     * @return Returns the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method is for when an item in the options dropdown menu is selected.
     *
     * @param item The Item is the option that the user clicked.
     * @return The item clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .commit();

            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is for when you click the Settings on the menu item.
     * @param mItem the menu item that is clicked.
     */
    public void settingsMenu(View view)
    {
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
        //finish();
    }

    /**
     * This method is for when you click the Settings on the menu item.
     * @param mItem the menu item that is clicked.
     */
    public void aboutScreen(View view)
    {
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
        //finish();
    }

    /**
     * The method for fetching the find a parking space screen.
     *
     * @param view Return the find a parking space view.
     */
    public void find(View view){
        Intent i = new Intent(this, FindActivity.class);
        startActivity(i);
        //finish();
    }

    /**
     * The method for fetching the reserving screen.
     *
     * @param view Return the fetching the reserving a space view.
     */
    public void reserve(View view){
        Intent j = new Intent(this, ReserveActivity.class);
        startActivity(j);
        //finish();
    }

    /**
     * The method for fetching the free parking space screen.
     *
     * @param view Return the Free a parking space view.
     */
    public void free(View view){
        Intent k = new Intent(this, FreeActivity.class);
        startActivity(k);
        //finish();
    }

    public void sendAMessage(View view){
        Intent m = new Intent(this, SendTextActivity.class);
        startActivity(m);
        //finish();
    }

    public void ListAllSpaces(View view)
    {
        Intent i = new Intent(this, AllSpacesActivity.class);
        startActivity(i);
        //finish();
    }
}
