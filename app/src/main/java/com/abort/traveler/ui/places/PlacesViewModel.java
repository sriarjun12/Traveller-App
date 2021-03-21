package com.abort.traveler.ui.places;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abort.traveler.CallBacks.IPlacesCallBacks;
import com.abort.traveler.CallBacks.IStatesCallBackListener;
import com.abort.traveler.Common.Common;
import com.abort.traveler.Model.PlacesModel;
import com.abort.traveler.Model.StatesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlacesViewModel extends ViewModel implements IPlacesCallBacks {

    private MutableLiveData<List<PlacesModel>> placesMutableList;
    private IPlacesCallBacks placesCallBacksLister;
    private MutableLiveData<String> messageError = new MutableLiveData<>();
    public PlacesViewModel() {
        placesCallBacksLister = this;
    }
    public MutableLiveData<String> getMessageError() {
        return messageError;
    }
    public MutableLiveData<List<PlacesModel>> getCategoryListMutable() {
        if (placesMutableList == null)
        {

            placesMutableList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
//            loadCategories();
        }

        return placesMutableList;
    }

    public void loadPlaces() {
        placesCallBacksLister.onCategoryLoadSuccess(Common.stateSelected.getPlaces());

    }

    @Override
    public void onCategoryLoadSuccess(List<PlacesModel> placesModelList) {
        placesMutableList.setValue(placesModelList);
    }

    @Override
    public void onCategoryLoadFailed(String message) {
    }



}