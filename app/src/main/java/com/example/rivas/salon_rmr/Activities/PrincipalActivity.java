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
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rivas.salon_rmr.R;
import com.example.rivas.salon_rmr.fragment.HomeFragment;
import com.example.rivas.salon_rmr.fragment.ProductFragment;
import com.example.rivas.salon_rmr.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    ViewPager viewPager;
    AdaptadorFragmento adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnNavigationItemSelectedListener(navListener);
        viewPager = findViewById(R.id.viewPager);

        setupViewPage(viewPager);

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

