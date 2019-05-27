package com.example.hospitalreviewsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterForHospital extends RecyclerView.Adapter<AdapterForHospital.ViewHolder> {
Context context;
ArrayList<HospitalCard> arraylist;
TextView titles,detail,place;
RatingBar ratingBar;
 public AdapterForHospital(Context context, ArrayList<HospitalCard> arraylist){
     this.arraylist = arraylist;
     this.context = context;

 }

    @NonNull
    @Override
    public AdapterForHospital.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_for_hospital,viewGroup,false);
        return new AdapterForHospital.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterForHospital.ViewHolder viewHolder, final int i) {

        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        ratingBar = (RatingBar)cardView.findViewById(R.id.ratingBar);
        titles = (TextView)cardView.findViewById(R.id.title);
        detail = (TextView)cardView.findViewById(R.id.details);
        place = (TextView)cardView.findViewById(R.id.place);
        ratingBar.setRating(Float.parseFloat(arraylist.get(i).getRating()));
        titles.setText(arraylist.get(i).getTitle().toString());
        detail.setText(arraylist.get(i).getDetails().toString());
        place.setText(  arraylist.get(i).getPlace().toString());

cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context.getApplicationContext(),MainPage.class);
        intent.putExtra("hospitalid",arraylist.get(i).toString());
        context.startActivity(intent);
    }
});


    }

    @Override
    public int getItemCount() {
        int len = arraylist.size();
        return len ;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_forhospital);

        }
    }



}
