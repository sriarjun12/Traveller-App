package com.abort.traveler.ui.booking;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abort.traveler.Common.Common;
import com.abort.traveler.Model.SummaryModel;
import com.abort.traveler.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingFragment extends Fragment {

    private BookingViewModel mViewModel;
    Unbinder unbinder;
    @BindView(R.id.placename)
    TextView placename;
    @BindView(R.id.placescover)
    TextView placescover;
    @BindView(R.id.packageprice)
    TextView packageprice;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.trans_id)
    TextView trans_id;
    @BindView(R.id.hindinglayout)
    LinearLayout hindinglayout;
    public static BookingFragment newInstance() {
        return new BookingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.booking_fragment, container, false);
        unbinder = ButterKnife.bind(this,root);
        FirebaseDatabase.getInstance().getReference(Common.BOOKING)
                .child(Common.currentUserModel.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        SummaryModel summaryModel=snapshot.getValue(SummaryModel.class);
                        if(summaryModel!=null) {
                            placename.setText(summaryModel.getName());
                            placescover.setText(summaryModel.getSpotscover());
                            trans_id.setText(summaryModel.getTransactionId());
                            date.setText(summaryModel.getDate());
                            packageprice.setText(String.valueOf(summaryModel.getPrice()));
                        }
                        else{
                            hindinglayout.setVisibility(View.GONE);
                            placename.setText("No Booking Yet ");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        // TODO: Use the ViewModel
    }

}