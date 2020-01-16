package com.ly.autoscrolllayout;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ly.autoscrolllayout.adapter.MainPagerAdapter;

/**
 * 2020年1月16日 change
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        initNavigationView();
    }

    private void initNavigationView() {
        mNavigationView = findViewById(R.id.navigationView);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_home) {
                    selectViewPager(0);
                } else {
                    selectViewPager(1);
                }

                return true;
            }
        });
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.viewPager);
//        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavigationView.setSelectedItemId(position == 0 ? R.id.menu_home : R.id.menu_view_flipper);

                getSupportActionBar().setTitle(position == 0 ? R.string.app_name : R.string.view_flipper);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectViewPager(int position) {
        mViewPager.setCurrentItem(position);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
