package com.abort.traveler.CallBacks;

import com.abort.traveler.Model.StatesModel;

import java.util.List;

public interface IStatesCallBackListener {
    void onCategoryLoadSuccess(List<StatesModel> statesModelList);
    void onCategoryLoadFailed(String message);
}
