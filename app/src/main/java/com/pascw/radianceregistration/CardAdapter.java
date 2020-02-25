package com.pascw.radianceregistration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class CardAdapter extends PagerAdapter {

    private List<Event> stringlist;
    private Context context;
    private LayoutInflater layoutInflater;


    public CardAdapter(List<Event> stringlist, Context context) {
        this.stringlist = stringlist;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stringlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        ImageView event_image = (ImageView) view.findViewById(R.id.event_image);
        final TextView event_title = (TextView) view.findViewById(R.id.event_title);

        event_image.setImageResource(stringlist.get(position).getImage());
        event_title.setText(stringlist.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (event_title.getText().toString() == "CODE WARS") {
                    view.getContext().startActivity(new Intent(context, SecondPage.class));


                } else if (event_title.getText().toString() == "RECODE IT") {
                    view.getContext().startActivity(new Intent(context, ThirdPage.class));
                } else if (event_title.getText().toString() == "QUIZ MASTER") {
                    view.getContext().startActivity(new Intent(context, ForthPage.class));
                } else if (event_title.getText().toString() == "SHUTTER UP") {
                    view.getContext().startActivity(new Intent(context, FifthPage.class));
                }
            }
        });

        container.addView(view);
        return view;
    }

}

