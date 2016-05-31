package ckubec.tacoma.uw.edu.carparker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AllSpacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_allspaces);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {

        }

         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {

             View rootView = inflater.inflate(R.layout.fragment_allspaces, container, false);

             Button btnAllSpots = (Button)rootView.findViewById(R.id.btnAllSpots);
//             btnAllSpots.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                 }
//             });
             return rootView;
         }
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
