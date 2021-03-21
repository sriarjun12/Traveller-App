package com.abort.traveler.Common;

import com.abort.traveler.Model.CategoryModel;
import com.abort.traveler.Model.PlacesModel;
import com.abort.traveler.Model.StatesModel;
import com.abort.traveler.Model.SummaryModel;
import com.abort.traveler.Model.UserModel;

import java.util.List;

public class Common {
    public static final String USER_REF = "users";
    public static final String BANNER_REF = "Banners";
    public static final String CATEGORY_HOME = "Types";
    public static final String STATES = "States";
    public static final String PACKAGE = "package";
    public static final String BOOKING = "bookings";
    public static UserModel currentUserModel;
    public static List<CategoryModel> categoryHome;
    public static CategoryModel categorySelected;
    public static StatesModel stateSelected;
    public static PlacesModel placeSelected;
    public static SummaryModel currentSymaryModel;
}
