package com.abort.traveler.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abort.traveler.Adapter.CategoryHomeAdapter;
import com.abort.traveler.Adapter.StatesAdapter;
import com.abort.traveler.Common.SpacesItemDecoration;
import com.abort.traveler.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GalleryFragment extends Fragment {

    private GalleryViewModel mViewModel;
    Unbinder unbinder;
    @BindView(R.id.recycler_category_home)
    RecyclerView recycler_home_category;
    StatesAdapter statesAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        unbinder = ButterKnife.bind(this,root);
        mViewModel.getCategoryListMutable().observe(getViewLifecycleOwner(),categoryModelList -> {
            statesAdapter = new StatesAdapter(getContext(),categoryModelList);
            recycler_home_category.setAdapter(statesAdapter);
        });
        initView();
        return root;
    }
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler_home_category.setLayoutManager(layoutManager);
        recycler_home_category.addItemDecoration(new SpacesItemDecoration(8));
        mViewModel.loadCategories();
    }
    @Override
    public void onStart() {
        super.onStart();
        mViewModel.loadCategories();
    }
    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadCategories();
    }
}