package com.example.eecs4443_lab2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eecs4443_lab2.R;
import com.example.eecs4443_lab2.model.Cat;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import com.example.eecs4443_lab2.ui.DetailActivity;




/**
 * RecyclerView Adapter for displaying a list of Cat items.
 * Responsible for creating ViewHolders and binding Cat data to item views.
 *
 * NOTE:
 *      This is used to manage and bind data to views. It will be used to
 *      create view holders and bind item data to the views in the RecyclerView.
 *
 *      Remember, Adapters and ViewHolders naturally are tightly coupled for the
 *      most part and are usually written together. - Kevin 20260206
 *
 *      Extending RecyclerView like this is necessary because we need to write
 *      our own adapter to manage the data and views for our iteration of the lab. - Kevin 20260206
 *
 */
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    private final List<Cat> cats;

    public CatAdapter(@NonNull List<Cat> cats) {
        this.cats = (cats != null) ? cats : new ArrayList<>();
    }

    /**
     * ViewHolder class that holds references to the views for each item.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleView;
        final TextView descView;

        /**
         * Need this to bind views to the item layout, inits ViewHolder views.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tile_image);
            titleView = itemView.findViewById(R.id.tile_title);
            descView = itemView.findViewById(R.id.tile_description);
        }
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }


    /**
     * Creates new ViewHolder, Inflates item layout, returns the new ViewHolder
     * with the inflated view.
     *
     * NOTE:
     *      The @NonNull decorator is used to force a non-null return value
     *      to prevent null pointer exceptions.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // make the view holder, inflate the item layout and return the inflated view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tile, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds Cat data to the views in the ViewHolder for position passed into
     * the arguments.
     */
    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the view holder to given data at int position
        Cat cat = cats.get(position);

        holder.titleView.setText(getSafeTitle(holder, cat));
        holder.descView.setText(getSafeDescription(holder, cat));
        holder.imageView.setImageResource(getSafeImageResId(cat));
        holder.imageView.setContentDescription(holder.titleView.getText()); //
        holder.itemView.setOnClickListener(v -> {
            if (cat == null) return;

            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("image", cat.getImageResId());
            intent.putExtra("title", cat.getTitle());
            intent.putExtra("description", cat.getDescription());
            v.getContext().startActivity(intent);
        });
    }


    // The getter functions below are helper methods for null safety in `onBindViewHolder`.
    // The original code block for it would look terrible else how. - Kevin 20260206
    private String getSafeTitle(@NonNull ViewHolder holder, Cat cat) {
        if (cat != null && cat.getTitle() != null && !cat.getTitle().trim().isEmpty()) {
            return cat.getTitle();
        }
        return holder.itemView.getContext().getString(R.string.cat_safe_title);
    }

    private String getSafeDescription(@NonNull ViewHolder holder, Cat cat) {
        if (cat != null && cat.getDescription() != null && !cat.getDescription().trim().isEmpty()) {
            return cat.getDescription();
        }
        return holder.itemView.getContext().getString(R.string.cat_safe_description);
    }

    private int getSafeImageResId(Cat cat) {
        if (cat != null && cat.getImageResId() != 0) {
            return cat.getImageResId();
        }
        return R.drawable.ic_launcher_background;
    }
}