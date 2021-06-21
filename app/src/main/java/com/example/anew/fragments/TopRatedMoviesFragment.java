package com.example.anew.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.anew.R;
import com.example.anew.activities.MovieActivity;
import com.example.anew.adapters.PopularMoviesAdapter;
import com.example.anew.adapters.TopRatesMoviesAdapter;
import com.example.anew.model.DataList;
import com.example.anew.model.Result;
import com.example.anew.network.APIClient;
import com.example.anew.network.APIInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMoviesFragment extends Fragment {

    private Context context;
    @BindView(R.id.topRatedRecycler)
    RecyclerView topRatedRecycler;
    @BindView(R.id.llContainer)
    LinearLayout llContainer;
    private TopRatesMoviesAdapter adapter;
    private Activity mActivity;
    private ArrayList<DataList> list =new ArrayList<DataList>();;
    private Unbinder unbinder;

    //API
    private APIInterface apiInterface;
    HashMap<String, String> hashMap = new HashMap<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopRatedMoviesFragment() {
        // Required empty public constructor
    }

    public static TopRatedMoviesFragment newInstance(String param1, String param2) {
        TopRatedMoviesFragment fragment = new TopRatedMoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    private void initView() {
        GridLayoutManager layoutManager=new GridLayoutManager(context,3);
        topRatedRecycler.setLayoutManager(layoutManager);
        adapter = new TopRatesMoviesAdapter(context,TopRatedMoviesFragment.this, list);
        topRatedRecycler.setAdapter(adapter);
    }

    private void hitAPI() {
        apiInterface=APIClient.getClient().create(APIInterface.class);
        HashMap<String, String> params = new HashMap<>();
        Call<ResponseBody> call=apiInterface.loadTopRated(params);
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
                    Result popularMovies = new Gson().fromJson(strResponse, Result.class);
                    list.addAll(popularMovies.getResults());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hitAPI();
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    public void getMovieByID(int sID) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra("sID", sID);
        startActivity(intent);
    }
}