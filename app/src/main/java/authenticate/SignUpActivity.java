/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * SingInActivity.java
 *
 * This is the fragment that shows up when you are logging in.
 */

package authenticate;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ckubec.tacoma.uw.edu.carparker.MainActivity;
import ckubec.tacoma.uw.edu.carparker.R;

public class SignUpActivity extends AppCompatActivity {
    private String SIGNUP_URL = "http://cssgate.insttech.washington.edu/~ckubec/Android/addUser.php?";

    /**
     * This is the onCreate method.
     *
     * @param savedInstanceState This is the saved instance state variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }

    public void signSubmit(View view) {
        String user = ((EditText) findViewById(R.id.userI)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.passwordI)).getText().toString();
        String fName = ((EditText) findViewById(R.id.fNameI)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailI)).getText().toString();

        SignUpTask task = new SignUpTask();

        task.execute(new String[]{SIGNUP_URL +"&user="+ user +"&pwd="+ pwd +"&name="+ fName.replaceAll(" ", "%20") +"&email="+ email });


    }


    public void signUp(View view) {
        Intent k = new Intent(this, SignUpActivity.class);
        startActivity(k);
        finish();
    }

    private class SignUpTask extends AsyncTask<String, Void, String> {
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
                Toast.makeText(getApplicationContext(), "Error" + resultSplit[0]+ ",," + resultSplit[1], Toast.LENGTH_LONG)
                        .show();
                return;
            }
            else if(resultSplit[0].equals("success")) {
                complete();
            }
        }
    }

    private void complete() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}