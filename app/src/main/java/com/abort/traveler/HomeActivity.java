package com.abort.traveler;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.abort.traveler.Common.Common;
import com.abort.traveler.Eventbus.CategoryHomeClick;
import com.abort.traveler.Eventbus.PlaceClick;
import com.abort.traveler.Eventbus.StatesClick;
import com.abort.traveler.Eventbus.SummaryClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.PaymentResultListener;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements PaymentResultListener {

    private AppBarConfiguration mAppBarConfiguration;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_booking)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onCategogHome(CategoryHomeClick event)
    {
        if (event.isSuccess())
        {

            navController.navigate(R.id.nav_gallery);
        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onStateClick(StatesClick event)
    {
        if (event.isSuccess())
        {

            navController.navigate(R.id.nav_place);


        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onPlaceClick(PlaceClick event)
    {
        if (event.isSuccess())
        {

            navController.navigate(R.id.nav_placeDetails);


        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onBookClick(SummaryClick event)
    {
        if (event.isSuccess())
        {

            navController.navigate(R.id.nav_summary);


        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPaymentSuccess(String s) {
        Common.currentSymaryModel.setTransactionId(s);
        FirebaseDatabase.getInstance().getReference(Common.BOOKING)
                .child(Common.currentUserModel.getUid())
                .setValue(Common.currentSymaryModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(HomeActivity.this, "Payment Success ! Your Booking is Comformed", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.nav_booking);
                    }
                });

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}