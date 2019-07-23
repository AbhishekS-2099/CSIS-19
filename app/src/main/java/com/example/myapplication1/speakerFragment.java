package com.example.myapplication1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class speakerFragment extends Fragment {

   protected RecyclerView recyclerView;
   protected speakerAdapter speakerAdapter;
   protected ArrayList<speaker>speakerList = new ArrayList<>();

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_speaker,null);
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);

      recyclerView = view.findViewById(R.id.recyclerView);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      speakerAdapter = new speakerAdapter(getContext(),speakerList);
      recyclerView.setAdapter(speakerAdapter);
      createSpeakerData();
   }
   private void createSpeakerData(){

      speaker speaker = new speaker("Shriram Vasudevan","IOT/AI","Innovation And You");
      speakerList.add(speaker);
      speaker = new speaker("Sreekutty O U","Country Ambassador, She love ","Kerala Startup Mission");
      speakerList.add(speaker);
      speaker = new speaker("Sobodh Gajare","5G","Chief Architect, CISCO");
      speakerList.add(speaker);
      speaker = new speaker("Gopi Krishnan","Machine Learning","Fullcontact Director, Tinkerhub");
      speakerList.add(speaker);
      speaker = new speaker("Anuj Dugal","Inovation Program Manager,Corporate Affairs","Intel India");
      speakerList.add(speaker);
      speaker = new speaker("Supriya V","Machine Learning","Data Science, EY");
      speakerList.add(speaker);
      speaker = new speaker("Shruti Sridharan","Business Intelligence","9X Certified Saleforce Developer");
      speakerList.add(speaker);
      speaker = new speaker("Moosa Mehar MP","Entrepreneurship","Co-Founder, TinkerHub Foundation");
      speakerList.add(speaker);

      speaker = new speaker("Radhakrishnan KG","","Founder/Director WebNamaste");
      speakerList.add(speaker);
      speakerAdapter.notifyDataSetChanged();
   }


}
