package com.example.eecs4443_lab2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.eecs4443_lab2.R;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private int imageResId;
    private String title;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailDescription = findViewById(R.id.detail_description);

        if (savedInstanceState != null) {
            imageResId = savedInstanceState.getInt("image");
            title = savedInstanceState.getString("title");
            description = savedInstanceState.getString("description");
        } else {
            imageResId = getIntent().getIntExtra("image", R.drawable.ic_launcher_background);
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
        }

        detailImage.setImageResource(imageResId);
        detailTitle.setText(title);
        detailDescription.setText(description);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("image", imageResId);
        outState.putString("title", title);
        outState.putString("description", description);
    }


}
