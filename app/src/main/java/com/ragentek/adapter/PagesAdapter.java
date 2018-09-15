package com.ragentek.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jun.wang on 2018/9/14.
 */

public class PagesAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public PagesAdapter(FragmentManager fm) {
        super(fm);
    }

    public PagesAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
