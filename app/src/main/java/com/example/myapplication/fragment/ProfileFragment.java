package com.example.myapplication.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ResultPhotosAdapter;
import com.example.myapplication.database.PinRepository;
import com.example.myapplication.helper.SpacesItemDecoration;
import com.example.myapplication.model.Photo;
import com.example.myapplication.model.Pin;

import java.util.ArrayList;

public class ProfileFragment extends BaseFragment {
    private EditText edt_search;
    private RecyclerView rvSavedPhotos;
    private ResultPhotosAdapter photosAdapter;
    private PinRepository pinRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshAdapter();
    }

    private void initViews(View view) {
        rvSavedPhotos = view.findViewById(R.id.rv_saved_photos);

        rvSavedPhotos.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        rvSavedPhotos.addItemDecoration(decoration);
        pinRepository = new PinRepository(requireActivity().getApplication());
        photosAdapter = new ResultPhotosAdapter(requireContext());
        rvSavedPhotos.setAdapter(photosAdapter);
    }

    private void refreshAdapter() {
        ArrayList<Photo> photoList = new ArrayList<>();
        for (Pin item : pinRepository.getAllSavedPhotos()) {
            if (item.getPhotoItem() != null) {
                photoList.add(item.getPhotoItem());
            }
        }
        photosAdapter.addNewPhotos(photoList);
    }
}
