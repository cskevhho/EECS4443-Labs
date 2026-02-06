package com.example.eecs4443_lab2.model;

import com.example.eecs4443_lab2.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Data model representing a Cat item.
 *
 * NOTE:
 *      This is the item data model, this should only contain the data fields
 *      and their getters/setters, no logic should be implemented here.
 *      Logic will be kept in ItemAdapter and DetailActivity (need to double check
 *      on the DetailActivity bit). - Kevin 20260206
 */
public class Cat {
    private String title;
    private int imageResId;
    private String description;

    public Cat(String title, int imageResId, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
    }

    /**
     * Returns hardcoded list of 15 sample Cat items.
     *
     * NOTE:
     *      Please don't change or remove any of the cats. 😠 - Kevin 20260206
     */
    public static List<Cat> getSampleCats() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Whiskers", R.drawable.cat1, "Playful and energetic."));
        cats.add(new Cat("Shadow", R.drawable.cat2, "Loves to nap in the sun."));
        cats.add(new Cat("Mittens", R.drawable.cat3, "Curious and friendly."));
        cats.add(new Cat("Tiger", R.drawable.cat4, "Bold and adventurous."));
        cats.add(new Cat("Luna", R.drawable.cat5, "Gentle and affectionate."));
        cats.add(new Cat("Simba", R.drawable.cat6, "King of the house."));
        cats.add(new Cat("Bella", R.drawable.cat7, "Sweet and cuddly."));
        cats.add(new Cat("Oliver", R.drawable.cat8, "Always hungry."));
        cats.add(new Cat("Cleo", R.drawable.cat9, "Elegant and graceful."));
        cats.add(new Cat("Leo", R.drawable.cat10, "Loves to climb."));
        cats.add(new Cat("Socks", R.drawable.cat11, "Has unique markings."));
        cats.add(new Cat("Pumpkin", R.drawable.cat12, "Orange and fluffy."));
        cats.add(new Cat("Nala", R.drawable.cat13, "Smart and playful."));
        cats.add(new Cat("Milo", R.drawable.cat14, "Likes to explore."));
        cats.add(new Cat("Ruby", R.drawable.cat15, "Shy but loving."));
        return cats;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
