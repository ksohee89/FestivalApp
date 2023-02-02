package com.sample.festival_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NewPagerAdpator extends FragmentPagerAdapter {


    public NewPagerAdpator(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    //프레그먼트 교체를 보여주는 처리를 구현한곳
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return frag_map.newInstance();
            case 1:
                return frag_list.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Map";
            case 1:
                return "List";
            default:
                return null;
        }
    }
}
