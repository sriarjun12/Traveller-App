package com.abort.traveler.Eventbus;

import com.abort.traveler.Model.PlacesModel;
import com.abort.traveler.Model.StatesModel;

public class PlaceClick {
    private  boolean success;
    private PlacesModel placesModel;

    public PlaceClick(boolean success, PlacesModel placesModel) {
        this.success = success;
        this.placesModel = placesModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PlacesModel getPlacesModel() {
        return placesModel;
    }

    public void setPlacesModel(PlacesModel placesModel) {
        this.placesModel = placesModel;
    }
}
