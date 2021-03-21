package com.abort.traveler.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.abort.traveler.Common.Common;
import com.abort.traveler.Eventbus.SummaryClick;
import com.abort.traveler.Model.SummaryModel;
import com.abort.traveler.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

public class SlideshowFragment extends Fragment {
    TextView silverDis,goldDis,platinumDis;
    Button silverBut,goldBut,platinumBut;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        silverDis=root.findViewById(R.id.package_discribtion1);
        goldDis=root.findViewById(R.id.package_discribtion2);
        platinumDis=root.findViewById(R.id.package_discribtion3);
        silverBut=root.findViewById(R.id.btnsilver);
        goldBut=root.findViewById(R.id.btngold);
        platinumBut=root.findViewById(R.id.btnplatinum);

        FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                .child("silver").child("discription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                silverDis.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                .child("gold").child("discription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                goldDis.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                .child("platinum").child("discription").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                platinumDis.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        silverBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                        .child("silver").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SummaryModel summaryModel =snapshot.getValue(SummaryModel.class);
                        summaryModel.setUserid(Common.currentUserModel.getUid());
                        summaryModel.setPhone(Common.currentUserModel.getPhone());
                        Common.currentSymaryModel=summaryModel;
                        EventBus.getDefault().postSticky(new SummaryClick(true, Common.currentSymaryModel));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        goldBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                        .child("gold").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SummaryModel summaryModel =snapshot.getValue(SummaryModel.class);
                        summaryModel.setUserid(Common.currentUserModel.getUid());
                        summaryModel.setPhone(Common.currentUserModel.getPhone());
                        Common.currentSymaryModel=summaryModel;
                        EventBus.getDefault().postSticky(new SummaryClick(true, Common.currentSymaryModel));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        platinumBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference(Common.PACKAGE)
                        .child("platinum").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SummaryModel summaryModel =snapshot.getValue(SummaryModel.class);
                        summaryModel.setUserid(Common.currentUserModel.getUid());
                        summaryModel.setPhone(Common.currentUserModel.getPhone());
                        Common.currentSymaryModel=summaryModel;
                        EventBus.getDefault().postSticky(new SummaryClick(true, Common.currentSymaryModel));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return root;
    }
}