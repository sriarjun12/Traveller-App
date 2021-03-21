package com.abort.traveler.ui.summary;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.abort.traveler.Common.Common;
import com.abort.traveler.R;
import com.razorpay.Checkout;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SummaryFragment extends Fragment {

    private SummaryViewModel mViewModel;
    Unbinder unbinder;
    @BindView(R.id.placename)
    TextView placename;
    @BindView(R.id.discription)
    TextView discription;
    @BindView(R.id.placescover)
    TextView placescover;
    @BindView(R.id.packageprice)
    TextView packageprice;
    @BindView(R.id.bookbtn)
    Button bookbtn;
    @BindView(R.id.datePicker)
    DatePicker datePicker;
    public static SummaryFragment newInstance() {
        return new SummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.summary_fragment, container, false);
        unbinder = ButterKnife.bind(this,root);
        placename.setText(Common.currentSymaryModel.getName());
        discription.setText(Common.currentSymaryModel.getDiscription());
        placescover.setText(Common.currentSymaryModel.getSpotscover());
        packageprice.setText(String.valueOf(Common.currentSymaryModel.getPrice()));
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                Common.currentSymaryModel.setDate(day + "-" + month + "-" + year);
                startpayment();
            }
        });


        return root;
    }
    private void startpayment() {

        final Checkout co = new Checkout();
        final Activity activity = this.getActivity();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Explorer");
            options.put("description", "Product Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://firebasestorage.googleapis.com/v0/b/traveller-dd3e9.appspot.com/o/logo_explorer.jpg?alt=media&token=c1f1bf93-4a65-4017-b105-6de1e813d150");
            options.put("currency", "INR");
            options.put("amount", Common.currentSymaryModel.getPrice()*100);
            JSONObject preFill = new JSONObject();
            preFill.put("email", Common.currentUserModel.getEmail());
            preFill.put("contact", Common.currentUserModel.getPhone());
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}