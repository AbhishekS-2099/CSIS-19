package com.example.myapplication1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
            case "Shriram Vasudevan": speakerImage.setImageResource(R.drawable.shriram);
                break;
            case "Sreekutty O U": speakerImage.setImageResource(R.drawable.sreekuttyou);
                break;
            case "Sobodh Gajare": speakerImage.setImageResource(R.drawable.subodhgajare);
                break;
            case "Gopi Krishnan": speakerImage.setImageResource(R.drawable.gopi_krishnan);
                break;
            case "Anuj Dugal": speakerImage.setImageResource(R.drawable.anuj);
                break;
            case "Supriya V": speakerImage.setImageResource(R.drawable.supriya);
                break;
            case "Shruti Sridharan": speakerImage.setImageResource(R.drawable.shrutisridharan);
                break;
            case "Moosa Mehar MP": speakerImage.setImageResource(R.drawable.moosameharmp);
                break;
            case "Radhakrishnan KG": speakerImage.setImageResource(R.drawable.radhakrishnan);
                break;
            default:
                speakerImage.setImageResource(R.drawable.ic_launcher);
        }
    }
}