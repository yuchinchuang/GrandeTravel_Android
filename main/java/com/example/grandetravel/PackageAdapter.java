package com.example.grandetravel;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PackageAdapter extends ArrayAdapter<Package> {

    public PackageAdapter(Context context, ArrayList<Package> packages) {
        super(context,0, packages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Package pack = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent,false);
        }

        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        TextView tvLocation = (TextView)convertView.findViewById(R.id.tvLocation);
        TextView tvDetails = (TextView)convertView.findViewById(R.id.tvDetails);
        TextView tvPrice = (TextView)convertView.findViewById(R.id.tvLPrice);

        tvName.setText(pack.getName());
        tvLocation.setText(pack.getLocation());
        tvDetails.setText(pack.getDescription());
        tvPrice.setText("$" + pack.getPrice());

        return convertView;
    }

    public ArrayList<Package> getList(){
        return new ArrayList<Package>();
    }


}
