package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends ArrayAdapter<Item> {

    ArrayList<Item> animalList = new ArrayList();

    public MyAdapter2(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        animalList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.princi_model, null);
        TextView textView = (TextView) v.findViewById(R.id.textViewl);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageViewl);
        textView.setText(animalList.get(position).getAnimalName());
        imageView.setImageResource(animalList.get(position).getAnimalImage());
        return v;

    }

}
