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


import com.example.rivas.salon_rmr.Fragment.ContactFragment;
import com.example.rivas.salon_rmr.R;
import com.example.rivas.salon_rmr.Fragment.HomeFragment;
import com.example.rivas.salon_rmr.Fragment.ProductFragment;
import com.example.rivas.salon_rmr.Fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    ViewPager viewPager;
    AdaptadorFragmento adaptador;
    private Toolbar toolbar;
    private MenuItem menuItem;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

         bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnNavigationItemSelectedListener(navListener);
        viewPager = findViewById(R.id.viewPager);

        setupViewPage(viewPager);

        toolbar = findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null)
                    menuItem.setChecked(false);
                else
                    bottomNav.getMenu().getItem(0).setChecked(false);
                bottomNav.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNav.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
                        case R.id.nav_contact:
                            viewPager.setCurrentItem(3);
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

