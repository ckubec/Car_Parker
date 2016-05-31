package authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import ckubec.tacoma.uw.edu.carparker.MainActivity;
import ckubec.tacoma.uw.edu.carparker.R;

/**
 * Created by ckubec on 5/28/16.
 */
public class LoginActivity extends AppCompatActivity {
    private String LOGGING = "http://cssgate.insttech.washington.edu/~ckubec/Android/login.php?";
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

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(getString(R.string.login), userId)
                .commit();

        if (networkInfo != null && networkInfo.isConnected()) {
            //Check if the login and password are valid
            //new LoginTask().execute(url);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput(getString(R.string.LOGIN_FILE)
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("user = " + userId + ";");
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

        LoginTask task = new LoginTask();
        task.execute(new String[]{LOGGING+"&user="+ userId +"&pwd=" + pwd});
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        /**
         * This is doInBackground method.
         * @param urls
         * @return responce of the method.
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to reach Login Server: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }
        /**
         * This is onPostExecute method.
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            //parsing the json
            String[] resultSplit = result.split("\"result\": \"");
            resultSplit = resultSplit[1].split("\", \"");
            // Something wrong with the network or the URL.
            if (resultSplit[0].equals("fail")) {
                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            else if(resultSplit[0].equals("success")) {
                loginSuccess();
            }
        }
    }

    public void loginSuccess(){
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