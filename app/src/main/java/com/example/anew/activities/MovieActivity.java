package com.example.anew.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anew.BaseActivity;
import com.example.anew.R;
import com.example.anew.model.DataList;
import com.example.anew.model.MovieResult;
import com.example.anew.model.Result;
import com.example.anew.network.APIClient;
import com.example.anew.network.APIInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends BaseActivity {

    @BindView(R.id.movieCover)
    RoundedImageView movieCover;
    @BindView(R.id.movieTitle)
    TextView movieTitle;
    @BindView(R.id.movieReleaseDate)
    TextView movieReleaseDate;
    @BindView(R.id.movieImdbRating)
    TextView movieImdbRating;
    @BindView(R.id.movieDescription)
    TextView movieDescription;
    @BindView(R.id.aboutTitle)
    TextView aboutTitle;
    @BindView(R.id.aboutStatus)
    TextView aboutStatus;
    @BindView(R.id.aboutRuntime)
    TextView aboutRuntime;
    /*@BindView(R.id.aboutType)
    TextView aboutType;*/
    @BindView(R.id.aboutPremiere)
    TextView aboutPremiere;
    @BindView(R.id.aboutBudget)
    TextView aboutBudget;
    @BindView(R.id.aboutRevenue)
    TextView aboutRevenue;
    @BindView(R.id.aboutHomePage)
    TextView aboutHomePage;

    @BindView(R.id.bookTicket)
    TextView bookTicket;



    int sID;
    private APIInterface apiInterface;
    private MovieActivity mMovieActivity;
    private ArrayList<MovieResult> list =new ArrayList<>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        getIntentFromID();
        hitMovieDetailsAPI(sID);

    }

    private void initView(int position) {

        movieTitle.setText(list.get(position).getOriginal_title());
        movieDescription.setText(list.get(position).getOverview());
        aboutTitle.setText(list.get(position).getOriginal_title());
        aboutStatus.setText(list.get(position).getStatus());
        /*aboutRuntime.setText(list.get(position).getRuntime());*/
        /*aboutType.setText(list.get(position).getType());*/
        /*aboutPremiere.setText(list.get(position).getRelease_date());*/
      /*  aboutBudget.setText(list.get(position).getBudget());*/
        /*aboutRevenue.setText(list.get(position).getRevenue());*/
        aboutHomePage.setText(list.get(position).getHomepage());

        String commonPosterURL = "https://image.tmdb.org/t/p/w500";
        String imageURL = (commonPosterURL + list.get(position).getPoster_path());
        Glide.with(this)
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(movieCover);

        bookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog createBottom = new BottomSheetDialog(MovieActivity.this,
                        R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.layout_book_movie,
                                (LinearLayout) findViewById(R.id.createPostBottomSheet));
                TextView create = (TextView) bottomSheetView.findViewById(R.id.createTV);
                create.setText("Book Ticket");
                TextView balconyTv = (TextView) bottomSheetView.findViewById(R.id.balconyTv);
                balconyTv.setText("Balcony");
                TextView firstClassTv = (TextView) bottomSheetView.findViewById(R.id.firstClassTv);
                firstClassTv.setText("First-Class");
                Button selectSeats = (Button) bottomSheetView.findViewById(R.id.selectSeats);
                firstClassTv.setText("Select seats");
                selectSeats.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MovieActivity.this, "Select seats",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                createBottom.setContentView(bottomSheetView);
                createBottom.show();
            }
        });
    }

    private void hitMovieDetailsAPI(int sID) {
        apiInterface= APIClient.getClient().create(APIInterface.class);
        HashMap<String, String> params = new HashMap<>();
        Call<ResponseBody> call=apiInterface.loadMovieDetails(sID,params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful() && response.body()!=null) {
                    String strResponse = null;
                    try {
                        strResponse = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("--Response--", strResponse);
                    MovieResult mv = new Gson().fromJson(strResponse, MovieResult.class);
                    list.add(mv);

                    initView(0);
                } else{
                    Toast.makeText(mMovieActivity, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mMovieActivity, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getIntentFromID() {
        sID = getIntent().getIntExtra("sID", sID);
    }
}