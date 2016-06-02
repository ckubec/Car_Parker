package ckubec.tacoma.uw.edu.carparker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ckubec.tacoma.uw.edu.carparker.AllSpacesFragment.OnListFragmentInteractionListener;
import ckubec.tacoma.uw.edu.carparker.model.Parking;

/**
 * {@link RecyclerView.Adapter} that can display a {@link //DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyAllSpacesRecyclerViewAdapter extends RecyclerView.Adapter<MyAllSpacesRecyclerViewAdapter.ViewHolder> {

    private final List<Parking> mValues;
    private final AllSpacesFragment.OnListFragmentInteractionListener mListener;

    public MyAllSpacesRecyclerViewAdapter(List<Parking> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_allspaces, parent, false);  //????
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mParking = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getParkingLocation() + "\nNumber of Spots: " + mValues.get(position).getNumSpots() + "\nNumber of Taken Spots: " + mValues.get(position).getNumSpotsTaken());
        holder.mContentView.setText(mValues.get(position).getNumSpots());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
//                    holder.mParking.setAllSpacesFragment("All_Spaces");
                    mListener.onListFragmentInteraction(holder.mParking);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public Parking mParking;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.allspacesfragmentTextView);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
