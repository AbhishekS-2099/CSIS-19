package com.example.myapplication1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<event> listEvent;

    public listViewAdapter(Activity activity, List<event> listEvent){
        this.activity = activity;
        this.listEvent=listEvent;
    }

    @Override
    public int getCount() {
        return listEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return listEvent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row_listview, null);

            holder = new ViewHolder();
            holder.eventName = convertView.findViewById(R.id.eventName);
            holder.eventDate = convertView.findViewById(R.id.eventDate);
            holder.eventType = convertView.findViewById(R.id.eventType);
            holder.eventImage = convertView.findViewById(R.id.eventImage);
            //holder.eventTimeStart = convertView.findViewById(R.id.)
            holder.eventLocation = convertView.findViewById(R.id.eventLocation);
            holder.eventImageIcon = convertView.findViewById(R.id.eventImageIcon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.eventName.setText(listEvent.get(position).getEventName());
        holder.eventDate.setText(listEvent.get(position).getEventDate());
        holder.eventType.setText(listEvent.get(position).getEventType() + "");
        holder.eventLocation.setText(listEvent.get(position).getEventLocation());
        holder.eventImageIcon.setImageResource(R.drawable.ic_location_on);

        String ET =listEvent.get(position).getEventType();
        switch (ET){
            case "Registration":
                holder.eventImage.setImageResource(R.drawable.reg);
                break;
            case "Inauguration":
                holder.eventImage.setImageResource(R.drawable.inauguration);
                break;
            case "General":
                holder.eventImage.setImageResource(R.drawable.general);
                break;
            case "Food":
                holder.eventImage.setImageResource(R.drawable.food);
                break;
            case "Cultural":
                holder.eventImage.setImageResource(R.drawable.cultural);
                break;
            case "Entertainment":
                holder.eventImage.setImageResource(R.drawable.entertainment);
                break;
            case "5G":
                holder.eventImage.setImageResource(R.drawable.fiveg);
                break;
            case "App Dev":
                holder.eventImage.setImageResource(R.drawable.mobileapp);
                break;
            case "WIC":
                holder.eventImage.setImageResource(R.drawable.wic);
                break;
            case "TISP":
                holder.eventImage.setImageResource(R.drawable.tisp);
                break;
            case "IOT":
                holder.eventImage.setImageResource(R.drawable.iot);
                break;
            case "AI":
                holder.eventImage.setImageResource(R.drawable.ai);
                break;
            case "ML":
                holder.eventImage.setImageResource(R.drawable.ml4);
                break;
            case "Entrepreneurship":
                holder.eventImage.setImageResource(R.drawable.entrepreneur);
                break;
            case "Closing Track":
                holder.eventImage.setImageResource(R.drawable.closing);
                break;
            default:
                holder.eventImage.setImageResource(R.drawable.baseline_event_black_48dp);
                break;
        }
        return convertView;
    }
    class ViewHolder{
        TextView eventName;
        TextView eventDate;
        TextView eventType;
        ImageView eventImage;
        TextView eventLocation;
        ImageView eventImageIcon;
        //TextView eventTimeStart;
        //TextView eventTimeEnd;
    }

}
