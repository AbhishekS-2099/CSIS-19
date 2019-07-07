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
        holder.eventImageIcon.setImageResource(R.drawable.baseline_location_on_black_18dp);
        holder.eventImage.setImageResource(R.drawable.baseline_event_black_48dp);
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
