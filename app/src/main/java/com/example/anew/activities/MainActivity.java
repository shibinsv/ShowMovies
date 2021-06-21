package com.example.anew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.anew.BaseActivity;
import com.example.anew.R;
import com.example.anew.fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.allTabs)
    TabLayout allTabs;
    @BindView(R.id.viewContent)
    ViewPager viewContent;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabConfig();

    }

    private void tabConfig() {
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewContent.setAdapter(viewPagerAdapter);
        allTabs.setupWithViewPager(viewContent);
    }
}