package com.example.gym_polyakov;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gym_polyakov.fragmentslessons.HandsFragment;
import com.example.gym_polyakov.fragmentslessons.LegsFragment;
import com.example.gym_polyakov.fragmentslessons.SpineFragment;
import com.example.gym_polyakov.fragmentslessons.TorsoFragment;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private int count;
    private List<String> PageTitles = new ArrayList<>();
    private List<String> Urls_list;

    public PageAdapter(FragmentManager fm, int count, final List<String> Urls_list) {
        super(fm);
        this.count = count;
        this.Urls_list = Urls_list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HandsFragment(Urls_list);
            case 1:
                return new SpineFragment(Urls_list);
            case 2:
                return new TorsoFragment(Urls_list);
            case 3:
                return new LegsFragment(Urls_list);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PageTitles.get(position);
    }

    public void addTitle(String title) {
        PageTitles.add(title);
    }


}
