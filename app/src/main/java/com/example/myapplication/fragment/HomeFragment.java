package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.HomeAdapter;
import com.example.myapplication.helper.SpacesItemDecoration;
import com.example.myapplication.model.Photo;
import com.example.myapplication.network.RetrofitHttp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private int currentPage = 1;
    private int perPage = 30;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerPins);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        recyclerView.addItemDecoration(decoration);
        apiPhotoList();
    }

    private void apiPhotoList() {
        RetrofitHttp.photoService.getPhotos(++currentPage, perPage)
                .enqueue(new Callback<ArrayList<Photo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                        ArrayList<Photo> body = response.body();
                        if (body != null) {
                            refreshAdapter(body);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                        Log.e("@@@onFailure", t.getMessage());
                    }
                });
    }

    private void refreshAdapter(ArrayList<Photo> items) {
        adapter = new HomeAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}
