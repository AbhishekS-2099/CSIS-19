package com.example.myapplication1;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;
    private FloatingActionButton fab;
    private ListView listView;
    private listViewAdapter listViewAdapter;
    private List<event> listEvent = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference=mDatabase.getReference();
        initUI();
        setListViewAdapter();
        addSingleEventListener();
        addChildEventListener();

//        setFabClickListener();
//        setListViewItemListener();
//        setListViewLongClickListener();
    }

    private void initUI(){
        Log.d("InitUI","UI initializing");
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.drawerOpen, R.string.drawerClosed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        progressBar = findViewById(R.id.progressBar);
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void setListViewAdapter(){
        listViewAdapter = new listViewAdapter(this, listEvent);
        listView.setAdapter(listViewAdapter);
        Log.d("setListViewAdapter","SetupListViewAdapter");
    }

    private void addChildEventListener() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                event event = dataSnapshot.getValue(event.class);
                Log.d("classEventSnapshot",dataSnapshot.toString());
                Log.d("classEvent",dataSnapshot.child("eventName").toString());
                Log.d("classEventActual",dataSnapshot.getValue(event.class).getEventName().toString());
                if(event != null){
                    event.setKey(dataSnapshot.getKey());
                    listEvent.add(dataSnapshot.getValue(event.class));
                    listViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                event event = dataSnapshot.getValue(event.class);
                Log.d("classAbhishek",dataSnapshot.toString());
                if(event != null){
                    String key = dataSnapshot.getKey();
                    for(int i=0;i<listEvent.size();i++){
                        event event1= listEvent.get(i);
                        if(event1.getKey().equals(key)){
                            listEvent.set(i, event);
                            listViewAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                listEvent.remove(dataSnapshot.getValue(event.class));
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void addSingleEventListener(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

//    private void setListViewItemListener(){
//        listView.setOnItemClickListener((adapterView, view, i, l) -> {
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("edit", true);
//            bundle.putParcelable("person", Parcels.wrap(listEvent.get(i)));
//            Intent intent = new Intent(this, EditPersonActivity.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        });
//    }

    //LONG CLICK LISTENER


//    private void setListViewLongClickListener(){
//        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
//            final event event = listEvent.get(i);
//            new AlertDialog.Builder(this)
//                    .setTitle("Delete " + event.getEventName() + " " + event.getEventType())
//                    .setMessage("Do you want to delete the selected record?")
//                    .setPositiveButton("Delete", (dialogInterface, i1) -> {
//                        databaseReference.child(event.getKey()).removeValue();
//                    })
//                    .setNegativeButton("Cancel", (dialogInterface, i12) -> {
//                        dialogInterface.dismiss();
//                    })
//                    .create()
//                    .show();
//            return true;
//        });
//    }


}
