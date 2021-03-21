package com.abort.traveler.ui.placeDetails;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abort.traveler.Common.Common;
import com.abort.traveler.Eventbus.PlaceClick;
import com.abort.traveler.Eventbus.SummaryClick;
import com.abort.traveler.Model.SpotsModel;
import com.abort.traveler.Model.SummaryModel;
import com.abort.traveler.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlaceDetailsFragment extends Fragment {

    private PlaceDetailsViewModel mViewModel;
    Unbinder unbinder;
    @BindView(R.id.image_sliders)
    ImageSlider mainSlider;
    @BindView(R.id.placename)
    TextView placename;
    @BindView(R.id.discription)
    TextView discription;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.placescover)
    TextView placescover;
    @BindView(R.id.packageprice)
    TextView packageprice;
    @BindView(R.id.bookbtn)
    Button bookbtn;
    public static PlaceDetailsFragment newInstance() {
        return new PlaceDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.place_details_fragment, container, false);
        unbinder = ButterKnife.bind(this,root);
        final List<SlideModel> remoteImages = new ArrayList<>();
        for (SpotsModel spotsModel: Common.placeSelected.getSpots()){
            remoteImages.add(new SlideModel(spotsModel.getImage(),"", ScaleTypes.CENTER_CROP));
        }
        mainSlider.setImageList(remoteImages);
        placename.setText(Common.placeSelected.getName());
        discription.setText(Common.placeSelected.getDescription());
        temperature.setText(String.valueOf(Common.placeSelected.getTempurature()));
        placescover.setText(Common.placeSelected.getSpotscover());
        packageprice.setText(String.valueOf(Common.placeSelected.getPrice()));
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SummaryModel summaryModel=new SummaryModel();
                summaryModel.setName(Common.placeSelected.getName());
                summaryModel.setDiscription(Common.placeSelected.getDescription());
                summaryModel.setPrice((int)Common.placeSelected.getPrice());
                summaryModel.setSpotscover(Common.placeSelected.getSpotscover());
                summaryModel.setUserid(Common.currentUserModel.getUid());
                summaryModel.setPhone(Common.currentUserModel.getPhone());
                Common.currentSymaryModel=summaryModel;
                EventBus.getDefault().postSticky(new SummaryClick(true, Common.currentSymaryModel));
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlaceDetailsViewModel.class);
        // TODO: Use the ViewModel
    }
}