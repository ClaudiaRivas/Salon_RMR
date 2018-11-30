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
import com.example.rivas.salon_rmr.R;
import com.example.rivas.salon_rmr.Fragment.HomeFragment;
import com.example.rivas.salon_rmr.Fragment.ProductFragment;
import com.example.rivas.salon_rmr.Fragment.ServiceFragment;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class PrincipalActivity extends AppCompatActivity implements BaseFragment.FragmentNavigation,  FragNavController.TransactionListener, FragNavController.RootFragmentListener{


    private final int INDEX_HOME    = FragNavController.TAB1;
    private final int INDEX_PRODUCT = FragNavController.TAB2;
    private final int INDEX_SERVICE = FragNavController.TAB3;
    private final int INDEX_CONTACT = FragNavController.TAB4;

    ViewPager viewPager;
    AdaptadorFragmento adaptador;

    private FragNavController mNavController;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnNavigationItemSelectedListener(navListener);
        viewPager = findViewById(R.id.viewPager);

        setupViewPage(viewPager);

        toolbar = findViewById(R.id.toolbar);




        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, 4)
                .build();



        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.nav_product:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.nav_service:
                            viewPager.setCurrentItem(2);
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

        viewPager.setAdapter(adaptador);

    }

    @NotNull
    @Override
    public Fragment getRootFragment(int i) {
        return null;
    }

    @Override
    public void onFragmentTransaction(@Nullable Fragment fragment, @NotNull FragNavController.TransactionType transactionType) {

    }

    @Override
    public void onTabTransaction(@Nullable Fragment fragment, int i) {

    }

    @Override
    public void pushFragment(Fragment fragment) {

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

