package com.example.gadso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class skilladapter extends RecyclerView.Adapter<skilladapter.MyViewHolder> {
    @NonNull
    Context context;
    ArrayList<skill> list;
    public skilladapter(Context cont, ArrayList<skill> list) {
        this.context = cont;
        this.list = list;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.details);
            text2 = (TextView) itemView.findViewById(R.id.name);
            img = (ImageView) itemView.findViewById(R.id.image);



        }
    }
    @Override
    public skilladapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.skill_rec, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull skilladapter.MyViewHolder holder, int position) {
        holder.text2.setText(list.get(position).getName());
        holder.text1.setText(list.get(position).getHours()+" skill IQ Score , "+list.get(position).getCountry());
        Picasso.with(context).load(list.get(position).getBadgeurl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
