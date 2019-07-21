package com.example.myapplication1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class eventFragment extends Fragment {
    protected listViewAdapter listViewAdapter;
    protected ListView listView;
    private DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;
    List<event> listEvent = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference=mDatabase.getReference();

        listView = view.findViewById(R.id.listView);
//        listViewAdapter listViewAdapter = ((MainActivity)this.getActivity()).getListViewAdapter();
        listViewAdapter =new listViewAdapter(getActivity(),listEvent);
        listView.setAdapter(listViewAdapter);
        addChildEventListener();

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
}
