package com.example.lap60020_local.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ReviewDetailActivity extends AppCompatActivity {

    TextView tvTen, tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_list);

        TextView tvTen = (TextView) findViewById(R.id.tvTen);
        TextView tvContent = (TextView) findViewById(R.id.tvContent);
        TextView tvIcon = (TextView) findViewById(R.id.tvIcon);

        Intent intent = getIntent();
        String ten = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");

        tvTen.setText(ten);
        tvContent.setText(content);

        Random random = new Random();
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        GradientDrawable drawable = (GradientDrawable) tvIcon.getBackground();
        drawable.setColor(color);

        tvIcon.setText(ten.charAt(0) + "");




    }

}
