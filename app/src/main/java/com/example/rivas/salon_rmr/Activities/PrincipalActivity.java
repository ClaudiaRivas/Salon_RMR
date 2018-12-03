package com.example.rivas.salon_rmr.Activities;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.rivas.salon_rmr.Apputilities.BaseFragment;
import com.example.rivas.salon_rmr.Fragment.ContactFragment;
import com.example.rivas.salon_rmr.R;
import com.example.rivas.salon_rmr.Fragment.HomeFragment;
import com.example.rivas.salon_rmr.Fragment.ProductFragment;
import com.example.rivas.salon_rmr.Fragment.ServiceFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements BaseFragment.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener{



    //para manejo de fragmentos
    private final int INDEX_HOME        = FragNavController.TAB1;
    private final int INDEX_PRODUCTS    = FragNavController.TAB2;
    private final int INDEX_SERVICES    = FragNavController.TAB3;
    private final int INDEX_CONTACT     = FragNavController.TAB4;

    private FragNavController mNavController;

    private static HomeFragment     frm1 = new HomeFragment();
    private static ProductFragment  frm2 = new ProductFragment();
    private static ServiceFragment  frm3 = new ServiceFragment();
    private static ContactFragment  frm4 = new ContactFragment();

    ViewPager viewPager;
    AdaptadorFragmento adaptador;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //viewPager = findViewById(R.id.viewPager);

        //setupViewPage(viewPager);

        toolbar = findViewById(R.id.toolbar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();



        //para manejo de fragmentos
        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, 4)
                .defaultTransactionOptions(FragNavTransactionOptions.newBuilder().customAnimations(R.anim.alpha_in, R.anim.alpha_out,R.anim.alpha_in,R.anim.alpha_out).build())
                .build();

        //TODO prueba
        bottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                mNavController.clearStack();
            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            mNavController.switchTab(INDEX_HOME);
                            break;
                        case R.id.nav_product:
                            mNavController.switchTab(INDEX_PRODUCTS);
                            break;
                        case R.id.nav_service:
                            mNavController.switchTab(INDEX_SERVICES);
                            break;
                        case R.id.nav_contact:
                            mNavController.switchTab(INDEX_CONTACT);
                            break;
                    }
                    return true;
                }
            };
    private void setupViewPage(ViewPager viewPager){
        adaptador = new AdaptadorFragmento(getSupportFragmentManager());
        adaptador.addFragment(new HomeFragment());
        adaptador.addFragment(new ProductFragment());
        adaptador.addFragment(new ServiceFragment());
        adaptador.addFragment(new ContactFragment());

        viewPager.setAdapter(adaptador);

    }

    //para manejo de fragmentos
    @Override
    public void onBackPressed() {
        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public Fragment getRootFragment(int i) {
        switch (i) {
            case INDEX_HOME:
                return frm1;
            case INDEX_PRODUCTS:
                return frm2;
            case INDEX_SERVICES:
                return frm3;
            case INDEX_CONTACT:
                return frm4;
        }
        throw new IllegalStateException("-------------------------Need to send an index that we know");
    }

    @Override
    public void onTabTransaction(Fragment fragment, int i) {

    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {

    }

    class AdaptadorFragmento extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public AdaptadorFragmento(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {


            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
        }
    }



}

