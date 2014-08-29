package com.mdstudios.mdsandboxpro.drawer;

/**
 * Created by jawad on 27/08/14.
 *
 * Purpose: Holds a child item of an expandedListView on the navigation drawer
 */
public class NavListChild {
    // Id for this child item, for later matching
    private int mChildId;

    // The displayed name of this child item
    private String mChildName;

        // As needed for now, only launches an activity
    // Determines the destination
    private Class mActivityDestination;
}
