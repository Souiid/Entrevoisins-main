package com.openclassrooms.entrevoisins;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDescriptionActivity extends AppCompatActivity {

    NeighbourApiService mApiService;

    @BindView(R.id.neighbourIV)
    ImageView neighbourIV;
    @BindView(R.id.nameTitleTV)
    TextView nameTitleTV;
    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.placeTV)
    TextView placeTV;
    @BindView(R.id.phoneNumberTV)
    TextView phoneNumberTv;
    @BindView(R.id.linkTV)
    TextView linkTV;
    @BindView(R.id.descriptionTV)
    TextView descriptionTV;
    @BindView(R.id.favoriteButton)
    ImageButton favoriteButton;
    @BindView(R.id.backButton)
    ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_description);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("neighbour")) {
            Neighbour neighbour = intent.getParcelableExtra("neighbour");
            if (neighbour != null) {
                nameTitleTV.setText(neighbour.getName());
                nameTV.setText(neighbour.getName());
                placeTV.setText(neighbour.getAddress());
                phoneNumberTv.setText(neighbour.getPhoneNumber());
                linkTV.setText(neighbour.getAvatarUrl());
                descriptionTV.setText(neighbour.getAboutMe());
                Glide.with(neighbourIV.getContext())
                        .load(neighbour.getAvatarUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(neighbourIV);
                fillFavoriteButton(neighbour);
                addToFavorite(neighbour);
            }

        }

        clickOnBackButton();

    }

    private void clickOnBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


     private void addToFavorite(Neighbour neighbour) {
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                neighbour.setIsFavorite(!neighbour.getIsFavorite());
                fillFavoriteButton(neighbour);
                mApiService.updateNeighbours(neighbour);
            }
        });

    }

    private void fillFavoriteButton(Neighbour neighbour) {
        ColorFilter colorFilter = new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_IN);
        if (neighbour.getIsFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
            favoriteButton.setColorFilter(colorFilter);
        }else {
            favoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
            favoriteButton.setColorFilter(colorFilter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}