package com.abort.traveler.CallBacks;

import com.abort.traveler.Model.PlacesModel;
import com.abort.traveler.Model.StatesModel;

import java.util.List;

public interface IPlacesCallBacks {
    void onCategoryLoadSuccess(List<PlacesModel> placesModelList);
    void onCategoryLoadFailed(String message);
}
