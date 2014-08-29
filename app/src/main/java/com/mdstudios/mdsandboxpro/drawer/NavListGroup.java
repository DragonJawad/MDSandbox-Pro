package com.mdstudios.mdsandboxpro.drawer;

import java.util.List;

/**
 * Created by jawad on 27/08/14.
 *
 * Purpose: Holds a group/header item of an expandedListView on the navigation drawer
 */
public class NavListGroup {
    // Id for this group, for later matching
    private int mGroupId;

    // The displayed name of this Group
    private String mGroupName;

        // As needed for now, only launches an activity
    // Determines the destination
    private Class mActivityDestination;

    // Easily determines if the group has children under it
    private boolean mHasChildren = false;

    // Holds all the children items that belongs under this group
    private List<NavListChild> mChildrenList;
}
