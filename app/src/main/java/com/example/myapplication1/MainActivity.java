package com.example.myapplication1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    protected ListView listView;
    protected listViewAdapter listViewAdapter;
    List<event> listEvent = new ArrayList<>();
    public FragmentManager fragmentManager = getSupportFragmentManager();

    private ProgressBar progressBar;
    protected DrawerLayout drawer;
    protected ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase mDatabase;

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference=mDatabase.getReference();
        Toolbar mToolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.drawerOpen, R.string.drawerClosed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//        setSupportActionBar(mToolbar);
        mToolbar.setTitle("CSIS 19");

        initUI();
        addSingleEventListener();
//        addChildEventListener();
        FragmentTransaction FragTrans = fragmentManager.beginTransaction();
        Fragment eventFragment = new eventFragment();
        FragTrans.add(R.id.homeFrame,eventFragment,"EventActivity");
        FragTrans.commit();


//        setFabClickListener();
//        setListViewItemListener();
//        setListViewLongClickListener();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void initUI(){
//        Log.d("InitUI","UI initializing");
        progressBar = findViewById(R.id.progressBar);
//        listView = findViewById(R.id.listView);
    }
//This is for the menu on the navbar...the ... on the right top
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

        Fragment fragment = null;

        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action        } else if (id == R.id.nav_gallery) {
        fragment = new cameraFragment();

        } else if (id == R.id.nav_home) {
            fragment = new eventFragment();
        } else if (id == R.id.nav_maps) {

        }

        if(fragment !=null){
            FragmentTransaction FragTrans = fragmentManager.beginTransaction();
            FragTrans.replace(R.id.homeFrame,fragment);
            FragTrans.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List getEventList(){
        return  this.listEvent;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    //private void setListViewItemListener(){
      //  listView.setOnItemClickListener((adapterView, view, i, l) -> {
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("edit", true);
//            bundle.putParcelable("person", Parcels.wrap(listEvent.get(i)));
            //Intent intent = new Intent(this, .class);
//            intent.putExtras(bundle);
            //startActivity(intent);
     //   });
    //}

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
