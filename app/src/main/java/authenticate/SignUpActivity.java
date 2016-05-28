/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * SingInActivity.java
 *
 * This is the fragment that shows up when you are logging in.
 */

package authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import ckubec.tacoma.uw.edu.carparker.FreeActivity;
import ckubec.tacoma.uw.edu.carparker.MainActivity;
import ckubec.tacoma.uw.edu.carparker.MainActivityFragment;
import ckubec.tacoma.uw.edu.carparker.R;

public class SignUpActivity extends AppCompatActivity {
    private String LOGIN_URL = "http://cssgate.insttech.washington.edu/~ckubec/addUser.php?";

    /**
     * This is the onCreate method.
     *
     * @param savedInstanceState This is the saved instance state variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new LoginFragment() )
                .commit();*/

    }


    public void signUp(View view) {
        Intent k = new Intent(this, SignUpActivity.class);
        startActivity(k);
        finish();
    }
}