package com.example.hospitalreviewsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterForViewPager extends PagerAdapter {
    private Context context;
    ArrayList<String> list;

    private LayoutInflater layoutInflater;

    public AdapterForViewPager(Context context , ArrayList<String> arrayList) {
        this.context = context;
        this.list = arrayList;

    }

    @Override
    public int getCount() {
        return list.size();
    }


    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_for_sliding,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        Glide.with(context).load(list.get(position)).into(imageView);
        //imageView.setImageResource(image[position]);
        ViewPager vp = (ViewPager)container;
        vp.addView(view,0);
        return view;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }





}
