package com.example.eecs4443_lab2.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eecs4443_lab2.R;
import com.example.eecs4443_lab2.adapter.CatAdapter;
import com.example.eecs4443_lab2.model.Cat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assigns RecyclerView to use GridLayoutManager
        RecyclerView recyclerView = findViewById(R.id.cat_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Passes data to the adapter
        List<Cat> cats = Cat.getSampleCats();
        recyclerView.setAdapter(new CatAdapter(cats));
    }
}