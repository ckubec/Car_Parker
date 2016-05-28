package authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import java.io.OutputStreamWriter;
import ckubec.tacoma.uw.edu.carparker.MainActivity;
import ckubec.tacoma.uw.edu.carparker.R;

/**
 * Created by ckubec on 5/28/16.
 */
public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;

    /**
     * This is the onCreate method.
     *
     * @param savedInstanceState This is the saved instance state variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        if (!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment())
                    .commit();
        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }


        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new LoginFragment())
                .commit();*/

    }

    /**
     * This is the login method. It handles what is taken from the app (user and pass) and sends it off to be processed.
     *
     * @param userId The User ID parameter that holds what the user's ID is, in this case they log in with email.
     * @param pwd    This is the password that they input into the app.
     */
    public void login(String userId, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Check if the login and password are valid
            //new LoginTask().execute(url);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput(getString(R.string.LOGIN_FILE)
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + userId + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "No network connection available. Cannot authenticate user",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signUp(View view) {
        Intent k = new Intent(this, SignUpActivity.class);
        startActivity(k);
        finish();
    }

    public void signUpSwap(){
        Intent k = new Intent(this, SignUpActivity.class);
        startActivity(k);
        finish();
    }
}