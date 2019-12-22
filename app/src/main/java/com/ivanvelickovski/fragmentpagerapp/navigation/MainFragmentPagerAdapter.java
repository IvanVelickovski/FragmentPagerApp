package com.ivanvelickovski.fragmentpagerapp.navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ivanvelickovski.fragmentpagerapp.help.HelpFragment;
import com.ivanvelickovski.fragmentpagerapp.info.InfoFragment;
import com.ivanvelickovski.fragmentpagerapp.search.SearchFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    *
    * Pager adapter that keeps state of the fragments inside the bottom page navigation tabs
    *
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    //region Statics
    private static final List<Fragment> BASE_FRAGMENTS = Arrays.asList(InfoFragment.newInstance(), SearchFragment.newInstance(), HelpFragment.newInstance());

    private static final int INFO_POSITION = 0;
    private static final int SEARCH_POSITION = 1;
    private static final int HELP_POSITION = 2;
    //endregion

    //region Fields
    private List<Fragment> mInfoFragments;
    private List<Fragment> mSearchFragments;
    private List<Fragment> mHelpFragments;
    //endregion

    //region constructor
    public MainFragmentPagerAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
        mInfoFragments = new ArrayList<>();
        mSearchFragments = new ArrayList<>();
        mHelpFragments = new ArrayList<>();
    }
    //endregion

    //region FragmentPagerAdapter overridden methods
    @Override
    @NonNull
    public Fragment getItem(int position) {
        if (position == INFO_POSITION) {
            if (mInfoFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mInfoFragments.get(mInfoFragments.size() - 1);
        } else if (position == SEARCH_POSITION) {
            if (mSearchFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mSearchFragments.get(mSearchFragments.size() - 1);
        } else {
            if (mHelpFragments.isEmpty()) {
                return BASE_FRAGMENTS.get(position);
            }
            return mHelpFragments.get(mHelpFragments.size() - 1);
        }
    }

    @Override
    public int getCount() {
        return BASE_FRAGMENTS.size();
    }

    @Override
    public long getItemId(int position) {
        if (position == INFO_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return INFO_POSITION;
        } else if (position == SEARCH_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return SEARCH_POSITION;
        } else if (position == HELP_POSITION
                && getItem(position).equals(BASE_FRAGMENTS.get(position))) {
            return HELP_POSITION;
        }

        return getItem(position).hashCode();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
    //endregion

    //region helper methods
    public void updateFragment(Fragment fragment, int position) {
        if (!BASE_FRAGMENTS.contains(fragment)) {
            addInnerFragment(fragment, position);
        }
        notifyDataSetChanged();
    }

    public boolean removeFragment(Fragment fragment, int position) {
        if (position == INFO_POSITION) {
            if (mInfoFragments.contains(fragment)) {
                removeInnerFragment(fragment, mInfoFragments);
                return true;
            }
        } else if (position == SEARCH_POSITION) {
            if (mSearchFragments.contains(fragment)) {
                removeInnerFragment(fragment, mSearchFragments);
                return true;
            }
        } else if (position == HELP_POSITION) {
            if (mHelpFragments.contains(fragment)) {
                removeInnerFragment(fragment, mHelpFragments);
                return true;
            }
        }

        return false;
    }

    private void removeInnerFragment(Fragment fragment, List<Fragment> tabFragments) {
        tabFragments.remove(fragment);
        notifyDataSetChanged();
    }

    private void addInnerFragment(Fragment fragment, int position) {
        if (position == INFO_POSITION) {
            mInfoFragments.add(fragment);
        } else if (position == SEARCH_POSITION) {
            mSearchFragments.add(fragment);
        } else {
            mHelpFragments.add(fragment);
        }
    }
    //endregion
}