package com.mdstudios.mdsandboxpro.drawer;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdstudios.mdsandboxpro.R;

/**
 * Created by jawad on 27/08/14.
 *
 * Purpose: Manages the navigation drawer as much as possible outside of the actual activity
 */
public class DrawerControllerFragment extends Fragment {
    // TODO: Why must this be a member variable, instead of local?
    private DrawerLayout mDrawerLayout;

    // Helper component that ties the action bar to the navigation drawer.
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_controller_fragment, container, false);

        return view;
    }

    /**
     * Must be called by the activity to properly set up the drawer
     *
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */

    public void setUpDrawer(DrawerLayout drawerLayout){
        // Save the drawerLayout
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
            // TODO: Find out how this works specifically
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Enable action bar button functionality
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // Set up drawerListener/ActionBarDrawerToggle object
        // Ties together actionbar and drawer
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                drawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                //    Toast.makeText(getActivity(), " Drawer Closed ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            //    Toast.makeText(getActivity(), " Drawer Opened ", Toast.LENGTH_SHORT).show();
            }
        };

        // Assign the drawerToggle
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // TODOx: Set up content of drawer
    }

    // Needed by activity to finalize actionBar functionality
    public ActionBarDrawerToggle getDrawerToggle(){return mDrawerToggle;}


}
