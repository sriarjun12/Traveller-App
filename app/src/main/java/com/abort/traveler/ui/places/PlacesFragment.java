package com.abort.traveler.ui.places;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abort.traveler.Adapter.PlacesAdapter;
import com.abort.traveler.Adapter.StatesAdapter;
import com.abort.traveler.Common.Common;
import com.abort.traveler.Common.SpacesItemDecoration;
import com.abort.traveler.Model.SpotsModel;
import com.abort.traveler.R;
import com.abort.traveler.ui.gallery.GalleryViewModel;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlacesFragment extends Fragment {

    private PlacesViewModel mViewModel;
    Unbinder unbinder;
    @BindView(R.id.recycler_category_home)
    RecyclerView recycler_home_category;
    PlacesAdapter placesAdapter;
    public static PlacesFragment newInstance() {
        return new PlacesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(PlacesViewModel.class);
        View root= inflater.inflate(R.layout.places_fragment, container, false);
        unbinder = ButterKnife.bind(this,root);
        mViewModel.getCategoryListMutable().observe(getViewLifecycleOwner(),categoryModelList -> {
            placesAdapter = new PlacesAdapter(getContext(),categoryModelList);
            recycler_home_category.setAdapter(placesAdapter);
        });
        initView();
        return root;
    }
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler_home_category.setLayoutManager(layoutManager);
        recycler_home_category.addItemDecoration(new SpacesItemDecoration(8));
        mViewModel.loadPlaces();
    }
    @Override
    public void onStart() {
        super.onStart();
        mViewModel.loadPlaces();
    }
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadPlaces();
    }
}