package com.example.lap60020_local.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lap60020_local.finalproject.Ui.MainActivity;

import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    RecyclerView rvContacts;
    ArrayList<Reviews> arr_ds = new ArrayList<Reviews>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        initArray();

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        // Create adapter passing in the sample user data
        ReviewAdapter adapter = new ReviewAdapter(arr_ds);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

//        rvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(ReviewListActivity.this, ReviewDetailActivity.class);
//                intent.putExtra("name", arr_ds.get(position).getTen().toString());
//                intent.putExtra("content", arr_ds.get(position).getContent().toString());
//                startActivity(intent);
//            }
//        });

    }

    public void initArray() {
        arr_ds.add(new Reviews("Steve", "A review by Steve."));
        arr_ds.add(new Reviews("Jordan", "A review by Jordan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus mollis imperdiet finibus. Etiam quis augue lacus. Donec viverra mi a neque tristique, sit amet dictum augue imperdiet. Integer posuere vehicula fringilla. Nam nec ex ut libero cursus viverra ut rutrum turpis. Proin ac imperdiet elit, ac iaculis lectus."));
        arr_ds.add(new Reviews("Harry", "A review by Harry. Sed ultrices tortor rhoncus est tempor, at euismod metus pretium. Fusce laoreet luctus justo, at scelerisque massa faucibus nec. Vestibulum eu lobortis urna. Fusce ullamcorper velit at orci tempus scelerisque. Sed faucibus felis eu convallis sagittis. Quisque consequat vitae nisi at fermentum."));
        arr_ds.add(new Reviews("Tom", "A review by Tom. Etiam cursus dignissim purus, non suscipit diam convallis at. Donec finibus erat nunc, efficitur aliquam lectus semper malesuada. Fusce non nulla id tortor varius tempor ultricies vel diam. Donec eget massa risus. Donec nibh tortor, laoreet vitae molestie sit amet, feugiat vulputate orci. Integer ultricies nulla et mauris lobortis, molestie luctus nisl porttitor. Curabitur mattis sapien sapien, non efficitur tortor dapibus ut."));
        arr_ds.add(new Reviews("Elisa", "A review by Elisa. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam gravida porttitor lorem, eu tincidunt sem dictum et. Vestibulum ac ante eget ex suscipit molestie. "));
    }
}
