package com.example.myapplication1;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;
    private FloatingActionButton fab;
    private ListView listView;
    private listViewAdapter listViewAdapter;
    private List<event> listEvent = new ArrayList<>();
    public Toolbar mToolbar;
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        progressBar = findViewById(R.id.progressBar);
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
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

//FAB CLICK LISTENER

//private void setFabClickListener() {
//        fab.setOnClickListener(e -> {
//        startActivity(new Intent(this, EditPersonActivity.class));
//        });
//        }

}
