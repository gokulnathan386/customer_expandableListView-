package com.tutorialscache.expandablelistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableCustomAdapter expandableCustomAdapter;
    ExpandableListView expandableListView;
    List<String> headerData;
    HashMap<String,ArrayList<ChildDataModel>> childData;
    ChildDataModel childDataModel;
    Context mContext;
    ArrayList<ChildDataModel> asianCountries,africanCountries,nAmericanCountries,sAmericanCountries;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        //initializing arraylists
        headerData = new ArrayList<>();
        childData = new HashMap<String,ArrayList<ChildDataModel>>();
        asianCountries = new ArrayList<>();
        africanCountries = new ArrayList<>();
        nAmericanCountries = new ArrayList<>();
        sAmericanCountries = new ArrayList<>();



        // link listview from activity_main.xml
        expandableListView = findViewById(R.id.expandAbleListView);

        //populating data of world continents and their countries.
        headerData.add("ASIA");

        //adding countries to Asian continent
        childDataModel = new ChildDataModel(1,"Afghanistan",R.drawable.afghanistan);
        asianCountries.add(childDataModel);

        childDataModel = new ChildDataModel(2,"China",R.drawable.china);
        asianCountries.add(childDataModel);

        childDataModel = new ChildDataModel(3,"India",R.drawable.india);
        asianCountries.add(childDataModel);

        childDataModel = new ChildDataModel(4,"Pakistan",R.drawable.pakistan);
        asianCountries.add(childDataModel);

        childData.put(headerData.get(0),asianCountries);


        headerData.add("AFRICA");

        //adding countries to African continent
        childDataModel = new ChildDataModel(1,"South Africa",R.drawable.southafrica);
        africanCountries.add(childDataModel);

        childDataModel = new ChildDataModel(2,"Zimbabwe",R.drawable.zimbabwe);
        childData.put(headerData.get(1),africanCountries);


        headerData.add("NORTH AMERICA");
        //adding countries to NORTH AMERICA continent
        childDataModel = new ChildDataModel(1,"Canada",R.drawable.canada);
        nAmericanCountries.add(childDataModel);
        childData.put(headerData.get(2),nAmericanCountries);


        headerData.add("SOUTH AMERICA");
        //adding countries to SOUTH AMERICA continent
        childDataModel = new ChildDataModel(1,"Argentina",R.drawable.argentena);
        sAmericanCountries.add(childDataModel);
        childData.put(headerData.get(3),sAmericanCountries);



        //set adapter to list view
        expandableCustomAdapter = new ExpandableCustomAdapter(mContext,headerData,childData);
        expandableListView.setAdapter(expandableCustomAdapter);

        //child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int headPosition, int childPosition, long id) {
                Toast.makeText(mContext,
                        headerData.get(headPosition)
                                + " has country "
                                + childData.get(
                                headerData.get(headPosition)).get(
                                childPosition).getTitle(), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        //group expanded
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int headPosition) {
                if (lastExpandedPosition != -1
                        && headPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = headPosition;
                Toast.makeText(mContext,
                        headerData.get(headPosition) + " continent expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //group collapsed
         expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
             @Override
             public void onGroupCollapse(int headPosition) {
                 Toast.makeText(mContext,
                         headerData.get(headPosition) + " continent collapsed",
                         Toast.LENGTH_SHORT).show();
             }
         });

        //Group Indicator
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                parent.smoothScrollToPosition(groupPosition);

                if (parent.isGroupExpanded(groupPosition)) {
                    ImageView imageView = v.findViewById(R.id.expandable_icon);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24));
                } else {
                    ImageView imageView = v.findViewById(R.id.expandable_icon);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_down));
                }
                return false    ;
            }
        });
    }
}
