package com.example.gadso;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    Context mcontext;
    public ViewPagerAdapter( FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return fragmentList.size();
    }
    public void addfragment(Fragment fragment, String string){
        fragmentList.add(fragment);
        stringList.add(string);

    }
}