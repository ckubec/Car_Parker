/**
 * Chris Kubec
 * Phillip Mishchuk
 *
 * MainActivityFragment.java
 *
 * This is the Main activity fragment that goes to the free screen.
 */

package ckubec.tacoma.uw.edu.carparker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    /**
     * This is a place holder class.
     */
    public MainActivityFragment() {
    }


    /**
     * This is the onCreateView method. It creates the view.
     *
     * @param inflater The Inflater variable
     * @param container The Container Variable
     * @param savedInstanceState The Saved Instance State Variable
     * @return Returns the view that is displayed on screen.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
