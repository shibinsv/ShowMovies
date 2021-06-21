package com.example.anew.fragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new TopRatedMoviesFragment();
            case 2:
                return new UpcomingMoviesFragment();
        }
        return new PopularMoviesFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Popular";
        else if (position == 1)
            title = "Top-Rated";
        else if (position == 2)
            title = "Upcoming";
        return title;
    }
}
