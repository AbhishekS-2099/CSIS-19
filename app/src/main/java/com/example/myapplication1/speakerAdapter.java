package com.example.myapplication1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class speakerAdapter extends RecyclerView.Adapter<speakerHolder> {

    private Context context;
    private ArrayList<speaker> speakerList;

    public speakerAdapter(Context context, ArrayList<speaker> speakerList) {
        this.context = context;
        this.speakerList = speakerList;
    }

    public speakerAdapter() {
    }

    @NonNull
    @Override
    public speakerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_view,parent,false);
        return new speakerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull speakerHolder speakerHolder, int i) {

        speaker speaker =  speakerList.get(i);
        speakerHolder.setDetails(speaker);
    }


    @Override
    public int getItemCount() {
        return speakerList.size();
    }
}
