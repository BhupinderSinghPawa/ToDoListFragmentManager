package com.example.todolistfragmentmanager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements ToDoListFragment.OnFragmentInteractionListener,
                    NewItemFragment.OnNewItemAddedListener {

    private ArrayAdapter<String> aa;
    private ArrayList<String> todoItems;

    // NewItemFragment.OnNewItemAddedListener
    public void onNewItemAdded(String newItem){
        todoItems.add(newItem);
        aa.notifyDataSetChanged();
        Log.i("myLog", "MainActivity::onNewItemAdded " + newItem);
    }

    // ToDoListFragment.OnFragmentInteractionListener
    public void onFragmentInteraction(Uri uri) {
        // nothing much to do in this case
        Log.i("myLog", "MainActivity::onFragmentInteraction ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout containing the Fragment containers
        setContentView(R.layout.activity_main);

        // Fragment manager and transactions
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        NewItemFragment newItemFragment = new NewItemFragment();
        ft.add(R.id.entry_container, newItemFragment);
        ToDoListFragment todoListFragment = new ToDoListFragment();
        ft.add(R.id.details_container, todoListFragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Create the array list of to do items
        todoItems = new ArrayList<String>();

        // Create the array adapter to bind the array to the listview
        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                todoItems);

        // Bind the array adapter to the listview.
        // todoListFragment.setListAdapter(aa);
        ListView myListView = findViewById(R.id.myListView);
        myListView.setAdapter(aa);
    }
}
