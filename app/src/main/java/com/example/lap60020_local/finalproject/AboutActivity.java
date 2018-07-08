package com.example.lap60020_local.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "email@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Recomment Books app]");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        TextView tvKhang = (TextView) findViewById(R.id.tvKhang);
        tvKhang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.emailKhang));
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Recomment Books app]");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        TextView tvTien = (TextView) findViewById(R.id.tvTien);
        tvTien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.emailTien));
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Recomment Books app]");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        TextView tvTruong = (TextView) findViewById(R.id.tvTruong);
        tvTruong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.emailTruong));
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Recomment Books app]");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}
