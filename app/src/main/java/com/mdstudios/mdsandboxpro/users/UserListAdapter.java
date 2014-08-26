package com.mdstudios.mdsandboxpro.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mdstudios.mdsandboxpro.R;

import java.util.List;

/**
 * Created by jawad on 26/08/14.
 */
public class UserListAdapter extends BaseAdapter {
    // Context later used to inflate the views
    Context mContext;

    // Holds all the data
    List<User> mUserList;

    // A simple recycled object to assist in optimizations
    class ViewHolder{
        // Simply holds the views that the row represents
        TextView mTextView;

        //--Constructor, needs a view to find the views this holder represents
        public ViewHolder(View view){
            // Find and store all the views
            mTextView = (TextView) view.findViewById(R.id.textView);
        }
    }

    //--Constructor
    public UserListAdapter(Context context, List<User> users){
        // Save the context for later use
        mContext = context;

        // Save the users within the adapter
        mUserList = users;
    }

    //--Gets number of rows--
    @Override
    public int getCount() {
        return mUserList.size();
    }

    //--Gets data object at position--
    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView = recycled view, if it exists
        View row = convertView;
        ViewHolder holder = null;

        if(row == null){
            // Then this view has not been initialized yet, so set it up
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_user, parent, false);

            // Create the viewHolder with this row view
            holder = new ViewHolder(row);

            // Tag the holder onto the view row so it gets recycled as well
            row.setTag(holder);
        }
        else{
            // Otherwise, only need to get the holder from the recycled view
            holder = (ViewHolder) row.getTag();
        }

        // Get the data to display
        User user = mUserList.get(position);
        String username = user.getUsername();

        // Display the data
        holder.mTextView.setText(username);

        // Return the view as requested
        return row;
    }
}
