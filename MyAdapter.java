package com.quizearn.rishap.quiz20;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RISHAP on 05-05-2020.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewProfile>profiles;

    public MyAdapter(Context c, ArrayList<NewProfile>p)
    {
        context = c;
        profiles = p;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.email.setText(profiles.get(position).getName());
        holder.score.setText(profiles.get(position).getScore());
        int pos = position+1;
        holder.serial.setText(Integer.toString(pos)+".");
        Picasso.with(context).load(profiles.get(position).getImg()).into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView email,score,serial;
        ImageView profilePic;

        public MyViewHolder(View itemView) {
            super(itemView);
            email = (TextView)itemView.findViewById(R.id.c_email);
            score = (TextView)itemView.findViewById(R.id.c_score);
            serial = (TextView)itemView.findViewById(R.id.serial_no);
            profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
        }
    }
}
