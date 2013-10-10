package com.example.dressup;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Dress> mItems;
    private LayoutInflater mInflater;

    public CustomAdapter(LayoutInflater inflater, ArrayList<Dress> items) {
        mItems = items;
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // TODO: why null here?
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.list_item_text);
            holder.image = (ImageView) convertView.findViewById(R.id.list_item_preview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(mItems.get(position).getName());
        //holder.image.setImageDrawable(Drawable.createFromStream(mItems.get(position).getImage(), null));
        holder.image.setImageResource(mItems.get(position).getImage());

        return convertView;
    }

    class ViewHolder {
        TextView name;
        ImageView image;
    }
}