package com.example.expandablelistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JituExpandableListAdapter exListAdapter;
    ExpandableListView exListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exListView=findViewById(R.id.expanListview);
        //prepare the list header and child
        prepareListData();

        // Invoking the adapter to transfer data onexpandedListView
        exListAdapter = new JituExpandableListAdapter(this,listDataHeader,listDataChild);

        exListView.setAdapter(exListAdapter);

        exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {

                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+"Expanded", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+"Expanded", Toast.LENGTH_SHORT).show();

            }
        });

        exListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+"Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int groupChild, long id) {

                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+":"+listDataChild.get(listDataHeader.get(groupPosition)).get(groupChild), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(listDataHeader.get(groupPosition));
                builder.setMessage(listDataChild.get(listDataHeader.get(groupPosition)).get(groupChild));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.aaaa);
                builder.show();


                return false;
            }
        });

    }
    private void prepareListData(){
        listDataHeader=new ArrayList<>();
        listDataChild=new HashMap<>();
           //Adding header data
        listDataHeader.add("Topics");
        listDataHeader.add("Topics covered");
        listDataHeader.add("Topics not covered");

              //Adding child data
        List<String> top250 = new ArrayList<>();
        top250.add("A");
        top250.add("B");
        top250.add("C");
        top250.add("D");
        top250.add("E");
        top250.add("F");
        top250.add("G");

        List<String> nowshowing = new ArrayList<>();
        nowshowing.add("A");
        nowshowing.add("B");
        nowshowing.add("C");
        nowshowing.add("D");

        List<String> commingsoon = new ArrayList<>();
        commingsoon.add("E");
        commingsoon.add("F");
        commingsoon.add("G");

       // List<String> workon = new ArrayList<>();
      //  workon.add("Start Activity And Start Activity Result");
        listDataChild.put(listDataHeader.get(0),top250);
        listDataChild.put(listDataHeader.get(1),nowshowing);
        listDataChild.put(listDataHeader.get(2),commingsoon);
       // listDataChild.put(listDataHeader.get(3),workon);


    }
}
