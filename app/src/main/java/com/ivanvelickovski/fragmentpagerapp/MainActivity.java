package com.ivanvelickovski.fragmentpagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ivanvelickovski.fragmentpagerapp.help.HelpFragment;
import com.ivanvelickovski.fragmentpagerapp.info.InfoFragment;
import com.ivanvelickovski.fragmentpagerapp.navigation.FragmentUpdateCallback;
import com.ivanvelickovski.fragmentpagerapp.navigation.MainFragmentPagerAdapter;
import com.ivanvelickovski.fragmentpagerapp.search.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentUpdateCallback {

    //region VI
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNavigationView;
    //endregion

    //region Fields
    MainFragmentPagerAdapter mPagerAdapter;
    private int mCurrentTabPosition;
    //endregion

    //region Activity lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menuInfo:
                    mCurrentTabPosition = InfoFragment.TAB_POSITION;
                    mViewPager.setCurrentItem(mCurrentTabPosition);
                    return true;
                case R.id.menuSearch:
                    mCurrentTabPosition = SearchFragment.TAB_POSITION;
                    mViewPager.setCurrentItem(mCurrentTabPosition);
                    return true;
                case R.id.menuHelp:
                    mCurrentTabPosition = HelpFragment.TAB_POSITION;
                    mViewPager.setCurrentItem(mCurrentTabPosition);
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mPagerAdapter.removeFragment(mPagerAdapter.getItem(mCurrentTabPosition), mCurrentTabPosition)) {
            finish();
        }
    }
    //endregion

    //region FragmentUpdateCallback implementation
    @Override
    public void addFragment(Fragment fragment, int tabPosition) {
        mPagerAdapter.updateFragment(fragment, tabPosition);
    }
    //endregion
}
