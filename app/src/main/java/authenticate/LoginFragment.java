/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * LoginFragment.java
 *
 * This is the fragment that shows up when you are logging in.
 */

package authenticate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ckubec.tacoma.uw.edu.carparker.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    /**
     * This is the empty constructor for the login fragment.
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * This is the method that creates the views that are displayed in the program.
     * It also includes PHP code that handles logging in and out.
     *
     * @param inflater The Inflater variable
     * @param container The Container Variable
     * @param savedInstanceState The Saved Instance State Variable
     * @return Returns the view that is displayed on screen.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        final EditText userIdText = (EditText) v.findViewById(R.id.username);
        final EditText pwdText = (EditText) v.findViewById(R.id.password);
        Button signInButton = (Button) v.findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdText.getText().toString();
                String pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(userId))  {
                    Toast.makeText(v.getContext(), "Enter userid"
                            , Toast.LENGTH_SHORT)
                            .show();
                    userIdText.requestFocus();
                    return;
                }
                if (!userId.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address"
                            , Toast.LENGTH_SHORT)
                            .show();
                    userIdText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pwd))  {
                    Toast.makeText(v.getContext(), "Enter password"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();
                    return;
                }
                if (pwd.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter password of at least 6 characters"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();
                    return;
                }

                ((SignUpActivity) getActivity()).login(userId, pwd);
            }
        });

        return v;
    }




    public interface LoginInteractionListener {
        public void login(String userId, String pwd);
    }

}
