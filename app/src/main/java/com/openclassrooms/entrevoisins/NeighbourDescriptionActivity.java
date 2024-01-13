package com.openclassrooms.entrevoisins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDescriptionActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_description);
        ButterKnife.bind(this);
        favoriteButton.bringToFront();
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
            }

        }

    }
}