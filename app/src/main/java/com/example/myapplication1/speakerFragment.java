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

      speaker speaker = new speaker("Speaker1","AI1","Hey im speaker1");
      speakerList.add(speaker);
      speaker = new speaker("Speaker2","AI2","Hey im speaker2");
      speakerList.add(speaker);
      speaker = new speaker("Speaker3","AI3","Hey im speaker3");
      speakerList.add(speaker);
      speaker = new speaker("Speaker4","AI4","Hey im speaker4");
      speakerList.add(speaker);
      speakerAdapter.notifyDataSetChanged();
   }


}
