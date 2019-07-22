package com.example.myapplication1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class speakerHolder extends RecyclerView.ViewHolder {

    private TextView speakerName;
    private TextView speakerBrief;
    private TextView speakerTopic;
    private ImageView speakerImage;

    public speakerHolder(View view){
        super(view);
        speakerName = view.findViewById(R.id.speakerName);
        speakerBrief = view.findViewById(R.id.speakerBrief);
        speakerTopic = view.findViewById(R.id.speakerTopic);
        speakerImage = view.findViewById(R.id.speakerImage);
    }
    void setDetails(speaker speaker){
        speakerName.setText(speaker.getName());
        speakerTopic.setText(speaker.getTalkOn());
        speakerBrief.setText(speaker.getBriefAbout());
        String imageCheck = speaker.getName();
        switch (imageCheck){
            case "Speaker1": speakerImage.setImageResource(R.drawable.anuj);
            break;
            case "Speaker2": speakerImage.setImageResource(R.drawable.gopi_krishnan);
            break;
        }










    }
}