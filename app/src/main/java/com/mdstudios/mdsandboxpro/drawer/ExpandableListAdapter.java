package com.mdstudios.mdsandboxpro.drawer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mdstudios.mdsandboxpro.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jawad on 27/08/14.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    //--Gets the number of groups--
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    //--Gets the number of children in a specified group--
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    //--Gets the data associated with the given group--
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    //--Gets the data associated with the given child within the given group--
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    //--Gets the data associated with the given child within the given group--
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //--Gets the ID for the given child within the given group--
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //--Indicates whether the child and group IDs are stable across changes to the underlying data--
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //--Gets a View that displays the given group ie the header--
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    //--Gets a View that displays the data for the given child within the given group--
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    //--Whether the child at the specified position is selectable--
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
