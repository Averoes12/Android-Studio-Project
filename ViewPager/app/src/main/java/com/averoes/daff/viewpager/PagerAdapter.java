package com.averoes.daff.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] img = {R.drawable.birthday, R.drawable.duaatu,R.drawable.cake, R.drawable.bg, R.drawable.bg2, R.drawable.happy_birthday, R.drawable.kk};


    public PagerAdapter(Context context){

        this.context = context;
    }



    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cutom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(img[position]);

        ViewPager vp = (ViewPager) container;

        vp.addView(view,0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;

        View view = (View) object;
        vp.removeView(view);
    }
}
