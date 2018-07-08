package com.example.lap60020_local.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAdapterCustom extends ArrayAdapter<Reviews> {
    public MyAdapterCustom(@NonNull Context context, @NonNull ArrayList<Reviews> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_list, parent, false);
        }

        TextView tvTen = (TextView) convertView.findViewById(R.id.tvTen);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);
//        WebView wvContent = (WebView) convertView.findViewById(R.id.wvContent);
        TextView tvIcon = (TextView) convertView.findViewById(R.id.tvIcon);

        Reviews review = getItem(position);
        if (review != null) {
            tvTen.setText(review.getTen().toString());
            tvContent.setText(review.getContent().toString());
//            String htmlText = " %s ";
//            String myData = review.getContent().toString();
//
//            wvContent.loadData(String.format(htmlText, myData), "text/html", "utf-8");

            Random random = new Random();
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            GradientDrawable drawable = (GradientDrawable) tvIcon.getBackground();
            drawable.setColor(color);
            tvIcon.setText(review.getTen().toString().charAt(0) + "");
        }


        return convertView;
    }
}
